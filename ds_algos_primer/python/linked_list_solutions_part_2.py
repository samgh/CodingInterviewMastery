"""
Title: Linked List Solutions Part 2

This file contains the solutions for Exercise Sets #2-5 of the Linked List
exercises in the DS & Algos Primer. If you have not already attempted these
exercises, we highly recommend you complete them before reviewing the
solutions here.

Execution: python linked_list_solutions_part_2.py
"""

"""
A simple singly-linked node class (copied from Part 1)
"""
class SinglyLinkedListNode:
    def __init__(self, val=0):
        self.val = val
        self.next = None

"""
A simple doubly-linked node class (copied from Part 1)
"""
class DoublyLinkedListNode:
    def __init__(self, val=0):
        self.val = val
        self.prev = None
        self.next = None

"""
Exercise 2.1: Write a function that swaps two nodes in a doubly-linked list

Time Complexity: O(max(n, m))
Space Complexity: O(1)
"""
def swap_nodes(l: DoublyLinkedListNode, n: int, m: int):
    if n < 0 or m < 0:
        raise IndexError()
    if n == m:
        return

    # First get pointers to the nodes at the two indices we want to swap
    nNode = l
    mNode = l
    curr = l
    for i in range(max(n,m)+1):
        # If we reach the end of the list, then one of our indices is out of
        # bounds
        if not curr:
            raise IndexError()
        if i == n:
            nNode = curr
        if i == m:
            mNode = curr
        curr = curr.next

    # We're going to swap these pointers in mNode, so we need to save them for
    # later
    mNodePrev = mNode.prev
    mNodeNext = mNode.next

    # Insert mNode into the position where nNode was by swapping the pointers
    # from other nodes to mNode and the pointers from mNode to point to those
    # before and after nNode
    mNode.prev = nNode.prev
    mNode.next = nNode.next
    if mNode.prev:
        mNode.prev.next = mNode
    if mNode.next:
        mNode.next.prev = mNode

    # Do the same for nNode
    nNode.prev = mNodePrev
    nNode.next = mNodeNext
    if nNode.prev:
        nNode.prev.next = nNode
    if nNode.next:
        nNode.next.prev = nNode

"""
Exercise 2.2: Write a function that removes the odd-indexed values from a
singly-linked list

Time Complexity: O(length(l))
Space Complexity: O(1)
"""
def remove_odd(l: SinglyLinkedListNode):
    curr = l
    while curr and curr.next:
        curr.next = curr.next.next
        curr = curr.next

"""
Exercise 2.3: Write a function that de-interleaves the even and odd indices in a
singly-linked list. Your resulting list should have all the even indices first
followed by all the odd indices

Time Complexity: O(length(l))
Space Complexity: O(1)
"""
def deinterleave(l: SinglyLinkedListNode):
    if l == None or l.next == None:
        return

    # We will start by separating the nodes into two separate lists. Then it is
    # easy to combine one with the other
    curr = l

    # evens and odds track the current end node of the list of even and odd
    # nodes respectively
    evens = l
    odds = l.next

    # We need to keep track of the beginning of the odds so we can point the end
    # of evens to the beginning of odds once we're done separating them
    oddsStart = odds

    # Track whether the current index is even or odd
    isEven = True

    while curr:
        next = curr.next

        # Depending whether the index is even or odd, we add the current node to
        # the end of the evens or odds list
        if isEven:
            evens.next = curr
            evens = evens.next
        else:
            odds.next = curr
            odds = odds.next
        curr = next
        isEven = not isEven

    # Make sure that if the last value of the list is even, we point the end of
    # the list to null
    odds.next = None

    # Merge the two lists together
    evens.next = oddsStart

"""
Exercise 2.4: Write a function that reverses a singly-linked list

Time Complexity: O(length(l))
Space Complexity: O(1)
"""
def reverse(l: SinglyLinkedListNode) -> SinglyLinkedListNode:
    # Track the previous because we will point the current node back
    prev = None
    curr = l

    # For each node, switch it to point to the node in front of it. The order of
    # steps here is very important
    while curr:
        next = curr.next
        curr.next = prev
        prev = curr
        curr = next

    return prev

"""
Exercise 3.1: Write a function that compares 2 singly-linked lists and returns
true if the two lists are identical

Time Complexity: O(min(length(l1), length(l2)))
Space Complexity: O(1)
"""
def are_equal(l1: SinglyLinkedListNode, l2: SinglyLinkedListNode) -> bool:
    # Iterate over both lists simultaneously and compare each value
    while l1 and l2:
        if l1.val != l2.val:
            return false
        l1 = l1.next
        l2 = l2.next

    # If there are no values that don't match, the lists are equal
    return not l1 and not l2

"""
Exercise 3.2: Write a function that returns the nth-to-last value in a
singly-linked list


Time Complexity: O(length(l))
Space Complexity: O(1)
"""
def nth_to_last(l: SinglyLinkedListNode, n: int) -> SinglyLinkedListNode:
    # We will use a fast and slow pointer that are exactly n nodes apart. When
    # fast reaches the end of the list, slow will be at the nth-to-last element
    fast = l
    slow = l

    # Advance fast pointer to the nth position
    for i in range(n):
        if not fast:
            raise IndexError()
        fast = fast.next

    # Increment both pointers together
    while fast:
        fast = fast.next
        slow = slow.next

    return slow

