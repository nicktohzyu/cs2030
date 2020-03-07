import java.util.Collection;
import java.util.Optional;

/**
 * The customer class denotes a customer.
 * @author Yong Qi
 */
class Customer {

    private final int id;
    private final double arrivalTime;

    /**
     * Constructs a customer.
     * @param id            The customer's ID.
     * @param arrivalTime   The customer's arrival time.
     */
    Customer(int id, double arrivalTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
    }

    /**
     * Gets the time this customer has waited at some time.
     * @param time Some time which the customer still has not been served.
     * @return     The waiting time at {@code time} since the customer's
     *             arrival time. 
     */
    double getWaitingTime(double time) {
        return time - this.arrivalTime;
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }

}

