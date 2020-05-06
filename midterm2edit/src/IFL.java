import java.util.function.Supplier;
import java.util.function.Predicate;
import java.util.ArrayList;
import java.util.List;

class IFL<T> {
    Supplier<T> head;
    Supplier<IFL<T>> tail;
    /* FIELDS AND METHODS START: DO NOT REMOVE */
    boolean isEmpty = false;
    /* FIELDS AND METHODS END: DO NOT REMOVE */

    IFL(Supplier<T> head, Supplier<IFL<T>> tail) {
        this.head = head;
        this.tail = tail;
    }

    static <T> IFL<T> of(List<? extends T> list) {
        /* OF START: DO NOT REMOVE!!! */
        if (list.isEmpty()) {
            return null;
        } else {
            return new IFL<T>(() -> list.get(0), () -> IFL.of(list.subList(1, list.size())));
        }
        /* OF END: DO NOT REMOVE!!! */
    }

    boolean allMatch(Predicate<? super T> predicate) {
        /* ALLMATCH START: DO NOT REMOVE!!! */
        IFL<T> t = tail.get();
        if (t == null) return predicate.test(head.get());
        return predicate.test(head.get()) && t.allMatch(predicate);
        /* ALLMATCH END: DO NOT REMOVE!!! */
    }
}

/* ADDITIONAL CODE START: DO NOT REMOVE */

/* ADDITIONAL CODE END: DO NOT REMOVE */
