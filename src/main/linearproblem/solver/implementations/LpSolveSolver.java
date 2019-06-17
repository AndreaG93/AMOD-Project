package linearproblem.solver.implementations;

import linearproblem.math.MathematicalSymbol;
import linearproblem.solver.Solver;
import lpsolve.LpSolve;
import lpsolve.LpSolveException;

public class LpSolveSolver extends Solver {

    private LpSolve solver;

    public LpSolveSolver() {
        try {
            this.solver = LpSolve.makeLp(0, 0);
        } catch (LpSolveException e) {
            e.printStackTrace();
            System.exit(1);
        }
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
        solver.addConstraint(constraintCoefficients, mathematicalSymbol.getLpSolveIndex(), leftSideValue);
    }

    @Override
    protected void setObjectFunctionCoefficients(double[] coefficients) throws Exception {
        solver.setObjFn(coefficients);
    }

    @Override
    protected double getObjectFunctionValue() throws Exception {
        return this.solver.getObjective();
    }

    @Override
    protected void solve() throws Exception {
        this.solver.solve();
    }

    @Override
    protected double[] getSolution() throws Exception {
        return solver.getPtrVariables();
    }

    @Override
    protected void freeMemory() {
        solver.deleteLp();
    }
}