package cs2030.simulator;

import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.stream.IntStream;
import java.util.Optional;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.OptionalInt;

/**
 * Main class where the simulations are run. It supports a method
 * runSimulation(), which executes the simulation. Statistics will be reported
 * at the end of runSimulation().
 */

public class DiscreteEventSimulator {
    PriorityQueue<Customer> queue;
    int numberOfServers;
    int numberOfSelfCheckoutCounters;
    int maxQueueLength;
    double probabilityOfResting;
    double probabilityOfGreedy;

    RandomGenerator randomGenerator;

    /**
     * Instantiates a new Simulator object with the given queue. The object
     * supports a function runSimulation() to execute the simulation.
     * @param seed Initial seed of the RandomGenerator.
     * @param numberOfServers Number of non self-checkout servers in the simulation.
     * @param numberOfSelfCheckoutCounters Number of self-checkout counters in the simulation.
     * @param maxQueueLength Maximum number of people who can queue for a server/self-checkout
     *                       counter at one time.
     * @param numberOfCustomers Number of customers generated in this simulation.
     * @param arrivalRate The rate of arrival for the RandomGenerator, lambda.
     * @param serviceRate The rate of service for the RandomGenerator, mu.
     * @param restingRate The rate of resting for the RandomGenerator, rho.
     * @param probabilityOfResting The probability at which a server will rest.
     * @param probabilityOfGreedy The probability of the occurrence of a greedy customer.
     */

    public DiscreteEventSimulator(int seed,
                                  int numberOfServers,
                                  int numberOfSelfCheckoutCounters,
                                  int maxQueueLength,
                                  int numberOfCustomers,
                                  double arrivalRate,
                                  double serviceRate,
                                  double restingRate,
                                  double probabilityOfResting,
                                  double probabilityOfGreedy) {



        this.numberOfServers = numberOfServers;
        this.numberOfSelfCheckoutCounters = numberOfSelfCheckoutCounters;
        this.maxQueueLength = maxQueueLength;
        this.probabilityOfResting = probabilityOfResting;
        this.probabilityOfGreedy = probabilityOfGreedy;
        this.randomGenerator = new RandomGenerator(seed, arrivalRate, serviceRate, restingRate);

        //initialise the queue
        this.queue = new PriorityQueue<Customer>(numberOfCustomers, new CustomerComparator());

        //add generated customers to the queue
        double currentTime = 0;

        for (int i = 0; i < numberOfCustomers; i++) {
            boolean isGreedyCustomer = randomGenerator.genCustomerType() < probabilityOfGreedy;
            this.queue.add(Customer.arrivesAt(i + 1, currentTime, isGreedyCustomer));
            currentTime = currentTime + randomGenerator.genInterArrivalTime();
        }
    }

    /**
     * Runs the discrete event simulator.
     * The states of the Customer and the timestamps will be printed out.
     * Statistics will be printed out at the end.
     */

