package csp;

import java.util.ArrayList;

public class CuttingStockPattern {

    private final int cardinality;
    private ArrayList<Double> cuttingLengths;

    CuttingStockPattern(int cardinality) {
        this.cardinality = cardinality;
        this.cuttingLengths = new ArrayList<>();
    }

    public int getCardinality() {
        return cardinality;
    }

    public ArrayList<Double> getCuttingLengths() {
        return cuttingLengths;
    }

    void putCuttingLength(double length) {
        this.cuttingLengths.add(length);
    }
}
