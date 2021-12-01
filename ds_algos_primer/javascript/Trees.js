/**
 *   Title: TreeSolutions
 *
 *   This file contains the solutions for the Tree exercises in the DS & Algos
 *   Primer. If you have not already attempted these exercises, we highly
 *   recommend you complete them before reviewing the solutions here.
 *
 *   Execution: node TreeSolutions.js
 */

/**
 * Exercise 1.1: Implement a Binary Search Tree class
 */
function BinarySearchTree() {

    /**
     * Simple TreeNode class
     */
    function TreeNode(val) {
        // INSERT YOUR CODE HERE
    }

    /**
     * Insert a value into the tree (in order)
     *
     * Time Complexity:
     * Space Complexity:
     *
     * @param{number} n
     */
    this.insert = function(n) {
        // INSERT YOUR CODE HERE
    }

    /**
     * Delete a value from the BST
     *
     * Time Complexity:
     * Space Complexity:
     *
     * @param{number} n
     */
    this.delete = function(n) {
        // INSERT YOUR CODE HERE
    }

    /**
     * Determine if the tree contains n
     *
     * Time Complexity:
     * Space Complexity:
     *
     * @param{number} n
     * @return{boolean}
     */
    this.contains = function(n) {
        // INSERT YOUR CODE HERE
    }

    /**
     * Serialize a BST by converting it into a string
     *
     * Time Complexity:
     * Space Complexity:
     *
     * @return{string}
     */
    this.serialize = function() {
        // INSERT YOUR CODE HERE
    }

    /**
     * Deserialize a serialized BST
     *
     * Time Complexity:
     * Space Complexity:
     *
     * @param{string} serialized
     * @return{BinarySearchTree}
     */
    this.deserialize = function(serialized) {
        // INSERT YOUR CODE HERE
    }

    /**
     * Convert the tree to a string
     *
     * Time Complexity:
     * Space Complexity:
     *
     * @return{string}
     */
    BinarySearchTree.prototype.toString = function() {
        // INSERT YOUR CODE HERE
    }
}

/**
 * Exercise 1.2: Implement a trie class
 */
function Trie() {

    /**
     * Simple Trie Node class
     *
     * @param{character} val
     * @param{boolean} isEndOfWord
     */
    function TrieNode(val, isEndOfWord) {
        // INSERT YOUR CODE HERE
    }

    /**
     * Insert a string into our trie
     *
     * Time Complexity:
     * Space Complexity:
     *
     * @param{string} s
     */
    this.insert = function(s) {
        // INSERT YOUR CODE HERE
    }

    /**
     * Does trie contain s
     *
     * Time Complexity:
     * Space Complexity:
     *
     * @param{string} s
     * @return{boolean}
     */
    this.contains = function(s) {
        // INSERT YOUR CODE HERE
    }

    /**
     * Find all strings in the trie with a given prefix
     *
     * Time Complexity:
     * Space Complexity:
     *
     * @param{string} pre
     * @return{string[]}
     */
    this.prefix = function(pre) {
        // INSERT YOUR CODE HERE
    }

    /**
     * Convert trie into a string
     *
     * Time Complexity:
     * Space Complexity:
     *
     * @return{string}
     */

    Trie.prototype.toString = function() {
        // INSERT YOUR CODE HERE
    }
}

/**
 * Exercise 2.1: Implement each tree traversal. You should implement each
 * traversal both recursively and iteratively.
 */

/**
 * Simple TreeNode class for remaining exercises
 */
 function TreeNode(val) {
     this.val = val;
     this.left = null;
     this.right = null;
 }

/**
 * Inorder Traversal - Recursive
 *
 * Time Complexity:
 * Space Complexity:
 *
 * @param{TreeNode} root
 * @return{number[]}
 */
var inOrderTraversalRecursive = function(root) {
    // INSERT YOUR CODE HERE
}

/**
 * Inorder Traversal - Iterative
 *
 * Time Complexity:
 * Space Complexity:
 *
 * @param{TreeNode} root
 * @return{number[]}
 */
var inOrderTraversalIterative = function(root) {
    // INSERT YOUR CODE HERE
}

/**
 * Preorder Traversal - Recursive
 *
 * Time Complexity:
 * Space Complexity:
 *
 * @param{TreeNode} root
 * @return{number[]}
 */
