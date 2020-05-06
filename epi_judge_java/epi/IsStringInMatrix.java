package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class IsStringInMatrix {

    private static class Attempt {

        public Integer x;
        public Integer y;
        public Integer offset;

        public Attempt(Integer x, Integer y, Integer offset) {
            this.x = x;
            this.y = y;
            this.offset = offset;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Attempt a = (Attempt) o;
            return Objects.equals(x, a.x) && Objects.equals(y, a.y) && Objects.equals(offset, a.offset);
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, offset);
        }

    }

    @EpiTest(testDataFile = "is_string_in_matrix.tsv")
    public static boolean isPatternContainedInGrid(List<List<Integer>> grid, List<Integer> pattern) {
        for (int i = 0; i < grid.size(); ++i)
            for (int j = 0; j < grid.get(i).size(); ++j)
                if (isPatternAtXY(grid, i, j, pattern, 0, new HashSet<>())) return true;
        return false;
    }

    private static boolean isPatternAtXY(List<List<Integer>> grid, int x, int y, List<Integer> pattern,
                                         int offset, Set<Attempt> previousAttempts) {
        if (pattern.size() == offset) return true;
        if (x < 0 || x >= grid.size() || y < 0 || y >= grid.get(x).size() ||
                previousAttempts.contains(new Attempt(x, y, offset)) ||
                !grid.get(x).get(y).equals(pattern.get(offset))) return false;
        for (List<Integer> nextXY : List.of(List.of(x - 1, y), List.of(x + 1, y),
                List.of(x, y - 1), List.of(x, y + 1)))
            if (isPatternAtXY(grid, nextXY.get(0), nextXY.get(1), pattern, offset + 1, previousAttempts)) return true;
        previousAttempts.add(new Attempt(x, y, offset));
        return false;
    }

    public static void main(String[] args) {
        System.exit(GenericTest.runFromAnnotations(args, "IsStringInMatrix.java", new Object() {
        }.getClass().getEnclosingClass()).ordinal());
    }

}
