"""
Title: Stack & Queue Solutions

This file contains the template for the Stack & Queue exercises in the
DS & Algos Primer. Fill in the exercises here and refer to
stack_and_queue_solutions.py for the complete code samples.

Execution: python stacks_and_queues.py
"""

import queue
from typing import List

"""
Exercise 1.1: Implement a Stack of integers using a Linked List
"""
class MyStack:

    """
    A simple singly-linked node class
    """
    class _Node:
        def __init__(self, n: int):
            self.val = n
            self.next = None

    """
    Constructor
    """
    def __init__(self):
        # INSERT YOUR SOLUTION HERE

    """
    Add an item to the top of the stack

    Time Complexity:
    Space Complexity:
    """
    def push(self, val: int):
        # INSERT YOUR SOLUTION HERE

    """
    Remove an item from the top of the stack

    Time Complexity:
    Space Complextiy:
    """
    def pop(self) -> int:
        # INSERT YOUR SOLUTION HERE

    """
    Get the size of the stack

    Time Complexity:
    Space Complexity:
    """
    def size(self) -> int:
        # INSERT YOUR SOLUTION HERE

"""
Exercise 1.2: Implement a Queue of integers using a Linked List
"""
class MyQueue:

    """
    A simple doubly-linked node class
    """
    class _Node:
        def __init__(self, n: int):
            self.val = n
            self.next = None
            self.prev = None

    """
    Constructor
    """
    def __init__(self):
        # INSERT YOUR SOLUTION HERE

    """
    Add an item to the beginning of the queue

    Time Complexity:
    Space Complexity:
    """
    def enqueue(self, val: int):
        # INSERT YOUR SOLUTION HERE

    """
    Remove an item from the end of the queue

    Time Complexity:
    Space Complexity:
    """
    def dequeue(self) -> int:
        # INSERT YOUR SOLUTION HERE

    """
    Get the size of the stack

    Time Complexity:
    Space Complexity:
    """
    def size(self) -> int:
        # INSERT YOUR SOLUTION HERE

"""
Exercise 1.3: Given a stack, find the nth element in the stack

Time Complexity:
Space Complexity:
"""
def nth_element_in_stack(stack: List[int], n: int) -> int:
    # INSERT YOUR SOLUTION HERE

"""
Exercise 1.4: Implement a Stack using Queues
"""
class StackFromQueues:

    """
    Constructor
    """
    def __init__(self):
        # INSERT YOUR SOLUTION HERE

    """
    Push an item onto the stack

    Time Complexity:
    Space Complexity:
    """
    def push(self, x: int) -> None:
        # INSERT YOUR SOLUTION HERE

    """
    Pop an item from the stack

    Time Complexity:
    Space Complexity:
    """
    def pop(self) -> int:
        # INSERT YOUR SOLUTION HERE

    """
    Return the top item on the stack

    Time Complexity:
    Space Complexity:
    """
    def top(self) -> int:
        # INSERT YOUR SOLUTION HERE

    def empty(self) -> bool:
        # INSERT YOUR SOLUTION HERE

"""
Exercise 2.1: Valid Parentheses

Time Complexity:
Space Complexity:
"""
def valid_parentheses(s: str) -> bool:
    # INSERT YOUR SOLUTION HERE

"""
Exercise 2.2: Basic Calculator

Time Complexity:
Space Complexity:
"""
def basic_calculator(s: str) -> int:
    # INSERT YOUR SOLUTION HERE

if __name__ == '__main__':
    # INSERT YOUR TEST CASES HERE
