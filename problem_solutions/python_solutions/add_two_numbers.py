"""
Title: Add Two Numbers
Leetcode Link: https://leetcode.com/problems/add-two-numbers/

Problem: You are given two non-empty linked lists representing two
non-negative integers. The most significant digit comes first and each of
their nodes contain a single digit. Add the two numbers and return it as a
linked list.

   Input:
      ListNode l1 => The first number
      ListNode l2 => The second number
   Output:
      ListNode    => The sum of l1 and l2

Execution: python add_two_numbers.py
"""
import unittest
from typing import List, Tuple


# Simple ListNode class
class ListNode:
    def __init__(self, x: int):
        self.val = x
        self.next = None

"""
We will start by adding the smallest-order values together. Then we will
carry over the 1 if necessary and add the next values.

Functionally, we are just doing longform addition like you would do by
hand.

Time Complexity: O(max(l1.size(), l2.size()))
Space Complexity: O(1)
"""
def add_two_numbers(l1: ListNode, l2: ListNode) -> ListNode:
    # We'll use a dummy head to make our life easier
    result = ListNode(0)

    curr = result
    carry = 0

    # Add the current two digits together
    while l1 and l2:
        curr_result = l1.val + l2.val + carry

        # We only want the lowest order digit. If it's 2 digits, the higher
        # order digit becomes the carry digit
        curr.next = ListNode(curr_result % 10)
        curr = curr.next
        carry = curr_result // 10
        l1 = l1.next
        l2 = l2.next

    # If one list is longer, get a pointer to the current place in that list
    remainder = l1
    if l2:
        remainder = l2

    # We need to add the carry digit and the rest of the number to our result
    while remainder:
        curr_result = remainder.val + carry
        curr.next = ListNode(curr_result % 10)
        curr = curr.next
        carry = curr_result // 10
        remainder = remainder.next

    # If there is a carry digit left over, add it to the result
    if carry != 0:
        curr.next = ListNode(carry)

    # We used a dummy pointer, so we have to return result.next
    return result.next

"""
Simple method to compare 2 lists for testing
"""
def compare_lists(l1: ListNode, l2: ListNode) -> bool:
    while l1:
        if l1.val != l2.val:
            return False
        l1 = l1.next
        l2 = l2.next
    return True


class TestAddTwoNumbers(unittest.TestCase):
    """Unit test for add_two_numbers."""

    def test_1(self):
        l1 = ListNode(7)
        l1.next = ListNode(2)
        l1.next.next = ListNode(4)
        l1.next.next.next = ListNode(3)

        l2 = ListNode(5)
        l2.next = ListNode(6)
        l2.next.next = ListNode(4)

        l_out = ListNode(2)
        l_out.next = ListNode(9)
        l_out.next.next = ListNode(8)
        l_out.next.next.next = ListNode(3)

        result = add_two_numbers(l1, l2)

        self.assertEqual(compare_lists(result, l_out), True)

        # ADD YOUR TEST CASES HERE


if __name__ == '__main__':
    unittest.main()
