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
        this.val = val;
        this.left = null;
        this.right = null;
    }

    // The only thing we need to store in our object is the root
    this.root = null;

    /**
     * Insert a value into the tree (in order)
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param{number} n
     */
    this.insert = function(n) {
        // If the tree is empty, make this new node the root
        if (this.root == null) {
            this.root = new TreeNode(n);
            return;
        }

        // Iterate down into the tree until we reach an empty space to
        // insert our value into the tree. We could also do this recursively
        var curr = this.root;
        while (true) {
            // If our value is less than the current node, it has to go in
            // the left subtree
            if (n < curr.val) {
                if (curr.left != null) {
                    curr = curr.left;
                } else {
                    curr.left = new TreeNode(n);
                    return;
                }
            } else {
                // Otherwise, our node goes in the right subtree
                if (curr.right != null) {
                    curr = curr.right;
                } else {
                    curr.right = new TreeNode(n);
                    return;
                }
            }
        }
    }

    /**
     * Delete a value from the BST
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * @param{number} n
     */
    this.delete = function(n) {
        // Recursively call our inner function
        this.root = deleteInner(n, this.root);
    }

    /**
     * Recursively delete the value from the tree. When we find the value,
     * there are 3 possibilities:
     *   1. It has 0 children - This is trivial, we just delete the node
     *   2. It has 1 child - This is also easy, we just replace the node
     *      with it's one child
     *   3. It has 2 children - This is tricky. We have to replace the node
     *      with the next larger or smaller node to maintain the proper
     *      structure of the tree. Then we have to recursively remove that
     *      node that was moved from it's previous location in the tree
     *
     * @param{number} n
     * @param{TreeNode} curr
     * @return{TreeNode}
     */
    var deleteInner = function(n, curr) {
        // Node to remove is in the left subtree
        if (n < curr.val) {
            curr.left = deleteInner(n, curr.left);
            return curr;
        }

        // Node to remove is in the right subtree
        if (n > curr.val) {
            curr.right = deleteInner(n, curr.right);
            return curr;
        }

        // Otherwise we need to remove the current node. If it has no
        // children, just return null
        if (curr.left == null && curr.right == null) return null;

        // If it has 1 child, replace the current node with it's child
        if (curr.left == null) return curr.right;
        if (curr.right == null) return curr.left;

        // Otherwise, find the smallest node in the right subtree and
        // replace the current node with that node. Then delete it from the
        // right subtree
        var min = minNode(curr.right);

        // Remove min from the right subtree
        var right = deleteInner(min.val, curr.right);
;
        // Replace current node with min
        min.left = curr.left;
        min.right = right;
        return min;
    }

    /**
     * Find the minimum node in a tree. We just do this by iterating as far
     * to the left as we can.
     *
     * @param{TreeNode} curr
     * @return{TreeNode}
     */
    var minNode = function(curr) {
        while (curr.left != null) curr = curr.left;
        return curr;
    }

    /**
     * Determine if the tree contains n
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param{number} n
     * @return{boolean}
     */
    this.contains = function(n) {
        var curr = this.root;

        // Iterate until we find the node or get to a null node
        while (curr != null) {
            if (curr.val == n) return true;

            // If the node is smaller than the current node its in the left
            // subtree. Otherwise its in the right subtree
            if (n < curr.val) curr = curr.left;
            else curr = curr.right;
        }

        return false;
    }

    /**
     * Serialize a BST by converting it into a string
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * @return{string}
     */
    this.serialize = function() {
        // We will use a simple serialization that follows a pre-order
        // traversal. We need to remember to track all the null nodes as
        // well so that we can properly reconstruct our tree
        var result = [];
        serializeInner(this.root, result);
        return result.join(" ");
    }

    /**
     * Inner function to iterate over tree recursively and add each node to
     * our serialized string.
     *
     * @param{TreeNode} curr
     * @param{string[]} result
     */
    var serializeInner = function(curr, result) {
        // Add the current value (or null) to our string, then go left, then
        // go right
        if (curr == null) {
            result.push("null");
            return;
        }

        result.push("" + curr.val);
        serializeInner(curr.left, result);
        serializeInner(curr.right, result);
    }

    /**
     * Deserialize a serialized BST
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * @param{string} serialized
     * @return{BinarySearchTree}
     */
    this.deserialize = function(serialized) {
        // Tokenize our serialized string
        var vals = serialized.split(" ");

        // Deserialize the tree
        var root = deserializeInner(vals);

        // Convert it from a TreeNode back into an actual BST object
        var result = new BinarySearchTree();
        result.root = root;
        return result;
    }

    /**
     * We deserialize our tree by traversing the tree in the same order as
     * we did to serialize it and constructing the tree as we go.
     *
     * @param{string[]} vals
     * @return{TreeNode}
     */
    var deserializeInner = function(vals) {
        // Get the current node
        var valStr = vals.shift();

        // If it's null, then there is no subtree on this side
        if (valStr == "null") return null;

        // Otherwise, continue with the preorder traversal that we did with
        // our serialization
        var val = parseInt(valStr);
        var curr = new TreeNode(val);
        curr.left = deserializeInner(vals);
        curr.right = deserializeInner(vals);

        return curr;
    }

    /**
     * Convert the tree to a string
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * @return{string}
     */
    BinarySearchTree.prototype.toString = function() {
        var result = []

        // In this case, we'll just return all the node values in order
        inOrderTraversal(this.root, result);
        return result.join(" ");
    }

    /**
     * Do an inorder traversal to generate the string
     *
     * @param{TreeNode} curr
     * @param{string[]} result
     * @result{string}
     */
    var inOrderTraversal = function(curr, result) {
        if (curr == null) return;
        inOrderTraversal(curr.left, result);
        result.push("" + curr.val);
        inOrderTraversal(curr.right, result);
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
        this.val = val;

        // This is an n-ary tree so we can have many children. We also want
        // to be able to easily reference them by their value, so we use a dict
        this.children = {};

        // We need to know whether we are at the end of a word or not.
        // Otherwise we don't know if a path is a prefix or full word
        this.isEndOfWord = isEndOfWord
    }

    // To make our lives easy, we'll use a dummy root node whose children
    // are the actual first characters of our strings
    this.root = new TrieNode('-', false);

    /**
     * Insert a string into our trie
     *
     * Time Complexity: O(s.length)
     * Space Complexity: O(s.length)
     *
     * @param{string} s
     */
    this.insert = function(s) {
        // Quick sanity check
        if (s == null || s.length == 0) return;
        insertInner(s, this.root, 0);
    }

    /**
     * Inner function to insert value. We traverse the tree as long as the
     * characters already exist. If they don't, then we build the path in
     * the tree
     *
     * @param{string} s
     * @param{TrieNode} curr
     * @param{number} idx
     */
    var insertInner = function(s, curr, idx) {
        // We're at the end of the string, so mark that node as the end of
        // a word
        if (idx == s.length) {
            curr.isEndOfWord = true;
            return;
        }

        // Determine whether the next character is already in the trie. If
        // it is, we just advance to the node that already exists. If not,
        // we need to create a new node
        var currChar = s[idx];
        if (curr.children[currChar] == null) {
            curr.children[currChar] = new TrieNode(currChar, false);
        }

        curr = curr.children[currChar];
        insertInner(s, curr, idx+1);
    }

    /**
     * Does trie contain s
     *
     * Time Complexity: O(s.length)
     * Space Complexity: O(1)
     *
     * @param{string} s
     * @return{boolean}
     */
    this.contains = function(s) {
        var curr = this.root;

        // Attempt to traverse the path in the trie for the string that we're
        // searching for. If at any point, the node we're looking for doesn't
        // exist, then the string is not in the trie
        for (var i = 0; i < s.length; i++) {
            var currChar = s[i];
            if (curr.children[currChar] == null) return false;
            curr = curr.children[currChar];
        }

        // If we found s but the last node is not EndOfWord, then s is a
        // prefix of a word in our trie but not a standalone word
        if (curr.isEndOfWord) return true;
        return false;
    }

    /**
     * Find all strings in the trie with a given prefix
     *
     * Time Complexity: O(# strings with prefix * length of strings with prefix)
     * Space Complexity: O(length of longest string with prefix)
     *
     * @param{string} pre
     * @return{string[]}
     */
    this.prefix = function(pre) {
        // First, we find the end of the prefix in the trie. We have to
        // traverse to the last character. If the prefix doesn't exist, then
        // there are no strings in the trie with that prefix
        var curr = this.root;
        for (var i = 0; i < pre.length; i++) {
            var currChar = pre[i];
            if (curr.children[currChar] == null) return [];
            curr = curr.children[currChar];
        }

        var results = [];

        // We'll pass in the prefix, so that we prepend that to all the
        // words that we generate starting at curr
        var currentString = [pre];

        // If prefix itself is a word, add it to the results
        if (curr.isEndOfWord) results.push(currentString.join(""));

        // For every child node of the prefix, find all the words starting
        // with that character
        for (var key in curr.children) {
            allStrings(curr.children[key], currentString, results);
        }

        return results;
    }

    /**
     * Given a TrieNode, find all words that start at that node
     *
     * @param{TrieNode} curr
     * @param{string[]} currentString
     * @param{string[]} result
     */
    var allStrings = function(curr, currentString, result) {
        // Add the current character to our result string
        currentString.push(curr.val);

        // If we are at the end of a word, add the whole string to our results
        if (curr.isEndOfWord) result.push(currentString.join(""));

        // For all the children, continue to traverse
        for (var key in curr.children) {
            allStrings(curr.children[key], currentString, result);
        }

        // Backtracking. When we return back up, we want to remove the
        // character that we added to the end of currentString to reset our
        // state
        currentString.pop();
    }

    /**
     * Convert trie into a string
     *
     * Time Complexity: O(# strings in trie * average string length)
     * Space Complexity: O(length of longest string in trie)
     *
     * @return{string}
     */

    Trie.prototype.toString = function() {
        // We can just use our prefix function to get a list of all strings
        // in our trie
        var result = this.prefix("");
        return result.join(" ");
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
 * Time Complexity: O(nodes in tree)
 * Space Complexity: O(nodes in tree)
 *
 * @param{TreeNode} root
 * @return{number[]}
 */
var inOrderTraversalRecursive = function(root) {
    // We will fill our results into this list
    result = [];

    // Recursive call
    inOrderTraversalRecursiveInner(root, result);
    return result;
}

/**
 * Inner recursive function
 *
 * @param{TreeNode} curr
 * @param{number[]} result
 */
var inOrderTraversalRecursiveInner = function(curr, result) {
    if (curr == null) return;

    // In order, we want to go left, then root, then right
    inOrderTraversalRecursiveInner(curr.left, result);
    result.push(curr.val);
    inOrderTraversalRecursiveInner(curr.right, result);
}

/**
 * Inorder Traversal - Iterative
 *
 * Time Complexity: O(nodes in tree)
 * Space Complexity: O(nodes in tree)
 *
 * @param{TreeNode} root
 * @return{number[]}
 */
var inOrderTraversalIterative = function(root) {
    if (root == null) return [];

    // We can emulate what we do recursively by using a stack
    var stack = [];
    var result = [];

    stack.push(root);
    var curr = root.left;

    // We go all the way to the left in our tree, then we backtrack by
    // popping items off of our stack. This allows us to keep track of the
    // proper order
    while (!(stack.length == 0 && curr == null)) {
        // Go all the way to the left
        while (curr != null) {
            stack.push(curr);
            curr = curr.left;
        }

        // Once we've reached the end, include the current node and then
        // go to the right
        curr = stack.pop();
        result.push(curr.val);
        curr = curr.right;
    }

    return result;
}

/**
 * Preorder Traversal - Recursive
 *
 * Time Complexity: O(nodes in tree)
 * Space Complexity: O(nodes in tree)
 *
 * @param{TreeNode} root
 * @return{number[]}
 */
var preOrderTraversalRecursive = function(root) {
    // We will fill our results into this list
    var result = [];

    // Recursive call
    preOrderTraversalRecursiveInner(root, result);
    return result;
}

/**
 * Inner recursive function
 *
 * @param{TreeNode} curr
 * @param{number[]} result
 */
var preOrderTraversalRecursiveInner = function(curr, result) {
    if (curr == null) return;

    // Pre order, we want to go root, then left, then right
    result.push(curr.val);
    preOrderTraversalRecursiveInner(curr.left, result);
    preOrderTraversalRecursiveInner(curr.right, result);
}

/**
 * Preorder Traversal - Iterative
 *
 * Time Complexity: O(nodes in tree)
 * Space Complexity: O(nodes in tree)
 *
 * @param{TreeNode} root
 * @return{number[]}
 */
var preOrderTraversalIterative = function(root) {
    if (root == null) return [];

    // We can emulate what we do recursively by using a stack
    var stack = [];
    var result = [];

    stack.push(root);

    // First we add the current node, then we'll want to go left first, then
    // right. So we add those to our stack in reverse order
    while (stack.length != 0) {
        var curr = stack.pop();
        result.push(curr.val);

        // Our stack is LIFO, so add the thing we want to do last to the
        // stack first
        if (curr.right != null) stack.push(curr.right);
        if (curr.left != null) stack.push(curr.left);
    }

    return result;
}

/**
 * Postorder Traversal - Recursive
 *
 * Time Complexity: O(nodes in tree)
 * Space Complexity: O(nodes in tree)
 *
 * @param{TreeNode}
 */
var postOrderTraversalRecursive = function(root) {
    // We will fill our results into this list
    var result = [];

    // Recursive call
    postOrderTraversalRecursiveInner(root, result);
    return result;
}

/**
 * Inner recursive function
 *
 * @param{TreeNode} curr
 * @param{number[]} result
 */
var postOrderTraversalRecursiveInner = function(curr, result) {
    if (curr == null) return;

    // Pre order, we want to go left, then right, then root
    postOrderTraversalRecursiveInner(curr.left, result);
    postOrderTraversalRecursiveInner(curr.right, result);
    result.push(curr.val);
}

/**
 * Postorder Traversal - Iterative
 *
 * Time Complexity: O(nodes in tree)
 * Space Complexity: O(nodes in tree)
 *
 * @param{TreeNode} root
 * @return{number[]}
 */
var postOrderTraversalIterative = function(root) {
    if (root == null) return [];

    // We can emulate what we do recursively by using a stack
    var stack = [];
    var result = [];

    stack.push(root);

    // This one is easier to do in reverse order. We'll add the roots to
    // the results first, then the children. Then all we have to do is
    // reverse our result
    while (stack.length != 0) {
        var curr = stack.pop();
        result.push(curr.val);

        if (curr.right != null) stack.push(curr.left);
        if (curr.left != null) stack.push(curr.right);
    }

    result.reverse();

    // Collections.reverse(result);
    return result;
}

/**
 * Levelorder Traversal - Recursive
 *
 * Time Complexity: O(nodes in tree)
 * Space Complexity: O(nodes in tree)
 *
 * @param{TreeNode} root
 * @return{number[]}
 */
var levelOrderTraversalRecursive = function(root) {
    // We will get a list for each level and then merge them together
    var levels = [];

    // Recursive call
    levelOrderTraversalRecursiveInner(root, levels, 0);

    return levels.flat();
}

/**
 * Inner recursive function
 *
 * @param{TreeNode} curr
 * @param{number[][]} levels
 * @param{number} level
 */
var levelOrderTraversalRecursiveInner = function(curr, levels, level) {
    if (curr == null) return;

    // If this is the first node in this level, we need to create a new list
    if (level >= levels.length) {
        levels.push([]);
    }

    // Add the node to the list for the appropriate level
    var currLevel = levels[level];
    currLevel.push(curr.val);

    // Continue our recursion
    levelOrderTraversalRecursiveInner(curr.left, levels, level+1);
    levelOrderTraversalRecursiveInner(curr.right, levels, level+1);
}

/**
 * Levelorder Traversal - Iterative
 *
 * Time Complexity: O(nodes in tree)
 * Space Complexity: O(nodes in tree)
 *
 * @param{TreeNode} root
 * @return{number[]}
 */
var levelOrderTraversalIterative = function(root) {
    // Fairly standard BFS implementation. We will use a queue to track
    // which nodes we've visited so far
    var visited = [];
    visited.push(root);

    var result = [];

    // Get the current node, add it to our result, and then add the children
    // to the queue
    while (visited.length != 0) {
        var curr = visited.shift();
        result.push(curr.val);

        // Add any existing children to the queue
        if (curr.left != null) visited.push(curr.left);
        if (curr.right != null) visited.push(curr.right);
    }

    return result;
}

/**
 * Exercise 3.1: Implement a function that determines whether a Binary Tree
 * contains a value.
 *
 * Time Complexity: O(nodes in tree)
 * Space Complexity: O(nodes in tree)
 *
 * @param{TreeNode} root
 * @param{number} val
 * @return{boolean}
 */
var containsValue = function(root, val) {
    // If null, we didn't find the value
    if (root == null) return false;

    // We found the value so return true
    if (root.val == val) return true;

    // See if the value is in either subtree
    return containsValue(root.left, val) || containsValue(root.right, val);
}

/**
 * Exercise 3.2: Implement a function that determines whether a Binary Tree
 * contains a value. If the tree contains the value, return the path to that
 * node
 *
 * Time Complexity: O(nodes in tree)
 * Space Complexity: O(nodes in tree)
 *
 * @param{TreeNode} root
 * @param{number} val
 * @return{number[]}
 */
var pathToNode = function(root, val) {
    // If null, there is no path so return null
    if (root == null) return null;

    // If we found the value, the path from that node to itself is just
    // [root.val]
    if (root.val == val) {
        var result = [root.val];
        return result;
    }

    // Get the paths from the child nodes to the target node
    var left = pathToNode(root.left, val);
    var right = pathToNode(root.right, val);

    // If there is a path from the left child to the target, prepend the
    // current value to that path
    if (left != null) {
        left.unshift(root.val);
        return left;
    }

    // If there is a path from the right child to the target, prepend the
    // current value to that path
    if (right != null) {
        right.unshift(root.val);
        return right;
    }

    // If neither child has a path to the target node, then root doesn't
    // have a path either so return null
    return null;
}

/**
 * Exercise 4.1: Max Binary Tree Depth
 *
 * Time Complexity: O(nodes in tree)
 * Space Complexity: O(nodes in tree)
 *
 * @param{TreeNode} root
 * @return{number}
 */
var maxDepth = function(root) {
    // If the node is null, the depth is 0
    if (root == null) return 0;

    // Otherwise, the depth is the max of the depth of the left and right
    // subtrees plus 1 to include the current node
    return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
}

/**
 * Exercise 4.2: Lowest Common Ancestor
 *
 * Time Complexity: O(nodes in tree)
 * Space Complexity: O(nodes in tree)
 *
 * @param{TreeNode} root
 * @param{TreeNode} p
 * @param{TreeNode} q
 * @return{TreeNode}
 */
var lowestCommonAncestor = function(root, p, q) {
    // Find the path to each node. Then find where the paths diverge and
    // the last common node is the lowest common ancestor
    var pathToP = lcaPathToNode(root, p);
    var pathToQ = lcaPathToNode(root, q);

    // Compare the paths to find where they diverge
    var curr = root;
    while (pathToP.length != 0 && pathToQ.length != 0) {
        if (pathToP[0] == pathToQ[0]) {
            curr = pathToP.shift();
            pathToQ.shift();
        } else {
            break;
        }
    }

    return curr;
}

/**
 * This is copied from pathToNode and modified to find the nodes rather than
 * the values
 *
 * @param{TreeNode} root
 * @param{TreeNode} val
 * @return{TreeNode[]}
 */
var lcaPathToNode = function(root, val) {
    if (root == null) return null;

    // If we found the value, the path from that node to itself is just
    // [root]
    if (root == val) {
        var result = [];
        result.push(root);
        return result;
    }

    // Get the paths from the child nodes to the target node
    var left = lcaPathToNode(root.left, val);
    var right = lcaPathToNode(root.right, val);

    // If there is a path from the left child to the target, prepend the
    // current value to that path
    if (left != null) {
        left.unshift(root);
        return left;
    }

    // If there is a path from the right child to the target, prepend the
    // current value to that path
    if (right != null) {
        right.unshift(root);
        return right;
    }

    // If neither child has a path to the target node, then root doesn't
    // have a path either so return null
    return null;
}

/**
 * Exercise 4.3: Balanced Binary Tree
 *
 * Time Complexity: O(nodes in tree)
 * Space Complexity: O(nodes in tree)
 *
 * @param{TreeNode} root
 * @return{boolean}
 */
var isBalanced = function(root) {
    return isBalancedInner(root)[0];
}

/**
 * Compute whether subtree is balanced and also get the height of the
 * subtree. Easy to do this by passing an array with the height and
 * updating the value
 *
 * @param{TreeNode}
 * @return{Object[]}
 */
var isBalancedInner = function(root) {
    // If null, the tree is balanced and height 0
    if (root == null) {
        return [true, 0];
    }

    var left = isBalancedInner(root.left);
    var right = isBalancedInner(root.right);

    // See if left and right subtrees are balanced
    if (!left[0] || !right[0]) return [false, 0];

    // If they are, make sure the full tree is balanced
    if (Math.abs(left[1] - right[1]) > 1) return [false, 0];

    return [true, Math.max(left[1], right[1])+1];
}

/**
 * Exercise 4.4: Merge Binary Trees
 *
 * Time Complexity: O(nodes in t1 + nodes in t2)
 * Space Complexity: O(max(nodes in t1, nodes in t2))
 *
 * @param{TreeNode} t1
 * @param{TreeNode} t2
 * @return{TreeNode}
 */
var mergeTrees = function(t1, t2) {
    // If trees are null, merged tree is also null
    if (t1 == null && t2 == null) return null;

    // To save duplicate work, if one node is null, make sure it's always t2
    if (t1 == null) {
        t1 = t2;
        t2 = null;
    }

    // If we have one null node, traverse the tree that is still non-null
    // and add it as-is to the result tree
    if (t2 == null) {
        var curr = new TreeNode(t1.val);

        // If t2 is null, the children will also be null
        curr.left = mergeTrees(t1.left, null);
        curr.right = mergeTrees(t1.right, null);
        return curr;
    }

    // If both nodes are non-null, the current node should have the sum of
    // both values, and then recursively merge the two subtrees
    var curr = new TreeNode(t1.val + t2.val);
    curr.left = mergeTrees(t1.left, t2.left);
    curr.right = mergeTrees(t1.right, t2.right);

    return curr;
}

/**
 * Exercise 4.5 Invert Binary Tree
 *
 * Time Complexity: O(nodes in tree)
 * Space Complexity: O(nodes in tree)
 *
 * @param{TreeNode} root
 * @return{TreeNode}
 */
var invertTree = function(root) {
    // If null, inverted tree is null
    if (root == null) return null;
    var left = root.left;
    var right = root.right;

    // Point the left pointer to the inverted right subtree and vice versa
    root.left = invertTree(right);
    root.right = invertTree(left);
    return root;
}

/**
 * Exercise 4.6: Diameter of Binary Tree
 *
 * Time Complexity: O(nodes in tree)
 * Space Complexity: O(nodes in tree)
 *
 * @param{TreeNode} root
 * @return{number}
 */
var diameterOfBinaryTree = function(root) {
    // Note: We have to subtract 1 at the end here because we are computing
    // the number of nodes in the diameter, whereas we actually want the
    // number of edges in the diameter, which is 1 less
    return diameterOfBinaryTreeInner(root)[0] - 1;
}

/**
 * Inner recursive function
 *
 * @param{TreeNode} root
 * @return{number[]}
 */
var diameterOfBinaryTreeInner = function(root) {
    // Base Case. If root is null, diameter of empty tree is 0
    if (root == null) return [0,0];

    // Compute diameters
    var left = diameterOfBinaryTreeInner(root.left);
    var right = diameterOfBinaryTreeInner(root.right);

    // The diameter is either the max diameter of the left and right
    // subtrees (doesn't go through the current node) or the height of the
    // left and right + 1 (does go through the current node)
    var diam = Math.max(left[0], right[0]);
    diam = Math.max(diam, left[1] + right[1] + 1);

    // Return diameter and max height of subtree
    return [diam, Math.max(left[1], right[1])+1];
}

/**
 * Exercise 4.7: Tree to Linked List
 *
 * Time Complexity: O(nodes in tree)
 * Space Complexity: O(nodes in tree)
 *
 * @param{TreeNode} root
 * @return{TreeNode}
 */
var treeToList = function(root) {
    // If node is null, list is null
    if (root == null) return null;

    // Convert left and right subtrees into doubly linked lists
    var leftList = treeToList(root.left);
    var rightList = treeToList(root.right);

    // Convert root into a doubly linked list containing just itself
    root.left = root;
    root.right = root;

    // Merge linked lists together
    root = mergeLists(leftList, root);
    root = mergeLists(root, rightList);

    return root;
}

/**
 * Helper fucntion that merges 2 doubly linked lists into a single list
 *
 * @param{TreeNode} a
 * @param{TreeNode} b
 * @return{TreeNode}
 */
var mergeLists = function(a, b) {
    if (a == null) return b;
    if (b == null) return a;

    var aEnd = a.left;
    var bEnd = b.left;

    a.left = bEnd;
    bEnd.right = a;
    aEnd.right = b;
    b.left = aEnd;
    return a;
}


var tester = function() {
    var t = new BinarySearchTree();
    t.insert(5);
    t.insert(2);
    t.insert(7);
    t.insert(1);
    t.insert(6);
    console.log(t.toString());
    console.log(t.contains(2));
    t.delete(5);
    console.log(t.toString());
    var ser = t.serialize();
    console.log(ser);
    console.log((new BinarySearchTree()).deserialize(ser).toString());

    var s = new Trie();
    s.insert("test");
    s.insert("tester");
    s.insert("rest");
    console.log(s.toString());
    console.log(s.contains("test"));
    console.log(s.contains("teste"));
    console.log(s.prefix("test"));

    var root = new TreeNode(5);
    root.left = new TreeNode(3);
    root.left.left = new TreeNode(1);
    root.left.right = new TreeNode(4);
    root.right = new TreeNode(8);
    root.right.left = new TreeNode(7);
    root.right.right = new TreeNode(9);

    console.log(inOrderTraversalRecursive(root));
    console.log(inOrderTraversalIterative(root));

    console.log(preOrderTraversalRecursive(root));
    console.log(preOrderTraversalIterative(root));

    console.log(postOrderTraversalRecursive(root));
    console.log(postOrderTraversalIterative(root));

    console.log(levelOrderTraversalRecursive(root));
    console.log(levelOrderTraversalIterative(root));

    console.log(containsValue(root, 4));
    console.log(pathToNode(root, 4));

    console.log(maxDepth(root));
    console.log(lowestCommonAncestor(root, root.left.left, root.left).val);
    console.log(isBalanced(root));

    var root2 = new TreeNode(3);
    root2.left = new TreeNode(2);
    root2.left.left = new TreeNode(4);
    root2.left.left.right = new TreeNode(5);
    console.log(mergeTrees(root, root2));

    console.log(invertTree(root).left.val);

    console.log(diameterOfBinaryTree(root));

    console.log(treeToList(root));
}

tester();
