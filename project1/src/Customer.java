public class Customer {
    public static final double SERVE_TIME = 1;
    final int id;
    final double arrivalTime;
    private double servedTime;

    public Customer(double arrivalTime, int id) {
        this.id = id;
        this.arrivalTime = arrivalTime;
    }

    public double getServedTime() {
        return servedTime;
    }

    public void setServedTime(double servedTime) {
        this.servedTime = servedTime;
    }
}