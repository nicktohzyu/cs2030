/**
 * Represents a server. Stores information of the server,
 * including whether it has a waiting customer,
 * and when it can next serve.
 */
public class Server {
    private boolean hasWaitingCustomer = false;
    private double nextServeTime = 0;

    public boolean isHasWaitingCustomer() {
        return hasWaitingCustomer;
    }

    public void setHasWaitingCustomer(boolean hasWaitingCustomer) {
        this.hasWaitingCustomer = hasWaitingCustomer;
    }

    public double getNextServeTime() {
        return nextServeTime;
    }

    public void setNextServeTime(double nextServeTime) {
        this.nextServeTime = nextServeTime;
    }
}
