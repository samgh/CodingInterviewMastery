/**
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
 *   Execution: node AddTwoNumbers.js
 */

/**
 * Simple ListNode class
 */
function ListNode(val) {
    this.val = val;
}

/**
 * We will start by adding the smallest-order values together. Then we will
 * carry over the 1 if necessary and add the next values.
 *
 * Functionally, we are just doing longform addition like you would do by
 * hand.
 *
 * Time Complexity: O(max(l1.size(), l2.size()))
 * Space Complexity: O(1)
 *
 * @param{ListNode} l1
 * @param{ListNode} l2
 * @return{ListNode}
 */
var addTwoNumbers = function(l1, l2) {
        // We'll use a dummy head to make our life easier
    var result = new ListNode(0);

    var curr = result;
    var carry = 0;

    // Add the current two digits together
    while (l1 != null && l2 != null) {
        var currResult = l1.val + l2.val + carry;

        // We only want the lowest order digit. If it's 2 digits, the higher
        // order digit becomes the carry digit
        curr.next = new ListNode(currResult % 10);
        curr = curr.next;
        carry = Math.floor(currResult / 10);
        l1 = l1.next;
        l2 = l2.next;
    }

    // If one list is longer, get a pointer to the current place in that
    // list
    var remainder = l1;
    if (l2 != null) remainder = l2;

    // We need to add the carry digit and the rest of the number to our result
    while (remainder != null) {
        var currResult = remainder.val + carry;
        curr.next = new ListNode(currResult % 10);
        curr = curr.next;
        carry = Math.floor(currResult / 10);
        remainder = remainder.next;
    }

    // If there is a carry digit left over, add it to the result
    if (carry != 0) curr.next = new ListNode(carry);

    // We used a dummy pointer, so we have to return result.next
    return result.next;
}

/**
 * Simple method to compare 2 lists for testing
 *
 * @param{ListNode} l1
 * @param{ListNode} l2
 * @return{boolean{}}
 */
var compareLists = function(l1, l2) {
    while (l1 != null) {
        if (l1.val != l2.val) {
            return false;
        }
        l1 = l1.next;
        l2 = l2.next;
    }
    return true;
}

/**
 * Sample test cases
 */
var tester = function() {
    var l1 = new ListNode(7);
    l1.next = new ListNode(2);
    l1.next.next = new ListNode(4);
    l1.next.next.next = new ListNode(3);

    var l2 = new ListNode(5);
    l2.next = new ListNode(6);
    l2.next.next = new ListNode(4);

    var out = addTwoNumbers(l1, l2);

    var expectedOut = new ListNode(2);
    expectedOut.next = new ListNode(9);
    expectedOut.next.next = new ListNode(8);
    expectedOut.next.next.next = new ListNode(3);

    while (out != null) {
        console.log(out.val);
        out = out.next;
    }

    console.assert(compareLists(out, expectedOut) === true);

    // ADD YOUR TEST CASES HERE

    console.log("Passed all test cases");
}

tester();
