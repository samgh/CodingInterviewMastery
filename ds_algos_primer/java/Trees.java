/*
 *   Title: TreeSolutions
 *
 *   This file contains the template for the Tree exercises in
 *   the DS & Algos Primer. Fill in the exercises here and refer to
 *   ArraysAndStringsSolutions.java for the complete code samples.
 *
 *   Execution: javac TreeSolutions.java && java -ea TreeSolutions
 */

import java.util.*;

public class TreeSolutions {

    /*
     * Exercise 1.1: Implement a Binary Search Tree class
     */
    public static class BinarySearchTree {

        /*
         * Simple TreeNode class
         */
        private static class TreeNode {
            // INSERT YOUR CODE HERE
        }

        // Constructor
        public BinarySearchTree() {
            // INSERT YOUR CODE HERE
        }

        /*
         * Insert a value into the tree (in order)
         *
         * Time Complexity:
         * Space Complexity:
         */
        public void insert(int n) {
            // INSERT YOUR CODE HERE
        }

        /*
         * Delete a value from the BST
         *
         * Time Complexity:
         * Space Complexity:
         */
        public void delete(int n) {
            // INSERT YOUR CODE HERE
        }

        /*
         * Determine if the tree contains n
         *
         * Time Complexity:
         * Space Complexity:
         */
        public boolean contains(int n) {
            // INSERT YOUR CODE HERE
        }

        /*
         * Serialize a BST by converting it into a string
         *
         * Time Complexity:
         * Space Complexity:
         */
        public static String serialize(BinarySearchTree tree) {
            // INSERT YOUR CODE HERE
        }

        /*
         * Deserialize a serialized BST
         *
         * Time Complexity:
         * Space Complexity:
         */
        public static BinarySearchTree deserialize(String serialized) {
            // INSERT YOUR CODE HERE
        }

        /*
         * Convert the tree to a string
         *
         * Time Complexity:
         * Space Complexity:
         */
        public String toString() {
            // INSERT YOUR CODE HERE
        }
    }

    /*
     * Exercise 1.2: Implement a trie class
     */
    public static class Trie {

        /*
         * Simple Trie Node class
         */
        private static class TrieNode {
            // INSERT YOUR CODE HERE
        }

        // Constructor
        public Trie() {
            // INSERT YOUR CODE HERE
        }

        /*
         * Insert a string into our trie
         *
         * Time Complexity:
         * Space Complexity:
         */
        public void insert(String s) {
            // INSERT YOUR CODE HERE
        }

        /*
         * Does trie contain s
         *
         * Time Complexity:
         * Space Complexity:
         */
        public boolean contains(String s) {
            // INSERT YOUR CODE HERE
        }

        /*
         * Find all strings in the trie with a given prefix
         *
         * Time Complexity:
         * Space Complexity:
         */
        public List<String> prefix(String pre) {
            // INSERT YOUR CODE HERE
        }

        /*
         * Convert trie into a string
         *
         * Time Complexity:
         * Space Complexity:
         */
        public String toString() {
            // INSERT YOUR CODE HERE
        }
    }

    /*
     * Exercise 2.1: Implement each tree traversal. You should implement each
     * traversal both recursively and iteratively.
     */

    /*
     * Simple TreeNode class for remaining exercises
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    /*
     * Inorder Traversal - Recursive
     *
     * Time Complexity:
     * Space Complexity:
     */
    public static List<Integer> inOrderTraversalRecursive(TreeNode root) {
        // INSERT YOUR CODE HERE
    }

    /*
     * Inorder Traversal - Iterative
     *
     * Time Complexity:
     * Space Complexity:
     */
    public static List<Integer> inOrderTraversalIterative(TreeNode root) {
        // INSERT YOUR CODE HERE
    }

    /*
     * Preorder Traversal - Recursive
     *
     * Time Complexity:
     * Space Complexity:
     */
    public static List<Integer> preOrderTraversalRecursive(TreeNode root) {
        // INSERT YOUR CODE HERE
    }

    /*
     * Preorder Traversal - Iterative
     *
     * Time Complexity:
     * Space Complexity:
     */
    public static List<Integer> preOrderTraversalIterative(TreeNode root) {
        // INSERT YOUR CODE HERE
    }

    /*
     * Postorder Traversal - Recursive
     *
     * Time Complexity:
     * Space Complexity:
     */
    public static List<Integer> postOrderTraversalRecursive(TreeNode root) {
        // INSERT YOUR CODE HERE
    }

    /*
     * Postorder Traversal - Iterative
     *
     * Time Complexity:
     * Space Complexity:
     */
    public static List<Integer> postOrderTraversalIterative(TreeNode root) {
        // INSERT YOUR CODE HERE
    }

    /*
     * Levelorder Traversal - Recursive
     *
     * Time Complexity:
     * Space Complexity:
     */
    public static List<Integer> levelOrderTraversalRecursive(TreeNode root) {
        // INSERT YOUR CODE HERE
    }

    /*
     * Levelorder Traversal - Iterative
     *
     * Time Complexity:
     * Space Complexity:
     */
    public static List<Integer> levelOrderTraversalIterative(TreeNode root) {
        // INSERT YOUR CODE HERE
    }

    /*
     * Exercise 3.1: Implement a function that determines whether a Binary Tree
     * contains a value.
     *
     * Time Complexity:
     * Space Complexity:
     */
    public static boolean containsValue(TreeNode root, int val) {
        // INSERT YOUR CODE HERE
    }

    /*
     * Exercise 3.2: Implement a function that determines whether a Binary Tree
     * contains a value. If the tree contains the value, return the path to that
     * node
     *
     * Time Complexity:
     * Space Complexity:
     */
    public static List<Integer> pathToNode(TreeNode root, int val) {
        // INSERT YOUR CODE HERE
    }

    /*
     * Exercise 4.1: Max Binary Tree Depth
     *
     * Time Complexity:
     * Space Complexity:
     */
    public static int maxDepth(TreeNode root) {
        // INSERT YOUR CODE HERE
    }

    /*
     * Exercise 4.2: Lowest Common Ancestor
     *
     * Time Complexity:
     * Space Complexity:
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // INSERT YOUR CODE HERE
    }

    /*
     * Exercise 4.3: Balanced Binary Tree
     *
     * Time Complexity:
     * Space Complexity:
     */
    public static boolean isBalanced(TreeNode root) {
        // INSERT YOUR CODE HERE
    }

    /*
     * Exercise 4.4: Merge Binary Trees
     *
     * Time Complexity:
     * Space Complexity:
     */
    public static TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        // INSERT YOUR CODE HERE
    }

    /*
     * Exercise 4.5 Invert Binary Tree
     *
     * Time Complexity:
     * Space Complexity:
     */
    public static TreeNode invertTree(TreeNode root) {
        // INSERT YOUR CODE HERE
    }

    /*
     * Exercise 4.6: Diameter of Binary Tree
     *
     * Time Complexity:
     * Space Complexity:
     */
    public static int diamerterOfBinaryTree(TreeNode root) {
        // INSERT YOUR CODE HERE
    }

    /*
     * Exercise 4.7: Tree to Linked List
     *
     * Time Complexity:
     * Space Complexity:
     */
    public static TreeNode treeToList(TreeNode root) {
        // INSERT YOUR CODE HERE
    }


    // Test cases
    public static void main(String[] args) {
        // ADD TEST CASES HER
    }
}
