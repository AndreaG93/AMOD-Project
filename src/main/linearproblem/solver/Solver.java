package linearproblem.solver;

import linearproblem.math.MathematicalSymbol;

public interface Solver {

    void setLinearProblemAsMinimum() throws Exception;

    void setLinearProblemAsMaximum() throws Exception;

    void addLinearProblemConstrain(double[] constraintCoefficients, MathematicalSymbol mathematicalSymbol, double leftSideValue) throws Exception;

    void setObjectFunctionCoefficients(double[] coefficients) throws Exception;

    double[] getSolution() throws Exception;

    double getObjectFunctionValue() throws Exception;

    void solve() throws Exception;
}
