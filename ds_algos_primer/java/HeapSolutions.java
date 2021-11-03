/*
 *   Title: HeapSolutions.java
 *
 *   This file contains the solutions for Exercise Sets of the Heap
 *   exercises in the DS & Algos Primer. If you have not already attempted these
 *   exercises, we highly recommend you complete them before reviewing the
 *   solutions here.
 *
 *   Execution: javac HeapSolutions.java && java -ea HeapSolutions
 */

import java.util.*;

public class HeapSolutions {

    /*
     * Exercise 1.1: Implement a min heap
     */
    public static class MinHeap {
        // We'll use an ArrayList so that we can easily resize our data
        ArrayList<Integer> data;

        // Constructor
        public MinHeap() {
            this.data = new ArrayList<>();
        }

        /*
         * Insert an item into the heap
         *
         * Time Complexity: O(log n)
         * Space Complexity: O(1)
         */
        public void insert(int x) {
            // To insert, we add the item at the next open space in the heap
            // and then bubble up until the value is in the right place to
            // satisfy the constraints of the heap
            data.add(x);

            // We could do this inline or break it out into a separate function
            this.bubbleUp(data.size()-1);
        }

        /*
         * Get the smallest value in the heap
         *
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         */
        public int peek() {
            if (data.isEmpty()) throw new IndexOutOfBoundsException();

            // This is easy because our data is already in order
            return data.get(0);
        }

        /*
         * Remove and return the smallest value in the heap
         *
         * Time Complexity: O(log n)
         * Space Complexity: O(log n)
         */
        public int pop() {
            // The first part is the same as peek()
            if (data.isEmpty()) throw new IndexOutOfBoundsException();
            int result = data.get(0);

            // We replace the root of our tree with the last value in the tree,
            // then we bubble down until our constraints are satisfied
            data.set(0, data.remove(data.size()-1));

            // We could do this inline or break it out
            this.bubbleDown(0);
            return result;
        }

        /*
         * Get the size of the heap
         *
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         */
        public int size() {
            return data.size();
        }

        /*
         * Convert the heap data into a string
         *
         * Time Complexity: O(n)
         * Space Complexity: O(n)
         */
        public String toString() {
            return data.toString();
        }

        /*
         * The following are some optional helper methods. These are not required
         * but may make your life easier
         */

        /*
         * Get the index of the parent node of a given index
         *
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         */
        private static int parent(int i) {
            // It is easy to determine this formula by experimenting with
            // specific examples
            return (i + 1)/2 - 1;
        }

        /*
         * Get the index of the left child of a given index
         *
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         */
        private static int leftChild(int i) {
            return i * 2 + 1;
        }

        /*
         * Swap the values at 2 indices in our heap
         *
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         */
        private void swap(int i, int j) {
            int temp = data.get(i);
            data.set(i, data.get(j));
            data.set(j, temp);
        }

        /*
         * Starting at index i, bubble up the value until it is at the correct
         * position in the heap
         *
         * Time Complexity: O(log n)
         * Space Complexity: O(1)
         */
        private void bubbleUp(int i) {
            // If we are already at the root, we can't bubble up
            if (i == 0) return;

            // While we're below the root, compare current index to the parent.
            // If the parent is greater, then they are out of order so swap them.
            // Repeat until the parent is less than index i
            while (i > 0 && data.get(i) < data.get(parent(i))) {
                this.swap(i, parent(i));
                i = parent(i);
            }
        }

        /*
         * Starting at index i, bubble down the value until it is at the correct
         * position in the heap
         *
         * Time Complexity: O(log n)
         * Space Complexity: O(log n)
         */
        private void bubbleDown(int i) {
            // Get the two children of the current index
            int left = leftChild(i);
            int right = leftChild(i)+1;

            if (left >= data.size()) return;

            // If index only has a left child, we just compare it to that value.
            // If it is greater than the left child, swap them
            if (right >= data.size()) {
                if (data.get(i) > data.get(left)) this.swap(i, left);
                return;
            }

            // Find the smaller of the two children
            int smaller = left;
            if (data.get(right) < data.get(left)) smaller = right;

            // If index is larger than the smaller child, swap it with the
            // the smaller child
            if (data.get(i) > data.get(smaller)) {
                swap(i, smaller);

                // Repeat this process until constraints are satisfied
                bubbleDown(smaller);
            }
        }
    }

