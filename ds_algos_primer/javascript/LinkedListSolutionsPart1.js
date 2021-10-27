/**
 *   Title: LinkedListSolutionsPart1
 *
 *   This file contains the solutions for Exercise Set #1 of the Linked List
 *   exercises in the DS & Algos Primer. If you have not already attempted these
 *   exercises, we highly recommend you complete them before reviewing the
 *   solutions here.
 *
 *   Execution: node LinkedListSolutionsPart1.js
 */

/**
 * Exercise 1.1: Implement a singly-linked list
 */
function SinglyLinkedList() {

    /**
     * A simple node class
     *
     * @param{number} n
     */
    function SinglyLinkedListNode(n) {
        this.val = n;
        this.next = null;
    }

    // The head of our linked list
    this.head = null;
    this.length = 0;

    /**
     * Insert new node at the head of the list
     *
     * @param{number} n
     */
    this.insert = function(n) {
        var newNode = new SinglyLinkedListNode(n);

        // Since we're inserting this at the head, it doesn't matter whether
        // head is null or not. We just create a new node and point it to
        // the previous head
        newNode.next = this.head;
        this.head = newNode;

        // Efficient to track size as we add and remove nodes
        this.length++;
    }

    /**
     * Delete the first occurrence of n from the list
     *
     * @param{number} n
     * @return{boolean}
     */
    this.delete = function(n) {
        // If the list is empty, there's nothing to remove
        if (this.length == 0) return false;

        // To remove a node, we simply remove the pointer to it. That means
        // we'll want to point the node before it (prev) to the one after
        // it (curr.next). We don't have a previous pointer, so we need to
        // track the previous node
        var prev = null;
        var curr = this.head;

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

    /**
     * Return the number of items in the list
     *
     * @return{number}
     */
    this.size = function() {
        return this.length;
    }

    /**
     * Convert the list to a string
     *
     * @return{string}
     */
    SinglyLinkedList.prototype.toString = function() {
        var curr = this.head;
        var result = [];

        // Iterate over the list and add everything to the string
        while (curr != null) {
            result.push(curr.val);
            result.push(" -> ");
            curr = curr.next;
        }
        result.push("null");
        return result.join("");
    }
}

/**
 * Exercise 1.2: Implement a singly-linked list
 */
function DoublyLinkedList() {

    /**
     * A simple node class
     *
     * @param{number} n
     */
    function DoublyLinkedListNode(n) {
        this.val = n;
        this.prev = null;
        this.next = null;
    }

    this.head = null;
    this.tail = null;
    this.length = 0;

    /**
     * Insert new node at the head of the list
     *
     * @param{number} n
     */
    this.insert = function(n) {
        var newNode = new DoublyLinkedListNode(n);
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

    /**
     * Delete the first occurrence of n from the list
     *
     * @param{number} n
     * @return{boolean}
     */
    this.delete = function(n) {
        // If the list is empty we can't remove anything
        if (this.length == 0) return false;

        var curr = this.head;
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

    /**
     * Return the number of items in the list
     *
     * @return{number}
     */
    this.size = function() {
        return this.length;
    }

    /**
     * Convert the list to a string
     *
     * @return{string}
     */
    DoublyLinkedList.prototype.toString = function() {
        if (this.size == 0) return "null";

        var curr = this.head;
        var result = [];
        result.push("null <- ");

        // Iterate over the list and add everything to the string
        while (curr.next != null) {
            result.push(curr.val);
            result.push(" <-> ");
            curr = curr.next;
        }
        result.push(curr.val + " -> null");
        return result.join("");
    }
}

// Test cases
var tester = function() {
    var l = new SinglyLinkedList();
    for (var i = 6; i > 0; i--) l.insert(i);
    console.log(l.toString());
    console.log(l.delete(1));
    console.log(l.delete(4));
    console.log(l.toString());
    console.log(l.size());

    var d = new DoublyLinkedList();
    for (var i = 6; i > 0; i--) d.insert(i);
    console.log(d.toString());
    console.log(d.delete(1));
    console.log(d.delete(4));
    console.log(d.toString());
    console.log(d.size());
}

tester();
