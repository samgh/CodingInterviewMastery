/**
 *   Title: First Missing Positive
 *   Leetcode Link: https://leetcode.com/problems/first-missing-positive/
 *
 *   Problem: Given an unsorted integer array, find the smallest missing
 *   positive integer.
 *
 *
 *   Input:
 *      int[] nums  => the input array
 *   Output:
 *      int         => the first missing positive value
 *
 *   Execution: node FirstMissingPositive.js
 */

/**
 * We will use the sign of the value at each index to indicate whether that
 * index value is in the array or not. This way we can track which values
 * are in our array without using extra space
 *
 * @param{number[]} nums
 * @return{number}
 */
var firstMissingPositive = function(nums) {
    // Any negative values cannot be our smallest positive integer and they
    // will mess us up, so set any negative values to MAX_VALUE
    for (var i = 0; i < nums.length; i++) {
        if (nums[i] <= 0) nums[i] = Number.MAX_VALUE;
    }

    // For each value, convert it to an index in our array. If that index is
    // in th earray and is positive, invert the value to indicate that that
    // value is in our array
    for (var i = 0; i < nums.length; i++) {
        var abs = Math.abs(nums[i]);

        // Make sure that we're in the range of the array and if so invert
        // the value
        if (abs <= nums.length) nums[abs-1] = -Math.abs(nums[abs-1]);
    }

    // Find the first non-negative index and that is our result
    for (var i = 0; i < nums.length; i++) {
        if (nums[i] > 0) return i+1;
    }

    // If values 1-N are in the array, our next value is N+1
    return nums.length+1;
}

// Sample test cases
var tester = function() {
    console.assert(firstMissingPositive([1, 2, 0]) == 3);

    console.assert(firstMissingPositive([3, 4, -1, 1]) == 2);

    console.assert(firstMissingPositive([7, 8, 9, 11, 12]) == 1);

    console.log("Passed all test cases");
}

tester();
