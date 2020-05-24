class HumanServer extends Server {
    /**
     * Creates a new server with the given ID.
     *
     * @param id                 the ID of the server.
     * @param maximumQueueLength the maximum queue length of the server's queue.
     * @param customerQueue      the queue which holds customers waiting on the server.
     */
    HumanServer(int id, int maximumQueueLength, CustomerQueue customerQueue) {
        super(id, maximumQueueLength, customerQueue);
    }

    /**
     * Returns a string representation of the server in the format
     * "server", ID of the server.
     *
     * @return a string representation of the server in the format
     *      "server", ID of the server.
     */
    @Override
    public String toString() {
        return "server " + id;
    }

    /**
     * Returns a string representation of the server's queue in the format
     * "server", ID of the server's queue.
     *
     * @return a string representation of the server's queue in the format
     *      "server", ID of the server's queue.
     */
    @Override
    public String waitMessage() {
        return "server " + customerQueue.id;
    }

    /**
     * Returns true as the human server can take a break.
     *
     * @return true as the human server can take a break.
     */
    @Override
    public boolean canRest() {
        return true;
    }
}
