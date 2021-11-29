/*
 *   Title: TreeSolutions
 *
 *   This file contains the solutions for the Tree exercises in the DS & Algos
 *   Primer. If you have not already attempted these exercises, we highly
 *   recommend you complete them before reviewing the solutions here.
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
            int val;
            TreeNode left;
            TreeNode right;

            private TreeNode(int val) {
                this.val = val;
            }
        }

        // The only thing we need to store in our object is the root
        TreeNode root;

        // Constructor
        public BinarySearchTree() {}

        /*
         * Insert a value into the tree (in order)
         *
         * Time Complexity: O(n)
         * Space Complexity: O(1)
         */
        public void insert(int n) {

            // If the tree is empty, make this new node the root
            if (this.root == null) {
                this.root = new TreeNode(n);
                return;
            }

            // Iterate down into the tree until we reach an empty space to
            // insert our value into the tree. We could also do this recursively
            TreeNode curr = this.root;
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

        /*
         * Delete a value from the BST
         *
         * Time Complexity: O(n)
         * Space Complexity: O(n)
         */
        public void delete(int n) {
            // Recursively call our inner function
            this.root = delete(n, this.root);
        }

        /*
         * Recursively delete the value from the tree. When we find the value,
         * there are 3 possibilities:
         *   1. It has 0 children - This is trivial, we just delete the node
         *   2. It has 1 child - This is also easy, we just replace the node
         *      with it's one child
         *   3. It has 2 children - This is tricky. We have to replace the node
         *      with the next larger or smaller node to maintain the proper
         *      structure of the tree. Then we have to recursively remove that
         *      node that was moved from it's previous location in the tree
         */
        private TreeNode delete(int n, TreeNode curr) {
            // Node to remove is in the left subtree
            if (n < curr.val) {
                curr.left = delete(n, curr.left);
                return curr;
            }

            // Node to remove is in the right subtree
            if (n > curr.val) {
                curr.right = delete(n, curr.right);
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
            TreeNode min = minNode(curr.right);

            // Remove min from the right subtree
            TreeNode right = delete(min.val, curr.right);

            // Replace current node with min
            min.left = curr.left;
            min.right = right;
            return min;
        }

        /*
         * Find the minimum node in a tree. We just do this by iterating as far
         * to the left as we can.
         */
        private static TreeNode minNode(TreeNode curr) {
            while (curr.left != null) curr = curr.left;
            return curr;
        }

        /*
         * Determine if the tree contains n
         *
         * Time Complexity: O(n)
         * Space Complexity: O(1)
         */
        public boolean contains(int n) {
            TreeNode curr = this.root;

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

        /*
         * Serialize a BST by converting it into a string
         *
         * Time Complexity: O(n)
         * Space Complexity: O(n)
         */
        public static String serialize(BinarySearchTree tree) {
            // We will use a simple serialization that follows a pre-order
            // traversal. We need to remember to track all the null nodes as
            // well so that we can properly reconstruct our tree
            StringBuilder sb = new StringBuilder();
            serialize(tree.root, sb);
            return sb.toString();
        }

        /*
         * Inner function to iterate over tree recursively and add each node to
         * our serialized string.
         */
        private static void serialize(TreeNode curr, StringBuilder sb) {
            // Add the current value (or null) to our string, then go left, then
            // go right
            if (curr == null) {
                sb.append("null ");
                return;
            }

            sb.append(curr.val + " ");
            serialize(curr.left, sb);
            serialize(curr.right, sb);
        }

        /*
         * Deserialize a serialized BST
         *
         * Time Complexity: O(n)
         * Space Complexity: O(n)
         */
        public static BinarySearchTree deserialize(String serialized) {
            // Tokenize our serialized string
            List<String> vals = new LinkedList<>(Arrays.asList(serialized.split(" ")));

            // Deserialize the tree
            TreeNode root = deserialize(vals);

            // Convert it from a TreeNode back into an actual BST object
            BinarySearchTree result = new BinarySearchTree();
            result.root = root;
            return result;
        }

        /*
         * We deserialize our tree by traversing the tree in the same order as
         * we did to serialize it and constructing the tree as we go.
         */
        private static TreeNode deserialize(List<String> vals) {
            // Get the current node
            String valStr = vals.remove(0);

            // If it's null, then there is no subtree on this side
            if (valStr.equals("null")) return null;

            // Otherwise, continue with the preorder traversal that we did with
            // our serialization
            int val = Integer.parseInt(valStr);
            TreeNode curr = new TreeNode(val);
            curr.left = deserialize(vals);
            curr.right = deserialize(vals);

            return curr;
        }

        /*
         * Convert the tree to a string
         *
         * Time Complexity: O(n)
         * Space Complexity: O(n)
         */
        public String toString() {
            StringBuilder sb = new StringBuilder();

            // In this case, we'll just return all the node values in order
            inOrderTraversal(this.root, sb);
            return sb.toString();
        }

        /*
         * Do an inorder traversal to generate the string
         */
        private void inOrderTraversal(TreeNode curr, StringBuilder sb) {
            if (curr == null) return;
            inOrderTraversal(curr.left, sb);
            sb.append(curr.val + " ");
            inOrderTraversal(curr.right, sb);
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
            char val;

            // This is an n-ary tree so we can have many children. We also want
            // to be able to easily reference them by their value, so we use a map
            Map<Character, TrieNode> children;

            // We need to know whether we are at the end of a word or not.
            // Otherwise we don't know if a path is a prefix or full word
            boolean isEndOfWord;

            // Constructor
            private TrieNode(char val, boolean isEndOfWord) {
                this.val = val;
                this.children = new HashMap<>();
                this.isEndOfWord = isEndOfWord;
            }
        }

        // To make our lives easy, we'll use a dummy root node whose children
        // are the actual first characters of our strings
        TrieNode root;

        // Constructor
        public Trie() {
            // Just initialize dummy node
            root = new TrieNode('-', false);
        }

        /*
         * Insert a string into our trie
         *
         * Time Complexity: O(s.length())
         * Space Complexity: O(s.length())
         */
        public void insert(String s) {
            // Quick sanity check
            if (s == null || s.length() == 0) return;
            insert(s, root, 0);
        }

        /*
         * Inner function to insert value. We traverse the tree as long as the
         * characters already exist. If they don't, then we build the path in
         * the tree
         */
        private void insert(String s, TrieNode curr, int idx) {
            // We're at the end of the string, so mark that node as the end of
            // a word
            if (idx == s.length()) {
                curr.isEndOfWord = true;
                return;
            }

            // Determine whether the next character is already in the trie. If
            // it is, we just advance to the node that already exists. If not,
            // we need to create a new node
            char currChar = s.charAt(idx);
            if (!curr.children.containsKey(currChar)) {
                curr.children.put(currChar, new TrieNode(currChar, false));
            }

            curr = curr.children.get(currChar);
            insert(s, curr, idx+1);
        }

        /*
         * Does trie contain s
         *
         * Time Complexity: O(s.length())
         * Space Complexity: O(1)
         */
        public boolean contains(String s) {
            TrieNode curr = root;

            // Attempt to traverse the path in the trie for the string that we're
            // searching for. If at any point, the node we're looking for doesn't
            // exist, then the string is not in the trie
            for (int i = 0; i < s.length(); i++) {
                char currChar = s.charAt(i);
                if (!curr.children.containsKey(currChar)) return false;
                curr = curr.children.get(currChar);
            }

            // If we found s but the last node is not EndOfWord, then s is a
            // prefix of a word in our trie but not a standalone word
            if (curr.isEndOfWord) return true;
            return false;
        }

        /*
         * Find all strings in the trie with a given prefix
         *
         * Time Complexity: O(# strings with prefix * length of strings with prefix)
         * Space Complexity: O(length of longest string with prefix)
         */
        public List<String> prefix(String pre) {
            // First, we find the end of the prefix in the trie. We have to
            // traverse to the last character. If the prefix doesn't exist, then
            // there are no strings in the trie with that prefix
            TrieNode curr = root;
            for (int i = 0; i < pre.length(); i++) {
                char currChar = pre.charAt(i);
                if (!curr.children.containsKey(currChar)) return new LinkedList<>();
                curr = curr.children.get(currChar);
            }

            List<String> results = new LinkedList<>();

            // We'll pass in the prefix, so that we prepend that to all the
            // words that we generate starting at curr
            StringBuilder currentString = new StringBuilder(pre);

            // If prefix itself is a word, add it to the results
            if (curr.isEndOfWord) results.add(currentString.toString());

            // For every child node of the prefix, find all the words starting
            // with that character
            for (Character key : curr.children.keySet()) {
                allStrings(curr.children.get(key), currentString, results);
            }

            return results;
        }

        /*
         * Given a TrieNode, find all words that start at that node
         */
        private void allStrings(TrieNode curr, StringBuilder currentString, List<String> result) {
            // Add the current character to our result string
            currentString.append(curr.val);

            // If we are at the end of a word, add the whole string to our results
            if (curr.isEndOfWord) result.add(currentString.toString());

            // For all the children, continue to traverse
            for (Character key : curr.children.keySet()) {
                allStrings(curr.children.get(key), currentString, result);
            }

            // Backtracking. When we return back up, we want to remove the
            // character that we added to the end of currentString to reset our
            // state
            currentString.setLength(currentString.length() - 1);
        }

        /*
         * Convert trie into a string
         *
         * Time Complexity: O(# strings in trie * average string length)
         * Space Complexity: O(length of longest string in trie)
         */
        public String toString() {
            // We can just use our prefix function to get a list of all strings
            // in our trie
            List<String> result = this.prefix("");
            return String.join(" ", result);
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
     * Time Complexity: O(nodes in tree)
     * Space Complexity: O(nodes in tree)
     */
    public static List<Integer> inOrderTraversalRecursive(TreeNode root) {
        // We will fill our results into this list
        List<Integer> result = new LinkedList<>();

        // Recursive call
        inOrderTraversalRecursive(root, result);
        return result;
    }

    /*
     * Inner recursive function
     */
    private static void inOrderTraversalRecursive(TreeNode curr, List<Integer> result) {
        if (curr == null) return;

        // In order, we want to go left, then root, then right
        inOrderTraversalRecursive(curr.left, result);
        result.add(curr.val);
        inOrderTraversalRecursive(curr.right, result);
    }

    /*
     * Inorder Traversal - Iterative
     *
     * Time Complexity: O(nodes in tree)
     * Space Complexity: O(nodes in tree)
     */
    public static List<Integer> inOrderTraversalIterative(TreeNode root) {
        if (root == null) return new LinkedList<>();

        // We can emulate what we do recursively by using a stack
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new LinkedList<>();

        stack.push(root);
        TreeNode curr = root.left;

        // We go all the way to the left in our tree, then we backtrack by
        // popping items off of our stack. This allows us to keep track of the
        // proper order
        while (!(stack.isEmpty() && curr == null)) {
            // Go all the way to the left
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            // Once we've reached the end, include the current node and then
            // go to the right
            curr = stack.pop();
            result.add(curr.val);
            curr = curr.right;
        }

        return result;
    }

    /*
     * Preorder Traversal - Recursive
     *
     * Time Complexity: O(nodes in tree)
     * Space Complexity: O(nodes in tree)
     */
    public static List<Integer> preOrderTraversalRecursive(TreeNode root) {
        // We will fill our results into this list
        List<Integer> result = new LinkedList<>();

        // Recursive call
        preOrderTraversalRecursive(root, result);
        return result;
    }

    /*
     * Inner recursive function
     */
    private static void preOrderTraversalRecursive(TreeNode curr, List<Integer> result) {
        if (curr == null) return;

        // Pre order, we want to go root, then left, then right
        result.add(curr.val);
        preOrderTraversalRecursive(curr.left, result);
        preOrderTraversalRecursive(curr.right, result);
    }

    /*
     * Preorder Traversal - Iterative
     *
     * Time Complexity: O(nodes in tree)
     * Space Complexity: O(nodes in tree)
     */
    public static List<Integer> preOrderTraversalIterative(TreeNode root) {
        if (root == null) return new LinkedList<>();

        // We can emulate what we do recursively by using a stack
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new LinkedList<>();

        stack.push(root);

        // First we add the current node, then we'll want to go left first, then
        // right. So we add those to our stack in reverse order
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            result.add(curr.val);

            // Our stack is LIFO, so add the thing we want to do last to the
            // stack first
            if (curr.right != null) stack.push(curr.right);
            if (curr.left != null) stack.push(curr.left);
        }

        return result;
    }

    /*
     * Postorder Traversal - Recursive
     *
     * Time Complexity: O(nodes in tree)
     * Space Complexity: O(nodes in tree)
     */
    public static List<Integer> postOrderTraversalRecursive(TreeNode root) {
        // We will fill our results into this list
        List<Integer> result = new LinkedList<>();

        // Recursive call
        postOrderTraversalRecursive(root, result);
        return result;
    }

    /*
     * Inner recursive function
     */
    private static void postOrderTraversalRecursive(TreeNode curr, List<Integer> result) {
        if (curr == null) return;

        // Pre order, we want to go left, then right, then root
        postOrderTraversalRecursive(curr.left, result);
        postOrderTraversalRecursive(curr.right, result);
        result.add(curr.val);
    }

    /*
     * Postorder Traversal - Iterative
     *
     * Time Complexity: O(nodes in tree)
     * Space Complexity: O(nodes in tree)
     */
    public static List<Integer> postOrderTraversalIterative(TreeNode root) {
        if (root == null) return new LinkedList<>();

        // We can emulate what we do recursively by using a stack
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new LinkedList<>();

        stack.push(root);

        // This one is easier to do in reverse order. We'll add the roots to
        // the results first, then the children. Then all we have to do is
        // reverse our result
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            result.add(curr.val);

            if (curr.right != null) stack.push(curr.left);
            if (curr.left != null) stack.push(curr.right);
        }

        Collections.reverse(result);
        return result;
    }

    /*
     * Levelorder Traversal - Recursive
     *
     * Time Complexity: O(nodes in tree)
     * Space Complexity: O(nodes in tree)
     */
    public static List<Integer> levelOrderTraversalRecursive(TreeNode root) {
        // We will get a list for each level and then merge them together
        List<List<Integer>> levels = new ArrayList<>();

        // Recursive call
        levelOrderTraversalRecursive(root, levels, 0);

        List<Integer> result = new LinkedList<>();
        for (List<Integer> l : levels) {
            result.addAll(l);
        }

        return result;
    }

    /*
     * Inner recursive function
     */
    private static void levelOrderTraversalRecursive(TreeNode curr, List<List<Integer>> levels, int level) {
        if (curr == null) return;

        // If this is the first node in this level, we need to create a new list
        if (level >= levels.size()) {
            levels.add(new LinkedList<>());
        }

        // Add the node to the list for the appropriate level
        List<Integer> currLevel = levels.get(level);
        currLevel.add(curr.val);

        // Continue our recursion
        levelOrderTraversalRecursive(curr.left, levels, level+1);
        levelOrderTraversalRecursive(curr.right, levels, level+1);
    }

    /*
     * Levelorder Traversal - Iterative
     *
     * Time Complexity: O(nodes in tree)
     * Space Complexity: O(nodes in tree)
     */
    public static List<Integer> levelOrderTraversalIterative(TreeNode root) {
        // Fairly standard BFS implementation. We will use a queue to track
        // which nodes we've visited so far
        Queue<TreeNode> visited = new LinkedList<>();
        visited.add(root);

        List<Integer> result = new LinkedList<>();

        // Get the current node, add it to our result, and then add the children
        // to the queue
        while (!visited.isEmpty()) {
            TreeNode curr = visited.remove();
            result.add(curr.val);

            // Add any existing children to the queue
            if (curr.left != null) visited.add(curr.left);
            if (curr.right != null) visited.add(curr.right);
        }

        return result;
    }

    /*
     * Exercise 3.1: Implement a function that determines whether a Binary Tree
     * contains a value.
     *
     * Time Complexity: O(nodes in tree)
     * Space Complexity: O(nodes in tree)
     */
    public static boolean containsValue(TreeNode root, int val) {
        // If null, we didn't find the value
        if (root == null) return false;

        // We found the value so return true
        if (root.val == val) return true;

        // See if the value is in either subtree
        return containsValue(root.left, val) || containsValue(root.right, val);
    }

    /*
     * Exercise 3.2: Implement a function that determines whether a Binary Tree
     * contains a value. If the tree contains the value, return the path to that
     * node
     *
     * Time Complexity: O(nodes in tree)
     * Space Complexity: O(nodes in tree)
     */
    public static List<Integer> pathToNode(TreeNode root, int val) {
        // If null, there is no path so return null
        if (root == null) return null;

        // If we found the value, the path from that node to itself is just
        // [root.val]
        if (root.val == val) {
            List<Integer> result = new LinkedList<>();
            result.add(root.val);
            return result;
        }

        // Get the paths from the child nodes to the target node
        List<Integer> left = pathToNode(root.left, val);
        List<Integer> right = pathToNode(root.right, val);

        // If there is a path from the left child to the target, prepend the
        // current value to that path
        if (left != null) {
            left.add(0, root.val);
            return left;
        }

        // If there is a path from the right child to the target, prepend the
        // current value to that path
        if (right != null) {
            right.add(0, root.val);
            return right;
        }

        // If neither child has a path to the target node, then root doesn't
        // have a path either so return null
        return null;
    }

    /*
     * Exercise 4.1: Max Binary Tree Depth
     *
     * Time Complexity: O(nodes in tree)
     * Space Complexity: O(nodes in tree)
     */
    public static int maxDepth(TreeNode root) {
        // If the node is null, the depth is 0
        if (root == null) return 0;

        // Otherwise, the depth is the max of the depth of the left and right
        // subtrees plus 1 to include the current node
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /*
     * Exercise 4.2: Lowest Common Ancestor
     *
     * Time Complexity: O(nodes in tree)
     * Space Complexity: O(nodes in tree)
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Find the path to each node. Then find where the paths diverge and
        // the last common node is the lowest common ancestor
        List<TreeNode> pathToP = lcaPathToNode(root, p);
        List<TreeNode> pathToQ = lcaPathToNode(root, q);

        // Compare the paths to find where they diverge
        TreeNode curr = root;
        while (!pathToP.isEmpty() && !pathToQ.isEmpty()) {
            if (pathToP.get(0) == pathToQ.get(0)) {
                curr = pathToP.get(0);
                pathToP.remove(0);
                pathToQ.remove(0);
            } else {
                break;
            }
        }

        return curr;
    }

    // This is copied from pathToNode and modified to find the nodes rather than
    // the values
    private static List<TreeNode> lcaPathToNode(TreeNode root, TreeNode val) {
        if (root == null) return null;

        // If we found the value, the path from that node to itself is just
        // [root]
        if (root == val) {
            List<TreeNode> result = new LinkedList<>();
            result.add(root);
            return result;
        }

        // Get the paths from the child nodes to the target node
        List<TreeNode> left = lcaPathToNode(root.left, val);
        List<TreeNode> right = lcaPathToNode(root.right, val);

        // If there is a path from the left child to the target, prepend the
        // current value to that path
        if (left != null) {
            left.add(0, root);
            return left;
        }

        // If there is a path from the right child to the target, prepend the
        // current value to that path
        if (right != null) {
            right.add(0, root);
            return right;
        }

        // If neither child has a path to the target node, then root doesn't
        // have a path either so return null
        return null;
    }

    /*
     * Exercise 4.3: Balanced Binary Tree
     *
     * Time Complexity: O(nodes in tree)
     * Space Complexity: O(nodes in tree)
     */
    public static boolean isBalanced(TreeNode root) {
        return isBalanced(root, new int[1]);
    }

    // Compute whether subtree is balanced and also get the height of the
    // subtree. Easy to do this by passing an array with the height and
    // updating the value
    private static boolean isBalanced(TreeNode root, int[] height) {
        // If null, the tree is balanced and height 0
        if (root == null) {
            return true;
        }

        // Arrays are pass by reference, so we can pass these and update the
        // value (we have to do this since we can't easily return multiple values)
        int[] leftHeight = new int[1];
        int[] rightHeight = new int[1];

        // See if left and right subtrees are balanced
        if (!isBalanced(root.left, leftHeight)) return false;
        if (!isBalanced(root.right, rightHeight)) return false;

        // If they are, make sure the full tree is balanced
        if (Math.abs(leftHeight[0] - rightHeight[0]) > 1) return false;

        // Update the height
        height[0] = Math.max(leftHeight[0], rightHeight[0]) + 1;

        return true;
    }

    /*
     * Exercise 4.4: Merge Binary Trees
     *
     * Time Complexity: O(nodes in t1 + nodes in t2)
     * Space Complexity: O(max(nodes in t1, nodes in t2))
     */
    public static TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
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
            TreeNode curr = new TreeNode(t1.val);

            // If t2 is null, the children will also be null
            curr.left = mergeTrees(t1.left, null);
            curr.right = mergeTrees(t1.right, null);
            return curr;
        }

        // If both nodes are non-null, the current node should have the sum of
        // both values, and then recursively merge the two subtrees
        TreeNode curr = new TreeNode(t1.val + t2.val);
        curr.left = mergeTrees(t1.left, t2.left);
        curr.right = mergeTrees(t1.right, t2.right);

        return curr;
    }

    /*
     * Exercise 4.5 Invert Binary Tree
     *
     * Time Complexity: O(nodes in tree)
     * Space Complexity: O(nodes in tree)
     */
    public static TreeNode invertTree(TreeNode root) {
        // If null, inverted tree is null
        if (root == null) return null;
        TreeNode left = root.left;
        TreeNode right = root.right;

        // Point the left pointer to the inverted right subtree and vice versa
        root.left = invertTree(right);
        root.right = invertTree(left);
        return root;
    }

    /*
     * Exercise 4.6: Diameter of Binary Tree
     *
     * Time Complexity: O(nodes in tree)
     * Space Complexity: O(nodes in tree)
     */
    public static int diamerterOfBinaryTree(TreeNode root) {
        // Note: We have to subtract 1 at the end here because we are computing
        // the number of nodes in the diameter, whereas we actually want the
        // number of edges in the diameter, which is 1 less
        return diameterOfBinaryTree(root, new int[1]) - 1;
    }

    private static int diameterOfBinaryTree(TreeNode root, int[] height) {
        // Base Case. If root is null, diameter of empty tree is 0
        if (root == null) return 0;

        // Initialize arrays to store heights of each subtree
        int[] leftHeight = new int[1];
        int[] rightHeight = new int[1];

        // Compute diameters
        int leftDiam = diameterOfBinaryTree(root.left, leftHeight);
        int rightDiam = diameterOfBinaryTree(root.right, rightHeight);

        // The diameter is either the max diameter of the left and right
        // subtrees (doesn't go through the current node) or the height of the
        // left and right + 1 (does go through the current node)
        int diam = Math.max(leftDiam, rightDiam);
        diam = Math.max(diam, leftHeight[0]+rightHeight[0]+1);

        // Update height
        height[0] = Math.max(leftHeight[0], rightHeight[0])+1;
        return diam;
    }

    /*
     * Exercise 4.7: Tree to Linked List
     *
     * Time Complexity: O(nodes in tree)
     * Space Complexity: O(nodes in tree)
     */
    public static TreeNode treeToList(TreeNode root) {
        // If node is null, list is null
        if (root == null) return null;

        // Convert left and right subtrees into doubly linked lists
        TreeNode leftList = treeToList(root.left);
        TreeNode rightList = treeToList(root.right);

        // Merge linked lists together
        root = mergeLists(leftList, root);
        root = mergeLists(root, rightList);

        return root;
    }

    // Helper fucntion that merges 2 doubly linked lists into a single list
    private static TreeNode mergeLists(TreeNode a, TreeNode b) {
        if (a == null) return b;
        if (b == null) return a;

        TreeNode aEnd = a.left;
        TreeNode bEnd = b.left;

        a.left = bEnd;
        bEnd.right = a;
        aEnd.right = b;
        b.left = aEnd;
        return a;
    }
    
    // Test cases
    public static void main(String[] args) {
        BinarySearchTree t = new BinarySearchTree();
        t.insert(5);
        t.insert(2);
        t.insert(7);
        t.insert(1);
        t.insert(6);
        System.out.println(t);
        System.out.println(t.contains(2));
        t.delete(5);
        System.out.println(t);
        String ser = BinarySearchTree.serialize(t);
        System.out.println(ser);
        System.out.println(BinarySearchTree.deserialize(ser));

        Trie s = new Trie();
        s.insert("test");
        s.insert("tester");
        s.insert("rest");
        System.out.println(s);
        System.out.println(s.contains("test"));
        System.out.println(s.contains("teste"));
        System.out.println(s.prefix("test"));

        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);

        System.out.println(inOrderTraversalRecursive(root));
        System.out.println(inOrderTraversalIterative(root));

        System.out.println(preOrderTraversalRecursive(root));
        System.out.println(preOrderTraversalIterative(root));

        System.out.println(postOrderTraversalRecursive(root));
        System.out.println(postOrderTraversalIterative(root));

        System.out.println(levelOrderTraversalRecursive(root));
        System.out.println(levelOrderTraversalIterative(root));

        System.out.println(containsValue(root, 4));
        System.out.println(pathToNode(root, 4));

        System.out.println(maxDepth(root));
        System.out.println(lowestCommonAncestor(root, root.left.left, root.left).val);
        System.out.println(isBalanced(root));

        TreeNode root2 = new TreeNode(3);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.left.right = new TreeNode(5);
        System.out.println(mergeTrees(root, root2).val);

        System.out.println(invertTree(root).left.val);
    }
}
