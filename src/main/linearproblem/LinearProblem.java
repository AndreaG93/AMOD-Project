package linearproblem;

import linearproblem.math.MathematicalSymbol;
import linearproblem.math.Matrix;
import linearproblem.type.LinearProblemType;
import java.util.Locale;
import java.util.Vector;

public class LinearProblem {

    private final LinearProblemType type;

    private Vector<Double> coefficientObjectiveFunction;
    private Vector<Double> knownCoefficients;
    private Vector<MathematicalSymbol> constraintsInequalitySymbols;
    private Matrix constraintsCoefficients;

    LinearProblem(LinearProblemType type, int initialNumberOfVariables, int initialNumberOfConstrains) {
        this.type = type;



    }

    public void initialize(int initialNumberOfVariables, int initialNumberOfConstrains) {

        this.coefficientObjectiveFunction = new Vector<>();
        for (int i = 0; i < initialNumberOfVariables; i++)
            this.coefficientObjectiveFunction.add(0.0);

        this.knownCoefficients = new Vector<>();
        for (int i = 0; i < initialNumberOfConstrains; i++)
            this.knownCoefficients.add(0.0);

        this.constraintsCoefficients = new Matrix(initialNumberOfConstrains, initialNumberOfVariables);

        this.constraintsInequalitySymbols = new Vector<>();
        for (int i = 0; i < initialNumberOfConstrains; i++)
            this.constraintsInequalitySymbols.add(MathematicalSymbol.GEQ);
    }


    public LinearProblemType getType() {
        return type;
    }

    public void setVariableCoefficientOfObjectiveFunction(int variableIndex, double value) {
        this.coefficientObjectiveFunction.set(variableIndex, value);
    }

    public void setConstraintsLeftSideValue(int constraintIndex, double value) {
        this.knownCoefficients.set(constraintIndex, value);
    }

    public void setConstraintsInequalitySymbols(int constraintIndex, MathematicalSymbol value) {
        this.constraintsInequalitySymbols.set(constraintIndex, value);
    }

    public void setConstraintsCoefficient(int constraintIndex, int variableIndex, double value) {
        this.constraintsCoefficients.setValue(constraintIndex, variableIndex, value);
    }


    /***** */

    public void printAsLatexString() {

        int numberOfVariables = this.coefficientObjectiveFunction.size();
        int numberOfConstraints = this.coefficientObjectiveFunction.size();

        StringBuilder latexOutput = new StringBuilder();

        latexOutput.append("\\documentclass[border=0.50001bp,convert={convertexe={convert},outext=.png}]{standalone}");
        latexOutput.append("\\usepackage{amsfonts}\n\\usepackage{amsmath}\n");
        latexOutput.append("\\begin{document}\n\\begin{tabular}{");
        latexOutput.append("c".repeat(this.coefficientObjectiveFunction.size() + 3)).append("}\n");
        latexOutput.append("min &");

        // Objective -----------------------
        for (int i = 0; i < numberOfVariables - 1; i++)
            latexOutput.append(String.format(Locale.US, " $%.2fx_{%d}$ &", this.coefficientObjectiveFunction.get(i), i + 1));
        latexOutput.append(String.format(Locale.US, "$%.2fx_{%d}$ & & \\\\\n", this.coefficientObjectiveFunction.get(numberOfVariables - 1), numberOfVariables));

        // Matrix coefficient -----------------------
        for (int rowIndex = 0; rowIndex < numberOfConstraints; rowIndex++) {

            latexOutput.append("&");

            for (int columnIndex = 0; columnIndex < numberOfVariables - 1; columnIndex++) {

                double value = this.constraintsCoefficients.getValue(rowIndex, columnIndex);

                if (value != 0)
                    latexOutput.append(String.format(Locale.US, " $%.2fx_{%d}$ &", value, columnIndex + 1));
                else
                    latexOutput.append(" & ");
            }

            double value = this.constraintsCoefficients.getValue(rowIndex, numberOfVariables - 1);

            if (value != 0)
                latexOutput.append(String.format(Locale.US, " $%.2fx_{%d}$ &", value, numberOfVariables));
            else
                latexOutput.append(" & ");

            latexOutput.append(String.format(Locale.US, " $\\geq$ & $%.2f$ \\\\\n", this.knownCoefficients.get(rowIndex)));
        }

        latexOutput.append("\\end{tabular}\n\\end{document}\n");

        System.out.println(latexOutput.toString());
    }
}
