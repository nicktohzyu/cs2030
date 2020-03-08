import java.util.Scanner;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
        int customersServed = 0;
        Server server = new Server();
        PriorityQueue<Event> events = new PriorityQueue<>();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            Customer customer = new Customer(scanner.nextDouble(), ++customersServed);
            Event arrivalEvent = new Event(customer, Event.ARRIVES);
            events.add(arrivalEvent);
        }
        ServerManager.manage(events, server);
    }
}
