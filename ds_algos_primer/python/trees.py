"""
Title: TreeSolutions

This file contains the template for the Tree exercises in the DS & Algos
Primer. If you have not already attempted these exercises, we highly
recommend you complete them before reviewing the solutions here.

Exection: python tree_solutions.py
"""

import queue
from typing import List

"""
Exercise 1.1: Implement a Binary Search Tree class
"""
class BinarySearchTree:

    """
    Simple TreeNode class
    """
    class _TreeNode:
        def __init__(self, val: int = 0):
            # INSERT YOUR SOLUTION HERE

    """
    Constructor
    """
    def __init__(self):
        # INSERT YOUR SOLUTION HERE

    """
    Insert a value into the tree (in order)

    Time Complexity:
    Space Complexity:
    """
    def insert(self, n: int):
        # INSERT YOUR SOLUTION HERE

    """
    Delete a value from the BST

    Time Complexity:
    Space Complexity:
    """
    def delete(self, n: int, curr: _TreeNode = None):
        # INSERT YOUR SOLUTION HERE

    """
    Determine if the tree contains n

    Time Complexity:
    Space Complexity:
    """
    def contains(self, n: int) -> bool:
        # INSERT YOUR SOLUTION HERE

    """
    Serialize a BST by converting it into a string

    Time Complexity:
    Space Complexity:
    """
    def serialize(self) -> str:
        # INSERT YOUR SOLUTION HERE

    """
    Deserialize a serialized BST

    Time Complexity:
    Space Complexity:
    """
    def deserialize(self, serialized: str):
        # INSERT YOUR SOLUTION HERE

    """
    Convert the tree to a string

    Time Complexity:
    Space Complexity:
    """
    def __str__(self) -> str:
        # INSERT YOUR SOLUTION HERE


"""
Exercise 1.2: Implement a trie class
"""
class Trie:

    """
    Simple Trie Node class
    """
    class _TrieNode:
        def __init__(self, val: str = '-', is_end_of_word: bool = False):
            # INSERT YOUR SOLUTION HERE

    def __init__(self):
        # INSERT YOUR SOLUTION HERE
    """
    Insert a string into our trie

    Time Complexity:
    Space Complexity:
    """
    def insert(self, s: str):
        # INSERT YOUR SOLUTION HERE

    """
    Does trie contain s

    Time Complexity:
    Space Complexity:
    """
    def contains(self, s: str) -> bool:
        # INSERT YOUR SOLUTION HERE

    """
    Find all strings in the trie with a given prefix

    Time Complexity:
    Space Complexity:
    """
    def prefix(self, pre: str = "") -> List[str]:
        # INSERT YOUR SOLUTION HERE

    """
    Convert trie into a string

    Time Complexity:
    Space Complexity:
    """
    def __str__(self) -> str:
        # INSERT YOUR SOLUTION HERE


"""
Simple TreeNode class for remaining exercises
"""
class TreeNode:
    def __init__(self, val: int):
        self.val = val
        self.left = None
        self.right = None

"""
Exercise 2.1: Implement each tree traversal. You should implement each
traversal both recursively and iteratively.
"""

"""
Inorder Traversal - Recursive

Time Complexity:
Space Complexity:
"""
def in_order_traversal_recursive(root: TreeNode) -> List[int]:
    # INSERT YOUR SOLUTION HERE

"""
Inorder Traversal - Iterative

Time Complexity:
Space Complexity:
"""
def in_order_traversal_iterative(root: TreeNode) -> List[int]:
    # INSERT YOUR SOLUTION HERE

"""
Preorder Traversal - Recursive

Time Complexity:
Space Complexity:
"""
def pre_order_traversal_recursive(root: TreeNode) -> List[int]:
    # INSERT YOUR SOLUTION HERE

"""
Preorder Traversal - Iterative

Time Complexity:
Space Complexity:
"""
def pre_order_traversal_iterative(root: TreeNode) -> List[int]:
    # INSERT YOUR SOLUTION HERE

"""
Postorder Traversal - Recursive

Time Complexity:
Space Complexity:
"""
def post_order_traversal_recursive(root: TreeNode) -> List[int]:
    # INSERT YOUR SOLUTION HERE

"""
Postorder Traversal - Iterative

Time Complexity:
Space Complexity:
"""
def post_order_traversal_iterative(root: TreeNode) -> List[int]:
    # INSERT YOUR SOLUTION HERE

