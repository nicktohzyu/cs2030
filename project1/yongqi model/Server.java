import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Supplier;

/**
 * The Server class defines a server.
 * @author Yong Qi
 */
class Server {

    /*
     * Servers have
     * (1)  A queue of customers.
     * (2)  a queue capacity,
     * (3)  a Supplier used to generate the service time for the customer
     *      it is currently serving.
     *
     * Note that the customer at the head of the queue is the customer it
     * is currently serving.
     */
    private final Queue<Customer> customerQueue;
    private final int capacity;
    private final Supplier<? extends Double> serviceTime;

    /**
     * Constructs a server.
     * @param queuingCapacity   The queueing capacity of this server.
     */
    Server(int queuingCapacity, 
            Supplier<? extends Double> serviceTimeSupplier) {
        this.customerQueue = new LinkedList<>();
        this.capacity = queuingCapacity + 1;
        this.serviceTime = serviceTimeSupplier;
    }

    /**
     * Serve a customer.
     * @param customer  The customer to be served.
     * @return          The service time of this customer.
     */
    double serveAndGetServiceTime(Customer customer) {
        if (this.canServe()) {

            // Server is idle, add customer to the queue.
            this.customerQueue.add(customer);

        } else if (this.customerQueue.peek() != customer) {

            // If the customer at the head of the queue is not the customer
            // to serve, time to debug your program.
            assert this.customerQueue.peek() == customer;

        }

        // Return the service time of this customer.
        return serviceTime.get();
    }

    /**
     * Indicate that this server has done serving a customer,
     * then returns the next customer in the queue.
     * @return  The next customer in the queue.
     */
    Optional<Customer> doneServingAndGetNextCustomer() {

        // Remove the currently serving customer from the head
        // of the queue.
        this.customerQueue.poll();

        // Return the next customer in line.
        return this.customerQueue.isEmpty()
            ? Optional.empty()
            : Optional.of(this.customerQueue.peek());
    }

    /**
     * Checks if the server can serve a customer not in the queue,
     * immediately.
     * @return  True if the server can serve a customer immediately,
     *          false otherwise.
     */
    boolean canServe() {
        return customerQueue.size() == 0;
    }

    /**
     * Checks if the server has queueing space to fit a customer not in
     * the queue, immediately.
     * @return  True if the server has queueing space, false otherwise.
     */
    boolean hasQueueingSpace() {
        return customerQueue.size() < this.capacity;
    }

    /**
     * Enqueues a customer.
     * @param customer  The customer to be enqueued.
     */
    void enqueue(Customer customer) {

        // If the customer cannot be queued, time to debug your program.
        assert this.hasQueueingSpace();

        // If the customer should be served immediately, time to debug your
        // program.
        assert !this.canServe();

        // Enqueue the customer.
        this.customerQueue.add(customer);
    }
}

