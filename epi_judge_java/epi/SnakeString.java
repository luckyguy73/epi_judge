package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SnakeString {
    @EpiTest(testDataFile = "snake_string.tsv")

    public static String snakeString(String s) {

        StringBuilder result = new StringBuilder();
        // Outputs the first row, i.e., s[1], s[5], s[9], ...
        result.append(outputRow(new StringBuilder(), 1, 4, s));
        // Outputs the second row, i.e., s[0], s[2], s[4], ...
        result.append(outputRow(new StringBuilder(), 0, 2, s));
        // Outputs the third row, i.e., s[3], s[7], s[11], ...
        result.append(outputRow(new StringBuilder(), 3, 4, s));

        return result.toString();
    }

    public static StringBuilder outputRow(StringBuilder r, int s, int o, String word) {
        for (int i = s; i < word.length(); i += o) r.append(word.charAt(i));
        return r;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SnakeString.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}