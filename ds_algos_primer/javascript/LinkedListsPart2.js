/*
 *   Title: LinkedListsPart2
 *
 *   This file contains the template for Exercise Sets #2-5 of the Linked List
 *   exercises in the DS & Algos Primer. Fill in the exercises here and refer to
 *   LinkedListSolutionsPart2.js for the complete code samples.
 *
 *   Execution: node LinkedListsPart2.js
 */

/**
 * A simple singly-linked node class (copied from Part 1)
 *
 * @param{number} n
 */
function SinglyLinkedListNode(n) {
    this.val = n;
    this.next = null;
}

/**
 * A simple doubly-linked node class (copied from Part 1)
 *
 * @param{number} n
 */
function DoublyLinkedListNode(n) {
    this.val = n;
    this.prev = null;
    this.next = null;
}

/**
 * Exercise 2.1: Write a function that swaps two nodes in a doubly-linked list
 *
 * @param{DoublyLinkedListNode} l
 * @param{number} n
 * @param{number} m
 */
var swapNodes = function(l, n, m) {
    // INSERT YOUR CODE HERE
}

/**
 * Exercise 2.2: Write a function that removes the odd-indexed values from a
 * singly-linked list
 *
 * @param{SinglyLinkedListNode} l
 */
var removeOdd = function(l) {
    // INSERT YOUR CODE HERE
}

/**
 * Exercise 2.3: Write a function that de-interleaves the even and odd indices
 * in a singly-linked list. Your resulting list should have all the even indices
 * first followed by all the odd indices.
 *
 * @param{SinglyLinkedListNode} l
 */
var deinterleave = function(l) {
    // INSERT YOUR CODE HERE
}

/**
 * Exercise 2.4: Write a function that reverses a singly-linked list
 *
 * @param{SinglyLinkedListNode} l
 */
var reverse = function(l) {
    // INSERT YOUR CODE HERE
}

/**
 * Exercise 3.1: Write a function that compares 2 singly-linked lists and
 * returns true if the two lists are identical
 *
 * @param{SinglyLinkedListNode} l1
 * @param{SinglyLinkedListNode} l2
 * @return{boolean}
 */
var areEqual = function(l1, l2) {
    // INSERT YOUR CODE HERE
}

/**
 * Exercise 3.2: Write a function that returns the nth-to-last value in a
 * singly-linked list
 *
 * @param{SinglyLinkedListNode} l
 * @param{number} n
 * @return{SinglyLinkedListNode}
 */
var nthToLast = function(l, n) {
    // INSERT YOUR CODE HERE
}

/**
 * Exercise 3.3: Write a function that returns the value at the midpoint of
 * a singly-linked list. You can assume the length of the list is odd.
 *
 * @param{SinglyLinkedListNode} l
 * @return{SinglyLinkedListNode}
 */
var midpoint = function(l) {
    // INSERT YOUR CODE HERE
}

/**
 * Exercise 4.1: Remove all occurrences of n from a singly-linked list
 *
 * @param{SinglyLinkedListNode} l
 * @param{number} n
 * @return{SinglyLinkedListNode}
 */
var removeAll = function(l, n) {
    // INSERT YOUR CODE HERE
}

/**
 * Exercise 5.1: Given a singly-linked list, determine if the list contains
 * a cycle. DO NOT use Floyd’s algorithm. FInd some other method for identifying
 * a cycle
 *
 * @param{SinglyLinkedListNode} l
 * @return{boolean}
 */
var hasCycleNaive = function(l) {
    // INSERT YOUR CODE HERE
}

/**
 * Exercise 5.2: Given a singly-linked list, determine if the list contains
 * a cycle using Floyd's algorithm
 *
 * @param{SinglyLinkedListNode} l
 * @return{boolean}
 */
var hasCycle = function(l) {
    // INSERT YOUR CODE HERE
}


// We've included some helper methods below that you can use for your tests

/**
 * Test method to generate singly linked list with n items
 *
 * @param{number} n
 * @return{SinglyLinkedListNode}
 */
var singleGenerator = function(n) {
    var head = new SinglyLinkedListNode(1);
    var curr = head;
    for (var i = 2; i <= n; i++) {
        curr.next = new SinglyLinkedListNode(i);
        curr = curr.next;
    }
    return head;
}

/**
 * Test method to generate doubly linked list with n items
 *
 * @param{number} n
 * @return{DoublyLinkedListNode}
 */
var doubleGenerator = function(n) {
    var head = new DoublyLinkedListNode(1);
    var curr = head;
    for (var i = 2; i <= n; i++) {
        curr.next = new DoublyLinkedListNode(i);
        curr.next.prev = curr;
        curr = curr.next;
    }
    return head;
}

/**
 * Test method to print singly linked list
 *
 * @param{SinglyLinkedListNode} n
 */
var printSingle = function(n) {
    var curr = n;
    var result = [];
    while (curr != null) {
        result.push(curr.val + " -> ");
        curr = curr.next;
    }
    result.push("null");
    console.log(result.join(""));
}

/**
 * Test method to print doubly linked list
 *
 * @param{DoublyLinkedListNode} n
 */
var printDouble = function(n) {
    if (n == null) console.log("null");

    var curr = n;
    var result = [];
    result.push("null <- ");
    while (curr.next != null) {
        result.push(curr.val + " <-> ");
        curr = curr.next;
    }
    result.push(curr.val + " -> null");
    console.log(result.join(""));
}

var tester = function() {
    // ADD TEST CASES HERE
}

tester();
