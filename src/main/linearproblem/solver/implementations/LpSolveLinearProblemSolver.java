package linearproblem.solver.implementations;

import linearproblem.math.MathematicalSymbol;
import linearproblem.solver.LinearProblemSolver;
import lpsolve.LpSolve;
import lpsolve.LpSolveException;

import java.util.Locale;

public class LpSolveLinearProblemSolver extends LinearProblemSolver {

    private LpSolve solver;

    @Override
    protected void initialize(int numberOfVariables) throws Exception {
        this.solver = LpSolve.makeLp(0, numberOfVariables);
    }

    @Override
    protected void setLinearProblemAsMinimum() {
        this.solver.setMinim();
    }

    @Override
    protected void setLinearProblemAsMaximum() {
        this.solver.setMaxim();
    }

    @Override
    protected void addLinearProblemConstrain(double[] constraintCoefficients, MathematicalSymbol mathematicalSymbol, double leftSideValue) throws Exception {

        StringBuilder input = new StringBuilder();

        for (double value : constraintCoefficients)
            input.append(String.format(Locale.US, " %f ", value));

        solver.strAddConstraint(input.toString(), mathematicalSymbol.getLpSolveIndex(), leftSideValue);
    }

    @Override
    protected void setObjectFunctionCoefficients(double[] coefficients) throws Exception {

        StringBuilder input = new StringBuilder();

        for (double value : coefficients)
            input.append(String.format(Locale.US, " %f ", value));

        solver.strSetObjFn(input.toString());
    }

    @Override
    protected double getObjectFunctionValue() throws Exception {
        return this.solver.getObjective();
    }

    @Override
    protected void solve() throws Exception {
        this.solver.solve();

        double[] output = this.solver.getPtrDualSolution();

        solver.getPtrVariables();
    }


    @Override
    protected double[] getSolution() throws Exception {
        return solver.getPtrVariables();
    }

    @Override
    protected void setVariablesAsInteger(int numberOfVariables) throws Exception {

        for (int i = 0; i < numberOfVariables; i++)
            solver.setInt(i+1, true);
    }

    @Override
    protected void freeMemory() {
        solver.deleteLp();
    }

    @Override
    protected void calcDual() throws Exception {
        this.solver.getPtrDualSolution();
    }
}