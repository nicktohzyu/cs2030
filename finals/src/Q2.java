import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

class Q2 {
}
class S<T> extends Stream<T> {
    <R extends Stream<?>> Stream<R> nmap(BiFunction<? super T, ? super T, Stream<?>> lambda) {
        return this.map(i -> this.flatMap(j -> lambda.apply(i, j)));
    }


}