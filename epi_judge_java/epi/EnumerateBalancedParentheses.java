package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EnumerateBalancedParentheses {

    @EpiTest(testDataFile = "enumerate_balanced_parentheses.tsv")
    public static List<String> genBalParens(int numPairs) {
        List<String> result = new ArrayList<>();
        genBalParens(numPairs, numPairs, "", result);
        return result;
    }

    private static void genBalParens(int leftNeeded, int rightNeeded, String validPrefix, List<String> result) {
        if (rightNeeded == 0) {
            result.add(validPrefix);
            return;
        }
        if (leftNeeded > 0) genBalParens(leftNeeded - 1, rightNeeded, validPrefix + "(", result);
        if (leftNeeded < rightNeeded) genBalParens(leftNeeded, rightNeeded - 1, validPrefix + ")", result);
    }

    @EpiTestComparator
    public static boolean comp(List<String> expected, List<String> result) {
        if (result == null) return false;
        Collections.sort(expected);
        Collections.sort(result);
        return expected.equals(result);
    }

    public static void main(String[] args) {
        System.exit(GenericTest.runFromAnnotations(args, "EnumerateBalancedParentheses.java", new Object() {
        }.getClass().getEnclosingClass()).ordinal());
    }

}
