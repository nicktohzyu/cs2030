import java.util.function.*;

public class EmptyList<T> extends InfiniteListImpl<T> {
    public EmptyList() {
        super(Lazy.ofNullable(null), EmptyList::new);
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public <R> InfiniteListImpl<R> map(Function<? super T, ? extends R> mapper) {
        return new EmptyList<R>();
    }

    @Override
    public InfiniteListImpl<T> filter(Predicate<? super T> predicate) {
        return this;
    }

    @Override
    public InfiniteListImpl<T> limit(long n) {
        return this;
    }

    @Override
    public InfiniteListImpl<T> takeWhile(Predicate<? super T> predicate) {
        return this;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator) {
        return identity;
    }

    @Override
    public void forEach(Consumer<? super T> action) {
    }
}
