package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class BstFromPreorder {

    private static Integer rootIdx;

    @EpiTest(testDataFile = "bst_from_preorder.tsv")
    public static BstNode<Integer> rebuildBSTFromPreorder(List<Integer> preorder) {
        rootIdx = 0;
        return rebuildBSTFromPreorder(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static BstNode<Integer> rebuildBSTFromPreorder(List<Integer> preorder, int lower, int upper) {
        if (rootIdx == preorder.size()) return null;
        Integer root = preorder.get(rootIdx);
        if (root < lower || root > upper) return null;
        ++rootIdx;
        return new BstNode<>(root, rebuildBSTFromPreorder(preorder, lower, root),
                rebuildBSTFromPreorder(preorder, root, upper));
    }

    public static void main(String[] args) {
        System.exit(GenericTest.runFromAnnotations(args, "BstFromPreorder.java", new Object() {
        }.getClass().getEnclosingClass()).ordinal());
    }

}
