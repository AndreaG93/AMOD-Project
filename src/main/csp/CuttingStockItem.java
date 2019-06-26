package csp;

class CuttingStockItem {

    private final double amount;
    private final double length;

    CuttingStockItem(double amount, double length) {
        this.amount = amount;
        this.length = length;
    }

    double getAmount() {
        return amount;
    }

    double getLength() {
        return length;
    }
}
