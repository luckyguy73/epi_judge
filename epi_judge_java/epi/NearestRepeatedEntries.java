package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NearestRepeatedEntries {

    @EpiTest(testDataFile = "nearest_repeated_entries.tsv")
    public static int findNearestRepetition(List<String> paragraph) {
        Map<String, Integer> map = new HashMap<>();
        int distance = Integer.MAX_VALUE;
        for (int i = 0; i < paragraph.size(); ++i) {
            if (map.containsKey(paragraph.get(i)))
                distance = Math.min(distance, i - map.get(paragraph.get(i)));
            map.put(paragraph.get(i), i);
        }
        return distance != Integer.MAX_VALUE ? distance : -1;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "NearestRepeatedEntries.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}