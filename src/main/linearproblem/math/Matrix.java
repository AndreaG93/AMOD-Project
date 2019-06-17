package linearproblem.math;

import java.util.ArrayList;

public class Matrix {

    private ArrayList<Vector> matrix;

    public Matrix(int initialNumberOfRows, int initialNumberOfColumns) {

        this.matrix = new ArrayList<>();

        initialNumberOfRows = (initialNumberOfRows < 1) ? 1 : initialNumberOfRows;
        initialNumberOfColumns = (initialNumberOfColumns < 1) ? 1 : initialNumberOfColumns;

        for (int i = 0; i < initialNumberOfRows; i++)
            this.matrix.add(new Vector(initialNumberOfColumns));
    }

    public double getValue(int rowIndex, int columnIndex) {
        return this.matrix.get(rowIndex).getValue(columnIndex);
    }

    public void setValue(int rowIndex, int column, double value) {
        this.matrix.get(rowIndex).setValue(column, value);
    }

    public void insertNewColumn() {

        for (Vector row : this.matrix)
            row.add(0.0);
    }

    public void print() {

        for (Vector row : this.matrix)
            row.print();
    }

    public double[] getRow(int rowIndex) {
        return this.matrix.get(rowIndex).toArray();
    }

    public int height() {
        return this.matrix.size();
    }

    public int width() {
        return this.matrix.get(0).size();
    }
}