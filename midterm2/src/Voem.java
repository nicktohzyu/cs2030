import java.util.*;
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

   public static <T> Voem<T> ok(T v){

      return new Voem<T>(true, v, null);

   }

   public static <T> Voem<T> fail(String message){

      return new Voem<T>(false, null, message);

   }

    public <U> Voem<U> map(Function<? super T, ? extends U> mapper) {

        try {

            U newValue = mapper.apply(v);

            return new Voem<U>(true, newValue, null);

        } catch (Exception e) {

            return new Voem<U>(false, null, e.getMessage());

        }

    }


    public <U> Voem<U> flatMap(Function<? super T, Voem<U>> mapper) {

        try {

            return mapper.apply(v);

        } catch (Exception e) {

            return new Voem<U>(false, null, e.getMessage());

        }

    }


    @Override

    public String toString() {

        if (isOK) {

            return ("Done: " + v);

        } else {

            return "Oops: " + message;

        }

    }

    public static void main(String[] args) {
        Voem<Integer> vOK, vFail;
        vOK = Voem.ok(1);
        vFail = Voem.fail("error");
        vOK.map(x -> x + 3);
        vOK.map(x -> x / 0);
        vFail.map(x -> x + 3);
        vFail.map(x -> x / 0);
        System.out.println(vOK.flatMap(x -> Voem.ok(x + 3)));
        System.out.println(vOK.flatMap(x -> Voem.fail("out of time")));
        vFail.flatMap(x -> Voem.fail("out of time"));
        vFail.flatMap(x -> Voem.ok(3));;
        Function<Object, Integer> f = x -> x.hashCode();
    }

}