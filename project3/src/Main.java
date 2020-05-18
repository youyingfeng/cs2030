import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

import cs2030.simulator.DiscreteEventSimulator;
import cs2030.simulator.Server;
import cs2030.simulator.Customer;
import cs2030.simulator.CustomerComparator;
import cs2030.simulator.Pair;
import cs2030.simulator.State;

/**
 * Main class, supporting the main method. The main method takes in the input
 * and parses it into a PriorityQueue, which is passed to the Simulator to run
 * the simulation.
 */

public class Main {
    /**
     * Main method. It will scan the input and create a PriorityQueue of
     * Customers, then instantiate a Simulator object with the PriorityQueue
     * as the argument. Finally, it will run the simulation.
     * @param args takes in the arguments to the programme.
     */
    public static void main(String[] args) {
        //Instance variables
        int seed;
        int numberOfServers;
        int numberOfSelfCheckoutCounters;
        int maxQueueLength;
        int numberOfCustomers;
        double arrivalRate;
        double serviceRate;
        double restingRate;
        double probabilityOfResting;
        double probabilityOfGreedy;

        //initialise the scanner
        Scanner scanner = new Scanner(System.in);

        //assign variables
        seed = scanner.nextInt();
        numberOfServers = scanner.nextInt();
        numberOfSelfCheckoutCounters = scanner.nextInt();
        maxQueueLength = scanner.nextInt();
        numberOfCustomers = scanner.nextInt();
        arrivalRate = scanner.nextDouble();
        serviceRate = scanner.nextDouble();
        restingRate = scanner.nextDouble();
        probabilityOfResting = scanner.nextDouble();
        probabilityOfGreedy = scanner.nextDouble();

        //close scanner
        scanner.close();

        //initialises the discrete event simulator
        DiscreteEventSimulator discreteEventSimulator = new DiscreteEventSimulator(seed,
                                                              numberOfServers,
                                                              numberOfSelfCheckoutCounters,
                                                              maxQueueLength,
                                                              numberOfCustomers,
                                                              arrivalRate,
                                                              serviceRate,
                                                              restingRate,
                                                              probabilityOfResting,
                                                              probabilityOfGreedy);
        //runs the discrete event simulator
        discreteEventSimulator.runSimulation();

    }
}
