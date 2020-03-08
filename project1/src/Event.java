/**
 * Represents a customer. Stores the customer's id, arrival time, served time, and state.
 * Implements comparable, which compares the customers by next relavant time and ID.
 */
public class Event implements Comparable<Event> {
    public static final int ARRIVES = 1;
    public static final int SERVED = 2;
    public static final int LEAVES = 3;
    public static final int DONE = 4;
    public static final int WAITS = 5;

    final Customer customer;
    final int state; //represents what will happen next
    final double time;

    Event(Customer customer, int state) {
        this.customer = customer;
        this.state = state;
        this.time = getTime();
    }

    public double getTime() {
        switch (this.state) {
            case ARRIVES:
            case LEAVES:
            case WAITS:
                return this.customer.arrivalTime;
            case SERVED:
                return this.customer.getServedTime();
            case DONE:
                return this.customer.getServedTime() + 1;
            default:
                break;
        }
        //raise error
        return 0;
    }

    @Override
    public String toString() {
        switch (this.state) {
            case ARRIVES:
                return String.format("%.3f %d arrives\n", this.time, this.customer.id);
            case LEAVES:
                return String.format("%.3f %d leaves\n", this.time, this.customer.id);
            case SERVED:
                return String.format("%.3f %d served\n", this.time, this.customer.id);
            case DONE:
                return String.format("%.3f %d done\n", this.time, this.customer.id);
            case WAITS:
                return String.format("%.3f %d waits\n", this.time, this.customer.id);
            default:
                return "";
        }
    }

    public int compareTo(Event otherEvent) {
        int comparedTime = Double.compare(this.time, otherEvent.time);
        return comparedTime == 0 ?
                Integer.compare(this.customer.id, otherEvent.customer.id)
                : comparedTime;
    }
}
