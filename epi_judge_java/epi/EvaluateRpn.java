package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import java.util.function.ToIntBiFunction;

public class EvaluateRpn {
    @EpiTest(testDataFile = "evaluate_rpn.tsv")

    public static int eval(String expression) {
        Deque<Integer> stack = new ArrayDeque<>();
        final Map<String, ToIntBiFunction<Integer, Integer>> symbols = Map.of(
                "+", (y, x) -> x + y, "-", (y, x) -> x - y, "*", (y, x) -> x * y,
                "/", (y, x) -> x / y);
        for (String token : expression.split(",")) {
            if (symbols.get(token) != null) stack.push(symbols.get(token).applyAsInt(stack.pop(), stack.pop()));
            else stack.push(Integer.parseInt(token));
        }
        return stack.pop();
    }

//     alternative method
//    public static int eval(String expression) {
//        Deque<Integer> stack = new LinkedList<>();
//        String symbols = "+-*/";
//        String[] exp = expression.split(",");
//        for (String e : exp) {
//            if (e.length() == 1 && symbols.contains(e)) {
//                final int y = stack.pop();
//                final int x = stack.pop();
//                switch (e.charAt(0)) {
//                    case '+':
//                        stack.push(x + y);
//                        break;
//                    case '-':
//                        stack.push(x - y);
//                        break;
//                    case '*':
//                        stack.push(x * y);
//                        break;
//                    case '/':
//                        stack.push(x / y);
//                        break;
//                    default:
//                        throw new IllegalArgumentException("Malformed expression: " + e);
//                }
//            } else stack.push(Integer.parseInt(e));
//        }
//        return stack.pop();
//    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "EvaluateRpn.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}