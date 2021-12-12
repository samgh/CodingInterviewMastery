"""
Title: TreeSolutions

This file contains the solutions for the Tree exercises in the DS & Algos
Primer. If you have not already attempted these exercises, we highly
recommend you complete them before reviewing the solutions here.

Exection: python tree_solutions.py
"""

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
            self.val = val
            self.left = None
            self.right = None

    """
    Constructor
    """
    def __init__(self):
        # The only thing we need to store in our object is the root
        self.root = None

    """
    Insert a value into the tree (in order)

    Time Complexity: O(n)
    Space Complexity: O(1)
    """
    def insert(self, n: int):
        # If the tree is empty, make this new node the root
        if not self.root:
            self.root = self._TreeNode(n)
            return

        # Iterate down into the tree until we reach an empty space to
        # insert our value into the tree. We could also do this
        curr = self.root
        while True:
            # If our value is less than the current node, it has to go in
            # the left subtree
            if n < curr.val:
                if curr.left:
                    curr = curr.left
                else:
                    curr.left = self._TreeNode(n)
                    return
            else:
                # Otherwise, our node goes in the right subtree
                if curr.right:
                    curr = curr.right
                else:
                    curr.right = self._TreeNode(n)
                    return

    """
    Delete a value from the BST

    Time Complexity: O(n)
    Space Complexity: O(n)
    """
    def delete(self, n: int, curr: _TreeNode = None):
        # Recursively call our inner function
        if not curr:
            self.root = self.delete(n, self.root)
            return None

        """
        Recursively delete the value from the tree. When we find the value,
        there are 3 possibilities:
           1. It has 0 children - This is trivial, we just delete the node
           2. It has 1 child - This is also easy, we just replace the node
              with it's one child
           3. It has 2 children - This is tricky. We have to replace the node
              with the next larger or smaller node to maintain the proper
              structure of the tree. Then we have to recursively remove that
              node that was moved from it's previous location in the tree
        """

        # Node to remove is in the left subtree
        if n < curr.val:
            curr.left = self.delete(n, curr.left)
            return curr

        # Node to remove is in the right subtree
        if n > curr.val:
            curr.right = self.delete(n, curr.right)
            return curr

        # Otherwise we need to remove the current node. If it has no
        # children, just return None
        if not curr.left and not curr.right:
            return None

        # If it has 1 child, replace the current node with it's child
        if not curr.left:
            return curr.right
        if not curr.right:
            return curr.left

        # Otherwise, find the smallest node in the right subtree and
        # replace the current node with that node. Then delete it from the
        # right subtree
        min = self._min_node(curr.right)

        # Remove min from the right subtree
        right = self.delete(min.val, curr.right)

        # Replace current node with min
        min.left = curr.left
        min.right = right
        return min

    """
    Find the minimum node in a tree. We just do this by iterating as far
    to the left as we can.
    """
    def _min_node(self, curr: _TreeNode) -> _TreeNode:
        while curr.left:
            curr = curr.left
        return curr

    """
    Determine if the tree contains n

    Time Complexity: O(n)
    Space Complexity: O(1)
    """
    def contains(self, n: int) -> bool:
        curr = self.root

        # Iterate until we find the node or get to a null node
        while curr:
            if curr.val == n:
                return True

            # If the node is smaller than the current node its in the left
            # subtree. Otherwise its in the right subtree
            if n < curr.val:
                curr = curr.left
            else:
                curr = curr.right

        return False

    """
    Serialize a BST by converting it into a string

    Time Complexity: O(n)
    Space Complexity: O(n)
    """
    def serialize(self) -> str:
        # We will use a simple serialization that follows a pre-order
        # traversal. We need to remember to track all the null nodes as
        # well so that we can properly reconstruct our tree
        ser = []
        self._serialize_inner(self.root, ser)
        return " ".join(ser)

    """
    Inner function to iterate over tree recursively and add each node to
    our serialized string.
    """
    def _serialize_inner(self, curr: _TreeNode, ser: List[str]):
        # Add the current value (or null) to our string, then go left, then
        # go right
        if not curr:
            ser.append("None")
            return

        ser.append(str(curr.val))
        self._serialize_inner(curr.left, ser)
        self._serialize_inner(curr.right, ser)

    """
    Deserialize a serialized BST

    Time Complexity: O(n)
    Space Complexity: O(n)
    """
    def deserialize(self, serialized: str):
        vals = serialized.split(" ")

        # Deserialize the tree
        root = self._deserialize_inner(vals)

        # Convert it from a TreeNode back into an actual BST object
        result = BinarySearchTree()
        result.root = root
        return result

    """
    We deserialize our tree by traversing the tree in the same order as
    we did to serialize it and constructing the tree as we go.
    """
    def _deserialize_inner(self, vals):
        # Get the current node
        val_str = vals.pop(0)

        # If it's null, then there is no subtree on this side
        if val_str == "None":
            return None

        # Otherwise, continue with the preorder traversal that we did with
        # our serialization
        val = int(val_str)
        curr = self._TreeNode(val)
        curr.left = self._deserialize_inner(vals)
        curr.right = self._deserialize_inner(vals)

        return curr

    """
    Convert the tree to a string

    Time Complexity: O(n)
    Space Complexity: O(n)
    """
    def __str__(self) -> str:
        result = []

        # In this case, we'll just return all the node values in order
        self._in_order_traversal(self.root, result)
        return " ".join(result)

    """
    Do an inorder traversal to generate the string
    """
    def _in_order_traversal(self, curr: _TreeNode, result: List[str]):
        if not curr:
            return

        self._in_order_traversal(curr.left, result)
        result.append(str(curr.val))
        self._in_order_traversal(curr.right, result)

