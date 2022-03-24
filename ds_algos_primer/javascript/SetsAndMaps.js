/*
 *   Title: SetsAndMaps
 *
 *   This file contains the template for the Set and Map exercises in
 *   the DS & Algos Primer. Fill in the exercises here and refer to
 *   S.java for the complete code samples.
 *
 *   Execution: node SetsAndMaps.js
 */

/**
 * Simple String hashing function from here:
 * https://stackoverflow.com/questions/194846/is-there-any-kind-of-hash-code-function-in-javascript
 *
 * @param{number} n
 * @return{number}
 */
String.prototype.hashCode = function(n){
    var hash = 0;
    for (var i = 0; i < this.length; i++) {
        var character = this.charCodeAt(i);
        hash = ((hash<<5)-hash)+character;
        hash = hash & hash; // Convert to 32bit integer
    }
    return hash % n;
}

/**
 * Exercise 1.1: Implement a HashSet
 *
 * @param{number} capacity
 */
function MyHashSet(capacity) {

    /**
     * Add a value to the set
     *
     * Time Complexity:
     * Space Complexity:
     *
     * @param{string} val
     */
    this.add = function(val) {
        // INSERT YOUR CODE HERE
    }

    /**
     * Does the set contain val?
     *
     * Time Complexity:
     * Space Complexity:
     *
     * @param{string} val
     * @return{boolean}
     */
    this.contains = function(val) {
        // INSERT YOUR CODE HERE
    }

    /**
     * Remove value from the set
     *
     * Time Complexity:
     * Space Complexity:
     *
     * @param{string} val
     */
    this.remove = function(val) {
        // INSERT YOUR CODE HERE
    }

    /**
     * Convert the set into a list
     *
     * Time Complexity:
     * Space Complexity:
     *
     * @return{string[]}
     */
    this.toList = function() {
        // INSERT YOUR CODE HERE
    }

    /**
     * Exercise 2.1: Resize the set backing array
     *
     * We probably want to do this once size > 2*capacity to keep our lookup
     * times low
     *
     * Time Complexity:
     * Space Complexity:
     *
     * @param{number} newCapacity
     */
    this.resize = function(newCapacity) {
        // INSERT YOUR CODE HERE
    }
}

/**
 * Exercise 1.2: Implement a HashMap
 *
 * @param{number} capacity
 */
function MyHashMap(capacity) {

    /**
     * Add a key-value pair to the map. If the key already exists, update
     * the corresponding value
     *
     * Time Complexity:
     * Space Complexity:
     *
     * @param{string} key
     * @param{string} val
     */
    this.put = function(key, val) {
        // INSERT YOUR CODE HERE
    }

    /**
     * Get the value in the map for the corresponding key
     *
     * Time Complexity:
     * Space Complexity:
     *
     * @param{string} key
     * @return{string}
     */
    this.get = function(key) {
        // INSERT YOUR CODE HERE
    }

    /**
     * Return true if the key is in the map
     *
     * Time Complexity:
     * Space Complexity:
     *
     * @param{string} key
     * @return{boolean}
     */
    this.containsKey = function(key) {
        // INSERT YOUR CODE HERE
    }

    /**
     * Remove the key-value pair from the map
     *
     * Time Complexity:
     * Space Complexity:
     *
     * @param{string} key
     */
    this.remove = function(key) {
        // INSERT YOUR CODE HERE
    }

    /**
     * Exercise 2.2: Resize the backing array to the new capacity
     *
     * Time Complexity:
     * Space Complexity:
     *
     * @param{number} newCapacity
     */
    this.resize = function(newCapacity) {
        // INSERT YOUR CODE HERE
    }
}

/**
 * Exercise 1.3: Flatten a dictionary
 *
 * Time Complexity:
 * Space Complexity:
 *
 * @param{Object} dict
 * @return{Object}
 */
var flattenDictionary = function(dict) {
    // INSERT YOUR CODE HERE
}


var tester = function() {
    // INSERT YOUR TEST CASES HERE
}

tester();
