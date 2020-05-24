/**
 * Represents a customer interaction.
 * Implements comparable, which compares the event by time and customer ID.
 */
class Event implements Comparable<Event> {
    public static final int CUSTOMER_ARRIVES = 1;
    public static final int CUSTOMER_SERVED = 2;
    public static final int CUSTOMER_LEAVES = 3;
    public static final int CUSTOMER_DONE = 4;
    public static final int CUSTOMER_WAITS = 5;
    public static final int SERVER_REST = 6;
    public static final int SERVER_BACK = 7;
    public static final int SERVER_NEXT_CUSTOMER = 8;

    private final Customer customer;
    private final Server server;
    /**
     * Represents the type of event.
     */
    final int state;
    /**
     * Represents the time at which the event occurs.
     */
    final double time;

    /**
     * Creates a new event storing the relevant customer
     * and the type of interaction.
     *
     * @param customer the customer which the event involves.
     * @param state    the type of event/customer interaction.
     * @param server   the server which the event involves.
     * @param time     the time at which the event occurs.
     */
    Event(Customer customer, int state, Server server, double time) {
        this.customer = customer;
        this.state = state;
        this.server = server;
        this.time = time;
    }

    private int getCustomerID() {
        //helper for compareTo, if is a non-customer event, return -1 to prioritize
        return customer == null ? -1 : customer.id;
    }

    /**
     * Compares two event objects by time then by whether a customer is involved,
     * then by customer id.
     *
     * @param otherEvent the other event to compare to.
     * @return a negative integer, zero, or a positive integer if this event
     *      occurs before, equal to, or after the other event.
     */
    public int compareTo(Event otherEvent) {
        int comparedTime = Double.compare(this.time, otherEvent.time);
        return comparedTime == 0 ?
                Integer.compare(this.getCustomerID(), otherEvent.getCustomerID())
                : comparedTime;
    }

    /**
     * Returns a string representation of the event in the format
     * time, customer id, type of event.
     * @return a string representation of the event in the format
     *      time, customer id, type of event.
     */
    @Override
    public String toString() {
        switch (this.state) {
            case CUSTOMER_ARRIVES:
                return String.format("%.3f %s arrives\n", this.time, this.customer);
            case CUSTOMER_LEAVES:
                return String.format("%.3f %s leaves\n", this.time, this.customer);
            case CUSTOMER_SERVED:
                return String.format("%.3f %s served by %s\n",
                        this.time, this.customer, this.server);
            case CUSTOMER_DONE:
                return String.format("%.3f %s done serving by %s\n",
                        this.time, this.customer, this.server);
            case CUSTOMER_WAITS:
                return String.format("%.3f %s waits to be served by %s\n",
                        this.time, this.customer, this.server.waitMessage());
            default:
                return "";
        }
    }

    /**
     * Returns the time at which the event occurs.
     *
     * @return the time at which the event occurs.
     */
    public double getTime() {
        return this.time;
    }

    /**
     * Returns the customer tied to the event, or null if there is no such customer.
     *
     * @return the customer tied to the event, or null if there is no such customer.
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Returns the server tied to the event, or null if there is no such server.
     *
     * @return the server tied to the event, or null if there is no such server.
     */
    public Server getServer() {
        return this.server;
    }
}
