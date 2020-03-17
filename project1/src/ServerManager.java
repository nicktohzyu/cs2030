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
     * @param events A priority queue initially consisting of customers arriving.
     * @param server The server which will serve customers.
     */
    //only ever one manager and does not store data, hence implemented as static
    public static void manage(PriorityQueue<Event> events, Server server) {
        double totalWaitingTime = 0;
        int customersServed = 0;
        int customersLeftWithoutBeingServed = 0;

        while (!events.isEmpty()) {
            Event event = events.poll();
            System.out.print(event.toString());
            Customer customer = event.customer;
            switch (event.state) {
                case Event.ARRIVES:
                    int outcomeState = server.customerArrives(customer);
                    if (outcomeState == Event.LEAVES) {
                        customersLeftWithoutBeingServed++;
                    }
                    events.add(new Event(customer, outcomeState));
                    break;
                case Event.WAITS: //serve the waiting customer
                    events.add(new Event(customer, Event.SERVED));
                    break;
                case Event.SERVED:
                    server.serveCustomer(customer);
                    events.add(new Event(customer, Event.DONE));
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