"""
Exercise 3.3: Write a function that returns the value at the midpoint of a
singly-linked list. You can assume the length of the list is odd.

Time Complexity: O(length(l))
Space Complexity: O(1)
"""
def midpoint(l: SinglyLinkedListNode) -> SinglyLinkedListNode:
    if l.next == None:
        return l

    # We're using a fast and slow pointer again to find the middle
    fast = l.next
    slow = l

    # Advance the fast pointer twice as fast as the slow pointer. That way when
    # it reaches the end, the slow pointer will be in the middle
    while fast and fast.next:
        fast = fast.next.next
        slow = slow.next

    return slow

"""
Exercise 4.1: Remove all occurrences of n from a singly-linked list

Time Complexity: O(length(l))
Space Complexity: O(1)
"""
def remove_all(l: SinglyLinkedListNode, n: int) -> SinglyLinkedListNode:
    # Use a dummy node to make it easy if we have to remove the first node in
    # the list
    dummy = SinglyLinkedListNode(0)
    curr = dummy
    dummy.next = l

    # Iterate over the list
    while curr:
        # If the next value needs to be removed, skip it. We may need to do this
        # multiple times if there are multiple of the value in a row
        while curr.next and curr.next.val == n:
            curr.next = curr.next.next
        curr = curr.next

    # dummy.next points to the current head of the list
    return dummy.next

"""
Exercise 5.1: Given a singly-linked list, determine if the list contains a
cycle. DO NOT use Floyd’s algorithm. FInd some other method for identifying a
cycle

Time Complexity: O(length(l))
Space Complexity: O(length(l))
"""
def has_cycle_naive(l: SinglyLinkedListNode) -> bool:
    # Add all nodes to a set and see if we visit them more than once
    visited = set()

    # Iterate over the list
    while l:
        # If we've already visited the node, we have a cycle
        if l in visited:
            return True

        # Otherwise add it to the set and continue iterating
        visited.add(l)
        l = l.next

    # If we get to the end of the list there is no cycle
    return False

"""
Exercise 5.2: Given a singly-linked list, determine if the list contains a cycle
using Floyd's algorithm

Time Complexity: O(length(l))
Space Complexity: O(1)
"""
def has_cycle(l: SinglyLinkedListNode) -> bool:
    if l == None:
        return False

    # Initialize a slow pointer and fast pointer
    slow = l
    fast = l.next

    # Fast increments by 2 and slow increments by 1. If they are ever equal then
    # there is a cycle. Otherwise if fast reaches the end of the list there is
    # no cycle
    while fast and fast.next:
        if slow == fast:
            return True
        slow = slow.next
        fast = fast.next.next

    return False

"""
We've included some helper methods below that you can use for your tests
"""

"""
Test method to generate singly linked list with n items
"""
def single_generator(n: int) -> SinglyLinkedListNode:
    head = SinglyLinkedListNode(1)
    curr = head
    for i in range(2, n+1):
        curr.next = SinglyLinkedListNode(i)
        curr = curr.next

    return head

"""
Test method to generate doubly linked list with n items
"""
def double_generator(n: int) -> DoublyLinkedListNode:
    head = DoublyLinkedListNode(1)
    curr = head
    for i in range(2, n+1):
        curr.next = DoublyLinkedListNode(i)
        curr.next.prev = curr
        curr = curr.next

    return head

"""
Test method to print singly linked list
"""
def print_single(n: SinglyLinkedListNode):
    curr = n
    string = []
    while curr:
        string.append(str(curr.val) + " -> ")
        curr = curr.next
    string.append("null")
    print(''.join(string))

"""
Test method to print doubly linked list
"""
def print_double(n: DoublyLinkedListNode):
    if not n:
        print("null")
    curr = n
    string = []
    while curr:
        string.append(str(curr.val) + " -> ")
        curr = curr.next
    string.append("null")
    print(''.join(string))


if __name__ == '__main__':
    d = double_generator(4)
    swap_nodes(d, 1, 3);
    print_double(d);

    l = single_generator(4);
    remove_odd(l);
    print_single(l);

    l = single_generator(9);
    deinterleave(l);
    print_single(l);

    l = single_generator(5);
    l = reverse(l);
    print_single(l);

    print(are_equal(single_generator(5), single_generator(5)));

    print(nth_to_last(single_generator(5), 2).val);

    print(midpoint(single_generator(5)).val);

    l = SinglyLinkedListNode(1);
    l.next = SinglyLinkedListNode(1);
    l.next.next = SinglyLinkedListNode(2);
    l.next.next.next = SinglyLinkedListNode(3);
    l.next.next.next.next = SinglyLinkedListNode(1);
    print_single(remove_all(l, 1));

    l = single_generator(5);
    l.next.next.next.next.next = l.next.next;
    print(has_cycle_naive(l));
    print(has_cycle(l));
