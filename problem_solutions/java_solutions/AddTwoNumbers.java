/*
 *   Title: Add Two Numbers
 *   Leetcode Link: https://leetcode.com/problems/add-two-numbers/
 *
 *   Problem: You are given two non-empty linked lists representing two
 *   non-negative integers. The most significant digit comes first and each of
 *   their nodes contain a single digit. Add the two numbers and return it as a
 *   linked list.
 *
 *   Input:
 *      ListNode l1 => The first number
 *      ListNode l2 => The second number
 *   Output:
 *      ListNode    => The sum of l1 and l2
 *
 *   Execution: javac AddTwoNumbers.java && java -ea AddTwoNumbers
 */

import java.util.*;

public class AddTwoNumbers {

    /*
     * Simple ListNode class
     */
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /*
     * We will start by adding the smallest-order values together. Then we will
     * carry over the 1 if necessary and add the next values.
     *
     * Functionally, we are just doing longform addition like you would do by
     * hand.
     *
     * Time Complexity: O(max(l1.size(), l2.size()))
     * Space Complexity: O(1)
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // We'll use a dummy head to make our life easier
        ListNode result = new ListNode(0);

        ListNode curr = result;
        int carry = 0;

        // Add the current two digits together
        while (l1 != null && l2 != null) {
            int currResult = l1.val + l2.val + carry;

            // We only want the lowest order digit. If it's 2 digits, the higher
            // order digit becomes the carry digit
            curr.next = new ListNode(currResult % 10);
            curr = curr.next;
            carry = currResult / 10;
            l1 = l1.next;
            l2 = l2.next;
        }

        // If one list is longer, get a pointer to the current place in that
        // list
        ListNode remainder = l1;
        if (l2 != null) remainder = l2;

        // We need to add the carry digit and the rest of the number to our result
        while (remainder != null) {
            int currResult = remainder.val + carry;
            curr.next = new ListNode(currResult % 10);
            curr = curr.next;
            carry = currResult / 10;
            remainder = remainder.next;
        }

        // If there is a carry digit left over, add it to the result
        if (carry != 0) curr.next = new ListNode(carry);

        // We used a dummy pointer, so we have to return result.next
        return result.next;
    }

    /*
     * Simple method to compare 2 lists for testing
     */
    public static boolean compareLists(ListNode l1, ListNode l2) {
        while (l1 != null) {
            if (l1.val != l2.val) {
                return false;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        return true;
    }

    // Test cases
    public static void main(String[] args) {
        ListNode l1 = new ListNode(7);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        l1.next.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode out = addTwoNumbers(l1, l2);

        ListNode expectedOut = new ListNode(2);
        expectedOut.next = new ListNode(9);
        expectedOut.next.next = new ListNode(8);
        expectedOut.next.next.next = new ListNode(3);

        assert compareLists(out, expectedOut) == true;

        // ADD YOUR TEST CASES HERE

        System.out.println("Passed all test cases");
    }
}
