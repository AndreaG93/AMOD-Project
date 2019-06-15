package LinearOptimization.math;

import java.util.Locale;
import java.util.Vector;

public class Matrix {

    private Vector<Vector<Double>> matrix;

    public Matrix(int initialNumberOfRows, int initialNumberOfColumns) {

        this.matrix = new Vector<>();

        for (int i = 0; i < initialNumberOfRows; i++) {

            Vector<Double> auxiliaryVector = new Vector<>();

            for (int y = 0; y < initialNumberOfColumns; y++)
                auxiliaryVector.add(0.0);

            this.matrix.add(auxiliaryVector);
        }
    }

    public double getValue(int rowIndex, int columnIndex) {
        return this.matrix.get(rowIndex).get(columnIndex);
    }

    public void setValue(int rowIndex, int column, double value) {
        this.matrix.get(rowIndex).set(column, value);
    }

    public void addNewColumn() {

        for (Vector<Double> row : this.matrix)
            row.add(0.0);
    }

    public void print() {

        for (Vector<Double> row : this.matrix) {
            for (Double value : row) {
                System.out.printf(Locale.US, "%f ", value);
            }

            System.out.println("");
        }
    }

    public Vector<Double> getRow(int rowIndex){
        return this.matrix.get(rowIndex);
    }
}
