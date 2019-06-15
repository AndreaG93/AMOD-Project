import LinearOptimization.math.Matrix;
import org.junit.jupiter.api.Test;
import java.util.Vector;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestMatrix {

    @Test
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

        Vector<Double> row = matrix.getRow(2);
        assertEquals(48, row.get(0));
        assertEquals(49, row.get(1));
        assertEquals(41, row.get(2));
    }
}
