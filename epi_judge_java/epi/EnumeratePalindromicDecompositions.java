package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

import java.util.ArrayList;
import java.util.List;

public class EnumeratePalindromicDecompositions {

    @EpiTest(testDataFile = "enumerate_palindromic_decompositions.tsv")
    public static List<List<String>> decompositions(String text) {
        List<List<String>> results = new ArrayList<>();
        decompositions(text, 0, new ArrayList<>(), results);
        return results;
    }

    private static void decompositions(String text, int i, List<String> partial, List<List<String>> results) {
        if (i == text.length()) {
            results.add(new ArrayList<>(partial));
            return;
        }
        for (int j = i + 1; j <= text.length(); ++j) {
            String prefix = text.substring(i, j);
            if (isPalindrome(prefix)) {
                partial.add(prefix);
                decompositions(text, j, partial, results);
                partial.remove(partial.size() - 1);
            }
        }
    }

    private static boolean isPalindrome(String prefix) {
        for (int i = 0, j = prefix.length() - 1; i < j; ++i, --j)
            if (prefix.charAt(i) != prefix.charAt(j)) return false;
        return true;
    }

    @EpiTestComparator
    public static boolean comp(List<List<String>> expected, List<List<String>> result) {
        if (result == null) return false;
        expected.sort(new LexicographicalListComparator<>());
        result.sort(new LexicographicalListComparator<>());
        return expected.equals(result);
    }

    public static void main(String[] args) {
        System.exit(GenericTest.runFromAnnotations(args, "EnumeratePalindromicDecompositions.java", new Object() {
        }.getClass().getEnclosingClass()).ordinal());
    }

}