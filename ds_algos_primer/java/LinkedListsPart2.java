/*
 *   Title: LinkedListsPart2
 *
 *   This file contains the template for Exercise Sets #2-5 of the Linked List
 *   exercises in the DS & Algos Primer. Fill in the exercises here and refer to
 *   LinkedListSolutionsPart2.java for the complete code samples.
 *
 *   Execution: javac LinkedListsPart2.java && java -ea LinkedListsPart2
 */

import java.util.*;

public class LinkedListSolutionsPart2 {

    // A simple singly-linked node class (copied from Part 1)
    public static class SinglyLinkedListNode {
        int val;
        SinglyLinkedListNode next;

        public SinglyLinkedListNode(int n) {
            this.val = n;
        }
    }

    // A simple singly-linked node class (copied from Part 1)
    public static class DoublyLinkedListNode {
        int val;
        DoublyLinkedListNode prev;
        DoublyLinkedListNode next;

        public DoublyLinkedListNode(int n) {
            this.val = n;
        }
    }

    // Exercise 2.1: Write a function that swaps two nodes in a doubly-linked
    // list
    public static void swapNodes(DoublyLinkedListNode l, int n, int m) {
        // INSERT YOUR CODE HERE
    }

    // Exercise 2.2: Write a function that removes the odd-indexed values from a
    // singly-linked list
    public static void removeOdd(SinglyLinkedListNode l) {
        // INSERT YOUR CODE HERE
    }

    // Exercise 2.3: Write a function that de-interleaves the even and odd
    // indices in a singly-linked list. Your resulting list should have all the
    // even indices first followed by all the odd indices.
    public static void deinterleave(SinglyLinkedListNode l) {
        // INSERT YOUR CODE HERE
    }

    // Exercise 2.4: Write a function that reverses a singly-linked list
    public static SinglyLinkedListNode reverse(SinglyLinkedListNode l) {
        // INSERT YOUR CODE HERE
    }

    // Exercise 3.1: Write a function that compares 2 singly-linked lists and
    // returns true if the two lists are identical
    public static boolean areEqual(SinglyLinkedListNode l1, SinglyLinkedListNode l2) {
        // INSERT YOUR CODE HERE
    }

    // Exercise 3.2: Write a function that returns the nth-to-last value in a
    // singly-linked list
    public static SinglyLinkedListNode nthToLast(SinglyLinkedListNode l, int n) {
        // INSERT YOUR CODE HERE
    }

    // Exercise 3.3: Write a function that returns the value at the midpoint of
    // a singly-linked list. You can assume the length of the list is odd.
    public static SinglyLinkedListNode midpoint(SinglyLinkedListNode l) {
        // INSERT YOUR CODE HERE
    }

    // Exercise 4.1: Remove all occurrences of n from a singly-linked list
    public static SinglyLinkedListNode removeAll(SinglyLinkedListNode l, int n) {
        // INSERT YOUR CODE HERE
    }

    // Exercise 5.1: Given a singly-linked list, determine if the list contains
    // a cycle. DO NOT use Floyd’s algorithm. FInd some other method for
    // identifying a cycle
    public static boolean hasCycleNaive(SinglyLinkedListNode l) {
        // INSERT YOUR CODE HERE
    }

    // Exercise 5.2: Given a singly-linked list, determine if the list contains
    // a cycle using Floyd's algorithm
    public static boolean hasCycle(SinglyLinkedListNode l) {
        // INSERT YOUR CODE HERE
    }


    // We've included some helper methods below that you can use for your tests

    // Test method to generate singly linked list with n items
    public static SinglyLinkedListNode singleGenerator(int n) {
        SinglyLinkedListNode head = new SinglyLinkedListNode(1);
        SinglyLinkedListNode curr = head;
        for (int i = 2; i <= n; i++) {
            curr.next = new SinglyLinkedListNode(i);
            curr = curr.next;
        }
        return head;
    }

    // Test method to generate doubly linked list with n items
    public static DoublyLinkedListNode doubleGenerator(int n) {
        DoublyLinkedListNode head = new DoublyLinkedListNode(1);
        DoublyLinkedListNode curr = head;
        for (int i = 2; i <= n; i++) {
            curr.next = new DoublyLinkedListNode(i);
            curr.next.prev = curr;
            curr = curr.next;
        }
        return head;
    }

    // Test method to print singly linked list
    public static void printSingle(SinglyLinkedListNode n) {
        SinglyLinkedListNode curr = n;
        while (curr != null) {
            System.out.print(curr.val + " -> ");
            curr = curr.next;
        }
        System.out.println("null");
    }

    // Test method to print doubly linked list
    public static void printDouble(DoublyLinkedListNode n) {
        if (n == null) System.out.println("null");
        DoublyLinkedListNode curr = n;
        System.out.print("null <- ");
        while (curr.next != null) {
            System.out.print(curr.val + " <-> ");
            curr = curr.next;
        }
        System.out.println(curr.val + " -> null");
    }

    // Test cases
    public static void main(String[] args) {
        // INSERT TEST CASES HERE
    }
}
