import cs2030.simulator.RngCustom;

/**
 * Represents a server. Stores information of the server,
 * including whether it has a waiting customer,
 * and when it can next serve.
 */
abstract class Server {
    private double nextServeTime = 0;
    private boolean isResting;
    final int id;
    private final int maximumQueueLength;
    protected final CustomerQueue customerQueue;

    /**
     * Creates a new server with the given ID.
     *
     * @param id                 the ID of the server.
     * @param maximumQueueLength the maximum queue length of the server's queue.
     * @param customerQueue      the queue which holds customers waiting on the server.
     */
    Server(int id, int maximumQueueLength, CustomerQueue customerQueue) {
        this.id = id;
        this.maximumQueueLength = maximumQueueLength;
        this.customerQueue = customerQueue;
        isResting = false;
    }

    /**
     * Returns the number of customers in the server's queue.
     *
     * @return the number of customers in the server's queue.
     */
    public int getQueueLength() {
        return customerQueue.size();
    }

    /**
     * Returns true if the number of customers in the server's
     * queue is less than the maximum queue length.
     *
     * @return true if the number of customers in the server's
     *      queue is less than the maximum queue length.
     */
    public boolean canEnqueueCustomer() {
        return customerQueue.size() < maximumQueueLength;
    }

    /**
     * Enqueues a customer into the server's queue.
     *
     * @param customer the customer to be enqueued.
     */
    public void enqueueCustomer(Customer customer) {
        customerQueue.add(customer);
    }

    /**
     * Retrieves and removes the next customer in the queue.
     *
     * @return the next customer in the queue.
     */
    public Customer nextCustomer() {
        return customerQueue.remove();
    }

    /**
     * Returns whether there is already a customer waiting.
     *
     * @return whether there is already a customer waiting.
     */
    public boolean hasWaitingCustomer() {
        return !customerQueue.isEmpty();
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
        return !isResting && customerQueue.isEmpty() &&
                customer.getArrivalTime() >= this.getNextServeTime();
    }

    /**
     * Simulates a customer being served.
     *
     * @param customer    the customer to be served.
     * @param serviceTime the time at which the customer is served.
     */
    public void serveCustomer(Customer customer, Double serviceTime) {
        double doneTime = customer.getServedTime() + serviceTime;
        customer.setDoneTime(doneTime);
        this.nextServeTime = doneTime;
    }

    /**
     * Sets the server as taking a break.
     */
    public void takeBreak() {
        isResting = true;
    }

    /**
     * Sets the server as back from a break.
     */
    public void endBreak() {
        isResting = false;
    }

    /**
     * Returns true if the server can take a break.
     *
     * @return true if the server can take a break.
     */
    public abstract boolean canRest();

    /**
     * Returns a string representation of the server when a customer is enqueued.
     * @return  a string representation of the server when a customer is enqueued.
     */
    public abstract String waitMessage();
}
