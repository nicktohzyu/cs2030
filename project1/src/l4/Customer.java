public class Customer implements Comparable<Customer>{
    public static final int ARRIVES = 1;
    public static final int SERVED = 2;
    public static final int LEAVES = 3;
    public static final int DONE = 4;
    public static final int WAITING = 5;

    int id;
    double arrivalTime;
    double servedTime;
    int state;

    Customer(double arrivalTime, int id){
        this.id = id;
        this.arrivalTime = arrivalTime;
        state = ARRIVES;
    }
    
    public double getNextTime(){
        switch(this.state){
            case ARRIVES:
            case LEAVES:
                return this.arrivalTime;
            case SERVED:
                return this.servedTime;
            case DONE:
                return this.servedTime+1;
            default:
                break;
        }
        //raise error
        return 0;
    }

    @Override
    public String toString(){
        switch(this.state) {
            case ARRIVES:
                return String.format("%.3f %d arrives\n", this.arrivalTime, this.id);
            case LEAVES:
                return String.format("%.3f %d leaves\n", this.arrivalTime, this.id);
            case SERVED:
                return String.format("%.3f %d served\n", this.servedTime, this.id);
            case DONE:
                return String.format("%.3f %d done\n", this.servedTime+1, this.id);
            default:
                return "";
        }
    }
    public int compareTo(Customer otherCustomer) {
        int comparedTime = Double.compare(this.getNextTime(), otherCustomer.getNextTime());
        return comparedTime == 0 ? Integer.compare(this.id, otherCustomer.id) : comparedTime;
    }
}
