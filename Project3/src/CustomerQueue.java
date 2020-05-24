import java.util.ArrayDeque;

/**
 * Encapsulates a queue of customers, with added functionality
 * of having an ID.
 */
class CustomerQueue {
    private final ArrayDeque<Customer> queue;
    final int id;

    /**
     * Constructs an empty customer queue with given ID.
     * @param id the ID of the customer queue.
     */
    public CustomerQueue(int id) {
        queue = new ArrayDeque<>();
        this.id = id;
    }

    /**
     * Returns the number of customers in this queue.
     *
     * @return the number of customers in this queue.
     */
    public int size() {
        return queue.size();
    }

    /**
     * Inserts the specified customer at the end of this queue.
     * @param customer the customer to add.
     */
    public void add(Customer customer) {
        queue.add(customer);
    }

    /**
     * Retrieves and removes the customer at the front of the queue.
     *
     * @return the customer at the front of the queue.
     */
    public Customer remove() {
        return queue.remove();
    }

    /**
     * Returns true if there are no customers in the queue.
     *
     * @return true if there are no customers in the queue.
     */
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
