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

    public double[] getRoundedSolutions() {

        double[] output = new double[this.solutions.length];

        for (int i = 0; i < this.solutions.length; i++){
            output[i] =   Math.ceil(this.solutions[i]);
        }

        return output;
    }
}