/**
 *   Title: ArraysAndStringsSolutions
 *
 *   This file contains the solutions for the Arrays and Strings exercises in
 *   the DS & Algos Primer. If you have not already attempted these exercises,
 *   we highly recommend you complete them before reviewing the solutions here.
 *
 *   Execution: node ArraysAndStringsSolutions.js

/**
 * Exercise 1.1: Write a function that takes an integer array and reverses the
 * values in place
 *
 * Time Complexity: O(arr.length)
 * Space Complexity: O(1)
 *
 * @param {number[]} arr
 */
var reverseArray = function(arr) {
    // We will iterate to the midpoint of the array. For each value, we can
    // get the index its supposed to swap with by computing arr.length-i-1
    for (var i = 0; i < arr.length/2; i++) {
        var temp = arr[i];
        arr[i] = arr[arr.length-i-1];
        arr[arr.length-i-1] = temp;
    }
}

/**
 * Exercise 1.2: Write a function that takes in a string and removes every
 * even-indexed character
 *
 * Time Complexity: O(s.length())
 * Space Complexity: O(s.length())
 *
 * @param{string} s
 * @return{string}
 */
var removeEven = function(s) {
    // It is more complexity-efficient for us to add all the characters to an
    // array and join them at the end
    var charArray = []

    // Increment by 2 each time to only visit odd indices
    for (var i = 0; i < s.length; i=i+2) {
        charArray.push(s[i]);
    }

    return charArray.join("");
}

/**
 * Exercises 1.3: Zig Zag Conversion
 * Full Problem Definition: https://leetcode.com/problems/zigzag-conversion/
 *
 * Time Complexity: O(s.length())
 * Space Complexity: O(s.length())
 *
 * @param{string} s
 * @param{number} numRows
 * @return{string}
 */
var zigZag = function(s, numRows) {
    // We'll compute each row and then merge them all together at the end
    var rows = []
    for (var i = 0; i < numRows; i++) rows.push([]);

    // We have 2 actions. First we iterate down over each row, then we
    // iterate back up. Do one then the other.
    var idx = 0;
    while (idx < s.length) {
        // Iterate from row 0 to numRows-1
        for (var i = 0; i < rows.length && idx < s.length; i++) {
            rows[i].push(s[idx++]);
        }

        // Iterate back up from numRows-2 to 1. Make sure we go from
        // numRows-2 to 1 and not numRows-1 to 0 because otherwise we'll
        // add 2 characters to row 0 and 2 characters to row numRows-1
        for (var i = rows.length-2; i >= 1 && idx < s.length; i--) {
            rows[i].push(s[idx++]);
        }
    }

    // Combine all the rows together
    var result = [];
    rows.forEach(function(r) {
        r.forEach(function(c) {
            result.push(c);
        });
    });

    return result.join("");
}

/**
 * Exercise 1.4: Given a 2D matrix, write a function to print the values
 * going back and forth across each row
 *
 * Time Complexity: O(arr.length * arr[0].length)
 * Space Complexity: O(1)
 *
 * @param {number[][]} arr
 */
var printBackAndForth = function(arr) {
    // Iterate 2 rows at a time and go across and back
    for (var i = 0; i < arr.length; i=i+2) {
        // Iterate across to the right
        for (var j = 0; j < arr[i].length; j++) {
            console.log(arr[i][j]);
        }

        // If iterating across to the right was the last row, end, otherwise
        // iterate back across to the right
        if (i+1 < arr.length) {
            for (var j = arr[i+1].length-1; j >= 0; j--) {
                console.log(arr[i+1][j]);
            }
        }
    }
}

/**
 * Exercise 1.5: Given a 2D matrix, write a function to print the values in the
 * matrix in a clockwise spiral from outside to inside
 *
 * Time Complexity: O(arr.length * arr[0].length)
 * Space Complexity: O(1)
 *
 * @param {number[][]} arr
 */
