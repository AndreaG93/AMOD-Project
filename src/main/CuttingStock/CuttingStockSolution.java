package CuttingStock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class CuttingStockSolution {

    private ArrayList<CuttingStockPattern> patterns;

    public CuttingStockSolution(){
        this.patterns = new ArrayList<>();
    }

    public void addPattern(CuttingStockPattern input){
        this.patterns.add(input);
    }

    public void print(){

        for (CuttingStockPattern pattern : this.patterns){

            double total = 0;
            for (int i = 0; i < pattern.getPattern().length; i++){
                total = total + pattern.getPattern()[i];
            }

            String output = String.format(" %d x %s - total: %f ", pattern.getAmount(), Arrays.toString(pattern.getPattern()),total);
            System.out.println(output);

        }
    }
}
