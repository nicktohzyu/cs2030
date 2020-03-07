import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int customersServed = 0;
        double nextServeTime = 0;
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            Customer customer = new Customer(scanner.nextDouble(), ++customersServed);
            System.out.printf("%.3f %d arrives\n", customer.arrivalTime, customer.id);
            if(customer.arrivalTime >= nextServeTime){
                nextServeTime = customer.arrivalTime + 1;
                System.out.printf("%.3f %d served\n", customer.arrivalTime, customer.id);
            } else{
                System.out.printf("%.3f %d leaves\n", customer.arrivalTime, customer.id);
            }
        }
        System.out.printf("Number of customers: %d\n", customersServed);
    }
}
