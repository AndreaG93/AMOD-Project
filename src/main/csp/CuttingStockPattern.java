package csp;

import java.util.ArrayList;

/**
 * This class is used to model a single cutting-pattern which belong to CSP solution.
 *
 * In other words, it is used to state how many times, specified by "cardinality" variable, a standard stock-roll must be cut
 * using the cutting-pattern specified by "cuttingLengths" field.
 *
 * @author Andrea Graziani
 * @version 1.0
 */
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
