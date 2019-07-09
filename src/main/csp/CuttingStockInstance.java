package csp;

import java.util.*;

/**
 * This class is used to model a CSP instance.
 *
 * @author Andrea Graziani
 * @version 1.0
 */
public class CuttingStockInstance {

    // Stock material width
    private final double maxItemLength;
    // Items required by customers
    private ArrayList<CuttingStockItem> items;

    public CuttingStockInstance(double maxItemLength) {
        this.maxItemLength = maxItemLength;
        this.items = new ArrayList<>();
    }

    public void addItems(double amount, double length) {
        this.items.add(new CuttingStockItem(amount, length));
    }

    public double getMaxItemLength() {
        return maxItemLength;
    }

    public ArrayList<CuttingStockItem> getItems() {
        return items;
    }
}