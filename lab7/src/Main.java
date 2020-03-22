import java.util.stream.Stream;

public class Main {
    static int gcd(int m, int n) {
        return Stream.iterate(new Pair(m, n), p -> p.getN() == 0 ? p : new Pair(p.getN(), p.getM() % p.getN()))
                .filter(p -> p.getN() == 0)
                .findFirst()
                .get()
                .getM();
    }
}

class Pair {
    private final int m, n;

    Pair(int m, int n) {
        this.m = m;
        this.n = n;
    }

    public int getM() {
        return m;
    }

    public int getN() {
        return n;
    }
}