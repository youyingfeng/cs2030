package cs2030.simulator;

/**
 * Server that can serve a Customer. The Customer class supports operators to
 * (i) serve a customer, (ii) set the waiting customer, and (iii) check for a
 * waiting customer. Getters are also supported.
 */

public class Server {
    private final Customer currentCustomer;
    private final double nextAvailableTime;
    // private final double serviceTime;
    private final int waitingCustomerId;

    /**
     * Initialises a Server object using default values.
     */

    public Server() {
        currentCustomer = null;
        nextAvailableTime = 0;
        waitingCustomerId = -1;
    }

    /**
     * Initialises a server who is serving the current Customer, who will take serviceTime
     * to serve.
     * @param customer Customer which is currently being served by the server.
     * @param serviceTime Amount of time required to serve the customer.
     */
    public Server(Customer customer, double serviceTime) {
        this.currentCustomer = customer;
        this.nextAvailableTime = customer.getServedTime() + serviceTime;
        this.waitingCustomerId = -1;
    }

    /**
     * Serves the specified customer, who will take the amount of time specified in serviceTime
     * to serve.
     * @param customer Customer who will be served by the server.
     * @param serviceTime Amount of time required to serve the customer.
     * @return Server who is serving the specified Customer, and takes the amount of time
     *     specified in serviceTime to do so.
     */
    public Server serve(Customer customer, double serviceTime) {
        return new Server(customer, serviceTime);
    }

    /**
     * The nextAvailableTime variable refers to the next available time where
     * the Server can serve another Customer.
     * @return the next available time where the Server is able to serve
     */

    public double getNextAvailableTime() {
        return nextAvailableTime;
    }

    /**
     * Takes in a customer and checks if it can be served by the Server.
     *
     * @param customer This is the customer who is waiting in line.
     * @return True if this customer can be served by the Server, false
     *         otherwise.
     */

    public boolean canServe(Customer customer) {
        if (nextAvailableTime <= customer.getTime()) {
            return true;
        } else {
            return false;
        }
    }
}
