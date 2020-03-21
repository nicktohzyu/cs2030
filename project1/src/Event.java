/**
 * Represents a customer interaction.
 * Implements comparable, which compares the event by time and customer ID.
 */
class Event implements Comparable<Event> {
    public static final int ARRIVES = 1;
    public static final int SERVED = 2;
    public static final int LEAVES = 3;
    public static final int DONE = 4;
    public static final int WAITS = 5;

    /**
     * Each event is tied to a customer.
     */
    final Customer customer;
    /**
     * Represents the type of event.
     */
    final int state;
    /**
     * Represents the time at which the event occurs.
     */
    final double time;
    /**
     * Represents the server which the customer is served by, if relevant.
     */
    final Server server;

    /**
     * Creates a new event storing the relevant customer
     * and the type of interaction.
     *
     * @param customer the customer which the event is related to.
     * @param state    the type of event/customer interaction.
     */
    Event(Customer customer, int state) {
        this(customer, state, null);
    }

    /**
     * Creates a new event storing the relevant customer
     * and the type of interaction.
     *
     * @param customer the customer which the event involves.
     * @param state    the type of event/customer interaction.
     * @param server    the server which the event involves.
     */
    Event(Customer customer, int state, Server server) {
        this.customer = customer;
        this.state = state;
        this.server = server;
        this.time = getTime();
    }

    /**
     * Returns the time at which the event occurs.
     *
     * @return the time at which the event occurs.
     */
    public double getTime() {
        switch (this.state) {
            case ARRIVES:
            case LEAVES:
            case WAITS:
                return this.customer.getArrivalTime();
            case SERVED:
                return this.customer.getServedTime();
            case DONE:
                return this.customer.getServedTime() + 1;
            default:
                break;
        }
        return 0;
    }

    /**
     * Compares two event objects by time then by customer id.
     *
     * @param otherEvent the other event to compare to.
     * @return a negative integer, zero, or a positive integer if this event
     *      occurs before, equal to, or after the other event.
     */
    public int compareTo(Event otherEvent) {
        int comparedTime = Double.compare(this.time, otherEvent.time);
        return comparedTime == 0 ?
                Integer.compare(this.customer.id, otherEvent.customer.id)
                : comparedTime;
    }

    @Override
    /**
     * Returns a string representation of the event in the format
     * time, customer id, type of event.
     * @return a string representation of the event in the format
     *      time, customer id, type of event
     */
    public String toString() {
        switch (this.state) {
            case ARRIVES:
                return String.format("%.3f %d arrives\n", this.time, this.customer.id);
            case LEAVES:
                return String.format("%.3f %d leaves\n", this.time, this.customer.id);
            case SERVED:
                return String.format("%.3f %d served by %s\n",
                        this.time, this.customer.id, this.server);
            case DONE:
                return String.format("%.3f %d done serving by %s\n",
                        this.time, this.customer.id, this.server);
            case WAITS:
                return String.format("%.3f %d waits to be served by %s\n",
                        this.time, this.customer.id, this.server);
            default:
                return "";
        }
    }

}