"""
Levelorder Traversal - Recursive

Time Complexity:
Space Complexity:
"""
def level_order_traversal_recursive(root: TreeNode) -> List[int]:
    # INSERT YOUR SOLUTION HERE

"""
Levelorder Traversal - Iterative

Time Complexity:
Space Complexity:
"""
def level_order_traversal_iterative(root: TreeNode) -> List[int]:
    # INSERT YOUR SOLUTION HERE

"""
Exercise 3.1: Implement a function that determines whether a Binary Tree
contains a value.

Time Complexity:
Space Complexity:
"""
def contains_value(root: TreeNode, val: int) -> bool:
    # INSERT YOUR SOLUTION HERE

"""
Exercise 3.2: Implement a function that determines whether a Binary Tree
contains a value. If the tree contains the value, return the path to that
node

Time Complexity:
Space Complexity:
"""
def path_to_node(root: TreeNode, val: int) -> List[int]:
    # INSERT YOUR SOLUTION HERE

"""
Exercise 4.1: Max Binary Tree Depth

Time Complexity:
Space Complexity:
"""
def max_depth(root: TreeNode) -> int:
    # INSERT YOUR SOLUTION HERE

"""
Exercise 4.2: Lowest Common Ancestor

Time Complexity:
Space Complexity:
"""
def lowest_common_ancestor(root: TreeNode, p: TreeNode, q: TreeNode) -> TreeNode:
    # INSERT YOUR SOLUTION HERE

"""
Exercise 4.3: Balanced Binary Tree

Time Complexity:
Space Complexity:
"""
def is_balanced(root: TreeNode) -> bool:
    # INSERT YOUR SOLUTION HERE

"""
Exercise 4.4: Merge Binary Trees

Time Complexity:
Space Complexity:
"""
def merge_trees(t1: TreeNode, t2: TreeNode) -> TreeNode:
    # INSERT YOUR SOLUTION HERE

"""
Exercise 4.5 Invert Binary Tree

Time Complexity:
Space Complexity:
"""
def invert_tree(root: TreeNode) -> TreeNode:
    # INSERT YOUR SOLUTION HERE

"""
Exercise 4.6: Diameter of Binary Tree

Time Complexity:
Space Complexity:
"""
def diameter_of_binary_tree(root: TreeNode) -> int:
    # INSERT YOUR SOLUTION HERE

"""
Exercise 4.7: Tree to Linked List

Time Complexity:
Space Complexity:
"""
def tree_to_list(root: TreeNode) -> TreeNode:
    # INSERT YOUR SOLUTION HERE


if __name__ == '__main__':
    t = BinarySearchTree();
    t.insert(5);
    t.insert(2);
    t.insert(7);
    t.insert(1);
    t.insert(6);
    print(t.root.val)

    print(t.contains(2))
    t.delete(5);
    print(t);

    ser = t.serialize()
    print(ser);

    print(BinarySearchTree().deserialize(ser))

    s = Trie();
    s.insert("test");
    s.insert("tester");
    s.insert("rest");
    print(s);

    print(s.contains("test"))
    print(s.contains("teste"))

    print(s.prefix("test"))

    root = TreeNode(5);
    root.left = TreeNode(3);
    root.left.left = TreeNode(1);
    root.left.right = TreeNode(4);
    root.right = TreeNode(8);
    root.right.left = TreeNode(7);
    root.right.right = TreeNode(9);

    print(in_order_traversal_recursive(root))
    print(in_order_traversal_iterative(root));

    print(pre_order_traversal_recursive(root))
    print(pre_order_traversal_iterative(root))

    print(post_order_traversal_recursive(root));
    print(post_order_traversal_iterative(root));

    print(level_order_traversal_recursive(root));
    print(level_order_traversal_iterative(root));

    print(contains_value(root, 4));
    print(path_to_node(root, 4));

    print(max_depth(root));
    print(lowest_common_ancestor(root, root.left.left, root.left).val);
    print(is_balanced(root));


    root2 = TreeNode(3);
    root2.left = TreeNode(2);
    root2.left.left = TreeNode(4);
    root2.left.left.right = TreeNode(5);
    print(merge_trees(root, root2).val);

    print(invert_tree(root).left.val);

    print(diameter_of_binary_tree(root));
    print(tree_to_list(root));