var preOrderTraversalRecursive = function(root) {
    // INSERT YOUR CODE HERE
}

/**
 * Preorder Traversal - Iterative
 *
 * Time Complexity:
 * Space Complexity:
 *
 * @param{TreeNode} root
 * @return{number[]}
 */
var preOrderTraversalIterative = function(root) {
    // INSERT YOUR CODE HERE
}

/**
 * Postorder Traversal - Recursive
 *
 * Time Complexity:
 * Space Complexity:
 *
 * @param{TreeNode}
 */
var postOrderTraversalRecursive = function(root) {
    // INSERT YOUR CODE HERE
}

/**
 * Postorder Traversal - Iterative
 *
 * Time Complexity:
 * Space Complexity:
 *
 * @param{TreeNode} root
 * @return{number[]}
 */
var postOrderTraversalIterative = function(root) {
    // INSERT YOUR CODE HERE
}

/**
 * Levelorder Traversal - Recursive
 *
 * Time Complexity:
 * Space Complexity:
 *
 * @param{TreeNode} root
 * @return{number[]}
 */
var levelOrderTraversalRecursive = function(root) {
    // INSERT YOUR CODE HERE
}

/**
 * Levelorder Traversal - Iterative
 *
 * Time Complexity:
 * Space Complexity:
 *
 * @param{TreeNode} root
 * @return{number[]}
 */
var levelOrderTraversalIterative = function(root) {
    // INSERT YOUR CODE HERE
}

/**
 * Exercise 3.1: Implement a function that determines whether a Binary Tree
 * contains a value.
 *
 * Time Complexity:
 * Space Complexity:
 *
 * @param{TreeNode} root
 * @param{number} val
 * @return{boolean}
 */
var containsValue = function(root, val) {
    // INSERT YOUR CODE HERE
}

/**
 * Exercise 3.2: Implement a function that determines whether a Binary Tree
 * contains a value. If the tree contains the value, return the path to that
 * node
 *
 * Time Complexity:
 * Space Complexity:
 *
 * @param{TreeNode} root
 * @param{number} val
 * @return{number[]}
 */
var pathToNode = function(root, val) {
    // INSERT YOUR CODE HERE
}

/**
 * Exercise 4.1: Max Binary Tree Depth
 *
 * Time Complexity:
 * Space Complexity:
 *
 * @param{TreeNode} root
 * @return{number}
 */
var maxDepth = function(root) {
    // INSERT YOUR CODE HERE
}

/**
 * Exercise 4.2: Lowest Common Ancestor
 *
 * Time Complexity:
 * Space Complexity:
 *
 * @param{TreeNode} root
 * @param{TreeNode} p
 * @param{TreeNode} q
 * @return{TreeNode}
 */
var lowestCommonAncestor = function(root, p, q) {
    // INSERT YOUR CODE HERE
}

/**
 * Exercise 4.3: Balanced Binary Tree
 *
 * Time Complexity:
 * Space Complexity:
 *
 * @param{TreeNode} root
 * @return{boolean}
 */
var isBalanced = function(root) {
    // INSERT YOUR CODE HERE
}

/**
 * Exercise 4.4: Merge Binary Trees
 *
 * Time Complexity:
 * Space Complexity:
 *
 * @param{TreeNode} t1
 * @param{TreeNode} t2
 * @return{TreeNode}
 */
var mergeTrees = function(t1, t2) {
    // INSERT YOUR CODE HERE
}

/**
 * Exercise 4.5 Invert Binary Tree
 *
 * Time Complexity:
 * Space Complexity:
 *
 * @param{TreeNode} root
 * @return{TreeNode}
 */
var invertTree = function(root) {
    // INSERT YOUR CODE HERE
}

/**
 * Exercise 4.6: Diameter of Binary Tree
 *
 * Time Complexity:
 * Space Complexity:
 *
 * @param{TreeNode} root
 * @return{number}
 */
var diameterOfBinaryTree = function(root) {
    // INSERT YOUR CODE HERE
}

/**
 * Exercise 4.7: Tree to Linked List
 *
 * Time Complexity:
 * Space Complexity:
 *
 * @param{TreeNode} root
 * @return{TreeNode}
 */
var treeToList = function(root) {
    // INSERT YOUR CODE HERE
}


var tester = function() {
    // ADD YOUR TEST CASES HERE
}

tester();
