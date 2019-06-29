package csp;

import gurobi.GRB;
import linearproblem.LinearProblem;
import linearproblem.LinearProblemSolution;
import linearproblem.LinearProblemType;
import linearproblem.gurobi.GurobiLinearProblem;
import linearproblem.utility.MathematicalSymbol;
import linearproblem.utility.VariableType;

import java.util.*;

public class CuttingStockProblem {

    private final CuttingStockInstance instance;
    private LinearProblem masterProblem;
    private LinearProblem knapsackSubProblem;
    private LinearProblemSolution masterProblemSolution;
    private CuttingStockSolution cuttingStockSolution;

    private boolean timeOut;
    private Timer timer;
    private TimerTask task;

    public CuttingStockProblem(CuttingStockInstance instance) {

        this.instance = instance;

        this.masterProblem = new GurobiLinearProblem();
        this.knapsackSubProblem = new GurobiLinearProblem();
        this.cuttingStockSolution = new CuttingStockSolution();

        this.timer = new Timer();
        this.task = new TimerTask() {
            @Override
            public void run() {

                //timeOut = true;
                cuttingStockSolution.setCurrentSolutionAsApproximate();
            }
        };
    }

    public void solve() throws Exception {

        buildMasterProblem();
        buildKnapsackSubProblem();

        timer.schedule( task, 15000 );
        long start = System.currentTimeMillis();

        executeColumnGenerationAlgorithm();

        long finish = System.currentTimeMillis();

        this.cuttingStockSolution.setSolutionPatterns(buildSolutionPatterns(this.masterProblemSolution.getRoundedSolutions()));
        this.cuttingStockSolution.setTimeElapsed(finish - start);
    }

    public CuttingStockSolution getCuttingStockSolution() {
        return cuttingStockSolution;
    }



    private Map<Integer, CuttingStockPattern> buildSolutionPatterns(double[] integerSolution) throws Exception {

        Map<Integer, CuttingStockPattern> SolutionPattern = new HashMap<>();
        ArrayList<CuttingStockItem> cuttingStockItems = instance.getItems();

        for (int index = 0; index < integerSolution.length; index++) {

            int patternCardinality = (int) integerSolution[index];

            if (patternCardinality != 0) {

                SolutionPattern.put(index, new CuttingStockPattern(patternCardinality));

                double[] column = this.masterProblem.getColumnCoefficient(index);

                for (int columnIndex = 0; columnIndex < column.length; columnIndex++) {
                    while (column[columnIndex] > 0) {

                        CuttingStockPattern pattern = SolutionPattern.get(index);
                        pattern.putCuttingLength(cuttingStockItems.get(columnIndex).getLength());

                        column[columnIndex]--;
                    }
                }
            }
        }

        return SolutionPattern;
    }


    private double computeWasteFromPattern(Map<Integer, CuttingStockPattern> solutionPatter) {

        double maxItemLength = this.instance.getMaxItemLength();
        double output = 0;

        for (Map.Entry<Integer, CuttingStockPattern> pair : solutionPatter.entrySet()) {

            double currentCutLength = 0;

            for (Double value : pair.getValue().getCuttingLengths()){
                currentCutLength += value;
            }

            if (currentCutLength > maxItemLength)
                System.exit(-2);

            output += maxItemLength - currentCutLength;
        }

        return output;
    }

    private void executeColumnGenerationAlgorithm() throws Exception {

        LinearProblemSolution masterProblemDualSolution;
        LinearProblemSolution knapsackSubProblemSolution;
        double[] currentIntegerSolution;
        double[] currentMasterProblemDualSolution;
        double currentWaste;

        while (!this.timeOut) {

            this.masterProblemSolution = this.masterProblem.getSolution();
            masterProblemDualSolution = this.masterProblem.getDualSolution();

            this.cuttingStockSolution.addRelaxedObjectiveFunctionValue(this.masterProblemSolution.getValueObjectiveFunction());

            currentMasterProblemDualSolution = masterProblemDualSolution.getSolutions();
            currentIntegerSolution = this.masterProblemSolution.getRoundedSolutions();
            currentWaste = computeWasteFromPattern(buildSolutionPatterns(currentIntegerSolution));

            double objectiveFunctionValue = 0.0;
            for (Double value : currentIntegerSolution){
                objectiveFunctionValue += value;
            }
            this.cuttingStockSolution.addObjectiveFunctionValue(objectiveFunctionValue);
            this.cuttingStockSolution.addWasteValue(currentWaste);


            double masterProblemDualSolutionValue = 0.0;
            for (Double value : currentMasterProblemDualSolution){
                masterProblemDualSolutionValue += value;
            }
            this.cuttingStockSolution.addDualObjectiveFunctionValue(masterProblemDualSolutionValue);


            this.knapsackSubProblem.changeObjectiveFunctionCoefficients(currentMasterProblemDualSolution);
            knapsackSubProblemSolution = this.knapsackSubProblem.getSolution();

            if (1 - knapsackSubProblemSolution.getValueObjectiveFunction() < 0) {

                double[] newColumn = knapsackSubProblemSolution.getSolutions();

                this.masterProblem.addNewColumn(0.0, GRB.INFINITY, 1.0, VariableType.REAL, newColumn);
                this.cuttingStockSolution.increaseTotalNumberOfColumnsAdded();

            } else
                break;
        }

        this.timer.cancel();
    }

    private void buildMasterProblem() throws Exception {

        ArrayList<CuttingStockItem> cuttingStockItems = instance.getItems();
        double maxItemLength = instance.getMaxItemLength();
        int numberOfVariables = cuttingStockItems.size();

        double[] coefficientObjectiveFunction = new double[numberOfVariables];

        this.masterProblem.setVariables(numberOfVariables, 0, GRB.INFINITY, VariableType.REAL);

        Arrays.fill(coefficientObjectiveFunction, 1);

        this.masterProblem.setObjectiveFunction(coefficientObjectiveFunction, LinearProblemType.min);

        for (int index = 0; index < numberOfVariables; index++) {

            CuttingStockItem currentItem = cuttingStockItems.get(index);

            double[] constraintCoefficients = new double[numberOfVariables];

            constraintCoefficients[index] = ((int) (maxItemLength / currentItem.getLength()));
            this.masterProblem.addConstraint(constraintCoefficients, MathematicalSymbol.GREATER_EQUAL, currentItem.getAmount());
        }
    }

    private void buildKnapsackSubProblem() throws Exception {

        ArrayList<CuttingStockItem> items = instance.getItems();

        this.knapsackSubProblem.setVariables(items.size(), 0.0, GRB.INFINITY, VariableType.INTEGER);
        this.knapsackSubProblem.setObjectiveFunctionType(LinearProblemType.max);

        double[] constraintCoefficient = new double[items.size()];

        for (int index = 0; index < items.size(); index++)
            constraintCoefficient[index] = items.get(index).getLength();

        this.knapsackSubProblem.addConstraint(constraintCoefficient, MathematicalSymbol.LESS_EQUAL, instance.getMaxItemLength());
    }
}
