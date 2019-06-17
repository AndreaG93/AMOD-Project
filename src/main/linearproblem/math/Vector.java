package linearproblem.math;

import java.util.ArrayList;
import java.util.Locale;

public class Vector {

    private ArrayList<Double> vector;

    public Vector(int initialNumberOfItem) {

        this.vector = new ArrayList<>();

        for (int index = 0; index < initialNumberOfItem; index++)
            this.vector.add(0.0);
    }

    public void add(double value) {
        this.vector.add(value);
    }

    public double getValue(int index) {
        return this.vector.get(index);
    }

    public void setValue(int index, double value) {
        this.vector.set(index, value);
    }

    public double[] toArray() {
        return this.vector.stream().mapToDouble(d -> d).toArray();
    }

    public void print() {

        for (Double value : this.vector)
            System.out.printf(Locale.US, "%f ", value);

        System.out.println("\n");
    }

    public int size(){
        return this.vector.size();
    }
}
