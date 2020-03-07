import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int customersServed = 0;
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            System.out.printf("%d arrives at %.3f\n", ++customersServed, scanner.nextDouble());
        }
        System.out.printf("Number of customers: %d\n", customersServed);
    }
}
