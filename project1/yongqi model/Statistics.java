/**
 * The Statistics class defines objects which store statistics of
 * a discrete event simulation.
 * @author Yong Qi
 */
class Statistics {

    /*
     * The three statistics to track are
     * (1)  The total waiting time among all customers,
     * (2)  The total number of customers served and
     * (3)  The total number of customers who left without being served.
     */
    private double totalWaitingTime;
    private int numberOfCustomersServed;
    private int numberOfCustomersLeft;

    /**
     * Initialises a Statistics object.
     */
    Statistics() {
        this.totalWaitingTime = 0;
        this.numberOfCustomersServed = 0;
        this.numberOfCustomersLeft = 0;
    }

    /**
     * Indicate that the given customer has been served at a particular
     * time.
     * @param customer      The customer that has been served.
     * @param servingTime   The time of the service.
     */
    void customerServed(Customer customer, double servingTime) {
        this.totalWaitingTime += customer.getWaitingTime(servingTime);
        ++this.numberOfCustomersServed;
    }

    /**
     * Indicate that a customer has left without being served.
     * @param customer  The customer that left.
     */
    void customerLeft(Customer customer) {
        ++this.numberOfCustomersLeft;
    }

    @Override
    public String toString() {
        return String.format("[%.3f %d %d]", 
                this.totalWaitingTime / this.numberOfCustomersServed,
                this.numberOfCustomersServed,
                this.numberOfCustomersLeft);
    }

}
