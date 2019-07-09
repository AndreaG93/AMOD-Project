package linearproblem;

/**
 * This class is used to model an ILP solution but for CSP-problems only.
 *
 * @author Andrea Graziani
 * @version 1.0
 */
public class LinearProblemSolution {

    private int[] integerSolutions;
    private int integerValueOfObjectiveFunction;

    private double[] realSolutions;
    private double realValueOfObjectiveFunction;

    public LinearProblemSolution(double[] realSolutions, double realValueOfObjectiveFunction) {

        this.integerValueOfObjectiveFunction = 0;
        this.integerSolutions = new int[realSolutions.length];

        this.realValueOfObjectiveFunction = realValueOfObjectiveFunction;
        this.realSolutions = realSolutions;

        // Integer solution are computed rounding real solutions.
        for (int i = 0; i < this.realSolutions.length; i++){

            this.integerSolutions[i] = (int) Math.ceil(this.realSolutions[i]);
            this.integerValueOfObjectiveFunction += integerSolutions[i];
        }
    }

    public int[] getIntegerSolutions() {
        return integerSolutions;
    }

    public int getIntegerValueOfObjectiveFunction() {
        return integerValueOfObjectiveFunction;
    }

    public double[] getRealSolutions() {
        return realSolutions;
    }

    public double getRealValueOfObjectiveFunction() {
        return realValueOfObjectiveFunction;
    }
}