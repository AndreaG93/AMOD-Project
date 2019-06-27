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
    private boolean isCurrentSolutionApproximated;
    private final double maxItemLength;


    CuttingStockSolution(double maxItemLength){
        this.maxItemLength = maxItemLength;
        this.SolutionPatterns = new HashMap<>();
        this.objectiveFunctionValues = new ArrayList<>();
        this.isCurrentSolutionApproximated = false;
    }

    void addNewPattern(int index, int patternCardinality) {
        this.SolutionPatterns.put(index, new CuttingStockPattern(patternCardinality));
    }

    void addCuttingLengthToPattern(int index, double length) {

        CuttingStockPattern pattern = this.SolutionPatterns.get(index);
        pattern.putCuttingLength(length);
    }

    public double getWaste() {

        double output = 0;

        for (Map.Entry<Integer, CuttingStockPattern> pair : SolutionPatterns.entrySet()) {

            double currentCutLength = 0;

            for (Double value : pair.getValue().getCuttingLengths()){
                currentCutLength += value;
            }

            if (currentCutLength > this.maxItemLength)
                System.exit(-2);

            output += this.maxItemLength - currentCutLength;
        }

        return output;
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

    public void setCurrentSolutionAsApproximate(){
        this.isCurrentSolutionApproximated = true;
    }

    public boolean isCurrentSolutionApproximated() {
        return isCurrentSolutionApproximated;
    }
}
