import java.util.ArrayList;
import java.util.Optional;
import java.util.function.*;

public class InfiniteListImpl<T> implements InfiniteList<T> {
    private final Lazy<T> head;
    private final Supplier<InfiniteListImpl<T>> tail;

    public InfiniteListImpl(Lazy<T> head, Supplier<InfiniteListImpl<T>> tail) {
        this.head = head;
        this.tail = tail;
    }

    public static <T> InfiniteListImpl<T> generate(Supplier<? extends T> supplier) {
        Lazy<T> newHead = new Lazy<T>(supplier);
        Supplier<InfiniteListImpl<T>> newTail = () -> InfiniteListImpl.generate(supplier);
        return new InfiniteListImpl<T>(newHead, newTail);
    }

    public static <T> InfiniteListImpl<T> iterate(T seed, UnaryOperator<T> next) {
        Lazy<T> newHead = new Lazy<>(seed);
        Supplier<InfiniteListImpl<T>> newTail = () -> InfiniteListImpl.iterate(next.apply(seed), next);
        return new InfiniteListImpl<T>(newHead, newTail);
    }

    public InfiniteList<T> peek() {
        head.get().ifPresent(System.out::println);
        return tail.get();
    }

    @Override
    public <R> InfiniteListImpl<R> map(Function<? super T, ? extends R> mapper) {
        Lazy<R> newHead = new Lazy<>(() -> head.get().map(mapper).orElse(null));
        Supplier<InfiniteListImpl<R>> newTail = () -> tail.get().map(mapper);
        return new InfiniteListImpl<R>(newHead, newTail);
    }

    @Override
    public InfiniteListImpl<T> filter(Predicate<? super T> predicate) {
        Lazy<T> newHead = new Lazy<>(() -> head.get().filter(predicate).orElse(null));
        Supplier<InfiniteListImpl<T>> newTail = () -> tail.get().filter(predicate);
        return new InfiniteListImpl<T>(newHead, newTail);
    }

    @Override
    public InfiniteListImpl<T> limit(long n) {
        if (n <= 0) {
            return new EmptyList<>();
        } else if (n == 1) {
            return new InfiniteListImpl<>(head, () -> head.get().isPresent() ? new EmptyList<T>() : tail.get().limit(1));
        } else {
            return new InfiniteListImpl<>(head, () -> tail.get().limit(head.get().isPresent() ? n - 1 : n));
        }
    }

    @Override
    public InfiniteListImpl<T> takeWhile(Predicate<? super T> predicate) {
        Lazy<T> newHead = new Lazy<T>(() -> head.get().filter(predicate).orElse(null));
        return new InfiniteListImpl<>(newHead, () -> newHead.get().isPresent() || head.get().isEmpty() ? tail.get().takeWhile(predicate) : new EmptyList<T>());
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Optional<T> elem = head.get();
        if (elem != Optional.empty()) {
            elem.ifPresent(action);
        }
        tail.get().forEach(action);
    }

    @Override
    public Object[] toArray() {
        ArrayList<T> arr = new ArrayList<T>();
        forEach(arr::add);
        return arr.toArray();
    }

    @Override
    public long count() {
        return head.get().isPresent() ? tail.get().count() + 1 : tail.get().count();
    }

    @Override
    public <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator) {
        return head.get().isPresent() ? tail.get().reduce(accumulator.apply(identity, head.get().get()), accumulator) : tail.get().reduce(identity, accumulator);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

}
