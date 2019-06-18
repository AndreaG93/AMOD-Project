package CuttingStock;


import gurobi.GRB;
import linearproblem.LinearProblem;
import linearproblem.LinearProblemSolution;
import linearproblem.LinearProblemType;
import linearproblem.gurobi.GurobiLinearProblem;
import linearproblem.utility.MathematicalSymbol;
import linearproblem.utility.VariableType;


import java.util.ArrayList;

public class CuttingStockResolver {

    private LinearProblem masterProblem;
    private LinearProblem columnCutPatternProblem;
    private LinearProblem knapsackSubProblem;


    public CuttingStockResolver(CuttingStockInstance instance) {
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
                //constraintCoefficient[index] = ((int) (maxItemLength / item.getLength()));
                constraintCoefficient[index] = 1;


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

        LinearProblemSolution solution = null;

        for (int iteration = 0; ; iteration++) {

            try {

                this.masterProblem.writeToLPFile("MasterProblem" + iteration);
                solution = this.masterProblem.getSolution();
                solution.print();

                double[] multiplier = this.masterProblem.getDualSolution();

                this.knapsackSubProblem.addObjectiveFunction(multiplier, LinearProblemType.max);
                this.knapsackSubProblem.writeToLPFile("Knapsack" + iteration);
                solution = this.knapsackSubProblem.getSolution();

                if (1 - solution.getValueObjectiveFunction() < 0){

                    double[] newColumn = solution.getSolutions();
                    this.masterProblem.addNewColumn(0.0, GRB.INFINITY, 1.0, VariableType.REAL, newColumn);

                } else {
                    return;
                }

            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
            /*
            this.masterProblem.printAsLatexString();


            LinearProblemSolution primarySolution = this.linearProblemSolver.solve(this.masterProblem);
            LinearProblemSolution dualSolution = this.linearProblemSolver.solveDual(this.masterProblem);

            System.out.println(dualSolution);

            double[] multiplier = {1,1,1};

            this.knapsackSubProblem.setObjectiveFunctionCoefficient(multiplier);
            this.knapsackSubProblem.printAsLatexString();

            LinearProblemSolution knapsackSubProblemSolution = this.linearProblemSolver.solve(this.knapsackSubProblem);

            System.out.println(knapsackSubProblemSolution);
            */

        }
    }


}
