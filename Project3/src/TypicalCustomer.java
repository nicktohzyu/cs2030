class TypicalCustomer extends Customer {
    /**
     * Constructs a new customer object that represents a customer
     * with ID given by the specified id value and arrival time indicated
     * by the double parameter.
     *
     * @param arrivalTime the time at which the customer arrives.
     * @param id          the ID of the customer.
     */
    public TypicalCustomer(double arrivalTime, int id) {
        super(arrivalTime, id);
    }

    /**
     * Returns false, indicating that the customer is not greedy.
     *
     * @return false, indicating that the customer is not greedy.
     */
    @Override
    boolean isGreedy() {
        return false;
    }

    /**
     * Returns a string representation of the customer's ID number.
     * @return a string representation of the customer's ID number.
     */
    @Override
    public String toString() {
        return Integer.toString(id);
    }
}
