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

    public double getMaxItemLength() {
        return maxItemLength;
    }

    public int getNumberOfItems() {
        return this.items.size();
    }

    public ArrayList<CuttingStockItem> getItems() {
        return items;
    }

    public void print() {
        System.out.println(String.format("MAX LENGTH %f", this.maxItemLength));

        for (CuttingStockItem item  : this.items){
            System.out.println(String.format("%5f %-5f", item.getLength(), item.getAmount()));
        }
    }
}