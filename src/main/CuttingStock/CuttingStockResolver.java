package CuttingStock;


import linearproblem.LinearProblem;
import linearproblem.LinearProblemSolution;
import linearproblem.LinearProblemType;
import linearproblem.math.MathematicalSymbol;
import linearproblem.solver.Solver;


import java.util.ArrayList;


public class CuttingStockResolver {

    private LinearProblem linearMasterProblem;
    private Solver linearProblemSolver;

    public CuttingStockResolver(CuttingStockInstance instance) {
        initializeInitialLinearProblem(instance);
    }

    private void initializeInitialLinearProblem(CuttingStockInstance instance){

        double maxItemLength = instance.getMaxItemLength();
        int numberOfVariables = instance.getNumberOfItems();
        ArrayList<CuttingStockItem> items = instance.getItems();

        this.linearMasterProblem = new LinearProblem(LinearProblemType.min, numberOfVariables, numberOfVariables);

        for (int index = 0; index < numberOfVariables; index++){

            CuttingStockItem currentItem = items.get(index);

            this.linearMasterProblem.setVariableCoefficientOfObjectiveFunction(index, 1.0);
            this.linearMasterProblem.setVariableCoefficientOfSpecifiedConstraint(index, index, ((int) (maxItemLength / currentItem.getLength())));
            this.linearMasterProblem.setMathematicalSymbolOfSpecifiedConstraint(index, MathematicalSymbol.GEQ);
            this.linearMasterProblem.setConstraintLeftSideValue(index, currentItem.getAmount());
        }
    }

    public void solve(){

        LinearProblemSolution solution = this.linearProblemSolver.solve(linearMasterProblem);

        System.out.println(solution);



    }

}
