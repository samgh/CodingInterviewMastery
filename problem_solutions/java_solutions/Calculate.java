/*
 *   Title: Basic Calculator
 *   Leetcode Link: https://leetcode.com/problems/basic-calculator/
 *
 *   Problem: Implement a basic calculator to evaluate a simple expression
 *   string.
 *
 *   The expression string may contain open ( and closing parentheses ), the
 *   plus + or minus sign -, non-negative integers and empty spaces.
 *
 *   Execution: javac Calculate.java && java -ea Calculate
 */

import java.util.*;

public class Calculate {

    /*
     * We will override this function to compute the sum of a specific range of
     * our string
     */
    public static int calculate(String s) {
        return calculate(s, 0, s.length());
    }

    /*
     * Calculate the sum for a specific range in our string. We do this my just
     * summing up all the values and keeping track of the sign. If we see a
     * parenthesis, we find the matching close parenthesis and calculate the
     * sum of that subexpression first
     */
    private static int calculate(String s, int start, int end) {
        // Track the running sum and current sign
        int sum = 0;

        // This is either 1 or -1. When we see a '-' it gets set to -1 and when
        // we see a '+' it gets set to 1. That way when we're computing our sum
        // we always add everything and just multiply our value by currentSign
        // to get the correct operation
        int currentSign = 1;

        // Iterate over our string and handle each different type of character
        for (int i = start; i < end; i++) {
            char curr = s.charAt(i);
            if (curr == ' ') continue;

            // If we find an open paren, first find the close paren and
            // calculate what is inside the parentheses first. Then add that to
            // the sum and move our index to after the parenthesized expression
            if (curr == '(') {
                int closeIdx = getMatchingParen(s, i);
                int innerSum = calculate(s, i+1, closeIdx);
                sum += currentSign * innerSum;
                i = closeIdx;
            }

            // If we see a '+' or '-' update the sign
            if (curr == '+') currentSign = 1;
            if (curr == '-') currentSign = -1;

            // If we see a digit, get the full number and add it to our result
            if (Character.isDigit(curr)) {
                int currVal = getNumber(s, i);
                sum += currentSign * currVal;

                // We need to skip i to the end of the number so we need to know
                // how many digits the number is. We can easily do this with log10
                if (currVal != 0) i+= Math.floor(Math.log10(currVal));
            }
        }
        return sum;
    }

    /*
     * Helper function that gets the number starting at a certain index. We
     * start at the index and traverse as long as we keep seeing digits
     */
    private static int getNumber(String s, int start) {
        int i = start;
        for (; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) break;
        }

        return Integer.parseInt(s.substring(start, i));
    }

    /*
     * Helper function that gets the matching close parenthesis for a paren at
     * a given starting index. We do this by keeping a running count of open and
     * close and waiting until they match up
     */
    private static int getMatchingParen(String s, int start) {
        int countOpen = 1;
        for (int i = start+1; i < s.length(); i++) {
            if (s.charAt(i) == '(') countOpen++;
            if (s.charAt(i) == ')') countOpen--;
            if (countOpen == 0) return i;
        }
        return -1;
    }

    // Sample test cases
    public static void main(String[] args) {
        assert calculate("0") == 0;
        assert calculate("1 + 1") == 2;
        assert calculate(" 2-1 + 2 ") == 3;
        assert calculate("(1+(4+5+2)-3)+(6+8)") == 23;

        // ADD YOUR TEST CASES HERE

        System.out.println("Passed all test cases");
    }
}
