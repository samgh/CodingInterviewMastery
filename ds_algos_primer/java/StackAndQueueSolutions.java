/*
 *   Title: Stack & Queue Solutions
 *
 *   This file contains the solutions for the Stack & Queue exercises in the
 *   DS & Algos Primer. If you have not already attempted these exercises, we
 *   highly recommend you complete them before reviewing the solutions here.
 *
 *   Execution: javac StackAndQueueSolutions.java && java -ea StackAndQueueSolutions
 */

import java.util.*;

public class StackAndQueueSolutions {

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

        private Node head;
        private int size;

        /*
         * Constructor
         */
        public MyStack() {}

        /*
         * Add an item to the top of the stack
         *
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         */
        public void push(int val) {
            // Add the new value to the front of the Linked List
            Node newNode = new Node(val);
            newNode.next = this.head;
            this.head = newNode;
            this.size++;
        }

        /*
         * Remove an item from the top of the stack
         *
         * Time Complexity: O(1)
         * Space Complextiy: O(1)
         */
        public int pop() {
            if (size == 0) throw new IndexOutOfBoundsException();

            // Remove the first item from the Linked List
            Node curr = this.head;
            this.head = this.head.next;
            this.size--;

            return curr.val;
        }

        /*
         * Get the size of the stack
         *
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         */
        public int size() {
            return this.size;
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

        private Node head;
        private Node tail;
        private int size;

        /*
         * Constructor
         */
        public MyQueue() {}

        /*
         * Add an item to the beginning of the queue
         *
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         */
        public void enqueue(int val) {
            Node newNode = new Node(val);

            // If this is the first node, initialize head and tail
            if (this.size == 0) {
                head = newNode;
                tail = newNode;
            } else {
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            }

            this.size++;
        }

        /*
         * Remove an item from the end of the queue
         *
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         */
        public int dequeue() {
            if (this.size == 0) throw new IndexOutOfBoundsException();

            Node curr = tail;
            // If this is the last node, set head and tail to null
            if (this.size == 1) {
                head = null;
                tail = null;
            } else {
                tail = curr.prev;
                tail.next = null;
            }

            size--;
            return curr.val;
        }

        /*
         * Get the size of the stack
         *
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         */
        public int size() {
            return this.size;
        }
    }

    /*
     * Exercise 1.3: Given a stack, find the nth element in the stack
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static int nthElementInStack(Stack<Integer> stack, int n) {
        if (n > stack.size()) throw new IndexOutOfBoundsException();

        // Pop the first n-1 elements off the stack
        Stack<Integer> temp = new Stack<>();
        for (int i = 1; i < n; i++) {
            temp.push(stack.pop());
        }

        int result = stack.peek();

        // Put the removed elements back onto the stack
        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }

        return result;
    }

    /*
     * Exercise 1.4: Implement a Stack using Queues
     */
    public static class StackFromQueues {

        private Queue<Integer> data;

        /*
         * Constructor
         *
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         */
        public StackFromQueues() {
            data = new LinkedList<>();
        }

        /*
         * Push an item onto the stack
         *
         * Time Complexity: O(n)
         * Space Complexity: O(1)
         */
        public void push(int x) {
            // We need to insert the value as the first item in the queue. We
            // can do that by copying everything into a new queue
            Queue<Integer> newData = new LinkedList<>();

            // Add the value
            newData.add(x);

            // Add everything else behind it in the queue
            while (!this.data.isEmpty()) {
                newData.add(this.data.remove());
            }

            // Update our data to point to the new queue
            this.data = newData;
        }

        /*
         * Pop an item from the stack
         *
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         */
        public int pop() {
            return this.data.remove();
        }

        /*
         * Return the top item on the stack
         *
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         */
        public int top() {
            return this.data.peek();
        }

        /*
         * Is the stack empty
         *
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         */
        public boolean empty() {
            return this.data.isEmpty();
        }
    }

    /*
     * Exercise 2.1: Valid Parentheses
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static boolean validParentheses(String s) {
        // We need to know which open parens match which close parens
        Map<Character, Character> matchingParens = new HashMap<>();
        matchingParens.put('(', ')');
        matchingParens.put('[', ']');
        matchingParens.put('{', '}');

        // Use a stack to keep track of all the open parens
        Stack<Character> openParens = new Stack<>();

        // If the current char is an open paren, add it to the stack. Otherwise
        // for the string to be valid, the char needs to be the matching paren
        // for the top char on the stack
        for (char c : s.toCharArray()) {
            if (matchingParens.containsKey(c)) {
                openParens.push(c);
            } else if (openParens.isEmpty()) {
                // If the stack is empty, then there are too many close parens
                return false;
            } else {
                char open = openParens.pop();
                if (matchingParens.get(open) != c) return false;
            }
        }

        return openParens.isEmpty();
    }

    /*
     * Exercise 2.2: Basic Calculator
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static int basicCalculator(String s) {
        Stack<Integer> calc = new Stack<>();

        // Track the running sum and the sign of the next value
        int sum = 0;
        int sign = 1;

        // Iterate over the string
        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);

            // If the character is a digit, we get the whole number and then add
            // or subtract it from our sum based on the current sign
            if (Character.isDigit(curr)) {
                // Get the full number. It can be more than 1 digit
                int endOfNumber = i;
                while (endOfNumber+1 < s.length() && Character.isDigit(s.charAt(endOfNumber+1))) {
                    endOfNumber++;
                }
                int number = Integer.parseInt(s.substring(i, endOfNumber+1));

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
                calc.push(sum);
                calc.push(sign);
                sum = 0;
                sign = 1;
            }

            // If we find a close paren, we combine our current sum with the
            // previous sum from outside the parens
            if (curr == ')') {
                sign = calc.pop();
                sum = calc.pop() + sum * sign;
            }
        }

        return sum;
    }

    /*
     * Sample test cases
     */
    public static void main(String[] args) {
        MyStack s = new MyStack();
        s.push(1);
        s.push(2);
        s.push(3);
        System.out.println(s.pop());
        s.push(4);
        System.out.println(s.pop());
        System.out.println(s.pop());
        System.out.println(s.size());

        MyQueue q = new MyQueue();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        System.out.println(q.dequeue());
        q.enqueue(4);
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.size());

        Stack<Integer> stack = new Stack<>();
        for (int i = 5; i > 0; i--) stack.push(i);
        System.out.println(nthElementInStack(stack, 2));

        StackFromQueues sfq = new StackFromQueues();
        sfq.push(1);
        sfq.push(2);
        sfq.push(3);
        System.out.println(sfq.pop());
        System.out.println(sfq.top());

        System.out.println(validParentheses("([]{}(()))"));

        System.out.println(basicCalculator("12 - (2 + 3) + 4"));
    }
}
