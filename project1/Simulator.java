import java.util.PriorityQueue;

/**
 * Main class where the simulations are run. It supports a method
 * runSimulation(), which executes the simulation. Statistics will be reported
 * at the end of runSimulation().
 */

class Simulator {
    PriorityQueue<Customer> queue;

    /**
     * Instantiates a new Simulator object with the given queue. The object
     * supports a function runSimulation() to execute the simulation.
     *
     * @param queue This is a queue of Customers.
     */

    Simulator(PriorityQueue<Customer> queue) {
        this.queue = new PriorityQueue<Customer>(new CustomerComparator());

        while (queue.peek() != null) {
            this.queue.add(queue.poll());
        }
    }

    /**
     * Runs the discrete event simulator.
     */

    void runSimulation() {
        int customersServed = 0;
        int waitingCustomers = 0;
        int leavingCustomers = 0;
        double totalWaitingTime = 0.0;

        Server server = new Server();

        while (queue.peek() != null) {
            Customer nextCustomer = queue.poll();
            System.out.println(nextCustomer);

            if (nextCustomer.getCurrentState() == State.ARRIVES) {
                if (server.canServe(nextCustomer)) {
                    server = server.serve(nextCustomer);
                    queue.add(nextCustomer.setState("SERVED"));
                } else if (server.hasWaitingCustomer() == false) {
                    queue.add(nextCustomer.setState("WAITS"));
                } else {
                    queue.add(nextCustomer.setState("LEAVES"));
                    leavingCustomers++;
                }
            } else if (nextCustomer.getCurrentState() == State.SERVED) {
                if (server.isWaitingCustomer(nextCustomer) == true) {
                    server = server.serve(nextCustomer);
                }
                queue.add(nextCustomer.servedAt(server.getNextAvailableTime()));
                customersServed++;
            } else if (nextCustomer.getCurrentState() == State.WAITS) {
                server = server.setCustomerWaiting(nextCustomer);
                queue.add(nextCustomer.waitsAt(server.getNextAvailableTime()));
                waitingCustomers++;
                totalWaitingTime = totalWaitingTime +
                    (server.getNextAvailableTime() - nextCustomer.getTime());
            }
        }

        System.out.println("[" + String.format("%.3f", totalWaitingTime / customersServed)
            + " " + customersServed + " " + leavingCustomers + "]");
    }
}
