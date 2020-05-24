public class Statistics {

    double totalWaitingTime;
    int customersServed;
    int customersLeftWithoutBeingServed;

    Statistics() {
        totalWaitingTime = 0;
        customersServed = 0;
        customersLeftWithoutBeingServed = 0;
    }

    @Override
    public String toString() {
        double averageWaitingTime = customersServed > 0 ? totalWaitingTime / customersServed : 0;
        return String.format("[%.3f %d %d]", averageWaitingTime,
                customersServed, customersLeftWithoutBeingServed);
    }

}
