package CuttingStock;

import gurobi.GRB;
import linearproblem.LinearProblem;
import linearproblem.LinearProblemSolution;
import linearproblem.LinearProblemType;
import linearproblem.gurobi.GurobiLinearProblem;
import linearproblem.utility.MathematicalSymbol;
import linearproblem.utility.VariableType;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class CuttingStockResolver {

    private final CuttingStockInstance instance;
    private LinearProblem masterProblem;
    private LinearProblem knapsackSubProblem;
    private LinearProblemSolution masterProblemSolution;
    private int totalNumberOfColumnsAdded;

    public CuttingStockResolver(CuttingStockInstance instance) {

        this.instance = instance;

        this.masterProblem = new GurobiLinearProblem();
        this.knapsackSubProblem = new GurobiLinearProblem();
    }

    public void solve() throws Exception {

        buildMasterProblem();
        buildKnapsackSubProblem();
        executeColumnGenerationAlgorithm();
    }

    public int getTotalNumberOfColumnsAdded() {
        return totalNumberOfColumnsAdded;
    }

    private void executeColumnGenerationAlgorithm() throws Exception {

        LinearProblemSolution masterProblemDualSolution;
        LinearProblemSolution knapsackSubProblemSolution;

        while (true) {

            this.masterProblemSolution = this.masterProblem.getSolution();
            masterProblemDualSolution = this.masterProblem.getDualSolution();

            this.knapsackSubProblem.changeObjectiveFunctionCoefficients(masterProblemDualSolution.getSolutions());
            knapsackSubProblemSolution = this.knapsackSubProblem.getSolution();

            if (1 - knapsackSubProblemSolution.getValueObjectiveFunction() < 0) {

                double[] newColumn = knapsackSubProblemSolution.getSolutions();

                this.masterProblem.addNewColumn(0.0, GRB.INFINITY, 1.0, VariableType.REAL, newColumn);
                this.totalNumberOfColumnsAdded = this.totalNumberOfColumnsAdded + 1;

            } else
                break;
        }
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


    public void printSolution() {

        try {
            double[] pppp = this.masterProblemSolution.getSolutions();
            CuttingStockSolution cuttingStockSolution = new CuttingStockSolution();

            for (int i = 0; i < pppp.length; i++) {

                int amount = (int) pppp[i];
                if (amount == 0)
                    continue;

                double[] column = this.masterProblem.getColumnCoefficient(i);
                ArrayList<Double> patternddd = new ArrayList<>();

                for (int j = 0; j < column.length; j++) {
                    while (column[j] > 0) {
                        patternddd.add(this.instance.getItems().get(j).getLength());
                        column[j]--;
                    }
                }

                double[] patternrrr = new double[patternddd.size()];
                for (int r = 0; r < patternrrr.length; r++) {
                    patternrrr[r] = patternddd.get(r);
                }

                cuttingStockSolution.addPattern(new CuttingStockPattern(amount, patternrrr));

            }

            cuttingStockSolution.print();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
