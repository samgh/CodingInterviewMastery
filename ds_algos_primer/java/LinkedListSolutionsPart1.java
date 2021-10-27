/*
 *   Title: LinkedListSolutionsPart1
 *
 *   This file contains the solutions for Exercise Set #1 of the Linked List
 *   exercises in the DS & Algos Primer. If you have not already attempted these
 *   exercises, we highly recommend you complete them before reviewing the
 *   solutions here.
 *
 *   Execution: javac LinkedListSolutionsPart1.java && java -ea LinkedListSolutionsPart1
 */

import java.util.*;

public class LinkedListSolutionsPart1 {

    // Exercise 1.1: Implement a singly-linked list

    // Singly linked list class
    public static class SinglyLinkedList {
        // A simple node class
        public static class SinglyLinkedListNode {
            int val;
            SinglyLinkedListNode next;

            public SinglyLinkedListNode(int n) {
                this.val = n;
            }
        }

        // The head of our linked list
        SinglyLinkedListNode head;
        int length;

        // Constructor
        public SinglyLinkedList() {
            // We don't need to do anything for the constructor. head defaults to
            // null and size defaults to 0
        }

        // Insert new node at the head of the list
        public void insert(int n) {
            SinglyLinkedListNode newNode = new SinglyLinkedListNode(n);

            // Since we're inserting this at the head, it doesn't matter whether
            // head is null or not. We just create a new node and point it to
            // the previous head
            newNode.next = head;
            head = newNode;

            // Efficient to track size as we add and remove nodes
            this.length++;
        }

        // Delete the first occurrence of n from the list
        public boolean delete(int n) {
            // If the list is empty, there's nothing to remove
            if (this.length == 0) return false;

            // To remove a node, we simply remove the pointer to it. That means
            // we'll want to point the node before it (prev) to the one after
            // it (curr.next). We don't have a previous pointer, so we need to
            // track the previous node
            SinglyLinkedListNode prev = null;
            SinglyLinkedListNode curr = this.head;

            // Iterate over the list looking for n
            while (curr != null) {
                // If we find the value, remove it
                if (curr.val == n) {
                    // If the value is the first item in our list, we need to
                    // handle this differently
                    if (prev == null) this.head = curr.next;
                    else prev.next = curr.next;

                    // We removed a node so update the size
                    this.length--;
                    return true;
                }

                // Increment prev and curr. DO NOT do "prev.next" because it
                // will fail on the initial pass when prev == null
                prev = curr;
                curr = curr.next;
            }
            return false;
        }

        // Return the number of items in the list
        public int size() {
            return this.length;
        }

        // Convert the list to a string
        public String toString() {
            SinglyLinkedListNode curr = this.head;

            // Remember to use a StringBuilder as we construct a string
            StringBuilder sb = new StringBuilder();

            // Iterate over the list and add everything to the string
            while (curr != null) {
                sb.append(curr.val);
                sb.append(" -> ");
                curr = curr.next;
            }
            sb.append("null");
            return sb.toString();
        }
    }

    // Exercise 1.2: Implement a doubly-linked list

    // Doubly linked list class
    public static class DoublyLinkedList {
        // A simple node class
        public static class DoublyLinkedListNode {
            int val;
            DoublyLinkedListNode prev;
            DoublyLinkedListNode next;

            public DoublyLinkedListNode(int n) {
                this.val = n;
            }
        }

        // We want pointers to both the head and tail
        DoublyLinkedListNode head;
        DoublyLinkedListNode tail;
        int length;

        // Constructor
        public DoublyLinkedList() {}

        // Insert node at the front of the list
        public void insert(int n) {
            DoublyLinkedListNode newNode = new DoublyLinkedListNode(n);
            // If list is empty (head is null) the node becomes head and tail
            if (this.head == null) {
                this.head = newNode;
                this.tail = newNode;
            } else {
                // We need both point the new node to the old head and also
                // point the old head back to the current node
                newNode.next = this.head;
                this.head.prev = newNode;
                this.head = newNode;
            }

            this.length++;
        }

        // Delete the first occurrence of n from the list
        public boolean delete(int n) {
            // If the list is empty we can't remove anything
            if (this.length == 0) return false;

            DoublyLinkedListNode curr = this.head;
            // Find the node in the list. We don't need to track the previous
            // node since we already have a pointer to it
            while (curr != null) {
                if (curr.val == n) {
                    // If it is the first node, update the head
                    if (curr.prev == null) this.head = curr.next;
                    else curr.prev.next = curr.next;

                    // If it is the last node, update the tail
                    if (curr.next == null) this.tail = curr.prev;
                    else curr.next.prev = curr.prev;

                    this.length--;
                    return true;
                }
                curr = curr.next;
            }
            return false;
        }

        // Return the number of items in the list
        public int size() {
            return this.length;
        }

        // Convert the list to a string
        public String toString() {
            if (this.length == 0) return "null";

            DoublyLinkedListNode curr = this.head;

            // Remember to use a StringBuilder as we construct a string
            StringBuilder sb = new StringBuilder();
            sb.append("null <- ");

            // Iterate over the list and add everything to the string
            while (curr.next != null) {
                sb.append(curr.val);
                sb.append(" <-> ");
                curr = curr.next;
            }
            sb.append(curr.val + " -> null");
            return sb.toString();
        }
    }

    // Test cases
    public static void main(String[] args) {
        SinglyLinkedList l = new SinglyLinkedList();
        for (int i = 6; i > 0; i--) l.insert(i);
        System.out.println(l);
        System.out.println(l.delete(1));
        System.out.println(l.delete(4));
        System.out.println(l);
        System.out.println(l.size());

        DoublyLinkedList d = new DoublyLinkedList();
        for (int i = 6; i > 0; i--) d.insert(i);
        System.out.println(d);
        System.out.println(d.delete(1));
        System.out.println(d.delete(4));
        System.out.println(d);
        System.out.println(d.size());
    }
}
