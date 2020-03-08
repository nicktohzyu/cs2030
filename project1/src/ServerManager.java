import java.util.PriorityQueue;

// can be modified to take multiple servers
public class ServerManager {
    //only ever one manager and does not store data, hence implemented as static
    public static void manage(PriorityQueue<Event> events, Server server) {
        double totalWaitingTime = 0;
        int customersServed = 0;
        int customersLeftWithoutBeingServed = 0;

        while (!events.isEmpty()) {
            Event event = events.poll();
            System.out.print(event.toString());
            Customer customer = event.customer;
            double eventTime = event.time;
            Event newEvent; // would have put this inside the relevant cases but compiler complains
            switch (event.state) {
                case Event.ARRIVES:
                    if (eventTime >= server.getNextServeTime()) {
                        //the server is free, customer is served immediately
                        customer.setServedTime(eventTime); //customer is served immediately
                        newEvent = new Event(customer, Event.SERVED);
                    } else {
                        if (server.isHasWaitingCustomer()) {
                            //there is already another customer waiting
                            newEvent = new Event(customer, Event.LEAVES);
                            customersLeftWithoutBeingServed++;
                        } else { //customer waits
                            customer.setServedTime(server.getNextServeTime());
                            newEvent = new Event(customer, Event.WAITS);
                            server.setHasWaitingCustomer(true);
                        }
                    }
                    events.add(newEvent);
                    break;
                case Event.WAITS: //serve the waiting customer
                    newEvent = new Event(customer, Event.SERVED);
                    events.add(newEvent);
                    break;
                case Event.SERVED:
                    server.setNextServeTime(event.customer.getServedTime() + Customer.SERVE_TIME);
                    server.setHasWaitingCustomer(false);
                    newEvent = new Event(customer, Event.DONE);
                    events.add(newEvent);
                    totalWaitingTime += customer.getServedTime() - customer.arrivalTime;
                    break;
                case Event.DONE:
                    customersServed++;
                case Event.LEAVES:
                default:
                    break;
            }
        }
        System.out.printf("[%.3f %d %d]\n", totalWaitingTime / customersServed,
                customersServed, customersLeftWithoutBeingServed);
    }
}
