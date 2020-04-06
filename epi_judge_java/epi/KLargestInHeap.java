package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class KLargestInHeap {

    private static class HeapEntry {
        public Integer index;
        public Integer value;

        public HeapEntry(Integer index, Integer value) {
            this.index = index;
            this.value = value;
        }
    }

    private static final int CAPACITY = 16;

    @EpiTest(testDataFile = "k_largest_in_heap.tsv")
    public static List<Integer> kLargestInBinaryHeap(List<Integer> A, int k) {
        if (k <= 0) return Collections.emptyList();
        PriorityQueue<HeapEntry> maxHeap = new PriorityQueue<>(CAPACITY,
                (o1, o2) -> Integer.compare(o2.value, o1.value));
        List<Integer> results = new ArrayList<>();
        maxHeap.add(new HeapEntry(0, A.get(0)));
        for (int i = 0; i < k; ++i) {
            Integer candidateIndex = maxHeap.peek().index;
            results.add(maxHeap.remove().value);
            Integer leftIndex = 2 * candidateIndex + 1;
            if (leftIndex < A.size()) maxHeap.add(new HeapEntry(leftIndex, A.get(leftIndex)));
            Integer rightIndex = 2 * candidateIndex + 2;
            if (rightIndex < A.size()) maxHeap.add(new HeapEntry(rightIndex, A.get(rightIndex)));
        }
        return results;
    }

    @EpiTestComparator
    public static boolean comp(List<Integer> expected, List<Integer> result) {
        if (result == null) {
            return false;
        }
        Collections.sort(expected);
        Collections.sort(result);
        return expected.equals(result);
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "KLargestInHeap.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}