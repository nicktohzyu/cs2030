import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

interface Compute<T> {
    boolean isRecursive();
    Compute<T> recurse();
    T evaluate();

    static Compute<Long> sum(long n, long s) {
        if (n == 0) {
            return new Base<>(() -> s);
        } else {
            return new Recursive<>(() -> sum(n - 1, n + s));
        }
    }
    static <R> R summer(Compute<R> c){
        Compute<R> next = c;
        while(c.isRecursive()){
            c = c.recurse();
        }
        return c.evaluate();
    }
    public static void main(String[] args) {
        System.out.println(summer(sum(100,0)));
    }
}
class Base<T> implements Compute<T> {
    Supplier<T> b;
    Base(Supplier<T> b){
        this.b=b;
    }

    @Override
    public boolean isRecursive() {
        return false;
    }

    @Override
    public Compute<T> recurse() {
        throw new IllegalStateException();
    }

    public T evaluate() {
        return b.get();
    }
}
class Recursive<T> implements Compute<T> {
    Supplier<Compute<T>> r;
    Recursive(Supplier<Compute<T>> r){
        this.r=r;
    }

    @Override
    public boolean isRecursive() {
        return true;
    }

    public Compute<T> recurse() {
        return r.get();
    }

    @Override
    public T evaluate() {
        return null;
    }
}
