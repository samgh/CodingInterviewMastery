/**
 *   Title: SortingAndSearchingSolutions
 *
 *   This file contains the solutions for the Sorting and Searching exercises in
 *   the DS & Algos Primer. If you have not already attempted these exercises,
 *   we highly recommend you complete them before reviewing the solutions here.
 *
 *   Execution: node SortingAndSearchingSolutions.js
 */

/**
 * Exercise 1.1: Implement Binary Search on a sorted array
 *
 * Time Complexity: O(log n)
 * Space Complexity: O(1)
 *
 * @param{number[]} arr
 * @param{number} target
 * @return{number}
 */
var binarySearch = function(arr, target) {
    // Get the current upper and lower bounds
    var lo = 0;
    var hi = arr.length-1;

    // If lo > hi, then the value is not in the array
    while (lo <= hi) {
        // Find the midpoint
        var mid = Math.floor((hi + lo) / 2);

        // If the value is the one we're looking for, return index
        if (arr[mid] == target) return mid;

        // If value is greater than target, update hi so that we only look
        // at the lower half of the array
        if (arr[mid] > target) hi = mid-1;

        // Otherwise look at the greater half of the array
        else lo = mid+1;
    }

    // We didn't find the value in the array so return -1
    return -1;
}

/**
 * Exercise 1.2: Find the closest number to target in a sorted array
 *
 * Time Complexity: O(log n)
 * Space Complexity: O(1)
 *
 * @param{number[]} arr
 * @param{number} target
 * @return{number}
 */
var closestNumber = function(arr, target) {
    if (target <= arr[0]) return arr[0];
    if (target >= arr[arr.length-1]) return arr[arr.length-1];

    // Get the current upper and lower bounds
    var lo = 0;
    var hi = arr.length-1;

    // Search for the value. If we find it, just return it. If we don't find
    // it, then we want to break out of our loop with lo equal to the index
    // of the number directly below the target
    while (lo <= hi) {
        // Find the midpoint
        var mid = Math.floor((hi + lo) / 2);

        // If the value is the one we're looking for, return the exact value
        if (arr[mid] == target) return mid;

        // If value is greater than target, update hi so that we only look
        // at the lower half of the array
        if (arr[mid] > target) {
            hi = mid-1;
        } else if (arr[mid] < target && arr[mid+1] > target) {
            // If the value is between mid and mid+1, set lo = mid and break
            lo = mid;
            break;
        } else {
            // Otherwise look at larger half of array
            lo = mid+1;
        }
    }

    // We didn't find the exact value, but we found the index where it
    // should be. Look at the values on either side to see which is closer.
    // Because of our checks at the top, we know that lo is not 0 or
    // arr.length-1
    if (arr[lo+1] - target > target - arr[lo]) return arr[lo];
    return arr[lo+1];
}

/**
 * Exercise 1.3: Find value in a rotated array
 *
 * Time Complexity: O(log n)
 * Space Complexity: O(1)
 *
 * @param {number[]} arr
 * @param {number} target
 * @return {number}
 */
var searchRotatedArray = function(arr, target) {
    // The bounds of our current subarray
    var low = 0;
    var high = arr.length-1;

    // Keep dividing subarray in half until we either find the value we're
    // looking for or the subarray length is 0 (aka low >= high)
    while (low <= high) {
        // The midpoint of our subarray
        var mid = Math.floor((low + high)/2);

        // If we've found the value, return the index
        if (target == arr[mid]) return mid;

        // If the target < arr[mid], we have 3 possible options:
        // 1. Left subarray contains pivot. This means all values lower than
        // arr[mid] are in the left subarray
        // 2. target >= arr[low]. This means target is in left subarray
        // 3. target < arr[low]. This means there could be a pivot in the
        // right subarray so if our target is in the array it must be there
        if (target < arr[mid]) {
            // A subarray must contain pivot if arr[low] > arr[high]
            if (arr[low] > arr[mid] || target >= arr[low]) {
                high = mid-1;
            } else {
                low = mid+1;
            }
        } else {
            // If target > arr[mid] we just do the opposite of above
            if (arr[mid] > arr[high] || target <= arr[high]) {
                low = mid+1;
            } else {
                high = mid-1;
            }
        }
    }

    return -1;
}

/**
 * Exercise 2.1: Implement mergesort
 *
 * Time Complexity: O(n log n)
 * Space Complexity: O(n)
 *
 * @param{number[]} arr
 */
var mergeSort = function(arr) {
    // Make recursive call
    mergeSortInner(arr, 0, arr.length-1);
}

/**
 * Inner recursive function that sorts subarray from lo to hi inclusive
 *
 * @param{number[]} arr
 * @param{number} lo
 * @param{number} hi
 */
