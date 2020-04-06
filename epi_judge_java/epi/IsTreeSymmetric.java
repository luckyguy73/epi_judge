package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsTreeSymmetric {
    @EpiTest(testDataFile = "is_tree_symmetric.tsv")

    public static boolean isSymmetric(BinaryTreeNode<Integer> tree) {
        return tree == null || checkSymmetry(tree.left, tree.right);
    }

    public static boolean checkSymmetry(BinaryTreeNode<Integer> t1, BinaryTreeNode<Integer> t2) {
        if (t1 == null && t2 == null) return true;
        else if (t1 != null && t2 != null) return t1.data.equals(t2.data) &&
                checkSymmetry(t1.left, t2.right) && checkSymmetry(t1.right, t2.left);
        return false;
    }

    public static void main(String[] args) {
        System.exit(GenericTest.runFromAnnotations(args, "IsTreeSymmetric.java",
                new Object() {}.getClass().getEnclosingClass()).ordinal());
    }
}
