import java.util.Scanner;
import java.util.PriorityQueue;

public class Main {

    /**
     * Scans customer arrival times from input, then creates events representing their arrival
     * and puts them into a priority queue, then passes it to the manage method to
     * simulate the discrete events.
     * @param args input arguments.
     */
    public static void main(String[] args) {
        int customersServed = 0;
        PriorityQueue<Event> events = new PriorityQueue<>();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            Customer customer = new Customer(scanner.nextDouble(), ++customersServed);
            Event arrivalEvent = new Event(customer, Event.ARRIVES);
            events.add(arrivalEvent);
        }
        scanner.close();
        //pass priority queue of customers arriving to ServerManager
        ServerManager.manage(events, new Server());
    }
}