var mergeSortInner = function(arr, lo, hi) {
    // If lo >= hi, then the subarray is length 0 or 1 and therefore already
    // sorted
    if (lo >= hi) return;

    // Find midpoint and sort each half of array
    var mid = Math.floor((lo + hi) / 2);
    mergeSortInner(arr, lo, mid);
    mergeSortInner(arr, mid+1, hi);

    // Merge the two sorted halves into a single array
    merge(arr, lo, mid, hi);
}

/**
 * Merge two sorted subarrays. Subarrays are lo to mid inclusive and
 * mid+1 to hi inclusive
 *
 * @param{number[]} arr
 * @param{number} lo
 * @param{number} mid
 * @param{number} hi
 */
var merge = function(arr, lo, mid, hi) {
    // Copy each subarray into a separate array so that we don't overwrite
    // data as we merge them into the main array
    var loArray = [];
    var hiArray = [];

    for (var i = 0; i <= mid-lo; i++) loArray.push(arr[lo + i]);
    for (var i = 0; i < hi-mid; i++) hiArray.push(arr[mid + i + 1]);

    // Merge two arrays in order into the main array
    var loIdx = 0;
    var hiIdx = 0;
    for (var i = lo; i <= hi; i++) {
        if (loIdx >= loArray.length) {
            arr[i] = hiArray[hiIdx++];
            continue;
        }
        if (hiIdx >= hiArray.length) {
            arr[i] = loArray[loIdx++];
            continue;
        }

        if (loArray[loIdx] < hiArray[hiIdx]) {
            arr[i] = loArray[loIdx++];
        } else {
            arr[i] = hiArray[hiIdx++];
        }
    }
}

/**
 * Exercise 2.2: Implement Quicksort on an array
 *
 * Time Complexity: O(n^2)
 * Space Complexity: O(n)
 *
 * @param{number[]} arr
 */
var quickSort = function(arr) {
    // Recursively call inner function
    quickSortInner(arr, 0, arr.length-1);
}

/**
 * Inner recursive function. Partition array and then recursively perform
 * quicksort on each partition
 *
 * @param{number[]} arr
 * @param{number} lo
 * @param{number} hi
 */
var quickSortInner = function(arr, lo, hi) {
    // Our subarray is length 0 or 1 and already sorted
    if (lo >= hi) return;

    // Partition and get the index of the partition
    var p = partition(arr, lo, hi);

    // Sort each partition
    quickSortInner(arr, lo, p-1);
    quickSortInner(arr, p+1, hi);
}

/**
 * Partition array into a larger and smaller half using the first element
 * in the array as the dividing point
 *
 * @param{number[]} arr
 * @param{number} lo
 * @param{number} hi
 * @return{number}
 */
var partition = function(arr, lo, hi) {
    // Arbitrarily pick partition. We could randomize this to improve
    // worst-case performance
    var partitionVal = arr[lo];

    // We will start with a pointer at arr[1] and arr[arr.length-1]
    var i = lo+1;
    var j = hi;

    while (i <= j) {
        // If arr[i] <= partition, it's arleady in the right spot in our
        // array so we don't want to move it. Just increment i to leave it
        // in place
        if (arr[i] <= partitionVal) i++;

        // Otherwise, we swap arr[i] and arr[j] to move arr[i] to the other
        // side of our partition. We can now decrement j because we know
        // that everything at j or greater is larger than the partition
        else {
            swap(arr, i, j);
            j--;
        }
    }


    // Currently our partition is at the beginning of our array and
    // most likely out of order. Move it to the middle so that it properly
    // sits between the two partitions
    swap(arr, lo, i-1);

    return i-1;
}

/**
 * Simple function to swap two indices of an array
 *
 * @param{number[]} arr
 * @param{number} i`
 * @param{number} j
 */
var swap = function(arr, i, j) {
    var temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
}


var tester = function() {
    console.log(binarySearch([1,2,3,4,5,6], 5));

    console.log(closestNumber([1,2,3,4,5,6], 0));
    console.log(closestNumber([1,2,5,6], 3));
    console.log(closestNumber([1,6], 3));

    console.log(searchRotatedArray([4,5,6,7,0,1,2], 0));
    console.log(searchRotatedArray([4,5,6,7,0,1,2], 3));

    var arr = [1,5,3,3,7,6,9,1];
    mergeSort(arr);
    console.log(arr);

    var arr2 = [1,5,3,3,7,6,9,1];
    quickSort(arr2);
    console.log(arr2);
        /*

        int[] arr2 = new int[]{1,5,3,3,7,6,9,1};
        quickSort(arr2);
        System.out.println(Arrays.toString(arr));

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

        System.out.println(splitLargest(new int[]{7,2,5,10,8}, 2));*/

}

tester();
