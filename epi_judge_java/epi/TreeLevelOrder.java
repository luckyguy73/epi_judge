package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;
import java.util.stream.Collectors;

public class TreeLevelOrder {
    @EpiTest(testDataFile = "tree_level_order.tsv")

//    public static List<List<Integer>> binaryTreeDepthOrder(BinaryTreeNode<Integer> tree) {
//        List<List<Integer>> result = new ArrayList<>();
//        if (tree == null) return result;
//        List<BinaryTreeNode<Integer>> q = List.of(tree);
//        while (!q.isEmpty()) {
//            result.add(q.stream().map(curr -> curr.data).collect(Collectors.toList()));
//            q = q.stream().map(curr -> Arrays.asList(curr.left, curr.right)).flatMap(List::stream)
//                    .filter(Objects::nonNull).collect(Collectors.toList());
//        }
//        return result;
//    }

    public static List<List<Integer>> binaryTreeDepthOrder(BinaryTreeNode<Integer> tree) {
        List<List<Integer>> results = new ArrayList<>();
        if (tree == null) return results;
        Deque<BinaryTreeNode<Integer>> q = new LinkedList<>(List.of(tree));
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; ++i) {
                BinaryTreeNode<Integer> node = q.poll();
                level.add(node.data);
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            results.add(level);
        }
        return results;
    }

    public static void main(String[] args) {
        System.exit(GenericTest.runFromAnnotations(args, "TreeLevelOrder.java",
                new Object() {
                }.getClass().getEnclosingClass()).ordinal());
    }
}