"""
Exercise 1.2: Implement a trie class
"""
class Trie:

    """
    Simple Trie Node class
    """
    class _TrieNode:
        def __init__(self, val: str = '-', is_end_of_word: bool = False):
            self.val = val

            # This is an n-ary tree so we can have many children. We also want
            # to be able to easily reference them by their value, so we use a dict
            self.children = {}

            # We need to know whether we are at the end of a word or not.
            # Otherwise we don't know if a path is a prefix or full word
            self.is_end_of_word = is_end_of_word

    def __init__(self):
        # To make our lives easy, we'll use a dummy root node whose children
        # are the actual first characters of our strings
        self.root = self._TrieNode()

    """
    Insert a string into our trie

    Time Complexity: O(s.length())
    Space Complexity: O(s.length())
    """
    def insert(self, s: str):
        if not s or len(s) == 0:
            return

        self._insert_inner(s, self.root, 0)

    """
    Inner function to insert value. We traverse the tree as long as the
    characters already exist. If they don't, then we build the path in
    the tree
    """
    def _insert_inner(self, s: str, curr: _TrieNode, idx: int):
        # We're at the end of the string, so mark that node as the end of
        # a word
        if idx == len(s):
            curr.is_end_of_word = True
            return

        # Determine whether the next character is already in the trie. If
        # it is, we just advance to the node that already exists. If not,
        # we need to create a new node
        curr_char = s[idx]
        if (not curr_char in curr.children):
            curr.children[curr_char] = self._TrieNode(curr_char, False)

        curr = curr.children[curr_char]
        self._insert_inner(s, curr, idx+1)

    """
    Does trie contain s

    Time Complexity: O(s.length())
    Space Complexity: O(1)
    """
    def contains(self, s: str) -> bool:
        curr = self.root

        # Attempt to traverse the path in the trie for the string that we're
        # searching for. If at any point, the node we're looking for doesn't
        # exist, then the string is not in the trie
        for i in range(len(s)):
            curr_char = s[i]
            if not curr_char in curr.children:
                return False
            curr = curr.children[curr_char]

        # If we found s but the last node is not EndOfWord, then s is a
        # prefix of a word in our trie but not a standalone word
        return curr.is_end_of_word

    """
    Find all strings in the trie with a given prefix

    Time Complexity: O(# strings with prefix * length of strings with prefix)
    Space Complexity: O(length of longest string with prefix)
    """
    def prefix(self, pre: str = "") -> List[str]:
        # First, we find the end of the prefix in the trie. We have to
        # traverse to the last character. If the prefix doesn't exist, then
        # there are no strings in the trie with that prefix
        curr = self.root
        for i in range(len(pre)):
            curr_char = pre[i]
            if not curr_char in curr.children:
                return []
            curr = curr.children[curr_char]

        results = []

        # We'll pass in the prefix, so that we prepend that to all the
        # words that we generate starting at curr
        current_string = [pre]

        # If prefix itself is a word, add it to the results
        if curr.is_end_of_word:
            results.append("".join(current_string))

        # For every child node of the prefix, find all the words starting
        # with that character
        for c in curr.children.values():
            self._all_strings(c, current_string, results)

        return results

    """
    Given a TrieNode, find all words that start at that node
    """
    def _all_strings(self, curr: _TrieNode, current_string: List[str], result: List[str]):
        # Add the current character to our result string
        current_string.append(curr.val)

        # If we are at the end of a word, add the whole string to our results
        if curr.is_end_of_word:
            result.append("".join(current_string))

        # For all the children, continue to traverse
        for c in curr.children.values():
            self._all_strings(c, current_string ,result)

        # Backtracking. When we return back up, we want to remove the
        # character that we added to the end of currentString to reset our
        # state
        current_string.pop()

    """
    Convert trie into a string

    Time Complexity: O(# strings in trie * average string length)
    Space Complexity: O(length of longest string in trie)
    """
    def __str__(self) -> str:
        # We can just use our prefix function to get a list of all strings
        # in our trie
        return " ".join(self.prefix())


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

