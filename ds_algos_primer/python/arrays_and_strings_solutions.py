"""
Title: Arrays and Strings Solutions

This file contains the solutions for the Arrays and Strings exercises in
the DS & Algos Primer. If you have not already attempted these exercises,
we highly recommend you complete them before reviewing the solutions here.

Execution: python arrays_and_strings_solutions.py


*** IMPORTANT NOTE ***
Python provides a lot of inbuilt functions to accomplish certain tasks. If you
are aware of these, that's great.

HOWEVER, the goal of these exercises is to understand these data structures.
Therefore, you are discouraged from writing one- to two-line functions. Instead
you will learn a lot more by implementing these things manually.

In your interview, you may want to use these inbuilt functions, but while
learning, you will learn more by doing things the hard way.
"""

from collections import Counter
from typing import List

"""
Exercise 1.1: Write a function that takes an integer array and reverses
the values in place

Time Complexity: O(len(arr))
Space Complexity: O(1)
"""
def reverse_array(arr: List[int]):
    # We will iterate to the midpoint of the array. For each value, we can
    # get the index its supposed to swap with by computing arr.length-i-1
    for i in range(len(arr)//2):
        temp = arr[i]
        arr[i] = arr[len(arr)-i-1]
        arr[len(arr)-i-1] = temp

"""
Exercise 1.2: Write a function that takes in a string and removes every
even-indexed character

Time Complexity: O(len(s))
Space Complexity: O(len(s))
"""
def remove_even(s: str) -> str:
    # Build the string as a list first and then join everything together
    result = []

    # Increment by 2 each time to only visit odd indices
    for i in range(1, len(s), 2):
        result.append(s[i])

    return ''.join(result)

"""
Exercises 1.3: Zig Zag Conversion
Full Problem Definition: https://leetcode.com/problems/zigzag-conversion/

Time Complexity: O(len(s))
Space Complexity: O(len(s))
"""
def zig_zag(s: str, num_rows: int) -> str:
    # Compute each row and then merge them at the end
    rows = [ [] for _ in range(num_rows)]

    # We have 2 actions. First we iterate down over each row, then we iterate
    # back up. Do one then the other
    idx = 0
    while idx < len(s):
        # Iterate from row 0 to num_rows-1
        i = 0
        while i < len(rows) and idx < len(s):
            rows[i].append(s[idx])
            idx = idx+1
            i = i+1

        # Iterate back up from numRows-2 to 1. Make sure we go from numRows-2 to
        # 1 and not numRows-1 to 0 because otherwise we'll add 2 characters to
        # row 0 and 2 characters to row numRows-1
        i = len(rows)-2
        while i >= 1 and idx < len(s):
            rows[i].append(s[idx])
            idx = idx+1
            i = i-1

    # Combine everything together
    result = []
    for row in rows:
        result.append(''.join(row))

    return ''.join(result)

"""
Exercise 1.4: Given a 2D matrix, write a function to print the values
going back and forth across each row

Time Complexity: O(arr.length * arr[0].length)
Space Complexity: O(1)
"""
def print_back_and_forth(arr: List[List[int]]):
    # Iterate 2 rows at a time and go across and back
    for i in range(0, len(arr), 2):
        # Iterate across to the right
        for j in range(len(arr[i])):
            print(arr[i][j])

        # If iterating across to the right was the last row, end, otherwise
        # iterate back across to the right
        if i+1 < len(arr):
            for j in range(len(arr[i+1])-1, -1, -1):
                print(arr[i+1][j])


"""
Exercise 1.5: Given a 2D matrix, write a function to print the values in
the matrix in a clockwise spiral from outside to inside

Time Complexity: O(len(arr) * len(arr[0]))
Space Complexity: O(1)
"""
def print_spiral(arr: List[List[int]]):
    # We need to keep track of the boundaries of the current layer of the
    # spiral that we are traversing
    min_row = 0
    min_col = 0
    max_row = len(arr)-1
    max_col = len(arr[0])-1

    # Once the mins and maxes converge, we are at the center of the spiral.
    # The spiral follows a fixed set of steps. We go left, down, right, up.
    # For each of these, we just interate to the bounds, so we express each
    # one explicitly.
    while min_row < max_row and min_col < max_col:
        # Go across the top
        for col in range(min_col, max_col+1):
            print(arr[min_row][col])
        min_row = min_row+1

        # Go down the right side
        for row in range(min_row, max_row+1):
            print(arr[row][max_col])
        max_col = max_col-1

        # Go across the bottom
        for col in range(max_col, min_col-1, -1):
            print(arr[max_row][col])
        max_row = max_row-1

        # Go up the left side
        for row in range(max_row, min_row-1, -1):
            print(arr[row][min_col])
        min_col = min_col+1

"""
Exercise 1.6: Given a 2D matrix, write a function to print the values in the
matrix in a zig-zag order

Time Complexity: O(len(arr) * len(arr[0]))
Space Complexity: O(1)
"""
def print_diagonals(arr: List[List[int]]):
    row = 0
    col = 0

    # Like the spiral, we have clearly defined directions we need to go. In
    # this case we either go up to the right or down to the left. We define
    # each of these explicitly and just go back and forth between doing one
    # and the other
    while True:
        # Go up to the right
        while row > 0 and col < len(arr[0])-1:
            print(arr[row][col])
            row = row-1
            col = col+1

        # Without this we won't print the final value in the diagonal
        print(arr[row][col])

        # Check whether we're at the botom right corner
        if row == len(arr)-1 and col == len(arr[0])-1:
            break

        # We need to update our positiion differently depending on whether
        # we're still going along the top of the matrix or down the
        # righthand side
        elif col+1 < len(arr[0]):
            col = col+1
        else:
            row = row+1

        # Go down to the left
        while row < len(arr)-1 and col > 0:
            print(arr[row][col])
            row = row+1
            col = col-1

        # Without this we won't print the final value in the diagonal
        print(arr[row][col])

        # Check whether we're at the botom right corner
        if row == len(arr)-1 and col == len(arr[0])-1:
            break

        # Are we going along the lefthand side or the bottom?
        elif row+1 < len(arr):
            row = row+1
        else:
            col = col+1

"""
Exercise 2.1: Given a string, print out all of the substrings

Time Complexity: O(len(s)^2)
Space Complexity: O(1)
"""
def print_substrings(s: str):
    for i in range(len(s)):
        for j in range(i+1, len(s)+1):
            print(s[i:j])

"""
Exercise 2.2: Write a function to find all duplicates in an array. The array
will contain exactly 1 duplicated value

Time Complexity: O(len(arr)^2)
Space Complexity: O(1)
"""
def find_duplicates(arr: List[int]) -> int:
    # Use 2 pointers to compare each pair of values
    for i in range(len(arr)):
        for j in range(i+1, len(arr)):
            if arr[i] == arr[j]:
                return arr[i]

"""
Exercise 2.3: Given a sorted array, find every pair of values in the
array that sum up to a given target

Time Complexity: O(len(arr))
Space Complexity: O(1)
"""
def two_sum(arr: List[int], target: int) -> List[List[int]]:
    result = []

    # We start our pointers at the beginning and move towards the center
    i = 0
    j = len(arr)-1

    while i < j:
        sum = arr[i] + arr[j]
        # If we found the target, we add it to the result. Then we either
        # increment i or decrement j. It doesn't matter which we do
        if sum == target:
            result.append([arr[i],arr[j]])

            # We want to avoid including the same pair multiple times so we
            # skip the pointer ahead to the next unique value. Since our
            # array is sorted, we just keep incrementing until we see a
            # new value
            while arr[i] == arr[i+1]:
                i = i+1
            i = i+1

        # We can find a larger sum by incrementing i. This makes the smaller
        # value in our pair larger so the sum is larger
        if sum < target:
            i = i+1

        # If it's too big, we do the opposite by decrementing j
        if sum > target:
            j = j-1

    return result

"""
Exercise 3.1: Given two arrays, compare them to see if they are equal

Time Complexity: O(len(arr1))
Space Complexity: O(1)
"""
def arrays_are_equal(arr1: List[int], arr2: List[int]) -> bool:
    # If they're not the same length they can't be equal
    if len(arr1) != len(arr2):
        return False

    # Compare each value. If they're not equal then the arrays are unequal
    for i in range(len(arr1)):
        if arr1[i] != arr2[i]:
            return False

    return True

"""
Exercise 3.2: Given two strings, determine if one string is the reverse of the
other string

Time Complexity: O(len(s1))
Space Complexity: O(1)
"""
def strings_are_opposite(s1: str, s2: str) -> bool:
    # If they're not the same length they can't be opposites
    if len(s1) != len(s2):
        return False

    # Compare the opposing characters in each string. We could also just
    # reverse one of the strings and compare them, but that takes extra
    # space whereas this does not
    for i in range(len(s1)):
        if s1[i] != s2[len(s2)-i-1]:
            return False

    return True

"""
Exercise 3.3: Given two strings, determine whether they are anagrams of
each other

Time Complexity: O(len(s1))
Space Complexity: O(len(s1))
"""
def are_anagrams(s1: str, s2: str) -> bool:
    # If they're not the same length they can't be anagrams
    if len(s1) != len(s2):
        return False

    # Count the number of occurrences of each character in s1
    chars = {}
    for c in s1:
        chars[c] = chars.get(c, 0) + 1

    # Subtract the chars in s2 from the count. We should end up with 0 of
    # each character left over
    for c in s2:
        # s1 doesn't contain c at all
        if c not in chars:
            return False

        # s1 contains fewer occurrences of c than s2
        chars[c] = chars[c]-1
        if chars[c] < 0:
            return False

    return True

"""
Exercise 4.1: Given an array, compute the sum of each length-k subarray

Time Complexity: O(len(arr))
Space Complexity: O(1)
"""
def subarray_sums(arr: List[int], k: int) -> List[int]:
    result = []

    # Compute the sum of the initial length-k subarray
    sum = 0
    for i in range(k):
        sum = sum + arr[i]

    result.append(sum)

    # Use a sliding window to go through the remainder of the array without
    # recomputing the sum for every subarray
    left = 0
    right = k-1
    while right < len(arr)-1:
        # The value at right+1 needs to be added to the sum and the value
        # at left needs to be subtracted
        right = right+1
        sum = sum + arr[right]
        sum = sum - arr[left]
        left = left + 1

        result.append(sum)

    return result

"""
Exercise 4.2: Given a string, find the longest substring of the string that does
not contain any repeated characters

Time Complexity: O(len(s))
Space Complexity: O(1)
"""
def no_repeated_chars(s: str) -> int:
    # Track the characters in our current substring
    in_substring = set()

    max_substring = 0
    left = 0
    right = 0

    # We expand right out as much as we can without getting duplicate chars. If
    # we end up with duplicates, we increment left to shrink the substring until
    # we no longer have duplicates
    while right < len(s):
        # We have a duplicate character, so increment left until the substring
        # no longer contains duplicates
        while s[right] in in_substring:
            in_substring.remove(s[left])
            left = left + 1

        # We have a valid substring so is it the longest one?
        max_substring = max(max_substring, right-left+1)

        # Try expanding the substring again
        in_substring.add(s[right])

        right = right+1

    return max_substring

"""
Exercise 4.3: Given two strings, s and p, find all occurrences of anagrams of p
in s. The output is the starting index of each anagram

Time Complexity: O(len(s))
Space Complexity: O(1)
"""
def find_all_anagrams(s: str, p: str) -> List[int]:
    result = []

    # This is another option for computing character counts instead of a dict
    # since we know they're lowercase English chars. This is a little easier
    # given the approach below than using a dict
    chars = [0]*256
    for c in p:
        chars[ord(c)] = chars[ord(c)] + 1

    # Do our sliding window
    left = 0
    right = 0
    while right < len(s):
        # Add in the right character to our current window. We account for this
        # by removing it from the character count we have for p
        right_char_ord = ord(s[right])
        right = right + 1
        chars[right_char_ord] = chars[right_char_ord] - 1

        # If the value is negative, then we have too many of rightChar in our
        # substring so we need to make it smaller until we no longer have too
        # many of that character
        while chars[right_char_ord] < 0:
            chars[ord(s[left])] = chars[ord(s[left])] + 1
            left = left + 1

        # If we have the exact right number of occurrences of the character AND
        # the substring is the right length, then this is a valid substring
        if chars[right_char_ord] == 0 and right-left == len(p):
            result.append(left)

    return result

"""
Exercise 4.4: Given two strings, s and p, find the smallest substring of s that
contains all the characters in p

Time Complexity: O(len(s))
Space Complexity: O(1)
"""
def smallest_substring(s: str, p: str) -> str:
    # Same as 4.3, we use an array to store character count
    chars = [0]*256
    for c in p:
        chars[ord(c)] = chars[ord(c)] + 1

    left = 0
    right = 0

    # In addition to tracking left and right, we'll track the start and length
    # of the string, as well as the count of characters from p that we have in
    # our substring. The count allows us to quickly see whether our substring
    # includes all the characters in p or not
    count = 0
    min_length = float('inf')
    min_start = 0

    while right < len(s):
        # This is basically opposite of 4.3 where we WANT all the values to get
        # to 0 or negative because we want the string to be inclusive of all the
        # characters in p
        right_char_ord = ord(s[right])
        right = right + 1
        chars[right_char_ord] = chars[right_char_ord] - 1

        if chars[right_char_ord] >= 0:
            count = count + 1

        # If count == p.length we have a valid substring. In this case, keep
        # shrinking it as much as we can by incrementing left
        while count == len(p):
            if right - left < min_length:
                min_length = right - left
                min_start = left

            # If we have extra of a character, we don't decrement the count
            # until we have fewer occurrences of that char than there are in p
            left_char_ord = ord(s[left])
            chars[left_char_ord] = chars[left_char_ord] + 1
            if chars[left_char_ord] > 0:
                count = count + 1

            left = left + 1

    # If we don't find a valid substring, return ""
    if (min_length > len(s)):
        return ""

    return s[min_start : min_start + min_length]



# Sample test cases
if __name__ == '__main__':
    l = [1,2,3,4]
    reverse_array(l)
    print(l)

    print(remove_even("abcdef"))

    print(zig_zag("PAYPALISHIRING", 3))

    matrix = [[1,2,3,4,5],[6,7,8,9,10],[11,12,13,14,15],[16, 17,18,19,20]]
    print_back_and_forth(matrix)

    print_spiral(matrix)

    print_diagonals(matrix)

    print_substrings("abcde")

    print(find_duplicates([1,2,3,4,3,5]))

    print(two_sum([1,2,2,2,3,4,5,6,6,6], 8))

    print(arrays_are_equal([1,2,3,4], [1,2,3,4]))

    print(strings_are_opposite("abcd", "dcba"))

    print(are_anagrams("abcd", "cdab"))

    print(subarray_sums([1,2,3,4,5], 3))

    print(no_repeated_chars("abcdbea"))

    print(find_all_anagrams("cbaebabacd", "abc"))

    print(smallest_substring("aabbccdd", "abc"))

    print(two_sum([1,3,3,3,5], 6))
