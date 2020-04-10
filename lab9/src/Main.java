import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Random rnd = new Random(1);
        InfiniteList.generate(() -> Math.abs(rnd.nextInt()) % 100).takeWhile(x -> x > 5).count();
        new EmptyList<Integer>().reduce(100, (x,y) -> x*y);
        InfiniteList.iterate(0, x -> x + 1).limit(5).reduce(0, (x, y) -> x + y);
        InfiniteList.iterate(0, x -> x + 1).limit(0).reduce(0, (x, y) -> x + y);
        InfiniteList.iterate(0, x -> x + 1).map(x -> x * x).limit(5).reduce(1, (x, y) -> x * y);
    }
}
