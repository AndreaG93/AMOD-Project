package csp;

import java.util.ArrayList;

public class CuttingStockPattern {

    private final int cardinality;
    private ArrayList<Double> cuttingLengths;

    public CuttingStockPattern(int cardinality) {
        this.cardinality = cardinality;
        this.cuttingLengths = new ArrayList<>();
    }

    public int getCardinality() {
        return cardinality;
    }

    public ArrayList<Double> getCuttingLengths() {
        return cuttingLengths;
    }

    public void putCuttingLength(double length) {
        this.cuttingLengths.add(length);
    }

    public void print() {
        System.out.println(String.format("%5d %s %-100s", this.cardinality, "x", this.cuttingLengths.toString()));
    }
}
