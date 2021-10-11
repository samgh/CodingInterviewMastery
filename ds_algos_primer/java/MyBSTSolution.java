/*
 *  Title: MyBSTSolution
 *
 *  This is a simple implementation of a binary search tree in Java
 *
 *  Execution: javac MyBSTSolution.java && java -ea MyBSTSolution
 */

import java.util.*;

public class MyBSTSolution<E> {

    // The root of the tree
    private Node<E> root;
    private int size;
    private int height;

    // Internal node class
    private class Node<E> {
        E data;

        // The child nodes
        Node<E> left;
        Node<E> right;

        private Node(E e) {
            this.data = e;
        }
    }

    // Constructor. Nothing to initialize, vars default to null/0
    public MyBSTSolution() {}

    // Add a new value to the tree. We must maintain the ordering of the tree
    public void add(E e) {
        if (e == null) return;

        Node newNode = new Node(e);

        // We'll track the depth so that we can update the height
        int depth = 1;

        // If tree is empty, this becomes the root. Otherwise find the right
        // place for it
        if (root == null) {
            root = newNode;
        } else {
            // Keep traversing through the tree until we find an empty spot for
            // our node
            Node curr = root;
            while (true) {
                // Node needs to go on the left side. If left child is null
                // we can just add it there. Otherwise we need to continue into
                // the left subtree. If the node is larger, then we do the same
                // on the right subtree
                if (e < curr.data) {
                    if (curr.left == null) {
                        curr.left = newNode;
                        break;
                    } else {
                        curr = curr.left;
                        depth++;
                    }
                } else {
                    if (curr.right == null) {
                        curr.right = newNode;
                        break;
                    } else {
                        curr = curr.right;
                        depth++;
                    }
                }
            }
        }

        size++;
        height = Math.max(height, depth);
    }

    // Check whether the tree contains the value. Because this is a BST we don't
    // need to search the entire tree. We know which subtree our result must be
    // in if it's in the tree at all
    public boolean contains(E e) {
        if (e == null) return false;

        Node curr = root;

        // Iteratively look left or right until we find the value or reach a
        // null node
        while (curr != null) {
            // If we round the value, return
            if (curr.data == e) return true;

            // Otherwise, look left if the value is smaller than curr and right
            // if the value is larger
            if (e < curr.data) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
    }

    // Get the max height of the tree
    public int height() {
        return height;
    }

    // Remove value from the tree. If it occurs multiple times, remove a single
    // occurrence of the value
    public E remove(E e) {
        // Check whether value is in tree at all
        if (!this.contains(e)) return null;

        // First find the parent of the node we want to replace
        Node parent = null;
        Node curr = root;
        while (curr != null) {
            if (curr.data == e) break;

            parent = curr;
            if (curr.data < e) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }

        // We need to remove curr and replace it with the next larger or smaller
        // element in the tree, since that allows us to avoid having to reorg
        // any other nodes. We have 3 possiblilites:
        //   1. Curr is a leaf node. This is easy, we just remove it
        //   2. Curr has a single child. This is also easy, we just replace curr
        //      with the child
        //   3. Curr has 2 children. In this case we need to iterate over one of
        //      the children to find the next largest or next smallest node
        if (curr.left == null && curr.right == null) {
            // Update parent to no longer point to curr
            if (curr.data < parent.data) parent.left = null;
            else parent.right = null;
            return curr.data;
        } else if (curr.left == null) {

        }
    }

    // Get the number of elements in the tree
    public int size() {
        return size;
    }

    public String toString() {
    }

    // Main method. Runs a series of basic tests
    public static void main(String[] args) {
    }
}
