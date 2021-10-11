/*
 *  Title: MyLinkedListSolution
 *  Reference: https://docs.oracle.com/javase/8/docs/api/java/util/LinkedList.html
 *
 *  This is a simple LinkedList implementation based on the Java 8 standard
 *
 *  Execution: javac MyLinkedListSolution.java && java -ea MyLinkedListSolution
 */

import java.util.*;

public class MyLinkedListSolution<E> {

    // With doubly linked lists, it makes sense to have a tail pointer
    private Node<E> head;
    private Node<E> tail;
    private int size;

    // Internal node class
    private class Node<E> {
        E data;

        // This is a doubly linked list so we have both next and prev pointers
        Node<E> next;
        Node<E> prev;

        private Node(E e) {
            this.data = e;
        }
    }

    // Constructor. Nothing to initialize, vars default to null/0
    public MyLinkedListSolution() {}

    // Add an element to the end of the list
    public boolean add(E e) {
        Node<E> newNode = new Node<E>(e);

        // If the list is empty, this is the only node. Otherwise we update the
        // previous tail of the list to point to our new node
        if (this.head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }

        size++;
        return true;
    }

    // Does the list contain object
    public boolean contains(Object o) {
        if (this.indexOf(o) >= 0) return true;
        return false;
    }

    // Get the element at index
    // If index < 0 || index >= this.size() throws IndexOutOfBoundsException
    public E get(int index) {
        if (index < 0 || index >= this.size()) throw new IndexOutOfBoundsException();

        Node<E> curr = this.head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }

        return curr.data;
    }

    // Get the index of object. If it doesn't exist, return -1
    public int indexOf(Object o) {
        if (o == null) return -1;

        Node<E> curr = this.head;
        int idx = 0;

        while (curr != null) {
            if (o.equals(curr.data)) return idx;
            curr = curr.next;
            idx++;
        }

        return -1;
    }

    // Remove element at index from list and return element
    // If index < 0 || index >= this.size() throws IndexOutOfBoundsException
    public E remove(int index) {
        if (index < 0 || index >= this.size()) throw new IndexOutOfBoundsException();

        Node<E> curr = null;

        if (index == 0) {
            curr = this.head;
            this.head = this.head.next;
            this.head.prev = null;
        } else if (index == this.size()-1) {
            curr = this.tail;
            this.tail = this.tail.prev;
            this.tail.next = null;
        } else {
            curr = this.head;

            for (int i = 0; i < index; i++) {
                curr = curr.next;
            }

            curr.prev.next = curr.next;
            curr.next.prev = curr.prev;
        }

        size--;

        return curr.data;
    }

    // Get the number of elements in the list
    public int size() {
        return size;
    }

    // Convert list to an array
    public Object[] toArray() {
        Object[] arr = new Object[this.size()];

        Node<E> curr = head;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (Object) curr.data;
            curr = curr.next;
        }

        return arr;
    }

    // Convert the list to a string in the following format:
    //  this.size() == 0 => "null"
    //  this.size() == 1 => "value"
    //  this.size() > 1 => "value1 <-> value2 <-> value3"
    public String toString() {
        if (this.size() == 0) return "null";

        StringBuilder sb = new StringBuilder();

        Node<E> curr = head;

        while (curr.next != null) {
            sb.append(curr.data);
            sb.append(" <-> ");
            curr = curr.next;
        }

        sb.append(curr.data);
        return sb.toString();
    }

    // Main method. Runs a series of basic tests
    public static void main(String[] args) {
        MyLinkedListSolution<Integer> l = new MyLinkedListSolution<>();
        l.add(1);
        assert l.toString().equals("1");
        assert l.size() == 1;
        assert l.contains(1);
        assert !l.contains(2);
        assert l.indexOf(1) == 0;
        assert l.indexOf(2) == -1;
        assert l.get(0) == 1;
        l.add(2);
        l.add(3);
        assert l.remove(1) == 2;
        assert l.toString().equals("1 <-> 3");
        assert l.size() == 2;
        assert l.contains(3);
        assert !l.contains(2);
        assert l.indexOf(3) == 1;
        assert l.indexOf(2) == -1;
        assert l.get(1) == 3;

        // ADD TEST CASES HERE

        System.out.println("Passed test cases");
    }
}
