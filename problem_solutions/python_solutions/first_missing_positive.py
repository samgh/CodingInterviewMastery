"""
Title: First Missing Positive
Leetcode Link: https://leetcode.com/problems/first-missing-positive/

Problem: Given an unsorted integer array, find the smallest missing positive
integer.

Input:
    int[] nums  => the input array
Output:
    int         => the first missing positive value

Execution: python first_missing_positive.py
"""
import unittest
from typing import List

"""
We will use the sign of the value at each index to indicate whether that
index value is in the array or not. This way we can track which values
are in our array without using extra space
"""
def first_missing_positive(nums: List[int]):
    # Any negative values cannot be our smallest positive integer and they
    # will mess us up, so set any negative values to inf
    for i in range(len(nums)):
        if nums[i] <= 0:
            nums[i] = float('inf')

    # For each value, convert it to an index in our array. If that index is
    # in th earray and is positive, invert the value to indicate that that
    # value is in our array
    for i in range(len(nums)):
        absolute_value = abs(nums[i])

        # Make sure that we're in the range of the array and if so invert
        # the value
        if absolute_value <= len(nums):
            nums[absolute_value-1] = -abs(nums[absolute_value-1])

    # Find the first non-negative index and that is our result
    for i in range(len(nums)):
        if nums[i] > 0:
            return i+1

    # If values 1-N are in the array, our next value is N+1
    return len(nums)+1


class TestFirstMissingPositive(unittest.TestCase):
    """Unit test for first_missing_positive."""

    def test_1(self):
        self.assertEqual(first_missing_positive([1, 2, 0]), 3)

    def test_2(self):
        self.assertEqual(first_missing_positive([3, 4, -1, 1]), 2)

    def test_3(self):
        self.assertEqual(first_missing_positive([7, 8, 9, 11, 12]), 1)


if __name__ == '__main__':
    unittest.main()
