import java.util.Scanner;
import java.util.PriorityQueue;

import cs2030.simulator.RngCustom;

/**
 * Provides a static method to scan input and initialize the simulation classes.
 */
public class Main {
    /**
     * Scans customer arrival times from input, then creates events representing their arrival
     * and puts them into a priority queue, then passes it to the manage method to
     * simulate the discrete events.
     *
     * @param args input arguments.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int randomSeed = scanner.nextInt();
        int numberOfServers = scanner.nextInt();
        int numberOfSelfCheckout = scanner.nextInt();
        int maximumQueueLength = scanner.nextInt();
        int numberOfCustomers = scanner.nextInt();
        double lambda = scanner.nextDouble();
        double mu = scanner.nextDouble();
        double rho = scanner.nextDouble();
        double restingProbability = scanner.nextDouble();
        double greedyProbability = scanner.nextDouble();
        scanner.close();

        assert lambda > 0 && mu > 0 && rho > 0 : "exponential parameters must be positive";
        RngCustom rng = new RngCustom(randomSeed, lambda, mu, rho);
        PriorityQueue<Event> events = new PriorityQueue<>();

        //generate customer arrival events, insert them into the events pqueue
        generateCustomerArrivals(numberOfCustomers, greedyProbability, rng, events);

        //initialize array of servers
        Server[] servers = initializeServers(numberOfServers,
                numberOfSelfCheckout, maximumQueueLength);

        //pass priority queue of customers arriving to ServerManager and run the simulation
        ServerManager.manage(events, servers, rng, restingProbability);
    }

    private static Server[] initializeServers(
            int numberOfServers, int numberOfSelfCheckout, int maximumQueueLength) {
        Server[] servers = new Server[numberOfServers + numberOfSelfCheckout];
        for (int i = 0; i < numberOfServers; i++) { //human servers
            CustomerQueue customerQueue = new CustomerQueue(i + 1);
            servers[i] = new HumanServer(i + 1 /*1-indexed IDs*/,
                    maximumQueueLength, customerQueue);
        }
        //self checkouts
        CustomerQueue selfCheckoutQueue = new CustomerQueue(
                numberOfServers + 1);
        for (int i = numberOfServers; i < numberOfServers + numberOfSelfCheckout;
             i++) { //human servers
            servers[i] = new SelfCheckout(i + 1 /*1-indexed IDs*/, maximumQueueLength,
                    selfCheckoutQueue);
        }
        return servers;
    }

    private static void generateCustomerArrivals(int numberOfCustomers, double greedyProbability,
                                                 RngCustom rng, PriorityQueue<Event> events) {
        double arrivalTime = 0;
        for (int customerNumber = 1; customerNumber <= numberOfCustomers; customerNumber++) {
            //generate customers and add their arrival into event queue
            if (customerNumber > 1) {
                // start the simulation by generating the first customer arrival event
                // with timestamp 0. If there are still more customers to simulate, generate
                // the next arrival event with a timestamp of T + now, where T is generated
                // with the method genInterArrivalTime.
                arrivalTime += rng.genInterArrivalTime();
            }
            boolean greedy = rng.genCustomerType() < greedyProbability;
            Customer customer;
            if (greedy) {
                customer = new GreedyCustomer(arrivalTime, customerNumber);
            } else {
                customer = new TypicalCustomer(arrivalTime, customerNumber);
            }
            Event arrivalEvent = new Event(customer, Event.CUSTOMER_ARRIVES, null, arrivalTime);
            events.add(arrivalEvent);
        }
    }
}
