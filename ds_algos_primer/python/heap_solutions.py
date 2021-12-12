"""
Title: Heap Solutions

This file contains the solutions for Exercise Sets of the Heap exercises in the
DS & Algos Primer. If you have not already attempted these exercises, we highly
recommend you complete them before reviewing the solutions here.

Execution: python heap_solutions.py
"""

from queue import PriorityQueue
from typing import List

"""
Exercise 1.1: Implement a min heap
"""
class MinHeap:

    """
    Constructor
    """
    def __init__(self):
        self.data = []

    """
    Insert an item into the heap

    Time Complexity: O(log n)
    Space Complexity: O(1)
    """
    def insert(self, x: int):
        # To insert, we add the item at the next open space in the heap and then
        # bubble up until the value is in the right place to satisfy the
        # constraints of the heap
        self.data.append(x)

        # We could do this inline or break it out into a separate function
        self._bubbleUp(len(self.data)-1)

    """
    Get the smallest value in the heap

    Time Complexity: O(1)
    Space Complexity: O(1)
    """
    def peek(self) -> int:
        if len(self.data) == 0:
            raise IndexError()

        # This is easy because our data is already in order
        return self.data[0]

    """
    Remove and return the smallest value in the heap

    Time Complexity: O(log n)
    Space Complexity: O(log n)
    """
    def pop(self) -> int:
        # The first part is the same as peek()
        if len(self.data) == 0:
            raise IndexError()

        result = self.data[0];

        # We replace the root of our tree with the last value in the tree, then
        # we bubble down until our constraints are satisfied
        self.data[0] = self.data.pop()

        # We could do this inline or break it out
        self._bubbleDown(0);

        return result;

    """
    Get the size of the heap

    Time Complexity: O(1)
    Space Complexity: O(1)
    """
    def size(self) -> int:
        return len(self.data)

    """
    Convert the heap data into a string

    Time Complexity: O(n)
    Space Complexity: O(n)
    """
    def __str__(self):
        return str(self.data)

    """
    The following are some optional helper methods. These are not required
    but may make your life easier
    """

    """
    Get the index of the parent node of a given index

    Time Complexity: O(1)
    Space Complexity: O(1)
    """
    def _parent(self, i: int) -> int:
        # It is easy to determine this formula by experimenting with specific
        # examples
        return (i + 1)//2 - 1

    """
    Get the index of the left child of a given index

    Time Complexity: O(1)
    Space Complexity: O(1)
    """
    def _left_child(self, i: int) -> int:
       return i * 2 + 1

    """
    Swap the values at 2 indices in our heap

    Time Complexity: O(1)
    Space Complexity: O(1)
    """
    def _swap(self, i: int, j: int):
        temp = self.data[i]
        self.data[i] = self.data[j]
        self.data[j] = temp

    """
    Starting at index i, bubble up the value until it is at the correct position
    in the heap

    Time Complexity: O(log n)
    Space Complexity: O(1)
    """
    def _bubbleUp(self, i: int):
        # If we are already at the root, we can't bubble up
        if i == 0:
            return

        # While we're below the root, compare current index to the parent. If
        # the parent is greater, then they are out of order so swap them. Repeat
        # until the parent is less than index i
        while i > 0 and self.data[i] < self.data[self._parent(i)]:
           self._swap(i, self._parent(i));
           i = self._parent(i);

    """
    Starting at index i, bubble down the value until it is at the correct
    position in the heap

    Time Complexity: O(log n)
    Space Complexity: O(log n)
    """
    def _bubbleDown(self, i: int):
        # Get the two children of the current index
        left = self._left_child(i)
        right = self._left_child(i)+1

        if left >= len(self.data):
            return

        # If index only has a left child, we just compare it to that value. If
        # it is greater than the left child, swap them
        if right >= len(self.data):
            if self.data[i] > self.data[left]:
                self._swap(i, left);
                return

        # Find the smaller of the two children
        smaller = left
        if self.data[right] < self.data[left]:
            smaller = right

        # If index is larger than the smaller child, swap it with the the
        # smaller child
        if self.data[i] > self.data[smaller]:
            self._swap(i, smaller)

            # Repeat this process until constraints are satisfied
            self._bubbleDown(smaller);

