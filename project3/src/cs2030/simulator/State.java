package cs2030.simulator;

/**
 * Enumerates the states of the Customer.
 */
public enum State {
    ARRIVES(" arrives"),
    SERVED(" served by "),
    WAITS(" waits to be served by "),
    LEAVES(" leaves"),
    DONE(" done serving by "),
    SERVER_REST(""),
    SERVER_BACK("");

    private String description;

    /**
     * The constructor for the enum. Associates a string with the respective state, to simplify
     * the code for toString() in Customer.java.
     * @param description The string associated with each state.
     */

    State(String description) {
        this.description = description;
    }

    /**
     * Returns the string associated with the state. This is mainly used in toString().
     * @return the string associated with the state.
     */

    public String getDescription() {
        return description;
    }
}
