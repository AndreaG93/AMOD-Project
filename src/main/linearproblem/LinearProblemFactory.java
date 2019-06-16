package linearproblem;

import linearproblem.type.Max;
import linearproblem.type.Min;

public class LinearProblemFactory {

    public static LinearProblem buildMaximumLinearProblem(int initialNumberOfVariables, int initialNumberOfConstrains) {
        return new LinearProblem(new Max(), initialNumberOfVariables, initialNumberOfConstrains);
    }

    public static LinearProblem buildMinimumLinearProblem(int initialNumberOfVariables, int initialNumberOfConstrains) {
        return new LinearProblem(new Min(), initialNumberOfVariables, initialNumberOfConstrains);
    }
}
