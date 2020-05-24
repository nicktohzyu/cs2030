class GreedyCustomer extends Customer {

    /**
     * Constructs a new customer object that represents a customer
     * with ID given by the specified id value and arrival time indicated
     * by the double parameter.
     *
     * @param arrivalTime the time at which the customer arrives.
     * @param id          the ID of the customer.
     */
    public GreedyCustomer(double arrivalTime, int id) {
        super(arrivalTime, id);
    }

    /**
     * Returns true, indicating that the customer is greedy.
     *
     * @return true, indicating that the customer is greedy.
     */
    @Override
    boolean isGreedy() {
        return true;
    }

    /**
     * Returns a string representation of the customer in the format
     * customer id, "(greedy)".
     *
     * @return a string representation of the customer in the format
     *      customer id, "(greedy)".
     */
    @Override
    public String toString() {
        return Integer.toString(id) + "(greedy)";
    }
}
