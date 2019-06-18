package CuttingStock;


import gurobi.GRB;
import linearproblem.LinearProblem;
import linearproblem.LinearProblemSolution;
import linearproblem.LinearProblemType;
import linearproblem.gurobi.GurobiLinearProblem;
import linearproblem.utility.MathematicalSymbol;
import linearproblem.utility.VariableType;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class CuttingStockResolver {

    private CuttingStockInstance instance;

    private LinearProblem masterProblem;
    private LinearProblem columnCutPatternProblem;
    private LinearProblem knapsackSubProblem;


    public CuttingStockResolver(CuttingStockInstance instance) {

        this.instance = instance;

        buildMasterProblem(instance);
        buildKnapsackSubProblem(instance);
    }


    private void buildMasterProblem(CuttingStockInstance instance) {

        double maxItemLength = instance.getMaxItemLength();
        int numberOfVariables = instance.getNumberOfItems();
        this.masterProblem = new GurobiLinearProblem();

        try {

            double[] coefficientObjectiveFunction = new double[numberOfVariables];

            this.masterProblem.modelInitialization();
            this.masterProblem.setVariables(numberOfVariables, 0, GRB.INFINITY, VariableType.REAL);

            for (int index = 0; index < numberOfVariables; index++)
                coefficientObjectiveFunction[index] = 1;

            this.masterProblem.addObjectiveFunction(coefficientObjectiveFunction, LinearProblemType.min);

            int index = 0;
            for (CuttingStockItem item : instance.getItems()) {

                double[] constraintCoefficient = new double[numberOfVariables];
                constraintCoefficient[index] = ((int) (maxItemLength / item.getLength()));
                //constraintCoefficient[index] = 1;


                this.masterProblem.addConstraint(constraintCoefficient, MathematicalSymbol.GREATER_EQUAL, item.getAmount());
                index++;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void buildKnapsackSubProblem(CuttingStockInstance instance) {

        this.knapsackSubProblem = new GurobiLinearProblem();
        ArrayList<CuttingStockItem> items = instance.getItems();
        int numberOfVariables = items.size();

        try {

            double[] coefficientObjectiveFunction = new double[numberOfVariables];

            this.knapsackSubProblem.modelInitialization();
            this.knapsackSubProblem.setVariables(numberOfVariables, 0.0, GRB.INFINITY, VariableType.INTEGER);

            this.knapsackSubProblem.addObjectiveFunction(null, LinearProblemType.max);

            int index = 0;
            double[] constraintCoefficient = new double[numberOfVariables];
            for (CuttingStockItem item : instance.getItems()) {
                constraintCoefficient[index] = item.getLength();
                index++;
            }

            this.knapsackSubProblem.addConstraint(constraintCoefficient, MathematicalSymbol.LESS_EQUAL, instance.getMaxItemLength());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public void solve() {

        LinearProblemSolution masterProblemsolution = null;
        LinearProblemSolution knapsackSolution = null;

        for (int iteration = 0; ; iteration++) {

            try {

                System.out.println("-----" + iteration);

                //this.masterProblem.writeToLPFile("MasterProblem" + iteration);
                masterProblemsolution = this.masterProblem.getSolution();
                //masterProblemsolution.print();

                double[] multiplier = this.masterProblem.getDualSolution();

                this.knapsackSubProblem.addObjectiveFunction(multiplier, LinearProblemType.max);
                //this.knapsackSubProblem.writeToLPFile("Knapsack" + iteration);
                knapsackSolution = this.knapsackSubProblem.getSolution();

                if (1 - knapsackSolution.getValueObjectiveFunction() < 0){

                    double[] newColumn = knapsackSolution.getSolutions();
                    this.masterProblem.addNewColumn(0.0, GRB.INFINITY, 1.0, VariableType.REAL, newColumn);

                } else {
                    break;
                }

            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }


        try {

            CuttingStockSolution cuttingStockSolution = new CuttingStockSolution();
            double[] pppp = masterProblemsolution.getSolutions();

            for (int i = 0; i < pppp.length; i++){

                int amount = (int) pppp[i];
                if (amount == 0)
                    continue;

                double[] column = this.masterProblem.getColumnCoefficient(i);
                ArrayList<Double> patternddd = new ArrayList<>();

                for (int j = 0; j < column.length; j++){
                    while (column[j] > 0){
                        patternddd.add(this.instance.getItems().get(j).getLength());
                        column[j]--;
                    }
                }

                double[] patternrrr = new double[patternddd.size()];
                for (int r = 0; r < patternrrr.length; r++){
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
