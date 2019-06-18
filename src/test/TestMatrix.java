import linearproblem.math.Matrix;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


class TestMatrix {


    void test() {

        Matrix matrix = new Matrix(3,3);

        matrix.setValue(0,0, 4.0);
        matrix.setValue(0,1, 8.0);
        matrix.setValue(0,2, 9.0);
        matrix.setValue(1,0, 33.0);
        matrix.setValue(1,1, 45.0);
        matrix.setValue(1,2, 746.0);
        matrix.setValue(2,0, 48.0);
        matrix.setValue(2,1, 49.0);
        matrix.setValue(2,2, 41.0);

        matrix.print();
    }


    @Test
    void test2() {

        ArrayList<Double> dd = new ArrayList<>();
        dd.add(3.0);
        dd.add(6.0);
        dd.add(10.0);

        for (Double value : dd){
            System.out.println(value);
        }

        Double[] array = new Double[dd.size()];

        array = dd.toArray(array);


        for (int i = 0; i < array.length; i++){
            System.out.println(array[i]);
        }
    }
}
