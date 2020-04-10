package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KLargestValuesInBst {

    @EpiTest(testDataFile = "k_largest_values_in_bst.tsv")
    public static List<Integer> findKLargestInBst(BstNode<Integer> tree, int k) {
        List<Integer> values = new ArrayList<>();
        findKLargestInBstHelper(tree, k, values);
        return values;
    }

    private static void findKLargestInBstHelper(BstNode<Integer> tree, int k, List<Integer> values) {
        if (tree != null && values.size() < k) {
            findKLargestInBstHelper(tree.right, k, values);
            if (values.size() < k) {
                values.add(tree.data);
                findKLargestInBstHelper(tree.left, k, values);
            }
        }
    }

    @EpiTestComparator
    public static boolean comp(List<Integer> expected, List<Integer> result) {
        if (result == null) return false;
        Collections.sort(expected);
        Collections.sort(result);
        return expected.equals(result);
    }

    public static void main(String[] args) {
        System.exit(GenericTest.runFromAnnotations(args, "KLargestValuesInBst.java", new Object() {
        }.getClass().getEnclosingClass()).ordinal());
    }

}