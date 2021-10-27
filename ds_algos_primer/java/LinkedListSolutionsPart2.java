/*
 *   Title: LinkedListSolutionsPart2
 *
 *   This file contains the solutions for Exercise Sets #2-5 of the Linked List
 *   exercises in the DS & Algos Primer. If you have not already attempted these
 *   exercises, we highly recommend you complete them before reviewing the
 *   solutions here.
 *
 *   Execution: javac LinkedListSolutionsPart2.java && java -ea LinkedListSolutionsPart2
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

    // A simple doubly-linked node class (copied from Part 1)
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
        if (n < 0 || m < 0) throw new IndexOutOfBoundsException();
        if (n == m) return;

        // First get pointers to the nodes at the two indices we want to swap
        DoublyLinkedListNode nNode = l;
        DoublyLinkedListNode mNode = l;
        DoublyLinkedListNode curr = l;
        for (int i = 0; i <= Math.max(m, n); i++) {
            // If we reach the end of the list, then one of our indices is out
            // of bounds
            if (curr == null) throw new IndexOutOfBoundsException();
            if (i == n) nNode = curr;
            if (i == m) mNode = curr;
            curr = curr.next;
        }

        // We're going to swap these pointers in mNode, so we need to save them
        // for later
        DoublyLinkedListNode mNodePrev = mNode.prev;
        DoublyLinkedListNode mNodeNext = mNode.next;

        // Insert mNode into the position where nNode was by swapping the
        // pointers from other nodes to mNode and the pointers from mNode to
        // point to those before and after nNode
        mNode.prev = nNode.prev;
        mNode.next = nNode.next;
        if (mNode.prev != null) mNode.prev.next = mNode;
        if (mNode.next != null) mNode.next.prev = mNode;

        // Do the same for nNode
        nNode.prev = mNodePrev;
        nNode.next = mNodeNext;
        if (nNode.prev != null) nNode.prev.next = nNode;
        if (nNode.next != null) nNode.next.prev = nNode;
    }

    // Exercise 2.2: Write a function that removes the odd-indexed values from a
    // singly-linked list
    public static void removeOdd(SinglyLinkedListNode l) {
        SinglyLinkedListNode curr = l;
        while (curr != null && curr.next != null) {
            curr.next = curr.next.next;
            curr = curr.next;
        }
    }

    // Exercise 2.3: Write a function that de-interleaves the even and odd
    // indices in a singly-linked list. Your resulting list should have all the
    // even indices first followed by all the odd indices.
    public static void deinterleave(SinglyLinkedListNode l) {
        if (l == null || l.next == null) return;

        // We will start by separating the nodes into two separate lists. Then
        // it is easy to combine one with the other
        SinglyLinkedListNode curr = l;

        // evens and odds track the current end node of the list of even and
        // odd nodes respectively
        SinglyLinkedListNode evens = l;
        SinglyLinkedListNode odds = l.next;

        // We need to keep track of the beginning of the odds so we can point
        // the end of evens to the beginning of odds once we're done separating
        // them
        SinglyLinkedListNode oddsStart = odds;

        // Track whether the current index is even or odd
        boolean isEven = true;

        while (curr != null) {
            SinglyLinkedListNode next = curr.next;

            // Depending whether the index is even or odd, we add the current
            // node to the end of the evens or odds list
            if (isEven) {
                evens.next = curr;
                evens = evens.next;
            } else {
                odds.next = curr;
                odds = odds.next;
            }
            curr = next;
            isEven = !isEven;
        }

        // Make sure that if the last value of the list is even, we point the
        // end of the list to null
        odds.next = null;

        // Merge the two lists together
        evens.next = oddsStart;
    }

    // Exercise 2.4: Write a function that reverses a singly-linked list
    public static SinglyLinkedListNode reverse(SinglyLinkedListNode l) {
        // Track the previous because we will point the current node back
        SinglyLinkedListNode prev = null;
        SinglyLinkedListNode curr = l;

        // For each node, switch it to point to the node in front of it. The
        // order of steps here is very important
        while (curr != null) {
            SinglyLinkedListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }

    // Exercise 3.1: Write a function that compares 2 singly-linked lists and
    // returns true if the two lists are identical
    public static boolean areEqual(SinglyLinkedListNode l1, SinglyLinkedListNode l2) {
        // Iterate over both lists simultaneously and compare each value
        while (l1 != null && l2 != null) {
            if (l1.val != l2.val) return false;
            l1 = l1.next;
            l2 = l2.next;
        }

        // If there are no values that don't match, the lists are equal
        return true;
    }

    // Exercise 3.2: Write a function that returns the nth-to-last value in a
    // singly-linked list
    public static SinglyLinkedListNode nthToLast(SinglyLinkedListNode l, int n) {
        // We will use a fast and slow pointer that are exactly n nodes apart.
        // When fast reaches the end of the list, slow will be at the nth-to-last
        // element
        SinglyLinkedListNode fast = l;
        SinglyLinkedListNode slow = l;

        // Advance fast pointer to the nth position
        for (int i = 0; i < n; i++) {
            if (fast == null) throw new IndexOutOfBoundsException();
            fast = fast.next;
        }

        // Increment both pointers together
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }

    // Exercise 3.3: Write a function that returns the value at the midpoint of
    // a singly-linked list. You can assume the length of the list is odd.
    public static SinglyLinkedListNode midpoint(SinglyLinkedListNode l) {
        if (l.next == null) return l;

        // We're using a fast and slow pointer again to find the middle
        SinglyLinkedListNode fast = l.next;
        SinglyLinkedListNode slow = l;

        // Advance the fast pointer twice as fast as the slow pointer. That way
        // when it reaches the end, the slow pointer will be in the middle
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    // Exercise 4.1: Remove all occurrences of n from a singly-linked list
    public static SinglyLinkedListNode removeAll(SinglyLinkedListNode l, int n) {
        // Use a dummy node to make it easy if we have to remove the first node
        // in the list
        SinglyLinkedListNode dummy = new SinglyLinkedListNode(0);
        SinglyLinkedListNode curr = dummy;
        dummy.next = l;

        // Iterate over the list
        while (curr != null) {
            // If the next value needs to be removed, skip it. We may need to
            // do this multiple times if there are multiple of the value in a
            // row
            while (curr.next != null && curr.next.val == n) {
                curr.next = curr.next.next;
            }
            curr = curr.next;
        }

        // dummy.next points to the current head of the list
        return dummy.next;
    }

    // Exercise 5.1: Given a singly-linked list, determine if the list contains
    // a cycle. DO NOT use Floyd’s algorithm. FInd some other method for
    // identifying a cycle
    public static boolean hasCycleNaive(SinglyLinkedListNode l) {
        // Add all nodes to a set and see if we visit them more than once
        Set<SinglyLinkedListNode> visited = new HashSet<>();

        // Iterate over the list
        while (l != null) {
            // If we've already visited the node, we have a cycle
            if (visited.contains(l)) return true;

            // Otherwise add it to the set and continue iterating
            visited.add(l);
            l = l.next;
        }

        // If we get to the end of the list there is no cycle
        return false;
    }

    // Exercise 5.2: Given a singly-linked list, determine if the list contains
    // a cycle using Floyd's algorithm
    public static boolean hasCycle(SinglyLinkedListNode l) {
        if (l == null) return false;

        // Initialize a slow pointer and a fast pointer
        SinglyLinkedListNode slow = l;
        SinglyLinkedListNode fast = l.next;

        // Fast increments by 2 and slow increments by 1. If they are ever
        // equal then there is a cycle. Otherwise if fast reaches the end of
        // the list there is no cycle
        while (fast != null && fast.next != null) {
            if (slow == fast) return true;
            slow = slow.next;
            fast = fast.next.next;
        }

        return false;
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
        DoublyLinkedListNode d = doubleGenerator(4);
        swapNodes(d, 1, 3);
        printDouble(d);

        SinglyLinkedListNode l = singleGenerator(4);
        removeOdd(l);
        printSingle(l);

        l = singleGenerator(9);
        deinterleave(l);
        printSingle(l);

        l = singleGenerator(5);
        l = reverse(l);
        printSingle(l);

        System.out.println(areEqual(singleGenerator(5), singleGenerator(5)));

        System.out.println(nthToLast(singleGenerator(5), 2).val);

        System.out.println(midpoint(singleGenerator(5)).val);

        l = new SinglyLinkedListNode(1);
        l.next = new SinglyLinkedListNode(1);
        l.next.next = new SinglyLinkedListNode(2);
        l.next.next.next = new SinglyLinkedListNode(3);
        l.next.next.next.next = new SinglyLinkedListNode(1);
        printSingle(removeAll(l, 1));

        l = singleGenerator(5);
        l.next.next.next.next.next = l.next.next;
        System.out.println(hasCycleNaive(l));
        System.out.println(hasCycle(l));
    }
}
