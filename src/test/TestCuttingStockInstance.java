

import CuttingStock.CuttingStockInstance;
import CuttingStock.CuttingStockResolver;
import org.junit.jupiter.api.Test;



class TestCuttingStockInstance {


    void test() {

        CuttingStockInstance instance = new CuttingStockInstance(11.0);

        instance.addItems(48, 2.0);
        instance.addItems(35, 4.5);
        instance.addItems(24, 5.0);
        instance.addItems(10, 5.5);
        instance.addItems(8, 7.5);

        System.out.println(instance.getMaxItemLength());

        CuttingStockResolver resolver = new CuttingStockResolver(instance);
        resolver.solve();

    }

    @Test
    void test2() {

        CuttingStockInstance instance = new CuttingStockInstance(218);

        instance.addItems(44, 81);
        instance.addItems(3, 70);
        instance.addItems(48, 68);

        CuttingStockResolver resolver = new CuttingStockResolver(instance);
        resolver.solve();
    }



}