var printSpiral = function(arr) {
    // We need to keep track of the boundaries of the current layer of the
    // spiral that we are traversing
    var minRow = 0;
    var minCol = 0;
    var maxRow = arr.length-1;
    var maxCol = arr[0].length-1;

    // Once the mins and maxes converge, we are at the center of the spiral.
    // The spiral follows a fixed set of steps. We go left, down, right, up.
    // For each of these, we just interate to the bounds, so we express each
    // one explicitly.
    while (true) {
        // Go across the top
        for (var col = minCol; col <= maxCol; col++) {
            console.log(arr[minRow][col]);
        }
        minRow++;
        if (minRow >= maxRow) break;

        // Go down the right side
        for (var row = minRow ; row <= maxRow; row++) {
            console.log(arr[row][maxCol]);
        }
        maxCol--;
        if (minCol >= maxCol) break;

        // Go across the bottom
        for (var col = maxCol; col >= minCol; col--) {
            console.log(arr[maxRow][col]);
        }
        maxRow--;
        if (minRow >= maxRow) break;

        // Go up the left side
        for (var row = maxRow; row >= minRow; row--) {
            console.log(arr[row][minCol]);
        }
        minCol++;
        if (minCol >= maxCol) break;
    }
}

/**
 * Exercise 1.6: Given a 2D matrix, write a function to print the values in the
 * matrix in a zig-zag order
 *
 * Time Complexity: O(arr.length * arr[0].length)
 * Space Complexity: O(1)
 *
 * @param{number[][]} arr
 */
var printDiagonals = function(arr) {
    var row = 0;
    var col = 0;

    // Like the spiral, we have clearly defined directions we need to go. In
    // this case we either go up to the right or down to the left. We define
    // each of these explicitly and just go back and forth between doing one
    // and the other
    while (true) {
        // Go up to the right
        while (row > 0 && col < arr[0].length-1) {
            console.log(arr[row][col]);
            row--;
            col++;
        }
        // Without this we won't print the final value in the diagonal
        console.log(arr[row][col]);

        // Check whether we're at the bottom right corner
        if (row == arr.length-1 && col == arr[0].length-1) break;

        // We need to update our positiion differently depending on whether
        // we're still going along the top of the matrix or down the
        // righthand side
        else if (col+1 < arr[0].length) col++;
        else row++;

        // Go down to the left
        while (row < arr.length-1 && col > 0) {
            console.log(arr[row][col]);
            row++;
            col--;
        }
        // Without this we won't print the final value in the diagonal
        console.log(arr[row][col]);

        // Check whether we're at the bottom right corner
        if (row == arr.length-1 && col == arr[0].length-1) break;

        // Are we going along the lefthand side or the bottom?
        else if (row+1 < arr.length) row++;
        else col++;
    }
}

/**
 * Exercise 2.1: Given a string, print out all of the substrings
 *
 * Time Complexity: O(s.length()^2)
 * Space Complexity: O(1)
 *
 * @param{string} s
 */
var printSubstrings = function(s) {
    for (var i = 0; i < s.length; i++) {
        for (var j = i+1; j <= s.length; j++) {
            console.log(s.substring(i,j));
        }
    }
}

/**
 * Exercise 2.2: Write a function to find all duplicates in an array. The array
 * will contain exactly 1 duplicated value
 *
 * Time Complexity: O(arr.length^2)
 * Space Complexity: O(1)
 *
 * @param{number[]} arr
 * @return{number}
 */
var findDuplicates = function(arr) {
    // Use 2 pointers to compare each pair of values
    for (var i = 0; i < arr.length; i++) {
        for (var j = i+1; j < arr.length; j++) {
            if (arr[i] == arr[j]) return arr[i];
        }
    }
}

/**
 * Exercise 2.3: Given a sorted array, find every pair of values in the
 * array that sum up to a given target
 *
 * Time Complexity: O(arr.length)
 * Space Complexity: O(1)
 *
 * @param{number[]} arr
 * @param{number} target
 * @return{number[][]}
 */
var twoSum = function(arr, target) {
    var result = [];

    // We start our pointers at the beginning and move towards the center
    var i = 0;
    var j = arr.length-1;

    while (i < j) {
        var sum = arr[i] + arr[j];
        // If we found the target, we add it to the result. Then we either
        // increment i or decrement j. It doesn't matter which we do
        if (sum == target) {
            result.push([arr[i], arr[j]]);

            // We want to avoid including the same pair multiple times so we
            // skip the pointer ahead to the next unique value. Since our
            // array is sorted, we just keep incrementing until we see a
            // new value
            while (arr[i] == arr[i+1]) i++;
            i++;
        }

        // We can find a larger sum by incrementing i. This makes the
        // smaller value in our pair larger so the sum is larger
        if (sum < target) i++;

        // If it's too big, we do the opposite by decrementing j
        if (sum > target) j--;
    }

    return result;
}

