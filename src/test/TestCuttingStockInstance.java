

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


    void test2() {

        CuttingStockInstance instance = new CuttingStockInstance(218);

        instance.addItems(44, 81);
        instance.addItems(3, 70);
        instance.addItems(48, 68);

        CuttingStockResolver resolver = new CuttingStockResolver(instance);
        resolver.solve();
    }

    @Test
    void test3() {

        CuttingStockInstance instance = new CuttingStockInstance(5600);

        instance.addItems(22, 1380);
        instance.addItems(25, 1520);
        instance.addItems(12, 1560);

        instance.addItems(14, 1710);
        instance.addItems(18, 1820);
        instance.addItems(18, 1880);

        instance.addItems(20, 1930);
        instance.addItems(10, 2000);
        instance.addItems(12, 2050);

        instance.addItems(14, 2100);
        instance.addItems(16, 2140);
        instance.addItems(18, 2150);

        instance.addItems(20, 2200);

        CuttingStockResolver resolver = new CuttingStockResolver(instance);
        resolver.solve();
    }





}
