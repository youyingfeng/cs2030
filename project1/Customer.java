/**
 * Customer that can be served by a Server. Supports states and setters for
 * these states, getters, and the waitsAt and servedAt methods which return
 * a new customer with the appropriate state and time.
 */

public class Customer {
    private final int id;
    private final double time;
    private final State currentState;

    /**
     * Initialises a Customer. The customer can support the function servedAt(),
     * waitsAt(), getters for id, time, and currentState, and a state setter.
     *
     * @param id ID of the customer
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
     * @param id ID of the customer
     * @param time Time of event of Customer.
     * @param state Current state of the customer
     */

    Customer(int id, double time, String state) {
        this.id = id;
        this.time = time;
        this.currentState = State.valueOf(state);
    }

    int getId() {
        return id;
    }

    State getCurrentState() {
        return currentState;
    }

    double getTime() {
        return time;
    }

    /**
     * Serves the customer, returning a customer with the state DONE at the
     * given time when the customer has been fully served.
     *
     * @param time Time at which the customer has been fully served.
     * @return Customer with the state done at the time which the customer has
     *         been fully served.
     */

    Customer servedAt(double time) {
        return new Customer(this.id, time).setState("DONE");
    }

    /**
     * Makes the customer wait in line until they have been served. Returns a
     * Customer who will be served at the given time.
     *
     * @param time Time at which the Customer will be served.
     * @return Customer with the state served at the time which the customer
     *         will be served.
     */

    Customer waitsAt(double time) {
        return new Customer(this.id, time).setState("SERVED");
    }

    /**
     * Sets the state of the Customer. Takes in a state and returns a Customer
     * at the specified state.
     *
     * @param state String representing the state to set the Customer to.
     * @return Customer with the state specified in the parameters.
     */

    Customer setState(String state) {
        return new Customer(this.id, this.time, state);


    }

    @Override
    public String toString() {
        if (currentState == State.ARRIVES) {
            return (String.format("%.3f", time) + " " + id + " arrives");
        } else if (currentState == State.SERVED) {
            return (String.format("%.3f", time) + " " + id + " served");
        } else if (currentState == State.LEAVES) {
            return (String.format("%.3f", time) + " " + id + " leaves");
        } else if (currentState == State.WAITS) {
            return (String.format("%.3f", time) + " " + id + " waits");
        } else {
            return (String.format("%.3f", time) + " " + id + " done");
        }
    }
}
