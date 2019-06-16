package linearproblem.solver.implementations;

import linearproblem.math.MathematicalSymbol;
import linearproblem.solver.Solver;
import lpsolve.LpSolve;
import lpsolve.LpSolveException;

public class LpSolveSolver implements Solver {

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
    public void setLinearProblemAsMinimum() {
        this.solver.setMinim();
    }

    @Override
    public void setLinearProblemAsMaximum() {
        this.solver.setMaxim();
    }

    @Override
    public void addLinearProblemConstrain(double[] constraintCoefficients, MathematicalSymbol mathematicalSymbol, double leftSideValue) throws Exception {
        solver.addConstraint(constraintCoefficients, mathematicalSymbol.getLpSolveIndex(), leftSideValue);
    }

    @Override
    public void setObjectFunctionCoefficients(double[] coefficients) throws Exception {
        solver.setObjFn(coefficients);
    }

    @Override
    public double getObjectFunctionValue() throws Exception {
        return this.solver.getObjective();
    }

    @Override
    public void solve() throws Exception {
        this.solver.solve();
    }

    @Override
    public double[] getSolution() throws Exception {
        return solver.getPtrVariables();
    }
}