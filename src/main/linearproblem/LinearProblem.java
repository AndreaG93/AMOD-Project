package linearproblem;

import linearproblem.math.MathematicalSymbol;
import linearproblem.math.Matrix;
import linearproblem.math.Vector;

import java.util.ArrayList;
import java.util.Locale;


/**
 * This class is used to model a generic linear problem.
 * <p>
 * Class fields are named using following syntax:
 * <p>
 * min [max] cx
 * Ax = (>=, <=, >, <) b
 */
public class LinearProblem {

    private final LinearProblemType type;

    private Vector c;
    private Vector b;
    private Matrix A;
    private ArrayList<MathematicalSymbol> constraintInequalitySymbols;
    private boolean areIntegerVariables;

    public LinearProblem(LinearProblemType type, boolean areVariablesBinary, int initialNumberOfVariables, int initialNumberOfConstrains) {
        this.type = type;
        this.areIntegerVariables = areVariablesBinary;

        this.c = new Vector(initialNumberOfVariables);
        this.b = new Vector(initialNumberOfConstrains);
        this.A = new Matrix(initialNumberOfConstrains, initialNumberOfVariables);
        this.constraintInequalitySymbols = new ArrayList<>();

        for (int i = 0; i < initialNumberOfConstrains; i++)
            this.constraintInequalitySymbols.add(MathematicalSymbol.EQ);
    }

    public void setVariableCoefficientOfSpecifiedConstraint(int constraintIndex, int variableIndex, double value) {
        this.A.setValue(constraintIndex, variableIndex, value);
    }

    public void setVariableCoefficientOfObjectiveFunction(int variableIndex, double value) {
        this.c.setValue(variableIndex, value);
    }

    public void setConstraintLeftSideValue(int constraintIndex, double value) {
        this.b.setValue(constraintIndex, value);
    }

    public void setMathematicalSymbolOfSpecifiedConstraint(int constraintIndex, MathematicalSymbol value) {
        this.constraintInequalitySymbols.set(constraintIndex, value);
    }

    public void setObjectiveFunctionCoefficient(double[] coefficient){

        for (int index = 0; index < coefficient.length; index++)
            this.c.setValue(index, coefficient[index]);
    }

    public double[] getCoefficientOfObjectiveFunction() {
        return this.c.toArray();
    }

    public double[] getVariableCoefficientOfSpecifiedConstraint(int index) {
        return this.A.getRow(index);
    }

    public MathematicalSymbol getMathematicalSymbol(int index) {
        return this.constraintInequalitySymbols.get(index);
    }

    public double getLeftValue(int index){
        return this.b.getValue(index);
    }


    public int getNumberOfConstrains(){
        return this.A.height();
    }

    public int getNumberOfVariables(){
        return this.A.width();
    }

    public LinearProblemType getType() {
        return type;
    }

    public boolean isAreIntegerVariables() {
        return areIntegerVariables;
    }

    public void setAreIntegerVariables(boolean areIntegerVariables) {
        this.areIntegerVariables = areIntegerVariables;
    }

    /***** */

    public void printAsLatexString() {

        int numberOfVariables = this.c.size();
        int numberOfConstraints = this.A.height();

        StringBuilder latexOutput = new StringBuilder();

        latexOutput.append("\\documentclass[border=0.50001bp,convert={convertexe={convert},outext=.png}]{standalone}");
        latexOutput.append("\\usepackage{amsfonts}\n\\usepackage{amsmath}\n");
        latexOutput.append("\\begin{document}\n\\begin{tabular}{");
        latexOutput.append("c".repeat(this.c.size() + 3)).append("}\n");
        latexOutput.append("min &");

        // Objective -----------------------
        for (int i = 0; i < numberOfVariables - 1; i++)
            latexOutput.append(String.format(Locale.US, " $%.2fx_{%d}$ &", this.c.getValue(i), i + 1));
        latexOutput.append(String.format(Locale.US, "$%.2fx_{%d}$ & & \\\\\n", this.c.getValue(numberOfVariables - 1), numberOfVariables));

        // Matrix coefficient -----------------------
        for (int rowIndex = 0; rowIndex < numberOfConstraints; rowIndex++) {

            latexOutput.append("&");

            for (int columnIndex = 0; columnIndex < numberOfVariables - 1; columnIndex++) {

                double value = this.A.getValue(rowIndex, columnIndex);

                if (value != 0)
                    latexOutput.append(String.format(Locale.US, " $%.2fx_{%d}$ &", value, columnIndex + 1));
                else
                    latexOutput.append(" & ");
            }

            double value = this.A.getValue(rowIndex, numberOfVariables - 1);

            if (value != 0)
                latexOutput.append(String.format(Locale.US, " $%.2fx_{%d}$ &", value, numberOfVariables));
            else
                latexOutput.append(" & ");

            latexOutput.append(String.format(Locale.US, " %s & $%.2f$ \\\\\n", this.constraintInequalitySymbols.get(rowIndex).getLatexMarkupString(), this.b.getValue(rowIndex)));
        }

        latexOutput.append("\\end{tabular}\n\\end{document}\n");

        System.out.println(latexOutput.toString());
    }
}
