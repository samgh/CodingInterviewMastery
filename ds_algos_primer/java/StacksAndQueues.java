/*
 *   Title: Stack & Queue Solutions
 *
 *   This file contains the template for the Stack & Queue exercises in the
 *   DS & Algos Primer. Fill in the exercises here and refer to
 *   StackAndQueueSolutions.java for the complete code samples.
 *
 *   Execution: javac StacksAndQueues.java && java -ea StacksAndQueues
 */

import java.util.*;

public class StacksAndQueues {

    /*
     * Exercise 1.1: Implement a Stack of integers using a Linked List
     */
    public static class MyStack {

        /*
         * Simple Linked List node class
         */
        private static class Node {
            private int val;
            private Node next;

            private Node(int val) {
                this.val = val;
            }
        }

        /*
         * Constructor
         */
        public MyStack() {
            // INSERT YOUR CODE HERE
        }

        /*
         * Add an item to the top of the stack
         *
         * Time Complexity:
         * Space Complexity:
         */
        public void push(int val) {
            // INSERT YOUR CODE HERE
        }

        /*
         * Remove an item from the top of the stack
         *
         * Time Complexity:
         * Space Complextiy:
         */
        public int pop() {
            // INSERT YOUR CODE HERE
        }

        /*
         * Get the size of the stack
         *
         * Time Complexity:
         * Space Complexity:
         */
        public int size() {
            // INSERT YOUR CODE HERE
        }
    }

    /*
     * Exercise 1.2: Implement a Queue of integers using a Linked List
     */
    public static class MyQueue {

        /*
         * Simple Doubly Linked List node class
         */
        private static class Node {
            private int val;
            private Node next;
            private Node prev;

            private Node(int val) {
                this.val = val;
            }
        }

        /*
         * Constructor
         */
        public MyQueue() {
            // INSERT YOUR CODE HERE
        }

        /*
         * Add an item to the beginning of the queue
         *
         * Time Complexity:
         * Space Complexity:
         */
        public void enqueue(int val) {
            // INSERT YOUR CODE HERE
        }

        /*
         * Remove an item from the end of the queue
         *
         * Time Complexity:
         * Space Complexity:
         */
        public int dequeue() {
            // INSERT YOUR CODE HERE
        }

        /*
         * Get the size of the stack
         *
         * Time Complexity:
         * Space Complexity:
         */
        public int size() {
            // INSERT YOUR CODE HERE
        }
    }

    /*
     * Exercise 1.3: Given a stack, find the nth element in the stack
     *
     * Time Complexity:
     * Space Complexity:
     */
    public static int nthElementInStack(Stack<Integer> stack, int n) {
        // INSERT YOUR CODE HERE
    }

    /*
     * Exercise 1.4: Implement a Stack using Queues
     */
    public static class StackFromQueues {

        /*
         * Constructor
         *
         * Time Complexity:
         * Space Complexity:
         */
        public StackFromQueues() {
            // INSERT YOUR CODE HERE
        }

        /*
         * Push an item onto the stack
         *
         * Time Complexity:
         * Space Complexity:
         */
        public void push(int x) {
            // INSERT YOUR CODE HERE
        }

        /*
         * Pop an item from the stack
         *
         * Time Complexity:
         * Space Complexity:
         */
        public int pop() {
            // INSERT YOUR CODE HERE
        }

        /*
         * Return the top item on the stack
         *
         * Time Complexity:
         * Space Complexity:
         */
        public int top() {
            // INSERT YOUR CODE HERE
        }

        /*
         * Is the stack empty
         *
         * Time Complexity:
         * Space Complexity:
         */
        public boolean empty() {
            // INSERT YOUR CODE HERE
        }
    }

    /*
     * Exercise 2.1: Valid Parentheses
     *
     * Time Complexity:
     * Space Complexity:
     */
    public static boolean validParentheses(String s) {
        // INSERT YOUR CODE HERE
    }

    /*
     * Exercise 2.2: Basic Calculator
     *
     * Time Complexity:
     * Space Complexity:
     */
    public static int basicCalculator(String s) {
        // INSERT YOUR CODE HERE
    }

    /*
     * Sample test cases
     */
    public static void main(String[] args) {
        // INSERT YOUR TEST CASES HERE
    }
}
