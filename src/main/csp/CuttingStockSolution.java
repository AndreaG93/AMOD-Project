package csp;

import java.util.ArrayList;
import java.util.Map;

/**
 * This class is used to model the solution for a specified CSP-Instance.
 *
 * @author Andrea Graziani
 * @version 1.0
 */
public class CuttingStockSolution {

    // Following hash-table is used to hold a reference to all CSP-solution cutting patterns.
    private Map<Integer, CuttingStockPattern> cspSolutionPatterns;
    // SPECIAL: Following hash-table is used to hold a reference to all minimum-waste CSP-solution cutting patterns.
    // Using that solution, the number of necessary cuts is not minimal.
    private Map<Integer, CuttingStockPattern> cspSolutionMinimumWaste;

    private int totalNumberOfColumnsAdded;
    private long timeElapsed;
    private boolean isTimeOut;

    // That list is used to hold all "Restricted Master Problem" objective-function (O.F) values
    // during column generation algorithm (it is used for chart generation!)
    private ArrayList<Double> objectiveFunctionRealValues;

    // That list is used to hold all "NOT RELAXED Master Problem" objective-function (O.F) values
    // during column generation algorithm (it is used for chart generation!)
    private ArrayList<Double> objectiveFunctionIntegerValues;

    // SPECIAL: following list holds all waste-value detected during column generation algorithm! (it is used for chart generation!)
    private ArrayList<Double> wasteValues;

    // Following variables are used for minimum-waste CSP-solution
    private double objectiveFunctionValues_MinimumWaste;
    private double minimumWaste;

    private final CuttingStockInstance cspInstance;

    CuttingStockSolution(CuttingStockInstance cspInstance){

        this.cspInstance = cspInstance;

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

    public Map<Integer, CuttingStockPattern> getSolutionPatternsMinimumWasteAsMap() {
        return this.cspSolutionMinimumWaste;
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

    public CuttingStockInstance getCspInstance() {
        return cspInstance;
    }
}