Time Complexity: O(nodes in tree)
Space Complexity: O(nodes in tree)
"""
def in_order_traversal_recursive(root: TreeNode) -> List[int]:
    # We will fill our results into this list
    result = []

    # Recursive call
    in_order_traversal_recursive_inner(root, result)
    return result

"""
Inner recursive function
"""
def in_order_traversal_recursive_inner(curr: TreeNode, result: List[int]):
    if not curr:
        return

    # In order, we want to go left, then root, then right
    in_order_traversal_recursive_inner(curr.left, result)
    result.append(curr.val)
    in_order_traversal_recursive_inner(curr.right, result)

"""
Inorder Traversal - Iterative

Time Complexity: O(nodes in tree)
Space Complexity: O(nodes in tree)
"""
def in_order_traversal_iterative(root: TreeNode) -> List[int]:
    if not root:
        return []

    # We can emulate what we do recursively by using a stack
    stack = []
    result = []

    stack.append(root)
    curr = root.left

    # We go all the way to the left in our tree, then we backtrack by
    # popping items off of our stack. This allows us to keep track of the
    # proper order
    while len(stack) > 0 or curr:
        # Go all the way to the left
        while curr:
            stack.append(curr)
            curr = curr.left

        # Once we've reached the end, include the current node and then
        # go to the right
        curr = stack.pop()
        result.append(curr.val)
        curr = curr.right

    return result

"""
Preorder Traversal - Recursive

Time Complexity: O(nodes in tree)
Space Complexity: O(nodes in tree)
"""
def pre_order_traversal_recursive(root: TreeNode) -> List[int]:
    # We will fill our results into this list
    result = []

    # Recursive call
    pre_order_traversal_recursive_inner(root, result)
    return result

"""
Inner recursive function
"""
def pre_order_traversal_recursive_inner(curr: TreeNode, result: List[int]):
    if not curr:
        return

    # Pre order, we want to go root, then left, then right
    result.append(curr.val)
    pre_order_traversal_recursive_inner(curr.left, result)
    pre_order_traversal_recursive_inner(curr.right, result)

"""
Preorder Traversal - Iterative

Time Complexity: O(nodes in tree)
Space Complexity: O(nodes in tree)
"""
def pre_order_traversal_iterative(root: TreeNode) -> List[int]:
    if not root:
        return []

    # We can emulate what we do recursively by using a stack
    stack = []
    result = []

    stack.append(root)

    # First we add the current node, then we'll want to go left first, then
    # right. So we add those to our stack in reverse order
    while len(stack) > 0:
        curr = stack.pop()
        result.append(curr.val)

        # Our stack is LIFO, so add the thing we want to do last to the
        # stack first
        if curr.right:
            stack.append(curr.right)
        if curr.left:
            stack.append(curr.left)

    return result

"""
Postorder Traversal - Recursive

Time Complexity: O(nodes in tree)
Space Complexity: O(nodes in tree)
"""
def post_order_traversal_recursive(root: TreeNode) -> List[int]:
    # We will fill our results into this list
    result = []

    # Recursive call
    post_order_traversal_recursive_inner(root, result)
    return result

"""
Inner recursive function
"""
def post_order_traversal_recursive_inner(curr: TreeNode, result: List[int]):
    if not curr:
        return

    # Post order, we want to go left, then right, then root
    post_order_traversal_recursive_inner(curr.left, result)
    post_order_traversal_recursive_inner(curr.right, result)
    result.append(curr.val)

"""
Postorder Traversal - Iterative

