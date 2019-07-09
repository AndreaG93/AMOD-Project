package linearproblem;

import linearproblem.utility.MathematicalSymbol;
import linearproblem.utility.VariableType;

/**
 * This class is used to model an generic ILP.
 * -> Only necessary methods for CSP-resolution have been modeled!!
 *
 * @author Andrea Graziani
 * @version 1.0
 */
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
