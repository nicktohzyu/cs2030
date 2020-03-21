import java.util.PriorityQueue;

/**
 * Provides a static function to simulate the discrete events.
 */
// can be modified to take multiple servers
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
     */
    //only ever one manager and does not store data, hence implemented as static
    public static void manage(PriorityQueue<Event> events, Server[] servers) {
        double totalWaitingTime = 0;
        int customersServed = 0;
        int customersLeftWithoutBeingServed = 0;

        while (!events.isEmpty()) {
            Event event = events.poll();
            System.out.print(event.toString());
            Customer customer = event.customer;
            switch (event.state) {
                case Event.ARRIVES:
                    boolean served = false;
                    for (int i = 0; i < servers.length && !served; i++) {
                        if (servers[i].canServeImmediately(customer)) {
                            //customer is served immediately
                            customer.setServedTime(customer.getArrivalTime());
                            served = true;
                            events.add(new Event(customer, Event.SERVED, servers[i]));
                        }
                    }
                    if (served) {
                        break;
                    }
                    for (int i = 0; i < servers.length && !served; i++) {
                        if (!servers[i].isHasWaitingCustomer()) {
                            customer.setServedTime(servers[i].getNextServeTime());
                            servers[i].setHasWaitingCustomer(true);
                            served = true;
                            events.add(new Event(customer, Event.WAITS, servers[i]));
                        }
                    }
                    if (served) {
                        break;
                    } else {
                        customersLeftWithoutBeingServed++;
                        events.add(new Event(customer, Event.LEAVES));
                    }
                    break;
                case Event.WAITS: //serve the waiting customer
                    events.add(new Event(customer, Event.SERVED, event.server));
                    break;
                case Event.SERVED:
                    event.server.serveCustomer(customer);
                    events.add(new Event(customer, Event.DONE, event.server));
                    totalWaitingTime += customer.getWaitingTime();
                    break;
                case Event.DONE:
                    customersServed++;
                    break;
                case Event.LEAVES: // checkstyle doesn't allow fall through
                    break;
                default:
                    break;
            }
        }
        System.out.printf("[%.3f %d %d]\n", totalWaitingTime / customersServed,
                customersServed, customersLeftWithoutBeingServed);
    }
}