Time Complexity: O(nodes in tree)
Space Complexity: O(nodes in tree)
"""
def post_order_traversal_iterative(root: TreeNode) -> List[int]:
    if not root:
        return []

    # We can emulate what we do recursively by using a stack
    stack = []
    result = []

    stack.append(root)

    # This one is easier to do in reverse order. We'll add the roots to
    # the results first, then the children. Then all we have to do is
    # reverse our result
    while len(stack) > 0:
        curr = stack.pop()
        result.append(curr.val)

        if curr.right:
            stack.append(curr.left)
        if curr.left:
            stack.append(curr.right)

    result.reverse()
    return result

"""
Levelorder Traversal - Recursive

Time Complexity: O(nodes in tree)
Space Complexity: O(nodes in tree)
"""
def level_order_traversal_recursive(root: TreeNode) -> List[int]:
    # We will get a list for each level and then merge them together
    levels = []

    # Recursive call
    level_order_traversal_recursive_inner(root, levels, 0)

    result = []
    TODO flatten
    for level in levels:
        for val in level:
            result.append(val)

    return result

"""
Inner recursive function
"""
def level_order_traversal_recursive_inner(curr: TreeNode, levels: List[List[int]], level: int):
    if not curr:
        return

    # If this is the first node in this level, we need to create a new list
    if level >= len(levels):
        levels.append([])

    # Add the node to the list for the appropriate level
    levels[level].append(curr.val)

    # Continue our recursion
    level_order_traversal_recursive_inner(curr.left, levels, level+1)
    level_order_traversal_recursive_inner(curr.right, levels, level+1)

"""
Levelorder Traversal - Iterative

Time Complexity: O(nodes in tree)
Space Complexity: O(nodes in tree)
"""
def level_order_traversal_iterative(root: TreeNode) -> List[int]:
    # Fairly standard BFS implementation. We will use a queue to track
    # which nodes we've visited so far
    to_visit = []
    to_visit.append(root)

    result = []

    # Get the current node, add it to our result, and then add the children
    # to the queue
    while len(to_visit) > 0:
        TODO queue
        curr = to_visit[0]
        to_visit = to_visit[1:]
        result.append(curr.val)

        # Add any existing children to the queue
        if curr.left:
            to_visit.append(curr.left)
        if curr.right:
            to_visit.append(curr.right)

    return result

"""
Exercise 3.1: Implement a function that determines whether a Binary Tree
contains a value.

Time Complexity: O(nodes in tree)
Space Complexity: O(nodes in tree)
"""
def contains_value(root: TreeNode, val: int) -> bool:
    # If None, we didn't find the value
    if not root:
        return False

    # We found the value so return true
    if root.val == val:
        return True

    # See if the value is in either subtree
    return contains_value(root.left, val) or contains_value(root.right, val)

"""
Exercise 3.2: Implement a function that determines whether a Binary Tree
contains a value. If the tree contains the value, return the path to that
node

Time Complexity: O(nodes in tree)
Space Complexity: O(nodes in tree)
"""
def path_to_node(root: TreeNode, val: int) -> List[int]:
    # If None, there is no path so return None
    if not root:
        return None

    # If we found the value, the path from that node to itself is just
    # [root.val]
    if root.val == val:
        return [root.val]

    # Get the paths from the child nodes to the target node
    left = path_to_node(root.left, val)
    right = path_to_node(root.right, val)

    # If there is a path from the left child to the target, prepend the
    # current value to that path
    if left:
        TODO append list
        result = [root.val]
        result.append(left)
        return result

    # If there is a path from the right child to the target, prepend the
    # current value to that path
    if right:
        result = [root.val]
        result.append(right)
        return result

    # If neither child has a path to the target node, then root doesn't
    # have a path either so return None
    return None


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
    """
    System.out.println(maxDepth(root));
    System.out.println(lowestCommonAncestor(root, root.left.left, root.left).val);
    System.out.println(isBalanced(root));

    TreeNode root2 = new TreeNode(3);
    root2.left = new TreeNode(2);
    root2.left.left = new TreeNode(4);
    root2.left.left.right = new TreeNode(5);
    System.out.println(mergeTrees(root, root2).val);

    System.out.println(invertTree(root).left.val);

    System.out.println(diameterOfBinaryTree(root));

    System.out.println(treeToList(root));
"""
