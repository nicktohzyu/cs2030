import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String ...args) {
    }
//
//    public static int[] twinPrimes(int i){
//        return IntStream.rangeClosed(2, i).filter(n -> isPrime(n) && (isPrime(n-2) || isPrime(n+2))).toArray();
//    }
//    public static boolean isPrime(int number) {
//        return number != 0 && IntStream.rangeClosed(2, number/2).noneMatch(i -> number%i == 0);
//    }
    public static class Pair{
        final int m, n;
        Pair(int m, int n){
            this.m = m;
            this.n = n;
        }
    }
    public static int gcd(int m, int n){
        return Stream.iterate(new Pair(m,n), p-> p.n==0? p: new Pair(n, m%n))
                .filter(p -> p.n==0)
                .findFirst()
                .get()
                .m;
    }
//
//    public static class Counter{
//        final int previous;
//        final int count;
//        final boolean isChain;
//        Counter(int previous, int count, boolean isChain){
//            this.previous = previous;
//            this.count = count;
//            this.isChain = isChain;
//        }
//    }
//
//    public static int countRepeats(int ...array){
//        return Arrays.stream(array)
//                .mapToObj(x -> x)
//                .reduce(new Counter(-1, 0, false),
//                        (c,x) -> x==c.previous ? new Counter(x, c.count+ (c.isChain ? 0 : 1), true) : new Counter(x, c.count, false)
//                        , (c, d) -> c).count;
//    }
//
//    public static class NormData{
//        final double min, max, sum, count;
//        NormData(double min, double max, double sum, double count){
//            this.min = min;
//            this.max = max;
//            this.sum = sum;
//            this.count = count;
//        }
//    }
//
//    public static double normalizedMean(Stream<Integer> stream){
//        final NormData normie = stream.reduce(new NormData(Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, 0, 0),
//                (norm, i) -> new NormData(Math.min((double) i, norm.min), Math.max((double) i, norm.max), norm.sum+i, norm.count+1),
//                (x,y) -> x);
//        if(normie.count == 0 || normie.max == normie.min) return 0;
//        return ((normie.sum/normie.count)-normie.min)/(normie.max-normie.min);
//    }
//
//    public static void l1(){
//        System.out.println(Arrays.toString(Main.twinPrimes(100)));
//        System.out.println(Arrays.toString(Main.twinPrimes(2)));
//    }
}
