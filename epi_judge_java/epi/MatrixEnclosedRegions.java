package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class MatrixEnclosedRegions {

    public static void fillSurroundedRegions(List<List<Character>> board) {
        for (int i = 0; i < board.size(); ++i) {
            markBoundaryRegion(i, 0, board);
            markBoundaryRegion(i, board.get(i).size() - 1, board);
        }
        for (int j = 0; j < board.get(0).size(); ++j) {
            markBoundaryRegion(0, j, board);
            markBoundaryRegion(board.size() - 1, j, board);
        }
        for (List<Character> characters : board)
            for (int j = 0; j < characters.size(); ++j)
                characters.set(j, characters.get(j) != 'T' ? 'B' : 'W');
    }

    private static class Coordinate {

        public Integer x;
        public Integer y;

        public Coordinate(Integer x, Integer y) {
            this.x = x;
            this.y = y;
        }

    }

    private static void markBoundaryRegion(int i, int j, List<List<Character>> board) {
        Queue<Coordinate> q = new ArrayDeque<>();
        q.add(new Coordinate(i, j));
        while (!q.isEmpty()) {
            Coordinate curr = q.poll();
            if (curr.x >= 0 && curr.x < board.size() && curr.y >= 0 && curr.y < board.get(curr.x).size() &&
                    board.get(curr.x).get(curr.y) == 'W') {
                board.get(curr.x).set(curr.y, 'T');
                q.add(new Coordinate(curr.x - 1, curr.y));
                q.add(new Coordinate(curr.x + 1, curr.y));
                q.add(new Coordinate(curr.x, curr.y - 1));
                q.add(new Coordinate(curr.x, curr.y + 1));
            }
        }
    }

    @EpiTest(testDataFile = "matrix_enclosed_regions.tsv")
    public static List<List<Character>> fillSurroundedRegionsWrapper(List<List<Character>> board) {
        fillSurroundedRegions(board);
        return board;
    }

    public static void main(String[] args) {
        System.exit(GenericTest.runFromAnnotations(args, "MatrixEnclosedRegions.java", new Object() {
        }.getClass().getEnclosingClass()).ordinal());
    }

}
