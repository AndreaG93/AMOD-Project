package csp;

/**
 * This class is used to model a specific item (roll) required by a specific customer.
 *
 * @author Andrea Graziani
 * @version 1.0
 */
public class CuttingStockItem {

    private final double amount;
    private final double length;

    CuttingStockItem(double amount, double length) {
        this.amount = amount;
        this.length = length;
    }

    public double getAmount() {
        return amount;
    }

    public double getLength() {
        return length;
    }
}
