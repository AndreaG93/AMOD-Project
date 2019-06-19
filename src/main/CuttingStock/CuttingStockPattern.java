package CuttingStock;

public class CuttingStockPattern {

    private final int amount;
    private final double[] pattern;

    public CuttingStockPattern(int amount, double[] pattern) {
        this.amount = amount;
        this.pattern = pattern;
    }

    public int getAmount() {
        return amount;
    }

    public double[] getPattern() {
        return pattern;
    }
}
