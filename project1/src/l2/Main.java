import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int customersServed = 0;
        double nextServeTime = 0;
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            Customer customer = new Customer(scanner.nextDouble(), ++customersServed);
            System.out.printf("%d arrives at %.3f\n", customer.id, customer.arrivalTime);
            if(customer.arrivalTime >= nextServeTime){
                nextServeTime = customer.arrivalTime + 1;
                System.out.printf("Customer served; next service @ %.3f\n", nextServeTime);
            } else{
                System.out.println("Customer leaves");
            }
        }
        System.out.printf("Number of customers: %d\n", customersServed);
    }
}
