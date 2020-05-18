import java.util.Comparator;

/**
 * A comparator which can compare customers to determine their order in the
 * PriorityQueue. Supports the compare method.
 */

public class CustomerComparator implements Comparator<Customer> {

    /**
     * Compares two customers to determine their order in the PriorityQueue.
     *
     * @param customer1 The first customer to compare.
     * @param customer2 The second customer to compare.
     * @return either 1, 0, or -1 depending on where customer1 should be placed.
     */

    public int compare(Customer customer1, Customer customer2) {
        if (customer1.getTime() < customer2.getTime()) {
            return -1;
        } else if (customer1.getTime() > customer2.getTime()) {
            return 1;
        } else {
            if (customer1.getId() < customer2.getId()) {
                return -1;
            } else if (customer1.getId() > customer2.getId()) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
