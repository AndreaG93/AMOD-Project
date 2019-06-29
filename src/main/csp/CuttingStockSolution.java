package csp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CuttingStockSolution {

    private Map<Integer, CuttingStockPattern> SolutionPatterns;

    private int totalNumberOfColumnsAdded;
    private long timeElapsed;
    private boolean isCurrentSolutionApproximated;

    private ArrayList<Double> objectiveFunctionValues;
    private ArrayList<Double> dualObjectiveFunctionValues;
    private ArrayList<Double> relaxedObjectiveFunctionValues;
    private ArrayList<Double> wasteValues;

    private double minimumObjectiveFunctionValues;
    private double minimumRelaxedObjectiveFunctionValues;
    private double minimumWaste;

    private int iterationIndex = 0;
    private int indexWhenOFIntegerIsMinimum = 0;
    private int indexWhenWasteIsMinimum = 0;

    CuttingStockSolution(){
        //this.SolutionPatterns = new HashMap<>();

        this.relaxedObjectiveFunctionValues = new ArrayList<>();
        this.objectiveFunctionValues = new ArrayList<>();
        this.wasteValues = new ArrayList<>();
        this.dualObjectiveFunctionValues = new ArrayList<>();

        this.isCurrentSolutionApproximated = false;
        this.minimumObjectiveFunctionValues = Double.POSITIVE_INFINITY;
        this.minimumWaste = Double.POSITIVE_INFINITY;
    }

    public void addWasteValue(double currentWaste) {
        this.wasteValues.add(currentWaste);

        if (minimumWaste > currentWaste) {
            minimumWaste = currentWaste;
            indexWhenWasteIsMinimum = iterationIndex;
        }
    }

    void addRelaxedObjectiveFunctionValue(double value){
        this.relaxedObjectiveFunctionValues.add(value);
        minimumRelaxedObjectiveFunctionValues = value;
    }

    void addObjectiveFunctionValue(double value) {
        iterationIndex++;
        this.objectiveFunctionValues.add(value);

        if (minimumObjectiveFunctionValues > value) {
            minimumObjectiveFunctionValues = value;
            indexWhenOFIntegerIsMinimum = iterationIndex;
        }
    }

    public double getWasteValueWhenOFIntegerValueIsMinimum() {
        return this.wasteValues.get(indexWhenOFIntegerIsMinimum);
    }

    public Map<Integer, CuttingStockPattern> getSolutionPatterns() {
        return SolutionPatterns;
    }

    public void setSolutionPatterns(Map<Integer, CuttingStockPattern> solutionPatterns) {
        SolutionPatterns = solutionPatterns;
    }

    void increaseTotalNumberOfColumnsAdded(){
        this.totalNumberOfColumnsAdded++;
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

    public double getMinimumRealObjectiveFunctionValue() {
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

    public double getMinimumIntegerObjectiveFunctionValue() {
        return this.minimumObjectiveFunctionValues;
    }



    public ArrayList<Double> getWasteValues() {
        return wasteValues;
    }

    public double getWasteLastValues() {
        return this.wasteValues.get(this.wasteValues.size()-1);
    }

    public double getMinimumWaste() {
        return minimumWaste;
    }

    public double getMinimumWasteIntegerObjectiveFunctionValue() {
        return objectiveFunctionValues.get(indexWhenWasteIsMinimum);
    }


    public void addDualObjectiveFunctionValue(double value) {
        this.dualObjectiveFunctionValues.add(value);
    }

    public ArrayList<Double> getDualObjectiveFunctionValues() {
        return dualObjectiveFunctionValues;
    }
}
