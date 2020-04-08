package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.Map;

public class IsAnonymousLetterConstructible {

    @EpiTest(testDataFile = "is_anonymous_letter_constructible.tsv")
    public static boolean isLetterConstructibleFromMagazine(String letterText, String magazineText) {
        Map<Character, Integer> map = new HashMap<>();
        for (Character c : letterText.toCharArray()) map.merge(c, 1, Integer::sum);
        for (Character m : magazineText.toCharArray()) {
            if (map.containsKey(m)) map.put(m, map.get(m) - 1);
            map.remove(m, 0);
        }
        return map.isEmpty();
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsAnonymousLetterConstructible.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
