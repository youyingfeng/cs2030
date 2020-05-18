/**
 * Customer that can be served by a Server. Supports states and setters for
 * these states, getters, and the waitsAt and servedAt methods which return
 * a new customer with the appropriate state and time.
 */

public class Customer {
    private final int id;
    private final double time;
    private final State currentState;
    private int serverID;

    /**
     * Initialises a Customer. The customer can support the function servedAt(),
     * waitsAt(), getters for id, time, and currentState, and a state setter.
     *
     * @param id ID of the customer.
     * @param time Time of entry of Customer.
     */

    Customer(int id, double time) {
        this.id = id;
        this.time = time;
        this.currentState = State.valueOf("ARRIVES");
    }

    /**
     * Initialises a Customer. The customer can support the function servedAt(),
     * waitsAt(), getters for id, time, and currentState, and a state setter.
     *
     * @param id ID of the customer.
     * @param time Time of event of Customer.
     * @param state Current state of the Customer.
     */

    Customer(int id, double time, String state) {
        this.id = id;
        this.time = time;
        this.currentState = State.valueOf(state);
    }

    /**
     * Initialises a Customer. The customer can support the function servedAt(),
     * waitsAt(), getters for id, time, and currentState, and a state setter.
     *
     * @param id ID of the customer.
     * @param time Time of event of Customer.
     * @param state Current state of the Customer.
     * @param serverID ID of the server serving the Customer.
     */

    Customer(int id, double time, String state, int serverID) {
        this.id = id;
        this.time = time;
        this.currentState = State.valueOf(state);
        this.serverID = serverID;
    }

    int getId() {
        return id;
    }

    int getServerID() {
        return serverID;
    }

    State getCurrentState() {
        return currentState;
    }

    double getTime() {
        return time;
    }

    /**
     * Sets the state of the Customer.
     *
     * @param state String representation of the state.
     * @return Customer with the appropriate state.
     */

    Customer setState(String state) {
        return new Customer(this.id, this.time, state);
    }

    /**
     * Returns a Customer that has been served immediately.
     *
     * @param serverID ID of the server which serves the customer.
     * @return Customer with the state SERVED at the time which the customer
     *         will be served.
     */

    Customer servedByServer(int serverID) {
        return new Customer(this.id, this.time, "SERVED", serverID);
    }

    /**
     * Returns a Customer who will wait for the specified server.
     *
     * @param serverID ID of the server which the Customer is waiting for.
     * @return Customer with the state WAITS at the same time.
     */

    Customer waitsForServer(int serverID) {
        return new Customer(this.id, this.time, "WAITS", serverID);
    }

    /**
     * Returns a Customer who will be served at the given time.
     *
     * @param time Time at which the Customer will be served.
     * @return Customer with the state SERVED at the time which the customer
     *         will be served.
     */

    Customer servedLaterAtTime(double time) {
        return new Customer(this.id, time, "SERVED", this.serverID);
    }

    /**
     * Returns a Customer who leaves immediately as the Customer is unable
     * to be served.
     *
     * @return Customer with the state SERVED at the same time.
     */

    Customer leaves() {
        return new Customer(this.id, this.time, "LEAVES");
    }

    /**
     * Serves the customer, returning a customer with the state DONE at the
     * given time when the customer has been fully served.
     *
     * @param time Time at which the customer has been fully served.
     * @return Customer with the state done at the time which the customer has
     *         been fully served.
     */

    Customer doneServingAt(double time) {
        return new Customer(this.id, time, "DONE", this.serverID);
    }

    @Override
    public String toString() {
        if (currentState == State.ARRIVES) {
            return (String.format("%.3f", time) + " " + id + " arrives");
        } else if (currentState == State.SERVED) {
            return (String.format("%.3f", time) + " " + id + " served by " + serverID);
        } else if (currentState == State.LEAVES) {
            return (String.format("%.3f", time) + " " + id + " leaves");
        } else if (currentState == State.WAITS) {
            return (String.format("%.3f", time) + " " + id + " waits to be served by " + serverID);
        } else {
            return (String.format("%.3f", time) + " " + id + " done serving by " + serverID);
        }
    }
}
