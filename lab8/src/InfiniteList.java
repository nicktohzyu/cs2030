import java.util.Optional;
import java.util.function.*;

public interface InfiniteList<T> {
    Optional<T> get();
    InfiniteList<T> limit(long count);
    void forEach(Consumer<? super T> action);
    Object[] toArray();
    <S> InfiniteList<S> map(Function<? super T, ? extends S> mapper);
    InfiniteList<T> filter(Predicate<? super T> predicate);
    InfiniteList<T> takeWhile(Predicate<? super T> predicate);
    static <T> InfiniteList<T> generate(Supplier<? extends T> supplier){
        return new InfiniteListImpl<T>() {
            public Optional<T> get(){
                return Optional.of(supplier.get());
            }
        };
    }
    static <T> InfiniteList<T> iterate(T seed, UnaryOperator<T> f){
        return new InfiniteListImpl<T>() {
            boolean first = true;
            T element = seed;
            public Optional<T> get(){
                if(first){
                    first = false;
                    return Optional.of(element);
                } else {
                    element = f.apply(element);
                    return Optional.of(element);
                }
            }
        };
    }
    long count();
    Optional<T> reduce(BinaryOperator<T> accumulator);
    T reduce(T identity, BinaryOperator<T> accumulator);
}
