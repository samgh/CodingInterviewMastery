/*
 *   Title: HeapSolutions.java
 *
 *   This file contains the template for the Heap exercises in
 *   the DS & Algos Primer. Fill in the exercises here and refer to
 *   ArraysAndStringsSolutions.java for the complete code samples.
 *
 *   Execution: javac HeapSolutions.java && java -ea HeapSolutions
 */

import java.util.*;

public class HeapSolutions {

    /*
     * Exercise 1.1: Implement a min heap
     */
    public static class MinHeap {

        // Constructor
        public MinHeap() {
            // INSERT YOUR CODE HERE
        }

        /*
         * Insert an item into the heap
         *
         * Time Complexity:
         * Space Complexity:
         */
        public void insert(int x) {
            // INSERT YOUR CODE HERE
        }

        /*
         * Get the smallest value in the heap
         *
         * Time Complexity:
         * Space Complexity:
         */
        public int peek() {
            // INSERT YOUR CODE HERE
        }

        /*
         * Remove and return the smallest value in the heap
         *
         * Time Complexity:
         * Space Complexity:
         */
        public int pop() {
            // INSERT YOUR CODE HERE
        }

        /*
         * Get the size of the heap
         *
         * Time Complexity:
         * Space Complexity:
         */
        public int size() {
            // INSERT YOUR CODE HERE
        }

        /*
         * Convert the heap data into a string
         *
         * Time Complexity:
         * Space Complexity:
         */
        public String toString() {
            // INSERT YOUR CODE HERE
        }

        /*
         * The following are some optional helper methods. These are not required
         * but may make your life easier
         */

        /*
         * Get the index of the parent node of a given index
         *
         * Time Complexity:
         * Space Complexity:
         */
        private static int parent(int i) {
            // INSERT YOUR CODE HERE
        }

        /*
         * Get the index of the left child of a given index
         *
         * Time Complexity:
         * Space Complexity:
         */
        private static int leftChild(int i) {
            // INSERT YOUR CODE HERE
        }

        /*
         * Swap the values at 2 indices in our heap
         *
         * Time Complexity:
         * Space Complexity:
         */
        private void swap(int i, int j) {
            // INSERT YOUR CODE HERE
        }

        /*
         * Starting at index i, bubble up the value until it is at the correct
         * position in the heap
         *
         * Time Complexity:
         * Space Complexity:
         */
        private void bubbleUp(int i) {
            // INSERT YOUR CODE HERE
        }

        /*
         * Starting at index i, bubble down the value until it is at the correct
         * position in the heap
         *
         * Time Complexity:
         * Space Complexity:
         */
        private void bubbleDown(int i) {
            // INSERT YOUR CODE HERE
        }
    }

    /*
     * Exercise 1.2: Given an array of integers, determine whether the array
     * represents a valid heap
     *
     * Time Complexity:
     * Space Complexity:
     */
    public static boolean isValid(int[] heap) {
        // INSERT YOUR CODE HERE
    }

    /*
     * Exercise 2.1: Given a list of integers, use a heap to find the largest
     * value in the list.
     *
     * Use the Java PriorityQueue data structure
     *
     * Time Complexity:
     * Space Complexity:
     */
    public static int findMax(int[] arr) {
        // INSERT YOUR CODE HERE
    }

    /*
     * Exercise 2.2: Given a list of integers, use a heap to sort the list.
     *
     * Use the Java PriorityQueue data structure
     *
     * Time Complexity:
     * Space Complexity:
     */
    public static int[] heapSort(int[] arr) {
        // INSERT YOUR CODE HERE
    }

    /*
     * Exercise 2.3: Find the k-th largest element in a stream of integers
     */
    public static class KthLargest {

        /*
         * Constructor
         *
         * Time Complexity:
         * Space Complexity:
         */
        public KthLargest(int k, int[] nums) {
            // INSERT YOUR CODE HERE
        }

        /*
         * Add the next value in the stream
         *
         * Time Complexity:
         * Space Complexity:
         */
        public int add(int val) {
            // INSERT YOUR CODE HERE
        }
    }

    /*
     * Exercise 2.4: Find the k closest points to the origin
     *
     * Time Complexity:
     * Space Complexity:
     */
    public static int[][] kClosest(int[][] points, int k) {
        // INSERT YOUR CODE HERE
    }

    /*
     * Exercise 3.1: Find the median of a data stream
     */
    public static class MedianFinder {

        /*
         * Constructor
         *
         * Time Complexity:
         * Space Complexity:
         */
        public MedianFinder() {
            // INSERT YOUR CODE HERE
        }

        /*
         * Add the next number from the stream
         *
         * Time Complexity:
         * Space Complexity:
         */
        public void addNum(int num) {
            // INSERT YOUR CODE HERE
        }

        /*
         * Get the median
         *
         * Time Complexity:
         * Space Complexity:
         */
        public double findMedian() {
            // INSERT YOUR CODE HERE
        }
    }

    /*
     * Exercise 3.2: Merge k sorted lists
     *
     * Time Complexity:
     * Space Complexity:
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        // INSERT YOUR CODE HERE
    }

    /*
     * Simple node class
     */
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            this.val = x;
        }
    }


    // Test cases
    public static void main(String[] args) {
        // ADD YOUR TEST CASES HERE
    }
}
