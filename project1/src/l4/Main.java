import java.util.Scanner;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
        int customersServed = 0;
        double nextServeTime = 0;
        PriorityQueue<Customer> customers = new PriorityQueue<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("# Adding arrivals");
        while(scanner.hasNext()){
            Customer customer = new Customer(scanner.nextDouble(), ++customersServed);
            customers.add(customer);
        }
        customers.forEach(customer -> System.out.print(customer.toString()));

        while(!customers.isEmpty()){
            Customer customer = customers.poll();
            System.out.print("\n# Get next event: " + customer.toString());
            switch(customer.state){
                case Customer.ARRIVES:
                    if(customer.arrivalTime >= nextServeTime){
                        nextServeTime = customer.arrivalTime + 1;
                        customer.state = Customer.SERVED;
                        customer.servedTime = customer.arrivalTime;
                    } else{
                        customer.state = Customer.LEAVES;
                    }
                    customers.add(customer);
                    break;
                case Customer.SERVED:
                    customer.state = Customer.DONE;
                    customers.add(customer);
                    break;
                case Customer.DONE:
                case Customer.LEAVES:
                default:
                    break;
            }
            customers.forEach(c -> System.out.print(c.toString()));
        }
        System.out.printf("\nNumber of customers: %d\n", customersServed);
    }
}
