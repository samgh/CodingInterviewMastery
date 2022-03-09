"""
Title: Find Median of Sorted Arrays
Leetcode Link: https://leetcode.com/problems/median-of-two-sorted-arrays/

Problem: There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should
be O(log (m+n)).

You may assume nums1 and nums2 cannot be both empty.

Input:
    int[] nums1     => the first array
    int[] nums2     => the second array
Output:
    double          => the median of the two arrays

Execution: python find_median_sorted_arrays.py
"""

import unittest
from typing import List

"""
We will solve this problem using a modified binary search. The way this works is
as follows:
    1. We divide the smaller array into two halves
    2. Using the length of the halves, we know how many elements we need to
       include from the larger array for the current values to be the middle
    3. We check if this is actually the median, and if not, we continue to
       subdivide the smaller array

For a detailed explanation, see this video:
https://www.youtube.com/watch?v=LPFhl65R7ww

Time Complexity: O(log(nums1.length))
Space Complexity:  O(1)
"""
def find_median_sorted_arrays(nums1: List[int], nums2: List[int]) -> float:
    # Ensure that nums2 is always the longer of the two arrays
    if len(nums1) > len(nums2):
        nums1, nums2 = nums2, nums1

    # We will do binary search in our smaller array, so establish the
    # current bounds of our array
    minBound = 0
    maxBound = len(nums1)

    # Keep looping until we find a solution
    while minBound <= maxBound:
        # The midpoint of nums1 is halfway between our two bounds
        nums1_mid = (minBound + maxBound) // 2

        # The midpoint of nums2 is dependent on what we choose for nums1.
        # We want to choose the index so that nums1Mid + nums2Mid ==
        # (nums1.length + nums2.length)/2. Basically these two midpoints
        # represent a midpoint of the elements of both arrays
        nums2_mid = (len(nums1) + len(nums2) + 1) // 2 - nums1_mid

        if nums1_mid < maxBound and nums1[nums1_mid] < nums2[nums2_mid-1]:
            # If nums1[nums1Mid] < nums2[nums2Mid-1], that means that we
            # chose a midpoint in nums1 that was too small, so our correct
            # midpoint must be in the larger half of nums1
            minBound = nums1_mid+1

        elif nums1_mid > minBound and nums1[nums1_mid-1] > nums2[nums2_mid]:
            # If nums1[nums1Mid-1] > nums2[nums2Mid] then we picked a
            # nums1Mid that is too large, so we look at the smaller half
            maxBound = nums1_mid-1

        else:
            # We've foudn the proper midpoint, now we just need to figure
            # out what the actual median is.

            # First find the larger value of the two smaller halves
            max_left_side = 0
            if nums1_mid == 0:
                max_left_side = nums2[nums2_mid-1]
            elif nums2_mid == 0:
                max_left_side = nums1[nums1_mid-1]
            else:
                max_left_side = max(nums1[nums1_mid-1], nums2[nums2_mid-1])

            # If our total array is odd-length, that's all we have to do
            if (len(nums1) + len(nums2)) % 2 == 1:
                return max_left_side

            # Otherwise, find the smaller value of the two larger halves
            min_right_side = 0
            if nums1_mid == len(nums1):
                min_right_side = nums2[nums2_mid]
            elif nums2_mid == len(nums2):
                min_right_side = nums1[nums1_mid]
            else:
                min_right_side = min(nums1[nums1_mid], nums2[nums2_mid])

            return (max_left_side + min_right_side) / 2.0


class TestFindMedianSortedArrays(unittest.TestCase):
    """Unit test for find_median_sorted_arrays"""

    def test_1(self):
        nums1 = [1, 3]
        nums2 = [2]
        self.assertEqual(find_median_sorted_arrays(nums1, nums2), 2.0)
        print("Explanation: The median is 2.0.")

    def test_2(self):
        nums1 = [1, 2]
        nums2 = [3, 4]
        self.assertEqual(find_median_sorted_arrays(nums1, nums2), 2.5)
        print("Explanation: The median is (2 + 3)/2 = 2.5")


if __name__ == '__main__':
    unittest.main()
