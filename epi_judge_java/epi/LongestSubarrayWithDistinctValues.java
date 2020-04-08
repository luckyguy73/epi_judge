package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestSubarrayWithDistinctValues {

    @EpiTest(testDataFile = "longest_subarray_with_distinct_values.tsv")
    public static int longestSubarrayWithDistinctEntries(List<Integer> A) {
        Map<Integer, Integer> map = new HashMap<>();
        int start = 0, result = 0;
        for (int i = 0; i < A.size(); ++i) {
            Integer dupIdx = map.put(A.get(i), i);
            if (dupIdx != null) {
                if (dupIdx >= start) {
                    result = Math.max(result, i - start);
                    start = dupIdx + 1;
                }
            }
        }
        return Math.max(result, A.size() - start);
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LongestSubarrayWithDistinctValues.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}