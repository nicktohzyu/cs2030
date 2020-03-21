/**
 * Represents a server. Stores information of the server,
 * including whether it has a waiting customer,
 * and when it can next serve.
 */
class Server {
    private boolean hasWaitingCustomer = false;
    private double nextServeTime = 0;
    private final int id;

    /**
     * Creates a new server with the given ID.
     *
     * @param id the ID of the server.
     */
    Server(int id) {
        this.id = id;
    }

    /**
     * Returns whether there is already a customer waiting.
     *
     * @return whether there is already a customer waiting.
     */
    public boolean isHasWaitingCustomer() {
        return hasWaitingCustomer;
    }

    /**
     * Sets whether there is a waiting customer.
     *
     * @param hasWaitingCustomer whether there is a waiting customer.
     */
    public void setHasWaitingCustomer(boolean hasWaitingCustomer) {
        this.hasWaitingCustomer = hasWaitingCustomer;
    }

    /**
     * Returns the time at which the server finishes serving the current customer.
     *
     * @return the time at which the server finishes serving the current customer.
     */
    public double getNextServeTime() {
        return nextServeTime;
    }

    /**
     * Queries whether a customer can be immediately served.
     *
     * @param customer the customer which we want to serve immediately.
     * @return whether the customer can be immediately served.
     */
    public boolean canServeImmediately(Customer customer) {
        return customer.getArrivalTime() >= this.getNextServeTime();
    }

    /**
     * Simulates a customer being served.
     *
     * @param customer the customer to be served.
     */
    public void serveCustomer(Customer customer) {
        this.nextServeTime = customer.getServedTime() + Customer.SERVE_TIME;
        hasWaitingCustomer = false;
    }

    /**
     * Returns a string representation of the server's ID.
     *
     * @return a string representation of the server's ID.
     */
    @Override
    public String toString() {
        return Integer.toString(id);
    }
}
