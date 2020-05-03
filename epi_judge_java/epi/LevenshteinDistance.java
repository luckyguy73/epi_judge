package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Arrays;

public class LevenshteinDistance {

    @EpiTest(testDataFile = "levenshtein_distance.tsv")
    public static int levenshteinDistance(String A, String B) {
        int[][] distance = new int[A.length()][B.length()];
        for (int[] row : distance) Arrays.fill(row, -1);
        return computeDistanceBetweenPrefixes(A, A.length() - 1, B, B.length() - 1, distance);
    }

    private static int computeDistanceBetweenPrefixes(String A, int aIdx, String B, int bIdx, int[][] distance) {
        if (aIdx < 0) return bIdx + 1;
        else if (bIdx < 0) return aIdx + 1;
        if (distance[aIdx][bIdx] == -1) if (A.charAt(aIdx) == B.charAt(bIdx))
            distance[aIdx][bIdx] = computeDistanceBetweenPrefixes(A, aIdx - 1, B, bIdx - 1, distance);
        else {
            int substituteLast = computeDistanceBetweenPrefixes(A, aIdx - 1, B, bIdx - 1, distance);
            int addLast = computeDistanceBetweenPrefixes(A, aIdx, B, bIdx - 1, distance);
            int deleteLast = computeDistanceBetweenPrefixes(A, aIdx - 1, B, bIdx, distance);
            distance[aIdx][bIdx] = 1 + Math.min(substituteLast, Math.min(addLast, deleteLast));
        }
        return distance[aIdx][bIdx];
    }

    public static void main(String[] args) {
        System.exit(GenericTest.runFromAnnotations(args, "LevenshteinDistance.java", new Object() {
        }.getClass().getEnclosingClass()).ordinal());
    }

}