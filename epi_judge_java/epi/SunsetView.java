package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;
import java.util.stream.Collectors;

public class SunsetView {

    private static class Bldg {
        public Integer id, height;

        public Bldg(Integer id, Integer height) {
            this.id = id;
            this.height = height;
        }
    }

    public static List<Integer> examineBuildingsWithSunset(Iterator<Integer> seq) {
        int bi = 0;
        Deque<Bldg> stack = new LinkedList<>();
        while (seq.hasNext()) {
            int bh = seq.next();
            while (!stack.isEmpty() && bh >= stack.peek().height) stack.pop();
            stack.push(new Bldg(bi++, bh));
        }
        return stack.stream().map(b -> b.id).collect(Collectors.toList());
    }

    @EpiTest(testDataFile = "sunset_view.tsv")
    public static List<Integer>
    examineBuildingsWithSunsetWrapper(List<Integer> sequence) {
        return examineBuildingsWithSunset(sequence.iterator());
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SunsetView.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}