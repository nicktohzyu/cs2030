import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.*;

public abstract class InfiniteListImpl<T> implements InfiniteList<T> {
    public InfiniteList<T> limit(long count) {
        if (count < 0) {
            throw new IllegalArgumentException("" + count);
        }
        return new InfiniteListImpl<T>() {
            long n = count;

            public Optional<T> get() {
                if (n > 0) {
                    n--;
                    return InfiniteListImpl.this.get();
                } else {
                    return Optional.empty();
                }
            }
        };
    }

    public void forEach(Consumer<? super T> action) {
        while (true) {
            Optional<T> elem = get();
            if (elem == Optional.empty()) {
                break;
            } else {
                elem.ifPresent(action);
            }
        }
    }

    public Object[] toArray() {
        ArrayList<T> arr = new ArrayList<T>();
        forEach(arr::add);
        return arr.toArray();
    }

    public <S> InfiniteList<S> map(Function<? super T, ? extends S> mapper) {
        return new InfiniteListImpl<S>() {
            public Optional<S> get() {
                return InfiniteListImpl.this.get().map(mapper);
            }
        };
    }

    public InfiniteList<T> filter(Predicate<? super T> predicate) {
        return new InfiniteListImpl<T>() {
            public Optional<T> get() {
                Optional<T> elem = InfiniteListImpl.this.get();
                while (elem != Optional.empty() && !elem.map(predicate::test).get()) {
                    elem = get();
                }
                return elem;
            }
        };
    }

    public InfiniteList<T> takeWhile(Predicate<? super T> predicate) {
        return new InfiniteListImpl<T>() {
            boolean isEnd = false;

            public Optional<T> get() {
                if (isEnd) {
                    return Optional.empty();
                }
                Optional<T> elem = InfiniteListImpl.this.get();
                if (elem != Optional.empty() && elem.map(predicate::test).get()) {
                    return elem;
                }
                isEnd = true;
                return Optional.empty();
            }
        };
    }

    public long count() {
        long c = 0;
        while (get() != Optional.empty()) {
            c++;
        }
        return c;
    }

    public Optional<T> reduce(BinaryOperator<T> accumulator) {
        Optional<T> opt = get();
        if(opt == Optional.empty()){
            return opt;
        }
        return Optional.ofNullable(reduce(opt.get(), accumulator));
    }

    public T reduce(T identity, BinaryOperator<T> accumulator) {
        Optional<T> opt = get();
        while (opt != Optional.empty()) {
            T elem = opt.get();
            identity = accumulator.apply(identity, elem);
            opt = get();
        }
        return identity;
    }

    public static void main(String[] args) {
        InfiniteList.iterate(0, x -> x + 1).limit(5).reduce(0, (x, y) -> x + y);
    }
}
