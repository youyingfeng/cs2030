package cs2030.simulator;

/**
 * Customer that can be served by a Server. Supports states and setters for
 * these states, getters, and the waitsAt and servedAt methods which return
 * a new customer with the appropriate state and time.
 */

public class Customer {

    private final int id;
    private double time;
    private final State currentState;
    private int serverID;
    private double entryTime;
    private double servedTime;
    private double completedTime;
    private boolean selfCheck;
    private int waitingID;
    private boolean isGreedy;

    /**
     * Instantiates a customer with the arguments as parameters.
     * @param id ID of the customer.
     * @param time Timestamp of the last update of the state of the Customer.
     * @param state Current state of the customer.
     * @param isGreedy Boolean to check if the customer is a greedy customer.
     */
    public Customer(int id, double time, String state, boolean isGreedy) {
        //used only for leaving
        this.id = id;
        this.time = time;
        this.entryTime = time;
        this.currentState = State.valueOf(state);
        this.isGreedy = isGreedy;
    }

    /**
     * Instantiates a new Customer with the arguments as parameters.
     * @param id ID of the customer.
     * @param time Timestamp of the last update of the state of the Customer.
     * @param state Current state of the customer.
     * @param selfCheck Boolean to check if the customer is waiting/served by a self-check station.
     * @param serverID ID of the server which the customer is being served by.
     * @param waitingID ID of the server which the customer is waiting for.
     * @param entryTime Time of arrival. Used for computing statistics.
     * @param servedTime Time of service. Used for computing statistics.
     * @param completedTime Time of completion (DONE). Used for computing statistics.
     * @param isGreedy Boolean to check if the customer is a greedy customer.
     */
    public Customer(int id, double time, String state, boolean selfCheck, int serverID,
                    int waitingID, double entryTime, double servedTime, double completedTime,
                    boolean isGreedy) {
        this.id = id;
        this.time = time;
        this.currentState = State.valueOf(state);
        this.selfCheck = selfCheck;
        this.serverID = serverID;
        this.waitingID = waitingID;
        this.entryTime = entryTime;
        this.servedTime = servedTime;
        this.completedTime = completedTime;
        this.isGreedy = isGreedy;
    }

    public int getId() {
        return id;
    }

    public int getServerID() {
        return serverID;
    }

    public State getCurrentState() {
        return currentState;
    }

    public double getTime() {
        return time;
    }

    public double getServedTime() {
        return servedTime;
    }

    public double getCompletedTime() {
        return completedTime;
    }

    public double getWaitingTime() {
        return servedTime - entryTime;
    }

    public boolean isSelfCheck() {
        return selfCheck;
    }

    public boolean isGreedy() {
        return isGreedy;
    }

    //FACTORY METHODS

    /**
     * Factory method to simulate a Customer arriving at the specified time, with the
     * specified ID and greediness.
     * @param id ID of the customer.
     * @param time Time of arrival of the customer.
     * @param isGreedy Boolean to check if the customer is a greedy customer.
     * @return new Customer initialised according to the arguments of this method.
     */
    public static Customer arrivesAt(int id, double time, boolean isGreedy) {
        return new Customer(id, time, "ARRIVES", isGreedy);
    }

    /**
     * Simulates a SERVER_REST event.
     * @param time Time at which the server starts resting.
     * @param serverID ID of the server currently resting.
     * @return SERVER_REST event for the specified server at the specified time.
     */
    public static Customer serverRest(double time, int serverID) {
        return new Customer(0, time, "SERVER_REST", false, serverID, serverID, time,
                            time, time, false);
    }

    /**
     * Simulates a SERVER_BACK event.
     * @param time Time at which the server has finished resting and is available for service.
     * @param serverID ID of the server which has finished resting.
     * @return SERVER_BACK event for the specified server at the specified time.
     */
    public static Customer serverBack(double time, int serverID) {
        return new Customer(0, time, "SERVER_BACK", false, serverID, serverID, time,
                            time, time, false);
    }

    /**
     * Returns a Customer that has been served immediately by the specified server.
     * @param serverID ID of the server serving the customer.
     * @return Customer with the state SERVED at the time which the customer
     *     will be served.
     */

