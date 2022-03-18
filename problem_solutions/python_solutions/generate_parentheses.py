"""
Title: Generate Parentheses
Leetcode Link: https://leetcode.com/problems/generate-parentheses/

Problem:
  Given n pairs of parentheses, write a function to generate all
  combinations of well-formed parentheses.  For example, given n = 3, a
  solution set is:
  [
    "((()))",
     "(()())",
     "(())()",
     "()(())",
     "()()()"
  ]

Execution: python generate_parentheses.py
"""

import unittest
from typing import List

"""
Solution #1: Generate every possible combination and filter results so
we end up with only the valid combinations
"""
def generate_parentheses_brute_force(n: int) -> List[str]:

    # Generate all combinations
    def generate_all(open: int, closed: int, results: List[str], curr: List[str]):
        # If we have no more parens to use, we've found a string that uses all
        # of our parens
        if open == 0 and closed == 0:
            results.append("".join(curr))
            return

        # If we have open parens available, try adding an open paren
        if open > 0:
            curr.append('(')
            generate_all(open-1, closed, results, curr)
            curr.pop()

        # If we have closed parens available, try adding a closed paren
        if closed > 0:
            curr.append(')')
            generate_all(open, closed-1, results, curr)
            curr.pop()

    # Determine if a string of parentheses is valid
    def valid(s: str):
        count = 0

        # Count the net number of open parens. If this goes negative or doesn't
        # end as 0, then the string is invalid
        for c in s:
            if c == '(':
                count = count + 1
            else:
                count = count - 1

            if count < 0:
                return False

        return count == 0

    results = []
    generate_all(n, n, results, [])
    return [result for result in results if valid(result)]

"""
Solution #2: Rather than generate all possible combinations, we can
generate only valid solutions on the initial pass
"""
def generate_parentheses(n: int) -> List[str]:

    # Generate all combinations
    def generate_valid(open: int, closed: int, results: List[str], curr: List[str]):
        # If we have no more parens to use, we've found a string that uses all
        # of our parens
        if open == 0 and closed == 0:
            results.append("".join(curr))
            return

        # If we have open parens available, try adding an open paren
        if open > 0:
            curr.append('(')
            generate_valid(open-1, closed, results, curr)
            curr.pop()

        # Only add a closed paren if it's allowed. We know it's allowed if
        # there are fewer open parens available than closed parens
        if closed > open:
            curr.append(')')
            generate_valid(open, closed-1, results, curr)
            curr.pop()

    results = []
    generate_valid(n, n, results, [])
    return results


class TestGenerateParenthesis(unittest.TestCase):
    """Unit test for generate_parenthesis."""

    def test_1(self):
        """Test for input shown below."""
        test_output = [
            "((()))",
            "(()())",
            "(())()",
            "()(())",
            "()()()"]
        self.assertEqual(generate_parentheses_brute_force(3), test_output)
        self.assertEqual(generate_parentheses(3), test_output)


if __name__ == '__main__':
    unittest.main()
