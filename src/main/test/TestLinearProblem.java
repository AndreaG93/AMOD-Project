package test;

import linearproblem.math.MathematicalSymbol;
import test.gurobi.TestLinearProblemType;

public abstract class TestLinearProblem {

    public abstract void addConstraint(double[] coefficients, MathematicalSymbol symbol, double value) throws Exception;

    public abstract void setNumberOfVariables(int value) throws Exception;

    public abstract void addObjectiveFunction(double[] coefficients, TestLinearProblemType type) throws Exception;

}
