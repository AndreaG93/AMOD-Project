package linearproblem;

import linearproblem.utility.MathematicalSymbol;
import linearproblem.utility.VariableType;

public abstract class LinearProblem {

    public abstract LinearProblemSolution getSolution();
    public abstract LinearProblemSolution getDualSolution();

    public abstract void setObjectiveFunctionType(LinearProblemType type) throws Exception;

    public abstract void changeObjectiveFunctionCoefficients(double[] newCoefficients) throws Exception;

    public abstract void addConstraint(double[] coefficients, MathematicalSymbol symbol, double value) throws Exception;

    public abstract void setVariables(int totalNumberOfVariables, double lowerBound, double upperBound, VariableType varType) throws Exception;

    public abstract void setObjectiveFunction(double[] coefficients, LinearProblemType type) throws Exception;



    public abstract double[] getObjectiveFunctionCoefficient() throws Exception;



    public abstract void addNewColumn(double newVariableLowerBound, double newVariableUpperBound, double value, VariableType varType, double[] columnCoefficient) throws Exception;

    public abstract void addNewVariable(double lowerBound, double upperBound, VariableType varType) throws Exception;

    public abstract double[] getColumnCoefficient(int index) throws Exception;

    public abstract void writeToLPFile(String name) throws Exception;
}
