import java.util.PriorityQueue;

/**
 * Main class where the simulations are run. It supports a method
 * runSimulation(), which executes the simulation. Statistics will be reported
 * at the end of runSimulation().
 */

class Simulator {
    int numberOfServers;
    PriorityQueue<Customer> queue;

    /**
     * Instantiates a new Simulator object with the given queue. The object
     * supports a function runSimulation() to execute the simulation.
     *
     * @param queue This is a queue of Customers.
     */

    Simulator(PriorityQueue<Customer> queue, int numberOfServers) {
        this.numberOfServers = numberOfServers;
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

        //initialise new server array
        Server[] serverArray = new Server[numberOfServers];
        for (int i = 0; i < serverArray.length; i++) {
            serverArray[i] = new Server();
        }



        while (queue.peek() != null) {
            //get next customer
            Customer nextCustomer = queue.poll();
            System.out.println(nextCustomer);

            if (nextCustomer.getCurrentState() == State.ARRIVES) {
                //initialises counter
                int currentServer = 0;
                boolean assigned = false;
                //iterate through once to check for free servers
                while (currentServer <= numberOfServers) {
                    if (currentServer >= numberOfServers) {
                        assigned = false;
                        break;
                    } else if (serverArray[currentServer].canServe(nextCustomer)) {
                        serverArray[currentServer] = serverArray[currentServer]
                                                     .serve(nextCustomer);
                        queue.add(nextCustomer.servedByServer(currentServer + 1));
                        assigned = true;
                        break;
                    } else {
                        currentServer++;
                    }
                }

                //check if assigned
                //if not assigned, iterate through a second time to find available queue.
                if (assigned == false) {
                    currentServer = 0;
                    while (currentServer <= numberOfServers) {
                        if (currentServer >= numberOfServers) {
                            queue.add(nextCustomer.leaves());
                            leavingCustomers++;
                            break;
                        } else if (serverArray[currentServer].hasWaitingCustomer() == false) {
                            queue.add(nextCustomer.waitsForServer(currentServer + 1));
                            break;
                        } else {
                            currentServer++;
                        }
                    }
                }

            } else if (nextCustomer.getCurrentState() == State.SERVED) {
                //get server ID
                int currentServingServer = nextCustomer.getServerID() - 1;
                //update server state
                serverArray[currentServingServer] = serverArray[currentServingServer]
                                                    .serve(nextCustomer);
                //add a done customer into the queue
                queue.add(nextCustomer.doneServingAt(serverArray[currentServingServer]
                          .getNextAvailableTime()));
                //increment counter
                customersServed++;
            } else if (nextCustomer.getCurrentState() == State.WAITS) {
                //get server ID
                int currentServingServer = nextCustomer.getServerID() - 1;
                //update server state
                serverArray[currentServingServer] = serverArray[currentServingServer]
                                                    .setCustomerWaiting(nextCustomer);
                //add a served customer into the queue
                queue.add(nextCustomer.servedLaterAtTime(serverArray[currentServingServer]
                          .getNextAvailableTime()));
                //increment counter
                waitingCustomers++;
                //update total waiting time
                totalWaitingTime = totalWaitingTime +
                    (serverArray[currentServingServer].getNextAvailableTime()
                     - nextCustomer.getTime());
            }
        }
        //print statistics
        System.out.println("[" + String.format("%.3f", totalWaitingTime / customersServed)
            + " " + customersServed + " " + leavingCustomers + "]");
    }
}
