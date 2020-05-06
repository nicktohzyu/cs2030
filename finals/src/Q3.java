import java.util.stream.Stream;

public class Q3 {

    static void scanThis(Stream<? super A1> s){

    }

    public static void main(String[] args) {
        scanThis(Stream.of(1).map(x -> new A3()) );
    }
    static class A1 {

    }
    static class A2 extends A1 {

    }
    static class A3 extends A2 {

    }
    static class A4 extends A3 {

    }
    static class A5 extends A4 {

    }
}
