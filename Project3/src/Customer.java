/**
 * Represents a customer, storing information
 * such as arrival time, serve time, and waiting time.
 */
abstract class Customer {
    /**
     * The ID of the customer.
     */
    final int id;
    private final double arrivalTime;
    private double servedTime;
    private double doneTime;

    /**
     * Constructs a new customer object that represents a customer
     * with ID given by the specified id value and arrival time indicated
     * by the double parameter.
     *
     * @param arrivalTime the time at which the customer arrives.
     * @param id          the ID of the customer.
     */
    public Customer(double arrivalTime, int id) {
        this.id = id;
        this.arrivalTime = arrivalTime;
    }

    /**
     * Returns the time at which the customer is served.
     *
     * @return the time at which the customer is served.
     */
    public double getServedTime() {
        return servedTime;
    }

    /**
     * Sets the time at which the customer is served.
     *
     * @param servedTime the time when the customer is served.
     */
    public void setServedTime(double servedTime) {
        this.servedTime = servedTime;
    }

    /**
     * Returns the time at which the customer is done being served.
     *
     * @return the time at which the customer is done being served.
     */
    public double getDoneTime() {
        return doneTime;
    }

    /**
     * Sets the time at which the customer is done being served.
     *
     * @param doneTime the time when the customer is done being served.
     */
    public void setDoneTime(double doneTime) {
        this.doneTime = doneTime;
    }

    /**
     * Returns the time at which the customer arrives.
     *
     * @return the time at which the customer arrives.
     */
    public double getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Returns the time for which the customer waits
     * to be served since arriving.
     *
     * @return the time for which the customer waits
     *      to be served since arriving.
     */
    public double getWaitingTime() {
        return servedTime - arrivalTime;
    }

    /**
     * Returns a boolean representing whether the customer is greedy.
     *
     * @return whether the customer is greedy.
     */
    abstract boolean isGreedy();
}