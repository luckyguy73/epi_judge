package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PickingUpCoins {

    @EpiTest(testDataFile = "picking_up_coins.tsv")
    public static int pickUpCoins(List<Integer> coins) {
        return computeRevenue(coins, 0, coins.size() - 1, new int[coins.size()][coins.size()]);
    }

    private static int computeRevenue(List<Integer> coins, int a, int b, int[][] revenue) {
        if (a > b) return 0;
        if (revenue[a][b] == 0) {
            int maxRevenueA = coins.get(a) + Math.min(computeRevenue(coins, a + 2, b, revenue),
                    computeRevenue(coins, a + 1, b - 1, revenue));
            int maxRevenueB = coins.get(b) + Math.min(computeRevenue(coins, a + 1, b - 1, revenue),
                    computeRevenue(coins, a, b - 2, revenue));
            revenue[a][b] = Math.max(maxRevenueA, maxRevenueB);
        }
        return revenue[a][b];
    }

    public static void main(String[] args) {
        System.exit(GenericTest.runFromAnnotations(args, "PickingUpCoins.java", new Object() {
        }.getClass().getEnclosingClass()).ordinal());
    }

}
