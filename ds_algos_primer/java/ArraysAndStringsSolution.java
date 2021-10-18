/*
 *   Title: ArraysAndStringsSolution
 *
 *   This file contains the solutions for the Arrays and Strings exercises in
 *   the DS & Algos Primer. If you have not already attempted these exercises,
 *   we highly recommend you complete them before reviewing the solutions here.
 *
 *   Execution: javac ArraysAndStringsSolution.java && java -ea ArraysAndStringsSolution
 */

import java.util.*;

public class ArraysAndStringsSolution {

    // Exercise 1.1: Write a function that takes an integer array and reverses
    // the values in place
    public static void reverseArray(int[] arr) {
        // We will iterate to the midpoint of the array. For each value, we can
        // get the index its supposed to swap with by computing arr.length-i-1
        for (int i = 0; i < arr.length/2; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length-i-1];
            arr[arr.length-i-1] = temp;
        }
    }

    // Exercise 1.2: Given a 2D matrix, write a function to print the values in
    // the matrix in a clockwise spiral from outside to inside
    public static void printSpiral(int[][] arr) {
        // We need to keep track of the boundaries of the current layer of the
        // spiral that we are traversing
        int minRow = 0;
        int minCol = 0;
        int maxRow = arr.length-1;
        int maxCol = arr[0].length-1;

        // Once the mins and maxes converge, we are at the center of the spiral.
        // The spiral follows a fixed set of steps. We go left, down, right, up.
        // For each of these, we just interate to the bounds, so we express each
        // one explicitly.
        while (minRow < maxRow && minCol < maxCol) {
            // Go across the top
            for (int col = minCol; col <= maxCol; col++) {
                System.out.println(arr[minRow][col]);
            }
            minRow++;

            // Go down the right side
            for (int row = minRow ; row <= maxRow; row++) {
                System.out.println(arr[row][maxCol]);
            }
            maxCol--;

            // Go across the bottom
            for (int col = maxCol; col >= minCol; col--) {
                System.out.println(arr[maxRow][col]);
            }
            maxRow--;

            // Go up the left side
            for (int row = maxRow; row >= minRow; row--) {
                System.out.println(arr[row][minCol]);
            }
            minCol++;
        }
    }

    // Exercise 1.3: Given a 2D matrix, write a function to print the values in
    // the matrix in a zig-zag order
    public static void printDiagonals(int[][] arr) {
        int row = 0;
        int col = 0;

        // Like the spiral, we have clearly defined directions we need to go. In
        // this case we either go up to the right or down to the left. We define
        // each of these explicitly and just go back and forth between doing one
        // and the other
        while (true) {
            // Go up to the right
            while (row > 0 && col < arr[0].length-1) {
                System.out.println(arr[row][col]);
                row--;
                col++;
            }
            // Without this we won't print the final value in the diagonal
            System.out.println(arr[row][col]);

            // Check whether we're at the bottom right corner
            if (row == arr.length-1 && col == arr[0].length-1) break;

            // We need to update our positiion differently depending on whether
            // we're still going along the top of the matrix or down the
            // righthand side
            else if (col+1 < arr[0].length) col++;
            else row++;

            // Go down to the left
            while (row < arr.length-1 && col > 0) {
                System.out.println(arr[row][col]);
                row++;
                col--;
            }
            // Without this we won't print the final value in the diagonal
            System.out.println(arr[row][col]);

            // Check whether we're at the bottom right corner
            if (row == arr.length-1 && col == arr[0].length-1) break;

            // Are we going along the lefthand side or the bottom?
            else if (row+1 < arr.length) row++;
            else col++;
        }
    }

    // Exercise 1.4: Write a function that takes in a string and removes every
    // even-indexed character
    public static String removeEven(String s) {
        // A StringBuilder is ideal here since strings are immutable. Could also
        // use a char[] and convert it to a string
        StringBuilder sb = new StringBuilder();

        // Increment by 2 each time to only visit odd indices
        for (int i = 1; i < s.length(); i=i+2) {
            sb.append(s.charAt(i));
        }

        return sb.toString();
    }

    // Exercises 1.5: Zig Zag Conversion
    // Full Problem Definition: https://leetcode.com/problems/zigzag-conversion/
    public static String zigZag(String s, int numRows) {
        // We'll compute each row and then merge them all together at the end
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < rows.length; i++) rows[i] = new StringBuilder();

        // We have 2 actions. First we iterate down over each row, then we
        // iterate back up. Do one then the other.
        int idx = 0;
        while (idx < s.length()) {
            // Iterate from row 0 to numRows-1
            for (int i = 0; i < rows.length && idx < s.length(); i++) {
                rows[i].append(s.charAt(idx++));
            }

            // Iterate back up from numRows-2 to 1. Make sure we go from
            // numRows-2 to 1 and not numRows-1 to 0 because otherwise we'll
            // add 2 characters to row 0 and 2 characters to row numRows-1
            for (int i = rows.length-2; i >= 1 && idx < s.length(); i--) {
                rows[i].append(s.charAt(idx++));
            }
        }

        // Combine all the strings together
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) result.append(row);

        return result.toString();
    }

    // Exercise 2.1: Given a string, print out all of the substrings
    public static void printSubstrings(String s) {
        for (int i = 0; i < s.length(); i++) {
            for (int j = i+1; j < s.length(); j++) {
                System.out.println(s.substring(i,j));
            }
        }
    }

    // Exercise 2.2: Write a function to find all duplicates in an array. The
    // array will contain exactly 1 duplicated value
    public static int findDuplicates(int[] arr) {
        // Use 2 pointers to compare each pair of values
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if (arr[i] == arr[j]) return arr[i];
            }
        }

        // We should never get here
        return -1;
    }

    // Exercise 2.3: Given a sorted array, find every pair of values in the
    // array that sum up to a given target
    public static int[][] twoSum(int[] arr, int target) {
        List<Integer[]> result = new ArrayList<>();

        // We start our pointers at the beginning and move towards the center
        int i = 0;
        int j = arr.length-1;

        while (i < j) {
            int sum = arr[i] + arr[j];
            // If we found the target, we add it to the result. Then we either
            // increment i or decrement j. It doesn't matter which we do
            if (sum == target) {
                result.add(new Integer[]{arr[i], arr[j]});

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

        // Convert our list into a 2D array
        int[][] resultArray = new int[result.size()][2];
        for (int k = 0; k < resultArray.length; k++) {
            Integer[] r = result.get(k);
            resultArray[k][0] = r[0];
            resultArray[k][1] = r[1];
        }

        return resultArray;
    }

    // Exercise 3.1: Given two arrays, compare them to see if they are equal
    public static boolean arraysAreEqual(int[] arr1, int[] arr2) {
        // If they're not the same length they can't be equal
        if (arr1.length != arr2.length) return false;

        // Compare each value. If they're not equal then the arrays are unequal
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) return false;
        }

        return true;
    }

    // Exercise 3.2: Given two strings, determine if one string is the reverse
    // of the other string
    public static boolean stringsAreOpposite(String s1, String s2) {
        // If they're not the same length they can't be opposites
        if (s1.length() != s2.length()) return false;

        // Compare the opposing characters in each string. We could also just
        // reverse one of the strings and compare them, but that takes extra
        // space whereas this does not
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(s1.length()-i-1)) return false;
        }

        return true;
    }

    // Exercise 3.3: Given two strings, determine whether they are anagrams of
    // each other
    public static boolean areAnagrams(String s1, String s2) {
        // If they're not the same length they can't be anagrams
        if (s1.length() != s2.length()) return false;

        // Count the number of occurrences of each character in s1
        Map<Character, Integer> chars = new HashMap<>();
        for (char c : s1.toCharArray()) {
            int count = 1;
            if (chars.containsKey(c)) {
                count = chars.get(c)+1;
            }
            chars.put(c, count);
        }

        // Subtract the chars in s2 from the count. We should end up with 0 of
        // each character left over
        for (char c : s2.toCharArray()) {
            if (!chars.containsKey(c)) return false;
            int count = chars.get(c);
            if (count == 0) return false;
            chars.put(c, count-1);
        }

        return true;
    }

    // Exercise 4.1: Given an array, compute the sum of each length-k subarray
    public static int[] subarraySums(int[] arr, int k) {
        // The size of our result will be arr.length - k + 1
        int[] result = new int[arr.length - k + 1];

        // Compute the sum of the initial length-k subarray
        int sum = 0;
        for (int i = 0; i < k; i++) sum += arr[i];
        result[0] = sum;

        // Use a sliding window to go through the remainder of the array without
        // recomputing the sum for every subarray
        int left = 0;
        int right = k-1;
        while (right < arr.length-1) {
            // The value at right+1 needs to be added to the sum and the value
            // at left needs to be subtracted
            sum += arr[++right];
            sum -= arr[left++];
            result[left] = sum;

        }

        return result;
    }

    // Exercise 4.2: Given a string, find the longest substring of the string
    // that does not contain any repeated characters
    public static int noRepeatedChars(String s) {
        // Track the characters in our current substring
        Set<Character> inSubstring = new HashSet<>();

        int maxSubstring = 0;
        int left = 0;
        int right = 0;

        // We expand right out as much as we can without getting duplicate
        // chars. If we end up with duplicates, we increment left to shrink the
        // substring until we no longer have duplicates
        while (right < s.length()) {
            // We have a duplicate character, so increment left until the
            // substring no longer contains duplicates
            while (inSubstring.contains(s.charAt(right))) {
                inSubstring.remove(s.charAt(left++));
            }

            // We have a valid substring so is it the longest one?
            maxSubstring = Math.max(maxSubstring, right-left+1);

            // Try expanding the substring again
            inSubstring.add(s.charAt(right++));
        }

        return maxSubstring;
    }

    // Exercise 4.3: Given two strings, s and p, find all occurrences of
    // anagrams of p in s. The output is the starting index of each anagram
    public static List<Integer> findAllAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();

        // Storing character counts. We can also use a HashMap like in the
        // solution for 3.3 but if we know that all of our characters are
        // English letters, this is easier for us to work with
        int[] chars = new int[256];
        for (char c : p.toCharArray()) chars[c]++;

        // Do our sliding window
        int left = 0;
        int right = 0;
        while (right < s.length()) {
            // Add in the right character to our current window. We account for
            // this by removing it from the character count we have for p
            char rightChar = s.charAt(right++);
            chars[rightChar]--;

            // If the value is negative, then we have too many of rightChar in
            // our substring so we need to make it smaller until we no longer
            // have too many of that character
            while (chars[rightChar] < 0) {
                chars[s.charAt(left)]++;
                left++;
            }

            // If we have the exact right number of occurrences of the character
            // AND the substring is the right length, then this is a valid
            // substring.
            if (chars[rightChar] == 0 && right-left == p.length()) result.add(left);
        }

        return result;
    }

    // Exercise 4.4: Given two strings, s and p, find the smallest substring of
    // s that contains all the characters in p
    public static String smallestSubstring(String s, String p) {
        // Same as 4.3, we use an array to store character count
        int[] chars = new int[256];
        for (char c : p.toCharArray()) chars[c]++;

        int left = 0;
        int right = 0;

        // In addition to tracking left and right, we'll track the start and
        // length of the string, as well as the count of characters from p that
        // we have in our substring. The count allows us to quickly see whether
        // our substring includes all the characters in p or not
        int count = 0;
        int minLength = Integer.MAX_VALUE;
        int minStart = 0;

        while (right < s.length()) {
            // This is basically opposite of 4.3 where we WANT all the values to
            // get to 0 or negative because we want the string to be inclusive
            // of all the characters in p
            char rightChar = s.charAt(right++);
            chars[rightChar]--;

            if (chars[rightChar] >= 0) {
                count++;
            }

            // If count == p.length we have a valid substring. In this case,
            // keep shrinking it as much as we can by incrementing left
            while (count == p.length()) {
                if (right - left < minLength) {
                    minLength = right-left;
                    minStart = left;
                }

                // If we have extra of a character, we don't decrement the count
                // until we have fewer occurrences of that char than there are
                // in p
                if (++chars[s.charAt(left)] > 0) count--;
                left++;
            }
        }

        // If we don't find a valid substring, return ""
        if (minLength > s.length()) return "";

        return s.substring(minStart, minStart+minLength);
    }


    // Sample test cases
    public static void main(String[] args) {
        int[] toReverse = new int[]{1,2,3,4,5};
        reverseArray(toReverse);
        System.out.println(Arrays.toString(toReverse));

        int[][] matrix = new int[4][5];
        int val = 1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = val++;
            }
        }
        printSpiral(matrix);

        printDiagonals(matrix);

        System.out.println(removeEven("iloveinterviewprep"));

        System.out.println(zigZag("PAYPALISHIRING", 3));

        printSubstrings("abcd");

        System.out.println(findDuplicates(new int[]{1,2,3,2,4}));

        int[][] twoSum = twoSum(new int[]{1,2,2,2,3,4,5,6,6,6}, 8);
        for (int[] a : twoSum) System.out.println(Arrays.toString(a));

        System.out.println(arraysAreEqual(new int[]{1,2,3}, new int[]{1,2,3}));

        System.out.println(stringsAreOpposite("abcde", "edcba"));

        System.out.println(areAnagrams("ababc", "cbaab"));

        System.out.println(Arrays.toString(subarraySums(new int[]{1,2,3,4,5}, 3)));

        System.out.println(noRepeatedChars("abcbabcd"));

        System.out.println(findAllAnagrams("cbaebabacd", "abc"));

        System.out.println(smallestSubstring("aabbccdd", "abc"));
    }
}
