/*
 *   Title: SortingAndSearchingSolutions
 *
 *   This file contains the solutions for the Sorting and Searching exercises in
 *   the DS & Algos Primer. If you have not already attempted these exercises,
 *   we highly recommend you complete them before reviewing the solutions here.
 *
 *   Execution: javac SortingAndSearchingSolutions.java && java -ea SortingAndSearchingSolutions
 */
import java.util.*;

public class SortingAndSearchingSolutions {
    /*
     * Exercise 1.1: Implement Binary Search on a sorted array
     *
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    public static int binarySearch(int[] arr, int target) {
        // Get the current upper and lower bounds
        int lo = 0;
        int hi = arr.length-1;

        // If lo > hi, then the value is not in the array
        while (lo <= hi) {
            // Find the midpoint
            int mid = (hi + lo) / 2;

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

    /*
     * Exercise 1.2: Find the closest number to target in a sorted array
     *
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    public static int closestNumber(int[] arr, int target) {
        if (target <= arr[0]) return arr[0];
        if (target >= arr[arr.length-1]) return arr[arr.length-1];

        // Get the current upper and lower bounds
        int lo = 0;
        int hi = arr.length-1;

        // Search for the value. If we find it, just return it. If we don't find
        // it, then we want to break out of our loop with lo equal to the index
        // of the number directly below the target
        while (lo <= hi) {
            // Find the midpoint
            int mid = (hi + lo) / 2;

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

    /*
     * Exercise 1.3: Find value in a rotated array
     *
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    public static int searchRotatedArray(int[] arr, int target) {
        // The bounds of our current subarray
        int low = 0;
        int high = arr.length-1;

        // Keep dividing subarray in half until we either find the value we're
        // looking for or the subarray length is 0 (aka low >= high)
        while (low <= high) {
            // The midpoint of our subarray
            int mid = (low + high)/2;

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

    /*
     * Exercise 2.1: Implement mergesort
     *
     * Time Complexity: O(n log n)
     * Space Complexity: O(n)
     */
    public static void mergeSort(int[] arr) {
        // Make recursive call
        mergeSort(arr, 0, arr.length-1);
    }

    /*
     * Inner recursive function that sorts subarray from lo to hi inclusive
     */
    private static void mergeSort(int[] arr, int lo, int hi) {
        // If lo >= hi, then the subarray is length 0 or 1 and therefore already
        // sorted
        if (lo >= hi) return;

        // Find midpoint and sort each half of array
        int mid = (lo + hi) / 2;
        mergeSort(arr, lo, mid);
        mergeSort(arr, mid+1, hi);

        // Merge the two sorted halves into a single array
        merge(arr, lo, mid, hi);
    }

