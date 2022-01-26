/**
 *   Title: StackAndQueueSolutions.js
 *
 *   This file contains the solutions for Exercise Sets of the Stack & Queue
 *   exercises in the DS & Algos Primer. If you have not already attempted these
 *   exercises, we highly recommend you complete them before reviewing the
 *   solutions here.
 *
 *   Execution: node StackAndQueueSolutions.js
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

    var head = null;
    var size = 0;

    /**
     * Add an item to the top of the stack
     *
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     * @param{number} val
     */
    this.push = function(val) {
        // Add the new value to the front of the Linked List
        var newNode = new Node(val);
        newNode.next = head;
        head = newNode;
        size++;
    }

    /**
     * Remove an item from the top of the stack
     *
     * Time Complexity: O(1)
     * Space Complextiy: O(1)
     *
     * @return{number}
     */
    this.pop = function() {
        if (size == 0) throw "Stack is empty";

        // Remove the first item from the Linked List
        var curr = head;
        head = head.next;
        size--;

        return curr.val;
    }

    /*
     * Get the size of the stack
     *
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     * @return{number}
     */
    this.size = function() {
        return size;
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

    var head = null;
    var tail = null;
    var size = 0;

    /**
     * Add an item to the beginning of the queue
     *
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     * @param{number} val
     */
    this.enqueue = function(val) {
        var newNode = new Node(val);

        // If this is the first node, initialize head and tail
        if (size == 0) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }

        size++;
    }

    /**
     * Remove an item from the end of the queue
     *
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     * @return{number}
     */
    this.dequeue = function() {
        if (size == 0) throw "Queue is empty";

        var curr = tail;
        // If this is the last node, set head and tail to null
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            tail = curr.prev;
            tail.next = null;
        }

        size--;
        return curr.val;
    }

    /**
     * Get the size of the stack
     *
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     * @return{number}
     */
    this.size = function() {
        return size;
    }
}

/**
 * Exercise 1.3: Given a stack, find the nth element in the stack
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * @param{number[]} stack
 * @param{number} n
 * @return{number}
 */
var nthElementInStack = function(stack, n) {
    if (n > stack.length) throw "n out of bounds";

    // Pop the first n-1 elements off the stack
    tempStack = [];
    for (var i = 1; i < n; i++) {
        tempStack.push(stack.pop());
    }

    var result = stack[stack.length-1];

    // Put the removed elements back onto the stack
    while (tempStack.length > 0) {
        stack.push(tempStack.pop());
    }

    return result;
}

/**
 * Exercise 1.4: Implement a Stack using Queues
 */

function StackFromQueues() {
    var data = [];

    /**
     * Push an item onto the stack
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param{number} x
     */
    this.push = function(x) {
        // We need to insert the value as the first item in the queue. We
        // can do that by copying everything into a new queue
        var newData = [];

        // Add the value
        newData.push(x);

        // Add everything else behind it in the queue
        while (data.length > 0) {
            newData.push(data.shift());
        }

        // Update our data to point to the new queue
        data = newData;
    }

    /**
     * Pop an item from the stack
     *
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     * @return{number}
     */
    this.pop = function() {
        return data.shift();
    }

    /**
     * Return the top item on the stack
     *
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     * @return{number}
     */
    this.top = function() {
        return data[0];
    }

    /**
     * Is the stack empty
     *
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     * @return{boolean}
     */
    this.empty = function() {
        return data.length == 0;
    }
}

/**
 * Exercise 2.1: Valid Parentheses
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * @param{string} s
 * @return{boolean}
 */
var validParentheses = function(s) {
    // We need to know which open parens match which close parens
    var matchingParens = {
        '(': ')',
        '[': ']',
        '{': '}'
    };

    // Use a stack to keep track of all the open parens
    var openParens = [];

    // If the current char is an open paren, add it to the stack. Otherwise
    // for the string to be valid, the char needs to be the matching paren
    // for the top char on the stack
    for (var i = 0; i < s.length; i++) {
        var c = s[i];
        if (c == '(' || c == '[' || c == '{') {
            openParens.push(c);
        } else if (openParens.length == 0) {
            // If the stack is empty, then there are too many close parens
            return false;
        } else {
            var open = openParens.pop();
            if (matchingParens[open] != c) return false;
        }
    }

    return openParens.length == 0;
}

/**
 * Exercise 2.2: Basic Calculator
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * @param{string} s
 * @return{number}
 */
var basicCalculator = function(s) {
    calc = [];

    // Track the running sum and the sign of the next value
    var sum = 0;
    var sign = 1;

    // Iterate over the string
    for (var i = 0; i < s.length; i++) {
        var curr = s[i];

        // If the character is a digit, we get the whole number and then add
        // or subtract it from our sum based on the current sign
        if (curr >= '0' && curr <= '9') {
            // Get the full number. It can be more than 1 digit
            var endOfNumber = i;
            while (endOfNumber+1 < s.length && s[endOfNumber+1] >= '0' && s[endOfNumber+1] <= '9') {
                endOfNumber++;
            }
            var number = parseInt(s.substring(i, endOfNumber+1));

            // Sign tells us whether to add or subtract the current number
            sum += number * sign;

            // If we took multiple digits, update the position of i
            i = endOfNumber;
        }

        // If we see a +/-, update the sign accordingly
        if (curr == '+') {
            sign = 1;
        }

        if (curr == '-') {
            sign = -1;
        }

        // If we see an open paren, we just save our current sum and sign
        // and then start a new computation. We essentially compute the inner
        // sum first, and then pop the sum from before from our stack to
        // combine them together
        if (curr == '(') {
            calc.push([sum, sign]);
            sum = 0;
            sign = 1;
        }

        // If we find a close paren, we combine our current sum with the
        // previous sum from outside the parens
        if (curr == ')') {
            var prevState = calc.pop();
            sign = prevState[1];
            sum = prevState[0] + sum * sign;
        }
    }

    return sum;
}

// Test cases
var tester = function() {
    var s = new MyStack();
    s.push(1);
    s.push(2);
    s.push(3);
    console.log(s.pop());
    s.push(4);
    console.log(s.pop());
    console.log(s.pop());
    console.log(s.size());

    var q = new MyQueue();
    q.enqueue(1);
    q.enqueue(2);
    q.enqueue(3);
    console.log(q.dequeue());
    q.enqueue(4);
    console.log(q.dequeue());
    console.log(q.dequeue());
    console.log(q.size());

    var stack = [1,2,3,4,5];
    console.log(nthElementInStack(stack, 2));

    var sfq = new StackFromQueues();
    sfq.push(1);
    sfq.push(2);
    sfq.push(3);
    console.log(sfq.pop());
    console.log(sfq.top());

    console.log(validParentheses("([]{}(()))"));
    console.log(validParentheses("(]"));

    console.log(basicCalculator("1 + 1"));
    console.log(basicCalculator("12 - (2 + 3) + 4"));
}

tester();
