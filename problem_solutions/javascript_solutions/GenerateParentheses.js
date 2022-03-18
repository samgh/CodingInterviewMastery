/**
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
 *   Execution: node GenerateParentheses.js
 */

/**
 * Solution #1: Generate every possible combination and filter results so
 * we end up with only the valid combinations
 *
 * @param{number} n
 * @return{number}
 */
var generateParenthesesBruteForce = function(n) {
    var results = [];
    generateParenthesesBruteForceInner(n, n, results, []);
    results = results.filter(valid);
    return results;
}

/**
 * Generate all combinations
 *
 * @param{number} open
 * @param{number} closed
 * @param{number[]} results
 * @param{string[]} curr
 */
var generateParenthesesBruteForceInner = function(open, closed, results, curr) {
    // If we have no more parens to use, we've found a string that uses all
    // of our parens
    if (open == 0 && closed == 0) {
        results.push(curr.join(""));
        return;
    }

    // If we have open parens available, try adding an open paren
    if (open > 0) {
        curr.push('(');
        generateParenthesesBruteForceInner(open-1, closed, results, curr);
        curr.pop();
    }

    // If we have closed parens available, try adding a closed paren
    if (closed > 0) {
        curr.push(')');
        generateParenthesesBruteForceInner(open, closed-1, results, curr);
        curr.pop();
    }
}

/**
 * Determine if a string of parentheses is valid
 *
 * @param{string} s
 * @return{boolean}
 */
var valid = function(s) {
    var count = 0;

    // Count the net number of open parens. If this goes negative or doesn't
    // end as 0, then the string is invalid
    for (var i = 0; i < s.length; i++) {
        if (s[i] == '(') count++;
        else count--;
        if (count < 0) return false;
    }

    return count == 0;
}

/**
 * Solution #2: Rather than generate all possible combinations, we can
 * generate only valid solutions on the initial pass
 *
 * @param{number} n
 * @return{number}
 */
var generateParentheses = function(n) {
    var results = [];
    generateParenthesesInner(n, n, results, []);
    return results;
}

/**
 * Generate all valid combinations
 *
 * @param{number} open
 * @param{number} closed
 * @param{number[]} results
 * @param{string[]} curr
 */
var generateParenthesesInner = function(open, closed, results, curr) {
    // If we have no more parens to use, we've found a string that uses all
    // of our parens
    if (open == 0 && closed == 0) {
        results.push(curr.join(""));
        return;
    }

    // If we have open parens available, try adding an open paren
    if (open > 0) {
        curr.push('(');
        generateParenthesesInner(open-1, closed, results, curr);
        curr.pop();
    }

    // Only add a closed paren if it's allowed. We know it's allowed if
    // there are fewer open parens available than closed parens
    if (closed > open) {
        curr.push(')');
        generateParenthesesInner(open, closed-1, results, curr);
        curr.pop();
    }
}

var tester = function() {
    var result =  ['((()))', '(()())', '(())()', '()(())', '()()()'];
    console.assert(generateParenthesesBruteForce(3).join("") == result.join(""));
    console.assert(generateParentheses(3).join("") == result.join(""));
}

tester();
