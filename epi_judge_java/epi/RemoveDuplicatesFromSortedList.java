package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class RemoveDuplicatesFromSortedList {
    @EpiTest(testDataFile = "remove_duplicates_from_sorted_list.tsv")

    public static ListNode<Integer> removeDuplicates(ListNode<Integer> L) {
        ListNode<Integer> current = L, nextDistinct;
        while (current != null) {
            nextDistinct = current.next;
            while (nextDistinct != null && nextDistinct.data.equals(current.data)) nextDistinct = nextDistinct.next;
            current.next = nextDistinct;
            current = current.next;
        }
        return L;
    }

    public static void main(String[] args) {
        System.exit(GenericTest.runFromAnnotations(args, "RemoveDuplicatesFromSortedList.java",
                new Object() {
                }.getClass().getEnclosingClass()).ordinal());
    }
}
