import java.util.*;

    class D{
        public static <T, U extends T> List<T> add(List<T> t, U u) {
            ArrayList<T> l = new ArrayList<T>(t);
            l.add(u);
            return l;
        }
        public static <T, U extends T> List<T> join(List<T> t, List<U> u) {
            if(t == u) return t;
            ArrayList<T> l = new ArrayList<T>(t);
            l.addAll(u);
            return l;
        }
    }