    /*
     * Exercise 1.2: Given an array of integers, determine whether the array
     * represents a valid heap
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static boolean isValid(int[] heap) {
        // We just have to check if each index is less than it's children. It is
        // a little easier for bounds-checking if we start at the end of the
        // array and work backwards but either way works
        for (int i = heap.length-1; i > 0; i--) {
            // This is the same formula from parent()
            if (heap[i] < heap[(i+1)/2 - 1]) return false;
        }

        return true;
    }

    /*
     * Exercise 2.1: Given a list of integers, use a heap to find the largest
     * value in the list.
     *
     * Use the Java PriorityQueue data structure
     *
     * Time Complexity: O(n log n)
     * Space Complexity: O(n)
     */
    public static int findMax(int[] arr) {
        // Java standard is a min heap. If we want a max heap we can just
        // reverse the order of the comparisons
        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.reverseOrder());

        // Add everything to the heap and find the max value
        for (int i : arr) heap.add(i);
        return heap.peek();
    }

    /*
     * Exercise 2.2: Given a list of integers, use a heap to sort the list.
     *
     * Use the Java PriorityQueue data structure
     *
     * Time Complexity: O(n log n)
     * Space Complexity: O(n)
     */
    public static int[] heapSort(int[] arr) {
        // Add everything to a heap
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int i : arr) heap.add(i);

        // Remove everything from the heap into our array. The heap will
        // inherently sort everything for us
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) result[i] = heap.remove();

        return result;
    }

    /*
     * Exercise 2.3: Find the k-th largest element in a stream of integers
     */
    public static class KthLargest {
        PriorityQueue<Integer> kLargest;
        int k;

        /*
         * Constructor
         *
         * Time Complexity: O(nums.length * log k)
         * Space Complexity: O(1)
         */
        public KthLargest(int k, int[] nums) {
            kLargest = new PriorityQueue<>();
            this.k = k;

            // Add nums to the heap. If we have k items in the heap, remove the
            // smallest one each time we add one. This way we maintain a heap of
            // the k largest values and can quickly access the smallest of the
            // k largest values
            for (int i = 0; i < nums.length; i++) {
                kLargest.add(nums[i]);
                if (kLargest.size() > k) kLargest.remove();
            }
        }

        /*
         * Add the next value in the stream
         *
         * Time Complexity: O(log k)
         * Space Complexity: O(1)
         */
        public int add(int val) {
            // Add the value to the heap
            kLargest.add(val);

            // If we have too many values, remove from the heap so that we have
            // exactly k. That way when we peek we get the kth largest element
            if (kLargest.size() > k) kLargest.remove();
            return kLargest.peek();
        }
    }

    /*
     * Exercise 2.4: Find the k closest points to the origin
     *
     * Time Complexity: O(n log k)
     * Space Complexity: O(k)
     */
    public static int[][] kClosest(int[][] points, int k) {
        // We cover this more in Sorting and Searching, but the easiest way to
        // do this is to create a custom comparator. That way we can sort our
        // heap directly by distance from the origin
        Comparator<int[]> distanceFromOrigin =
            Comparator.comparing(point -> point[0]*point[0] + point[1]*point[1]);

        // Create a heap. We want a min heap, so we need to reverse our comparator
        PriorityQueue<int[]> closest = new PriorityQueue<>(distanceFromOrigin.reversed());

        // Add all the items to heap
        for (int[] point : points) {
            closest.add(point);

            // Maintain the heap size at k. We could remove everything at the
            // end, but that would slow us down because the heap would get
            // unnecessarily large
            if (closest.size() > k) closest.remove();
        }

        // Add results to int[][]
        int[][] results = new int[k][];
        for (int i = 0; i < k; i++) {
            results[i] = closest.remove();
        }

        return results;
    }

    /*
     * Exercise 3.1: Find the median of a data stream
     */
    public static class MedianFinder {

        // We will use two heaps to maintain the smaller half of the stream and
        // the larger half of the stream. That way we can peek into the two to
        // figure out what the median value is
        PriorityQueue<Integer> smallerHalf;
        PriorityQueue<Integer> largerHalf;

        /*
         * Constructor
         *
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         */
        public MedianFinder() {
            // The smaller set of numbers we want to store as a max heap
            smallerHalf = new PriorityQueue<>(Comparator.reverseOrder());

            // The larger set of numbers we want to store as a min heap
            largerHalf = new PriorityQueue<>();
        }

        /*
         * Add the next number from the stream
         *
         * Time Complexity: O(log n)
         * Space Complexity: O(1)
         */
        public void addNum(int num) {
            // We can quickly get the largest of the small numbers to see whether
            // our number should go on the left or right side of the median
            if (smallerHalf.isEmpty() || smallerHalf.peek() < num) largerHalf.add(num);
            else smallerHalf.add(num);

            // Once we've added the number, we need to rebalance the two heaps
            // to maintain an equal number of values on each side of the median
            while(smallerHalf.size() > largerHalf.size()) {
                largerHalf.add(smallerHalf.remove());
            }
            while (smallerHalf.size() < largerHalf.size()) {
                smallerHalf.add(largerHalf.remove());
            }
        }

        /*
         * Get the median
         *
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         */
        public double findMedian() {
            // If we have an odd number, then we just return the top value on
            // the smaller heap (this heap is always going to be larger if there
            // are an odd number of values)
            if (smallerHalf.size() > largerHalf.size()) return smallerHalf.peek();

            // Otherwise, just average the two middle values
            return (smallerHalf.peek() + largerHalf.peek())/2.0;
        }
    }

    /*
     * Exercise 3.2: Merge k sorted lists
     *
     * Time Complexity: O(n log lists.length)
     * Space Complexity: O(lists.length)
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        // Create a heap that compares ListNodes by size
        PriorityQueue<ListNode> currentNodes = new PriorityQueue<>(Comparator.comparing(node -> node.val));

        // Our smallest node overall has to be the first node in one of our
        // lists since the lists are sorted. Add all these first nodes to the
        // heap
        for (ListNode n : lists) {
            if (n != null) currentNodes.add(n);
        }

        // Use a dummy head to construct our resulting list
        ListNode dummyHead = new ListNode(0);
        ListNode curr = dummyHead;

        // Remove the smallest value from the heap. Then the next smallest value
        // overall is either one of the other values in the heap OR the next
        // node after the one we removed. So we add the next node to our heap
        // and repeat
        while (!currentNodes.isEmpty()) {
            ListNode toAdd = currentNodes.remove();

            // Add in the next node if it exists
            if (toAdd.next != null) currentNodes.add(toAdd.next);

            // Add toAdd to our result list
            curr.next = toAdd;
            curr = curr.next;
        }

        return dummyHead.next;
    }

    /*
     * Simple node class
     */
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            this.val = x;
        }
    }


    // Test cases
    public static void main(String[] args) {
        MinHeap m = new MinHeap();
        m.insert(5);
        m.insert(2);
        m.insert(1);
        m.insert(-1);
        System.out.println(m);
        System.out.println(m.pop());
        System.out.println(m);
        m.insert(6);
        m.insert(3);
        System.out.println(m);

        System.out.println(isValid(new int[]{1,3,2,4,6,5,0}));
        System.out.println(isValid(new int[]{1,3,2,6,5}));

        System.out.println(findMax(new int[]{1,3,2,4,6,5,0}));

        System.out.println(Arrays.toString(heapSort(new int[]{1,3,2,4,6,5,0})));

        KthLargest kth = new KthLargest(3, new int[]{4,5,8,2});
        System.out.println(kth.add(3));
        System.out.println(kth.add(5));
        System.out.println(kth.add(10));
        System.out.println(kth.add(9));

        int[][] points = new int[][]{new int[]{3,3}, new int[]{5,-1}, new int[]{-2,4}};
        int[][] closest = kClosest(points, 2);
        for (int[] point : closest) System.out.println(Arrays.toString(point));

        MedianFinder mf = new MedianFinder();
        mf.addNum(1);
        mf.addNum(2);
        System.out.println(mf.findMedian());
        mf.addNum(3);
        System.out.println(mf.findMedian());

        mf = new MedianFinder();
        mf.addNum(-1);
        System.out.println(mf.findMedian());
        mf.addNum(-2);
        System.out.println(mf.findMedian());
        mf.addNum(-3);
        System.out.println(mf.findMedian());
        mf.addNum(-4);
        System.out.println(mf.findMedian());
        mf.addNum(-4);
        System.out.println(mf.findMedian());

        ListNode[] lists = new ListNode[3];
        lists[0] = new ListNode(1);
        lists[0].next = new ListNode(4);
        lists[0].next.next = new ListNode(5);
        lists[1] = new ListNode(1);
        lists[1].next = new ListNode(3);
        lists[1].next.next = new ListNode(4);
        lists[2] = new ListNode(2);
        lists[2].next = new ListNode(6);
        ListNode head = mergeKLists(lists);
        while (head != null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }
}
