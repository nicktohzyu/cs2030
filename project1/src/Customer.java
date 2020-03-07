public class Customer {
    public static final double SERVE_TIME = 1;
    final int id;
    final double arrivalTime;
    double servedTime;

    public Customer(double arrivalTime, int id) {
        this.id = id;
        this.arrivalTime = arrivalTime;
    }
}