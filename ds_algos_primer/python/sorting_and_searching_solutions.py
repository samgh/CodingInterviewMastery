"""
Title: Sorting And Searching Solutions

This file contains the solutions for the Sorting and Searching exercises in
the DS & Algos Primer. If you have not already attempted these exercises,
we highly recommend you complete them before reviewing the solutions here.

Execution: python sorting_and_searching_solutions.py
"""
from typing import List
import functools

"""
Exercise 1.1: Implement Binary Search on a sorted array

Time Complexity: O(log n)
Space Complexity: O(1)
"""
def binary_search(arr: List[int], target: int) -> int:
    # Get the current upper and lower bounds
    lo = 0
    hi = len(arr)-1

    # If lo > hi, then the value is not in the array
    while lo <= hi:
        # Find the midpoint
        mid = (hi + lo) // 2

        # If the value is the one we're looking for, return index
        if arr[mid] == target:
            return mid

        # If value is greater than target, update hi so that we only look
        # at the lower half of the array
        if arr[mid] > target:
            hi = mid-1

        # Otherwise look at the greater half of the array
        else:
            lo = mid+1

    # We didn't find the value in the array so return -1
    return -1

"""
Exercise 1.2: Find the closest number to target in a sorted array

Time Complexity: O(log n)
Space Complexity: O(1)
"""
def closest_number(arr: List[int], target: int) -> int:
    if target <= arr[0]:
        return arr[0]
    if target >= arr[-1]:
        return arr[-1]

    # Get the current upper and lower bounds
    lo = 0
    hi = len(arr)-1

    # Search for the value. If we find it, just return it. If we don't find
    # it, then we want to break out of our loop with lo equal to the index
    # of the number directly below the target
    while lo <= hi:
        # Find the midpoint
        mid = (hi + lo) // 2

        # If the value is the one we're looking for, return the exact value
        if arr[mid] == target:
            return mid

        # If value is greater than target, update hi so that we only look
        # at the lower half of the array
        if arr[mid] > target:
            hi = mid-1
        elif arr[mid] < target and arr[mid+1] > target:
            # If the value is between mid and mid+1, set lo = mid and break
            lo = mid
            break
        else:
            # Otherwise look at larger half of array
            lo = mid+1

    # We didn't find the exact value, but we found the index where it
    # should be. Look at the values on either side to see which is closer.
    # Because of our checks at the top, we know that lo is not 0 or
    # arr.length-1
    if arr[lo+1] - target > target - arr[lo]:
         return arr[lo]

    return arr[lo+1]

"""
Exercise 1.3: Find value in a rotated array

Time Complexity: O(log n)
Space Complexity: O(1)
"""
def search_rotated_array(nums: List[int], target: int)->int:
    # The bounds of our current sublist
    low, high = 0, len(nums)-1

    # Keep dividing subarray in half until we either find the value we're
    # looking for or the subarray length is 0 (aka low >= high)
    while low <= high:
        # The midpoint of our sublist
        mid = (low + high) // 2

        # If we've found the value, return the index
        if target == nums[mid]:
            return mid

        # If the target < nums[mid], we have 3 possible options:
        # 1. Left sublist contains pivot. This means all values lower than
        # nums[mid] are in the left sublist
        # 2. target >= nums[low]. This means target is in left sublist
        # 3. target < nums[low]. This means there could be a pivot in the
        # right sublist so if our target is in the list it must be there
        if target < nums[mid]:
            # A sublist must contain pivot if nums[low] > nums[high]
            if nums[low] > nums[mid] or target >= nums[low]:
                high = mid-1
            else:
                low = mid+1
        else:
            # If target > nums[mid] we just do the opposite of above
            if nums[mid] > nums[high] or target <= nums[high]:
                low = mid+1
            else:
                high = mid-1

    return -1

"""
Exercise 2.1: Implement mergesort

Time Complexity: O(n log n)
Space Complexity: O(n)
"""
def merge_sort(arr: List[int]):

    # Merge two sorted subarrays. Subarrays are lo to mid inclusive and
    # mid+1 to hi inclusive
    def merge(arr: List[int], lo: int, mid: int, hi: int):
        # Copy each subarray into a separate array so that we don't overwrite
        # data as we merge them into the main array
        lo_array = arr[lo:mid+1].copy()
        hi_array = arr[mid+1:hi+1].copy()

        lo_idx = 0
        hi_idx = 0
        for i in range(lo, hi+1):
            if lo_idx >= len(lo_array):
                arr[i] = hi_array[hi_idx]
                hi_idx = hi_idx+1
                continue

            if hi_idx >= len(hi_array):
                arr[i] = lo_array[lo_idx]
                lo_idx = lo_idx+1
                continue

            if lo_array[lo_idx] < hi_array[hi_idx]:
                arr[i] = lo_array[lo_idx]
                lo_idx = lo_idx+1
            else:
                arr[i] = hi_array[hi_idx]
                hi_idx = hi_idx+1

    # Inner recursive function that sorts subarray from lo to hi inclusive
    def merge_sort_inner(arr: List[int], lo: int = 0, hi: int = len(arr)-1):
        # If lo >= hi, then the subarray is length 0 or 1 and therefore already
        # sorted
        if lo >= hi:
            return

        # Find midpoint and sort each half of array
        mid = (lo + hi) // 2
        merge_sort_inner(arr, lo, mid)
        merge_sort_inner(arr, mid+1, hi)

        # Merge the two sorted halves into a single array
        merge(arr, lo, mid, hi)

    merge_sort_inner(arr)

