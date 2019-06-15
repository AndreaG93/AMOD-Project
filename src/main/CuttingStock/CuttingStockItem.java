package CuttingStock;

public class CuttingStockItem {

    private final double amount;
    private final double length;

    public CuttingStockItem(double amount, double length) {
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