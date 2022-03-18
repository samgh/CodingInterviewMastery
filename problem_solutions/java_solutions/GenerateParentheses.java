/*
 *   Title: Generate Parentheses
 *   Leetcode Link: https://leetcode.com/problems/generate-parentheses/
 *
 *   Problem:
 *      Given n pairs of parentheses, write a function to generate all
 *      combinations of well-formed parentheses.  For example, given n = 3, a
 *      solution set is:
 *      [
 *        "((()))",
 *         "(()())",
 *         "(())()",
 *         "()(())",
 *         "()()()"
 *      ]
 *
 *   Execution: javac GenerateParentheses.java && java -ea GenerateParentheses
 */

import java.util.*;

public class GenerateParentheses {

    /*
     * Solution #1: Generate every possible combination and filter results so
     * we end up with only the valid combinations
     */
    public static List<String> generateParenthesesBruteForce(int n) {
        List<String> results = new ArrayList();
        generateParenthesesBruteForce(n, n, results, new StringBuilder());
        results.removeIf(s -> !valid(s));
        return results;
    }

    // Generate all combinations
    private static void generateParenthesesBruteForce(int open, int closed, List<String> results, StringBuilder curr) {
        // If we have no more parens to use, we've found a string that uses all
        // of our parens
        if (open == 0 && closed == 0) {
            results.add(curr.toString());
            return;
        }

        // If we have open parens available, try adding an open paren
        if (open > 0) {
            curr.append('(');
            generateParenthesesBruteForce(open-1, closed, results, curr);
            curr.setLength(curr.length() - 1);
        }

        // If we have closed parens available, try adding a closed paren
        if (closed > 0) {
            curr.append(')');
            generateParenthesesBruteForce(open, closed-1, results, curr);
            curr.setLength(curr.length() - 1);
        }
    }

    // Determine if a string of parentheses is valid
    private static boolean valid(String s) {
        int count = 0;

        // Count the net number of open parens. If this goes negative or doesn't
        // end as 0, then the string is invalid
        for (char c: s.toCharArray()) {
            if (c == '(') count++;
            else count--;
            if (count < 0) return false;
        }

        return count == 0;
    }

    /*
     * Solution #2: Rather than generate all possible combinations, we can
     * generate only valid solutions on the initial pass
     */
    public static List<String> generateParentheses(int n) {
        List<String> results = new ArrayList();
        generateParentheses(n, n, results, new StringBuilder());
        return results;
    }

    // This is the same as brute force solution except for checking when we are
    // allowed to add a closed paren
    private static void generateParentheses(int open, int closed, List<String> results, StringBuilder curr) {
        // If we have no more parens to use, we've found a string that uses all
        // of our parens
        if (open == 0 && closed == 0) {
            results.add(curr.toString());
            return;
        }

        // If we have open parens available, try adding an open paren
        if (open > 0) {
            curr.append('(');
            generateParentheses(open-1, closed, results, curr);
            curr.setLength(curr.length() - 1);
        }

        // Only add a closed paren if it's allowed. We know it's allowed if
        // there are fewer open parens available than closed parens
        if (closed > open) {
            curr.append(')');
            generateParentheses(open, closed-1, results, curr);
            curr.setLength(curr.length() - 1);
        }
    }

    // Sample test cases
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("((()))");
        list.add("(()())");
        list.add("(())()");
        list.add("()(())");
        list.add("()()()");

        // Brute-force approach:
        Collections.sort(list);
        List<String> result1 = generateParenthesesBruteForce(3);
        Collections.sort(result1);
        assert list.equals(result1);

        // Backtracking approach:
        List<String> result2 = generateParentheses(3);
        Collections.sort(result2);
        assert list.equals(result2);

        System.out.println("Passed all test cases");
    }

}
