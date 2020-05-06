import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MinMax {
    final int min, max;

    public MinMax(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public String toString() {
        return min + ", " + max;
    }
    static Optional<MinMax> findMinMax(Stream<Integer> instream) {
        return instream.reduce(Optional.empty(), (o, i) -> o.isEmpty() ? Optional.of(new MinMax(i,i)) :
                o.map(m -> new MinMax(Math.min(m.min, i), Math.max(m.max, i))),
                (o1, o2) -> o1 );
    }
    public static void main(String[] args) {
        Stream<Integer> instream = IntStream
                .rangeClosed(1, 0)
                .mapToObj(x -> Integer.valueOf(x));
        System.out.println(findMinMax(instream));
    }
}