"""
Exercise 2.2: Implement Quicksort on an array
*
* Time Complexity: O(n^2)
* Space Complexity: O(n)
"""
def quick_sort(arr: List[int]):
    # Partition array into a larger and smaller half using the first element
    # in the array as the dividing point
    def partition(arr: List[int], lo: int, hi: int) -> int:
        # Arbitrarily pick partition. We could randomize this to improve
        # worst-case performance
        partition_val = arr[lo]

        # We will start with a pointer at arr[1] and arr[arr.length-1]
        i = lo+1
        j = hi

        while i <= j:
            # If arr[i] <= partition, it's arleady in the right spot in our
            # array so we don't want to move it. Just increment i to leave it
            # in place
            if arr[i] <= partition_val:
                i = i+1

            # Otherwise, we swap arr[i] and arr[j] to move arr[i] to the other
            # side of our partition. We can now decrement j because we know
            # that everything at j or greater is larger than the partition
            else:
                arr[i], arr[j] = arr[j], arr[i]
                j = j-1

        # Currently our partition is at the beginning of our array and
        # most likely out of order. Move it to the middle so that it properly
        # sits between the two partitions
        arr[i-1], arr[lo] = arr[lo], arr[i-1]

        return i-1

    # Inner recursive function. Partition array and then recursively perform
    # quicksort on each partition
    def quick_sort_inner(arr: List[int], lo: int = 0, hi: int = len(arr)-1):
        # Our subarray is length 0 or 1 and already sorted
        if lo >= hi:
            return

        # Partition and get the index of the partition
        p = partition(arr, lo, hi)

        # Sort each partition
        quick_sort_inner(arr, lo, p-1)
        quick_sort_inner(arr, p+1, hi)

    quick_sort_inner(arr)

"""
Simple list node class copied from Leetcode: https://leetcode.com/problems/sort-list/
"""
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

"""
Exercise 3.1: Sort a Linked List

Time Complexity: O(n log n)
Space Complexity: O(log n)
"""
def sort_list(head: ListNode) -> ListNode:
    # If the list contains 0 or 1 nodes, it is already sorted
    if not head or not head.next:
        return head

    # Use a fast and slow pointer to split the lists down the middle into
    # two roughly even halves
    slow = head
    fast = head.next

    # Find the midpoint
    while fast and fast.next:
        slow = slow.next
        fast = fast.next.next

    # Split the lists
    first_half = head
    second_half = slow.next
    slow.next = None

    # Sort each half
    first_half = sort_list(first_half)
    second_half = sort_list(second_half)

    # Merge the sorted halves
    return merge_lists(first_half, second_half)

"""
Merge two sorted lists
"""
def merge_lists(l1: ListNode, l2: ListNode) -> ListNode:
    dummy_head = ListNode()
    curr = dummy_head

    # The concept is basically the same as merging arrays
    while l1 or l2:
        # If we've already added all of l1, then just add the remainder of
        # l2 and return
        if not l1:
            curr.next = l2
            return dummy_head.next

        # If we've already added all of l2, then just add the remainder of
        # l1 and return
        if not l2:
            curr.next = l1
            return dummy_head.next

        # If there are remaining elements in both lists, add the smaller
        # to our result list
        if l1.val < l2.val:
            curr.next = l1
            l1 = l1.next
        else:
            curr.next = l2
            l2 = l2.next

        curr = curr.next

    return dummy_head.next

"""
Exercise 3.2: Find the largest number

Time Complexity: O(n log n)
Space Complexity: O(n)
"""
def largest_number(nums: List[int]) -> str:
    # We ultimately need to treat this as a string, so let's go ahead and
    # convert everything
    num_strings = []
    for i in nums:
        num_strings.append(str(i))

    # To determine which string comes first, see what happens when we
    # concatenate them each way. We want the largest resulting string
    def compare(s1: str, s2: str) -> int:
        s1_first = s1+s2
        s2_first = s2+s1

        #  Since we want the largest strings first, reverse the order
        if s1_first < s2_first:
            return 1
        return -1

    num_strings = sorted(num_strings, key=functools.cmp_to_key(compare))

    # If our string is all 0s, then just return "0"
    if num_strings[0] == "0":
        return "0"

    # Otherwise join our array
    return "".join(num_strings)


# Sample test cases
if __name__ == '__main__':
    print(binary_search([1,2,3,4,5,6], 5))

    print(closest_number([1,2,3,4,5,6], 0))
    print(closest_number([1,2,5,6], 3))
    print(closest_number([1,6], 3))

    print(search_rotated_array([4,5,6,7,0,1,2], 0))
    print(search_rotated_array([4,5,6,7,0,1,2], 3))

    arr = [1,5,3,3,7,6,9,1]
    merge_sort(arr)
    print(arr)

    arr2 = [1,5,3,3,7,6,9,1]
    quick_sort(arr2)
    print(arr2)

    l = ListNode(5)
    l.next = ListNode(2)
    l.next.next = ListNode(3)
    l.next.next.next = ListNode(7)
    l = sort_list(l)
    while l:
        print(l.val)
        l = l.next

    print(largest_number([3,30,34,5,9]))

    """

    ListNode l = new ListNode(5);
    l.next = new ListNode(2);
    l.next.next = new ListNode(3);
    l.next.next.next = new ListNode(7);
    l = sortList(l);
    while (l != null) {
        System.out.println(l.val);
        l = l.next;
    }

    System.out.println(largestNumber(new int[]{3,30,34,5,9}));

    System.out.println(squareRoot(4));
    System.out.println(squareRoot(8));

    System.out.println(splitLargest(new int[]{7,2,5,10,8}, 2));
    """
