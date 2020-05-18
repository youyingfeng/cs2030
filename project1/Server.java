/**
 * Server that can serve a Customer. The Customer class supports operators to
 * (i) serve a customer, (ii) set the waiting customer, and (iii) check for a
 * waiting customer. Getters are also supported.
 */

class Server {
    private final Customer currentCustomer;
    private final double nextAvailableTime;
    private final double serviceTime;
    private final int waitingCustomerId;

    /**
     * Initialises a Server object using default values.
     */

    Server() {
        currentCustomer = null;
        nextAvailableTime = 0;
        serviceTime = 1.0;
        waitingCustomerId = -1;
    }

    /**
     * Takes in a customer and instantiates a server whose currentCustomer
     * is the customer.
     *
     * @param customer Customer being served.
     */

    Server(Customer customer) {
        currentCustomer = customer;
        serviceTime = 1.0;
        nextAvailableTime = customer.getTime() + serviceTime;
        waitingCustomerId = -1;
    }

    /**
     * Takes in a customer and instantiates a server whose currentCustomer
     * is the customer and the waitingCustomerId is the id of the Customer
     * currently waiting.
     *
     * @param customer Customer being served.
     * @param waitingCustomerId Id of the customer who is waiting.
     */

    Server(Customer customer, int waitingCustomerId) {
        currentCustomer = customer;
        serviceTime = 1.0;
        nextAvailableTime = customer.getTime() + serviceTime;
        this.waitingCustomerId = waitingCustomerId;
    }

    /**
     * Takes in a customer and returns a server which is currently serving the
     * customer.
     *
     * @param customer This is the customer to be served.
     * @return New Server object which is serving the current customer.
     */

    Server serve(Customer customer) {
        return new Server(customer);
    }

    /**
     * Takes in a customer and returns a server with the waitingCustomerId set
     * to the id of the customer.
     *
     * @param waitingCustomer This is the customer who is waiting in line.
     * @return New Server object.
     */

    Server setCustomerWaiting(Customer waitingCustomer) {
        return new Server(currentCustomer, waitingCustomer.getId());
    }

    /**
     * Takes in a customer and checks if it shares the same Id as the customer
     * waiting for the Server.
     *
     * @param customer This is the customer who is waiting in line.
     * @return True if this customer and the customer waiting for the server
     *         have the same id, false otherwise.
     */

    boolean isWaitingCustomer(Customer customer) {
        return (customer.getId() == waitingCustomerId);
    }

    /**
     * Checks if the Server has a Customer already waiting for it.
     *
     * @return True if there is a Customer waiting, false otherwise.
     */

    boolean hasWaitingCustomer() {
        return (waitingCustomerId != -1);
    }

    /**
     * The nextAvailableTime variable refers to the next available time where
     * the Server can serve another Customer.
     *
     * @return the next available time where the Server is able to serve
     */

    double getNextAvailableTime() {
        return nextAvailableTime;
    }

    /**
     * Takes in a customer and checks if it can be served by the Server.
     *
     * @param customer This is the customer who is waiting in line.
     * @return True if this customer can be served by the Server, false
     *         otherwise.
     */

    boolean canServe(Customer customer) {
        if (nextAvailableTime <= customer.getTime()) {
            return true;
        } else {
            return false;
        }
    }
}
