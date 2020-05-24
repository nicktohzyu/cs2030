import cs2030.simulator.RngCustom;

import java.util.Optional;
import java.util.PriorityQueue;

/**
 * Provides a static method to simulate the discrete events.
 */
class ServerManager {
    /**
     * Manages the server by handling events and assigning customers
     * to the server as necessary. Effectively, it simulates the discrete events.
     * Prints a string representation of each event as it is processed, then
     * prints the statistics of average customer waiting time, number of
     * customers served, and number of customers who left without being served.
     *
     * @param events  A priority queue initially consisting of customers arriving.
     * @param servers An array of the servers which will serve customers.
     * @param rng     The random number generator to be used.
     */
    // only ever one manager and does not store data, hence implemented as static
    public static void manage(PriorityQueue<Event> events, Server[] servers,
                              RngCustom rng, double restingProbability) {
        Statistics statistics = new Statistics();

        while (!events.isEmpty()) {
            Event event = events.poll();
            System.out.print(event.toString());
            double eventTime = event.getTime();
            Customer customer = event.getCustomer();
            Server server = event.getServer();
            switch (event.state) {
                case Event.CUSTOMER_ARRIVES:
                    assert customer != null;
                    handleCustomerArrival(events, servers, statistics, eventTime, customer);
                    break;
                case Event.CUSTOMER_SERVED:
                    assert customer != null;
                    assert server != null;
                    handleCustomerServed(events, rng, eventTime, statistics, customer, server);
                    break;
                case Event.CUSTOMER_DONE:
                    assert server != null;
                    handleCustomerDone(events, rng, restingProbability,
                            statistics, eventTime, server);
                    break;
                case Event.CUSTOMER_LEAVES:
                    // checkstyle doesn't allow fall through
                    break;
                case Event.CUSTOMER_WAITS:
                    // no event added as customer is in a server queue
                    // checkstyle doesn't allow fall through
                    break;
                case Event.SERVER_REST:
                    assert server != null;
                    handleServerRest(events, rng, eventTime, server);
                    break;
                case Event.SERVER_BACK:
                    assert server != null;
                    handleServerBack(events, eventTime, server);
                    break;
                case Event.SERVER_NEXT_CUSTOMER:
                    assert server != null;
                    handleServerGetNextCustomer(events, eventTime, server);
                    break;
                default:
                    break;
            }
        }
        System.out.println(statistics.toString());
    }

    private static void handleServerGetNextCustomer(PriorityQueue<Event> events,
                                                    double eventTime, Server server) {
        if (server.hasWaitingCustomer()) {
            // serve the next customer
            Customer nextCustomer = server.nextCustomer();
            events.add(new Event(nextCustomer, Event.CUSTOMER_SERVED, server, eventTime));
        }
    }

    private static void handleServerBack(PriorityQueue<Event> events,
                                         double eventTime, Server server) {
        server.endBreak();
        events.add(new Event(null, Event.SERVER_NEXT_CUSTOMER, server, eventTime));
    }

    private static void handleServerRest(PriorityQueue<Event> events, RngCustom rng,
                                         double eventTime, Server server) {
        double restPeriod = rng.genRestPeriod();
        events.add(new Event(null, Event.SERVER_BACK, server, eventTime + restPeriod));
    }

    private static void handleCustomerDone(PriorityQueue<Event> events, RngCustom rng,
                                           double restingProbability, Statistics statistics,
                                           double eventTime, Server server) {
        statistics.customersServed++;
        if (server.canRest() && rng.genRandomRest() < restingProbability) {
            // server rests
            server.takeBreak();
            events.add(new Event(null, Event.SERVER_REST, server, eventTime));
        } else {
            // server serves next customer
            events.add(new Event(null, Event.SERVER_NEXT_CUSTOMER, server, eventTime));
        }
    }

    private static void handleCustomerServed(PriorityQueue<Event> events, RngCustom rng,
                                             double eventTime, Statistics statistics,
                                             Customer customer, Server server) {
        customer.setServedTime(eventTime);
        double serviceTime = rng.genServiceTime();
        server.serveCustomer(customer, serviceTime);
        events.add(new Event(customer, Event.CUSTOMER_DONE, server, customer.getDoneTime()));
        statistics.totalWaitingTime += customer.getWaitingTime();
    }

    private static void handleCustomerArrival(PriorityQueue<Event> events, Server[] servers,
                                              Statistics statistics, double eventTime,
                                              Customer customer) {
        //sorry for the disgusting folding; had to appease the checkstyle gods
        Optional<Server> potentialServerOptional = immediatelyAvailableServer(customer, servers)
                .or(() -> enqueueableServer(customer, servers));

        potentialServerOptional.ifPresentOrElse(
            //if the server is available, add a queue/wait event
            (potentialServer) -> events.add(new Event(customer,
                    potentialServer.getQueueLength() > 0
                            ? Event.CUSTOMER_WAITS
                            : Event.CUSTOMER_SERVED,
                    potentialServer, eventTime)),
            //otherwise, add a leave event
            () -> {
                statistics.customersLeftWithoutBeingServed++;
                events.add(new Event(customer, Event.CUSTOMER_LEAVES, null, eventTime));
            }
        );
    }

    private static Optional<Server> immediatelyAvailableServer(Customer customer,
                                                               Server[] servers) {
        for (Server potentialServer : servers) {
            if (potentialServer.canServeImmediately(customer)) {
                // customer is served immediately
                return Optional.of(potentialServer);
            }
        }
        return Optional.empty();
    }

    private static Optional<Server> enqueueableServer(Customer customer, Server[] servers) {
        if (customer.isGreedy()) {
            // greedy customer
            Server shortestQueueServer = servers[0];
            for (int i = 1; i < servers.length; i++) {
                Server potentialServer = servers[i];
                if (potentialServer.getQueueLength() < shortestQueueServer.getQueueLength()) {
                    shortestQueueServer = potentialServer;
                }
            }
            // check if the shortest Queue can be joined
            if (shortestQueueServer.canEnqueueCustomer()) {
                shortestQueueServer.enqueueCustomer(customer);
                return Optional.of(shortestQueueServer);
            }
        } else {
            // typical customer
            for (Server potentialServer : servers) {
                if (potentialServer.canEnqueueCustomer()) {
                    potentialServer.enqueueCustomer(customer);
                    return Optional.of(potentialServer);
                }
            }
        }
        return Optional.empty();
    }
}
