package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.Map;

public class IsStringPermutableToPalindrome {
    @EpiTest(testDataFile = "is_string_permutable_to_palindrome.tsv")

    public static boolean canFormPalindrome(String s) {
        int count = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) map.merge(c, 1, Integer::sum);
        for (Map.Entry<Character, Integer> entry : map.entrySet())
            if (entry.getValue() % 2 != 0 && ++count > 1) return false;
        return true;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsStringPermutableToPalindrome.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
