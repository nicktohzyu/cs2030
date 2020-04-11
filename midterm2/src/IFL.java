import java.util.Arrays;
import java.util.function.Supplier;
import java.util.function.Predicate;
import java.util.ArrayList;
import java.util.List;

class IFL<T> {
    Supplier<T> head;
    Supplier<IFL<T>> tail;

    // Fill in any additional fields and methods (if any) in the
// answer box for Question 2 on Examplify
    IFL(Supplier<T> head, Supplier<IFL<T>> tail) {
        this.head = head;
        this.tail = tail;
    }

    static <T> IFL<T> of(List<? extends T> list) {
        if (list.isEmpty()) {

            return null;

        } else {

            return new IFL(() -> list.get(0), () -> IFL.of(list.subList(1, list.size())));

        }
    }

    boolean allMatch(Predicate<? super T> predicate) {
        IFL<T> t = tail.get();

        if(t == null) return true;

        return predicate.test(head.get()) && t.allMatch(predicate);
    }

    public static void main(String[] args) {
        IFL<String> list = IFL.of(Arrays.asList("three", "little", "pigs"));
        list.allMatch(str -> str.length() < 10);
    }
}