"""
Exercise 1.2: Given an array of integers, determine whether the array represents
a valid heap

Time Complexity: O(n)
Space Complexity: O(1)
"""
def is_valid(heap: List[int]) -> bool:
    # We just have to check if each index is less than it's children. It is a
    # little easier for bounds-checking if we start at the end of the array and
    # work backwards but either way works
    for i in range(len(heap)-1, 1, -1):
        # This is the same formula from parent()
        if heap[i] < heap[(i+1)//2 - 1]:
            return False

    return True

"""
Exercise 2.1: Given a list of integers, use a heap to find the largest value in
the list.

Use the Python PriorityQueue type

Time Complexity: O(n log n)
Space Complexity: O(n)
"""
def find_max(arr: List[int]) -> int:
    heap = PriorityQueue()

    # Add everything to the heap and find the max value
    for i in arr:
        heap.put((-i, i))

    return heap.get()[1]

"""
Exercise 2.2: Given a list of integers, use a heap to sort the list.

Use the Python PriorityQueue type

Time Complexity: O(n log n)
Space Complexity: O(n)
"""
def heap_sort(arr: List[int]) -> List[int]:
    heap = PriorityQueue()

    # Add everything to the heap
    for i in arr:
        heap.put(i)

    result = []
    while not heap.empty():
        result.append(heap.get())

    return result

"""
Exercise 2.3: Find the k-th largest element in a stream of integers
"""
class KthLargest:

    """
    Constructor

    Time Complexity: O(nums.length * log k)
    Space Complexity: O(1)
    """
    def __init__(self, k: int, nums: List[int]):
        self._k_largest = PriorityQueue()
        self._k = k

        # Add nums to the heap. If we have k items in the heap, remove the
        # smallest one each time we add one. This way we maintain a heap of the
        # k largest values and can quickly access the smallest of the k largest
        # values
        for num in nums:
            self._k_largest.put(num)
            if self._k_largest.qsize() > self._k:
                self._k_largest.get()

    """
    Add the next value in the stream

    Time Complexity: O(log k)
    Space Complexity: O(1)
    """
    def add(self, val: int) -> int:
        # Add the value to the heap

        self._k_largest.put(val)

        # If we have too many values, remove from the heap so that we have
        # exactly k. That way when we peek we get the kth largest element
        if self._k_largest.qsize() > self._k:
            self._k_largest.get()

        return self._k_largest.queue[0]

"""
Exercise 2.4: Find the k closest points to the origin

Time Complexity: O(n log k)
Space Complexity: O(k)
"""
def k_closest(points: List[List[int]], k: int) -> List[List[int]]:
    # We'll add everything to a heap
    closest = PriorityQueue()

    for point in points:
        # We want to sort our heap by closest points to origin
        closest.put((-(point[0]*point[0]+point[1]*point[1]), point))

        # Maintain the heap size at k. We could remove everything at the end,
        # but that would slow us down because the heap would get unnecessarily
        # large
        if closest.qsize() > k:
            closest.get()

    # We need to extract the data value from each tuple in our heap
    return [c[1] for c in closest.queue]

"""
Exercise 3.1: Find the median of a data stream
"""
class MedianFinder:

    """
    Constructor

    Time Complexity: O(1)
    Space Complexity: O(1)
    """
    def __init__(self):
        # We will use two heaps to maintain the smaller half of the stream and
        # the larger half of the stream. That way we can peek into the two to
        # figure out what the median value is
        self._smaller_half = PriorityQueue()
        self._larger_half = PriorityQueue()

    """
    Add the next number from the stream

    Time Complexity: O(log n)
    Space Complexity: O(1)
    """
    def add_num(self, num: int) -> None:
        # We can quickly get the largest of the small numbers to see whether our
        # number should go on the left or right side of the median
        if self._smaller_half.empty() or self._smaller_half.queue[0][1] < num:
            self._larger_half.put(num)
        else:
            # _smaller_half should be a max heap
            self._smaller_half.put((-num, num))

        # Once we've added the number, we need to rebalance the two heaps to
        # maintain an equal number of values on each side of the median
        while self._smaller_half.qsize() > self._larger_half.qsize():
            self._larger_half.put(self._smaller_half.get()[1])

        while self._smaller_half.qsize() < self._larger_half.qsize():
            to_add = self._larger_half.get()
            self._smaller_half.put((-to_add, to_add))

    """
    Get the median

    Time Complexity: O(1)
    Space Complexity: O(1)
    """
    def find_median(self) -> float:
        # If we have an odd number, then we just return the top value on the
        # smaller heap (this heap is always going to be larger if there are an
        # odd number of values)
        if self._smaller_half.qsize() > self._larger_half.qsize():
            return self._smaller_half.queue[0][1]

        # Otherwise, just average the two middle values
        return (self._smaller_half.queue[0][1] + self._larger_half.queue[0])/2

"""
Simple node class
"""
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

"""
Exercise 3.2: Merge k sorted lists

Time Complexity: O(n log lists.length)
Space Complexity: O(lists.length)
"""
def merge_k_lists(lists: List[ListNode]) -> ListNode:
    # Create a heap that compares ListNodes by size
    current_nodes = PriorityQueue()
    count = 0

    # Our smallest node overall has to be the first node in one of our lists
    # since the lists are sorted. Add all these first nodes to the heap
    for l in lists:
        if l:
            # If two nodes have the same value, adding (l.val, l) to our heap
            # will cause an error. Therefore we add a third value that is unique
            # for each node. It doesn't matter what it is, so we'll just keep
            # a running count of all the nodes in this case
            current_nodes.put((l.val, count, l))
            count = count+1

    # Use a dummy head to construct our resulting list
    dummy_head = ListNode()
    curr = dummy_head

    # Remove the smallest value from the heap. Then the next smallest value
    # overall is either one of the other values in the heap OR the next node
    # after the one we removed. So we add the next node to our heap  and repeat
    while current_nodes.qsize() > 0:
        to_add = current_nodes.queue[0][2]
        current_nodes.get()

        # Add in the next node if it exists
        if to_add.next:
            current_nodes.put((to_add.next.val, count, to_add.next))
            count = count+1

        # Add to_add to our results list
        curr.next = to_add
        curr = curr.next

    return dummy_head.next

if __name__ == '__main__':

        m = MinHeap()
        m.insert(5)
        m.insert(2)
        m.insert(1)
        m.insert(-1)
        print(m)
        print(m.pop())
        print(m)
        m.insert(6)
        m.insert(3)
        print(m)

        print(is_valid([1,3,2,4,6,5,0]))
        print(is_valid([1,3,2,6,5]))

        print(find_max([1,3,2,4,6,5,0]))

        print(heap_sort([1,3,2,4,6,5,0]))


        kth = KthLargest(3, [4,5,8,2]);
        print(kth.add(3));
        print(kth.add(5));
        print(kth.add(10));
        print(kth.add(9));

        print(k_closest([[3,3], [5,-1], [-2,4]], 2))

        mf = MedianFinder();
        mf.add_num(1);
        mf.add_num(2);
        print(mf.find_median());
        mf.add_num(3);
        print(mf.find_median());

        mf = MedianFinder();
        mf.add_num(-1);
        print(mf.find_median());
        mf.add_num(-2);
        print(mf.find_median());
        mf.add_num(-3);
        print(mf.find_median());
        mf.add_num(-4);
        print(mf.find_median());
        mf.add_num(-4);
        print(mf.find_median());

        lists = [];
        lists.append(ListNode(1))
        lists[0].next = ListNode(4);
        lists[0].next.next = ListNode(5);
        lists.append(ListNode(1));
        lists[1].next = ListNode(3);
        lists[1].next.next = ListNode(4);
        lists.append(ListNode(2));
        lists[2].next = ListNode(6);
        head = merge_k_lists(lists);
        result = []
        while head:
            result.append(str(head.val) + " -> ")
            head = head.next;
        result.append("null");
        print("".join(result))
