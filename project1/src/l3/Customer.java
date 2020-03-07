public class Customer {
    public static final int ARRIVES = 1;
    public static final int SERVED = 2;
    public static final int LEAVES = 3;

    int id;
    double arrivalTime;
    int state;

    Customer(double arrivalTime, int id){
        this.id = id;
        this.arrivalTime = arrivalTime;
    }
}
