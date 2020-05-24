public class SelfCheckout extends Server {
    /**
     * Creates a new server with the given ID.
     *
     * @param id                 the ID of the server.
     * @param maximumQueueLength the maximum queue length of the server's queue.
     * @param customerQueue      the queue which holds customers waiting on the server.
     */
    SelfCheckout(int id, int maximumQueueLength, CustomerQueue customerQueue) {
        super(id, maximumQueueLength, customerQueue);
    }

    /**
     * Returns a string representation of the server in the format
     * "self-check", ID of the server.
     *
     * @return a string representation of the server in the format
     *      "self-check", ID of the server.
     */
    @Override
    public String toString() {
        return "self-check " + id;
    }

    /**
     * Returns a string representation of the server's queue in the format
     * "self-check", ID of the server's queue.
     *
     * @return a string representation of the server's queue in the format
     *      "self-check", ID of the server's queue.
     */
    @Override
    public String waitMessage() {
        return "self-check " + customerQueue.id;
    }

    /**
     * Returns false as the self checkout does not take breaks.
     *
     * @return false as the self checkout does not take breaks.
     */
    @Override
    public boolean canRest() {
        return false;
    }
}
