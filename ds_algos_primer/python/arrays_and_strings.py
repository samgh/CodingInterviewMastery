"""
Title: Arrays and Strings

This file contains the solutions for the Arrays and Strings exercises in
the DS & Algos Primer. If you have not already attempted these exercises,
we highly recommend you complete them before reviewing the solutions here.

Execution: python arrays_and_strings.py
"""

from typing import List

"""
Exercise 1.1: Write a function that takes an integer array and reverses
the values in place
"""
def reverse_array(arr: List[int]):
    # We will iterate to the midpoint of the array. For each value, we can
    # get the index its supposed to swap with by computing arr.length-i-1
    for i in range(len(arr)//2):
        temp = arr[i]
        arr[i] = arr[len(arr)-i-1]
        arr[len(arr)-i-1] = temp

"""
Exercise 1.2: Given a 2D matrix, write a function to print the values in
the matrix in a clockwise spiral from outside to inside
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
Exercise 1.3: Given a 2D matrix, write a function to print the values in the
matrix in a zig-zag order
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
Exercise 1.4: Write a function that takes in a string and removes every
even-indexed character
"""
def remove_even(s: str) -> str:
    # Build the string as a list first and then join everything together
    result = []

    # Increment by 2 each time to only visit odd indices
    for i in range(1, len(s), 2):
        result.append(s[i])

    return ''.join(result)

"""
Exercises 1.5: Zig Zag Conversion
Full Problem Definition: https://leetcode.com/problems/zigzag-conversion/
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
Exercise 2.1: Given a string, print out all of the substrings
"""
def print_substrings(s: str):
    for i in range(len(s)):
        for j in range(i+1, len(s)+1):
            print(s[i:j])

"""
Exercise 2.2: Write a function to find all duplicates in an array. The array
will contain exactly 1 duplicated value
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


if __name__ == '__main__':
    l = [1,2,3,4]
    reverse_array(l)
    print(l)

    matrix = [[1,2,3,4,5],[6,7,8,9,10],[11,12,13,14,15],[16, 17,18,19,20]]
    print_spiral(matrix)

    print_diagonals(matrix)

    print(remove_even("abcdef"))

    print(zig_zag("PAYPALISHIRING", 3))

    print_substrings("abcde")

    print(find_duplicates([1,2,3,4,3,5]))

    print(two_sum([1,2,2,2,3,4,5,6,6,6], 8))
