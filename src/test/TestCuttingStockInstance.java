

import CuttingStock.CuttingStockInstance;
import CuttingStock.CuttingStockItem;
import CuttingStock.MasterProblem;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestCuttingStockInstance {

    @Test
    void test() {

        CuttingStockInstance instance = new CuttingStockInstance(11.0);

        instance.addCuttingStockItem(new CuttingStockItem(48, 2.0));
        instance.addCuttingStockItem(new CuttingStockItem(35, 4.5));
        instance.addCuttingStockItem(new CuttingStockItem(24, 5.0));
        instance.addCuttingStockItem(new CuttingStockItem(10, 5.5));
        instance.addCuttingStockItem(new CuttingStockItem(8, 7.5));

        System.out.println(instance.getMaxItemLength());

        MasterProblem myMaster = new MasterProblem(instance);


    }
}
