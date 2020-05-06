import java.util.Arrays;
import java.util.function.Function;

public class Main {

    public static void main(String[] args) {
        Function<Object,Integer> f = x -> x.hashCode();
        Voem<Number> v = Voem.ok(null).map(f);
        System.out.println(v);
    }
}
