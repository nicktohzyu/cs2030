import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

public class KeyableMap<T, K, V extends Keyable<K>> implements Keyable<T> {
    final T t;
    final HashMap<K, V> internalMap = new HashMap<>();

    KeyableMap(T t) {
        this.t = t;
    }

    Optional<V> get(K k) {
        return Optional.ofNullable(internalMap.get(k));
    }

    KeyableMap<T, K, V> put(V v) {
        internalMap.put(v.getKey(), v);
        return this;
    }

    @Override
    public T getKey() {
        return t;
    }

    public String toString() {
        String s = t + ": {";
        Iterator<Map.Entry<K, V>> it = internalMap.entrySet().iterator();
        while (it.hasNext()) {
            s += it.next().getValue().toString();
            if (it.hasNext()) {
                s += ", ";
            }
        }
        s += "}";
        return s;
    }
}
