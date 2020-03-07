import java.util.Optional;

/**
 * The Event abstract class makes changes to the state of the system
 * and schedules new events.
 * @author Yong Qi
 */
abstract class Event implements Comparable<Event> {

    static final int LEAVE_EVENT = 0;
    static final int DONE_EVENT = 1;
    static final int SERVE_EVENT = 2;
    static final int WAIT_EVENT = 3;
    static final int ARRIVAL_EVENT = 4;
    static final String[] EVENT_STRINGS = new String[] {
        "leaves",
        "done",
        "served",
        "waits",
        "arrives"
    };
    static final String EVENT_FORMAT_STRING = "%.3f %s %s";

    /*
     * All events have a
     * (1)  time,
     * (2)  type,
     * (3)  server involved and a
     * (4)  customer involved.
     */
    final double time;
    final Server server;
    final Customer customer;
    private final int type;

    /**
     * Returns an arrival Event.
     * @param time      The time of the Event.
     * @param customer  The customer involved in the Event.
     * @return          The resulting arrival Event.
     */
    static Event arrivalEvent(double time, Customer customer) {
        return new Event(time, Event.ARRIVAL_EVENT, null, customer) {

            /*
             * Arrival Events can either return:
             * (1)  A serve Event if a server is free, or
             * (2)  A wait Event if a server can enqueue a customer or,
             * (3)  A leave Event if no server can serve or enqueue
             *      a customer.
             */
            @Override
            Optional<Event> getNextEvent(SystemState state, 
                    Statistics statistics) {
                return Optional.of(state
                        .getFirstServableServer()
                        .map(s -> serveEvent(time, s, this.customer))
                        .orElse(state
                            .getFirstWaitableQueue()
                            .map(s -> waitEvent(time, s, this.customer))
                            .orElse(leaveEvent(time, this.customer))));
            }
        };
    }

    /**
     * Returns a serve Event.
     * @param time      The time of the Event.
     * @param server    The server involved in the Event.
     * @param customer  The customer involved in the Event.
     * @return          The resulting serve Event.
     */
    private static Event serveEvent(double time, Server server,
            Customer customer) {
        return new Event(time, Event.SERVE_EVENT, server, customer) {

            /*
             * Serve Events will return done Events of time t where
             * t = current time + service time of the customer.
             */
            @Override
            Optional<Event> getNextEvent(SystemState state,
                    Statistics statistics) {
                
                // Get the server to serve the customer, and store this
                // customer's service time.
                double serviceTime = this.server
                        .serveAndGetServiceTime(this.customer);

                // Store statistics of this served customer.
                statistics.customerServed(this.customer, this.time);

                // Return resulting done Event.
                return Optional.of(doneEvent(
                            this.time + serviceTime, 
                            this.server,
                            this.customer));
            }
        };

    }

    /**
     * Returns a wait Event.
     * @param time      The time of the event. 
     * @param server    The server involved in the event.
     * @param customer  The customer involved in the event.
     * @return          The resulting wait Event.
     */
    private static Event waitEvent(double time, Server server, 
            Customer customer) {
        return new Event(time, Event.WAIT_EVENT, server, customer) {
            /*
             * Wait events simply gets the server to enqueue the
             * customer.
             */
            @Override
            Optional<Event> getNextEvent(SystemState s,
                    Statistics statistics) {

                // Enqueue the customer.
                this.server.enqueue(this.customer);
                
                return Optional.empty();
            }
        };
    }

    /**
     * Returns a leave Event.
     * @param time      The time of the Event.
     * @param customer  The customer involved in the event.
     * @return          The resulting leave Event.
     */
    private static Event leaveEvent(double time, Customer customer) {
        return new Event(time, Event.LEAVE_EVENT, null, customer) {

            /*
             * Leave Events simply store statistics.
             */
            @Override
            Optional<Event> getNextEvent(SystemState state, 
                    Statistics statistics) {
                
                // Store statistics.
                statistics.customerLeft(this.customer);
                
                return Optional.empty();
            }
        };
    }

    /**
     * Returns a done Event.
     * @param time      The time of the Event.
     * @param server    The server involved in the event.
     * @param customer  The customer involved in the event.
     * @return          The resulting done Event.
     */
    private static Event doneEvent(double time, Server server, 
            Customer customer) {
        return new Event(time, Event.DONE_EVENT, server, customer) {
            /*
             * Done Events tell the server that it is done serving the
             * currently serving customer, then schedules a served Event
             * if there is another customer in line.
             */
            @Override
            Optional<Event> getNextEvent(SystemState state, 
                    Statistics statistics) {
                return server.doneServingAndGetNextCustomer()
                    .map(c -> Event.serveEvent(time, this.server, c));
            }
        };
    }

    /**
     * Constructs an event.
     * @param time      The time of this event.
     * @param type      The type of this event.
     * @param server    The server involved in this event.
     * @param customer  The customer involved in this event.
     */
    Event(double time, int type, Server server, Customer customer) {
        this.time = time;
        this.type = type;
        this.server = server;
        this.customer = customer;
    }

    /**
     * Returns the result of running this event based on a given
     * system, and saves any statistics required.
     * @param state         The current system state.
     * @param statistics    The statistics object to store statistics.
     * @return              The resulting event.
     */
    abstract Optional<Event> getNextEvent(SystemState state, 
            Statistics statistics);

    @Override
    public int compareTo(Event other) {
        return this.time != other.time
            ? Double.compare(this.time, other.time)
            : Integer.compare(this.type, other.type);
    }

    @Override
    public String toString() {
        return String.format(EVENT_FORMAT_STRING, this.time, this.customer,
                EVENT_STRINGS[this.type]);
    }

}

