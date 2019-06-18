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

    public void print() {

        for (int i = 0; i < this.solutions.length; i++)
            System.out.println(String.format(Locale.US, "x_%d = %f", i+1, this.solutions[i]));
    }
}