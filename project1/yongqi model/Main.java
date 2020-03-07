import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Main class drives the program.
 * @author Yong Qi.
 */
public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * The main method simulates a G/M/1 queue given arrival times through
     * stdin. The result is printed to stdout.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        // Handle wrong usage.
        if (args.length > 0) {
            System.err.println("Usage: java -ea Main [< /path/to/test_file]");
            return;
        }

        List<Double> ls = new ArrayList<>();

        // Scan inputs.
        while (SCANNER.hasNext()) {
            ls.add(SCANNER.nextDouble());
        }

        // Initialise the DiscreteEventSimulator with inputs.
        DiscreteEventSimulator des = DiscreteEventSimulator.init(ls);

        // Run the DiscreteEventSimulator.
        des.run();

        // Print the results of the simulation.
        System.out.println(des);

        // Close the scanner resource.
        SCANNER.close();
    }
}

