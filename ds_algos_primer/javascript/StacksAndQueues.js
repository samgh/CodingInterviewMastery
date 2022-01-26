/**
 *   Title: StacksAndQueues.js
 *
 *   This file contains the template for the Stack & Queue exercises in the
 *   DS & Algos Primer. Fill in the exercises here and refer to
 *   StackAndQueueSolutions.js for the complete code samples.
 *
 *   Execution: node StacksAndQueues.js
 */

/**
 * Exercise 1.1: Implement a Stack of integers using a Linked List
 */
function MyStack() {

    /**
     * A simple singly-linked node class
     *
     * @param{number} n
     */
    function Node(n) {
        this.val = n;
        this.next = null;
    }

    /**
     * Add an item to the top of the stack
     *
     * Time Complexity:
     * Space Complexity:
     *
     * @param{number} val
     */
    this.push = function(val) {
        // INSERT YOUR CODE HERE
    }

    /**
     * Remove an item from the top of the stack
     *
     * Time Complexity:
     * Space Complextiy:
     *
     * @return{number}
     */
    this.pop = function() {
        // INSERT YOUR CODE HERE
    }

    /*
     * Get the size of the stack
     *
     * Time Complexity:
     * Space Complexity:
     *
     * @return{number}
     */
    this.size = function() {
        // INSERT YOUR CODE HERE
    }
}

/*
 * Exercise 1.2: Implement a Queue of integers using a Linked List
 */
function MyQueue() {

    /**
     * Simple Doubly Linked List node class
     *
     * @param{number} n
     */
    function Node(n) {
        this.val = n;
        this.next = null;
        this.prev = null;
    }

    /**
     * Add an item to the beginning of the queue
     *
     * Time Complexity:
     * Space Complexity:
     *
     * @param{number} val
     */
    this.enqueue = function(val) {
        // INSERT YOUR CODE HERE
    }

    /**
     * Remove an item from the end of the queue
     *
     * Time Complexity:
     * Space Complexity:
     *
     * @return{number}
     */
    this.dequeue = function() {
        // INSERT YOUR CODE HERE
    }

    /**
     * Get the size of the stack
     *
     * Time Complexity:
     * Space Complexity:
     *
     * @return{number}
     */
    this.size = function() {
        // INSERT YOUR CODE HERE
    }
}

/**
 * Exercise 1.3: Given a stack, find the nth element in the stack
 *
 * Time Complexity:
 * Space Complexity:
 *
 * @param{number[]} stack
 * @param{number} n
 * @return{number}
 */
var nthElementInStack = function(stack, n) {
    // INSERT YOUR CODE HERE
}

/**
 * Exercise 1.4: Implement a Stack using Queues
 */

function StackFromQueues() {

    /**
     * Push an item onto the stack
     *
     * Time Complexity:
     * Space Complexity:
     *
     * @param{number} x
     */
    this.push = function(x) {
        // INSERT YOUR CODE HERE
    }

    /**
     * Pop an item from the stack
     *
     * Time Complexity:
     * Space Complexity:
     *
     * @return{number}
     */
    this.pop = function() {
        // INSERT YOUR CODE HERE
    }

    /**
     * Return the top item on the stack
     *
     * Time Complexity:
     * Space Complexity:
     *
     * @return{number}
     */
    this.top = function() {
        // INSERT YOUR CODE HERE
    }

    /**
     * Is the stack empty
     *
     * Time Complexity:
     * Space Complexity:
     *
     * @return{boolean}
     */
    this.empty = function() {
        // INSERT YOUR CODE HERE
    }
}

/**
 * Exercise 2.1: Valid Parentheses
 *
 * Time Complexity:
 * Space Complexity:
 *
 * @param{string} s
 * @return{boolean}
 */
var validParentheses = function(s) {
    // INSERT YOUR CODE HERE
}

/**
 * Exercise 2.2: Basic Calculator
 *
 * Time Complexity:
 * Space Complexity:
 *
 * @param{string} s
 * @return{number}
 */
var basicCalculator = function(s) {
    // INSERT YOUR CODE HERE
}

// Test cases
var tester = function() {
    // INSERT YOUR TEST CASES HERE
}

tester();
