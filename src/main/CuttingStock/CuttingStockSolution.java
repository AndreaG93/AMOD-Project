package CuttingStock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class CuttingStockSolution {

    Map<Integer, CuttingStockPattern> solutions;

    CuttingStockSolution(){
        this.solutions = new HashMap<>();
    }

    void addNewPattern(int index, int patternCardinality) {
        this.solutions.put(index, new CuttingStockPattern(patternCardinality));
    }

    void addCuttingLengthToPattern(int index, double length) {

        CuttingStockPattern pattern = this.solutions.get(index);
        pattern.putCuttingLength(length);
    }

    public Map<Integer, CuttingStockPattern> getSolutions() {
        return solutions;
    }

    public void print(){

        for (Map.Entry<Integer, CuttingStockPattern> pair : solutions.entrySet()) {
            pair.getValue().print();
        }
    }
}
