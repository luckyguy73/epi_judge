package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {

    private static Map<Integer, Integer> map = new HashMap<>();

    @EpiTest(testDataFile = "fibonacci.tsv")
//    public static int fibonacci(int n) {
//        if (n < 2) return n;
//        int fib1 = 0;
//        int fib2 = 1;
//        for (int i = 0; i < n; ++i) {
//            int f = fib1 + fib2;
//            fib1 = fib2;
//            fib2 = f;
//        }
//        return fib1;
//    }

    public static int fibonacci(int n) {
        if (n < 2) return n;
        if (map.containsKey(n)) return map.get(n);
        map.put(n, fibonacci(n - 1) + fibonacci(n - 2));
        return map.get(n);
    }

    public static void main(String[] args) {
        System.exit(GenericTest.runFromAnnotations(args, "Fibonacci.java", new Object() {
        }.getClass().getEnclosingClass()).ordinal());
    }

}
