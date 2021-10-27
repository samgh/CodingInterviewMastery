"""
Title: Linked List Solutions Part 1

This file contains the solutions for Exercise Set #1 of the Linked List
exercises in the DS & Algos Primer. If you have not already attempted these
exercises, we highly recommend you complete them before reviewing the solutions
here.

Execution: python linked_list_solutions_part_1.py
"""

"""
Exercise 1.1: Implement a singly-linked list
"""

"""
Singly linked list class
"""
class SinglyLinkedList:

    """
    A simple singly-linked node class
    """
    class _SinglyLinkedListNode:
        def __init__(self, val=0):
            self.val = val
            self.next = None

    """
    Constructor
    """
    def __init__(self):
        self.head = None
        self.length = 0

    """
    Insert new node at the head of the list
    """
    def insert(self, n: int):
        newNode = self._SinglyLinkedListNode(n)

        # Since we're inserting this at the head, it doesn't matter whether head
        # is null or not. We just create a new node and point it to the previous
        # head
        newNode.next = self.head
        self.head = newNode

        # Efficient to track size as we add and remove nodes
        self.length = self.length+1

    """
    Delete the first occurrence of n from the list
    """
    def delete(self, n: int) -> bool:
        # If the list is empty, there's nothing to remove
        if self.length == 0:
            return False

        # To remove a node, we simply remove the pointer to it. That means
        # we'll want to point the node before it (prev) to the one after
        # it (curr.next). We don't have a previous pointer, so we need to
        # track the previous node
        prev = None
        curr = self.head

        # Iterate over the list looking for n
        while curr:
            # If we find the value, remove it
            if curr.val == n:
                # If the value is the first item in our list, we need to handle
                # this differently
                if not prev:
                    self.head = curr.next
                else:
                    prev.next = curr.next

                # We removed a node so update the size
                self.length = self.length-1
                return True

            # Increment prev and curr. DO NOT do "prev.next" because it  will
            # fail on the initial pass when prev == None
            prev = curr
            curr = curr.next

        return False

    """
    Return the number of items in the list
    """
    def size(self) -> int:
        return self.length

    """
    Convert the list to a string
    """
    def __str__(self):
        curr = self.head
        string = []

        # Iterate over the list and add everything to the string
        while curr:
            string.append(str(curr.val))
            string.append(" -> ")
            curr = curr.next

        string.append("null")
        return ''.join(string)

"""
Exercise 1.2: Implement a doubly-linked list
"""

"""
Doubly linked list class
"""
class DoublyLinkedList:

    """
    A simple doubly-linked node class
    """
    class _DoublyLinkedListNode:
        def __init__(self, val=0):
            self.val = val
            self.prev = None
            self.next = None

    """
    Constructor
    """
    def __init__(self):
        self.head = None
        self.tail = None
        self.length = 0

    """
    Insert node at the front of the list
    """
    def insert(self, n: int):
        newNode = self._DoublyLinkedListNode(n);

        # If list is empty (head is null) the node becomes head and tail
        if not self.head:
            self.head = newNode
            self.tail = newNode
        else:
            # We need both point the new node to the old head and also point the
            # old head back to the current node
            newNode.next = self.head
            self.head.prev = newNode
            self.head = newNode

        self.length = self.length+1

    """
    Delete the first occurrence of n from the list
    """
    def delete(self, n: int) -> bool:
        # If the list is empty we can't remove anything
        if self.length == 0:
            return false

        curr = self.head

        # Find the node in the list. We don't need to track the previous node
        # since we already have a pointer to it
        while curr:
            if curr.val == n:
                # If it is the first node, update the head
                if not curr.prev:
                    self.head = curr.next
                else:
                    curr.prev.next = curr.next

                # If it is the last node, update the tail
                if not curr.next:
                    self.tail = curr.prev
                else:
                    curr.next.prev = curr.prev

                self.length = self.length-1
                return True

            curr = curr.next

        return False

    """
    Return the number of items in the list
    """
    def size(self) -> int:
        return self.length

    """
    Convert the list to a string
    """
    def __str__(self):
        if self.length == 0:
            return "null"

        curr = self.head
        string = []
        string.append("null <- ")

        # Iterate over the list and add everything to the string
        while curr.next:
            string.append(str(curr.val))
            string.append(" <-> ")
            curr = curr.next

        string.append(str(curr.val) + " -> null")
        return ''.join(string)

"""
Sample test cases
"""
if __name__ == '__main__':
    l = SinglyLinkedList()
    for i in range(6,0,-1):
        l.insert(i)
    print(l)
    print(l.delete(1))
    print(l.delete(4))
    print(l)
    print(l.size())

    d = DoublyLinkedList()
    for i in range(6,0,-1):
        d.insert(i)
    print(d)
    print(d.delete(1))
    print(d.delete(4))
    print(d)
    print(d.size())
