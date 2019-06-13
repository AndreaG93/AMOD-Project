import java.util.Collections;
import java.util.LinkedHashMap;

import java.util.Map;

import java.util.Random;

import java.util.Set;

class CuttingStockInstance {

    private int capacity;
    private Map<Integer, Integer> orders;


    /**
     * Creates a cutting stock instance based on the provided orders and a capacity
     *
     * @param orders   the orders that need to be produced
     * @param capacity the length or capacity of the base stock we must cut up
     */
    CuttingStockInstance(Map<Integer, Integer> orders, int capacity) {

        this.orders = new LinkedHashMap<>(orders);
        this.capacity = capacity;
    }


    /**
     * Provides a list of sizes that occur in the orders in this instance.
     * <p>
     * The size of the order is the amount of stock required to produce a
     * <p>
     * single item of a particular size.
     *
     * @return a list with the sizes of the order.
     */

    public Set<Integer> getSizes() {
        return Collections.unmodifiableSet(orders.keySet());
    }


    /**
     * Provides how many copies of items of the provides size need to be
     * <p>
     * produces
     *
     * @param size the size of the item to be produced
     * @return how many copies of the item must be produced
     */

    public int getAmount(int size) {

        return orders.getOrDefault(size, 0);

    }


    /**
     * Provides the capacity of a unit of base stock that will be cut up
     * <p>
     * in order to produce the items in the orders.
     *
     * @return the capacity of a unit of base stock
     */

    public int getCapacity() {

        return capacity;

    }


    /**
     * Method that can be used to generate a random cutting-stock instance
     *
     * @param seed      a random seed
     * @param orders    the number unique sizes that occur in the orders
     * @param maxStep   the maximum step size of the sizes in the orders
     * @param maxAmount the maximum amount that can be ordered of a single size
     * @return a random instance based on the provided information
     */

    public static CuttingStockInstance randomInstance(long seed, int orders, int maxStep, int maxAmount) {

        Random ran = new Random(seed);

        Map<Integer, Integer> map = new LinkedHashMap<>();

        int curLength = 0;

        for (int t = 0; t < orders; t++) {

            curLength += 1 + ran.nextInt(maxStep);

            int amount = 1 + ran.nextInt(maxAmount);

            map.put(curLength, amount);

        }

        curLength += 1 + ran.nextInt(maxStep);

        return new CuttingStockInstance(map, curLength);

    }


    @Override

    public String toString() {

        return "Instance [capacity=" + capacity + ", orders=" + orders + "]";

    }

}