"""
Title: Heap Solutions

This file contains the template for the Heap exercisess in the DS & Algos
Primer. If you have not already attempted these exercises, we highly
recommend you complete them before reviewing the solutions here.
"""

from queue import PriorityQueue
from typing import List

"""
Exercise 1.1: Implement a min heap
"""
class MinHeap:

    """
    Constructor
    """
    def __init__(self):
        # INSERT YOUR SOLUTION HERE

    """
    Insert an item into the heap

    Time Complexity:
    Space Complexity:
    """
    def insert(self, x: int):
        # INSERT YOUR SOLUTION HERE

    """
    Get the smallest value in the heap

    Time Complexity:
    Space Complexity:
    """
    def peek(self) -> int:
        # INSERT YOUR SOLUTION HERE

    """
    Remove and return the smallest value in the heap

    Time Complexity:
    Space Complexity:
    """
    def pop(self) -> int:
        # INSERT YOUR SOLUTION HERE

    """
    Get the size of the heap

    Time Complexity:
    Space Complexity:
    """
    def size(self) -> int:
        # INSERT YOUR SOLUTION HERE

    """
    Convert the heap data into a string

    Time Complexity:
    Space Complexity:
    """
    def __str__(self):
        # INSERT YOUR SOLUTION HERE

    """
    The following are some optional helper methods. These are not required
    but may make your life easier
    """

    """
    Get the index of the parent node of a given index

    Time Complexity:
    Space Complexity:
    """
    def _parent(self, i: int) -> int:
        # INSERT YOUR SOLUTION HERE

    """
    Get the index of the left child of a given index

    Time Complexity:
    Space Complexity:
    """
    def _left_child(self, i: int) -> int:
       # INSERT YOUR SOLUTION HERE

    """
    Swap the values at 2 indices in our heap

    Time Complexity:
    Space Complexity:
    """
    def _swap(self, i: int, j: int):
        # INSERT YOUR SOLUTION HERE

    """
    Starting at index i, bubble up the value until it is at the correct position
    in the heap

    Time Complexity:
    Space Complexity:
    """
    def _bubbleUp(self, i: int):
        # INSERT YOUR SOLUTION HERE

    """
    Starting at index i, bubble down the value until it is at the correct
    position in the heap

    Time Complexity:
    Space Complexity:
    """
    def _bubbleDown(self, i: int):
        # INSERT YOUR SOLUTION HERE

"""
Exercise 1.2: Given an array of integers, determine whether the array represents
a valid heap

Time Complexity:
Space Complexity:
"""
def is_valid(heap: List[int]) -> bool:
    # INSERT YOUR SOLUTION HERE

"""
Exercise 2.1: Given a list of integers, use a heap to find the largest value in
the list.

Use the Python PriorityQueue type

Time Complexity:
Space Complexity:
"""
def find_max(arr: List[int]) -> int:
    # INSERT YOUR SOLUTION HERE

"""
Exercise 2.2: Given a list of integers, use a heap to sort the list.

Use the Python PriorityQueue type

Time Complexity:
Space Complexity:
"""
def heap_sort(arr: List[int]) -> List[int]:
    # INSERT YOUR SOLUTION HERE

"""
Exercise 2.3: Find the k-th largest element in a stream of integers
"""
class KthLargest:

    """
    Constructor

    Time Complexity:
    Space Complexity:
    """
    def __init__(self, k: int, nums: List[int]):
        # INSERT YOUR SOLUTION HERE

    """
    Add the next value in the stream

    Time Complexity:
    Space Complexity:
    """
    def add(self, val: int) -> int:
        # INSERT YOUR SOLUTION HERE

"""
Exercise 2.4: Find the k closest points to the origin

Time Complexity:
Space Complexity:
"""
def k_closest(points: List[List[int]], k: int) -> List[List[int]]:
    # INSERT YOUR SOLUTION HERE

"""
Exercise 3.1: Find the median of a data stream
"""
class MedianFinder:

    """
    Constructor

    Time Complexity:
    Space Complexity:
    """
    def __init__(self):
        # INSERT YOUR SOLUTION HERE

    """
    Add the next number from the stream

    Time Complexity:
    Space Complexity:
    """
    def add_num(self, num: int) -> None:
        # INSERT YOUR SOLUTION HERE

    """
    Get the median

    Time Complexity:
    Space Complexity:
    """
    def find_median(self) -> float:
        # INSERT YOUR SOLUTION HERE

"""
Simple node class
"""
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

"""
Exercise 3.2: Merge k sorted lists

Time Complexity:
Space Complexity:
"""
def merge_k_lists(lists: List[ListNode]) -> ListNode:
    # INSERT YOUR SOLUTION HERE


# Test Cases
if __name__ == '__main__':
    # ADD YOUR TEST CASES HERE
