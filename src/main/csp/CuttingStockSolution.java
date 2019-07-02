package csp;

import java.util.ArrayList;
import java.util.Map;

public class CuttingStockSolution {

    private Map<Integer, CuttingStockPattern> cspSolutionPatterns;
    private Map<Integer, CuttingStockPattern> cspSolutionMinimumWaste;

    private int totalNumberOfColumnsAdded;
    private long timeElapsed;
    private boolean isTimeOut;

    private ArrayList<Double> objectiveFunctionIntegerValues;
    private ArrayList<Double> objectiveFunctionRealValues;
    private ArrayList<Double> wasteValues;

    private double objectiveFunctionValues_MinimumWaste;
    private double minimumWaste;

    CuttingStockSolution(){

        this.objectiveFunctionRealValues = new ArrayList<>();
        this.objectiveFunctionIntegerValues = new ArrayList<>();
        this.wasteValues = new ArrayList<>();

        this.minimumWaste = Double.POSITIVE_INFINITY;
    }

    // --------------------------------------------------------------- //
    // SETTER
    // --------------------------------------------------------------- //

    void addObjectiveFunctionRealValue(double value){
        this.objectiveFunctionRealValues.add(value);
    }

    void addObjectiveFunctionIntegerValue(double value) {
        this.objectiveFunctionIntegerValues.add(value);
    }

    boolean addWasteValueCheckingForMinimumValue(double currentWaste) {

        this.wasteValues.add(currentWaste);

        if (minimumWaste > currentWaste) {
            minimumWaste = currentWaste;
            return true;
        }

        return false;
    }

    void setTimeElapsed(long timeElapsed) {
        this.timeElapsed = timeElapsed;
    }

    void increaseTotalNumberOfColumnsAdded(){
        this.totalNumberOfColumnsAdded++;
    }

    public boolean isTimeOut() {
        return this.isTimeOut;
    }

    void setTimeOut() {
        this.isTimeOut = true;
    }

    void setCspSolutionPatterns(Map<Integer, CuttingStockPattern> input) {
        this.cspSolutionPatterns = input;
    }

    void setCspSolutionPatternsMinimumWaste(Map<Integer, CuttingStockPattern> input) {
        this.cspSolutionMinimumWaste = input;
    }

    public void setObjectiveFunctionValues_MinimumWaste(double objectiveFunctionValues_MinimumWaste) {
        this.objectiveFunctionValues_MinimumWaste = objectiveFunctionValues_MinimumWaste;
    }

    // --------------------------------------------------------------- //
    // GETTERS (for GUI)
    // --------------------------------------------------------------- //

    public ArrayList<CuttingStockPattern> getCspSolutionPatterns(){

        ArrayList<CuttingStockPattern> output = new ArrayList<>();

        for (Map.Entry<Integer, CuttingStockPattern> pair : this.cspSolutionPatterns.entrySet())
            output.add(pair.getValue());

        return output;
    }

    public ArrayList<CuttingStockPattern> getCspSolutionPatternsMinimumWaste() {

        ArrayList<CuttingStockPattern> output = new ArrayList<>();

        for (Map.Entry<Integer, CuttingStockPattern> pair : this.cspSolutionMinimumWaste.entrySet())
            output.add(pair.getValue());

        return output;
    }

    public Map<Integer, CuttingStockPattern> getSolutionPatternsAsMap() {
        return this.cspSolutionPatterns;
    }

    public ArrayList<Double> getObjectiveFunctionRealValues() {
        return this.objectiveFunctionRealValues;
    }

    public ArrayList<Double> getObjectiveFunctionIntegerValues() {
        return this.objectiveFunctionIntegerValues;
    }

    public ArrayList<Double> getWasteValues() {
        return wasteValues;
    }

    public int getTotalNumberOfColumnsAdded() {
        return totalNumberOfColumnsAdded;
    }

    public long getTimeElapsed() {
        return timeElapsed;
    }

    public double getObjectiveFunctionReal() {
        return this.objectiveFunctionRealValues.get(this.objectiveFunctionRealValues.size() - 1);
    }

    public double getObjectiveFunctionInteger() {
        return this.objectiveFunctionIntegerValues.get(this.objectiveFunctionIntegerValues.size() - 1);
    }

    public double getWaste_Minimum() {
        return minimumWaste;
    }

    public double getWaste() {
        return this.wasteValues.get(this.wasteValues.size()-1);
    }

    public double getObjectiveFunctionInteger_MinimumWaste() {
       return this.objectiveFunctionValues_MinimumWaste;
    }
}