    public Customer servedByServer(int serverID) {
        return new Customer(this.id, this.time, "SERVED", false, serverID, serverID,
                            this.time, this.time, 0.0, this.isGreedy);
    }

    /**
     * Returns a Customer that has been served immediately by the specified self-check counter.
     * @param serverID ID of the self-check counter serving the customer.
     * @param waitingID ID of the first self-check counter.
     * @return Customer with the state SERVED at the time which the customer
     *     will be served.
     */

    public Customer servedBySelfCheck(int serverID, int waitingID) {
        return new Customer(this.id, this.time, "SERVED", true, serverID, waitingID,
                            this.time, this.time, 0.0, this.isGreedy);
    }

    /**
     * Returns a Customer who is waiting for the specified server.
     * @param serverID ID of the server which the Customer is waiting for.
     * @return Customer with the state WAITS at the time specified, waiting for the server with
     *     the ID corresponding to serverID.
     */
    public Customer waitsForServer(int serverID) {
        return new Customer(this.id, this.time, "WAITS", false, serverID, serverID,
                            this.time, 0.0, 0.0, this.isGreedy);
    }

    /**
     * Returns a customer who is waiting for the specified self-check counter.
     * @param waitingID ID of the first self-check counter.
     * @return Customer with the state WAITS at the time specified, waiting for the server
     *     specified in waitingID. The ID of the actual counter serving the customer may differ
     *     from the waitingID.
     */
    public Customer waitsForSelfCheck(int waitingID) {
        return new Customer(this.id, this.time, "WAITS", true, waitingID, waitingID,
                            this.time, 0.0, 0.0, this.isGreedy);
    }


    /**
     * Returns a customer who will be served at the time specified in the arguments.
     * @param servedTime The time at which the customer will be served at.
     * @return Customer with the state SERVED with the time specified.
     */
    public Customer servedLaterAtTime(double servedTime) {
        return new Customer(this.id, servedTime, "SERVED", this.selfCheck,
                            this.serverID, this.waitingID,
                            this.time, servedTime, 0.0, this.isGreedy);
    }

    /**
     * Returns a customer who will be served by a self-check counter at the
     * time specified in the arguments.
     * @param servedTime The time at which the customer will be served at.
     * @param serverID The ID of the self-check counter serving the customer.
     * @return Customer with the state SERVED with the time specified.
     */
    public Customer servedLaterAtTimeBySelfCheck(double servedTime, int serverID) {
        return new Customer(this.id, servedTime, "SERVED", this.selfCheck,
                            serverID, this.waitingID,
                            this.time, servedTime, 0.0, this.isGreedy);
    }

    /**
     * Serves the customer, returning a customer with the state DONE at the
     * given time when the customer has been fully served.
     *
     * @param completedTime Time at which the customer has been fully served.
     * @return Customer with the state done at the time which the customer has
     *     been fully served.
     */
    public Customer doneServingAt(double completedTime) {
        return new Customer(this.id, completedTime, "DONE", this.selfCheck,
                            this.serverID, this.waitingID,
                            this.entryTime, this.servedTime, completedTime,
                            this.isGreedy);
    }

    /**
     * Returns a Customer who leaves immediately as the Customer is unable
     * to be served.
     * @return Customer with the state LEAVES at the same time.
     */

    public Customer leaves() {
        return new Customer(this.id, this.time, "LEAVES", this.isGreedy);
    }

    @Override
    public String toString() {
        if (currentState == State.SERVER_REST || currentState == State.SERVER_BACK) {
            return "";
        } else if (currentState == State.ARRIVES || currentState == State.LEAVES) {
            return String.format("%.3f", time) + " " + id + (isGreedy ? "(greedy)"
                    : ("")) + currentState.getDescription();
        } else {
            return String.format("%.3f", time) + " " + id + (isGreedy ? "(greedy)"
                    : ("")) + currentState.getDescription() +
                    (selfCheck ? "self-check " : "server ") +
                    (currentState == State.WAITS ? waitingID : serverID);
        }
    }
}
