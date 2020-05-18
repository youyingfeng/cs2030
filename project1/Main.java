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

        while (input.hasNextLine() == true) {
            counter++;
            inputLine = input.nextLine();
            double time = Double.parseDouble(inputLine);
            Customer customer = new Customer(counter, time);
            queue.add(customer.setState("ARRIVES"));
        }

        Simulator discreteEventSimulator = new Simulator(queue);
        discreteEventSimulator.runSimulation();

    }
}
