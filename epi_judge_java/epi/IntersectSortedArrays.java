package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class IntersectSortedArrays {

    @EpiTest(testDataFile = "intersect_sorted_arrays.tsv")
    public static List<Integer> intersectTwoSortedArrays(List<Integer> A, List<Integer> B) {
//        List<Integer> intersection = new ArrayList<>();
//        int i = 0, j = 0;
//        while (i < A.size() && j < B.size()) {
//            int a = A.get(i);
//            int b = B.get(j);
//            if (a == b) {
//                if (!intersection.contains(a)) intersection.add(a);
//                i++;
//                j++;
//            } else if (a < b) i++;
//            else j++;
//        }
//        return intersection;

        List<Integer> intersectionAB = new ArrayList<>();
        for (int i = 0; i < A.size(); ++i) {
            if ((i == 0 || !A.get(i).equals(A.get(i - 1))) && B.contains(A.get(i))) {
                intersectionAB.add(A.get(i));
            }
        }
        return intersectionAB;
    }

    public static void main(String[] args) {
        System.exit(GenericTest.runFromAnnotations(args, "IntersectSortedArrays.java", new Object() {
                                }.getClass().getEnclosingClass()).ordinal());
    }
}
