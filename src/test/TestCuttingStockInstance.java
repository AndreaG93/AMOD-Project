

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestCuttingStockInstance {

    @Test
    void test() {

        Map<Integer, Double> demand = new HashMap<>();
        demand.put(48, 2.0);
        demand.put(35, 4.5);
        demand.put(24, 5.0);
        demand.put(10, 5.5);
        demand.put(8, 7.5);

        CuttingStockInstance instance = new CuttingStockInstance(demand, 11.0);

        System.out.println(instance.getRollLength());
        System.out.println(instance.getDemandOfRequiredItems());
        System.out.println(instance.getLengthsOfRequiredItems());
        System.out.println(11/2);

        assertEquals(4.0, 4.0);
    }
}
