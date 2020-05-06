import java.util.function.*;

public class Voem<T> {
    boolean isOK;
    T v;
    String message;

    Voem(boolean isOK, T v, String message) {
        this.isOK = isOK;
        this.v = v;
        this.message = message;
    }

    public static <T> Voem<T> ok(T v) {
        return new Voem<T>(true, v, null);
    }

    public static <T> Voem<T> fail(String message) {
        return new Voem<T>(false, null, message);
    }

    public <U> Voem<U> map(Function<? super T, ? extends U> mapper) {
        try {
            if (!isOK) return fail(message);
            U newValue = mapper.apply(v);
            return new Voem<U>(true, newValue, null);
        } catch (Exception e) {
            return new Voem<U>(false, null, e.getMessage());
        }
    }

    public <U> Voem<U> flatMap(Function<? super T, Voem<U>> mapper) {
        try {
            if (!isOK) return fail(message);
            return mapper.apply(v);
        } catch (Exception e) {
            return new Voem<U>(false, null, e.getMessage());
        }
    }

    T orElse(T otherValue) {
        return isOK ? v : otherValue;
    }

    @Override
    public String toString() {
        if (isOK) {
            return ("Done: " + v);
        } else {
            return "Oops: " + message;
        }
    }
}
