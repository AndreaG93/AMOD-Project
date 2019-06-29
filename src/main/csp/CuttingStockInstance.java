package csp;

import java.util.*;

public class CuttingStockInstance {

    private final double maxItemLength;
    private ArrayList<CuttingStockItem> items;

    public CuttingStockInstance(double maxItemLength) {
        this.maxItemLength = maxItemLength;
        this.items = new ArrayList<>();
    }

    public void addItems(double amount, double length) {
        this.items.add(new CuttingStockItem(amount, length));
    }

    double getMaxItemLength() {
        return maxItemLength;
    }

    ArrayList<CuttingStockItem> getItems() {
        return items;
    }
}