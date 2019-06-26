package csp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CuttingStockSolution {

    private Map<Integer, CuttingStockPattern> SolutionPatterns;
    private int totalNumberOfColumnsAdded;
    private ArrayList<Double> objectiveFunctionValues;
    private double minimumObjectiveFunctionValues;
    private long timeElapsed;

    CuttingStockSolution(){
        this.SolutionPatterns = new HashMap<>();
        this.objectiveFunctionValues = new ArrayList<>();
    }

    void addNewPattern(int index, int patternCardinality) {
        this.SolutionPatterns.put(index, new CuttingStockPattern(patternCardinality));
    }

    void addCuttingLengthToPattern(int index, double length) {

        CuttingStockPattern pattern = this.SolutionPatterns.get(index);
        pattern.putCuttingLength(length);
    }

    public Map<Integer, CuttingStockPattern> getSolutionPatterns() {
        return SolutionPatterns;
    }

    public void increaseTotalNumberOfColumnsAdded(){
        this.totalNumberOfColumnsAdded++;
    }

    public void addObjectiveFunctionValue(double value){
        this.objectiveFunctionValues.add(value);
        minimumObjectiveFunctionValues = value;
    }

    public int getTotalNumberOfColumnsAdded() {
        return totalNumberOfColumnsAdded;
    }

    public ArrayList<Double> getObjectiveFunctionValues() {
        return objectiveFunctionValues;
    }

    public long getTimeElapsed() {
        return timeElapsed;
    }

    public void setTimeElapsed(long timeElapsed) {
        this.timeElapsed = timeElapsed;
    }

    public double getMinimumObjectiveFunctionValues() {
        return minimumObjectiveFunctionValues;
    }

    public void print(){

        for (Map.Entry<Integer, CuttingStockPattern> pair : SolutionPatterns.entrySet()) {
            pair.getValue().print();
        }
    }

    public ArrayList<CuttingStockPattern> getPatterns(){

        ArrayList<CuttingStockPattern> output = new ArrayList<>();

        for (Map.Entry<Integer, CuttingStockPattern> pair : SolutionPatterns.entrySet()) {
            output.add(pair.getValue());
        }

        return output;
    }
}
