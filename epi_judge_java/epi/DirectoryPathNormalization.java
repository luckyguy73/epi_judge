package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class DirectoryPathNormalization {
    @EpiTest(testDataFile = "directory_path_normalization.tsv")

    public static String shortestEquivalentPath(String path) {
        if (path.equals("")) throw new IllegalArgumentException("Empty string is not a legal path.");
        Deque<String> pathNames = new ArrayDeque<>();
        if (path.startsWith("/")) pathNames.push("/");
        for (String token : path.split("/")) {
            if (token.equals("..")) {
                if (pathNames.isEmpty() || pathNames.peekFirst().equals("..")) pathNames.push(token);
                else {
                    if (pathNames.peekFirst().equals("/"))
                        throw new IllegalArgumentException("Path error, trying to go up root " + path);
                    pathNames.pop();
                }
            } else if (!token.equals(".") && !token.isEmpty()) pathNames.push(token);
        }

        StringBuilder result = new StringBuilder();
        if (!pathNames.isEmpty()) {
            Iterator<String> it = pathNames.descendingIterator();
            String prev = it.next();
            result.append(prev);
            while (it.hasNext()) {
                if (!prev.equals("/")) result.append("/");
                prev = it.next();
                result.append(prev);
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "DirectoryPathNormalization.java",
                                new Object() {}.getClass().getEnclosingClass())
                        .ordinal());
    }
}