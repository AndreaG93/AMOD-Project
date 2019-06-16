package CuttingStock;


import linearproblem.LinearProblem;


import java.util.Vector;


public class MasterProblem {

    private LinearProblem master;

    public MasterProblem(CuttingStockInstance cuttingStockInstance) {


        double x = cuttingStockInstance.getMaxItemLength();
        Vector<CuttingStockItem> demand = cuttingStockInstance.getDemand();


        //this.master = new MinimumLinearProblem(demand.size(), demand.size());

        int index = 0;
        for (CuttingStockItem item : demand) {
            this.master.setVariableCoefficientOfObjectiveFunction(index, 1.0);
            this.master.setConstraintsCoefficient(index, index, ((int) (cuttingStockInstance.getMaxItemLength() / item.getLength())));
            this.master.setConstraintsLeftSideValue(index, item.getAmount());

            index++;
        }

        this.master.printAsLatexString();
    }

    private void singleItemCuttingPatterns(CuttingStockInstance cuttingStockInstance) {


    }

}
