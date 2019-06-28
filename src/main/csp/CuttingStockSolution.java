package csp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CuttingStockSolution {

    private Map<Integer, CuttingStockPattern> SolutionPatterns;

    private int totalNumberOfColumnsAdded;
    private long timeElapsed;
    private boolean isCurrentSolutionApproximated;
    private final double maxItemLength;

    private ArrayList<Double> objectiveFunctionValues;
    private ArrayList<Double> relaxedObjectiveFunctionValues;
    private ArrayList<Integer> wasteValue;

    private double minimumObjectiveFunctionValues;
    private double minimumRelaxedObjectiveFunctionValues;

    private double[] betterIntegerSolution;

    CuttingStockSolution(double maxItemLength){
        this.maxItemLength = maxItemLength;
        this.SolutionPatterns = new HashMap<>();
        this.relaxedObjectiveFunctionValues = new ArrayList<>();
        this.objectiveFunctionValues = new ArrayList<>();
        this.isCurrentSolutionApproximated = false;
        this.minimumObjectiveFunctionValues = Double.POSITIVE_INFINITY;
    }

    public double[] getBetterIntegerSolution() {
        return betterIntegerSolution;
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

    void increaseTotalNumberOfColumnsAdded(){
        this.totalNumberOfColumnsAdded++;
    }

    void addRelaxedObjectiveFunctionValue(double value){
        this.relaxedObjectiveFunctionValues.add(value);
        minimumRelaxedObjectiveFunctionValues = value;
    }

    void addObjectiveFunctionValue(double value, double[] currentIntegerSolution) {
        this.objectiveFunctionValues.add(value);

        if (minimumObjectiveFunctionValues > value) {
            minimumObjectiveFunctionValues = value;
            betterIntegerSolution = currentIntegerSolution;
        }
    }







    public int getTotalNumberOfColumnsAdded() {
        return totalNumberOfColumnsAdded;
    }



    public long getTimeElapsed() {
        return timeElapsed;
    }

    public void setTimeElapsed(long timeElapsed) {
        this.timeElapsed = timeElapsed;
    }

    public double getMinimumRelaxedObjectiveFunctionValues() {
        return minimumRelaxedObjectiveFunctionValues;
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

    public ArrayList<Double> getRelaxedObjectiveFunctionValues() {
        return relaxedObjectiveFunctionValues;
    }

    public ArrayList<Double> getObjectiveFunctionValues() {
        return objectiveFunctionValues;
    }

    public double getMinimumIntegerObjectiveFunctionValues() {
        return this.minimumObjectiveFunctionValues;
    }
}
