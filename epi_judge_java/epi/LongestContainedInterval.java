package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LongestContainedInterval {

    @EpiTest(testDataFile = "longest_contained_interval.tsv")
    public static int longestContainedRange(List<Integer> A) {
        Set<Integer> remaining = new HashSet<>(A);
        int maxIntervalSize = 0;
        while (!remaining.isEmpty()) {
            int a = remaining.iterator().next();
            remaining.remove(a);
            int lowerBound = a - 1;
            while (remaining.contains(lowerBound)) remaining.remove(lowerBound--);
            int upperBound = a + 1;
            while (remaining.contains(upperBound)) remaining.remove(upperBound++);
            maxIntervalSize = Math.max(upperBound - lowerBound - 1, maxIntervalSize);
        }
        return maxIntervalSize;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LongestContainedInterval.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}