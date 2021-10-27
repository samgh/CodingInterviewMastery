"""
Title: Linked List Solutions Part 2

This file contains the template for Exercise Sets #2-5 of the Linked List
exercises in the DS & Algos Primer. Fill in the exercises here and refer to
linked_list_solutions_part_2.py for the complete code samples.

Execution: python linked_lists_part_2.py
"""

"""
A simple singly-linked node class (copied from Part 1)
"""
class SinglyLinkedListNode:
    def __init__(self, val=0):
        self.val = val
        self.next = None

"""
A simple doubly-linked node class (copied from Part 1)
"""
class DoublyLinkedListNode:
    def __init__(self, val=0):
        self.val = val
        self.prev = None
        self.next = None

"""
Exercise 2.1: Write a function that swaps two nodes in a doubly-linked
list
"""
def swap_nodes(l: DoublyLinkedListNode, n: int, m: int):
    # INSERT YOUR CODE HERE

"""
Exercise 2.2: Write a function that removes the odd-indexed values from a
singly-linked list
"""
def remove_odd(l: SinglyLinkedListNode):
    # INSERT YOUR CODE HERE

"""
Exercise 2.3: Write a function that de-interleaves the even and odd indices in a
singly-linked list. Your resulting list should have all the even indices first
followed by all the odd indices
"""
def deinterleave(l: SinglyLinkedListNode):
    # INSERT YOUR CODE HERE

"""
Exercise 2.4: Write a function that reverses a singly-linked list
"""
def reverse(l: SinglyLinkedListNode) -> SinglyLinkedListNode:
    # INSERT YOUR CODE HERE

"""
Exercise 3.1: Write a function that compares 2 singly-linked lists and returns
true if the two lists are identical
"""
def are_equal(l1: SinglyLinkedListNode, l2: SinglyLinkedListNode) -> bool:
    # INSERT YOUR CODE HERE

"""
Exercise 3.2: Write a function that returns the nth-to-last value in a
singly-linked list
"""
def nth_to_last(l: SinglyLinkedListNode, n: int) -> SinglyLinkedListNode:
    # INSERT YOUR CODE HERE

"""
Exercise 3.3: Write a function that returns the value at the midpoint of a
singly-linked list. You can assume the length of the list is odd.
"""
def midpoint(l: SinglyLinkedListNode) -> SinglyLinkedListNode:
    # INSERT YOUR CODE HERE

"""
Exercise 4.1: Remove all occurrences of n from a singly-linked list
"""
def remove_all(l: SinglyLinkedListNode, n: int) -> SinglyLinkedListNode:
    # INSERT YOUR CODE HERE

"""
Exercise 5.1: Given a singly-linked list, determine if the list contains a
cycle. DO NOT use Floyd’s algorithm. FInd some other method for identifying a
cycle
"""
def has_cycle_naive(l: SinglyLinkedListNode) -> bool:
    # INSERT YOUR CODE HERE

"""
Exercise 5.2: Given a singly-linked list, determine if the list contains a cycle
using Floyd's algorithm
"""
def has_cycle(l: SinglyLinkedListNode) -> bool:
    # INSERT YOUR CODE HERE



"""
We've included some helper methods below that you can use for your tests
"""

"""
Test method to generate singly linked list with n items
"""
def single_generator(n: int) -> SinglyLinkedListNode:
    head = SinglyLinkedListNode(1)
    curr = head
    for i in range(2, n+1):
        curr.next = SinglyLinkedListNode(i)
        curr = curr.next

    return head

"""
Test method to generate doubly linked list with n items
"""
def double_generator(n: int) -> DoublyLinkedListNode:
    head = DoublyLinkedListNode(1)
    curr = head
    for i in range(2, n+1):
        curr.next = DoublyLinkedListNode(i)
        curr.next.prev = curr
        curr = curr.next

    return head

"""
Test method to print singly linked list
"""
def print_single(n: SinglyLinkedListNode):
    curr = n
    string = []
    while curr:
        string.append(str(curr.val) + " -> ")
        curr = curr.next
    string.append("null")
    print(''.join(string))

"""
Test method to print doubly linked list
"""
def print_double(n: DoublyLinkedListNode):
    if not n:
        print("null")
    curr = n
    string = []
    while curr:
        string.append(str(curr.val) + " -> ")
        curr = curr.next
    string.append("null")
    print(''.join(string))


if __name__ == '__main__':
    # ADD YOUR TEST CASES HERE