/**
 * Exercise 3.1: Given two arrays, compare them to see if they are equal
 *
 * Time Complexity: O(arr1.length)
 * Space Complexity: O(1)
 *
 * @param{number[]} arr1
 * @param{number[]} arr2
 * @return{boolean}
 */
var arraysAreEqual = function(arr1, arr2) {
    // If they're not the same length they can't be equal
    if (arr1.length != arr2.length) return false;

    // Compare each value. If they're not equal then the arrays are unequal
    for (var i = 0; i < arr1.length; i++) {
        if (arr1[i] != arr2[i]) return false;
    }

    return true;
}

/**
 * Exercise 3.2: Given two strings, determine if one string is the reverse of
 * the other string
 *
 * Time Complexity: O(s1.length())
 * Space Complexity: O(1)
 *
 * @param{string} s1
 * @param{string} s2
 * @return{boolean}
 */
var stringsAreOpposite = function(s1, s2) {
    // If they're not the same length they can't be opposites
    if (s1.length != s2.length) return false;

    // Compare the opposing characters in each string. We could also just
    // reverse one of the strings and compare them, but that takes extra
    // space whereas this does not
    for (var i = 0; i < s1.length; i++) {
        if (s1[i] != s2[s2.length-i-1]) return false;
    }

    return true;
}

/**
 * Exercise 3.3: Given two strings, determine whether they are anagrams of each
 * other
 *
 * Time Complexity: O(s1.length())
 * Space Complexity: O(s1.length())
 *
 * @param{string} s1
 * @param{string} s2
 * @return{boolean}
 */
var areAnagrams = function(s1, s2) {
    // If they're not the same length they can't be anagrams
    if (s1.length != s2.length) return false;

    // Count the number of occurrences of each character in s1
    chars = new Map();
    [...s1].forEach(function(c) {
        var count = 1;
        if (chars.has(c)) {
            count = chars.get(c)+1;
        }
        chars.set(c, count);
    });

    // Subtract the chars in s2 from the count. We should end up with 0 of
    // each character left over
    [...s2].forEach(function(c) {
        if (!chars.has(c)) return false;
        var count = chars.get(c);
        if (count == 0) return false;
        chars.set(c, count-1);
    });

    return true;
}

/**
 * Exercise 4.1: Given an array, compute the sum of each length-k subarray
 *
 * Time Complexity: O(arr.length)
 * Space Complexity: O(1)
 *
 * @param{number[]} arr
 * @param{number} k
 * @return{number[]}
 */
var  subarraySums = function(arr, k) {
    var result = [];

    // Compute the sum of the initial length-k subarray
    var sum = 0;
    for (var i = 0; i < k; i++) sum += arr[i];
    result[0] = sum;

    // Use a sliding window to go through the remainder of the array without
    // recomputing the sum for every subarray
    var left = 0;
    var right = k-1;
    while (right < arr.length-1) {
        // The value at right+1 needs to be added to the sum and the value
        // at left needs to be subtracted
        sum += arr[++right];
        sum -= arr[left++];
        result.push(sum);
    }

    return result;
}

/**
 * Exercise 4.2: Given a string, find the longest substring of the string that
 * does not contain any repeated characters
 *
 * Time Complexity: O(s.length())
 * Space Complexity: O(1)
 *
 * @param{string} s
 * @return{number}
 */
var noRepeatedChars = function(s) {
    // Track the characters in our current substring
    var inSubstring = new Set();

    var maxSubstring = 0;
    var left = 0;
    var right = 0;

    // We expand right out as much as we can without getting duplicate
    // chars. If we end up with duplicates, we increment left to shrink the
    // substring until we no longer have duplicates
    while (right < s.length) {
        // We have a duplicate character, so increment left until the
        // substring no longer contains duplicates
        while (inSubstring.has(s[right])) {
            inSubstring.delete(s[left++]);
        }

        // We have a valid substring so is it the longest one?
        maxSubstring = Math.max(maxSubstring, right-left+1);

        // Try expanding the substring again
        inSubstring.add(s[right++]);
    }

    return maxSubstring;
}

