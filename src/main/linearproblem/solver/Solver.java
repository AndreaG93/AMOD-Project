package linearproblem.solver;

import linearproblem.LinearProblem;
import linearproblem.LinearProblemSolution;
import linearproblem.LinearProblemType;
import linearproblem.math.MathematicalSymbol;

public abstract class Solver {

    public LinearProblemSolution solve(LinearProblem linearProblem) {

        LinearProblemSolution output = null;

        try {

            if (linearProblem.getType() == LinearProblemType.min)
                setLinearProblemAsMinimum();
            else
                setLinearProblemAsMaximum();

            setObjectFunctionCoefficients(linearProblem.getCoefficientOfObjectiveFunction());

            for (int i = 0; i < linearProblem.getNumberOfConstrains(); i++) {

                double[] coefficients = linearProblem.getVariableCoefficientOfSpecifiedConstraint(i);
                MathematicalSymbol symbol = linearProblem.getMathematicalSymbol(i);
                double value = linearProblem.getLeftValue(i);

                addLinearProblemConstrain(coefficients, symbol, value);
            }

            solve();

            output = new LinearProblemSolution(getSolution(), getObjectFunctionValue());

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            freeMemory();
        }

        return output;
    }


    abstract protected void setLinearProblemAsMinimum() throws Exception;

    abstract protected void setLinearProblemAsMaximum() throws Exception;

    abstract protected void addLinearProblemConstrain(double[] constraintCoefficients, MathematicalSymbol mathematicalSymbol, double leftSideValue) throws Exception;

    abstract protected void setObjectFunctionCoefficients(double[] coefficients) throws Exception;

    abstract protected double[] getSolution() throws Exception;

    abstract protected double getObjectFunctionValue() throws Exception;

    abstract protected void solve() throws Exception;

    abstract protected void freeMemory();
}
