package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class NumberOfScoreCombinations {

    @EpiTest(testDataFile = "number_of_score_combinations.tsv")
    public static int numCombinationsForFinalScore(int finalScore, List<Integer> scoringPlays) {
        int[][] combinations = new int[scoringPlays.size()][finalScore + 1];
        for (int i = 0; i < scoringPlays.size(); ++i) {
            combinations[i][0] = 1;
            for (int j = 1; j <= finalScore; ++j) {
                int withoutPlay = i - 1 >= 0 ? combinations[i - 1][j] : 0;
                int withPlay = j >= scoringPlays.get(i) ? combinations[i][j - scoringPlays.get(i)] : 0;
                combinations[i][j] = withoutPlay + withPlay;
            }
        }
        return combinations[scoringPlays.size() - 1][finalScore];
    }

    public static void main(String[] args) {
        System.exit(GenericTest.runFromAnnotations(args, "NumberOfScoreCombinations.java", new Object() {
        }.getClass().getEnclosingClass()).ordinal());
    }

}