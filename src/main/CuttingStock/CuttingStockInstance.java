package CuttingStock;

import java.util.*;

public class CuttingStockInstance {

    private final double maxItemLength;
    private ArrayList<CuttingStockItem> items;

    public CuttingStockInstance(double maxItemLength) {
        this.maxItemLength = maxItemLength;
    }

    public void addItems(double amount, double length) {
        this.items.add(new CuttingStockItem(amount, length));
    }

    public double getMaxItemLength() {
        return maxItemLength;
    }

    public int getNumberOfItems() {
        return this.items.size();
    }

    public ArrayList<CuttingStockItem> getItems() {
        return items;
    }
}