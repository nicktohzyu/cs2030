import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.function.Supplier;

/**
 * The DiscreteEventSimulator class defines a Discrete Event Simulator with a
 * G/M/1 queue.
 * @author Yong Qi
 */
public class DiscreteEventSimulator {

    private static final int DEFAULT_SERVER_QUEUEING_CAPACITY = 1;
    private static final Supplier<Double> DEFAULT_SERVICE_TIME_SUPPLIER =
        () -> 1.0;

    /*
     * The Simulator will contain 
     * (1)  A list of completed events,
     * (2)  The Future Event List,
     * (3)  The state of the system, and
     * (4)  An initialised set of statistics.
     */
    private final List<Event> completedEvents;
    private final PriorityQueue<Event> futureEventList;
    private final SystemState state;
    private final Statistics statistics;

    /**
     * Initialise a DiscreteEventSimulator given a list of customer arrival
     * times.
     * @param  arrivalTimes A List of customer arrival times.
     * @return              An instance of DiscreteEventSimulator initialised 
     *                      with {@code arrivalTimes}.
     */
    public static DiscreteEventSimulator init(
            List<? extends Double> arrivalTimes) {
        List<Customer> listOfCustomers = new ArrayList<>();
        List<Server> listOfServers = new ArrayList<>();
        PriorityQueue<Event> pq = new PriorityQueue<>();

        // Create customers and add arrival events.
        int id = 1;
        for (double d : arrivalTimes) {
            Customer c = new Customer(id++, d);
            listOfCustomers.add(c);
            pq.add(Event.arrivalEvent(d, c));
        }

        // Add servers.
        listOfServers.add(new Server(DEFAULT_SERVER_QUEUEING_CAPACITY, 
                    DEFAULT_SERVICE_TIME_SUPPLIER));

        // Initialise the system.
        SystemState s = new SystemState(listOfCustomers, listOfServers);

        return new DiscreteEventSimulator(pq, s);
    }
   
    private DiscreteEventSimulator(PriorityQueue<Event> pq,
            SystemState initialState) {
        this.futureEventList = pq;
        this.state = initialState;
        this.completedEvents = new ArrayList<>();
        this.statistics = new Statistics();
    }

    /**
     * Run this simulator.
     */
    public void run() {
        while (!futureEventList.isEmpty()) {

            // Get the next event in FEL.
            Event e = futureEventList.poll();
            
            // Get the result of running this event.
            Optional<Event> res = e.getNextEvent(this.state, this.statistics);
            
            // Add the result to FEL, if any.
            res.ifPresent(x -> futureEventList.add(x));

            // Save the completed event.
            completedEvents.add(e);

        }     
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Event e : completedEvents) {
            sb.append(e);
            sb.append('\n');
        }
        sb.append(this.statistics);
        return sb.toString();
    }
}

