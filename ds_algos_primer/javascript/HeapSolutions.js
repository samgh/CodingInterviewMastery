/**
 *   Title: HeapSolutions.js
 *
 *   This file contains the solutions for Exercise Sets of the Heap
 *   exercises in the DS & Algos Primer. If you have not already attempted these
 *   exercises, we highly recommend you complete them before reviewing the
 *   solutions here.
 *
 *   Execution: node HeapSolutions.js
 */

/**
 * Exercise 1.1: Implement a min heap
 */
function MinHeap() {

    var data = [];

    /**
     * Insert an item into the heap
     *
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     *
     * @param{number} x
     */
    this.insert = function(x) {
        // To insert, we add the item at the next open space in the heap
        // and then bubble up until the value is in the right place to
        // satisfy the constraints of the heap
        data.push(x);

        // We could do this inline or break it out into a separate function
        bubbleUp(data.length-1);
    }

    /**
     * Get the smallest value in the heap
     *
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     * @return{number}
     */
    this.peek = function() {
        if (data.length == 0) throw "Heap is empty";

        // This is easy because our data is already in order
        return data[0];
    }

    /**
     * Remove and return the smallest value in the heap
     *
     * Time Complexity: O(log n)
     * Space Complexity: O(log n)
     *
     * @return{number}
     */
    this.pop = function() {
        // The first part is the same as peek()
        if (data.length == 0) throw "Heap is empty";
        var result = data[0];

        // We replace the root of our tree with the last value in the tree,
        // then we bubble down until our constraints are satisfied
        data[0] = data.pop();

        // We could do this inline or break it out
        bubbleDown(0);
        return result;
    }

    /**
     * Get the size of the heap
     *
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     * @return{number}
     */
    this.size = function() {
        return data.length;
    }

    /**
     * Convert the heap data into a string
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * @return{string}
     */
    MinHeap.prototype.toString = function() {
        return data.toString();
    }

    /*
     * The following are some optional helper methods. These are not required
     * but may make your life easier
     */

    /**
     * Get the index of the parent node of a given index
     *
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     * @param{number} i
     * @return{number}
     */
    var parent = function(i) {
        // It is easy to determine this formula by experimenting with
        // specific examples
        return Math.floor((i + 1)/2 - 1);
    }

    /**
     * Get the index of the left child of a given index
     *
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     * @param{number} i
     * @return{number}
     */
    var leftChild = function(i) {
        return i * 2 + 1;
    }

    /**
     * Swap the values at 2 indices in our heap
     *
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     * @param{number} i
     * @param{number} j
     */
    var swap = function(i, j) {
        var temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    /**
     * Starting at index i, bubble up the value until it is at the correct
     * position in the heap
     *
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     *
     * @param{number} i
     */
    var bubbleUp = function(i) {
        // If we are already at the root, we can't bubble up
        if (i == 0) return;

        // While we're below the root, compare current index to the parent.
        // If the parent is greater, then they are out of order so swap them.
        // Repeat until the parent is less than index i
        while (i > 0 && data[i] < data[parent(i)]) {
             swap(i, parent(i));
             i = parent(i);
         }
     }

     /**
      * Starting at index i, bubble down the value until it is at the correct
      * position in the heap
      *
      * Time Complexity: O(log n)
      * Space Complexity: O(log n)
      *
      * @param{number} i
      */
     var bubbleDown = function(i) {
         // Get the two children of the current index
         var left = leftChild(i);
         var right = leftChild(i)+1;

         if (left >= data.length) return;

         // If index only has a left child, we just compare it to that value.
         // If it is greater than the left child, swap them
         if (right >= data.length) {
             if (data[i] > data[left]) swap(i, left);
             return;
         }

         // Find the smaller of the two children
         var smaller = left;
         if (data[right] < data[left]) smaller = right;

         // If index is larger than the smaller child, swap it with the
         // the smaller child
         if (data[i] > data[smaller]) {
             swap(i, smaller);

             // Repeat this process until constraints are satisfied
             bubbleDown(smaller);
         }
     }
}

/**
 * Exercise 1.2: Given an array of integers, determine whether the array
 * represents a valid heap
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * @param{number[]} heap
 * @return{boolean}
 */
var isValid = function(heap) {
    // We just have to check if each index is less than it's children. It is
    // a little easier for bounds-checking if we start at the end of the
    // array and work backwards but either way works
    for (var i = heap.length-1; i > 0; i--) {
        // This is the same formula from parent()
        if (heap[i] < heap[Math.floor((i+1)/2 - 1)]) return false;
    }

    return true;
}

/**
 * Simple heap implementation that you can use in your interviews. This just
 * sorts an array rather than doing the proper heap implementation. However, it
 * is very quick to implement when you don't have an actual heap implementation
 * to use.
 *
 * This implementation allows you to take in a function for custom comparisons.
 * You can also just implement the comparison directly if you only need one
 * instance of the heap.
 */
function SimpleHeap(f) {
    var data = [];

    // By default just use standard comparison
    var compareFunc = f;
    if (!compareFunc) {
        compareFunc = function(a,b) {
            if (a < b) return -1;
            return 1;
        }
    }

    this.insert = function(x) {
        data.push(x);
        data.sort(compareFunc);
    }

    this.pop = function() {
        return data.pop();
    }

    this.peek = function() {
        return data[data.length - 1];
    }

    this.size = function() {
        return data.length;
    }

    SimpleHeap.prototype.toString = function() {
        return data.toString();
    }
}

/**
 * Exercise 2.1: Given a list of integers, use a heap to find the largest
 * value in the list.
 *
 * Use the SimpleHeap implementation
 *
 * Time Complexity: O(n log n)
 * Space Complexity: O(n)
 *
 * @param{number[]} arr
 * @return{number}
 */
var findMax = function(arr) {
    // I recommend using a simple heap implementation like above
    var heap = new SimpleHeap();

    // Add everything to the heap and find the max value
    arr.forEach(function(i) {
        heap.insert(i);
    });

    return heap.peek();
}

/**
 * Exercise 2.2: Given a list of integers, use a heap to sort the list.
 *
 * Use the SimpleHeap implementation
 *
 * Time Complexity: O(n log n)
 * Space Complexity: O(n)
 *
 * @param{number[]} arr
 * @return{number[]}
 */
var heapSort = function(arr) {
    // Add everything to a heap. We want smallest items to be at the top of the
    // heap so we'll do an appropriate comparison function
    var heap = new SimpleHeap(function(a,b) {
        if (a < b) return 1;
        return -1;
    });

    arr.forEach(function(i) {
        heap.insert(i);
    });

    // Remove everything from the heap into our array. The heap will
    // inherently sort everything for us
    var result = [];
    for (var i = 0; i < arr.length; i++) {
        result.push(heap.pop());
    }

    return result;
}

/**
 * Exercise 2.3: Find the k-th largest element in a stream of integers
 *
 * Time Complexity: O(nums.length * log k)
 * Space Complexity: O(1)
 *
 * @param {number} k
 * @param {number[]} nums
 */
var KthLargest = function(k, nums) {
    this.kLargest = new SimpleHeap(function(a,b) {
        if (a < b) return 1;
        return -1;
    });
    this.k = k;

    // Add nums to the heap. If we have k items in the heap, remove the
    // smallest one each time we add one. This way we maintain a heap of
    // the k largest values and can quickly access the smallest of the
    // k largest values
    for (var i = 0; i < nums.length; i++) {
        this.kLargest.insert(nums[i]);
        if (this.kLargest.size() > k) this.kLargest.pop();
    }
};

/**
 * Add the next value in the stream
 *
 * Time Complexity: O(log k)
 * Space Complexity: O(1)
 *
 * @param {number} val
 * @return {number}
 */
KthLargest.prototype.add = function(val) {
    // Add the value to the heap
    this.kLargest.insert(val);

    // If we have too many values, remove from the heap so that we have
    // exactly k. That way when we peek we get the kth largest element
    if (this.kLargest.size() > this.k) this.kLargest.pop();
    return this.kLargest.peek();
};

/**
 * Exercise 2.4: Find the k closest points to the origin
 *
 * Time Complexity: O(n log k)
 * Space Complexity: O(k)
 *
 * @param{number[][]} points
 * @param{number} k
 * @return{number[][]}
 */
var kClosest = function(points, k) {
    // Create the heap. We will use a custom comparison function to compare
    // directly by distance from origin
    var closest = new SimpleHeap(function(a, b) {
        var aDist = a[0]*a[0] + a[1]*a[1];
        var bDist = b[0]*b[0] + b[1]*b[1];
        if (aDist < bDist) return -1;
        return 1;
    })

    // Add all the items to heap
    points.forEach(function(point) {
        closest.insert(point);

        // Maintain the heap size at k. We could remove everything at the
        // end, but that would slow us down because the heap would get
        // unnecessarily large
        if (closest.size() > k) closest.pop();
    });

    // Add all values to an array
    var results = [];
    for (var i = 0; i < k; i++) {
        results.push(closest.pop());
    }

    return results;
}

/**
 * Exercise 3.1: Find the median of a data stream
 */
var MedianFinder = function() {
    // We will use two heaps to maintain the smaller half of the stream and
    // the larger half of the stream. That way we can peek into the two to
    // figure out what the median value is
    this.smallerHalf = new SimpleHeap();
    this.largerHalf = new SimpleHeap(function(a,b) {
        if (a < b) return 1;
        return -1;
    });
};

/**
 * Add the next number from the stream
 *
 * Time Complexity: O(log n)
 * Space Complexity: O(1)
 *
 * @param {number} num
 * @return {void}
 */
MedianFinder.prototype.addNum = function(num) {
    // We can quickly get the largest of the small numbers to see whether
    // our number should go on the left or right side of the median
    if (this.smallerHalf.size() == 0 || this.smallerHalf.peek() < num) this.largerHalf.insert(num);
    else this.smallerHalf.insert(num);

    // Once we've added the number, we need to rebalance the two heaps
    // to maintain an equal number of values on each side of the median
    while(this.smallerHalf.size() > this.largerHalf.size()) {
        this.largerHalf.insert(this.smallerHalf.pop());
    }
    while (this.smallerHalf.size() < this.largerHalf.size()) {
        this.smallerHalf.insert(this.largerHalf.pop());
    }
};

/**
 * Get the median
 *
 * Time Complexity: O(1)
 * Space Complexity: O(1)
 *
 * @return {number}
 */
MedianFinder.prototype.findMedian = function() {
    // If we have an odd number, then we just return the top value on
    // the smaller heap (this heap is always going to be larger if there
    // are an odd number of values)
    if (this.smallerHalf.size() > this.largerHalf.size()) return this.smallerHalf.peek();

    // Otherwise, just average the two middle values
    return (this.smallerHalf.peek() + this.largerHalf.peek())/2;
};

/**
 * Exercise 3.2: Merge k sorted lists
 *
 * Time Complexity: O(n log lists.length)
 * Space Complexity: O(lists.length)
 *
 * @param{ListNode[]} lists
 * @return{ListNode}
 */
var mergeKLists = function(lists) {
    // Create a heap that compares ListNodes by size
    var currentNodes = new SimpleHeap(function(a,b) {
        if (a.val < b.val) return 1;
        return -1;
    });

    // Our smallest node overall has to be the first node in one of our
    // lists since the lists are sorted. Add all these first nodes to the
    // heap
    lists.forEach(function(n) {
        if (n != null) currentNodes.insert(n);
    });

    // Use a dummy head to construct our resulting list
    var dummyHead = new ListNode(0);
    var curr = dummyHead;

    // Remove the smallest value from the heap. Then the next smallest value
    // overall is either one of the other values in the heap OR the next
    // node after the one we removed. So we add the next node to our heap
    // and repeat
    while (!currentNodes.size() == 0) {
        var toAdd = currentNodes.pop();

        // Add in the next node if it exists
        if (toAdd.next != null) currentNodes.insert(toAdd.next);

        // Add toAdd to our result list
        curr.next = toAdd;
        curr = curr.next;
    }

    return dummyHead.next;
}

/**
 * Simple node class
 */
function ListNode(val) {
    this.val = val;
    this.next = null;
}

// Test cases
var tester = function() {
    var m = new MinHeap();
    m.insert(5);
    m.insert(2);
    m.insert(1);
    m.insert(-1);
    console.log(m.toString());
    console.log(m.pop());
    console.log(m.toString());
    m.insert(6);
    m.insert(3);
    console.log(m.toString());

    console.log(isValid([1,3,2,4,6,5,0]));
    console.log(isValid([1,3,2,6,5]));

    var h = new SimpleHeap();
    h.insert(2);
    h.insert(1);
    h.insert(5);
    h.insert(-1);
    console.log(h.pop());
    console.log(h.pop());

    var h = new SimpleHeap(function(a, b) {
        if (a < b) return 1;
        return -1;
    });
    h.insert(2);
    h.insert(1);
    h.insert(5);
    h.insert(-1);
    console.log(h.pop());
    console.log(h.pop());

    console.log(findMax([1,3,2,4,6,5,0]));

    console.log(heapSort([1,3,2,4,6,5,0]));

    var kth = new KthLargest(3, [4,5,8,2]);
    console.log(kth.add(3));
    console.log(kth.add(5));
    console.log(kth.add(10));
    console.log(kth.add(9));

    console.log(kClosest([[3,3],[5,-1],[-2,4]], 2));


    var mf = new MedianFinder();
    mf.addNum(1);
    mf.addNum(2);
    console.log(mf.findMedian());
    mf.addNum(3);
    console.log(mf.findMedian());

    mf = new MedianFinder();
    mf.addNum(-1);
    console.log(mf.findMedian());
    mf.addNum(-2);
    console.log(mf.findMedian());
    mf.addNum(-3);
    console.log(mf.findMedian());
    mf.addNum(-4);
    console.log(mf.findMedian());
    mf.addNum(-4);
    console.log(mf.findMedian());

    var lists = [new ListNode(1), new ListNode(1), new ListNode(2)];
    lists[0].next = new ListNode(4);
    lists[0].next.next = new ListNode(5);
    lists[1].next = new ListNode(3);
    lists[1].next.next = new ListNode(4);
    lists[2].next = new ListNode(6);
    var head = mergeKLists(lists);
    var result = [];
    while (head != null) {
        result.push(head.val + " -> ");
        head = head.next;
    }
    result.push("null");
    console.log(result.join(""));
}

tester();
