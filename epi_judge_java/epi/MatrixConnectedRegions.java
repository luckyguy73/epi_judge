package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.*;

public class MatrixConnectedRegions {

    private static class Coordinate {

        public Integer x, y;

        public Coordinate(Integer x, Integer y) {
            this.x = x;
            this.y = y;
        }

    }

    public static void flipColor(int x, int y, List<List<Boolean>> image) {
        boolean color = image.get(x).get(y);
        Queue<Coordinate> q = new ArrayDeque<>();
        image.get(x).set(y, !color);
        q.add(new Coordinate(x, y));
        while (!q.isEmpty()) {
            Coordinate c = q.poll();
            for (Coordinate n : List.of(new Coordinate(c.x + 1, c.y), new Coordinate(c.x - 1, c.y),
                    new Coordinate(c.x, c.y + 1), new Coordinate(c.x, c.y - 1))) {
                if (n.x >= 0 && n.x < image.size() && n.y >= 0 && n.y < image.get(x).size() &&
                        image.get(n.x).get(n.y) == color) {
                    image.get(n.x).set(n.y, !color);
                    q.add(n);
                }
            }
        }
    }

    @EpiTest(testDataFile = "painting.tsv")
    public static List<List<Integer>> flipColorWrapper(TimedExecutor executor, int x, int y, List<List<Integer>> image)
            throws Exception {
        List<List<Boolean>> B = new ArrayList<>();
        for (int i = 0; i < image.size(); i++) {
            B.add(new ArrayList<>());
            for (int j = 0; j < image.get(i).size(); j++) B.get(i).add(image.get(i).get(j) == 1);
        }
        executor.run(() -> flipColor(x, y, B));
        image = new ArrayList<>();
        for (int i = 0; i < B.size(); i++) {
            image.add(new ArrayList<>());
            for (int j = 0; j < B.get(i).size(); j++) image.get(i).add(B.get(i).get(j) ? 1 : 0);
        }
        return image;
    }

    public static void main(String[] args) {
        System.exit(GenericTest.runFromAnnotations(args, "MatrixConnectedRegions.java", new Object() {
        }.getClass().getEnclosingClass()).ordinal());
    }

}
