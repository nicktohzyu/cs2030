import java.time.Instant;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

/**
 * This program finds different ways one can travel by bus (with a bit
 * of walking) from one bus stop to another.
 *
 * @author: Ooi Wei Tsang
 * @version: CS2030 AY19/20 Semester 1, Lab 10
 */
public class Main {
    /**
     * The program read a sequence of (id, search string) from standard input.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        Instant start = Instant.now();
        Scanner sc = new Scanner(System.in);
        List<CompletableFuture<String>> list = new ArrayList<>();
        while (sc.hasNext()) {
            BusStop srcId = new BusStop(sc.next());
            String searchString = sc.next();
            list.add(BusSg.findBusServicesBetween(srcId, searchString).thenCompose(BusRoutes::description));
        }
        sc.close();

        CompletableFuture<?>[] array = list.toArray(new CompletableFuture<?>[0]);
        CompletableFuture.allOf(array).join();

        for (CompletableFuture<?> completableFuture : array) {
            completableFuture.thenAccept(System.out::println);
        }

        Instant stop = Instant.now();
        System.out.printf("Took %,dms\n", Duration.between(start, stop).toMillis());
    }
}
