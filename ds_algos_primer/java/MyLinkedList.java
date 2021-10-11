/*
 *  Title: MyLinkedList
 *
 *  Use this template to implement a Doubly Linked List.
 *
 *  When in doubt of the proper function behavior, refer to the Java 8
 *  Standard:
 *  https://docs.oracle.com/javase/8/docs/api/java/util/LinkedList.html
 *
 *  Execution: javac MyLinkedList.java && java -ea MyLinkedList
 */

import java.util.*;

public class MyLinkedList<E> {

    // TODO: Declare any instance variables you need


    // Our LinkedList Nodes are represented by this Node class. Each node should
    // contain:
    // * A generic value
    // * A pointer to the next Node
    // * A pointer to the previous Node
    private class Node<E> {
        private Node(E e) {
            // TODO
        }
    }

    // Constructs an empty list.
    public MyLinkedList() {
        // TODO
    }

    // Appends the specified element to the end of this list.
    public boolean add(E e) {
        // TODO
        return false;
    }

    // Does the list contain object
    public boolean contains(Object o) {
        // TODO
        return false;
    }

    // Get the element at index
    // If index < 0 || index >= this.size() throws IndexOutOfBoundsException
    public E get(int index) {
        return null;
    }

    // Get the index of object. If it doesn't exist, return -1
    public int indexOf(Object o) {
        return 0;
    }

    // Remove element at index from list and return element
    // If index < 0 || index >= this.size() throws IndexOutOfBoundsException
    public E remove(int index) {
        return null;
    }

    // Get the number of elements in the list
    public int size() {
        return 0;
    }

    // Convert list to an array
    public Object[] toArray() {
        return null;
    }

    // Convert the list to a string in the following format:
    //  this.size() == 0 => "null"
    //  this.size() == 1 => "value"
    //  this.size() > 1 => "value1 <-> value2 <-> value3"
    public String toString() {
        return null;
    }

    // Main method. Runs a series of basic tests
    public static void main(String[] args) {

    }
}
