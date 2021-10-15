/*
 *   Title: ArraysAndStrings
 *
 *   This file contains the solutions for the Arrays and Strings exercises in
 *   the DS & Algos Primer. If you have not already attempted these exercises,
 *   we highly recommend you complete them before reviewing the solutions here.
 *
 *   Execution: javac ArraysAndStrings.java && java -ea ArraysAndStrings
 */

import java.util.*;

public class ArraysAndStrings {

    // Exercise 1.1: Write a function that takes an integer array and reverses
    // the values in place
    public static void reverseArray(int[] arr) {
        // We will iterate to the midpoint of the array. For each value, we can
        // get the index its supposed to swap with by computing arr.length-i-1
        for (int i = 0; i < arr.length/2; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length-i-1];
            arr[arr.length-i-1] = temp;
        }
    }

    // Exercise 1.2: Given a 2D matrix, write a function to print the values in
    // the matrix in a clockwise spiral from outside to inside
    public static void printSpiral(int[][] arr) {
        // We need to keep track of the boundaries of the current layer of the
        // spiral that we are traversing
        int minRow = 0;
        int minCol = 0;
        int maxRow = arr.length-1;
        int maxCol = arr[0].length-1;

        // Once the mins and maxes converge, we are at the center of the spiral.
        // The spiral follows a fixed set of steps. We go left, down, right, up.
        // For each of these, we just interate to the bounds, so we express each
        // one explicitly.
        while (minRow < maxRow && minCol < maxCol) {
            // Go across the top
            for (int col = minCol; col <= maxCol; col++) {
                System.out.println(arr[minRow][col]);
            }
            minRow++;

            // Go down the right side
            for (int row = minRow ; row <= maxRow; row++) {
                System.out.println(arr[row][maxCol]);
            }
            maxCol--;

            // Go across the bottom
            for (int col = maxCol; col >= minCol; col--) {
                System.out.println(arr[maxRow][col]);
            }
            maxRow--;

            // Go up the left side
            for (int row = maxRow; row >= minRow; row--) {
                System.out.println(arr[row][minCol]);
            }
            minCol++;
        }
    }

    // Exercise 1.3: Given a 2D matrix, write a function to print the values in
    // the matrix in a zig-zag order
    public static void printZigZag(int[][] arr) {
        int row = 0;
        int col = 0;

        // Like the spiral, we have clearly defined directions we need to go. In
        // this case we either go up to the right or down to the left. We define
        // each of these explicitly and just go back and forth between doing one
        // and the other
        while (true) {

            // Go up to the right
            while (row > 0 && col < arr[0].length-1) {
                System.out.println(arr[row][col]);
                row--;
                col++;
            }
            // Without this we won't print the final value in the diagonal
            System.out.println(arr[row][col]);

            // Check whether we're at the bottom right corner
            if (row == arr.length-1 && col == arr[0].length-1) break;

            // We need to update our positiion differently depending on whether
            // we're still going along the top of the matrix or down the
            // righthand side
            else if (col+1 < arr[0].length) col++;
            else row++;

            // Go down to the left
            while (row < arr.length-1 && col > 0) {
                System.out.println(arr[row][col]);
                row++;
                col--;
            }
            // Without this we won't print the final value in the diagonal
            System.out.println(arr[row][col]);

            // Check whether we're at the bottom right corner
            if (row == arr.length-1 && col == arr[0].length-1) break;

            // Are we going along the lefthand side or the bottom?
            else if (row+1 < arr.length) row++;
            else col++;
        }
    }

    // Sample test cases
    public static void main(String[] args) {
        int[] toReverse = new int[]{1,2,3,4,5};
        reverseArray(toReverse);
        System.out.println(Arrays.toString(toReverse));

        int[][] matrix = new int[4][5];
        int val = 1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = val++;
            }
        }
        printSpiral(matrix);

        printZigZag(matrix);
    }
}
