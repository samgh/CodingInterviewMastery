"""
Title: Basic Calculator
Leetcode Link: https://leetcode.com/problems/basic-calculator/

Problem: Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the
plus + or minus sign -, non-negative integers and empty spaces.

Execution: python calculate.py
"""
import unittest

"""
Calculate the sum for a specific range in our string. We do this my just
summing up all the values and keeping track of the sign. If we see a
parenthesis, we find the matching close parenthesis and calculate the
sum of that subexpression first
"""
def calculate(s: str, start: int = 0, end: int = None) -> int:
    if end is None:
        end = len(s)

    # Track the running sum and current sign
    sum = 0

    # This is either 1 or -1. When we see a '-' it gets set to -1 and when we
    # see a '+' it gets set to 1. That way when we're computing our sum we
    # always add everything and just multiply our value by currentSign to get
    # the correct
    current_sign = 1;

    # Iterate over our string and handle each different type of character
    i = start
    while i < end:
        curr = s[i]

        # If we find an open paren, first find the close paren and calculate
        # what is inside the parentheses first. Then add that to the sum and
        # move our index to after the parenthesized expression
        if curr == '(':
            close_idx = get_matching_paren(s, i)
            inner_sum = calculate(s, i+1, close_idx)
            sum = sum + current_sign * inner_sum
            i = close_idx

        # If we see a '+' or '-' update the sign
        if curr == '+':
            current_sign = 1
        if curr == '-':
            current_sign = -1

        # If we see a digit, get the full number and add it to our result
        if curr.isnumeric():
            curr_val = get_number(s, i)
            sum = sum + current_sign * curr_val[0]
            i = curr_val[1]

        i = i+1

    return sum

"""
Helper function that gets the number starting at a certain index. We start at
the index and traverse as long as we keep seeing digits
"""
def get_number(s, start):
    i = start
    while i < len(s):
        if not s[i].isnumeric():
            break
        i = i+1

    return (int(s[start:i]), i-1)

"""
Helper function that gets the matching close parenthesis for a paren at
a given starting index. We do this by keeping a running count of open and
close and waiting until they match up
"""
def get_matching_paren(s: str, start: int) -> int:
    count_open = 1
    for i in range(start+1, len(s)):
        if s[i] == '(':
            count_open = count_open+1
        if s[i] == ')':
            count_open = count_open-1
        if count_open == 0:
            return i

    return -1

class TestCalculate(unittest.TestCase):
    """Unit test for calculate."""

    def test_1(self):
        """Test for 1+1 = 2."""
        self.assertEqual(calculate("1 + 1"), 2)

    def test_2(self):
        """Test for 2-1+2 = 3."""
        self.assertEqual(calculate(" 2-1 + 2 "), 3)

    def test_3(self):
        """Test for (1+(4+5+2)-3)+(6+8) = 23"""
        self.assertEqual(calculate("(1+(4+5+2)-3)+(6+8)"), 23)

    # ADD TESTS HERE


if __name__ == '__main__':
    unittest.main()
