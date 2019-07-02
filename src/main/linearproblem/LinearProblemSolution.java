package linearproblem;

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