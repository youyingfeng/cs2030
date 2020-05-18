import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

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
     */
    public static void main(String[] args) {
        PriorityQueue<Customer> queue = new PriorityQueue<Customer>(11, new CustomerComparator());
        Scanner input = new Scanner(System.in);
        String inputLine;
        int counter = 0;
        //gets the number of servers
        int numberOfServers = Integer.parseInt(input.nextLine());
        //parses each line
        while (input.hasNextLine() == true) {
            counter++;
            inputLine = input.nextLine();
            double time = Double.parseDouble(inputLine);
            Customer customer = new Customer(counter, time);
            queue.add(customer.setState("ARRIVES"));
        }
        //initialises the discrete event simulator
        Simulator discreteEventSimulator = new Simulator(queue, numberOfServers);
        //runs the discrete event simulator
        discreteEventSimulator.runSimulation();

    }
}
