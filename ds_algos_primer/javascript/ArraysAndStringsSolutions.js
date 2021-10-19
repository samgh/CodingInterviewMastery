/**
 *   Title: ArraysAndStringsSolutions
 *
 *   This file contains the solutions for the Arrays and Strings exercises in
 *   the DS & Algos Primer. If you have not already attempted these exercises,
 *   we highly recommend you complete them before reviewing the solutions here.
 *
 *   Execution: node ArraysAndStringsSolutions.js

/**
 * Exercise 1.1: Write a function that takes an integer array and reverses the
 * values in place
 *
 * @param {number[]} arr
 */
var reverseArray = function(arr) {
    // We will iterate to the midpoint of the array. For each value, we can
    // get the index its supposed to swap with by computing arr.length-i-1
    for (var i = 0; i < arr.length/2; i++) {
        var temp = arr[i];
        arr[i] = arr[arr.length-i-1];
        arr[arr.length-i-1] = temp;
    }
}

/**
 * Exercise 1.2: Given a 2D matrix, write a function to print the values in the
 * matrix in a clockwise spiral from outside to inside
 *
 * @param {number[][]} arr
 */
var printSpiral = function(arr) {
    // We need to keep track of the boundaries of the current layer of the
    // spiral that we are traversing
    var minRow = 0;
    var minCol = 0;
    var maxRow = arr.length-1;
    var maxCol = arr[0].length-1;

    // Once the mins and maxes converge, we are at the center of the spiral.
    // The spiral follows a fixed set of steps. We go left, down, right, up.
    // For each of these, we just interate to the bounds, so we express each
    // one explicitly.
    while (minRow < maxRow && minCol < maxCol) {
        // Go across the top
        for (var col = minCol; col <= maxCol; col++) {
            console.log(arr[minRow][col]);
        }
        minRow++;

        // Go down the right side
        for (var row = minRow ; row <= maxRow; row++) {
            console.log(arr[row][maxCol]);
        }
        maxCol--;

        // Go across the bottom
        for (var col = maxCol; col >= minCol; col--) {
            console.log(arr[maxRow][col]);
        }
        maxRow--;

        // Go up the left side
        for (var row = maxRow; row >= minRow; row--) {
            console.log(arr[row][minCol]);
        }
        minCol++;
    }
}

/**
 * Exercise 1.3: Given a 2D matrix, write a function to print the values in the
 * matrix in a zig-zag order
 *
 * @param{number[][]}
 */
var printDiagonals = function(arr) {
    var row = 0;
    var col = 0;

    // Like the spiral, we have clearly defined directions we need to go. In
    // this case we either go up to the right or down to the left. We define
    // each of these explicitly and just go back and forth between doing one
    // and the other
    while (true) {
        // Go up to the right
        while (row > 0 && col < arr[0].length-1) {
            console.log(arr[row][col]);
            row--;
            col++;
        }
        // Without this we won't print the final value in the diagonal
        console.log(arr[row][col]);

        // Check whether we're at the bottom right corner
        if (row == arr.length-1 && col == arr[0].length-1) break;

        // We need to update our positiion differently depending on whether
        // we're still going along the top of the matrix or down the
        // righthand side
        else if (col+1 < arr[0].length) col++;
        else row++;

        // Go down to the left
        while (row < arr.length-1 && col > 0) {
            console.log(arr[row][col]);
            row++;
            col--;
        }
        // Without this we won't print the final value in the diagonal
        console.log(arr[row][col]);

        // Check whether we're at the bottom right corner
        if (row == arr.length-1 && col == arr[0].length-1) break;

        // Are we going along the lefthand side or the bottom?
        else if (row+1 < arr.length) row++;
        else col++;
    }
}

/**
 * Exercise 1.4: Write a function that takes in a string and removes every
 * even-indexed character
 *
 * @param{string}
 */
var removeEven = function(s) {
    // It is more complexity-efficient for us to add all the characters to an
    // array and join them at the end
    var charArray = []

    // Increment by 2 each time to only visit odd indices
    for (var i = 1; i < s.length; i=i+2) {
        charArray.push(s[i]);
    }

    return charArray.join("");
}


var tester = function() {
    var toReverse = [1,2,3,4,5];
    reverseArray(toReverse);
    console.log(toReverse);

    var matrix = [[1,2,3,4,5],[6,7,8,9,10],[11,12,13,14,15],[16, 17,18,19,20]];
    printSpiral(matrix);
    printDiagonals(matrix);

    console.log(removeEven("abcdefg"));
}

tester();