    public void runSimulation() {
        int customersServed = 0;
        int waitingCustomers = 0;
        int leavingCustomers = 0;
        double totalWaitingTime = 0.0;

        //initialise new server array
        Server[] serverArray = new Server[numberOfServers + numberOfSelfCheckoutCounters];
        for (int i = 0; i < serverArray.length; i++) {
            serverArray[i] = new Server();
        }

        //initialise a new customer queue array
        ArrayList<Pair> serverQueues = new ArrayList<>(numberOfSelfCheckoutCounters > 0
                                                        ? numberOfServers + 1
                                                        : numberOfServers);
        for (int i = 0; i < (numberOfSelfCheckoutCounters > 0
                            ? numberOfServers + 1
                            : numberOfServers); i++) {
            serverQueues.add(i, new Pair(i,
                new PriorityQueue<Customer>(maxQueueLength, new CustomerComparator())));
        }

        /*
         * When a customer can be served immediately, it will be added back into the main queue
         * When a customer cannot be served immediately, it will be added with WAITS into the
         * main queue and the server queue.
         * Upon processing in the main queue, it will be removed.
         * It will only be added back into the queue when the server processes their own queue
         */

        while (queue.peek() != null) {
            //Obtains the next customer for processing
            Customer nextCustomer = queue.poll();

            if (nextCustomer.getCurrentState() == State.ARRIVES) {
                System.out.println(nextCustomer);

                Runnable customerLeaves = () -> {
                    queue.add(nextCustomer.leaves());
                };

                Consumer<Pair> customerJoinsAvailableQueue =
                    pair -> {
                        int currentID = pair.getID();
                        PriorityQueue<Customer> currentQueue = pair.getQueue();
                        if (currentID >= numberOfServers) {
                            currentQueue.add(nextCustomer.waitsForSelfCheck(numberOfServers + 1));
                            queue.add(nextCustomer.waitsForSelfCheck(numberOfServers + 1));
                        } else {
                            currentQueue.add(nextCustomer.waitsForServer(currentID + 1));
                            queue.add(nextCustomer.waitsForServer(currentID + 1));
                        }
                    };

                Runnable customerScansForAvailableQueue = () -> {
                    Optional<Pair> availablePair;

                    if (nextCustomer.isGreedy()) {
                        availablePair = serverQueues.stream()
                                .filter(pair ->
                                        pair.getQueue().size() < maxQueueLength)
                                .min((pair1, pair2) -> Integer.valueOf(pair1.getQueue().size())
                                                    .compareTo(
                                                        Integer.valueOf(pair2.getQueue().size())));
                    } else {
                        availablePair = serverQueues.stream()
                                                    .filter(pair ->
                                                            pair.getQueue().size() < maxQueueLength)
                                                    .findFirst();
                    }

                    availablePair.ifPresentOrElse(customerJoinsAvailableQueue, customerLeaves);
                };



                Consumer<Integer> customerGoesToEmptyServer = index -> {
                    if (index >= numberOfServers) {
                        queue.add(nextCustomer
                             .servedBySelfCheck(index + 1, numberOfServers));
                    } else {
                        queue.add(nextCustomer.servedByServer(index + 1));
                    }
                };

                Optional<Integer> scanForEmptyServers =
                    Stream.iterate(0, x -> x + 1)
                          .limit(numberOfServers + numberOfSelfCheckoutCounters)
                          .filter(index -> serverArray[index].canServe(nextCustomer) &&
                              serverQueues.get(index >= numberOfServers ? numberOfServers : index)
                                          .getQueue().size() <= 0)
                          .findFirst();

                scanForEmptyServers.ifPresentOrElse(customerGoesToEmptyServer,
                                                    customerScansForAvailableQueue);

            } else if (nextCustomer.getCurrentState() == State.SERVED) {
                System.out.println(nextCustomer);
                //get server ID
                int serverIndex = nextCustomer.getServerID() - 1;
                //update server state
                //serve() should also update the state of the server.
                serverArray[serverIndex] = serverArray[serverIndex]
                                                    .serve(nextCustomer,
                                                        randomGenerator.genServiceTime());

                //add a done customer into the queue
                queue.add(nextCustomer.doneServingAt(serverArray[serverIndex]
                          .getNextAvailableTime()));
                //increment counter
                customersServed++;
            } else if (nextCustomer.getCurrentState() == State.WAITS) {
                System.out.println(nextCustomer);
                //If WAITS, just print it out
                waitingCustomers++;
            } else if (nextCustomer.getCurrentState() == State.DONE) {
                System.out.println(nextCustomer);
                //reinsert the next guy from the queue

                //get server index
                int serverIndex = nextCustomer.getServerID() - 1;

                if (nextCustomer.isSelfCheck()) {
                    //that is, a self-check counter has just opened
                    Optional.ofNullable(serverQueues.get(numberOfServers).getQueue().poll())
                            .ifPresent(waitingCustomer ->
                                queue.add(waitingCustomer.servedLaterAtTimeBySelfCheck(
                                serverArray[serverIndex].getNextAvailableTime(), serverIndex + 1)));
                } else {
                    if (randomGenerator.genRandomRest() < probabilityOfResting) {
                        double startTime = nextCustomer.getCompletedTime();
                        double restTime = randomGenerator.genRestPeriod();
                        //this is a hack
                        serverArray[serverIndex] = serverArray[serverIndex].serve(
                            Customer.serverRest(startTime, serverIndex + 1), restTime);
                        queue.add(Customer.serverRest(startTime, serverIndex + 1));
                        queue.add(Customer.serverBack(startTime + restTime, serverIndex + 1));

                    } else {
                        //make the server serve the next one in the queue
                        //this may scan up to the self check one
                        Optional.ofNullable(serverQueues.get(serverIndex).getQueue().poll())
                                .ifPresent(waitingCustomer ->
                                    queue.add(waitingCustomer.servedLaterAtTime(
                                        serverArray[serverIndex].getNextAvailableTime())));
                    }
                }



                //update total waiting time
                totalWaitingTime = totalWaitingTime + nextCustomer.getWaitingTime();


            } else if (nextCustomer.getCurrentState() == State.LEAVES) {
                System.out.println(nextCustomer);
                leavingCustomers++;
            } else if (nextCustomer.getCurrentState() == State.SERVER_BACK) {
                int serverIndex = nextCustomer.getServerID() - 1;

                Optional.ofNullable(serverQueues.get(serverIndex).getQueue().poll())
                        .ifPresent(waitingCustomer ->
                            queue.add(waitingCustomer.servedLaterAtTime(serverArray[serverIndex]
                                                     .getNextAvailableTime())));
            }
        }
        //print statistics
        double averageWaitingTime = customersServed == 0 ? 0 : totalWaitingTime / customersServed;

        System.out.println("[" + String.format("%.3f", averageWaitingTime)
            + " " + customersServed + " " + leavingCustomers + "]");
    }
}