    /*
     * Merge two sorted subarrays. Subarrays are lo to mid inclusive and
     * mid+1 to hi inclusive
     */
    private static void merge(int[] arr, int lo, int mid, int hi) {
        // Copy each subarray into a separate array so that we don't overwrite
        // data as we merge them into the main array
        int[] loArray = new int[mid-lo+1];
        int[] hiArray = new int[hi-mid];

        for (int i = 0; i < loArray.length; i++) loArray[i] = arr[lo + i];
        for (int i = 0; i < hiArray.length; i++) hiArray[i] = arr[mid + i + 1];

        // Merge two arrays in order into the main array
        int loIdx = 0;
        int hiIdx = 0;
        for (int i = lo; i <= hi; i++) {
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

    /*
     * Exercise 2.2: Implement Quicksort on an array
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(n)
     */
    public static void quickSort(int[] arr) {
        // Recursively call inner function
        quickSort(arr, 0, arr.length-1);
    }

    /*
     * Inner recursive function. Partition array and then recursively perform
     * quicksort on each partition
     */
    private static void quickSort(int[] arr, int lo, int hi) {
        // Our subarray is length 0 or 1 and already sorted
        if (lo >= hi) return;

        // Partition and get the index of the partition
        int p = partition(arr, lo, hi);

        // Sort each partition
        quickSort(arr, lo, p-1);
        quickSort(arr, p+1, hi);
    }

    /*
     * Partition array into a larger and smaller half using the first element
     * in the array as the dividing point
     */
    private static int partition(int[] arr, int lo, int hi) {
        // Arbitrarily pick partition. We could randomize this to improve
        // worst-case performance
        int partition = arr[lo];

        // We will start with a pointer at arr[1] and arr[arr.length-1]
        int i = lo+1;
        int j = hi;

        while (i < j) {
            // If arr[i] < partition, it's arleady in the right spot in our
            // array so we don't want to move it. Just increment i to leave it
            // in place
            if (partition < arr[i]) i++;

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
        swap(arr, 0, i);
        return i;
    }

    // Simple function to swap two indices of an array
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /*
     * Simple list node class copied from Leetcode: https://leetcode.com/problems/sort-list/
     */
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /*
     * Exercise 3.1: Sort a Linked List
     *
     * Time Complexity: O(n log n)
     * Space Complexity: O(log n)
     */
    public static ListNode sortList(ListNode head) {
        // If the list contains 0 or 1 nodes, it is already sorted
        if (head == null || head.next == null) return head;

        // Use a fast and slow pointer to split the lists down the middle into
        // two roughly even halves
        ListNode slow = head;
        ListNode fast = head.next;

        // Find the midpoint
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Split the lists
        ListNode firstHalf = head;
        ListNode secondHalf = slow.next;
        slow.next = null;

        // Sort each half
        firstHalf = sortList(firstHalf);
        secondHalf = sortList(secondHalf);

        // Merge the sorted halves
        return mergeLists(firstHalf, secondHalf);
    }

    /*
     * Merge two sorted lists
     */
    private static ListNode mergeLists(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode curr = dummyHead;

        // The concept is basically the same as merging arrays
        while (l1 != null || l2 != null) {
            // If we've already added all of l1, then just add the remainder of
            // l2 and return
            if (l1 == null) {
                curr.next = l2;
                return dummyHead.next;
            }

            // If we've already added all of l2, then just add the remainder of
            // l1 and return
            if (l2 == null) {
                curr.next = l1;
                return dummyHead.next;
            }

            // If there are remaining elements in both lists, add the smaller
            // to our result list
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }

        return dummyHead.next;
    }

    /*
     * Exercise 3.2: Find the largest number
     *
     * Time Complexity: O(n log n)
     * Space Complexity: O(n)
     */
    public static String largestNumber(int[] nums) {
        // We ultimately need to treat this as a string, so let's go ahead and
        // convert everything
        String[] numStrings = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numStrings[i] = String.valueOf(nums[i]);
        }

        // Sort our array of strings
        Arrays.sort(numStrings, (String s1, String s2) -> {
            // To determine which string comes first, see what happens when we
            // concatenate them each way. We want the largest resulting string
            String s1First = s1 + s2;
            String s2First = s2 + s1;

            // Since we want the largest strings first, reverse the order
            return -s1First.compareTo(s2First);
        });

        // If our string is all 0s, then just return "0"
        if (numStrings[0].equals("0")) return "0";

        // Otherwise join our array
        return String.join("", numStrings);
    }

    /*
     * Exercise 3.3: Find the square root
     *
     * Time Complexity: O(log x)
     * Space Complexity: O(1)
     */
    public static int squareRoot(int x) {
        // If x == the square root is 0
        if (x == 0) return 0;

        // We'll do binary search, so establish starting bounds. Square root
        // cannot be more than half of the value
        int lo = 1;
        int hi = x/2;

        // Perform search
        while (lo < hi) {
            int mid = (lo + hi) / 2;

            // We cannot do mid*mid > x because of overflow error, so this is
            // the same expression rearranged to use division and avoid overflow
            if (mid > x / mid) {
                hi = mid-1;
            } else if (mid+1 > x/(mid+1)) {
                // If mid is too low but mid+1 is too high, then return mid
                return mid;
            } else {
                lo = mid+1;
            }
        }

        return lo;
    }

    /*
     * Exercise 3.4: Split Array Largest Sum
     *
     * Time Complexity: O(log(sum(arr)) * arr.length)
     * Space Complexity: O(1)
     */
    public static int splitLargest(int[] arr, int m) {
        // We know that our value must be between the max individual value in
        // the array and the sum of the total array. So we'll just do binary
        // search to find the minimum value where we can validly divide the array
        int max = 0;
        int sum = 0;
        for (int i : arr) {
            max = Math.max(max, i);
            sum += i;
        }

        if (m == 0) return (int) sum;

        // Do binary search
        int lo = max;
        int hi = sum;
        while (lo <= hi) {
            int mid = (lo + hi)/2;

            // If the array can be validly divided with the current max sum, try
            // a smaller max sum
            if (valid(arr, m, mid)) {
                hi = mid-1;
            } else {
                lo = mid+1;
            }
        }

        return (int)lo;
    }

    /*
     * Determine whether an array can be divided into <= m subarrays all with
     * sum of <= maxSum.
     */
    private static boolean valid(int[] arr, int m, int maxSum) {
        int subarrayCount = 1;
        int subarraySum = 0;

        // Greedily divide into the minimum possible subarrays. For each
        // subarray just add as many values as you can without exceeding the
        // maxSum. Any time you exceed it, then that is the start of a new subarray
        for (int a : arr) {
            subarraySum += a;
            if (subarraySum > maxSum) {
                subarrayCount++;
                subarraySum = a;

                if (subarrayCount > m) return false;
            }
        }

        return true;
    }


    public static void main(String[] args) {
        System.out.println(binarySearch(new int[]{1,2,3,4,5,6}, 5));

        System.out.println(closestNumber(new int[]{1,2,3,4,5,6}, 0));
        System.out.println(closestNumber(new int[]{1,2,5,6}, 3));
        System.out.println(closestNumber(new int[]{1,6}, 3));

        System.out.println(searchRotatedArray(new int[]{4,5,6,7,0,1,2}, 0));
        System.out.println(searchRotatedArray(new int[]{4,5,6,7,0,1,2}, 3));

        int[] arr = new int[]{1,5,3,3,7,6,9,1};
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));

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

        System.out.println(splitLargest(new int[]{7,2,5,10,8}, 2));
    }
}