/**
 * Exercise 4.3: Given two strings, s and p, find all occurrences of anagrams of
 * p in s. The output is the starting index of each anagram
 *
 * Time Complexity: O(s.length())
 * Space Complexity: O(1)
 *
 * @param{string} s
 * @param{string} p
 * @return{number[]}
 */
var findAllAnagrams = function(s, p) {
    var result = [];

    // Storing character counts. We can also use a HashMap like in the
    // solution for 3.3 but if we know that all of our characters are
    // English letters, this is easier for us to work with
    var chars = [];
    for (var i = 0; i < 256; i++) chars.push(0);
    [...p].forEach(function(c) {
        chars[c.charCodeAt(0)]++;
    })

    // Do our sliding window
    var left = 0;
    var right = 0;
    while (right < s.length) {
        // Add in the right character to our current window. We account for
        // this by removing it from the character count we have for p
        var rightChar = s[right++].charCodeAt(0);
        chars[rightChar]--;

        // If the value is negative, then we have too many of rightChar in
        // our substring so we need to make it smaller until we no longer
        // have too many of that character
        while (chars[rightChar] < 0) {
            chars[s[left].charCodeAt(0)]++;
            left++;
        }

        // If we have the exact right number of occurrences of the character
        // AND the substring is the right length, then this is a valid
        // substring
        if (chars[rightChar] == 0 && right-left == p.length) result.push(left);
    }

    return result;
}

/**
 * Exercise 4.4: Given two strings, s and p, find the smallest substring of s
 * that contains all the characters in p
 *
 * Time Complexity: O(s.length())
 * Space Complexity: O(1)
 *
 * @param{string} s
 * @param{string} p
 * @result{string}
 */
var smallestSubstring = function(s, p) {
    // Same as 4.3, we use an array to store character count
    var chars = [];
    for (var i = 0; i < 256; i++) chars.push(0);
    [...p].forEach(function(c) {
        chars[c.charCodeAt(0)]++;
    })

    var left = 0;
    var right = 0;

    // In addition to tracking left and right, we'll track the start and
    // length of the string, as well as the count of characters from p that
    // we have in our substring. The count allows us to quickly see whether
    // our substring includes all the characters in p or not
    var count = 0;
    var minLength = Number.MAX_VALUE;
    var minStart = 0;

    while (right < s.length) {
        // This is basically opposite of 4.3 where we WANT all the values to
        // get to 0 or negative because we want the string to be inclusive
        // of all the characters in p
        var rightChar = s[right++].charCodeAt(0);
        chars[rightChar]--;

        if (chars[rightChar] >= 0) {
            count++;
        }

        // If count == p.length we have a valid substring. In this case,
        // keep shrinking it as much as we can by incrementing left
        while (count == p.length) {
            if (right - left < minLength) {
                minLength = right-left;
                minStart = left;
            }

            // If we have extra of a character, we don't decrement the count
            // until we have fewer occurrences of that char than there are
            // in p
            if (++chars[s[left].charCodeAt(0)] > 0) count--;
            left++;
        }
    }

    // If we don't find a valid substring, return ""
    if (minLength > s.length) return "";

    return s.substring(minStart, minStart+minLength);
}

var tester = function() {
    var toReverse = [1,2,3,4,5];
    reverseArray(toReverse);
    console.log(toReverse);

    console.log(removeEven("abcdefg"));

    console.log(zigZag("PAYPALISHIRING", 3));

    var matrix = [[1,2,3,4,5],[6,7,8,9,10],[11,12,13,14,15],[16, 17,18,19,20]];
    printBackAndForth(matrix);
    printSpiral(matrix);
    printDiagonals(matrix);

    printSubstrings("abcd");

    console.log(findDuplicates([1,2,3,2,4]));

    console.log(twoSum([1,2,2,2,3,4,5,6,6,6,6], 8));

    console.log(arraysAreEqual([1,2,3], [1,2,3]));

    console.log(stringsAreOpposite("abcde", "edcba"));

    console.log(areAnagrams("ababc", "cbaab"));

    console.log(subarraySums([1,2,3,4,5], 3));

    console.log(noRepeatedChars("abcbabcd"));

    console.log(findAllAnagrams("cbaebabacd", "abc"));

    console.log(smallestSubstring("aabbccdd", "abc"));
}

tester();
