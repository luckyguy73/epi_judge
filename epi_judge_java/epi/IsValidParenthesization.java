package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class IsValidParenthesization {
    @EpiTest(testDataFile = "is_valid_parenthesization.tsv")

    public static boolean isWellFormed(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        final Map<Character, Character> symbols = Map.of('(', ')', '{', '}', '[', ']');
        for (Character c : s.toCharArray()) {
            if (symbols.containsKey(c)) stack.push(c);
            else if (stack.isEmpty() || symbols.get(stack.pop()) != c) return false;
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsValidParenthesization.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
