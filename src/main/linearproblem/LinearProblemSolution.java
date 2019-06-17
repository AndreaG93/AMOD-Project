package linearproblem;

import java.util.Locale;

public class LinearProblemSolution {

    private final double[] solutions;
    private final double valueObjectiveFunction;

    public LinearProblemSolution(double[] solutions, double valueObjectiveFunction) {
        this.solutions = solutions;
        this.valueObjectiveFunction = valueObjectiveFunction;
    }

    public double[] getSolutions() {
        return solutions;
    }

    public double getValueObjectiveFunction() {
        return valueObjectiveFunction;
    }

    @Override
    public String toString() {

        StringBuilder output = new StringBuilder();


        output.append("sol = [");
        for (double value : this.solutions)
            output.append(String.format(Locale.US, " %f ", value));
        output.append("]\n");
        output.append(String.format(Locale.US, "Value of Objective Function: %f \n", this.valueObjectiveFunction));

        return output.toString();
    }
}