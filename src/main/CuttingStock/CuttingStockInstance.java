package CuttingStock;

import java.util.*;

public class CuttingStockInstance {

    private final double maxItemLength;
    private Vector<CuttingStockItem> demand;

    public CuttingStockInstance(double maxItemLength) {
        this.maxItemLength = maxItemLength;
        this.demand = new Vector<>();
    }

    public void addCuttingStockItem(CuttingStockItem item) {
        this.demand.add(item);
    }

    public double getMaxItemLength() {
        return maxItemLength;
    }

    public Vector<CuttingStockItem> getDemand() {
        return demand;
    }
}