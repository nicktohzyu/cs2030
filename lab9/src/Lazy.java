import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Lazy<T>{
    T v;
    boolean haveGotten;
    Supplier<? extends T> supplier;
    Lazy(T v){
        this.v = v;
        haveGotten = true;
    }
    Lazy(Supplier<? extends T> supplier){
        this.supplier = supplier;
        haveGotten = false;
    }
    static <T> Lazy<T> ofNullable(T v){
        return new Lazy<T>(v);
    }
    static <T> Lazy<T> generate(Supplier<? extends T> supplier){
        return new Lazy<T>(supplier);
    }
    Optional<T> get(){
        if(!haveGotten){
            v = supplier.get();
            haveGotten = true;
        }
        return Optional.ofNullable(v);
    }
    <R> Lazy<R> map(Function<? super T, ? extends R> mapper){
        return new Lazy<R>(() -> this.get().map(mapper).orElse(null));
    }

    Lazy<T> filter(Predicate<? super T> predicate){
        return new Lazy<T>(() -> this.get().filter(predicate).orElse(null));
    }

    public String toString(){
        return haveGotten ? v == null ? "null" : v.toString() : "?";
    }
}
