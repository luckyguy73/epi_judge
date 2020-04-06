package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortIncreasingDecreasingArray {
    @EpiTest(testDataFile = "sort_increasing_decreasing_array.tsv")
    public static List<Integer> sortKIncreasingDecreasingArray(List<Integer> A) {
        List<List<Integer>> sortedSubarrays = new ArrayList<>();
        SubarrayType type = SubarrayType.INCREASING;
        int startIdx = 0;
        for (int i = 1; i <= A.size(); ++i) {
            if (i == A.size() || isChanging(A, type, i)) {
                List<Integer> sub = A.subList(startIdx, i);
                if (type == SubarrayType.DECREASING) Collections.reverse(sub);
                sortedSubarrays.add(sub);
                startIdx = i;
                type = type.toggle();
            }
        }
        return SortedArraysMerge.mergeSortedArrays(sortedSubarrays);
    }

    private static boolean isChanging(List<Integer> A, SubarrayType type, int i) {
        boolean dec = (A.get(i - 1) < A.get(i) && type == SubarrayType.DECREASING);
        boolean inc = (A.get(i - 1) >= A.get(i) && type == SubarrayType.INCREASING);
        return dec || inc;
    }

    private enum SubarrayType {
        INCREASING, DECREASING;
        private SubarrayType toggle() {
            return this.equals(INCREASING) ? DECREASING : INCREASING;
//            return values()[ordinal() ^ 1];
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SortIncreasingDecreasingArray.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}