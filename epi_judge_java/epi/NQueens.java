package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

import java.util.ArrayList;
import java.util.List;

public class NQueens {

    @EpiTest(testDataFile = "n_queens.tsv")
    public static List<List<Integer>> nQueens(int n) {
        List<List<Integer>> results = new ArrayList<>();
        solveNQueens(n, 0, new ArrayList<>(), results);
        return results;
    }

    private static void solveNQueens(int n, int row, List<Integer> colPlacement, List<List<Integer>> results) {
        if (row == n) results.add(new ArrayList<>(colPlacement));
        else
            for (int col = 0; col < n; ++col) {
                colPlacement.add(col);
                if (isValid(colPlacement)) solveNQueens(n, row + 1, colPlacement, results);
                colPlacement.remove(colPlacement.size() - 1);
            }
    }

    private static boolean isValid(List<Integer> colPlacement) {
        int lastRow = colPlacement.size() - 1;
        for (int row = 0; row < lastRow; ++row) {
            int diff = Math.abs(colPlacement.get(row) - colPlacement.get(lastRow));
            if (diff == 0 || diff == lastRow - row) return false;
        }
        return true;
    }

    @EpiTestComparator
    public static boolean comp(List<List<Integer>> expected, List<List<Integer>> result) {
        if (result == null) return false;
        expected.sort(new LexicographicalListComparator<>());
        result.sort(new LexicographicalListComparator<>());
        return expected.equals(result);
    }

    public static void main(String[] args) {
        System.exit(GenericTest.runFromAnnotations(args, "NQueens.java", new Object() {
        }.getClass().getEnclosingClass()).ordinal());
    }

}
