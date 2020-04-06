package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class SearchFirstKey {
    @EpiTest(testDataFile = "search_first_key.tsv")
    public static int searchFirstOfK(List<Integer> A, int k) {
        int index = -1, lower = 0, upper = A.size() - 1, mid;
        while (lower <= upper) {
            mid = lower + (upper - lower) / 2;
            if (A.get(mid) == k) {
                index = mid;
                upper = mid - 1;
            } else if (A.get(mid) < k) lower = mid + 1;
            else upper = mid - 1;
        }
        return index;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SearchFirstKey.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
