package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

import java.util.ArrayList;
import java.util.List;

public class Combinations {

    @EpiTest(testDataFile = "combinations.tsv")
    public static List<List<Integer>> combinations(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        combinations(n, k, 1, new ArrayList<>(), result);
        return result;
    }

    private static void combinations(int n, int k, int offset, List<Integer> p, List<List<Integer>> result) {
        if (p.size() == k) {
            result.add(new ArrayList<>(p));
            return;
        }
        final int numRemaining = k - p.size();
        for (int i = offset; i <= n && numRemaining <= n - i + 1; ++i) {
            p.add(i);
            combinations(n, k, i + 1, p, result);
            p.remove(p.size() - 1);
        }
    }

    @EpiTestComparator
    public static boolean comp(List<List<Integer>> expected, List<List<Integer>> result) {
        if (result == null) return false;
        expected.sort(new LexicographicalListComparator<>());
        result.sort(new LexicographicalListComparator<>());
        return expected.equals(result);
    }

    public static void main(String[] args) {
        System.exit(GenericTest.runFromAnnotations(args, "Combinations.java", new Object() {
        }.getClass().getEnclosingClass()).ordinal());
    }

}