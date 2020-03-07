import java.util.List;
import java.util.Optional;

/**
 * The SystemState class stores the state of a system during 
 * simulation.
 * @author Yong Qi
 */
class SystemState {

    /*
     * The state contains
     * (1)  Customer and
     * (2)  Servers.
     */
    private final List<Customer> listOfCustomers;
    private final List<Server> listOfServers;

    /**
     * Creates a new system with the given customers and servers.
     * @param listOfCustomers   The list of customers in the system.
     * @param listOfServers     The list of servers in the system.
     */
    SystemState(List<Customer> listOfCustomers, List<Server> listOfServers) {
        this.listOfCustomers = listOfCustomers;
        this.listOfServers = listOfServers;
    }
    
    /**
     * Gets the first server which can serve a customer immediately, if any.
     * @return          The server in the list of servers who can serve a
     *                  customer immediately.
     */
    Optional<Server> getFirstServableServer() {
        for (Server s : this.listOfServers) {
            if (s.canServe()) {
                return Optional.of(s);
            }
        }
        return Optional.empty();
    }

    /**
     * Gets the first queue among the list of servers which the customer can
     * be enqueued, if any.
     * @return          The server in the list of servers who can enqueue a
     *                  customer.
     */
    Optional<Server> getFirstWaitableQueue() {
        for (Server s: this.listOfServers) {
            if (s.hasQueueingSpace()) {
                return Optional.of(s);
            }
        }
        return Optional.empty();
    }

}

