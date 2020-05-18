package cs2030.simulator;

import java.util.PriorityQueue;

/**
 * Pairs the queue with the ID of the server with which it is associated with.
 * Self-check counters only get one queue, so they are only associated with
 * the ID of the first self-check counter.
 */
public class Pair {
    private final int id;
    private final PriorityQueue<Customer> queue;

    /**
     * Instantiates a new Pair with the specified ID and queue.
     * @param id ID of the server which the queue is associated with.
     * @param queue Queue of customers waiting for the server with the given ID.
     */
    Pair(int id, PriorityQueue<Customer> queue) {
        this.id = id;
        this.queue = queue;
    }

    public int getID() {
        return id;
    }

    public PriorityQueue<Customer> getQueue() {
        return queue;
    }
}
