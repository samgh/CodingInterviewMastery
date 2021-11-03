/**
 *   Title: HeapSolutions.js
 *
 *   This file contains the template for the Heap exercises in
 *   the DS & Algos Primer. Fill in the exercises here and refer to
 *   ArraysAndStringsSolutions.java for the complete code samples.
 *
 *   Execution: node HeapSolutions.js
 */

/**
 * Exercise 1.1: Implement a min heap
 */
function MinHeap() {

    /**
     * Insert an item into the heap
     *
     * Time Complexity:
     * Space Complexity:
     *
     * @param{number} x
     */
    this.insert = function(x) {
        // INSERT YOUR CODE HERE
    }

    /**
     * Get the smallest value in the heap
     *
     * Time Complexity:
     * Space Complexity:)
     *
     * @return{number}
     */
    this.peek = function() {
        // INSERT YOUR CODE HERE
    }

    /**
     * Remove and return the smallest value in the heap
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
     * Get the size of the heap
     *
     * Time Complexity:
     * Space Complexity:
     *
     * @return{number}
     */
    this.size = function() {
        // INSERT YOUR CODE HERE
    }

    /**
     * Convert the heap data into a string
     *
     * Time Complexity:
     * Space Complexity:
     *
     * @return{string}
     */
    MinHeap.prototype.toString = function() {
        // INSERT YOUR CODE HERE
    }

    /*
     * The following are some optional helper methods. These are not required
     * but may make your life easier
     */

    /**
     * Get the index of the parent node of a given index
     *
     * Time Complexity:
     * Space Complexity:
     *
     * @param{number} i
     * @return{number}
     */
    var parent = function(i) {
        // INSERT YOUR CODE HERE
    }

    /**
     * Get the index of the left child of a given index
     *
     * Time Complexity:
     * Space Complexity:
     *
     * @param{number} i
     * @return{number}
     */
    var leftChild = function(i) {
        // INSERT YOUR CODE HERE
    }

    /**
     * Swap the values at 2 indices in our heap
     *
     * Time Complexity:
     * Space Complexity:
     *
     * @param{number} i
     * @param{number} j
     */
    var swap = function(i, j) {
        // INSERT YOUR CODE HERE
    }

    /**
     * Starting at index i, bubble up the value until it is at the correct
     * position in the heap
     *
     * Time Complexity:
     * Space Complexity:
     *
     * @param{number} i
     */
    var bubbleUp = function(i) {
        // INSERT YOUR CODE HERE
    }

    /**
     * Starting at index i, bubble down the value until it is at the correct
     * position in the heap
     *
     * Time Complexity:
     * Space Complexity:
     *
     * @param{number} i
     */
    var bubbleDown = function(i) {
        // INSERT YOUR CODE HERE
    }
}

/**
 * Exercise 1.2: Given an array of integers, determine whether the array
 * represents a valid heap
 *
 * Time Complexity:
 * Space Complexity:
 *
 * @param{number[]} heap
 * @return{boolean}
 */
var isValid = function(heap) {
    // INSERT YOUR CODE HERE
}

/**
 * Simple heap implementation that you can use in your interviews. This just
 * sorts an array rather than doing the proper heap implementation. However, it
 * is very quick to implement when you don't have an actual heap implementation
 * to use.
 *
 * This implementation allows you to take in a function for custom comparisons.
 * You can also just implement the comparison directly if you only need one
 * instance of the heap.
 */
function SimpleHeap(f) {
    var data = [];

    // By default just use standard comparison
    var compareFunc = f;
    if (!compareFunc) {
        compareFunc = function(a,b) {
            if (a < b) return -1;
            return 1;
        }
    }

    this.insert = function(x) {
        data.push(x);
        data.sort(compareFunc);
    }

    this.pop = function() {
        return data.pop();
    }

    this.peek = function() {
        return data[data.length - 1];
    }

    this.size = function() {
        return data.length;
    }

    SimpleHeap.prototype.toString = function() {
        return data.toString();
    }
}

/**
 * Exercise 2.1: Given a list of integers, use a heap to find the largest
 * value in the list.
 *
 * Use the SimpleHeap implementation
 *
 * Time Complexity:
 * Space Complexity:
 *
 * @param{number[]} arr
 * @return{number}
 */
var findMax = function(arr) {
    // INSERT YOUR CODE HERE
}

/**
 * Exercise 2.2: Given a list of integers, use a heap to sort the list.
 *
 * Use the SimpleHeap implementation
 *
 * Time Complexity:
 * Space Complexity:
 *
 * @param{number[]} arr
 * @return{number[]}
 */
var heapSort = function(arr) {
    // INSERT YOUR CODE HERE
}

/**
 * Exercise 2.3: Find the k-th largest element in a stream of integers
 *
 * Time Complexity:
 * Space Complexity:
 *
 * @param {number} k
 * @param {number[]} nums
 */
var KthLargest = function(k, nums) {
    // INSERT YOUR CODE HERE
};

/**
 * Add the next value in the stream
 *
 * Time Complexity:
 * Space Complexity:
 *
 * @param {number} val
 * @return {number}
 */
KthLargest.prototype.add = function(val) {
    // INSERT YOUR CODE HERE
};

/**
 * Exercise 2.4: Find the k closest points to the origin
 *
 * Time Complexity:
 * Space Complexity:
 *
 * @param{number[][]} points
 * @param{number} k
 * @return{number[][]}
 */
var kClosest = function(points, k) {
    // INSERT YOUR CODE HERE
}

/**
 * Exercise 3.1: Find the median of a data stream
 */
var MedianFinder = function() {
    // INSERT YOUR CODE HERE
};

/**
 * Add the next number from the stream
 *
 * Time Complexity:
 * Space Complexity:
 *
 * @param {number} num
 * @return {void}
 */
MedianFinder.prototype.addNum = function(num) {
    // INSERT YOUR CODE HERE
};

/**
 * Get the median
 *
 * Time Complexity:
 * Space Complexity:
 *
 * @return {number}
 */
MedianFinder.prototype.findMedian = function() {
    // INSERT YOUR CODE HERE
};

/**
 * Exercise 3.2: Merge k sorted lists
 *
 * Time Complexity:
 * Space Complexity:
 *
 * @param{ListNode[]} lists
 * @return{ListNode}
 */
var mergeKLists = function(lists) {
    // INSERT YOUR CODE HERE
}

/**
 * Simple node class
 */
function ListNode(val) {
    this.val = val;
    this.next = null;
}

// Test cases
var tester = function() {
    // ADD YOUR TEST CASES HERE
}

tester();
