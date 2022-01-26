"""
Title: Stack & Queue Solutions

This file contains the solutions for Exercise Sets of the Stack & Queue
exercises in the DS & Algos Primer. If you have not already attempted these
exercises, we highly recommend you complete them before reviewing the solutions
here.

Execution: python stack_and_queue_solutions.py
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
        self._head = None
        self._size = 0

    """
    Add an item to the top of the stack

    Time Complexity: O(1)
    Space Complexity: O(1)
    """
    def push(self, val: int):
        # Add the new value to the front of the Linked List
        new_node = MyStack._Node(val)
        new_node.next = self._head
        self._head = new_node
        self._size = self._size+1

    """
    Remove an item from the top of the stack

    Time Complexity: O(1)
    Space Complextiy: O(1)
    """
    def pop(self) -> int:
        if self._size == 0:
            raise IndexError()

        # Remove the first item from the Linked List
        curr = self._head
        self._head = self._head.next
        self._size = self._size-1

        return curr.val

    """
    Get the size of the stack

    Time Complexity: O(1)
    Space Complexity: O(1)
    """
    def size(self) -> int:
        return self._size

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
        self._head = None
        self._tail = None
        self._size = 0

    """
    Add an item to the beginning of the queue

    Time Complexity: O(1)
    Space Complexity: O(1)
    """
    def enqueue(self, val: int):
        new_node = MyQueue._Node(val)

        # If this is the first node, initialize head and tail
        if self._size == 0:
            self._head = new_node
            self._tail = new_node
        else:
            new_node.next = self._head
            self._head.prev = new_node
            self._head = new_node

        self._size = self._size + 1

    """
    Remove an item from the end of the queue

    Time Complexity: O(1)
    Space Complexity: O(1)
    """
    def dequeue(self) -> int:
        if self._size == 0:
            raise IndexError()

        curr = self._tail

        # If this is the last node, set head and tail to null
        if self._size == 1:
            self._head = None
            self._tail = None
        else:
            self._tail = curr.prev
            self._tail.next = None

        self._size = self._size - 1
        return curr.val

    """
    Get the size of the stack

    Time Complexity: O(1)
    Space Complexity: O(1)
    """
    def size(self) -> int:
        return self._size

"""
Exercise 1.3: Given a stack, find the nth element in the stack

Time Complexity: O(n)
Space Complexity: O(n)
"""
def nth_element_in_stack(stack: List[int], n: int) -> int:
    if n > len(stack):
        raise IndexError()

    # Pop the first n-1 elements off the stack
    temp_stack = []
    for i in range(1, n):
        temp_stack.append(stack.pop())

    result = stack[-1]

    # Put the removed elements back onto the stack
    while len(temp_stack) > 0:
        stack.append(temp_stack.pop())

    return result

"""
Exercise 1.4: Implement a Stack using Queues
"""
class StackFromQueues:

    """
    Constructor
    """
    def __init__(self):
        self._data = queue.Queue()

    """
    Push an item onto the stack

    Time Complexity: O(n)
    Space Complexity: O(1)
    """
    def push(self, x: int) -> None:
        # We need to insert the value as the first item in the queue. We
        # can do that by copying everything into a new queue
        new_data = queue.Queue()

        # Add the value
        new_data.put(x)

        # Add everything else behind it in the queue
        while not self._data.empty():
            new_data.put(self._data.get());

        # Update our data to point to the new queue
        self._data = new_data;

    """
    Pop an item from the stack

    Time Complexity: O(1)
    Space Complexity: O(1)
    """
    def pop(self) -> int:
        return self._data.get()

    """
    Return the top item on the stack

    Time Complexity: O(1)
    Space Complexity: O(1)
    """
    def top(self) -> int:
        return self._data.queue[0]

    def empty(self) -> bool:
        return self._data.empty()

"""
Exercise 2.1: Valid Parentheses

Time Complexity: O(n)
Space Complexity: O(n)
"""
def valid_parentheses(s: str) -> bool:
    #  We need to know which open parens match which close parens
    matching_parens = {
        '(': ')',
        '[': ']',
        '{': '}'
    }

    # Use a stack to keep track of all the open parens
    open_parens = []

    # If the current char is an open paren, add it to the stack. Otherwise
    # for the string to be valid, the char needs to be the matching paren
    # for the top char on the stack
    for c in s:
        if c == '(' or c == '[' or c == '{':
            open_parens.append(c);
        elif len(open_parens) == 0:
            # If the stack is empty, then there are too many close parens
            return False
        else:
            open = open_parens.pop();
            if matching_parens[open] != c:
                return False

    return len(open_parens) == 0

"""
Exercise 2.2: Basic Calculator

Time Complexity: O(n)
Space Complexity: O(n)
"""
def basic_calculator(s: str) -> int:
    calc = []

    # Track the running sum and the sign of the next value
    sum = 0
    sign = 1

    i = 0
    # Iterate over the string
    while i < len(s):
        curr = s[i]

        # If the character is a digit, we get the whole number and then add
        # or subtract it from our sum based on the current sign
        if curr.isdigit():
            # Get the full number. It can be more than 1 digit
            end_of_number = i
            while end_of_number+1 < len(s) and s[end_of_number+1].isdigit():
                end_of_number = end_of_number + 1

            number = int(s[i: end_of_number+1])

            # Sign tells us whether to add or subtract the current number
            sum = sum + number * sign

            # If we took multiple digits, update the position of i
            i = end_of_number

        # If we see a +/-, update the sign accordingly
        if curr == '+':
            sign = 1

        if curr == '-':
            sign = -1

        # If we see an open paren, we just save our current sum and sign
        # and then start a new computation. We essentially compute the inner
        # sum first, and then pop the sum from before from our stack to
        # combine them together
        if curr == '(':
            calc.append((sum, sign))
            sum = 0
            sign = 1

        # If we find a close paren, we combine our current sum with the
        # previous sum from outside the parens
        if curr == ')':
            prev_state = calc.pop()
            sign = prev_state[1];
            sum = prev_state[0] + sum * sign;

        i = i+1

    return sum

if __name__ == '__main__':
    s = MyStack()
    s.push(1)
    s.push(2)
    s.push(3)
    print(s.pop())
    s.push(4)
    print(s.pop())
    print(s.pop())
    print(s.size())


    q = MyQueue()
    q.enqueue(1)
    q.enqueue(2)
    q.enqueue(3)
    print(q.dequeue())
    q.enqueue(4)
    print(q.dequeue())
    print(q.dequeue())
    print(q.size())


    stack = [1,2,3,4,5];
    print(nth_element_in_stack(stack, 2));

    sfq = StackFromQueues();
    sfq.push(1);
    sfq.push(2);
    sfq.push(3);
    print(sfq.pop());
    print(sfq.top());

    print(valid_parentheses("([]{}(()))"));
    print(valid_parentheses("(]"));

    print(basic_calculator("1 + 1"));
    print(basic_calculator("12 - (2 + 3) + 4"));
