package CuttingStock;


import linearproblem.LinearProblem;
import linearproblem.LinearProblemSolution;
import linearproblem.LinearProblemType;
import linearproblem.math.MathematicalSymbol;
import linearproblem.solver.LinearProblemSolver;


import java.util.ArrayList;


public class CuttingStockResolver {

    private LinearProblem masterProblem;
    private LinearProblem knapsackSubProblem;

    private LinearProblemSolver linearProblemSolver;

    public CuttingStockResolver(CuttingStockInstance instance, LinearProblemSolver linearProblemSolver) {

        this.linearProblemSolver = linearProblemSolver;

        buildMasterProblem(instance);
        buildKnapsackSubProblem(instance);
    }


    private void buildMasterProblem(CuttingStockInstance instance) {

        double maxItemLength = instance.getMaxItemLength();
        int numberOfVariables = instance.getNumberOfItems();
        ArrayList<CuttingStockItem> items = instance.getItems();

        this.masterProblem = new LinearProblem(LinearProblemType.min, true, numberOfVariables, numberOfVariables);

        for (int index = 0; index < numberOfVariables; index++) {

            CuttingStockItem currentItem = items.get(index);

            this.masterProblem.setVariableCoefficientOfObjectiveFunction(index, 1.0);
            //this.masterProblem.setVariableCoefficientOfSpecifiedConstraint(index, index, ((int) (maxItemLength / currentItem.getLength())));
            this.masterProblem.setVariableCoefficientOfSpecifiedConstraint(index, index, 1.0);
            this.masterProblem.setMathematicalSymbolOfSpecifiedConstraint(index, MathematicalSymbol.GEQ);
            this.masterProblem.setConstraintLeftSideValue(index, currentItem.getAmount());
        }
    }

    private void buildKnapsackSubProblem(CuttingStockInstance instance) {

        ArrayList<CuttingStockItem> items = instance.getItems();
        int numberOfItems = items.size();

        this.knapsackSubProblem = new LinearProblem(LinearProblemType.max, false, numberOfItems, 1);

        int i = 0;
        for (CuttingStockItem item : instance.getItems()) {
            this.knapsackSubProblem.setVariableCoefficientOfSpecifiedConstraint(0, i, item.getLength());
            i++;
        }

        this.knapsackSubProblem.setConstraintLeftSideValue(0, instance.getMaxItemLength());
        this.knapsackSubProblem.setMathematicalSymbolOfSpecifiedConstraint(0, MathematicalSymbol.LEQ);
    }


    public void solve() {

        for (int iteration = 0; ; iteration++) {

            this.masterProblem.printAsLatexString();


            LinearProblemSolution primarySolution = this.linearProblemSolver.solve(this.masterProblem);
            LinearProblemSolution dualSolution = this.linearProblemSolver.solveDual(this.masterProblem);

            System.out.println(dualSolution);

            double[] multiplier = {1,1,1};

            this.knapsackSubProblem.setObjectiveFunctionCoefficient(multiplier);
            this.knapsackSubProblem.printAsLatexString();

            LinearProblemSolution knapsackSubProblemSolution = this.linearProblemSolver.solve(this.knapsackSubProblem);

            System.out.println(knapsackSubProblemSolution);
            System.exit(1);
        }
    }

}
