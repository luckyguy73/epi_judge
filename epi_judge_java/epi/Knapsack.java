package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.Arrays;
import java.util.List;

public class Knapsack {

    @EpiUserType(ctorParams = {Integer.class, Integer.class})
    public static class Item {

        public Integer weight;
        public Integer value;

        public Item(Integer weight, Integer value) {
            this.weight = weight;
            this.value = value;
        }

    }

    @EpiTest(testDataFile = "knapsack.tsv")
    public static int optimumSubjectToCapacity(List<Item> items, int capacity) {
        int[][] V = new int[items.size()][capacity + 1];
        for (int[] a : V) Arrays.fill(a, -1);
        return capacity(items, items.size() - 1, capacity, V);
    }

    private static int capacity(List<Item> items, int k, int capacity, int[][] V) {
        if (k < 0) return 0;
        if (V[k][capacity] == -1) {
            int withoutItem = capacity(items, k - 1, capacity, V);
            int withItem = capacity < items.get(k).weight ? 0 : items.get(k).value +
                    capacity(items, k - 1, capacity - items.get(k).weight, V);
            V[k][capacity] = Math.max(withItem, withoutItem);
        }
        return V[k][capacity];
    }

    public static void main(String[] args) {
        System.exit(GenericTest.runFromAnnotations(args, "Knapsack.java", new Object() {
        }.getClass().getEnclosingClass()).ordinal());
    }

}
