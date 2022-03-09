/*
 *   Title: Find Median of Sorted Arrays
 *   Leetcode Link: https://leetcode.com/problems/median-of-two-sorted-arrays/
 *
 *   Problem: There are two sorted arrays nums1 and nums2 of size m and n
 *   respectively.
 *
 *   Find the median of the two sorted arrays. The overall run time complexity
 *   should be O(log (m+n)).
 *
 *   You may assume nums1 and nums2 cannot be both empty.
 *
 *   Input:
 *      int[] nums1     => the first array
 *      int[] nums2     => the second array
 *   Output:
 *      double          => the median of the two arrays
 *
 *   Execution: javac FindMedianSortedArrays.java && java -ea FindMedianSortedArrays
 */

public class FindMedianSortedArrays {

    /*
     * We will solve this problem using a modified binary search. The way this
     * works is as follows:
     *  1. We divide the smaller array into two halves
     *  2. Using the length of the halves, we know how many elements we need to
     *     include from the larger array for the current values to be the middle
     *  3. We check if this is actually the median, and if not, we continue to
     *     subdivide the smaller array
     *
     * For a detailed explanation, see this video:
     * https://www.youtube.com/watch?v=LPFhl65R7ww
     *
     * Time Complexity: O(log(nums1.length))
     * Space Complexity:  O(1)
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Ensure that nums2 is always the longer of the two arrays
        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }

        // We will do binary search in our smaller array, so establish the
        // current bounds of our array
        int min = 0;
        int max = nums1.length;

        // Keep looping until we find a solution
        while (min <= max) {
            // The midpoint of nums1 is halfway between our two bounds
            int nums1Mid = (min + max) / 2;

            // The midpoint of nums2 is dependent on what we choose for nums1.
            // We want to choose the index so that nums1Mid + nums2Mid ==
            // (nums1.length + nums2.length)/2. Basically these two midpoints
            // represent a midpoint of the elements of both arrays
            int nums2Mid = (nums1.length + nums2.length + 1) / 2 - nums1Mid;

            if (nums1Mid < max && nums1[nums1Mid] < nums2[nums2Mid-1]) {
                // If nums1[nums1Mid] < nums2[nums2Mid-1], that means that we
                // chose a midpoint in nums1 that was too small, so our correct
                // midpoint must be in the larger half of nums1
                min = nums1Mid+1;
            } else if (nums1Mid > min && nums1[nums1Mid-1] > nums2[nums2Mid]) {
                // If nums1[nums1Mid-1] > nums2[nums2Mid] then we picked a
                // nums1Mid that is too large, so we look at the smaller half
                max = nums1Mid-1;
            } else {
                // We've foudn the proper midpoint, now we just need to figure
                // out what the actual median is.

                // First find the larger value of the two smaller halves
                int maxLeftSide = 0;
                if (nums1Mid == 0) {
                    maxLeftSide = nums2[nums2Mid-1];
                } else if (nums2Mid == 0) {
                    maxLeftSide = nums1[nums1Mid-1];
                } else {
                    maxLeftSide = Math.max(nums1[nums1Mid-1], nums2[nums2Mid-1]);
                }

                // If our total array is odd-length, that's all we have to do
                if ((nums1.length + nums2.length) % 2 == 1) return maxLeftSide;

                // Otherwise, find the smaller value of the two larger halves
                int minRightSide = 0;
                if (nums1Mid == nums1.length) {
                    minRightSide = nums2[nums2Mid];
                } else if (nums2Mid == nums2.length) {
                    minRightSide = nums1[nums1Mid];
                } else {
                    minRightSide = Math.min(nums1[nums1Mid], nums2[nums2Mid]);
                }

                return (maxLeftSide + minRightSide) / 2.0;
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        assert findMedianSortedArrays(new int[]{1,3}, new int[]{2}) == 2.0;
        assert findMedianSortedArrays(new int[]{1,2}, new int[]{3,4}) == 2.5;
        assert findMedianSortedArrays(new int[]{1,3}, new int[]{2,7}) == 2.5;

        System.out.println("Passed all test cases");
    }

}
