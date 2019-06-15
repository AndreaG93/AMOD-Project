import java.util.*;

class CuttingStockInstance {

    private double rollLength;
    private Map<Integer, Double> itemsIDSizeMap;

    CuttingStockInstance(Map<Integer, Double> itemsIDSizeMap, double rollLength) {

        this.itemsIDSizeMap = new LinkedHashMap<>(itemsIDSizeMap);
        this.rollLength  = rollLength;
    }

    Set<Integer> getDemandOfRequiredItems() {
        return this.itemsIDSizeMap.keySet();
    }

    Collection<Double> getLengthsOfRequiredItems() {
        return this.itemsIDSizeMap.values();
    }

    double getRollLength() {
        return rollLength;
    }

    /*
    @Override
    public String toString() {
        return "Instance [capacity=" + capacity + ", orders=" + orders + "]";

    }
*/
}