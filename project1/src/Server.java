/**
 * Represents a server. Stores information of the server,
 * including whether it has a waiting customer,
 * and when it can next serve.
 */
class Server {
    private boolean hasWaitingCustomer = false;
    private double nextServeTime = 0;

    /**
     * Returns whether there is already a customer waiting.
     * @return whether there is already a customer waiting.
     */
    public boolean isHasWaitingCustomer() {
        return hasWaitingCustomer;
    }

    /**
     * Sets whether there is a waiting customer.
     * @param hasWaitingCustomer whether there is a waiting customer.
     */
    public void setHasWaitingCustomer(boolean hasWaitingCustomer) {
        this.hasWaitingCustomer = hasWaitingCustomer;
    }

    /**
     * Returns the time at which the server finishes serving the current customer.
     * @return the time at which the server finishes serving the current customer.
     */
    public double getNextServeTime() {
        return nextServeTime;
    }

    /**
     * Simulates a customer arriving. Modifies the customer's served time
     * if appropriate.
     * @param customer the customer arriving.
     * @return an event state representing whether the customer will
     *      be served immediately, wait, or leave.
     */
    public int customerArrives(Customer customer) {
        if (customer.getArrivalTime() >= this.getNextServeTime()) {
            //the server is free, customer is served immediately
            customer.setServedTime(customer.getArrivalTime()); //customer is served immediately
            return Event.SERVED;
        } else {
            if (this.isHasWaitingCustomer()) {
                //there is already another customer waiting
                return Event.LEAVES;
            } else { //customer waits
                customer.setServedTime(this.getNextServeTime());
                this.setHasWaitingCustomer(true);
                return Event.WAITS;
            }
        }
    }

    /**
     * Simulates a customer being served.
     * @param customer the customer to be served.
     */
    public void serveCustomer(Customer customer) {
        this.nextServeTime = customer.getServedTime() + Customer.SERVE_TIME;
        hasWaitingCustomer = false;
    }
}
