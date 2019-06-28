package linearproblem;

import linearproblem.utility.MathematicalSymbol;
import linearproblem.utility.VariableType;

public interface LinearProblem {

    LinearProblemSolution getSolution() throws Exception;
    
	LinearProblemSolution getDualSolution() throws Exception;

    void setObjectiveFunctionType(LinearProblemType type) throws Exception;

    void changeObjectiveFunctionCoefficients(double[] newCoefficients) throws Exception;

    void addConstraint(double[] coefficients, MathematicalSymbol symbol, double value) throws Exception;

    void setVariables(int totalNumberOfVariables, double lowerBound, double upperBound, VariableType varType) throws Exception;

    void setObjectiveFunction(double[] coefficients, LinearProblemType type) throws Exception;

    void addNewColumn(double newVariableLowerBound, double newVariableUpperBound, double value, VariableType varType, double[] columnCoefficient) throws Exception;

    double[] getColumnCoefficient(int index) throws Exception;
}
