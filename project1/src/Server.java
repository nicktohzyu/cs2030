/**
 * Represents a server. Stores information of the server, including whether it has a waiting customer,
 * and when it can next serve.
 */
public class Server {
    boolean hasWaitingCustomer = false;
    double nextServeTime = 0;
}
