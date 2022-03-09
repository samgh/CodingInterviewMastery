/*
 *   Title: SetAndMapSolutions
 *
 *   This file contains the solutions for the Set and Map exercises in
 *   the DS & Algos Primer. If you have not already attempted these exercises,
 *   we highly recommend you complete them before reviewing the solutions here.
 *
 *   Execution: node SetAndMapSolutions.js
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
    var data = [];
    while (capacity--) data.push([]);
    var size = 0;

    /**
     * Add a value to the set
     *
     * Time Complexity: O(size(set) + len(val))
     * Space Complexity: O(1)
     *
     * @param{string} val
     */
    this.add = function(val) {
        // Find the bucket index
        var idx = val.hashCode(data.length);

        // If the value isn't in the bucket, add it to the bucket
        if (!data[idx].includes(val)) {
            data[idx].push(val);
            size++;
        }
    }

    /**
     * Does the set contain val?
     *
     * Time Complexity: O(size(set) + len(val))
     * Space Complexity: O(1)
     *
     * @param{string} val
     * @return{boolean}
     */
    this.contains = function(val) {
        // Find the bucket and see if it contains the desired value
        var idx = val.hashCode(data.length);
        return data[idx].includes(val);
    }

    /**
     * Remove value from the set
     *
     * Time Complexity: O(size(set) + len(val))
     * Space Complexity: O(1)
     *
     * @param{string} val
     */
    this.remove = function(val) {
        // Find the bucket and if it contains the value, remove it
        var idx = val.hashCode(data.length);

        var idxToRemove = data[idx].indexOf(val);

        if (idxToRemove > -1) {
            data[idx].splice(idxToRemove, 1);
            size--;
        }
    }

    /**
     * Convert the set into a list
     *
     * Time Complexity: O(n * average value length)
     * Space Complexity: O(1)
     *
     * @return{string[]}
     */
    this.toList = function() {
        return data.flat();
    }

    /**
     * Exercise 2.1: Resize the set backing array
     *
     * We probably want to do this once size > 2*capacity to keep our lookup
     * times low
     *
     * Time Complexity: O(capacity + size(set) * average value length)
     * Space Complexity: O(1)
     *
     * @param{number} newCapacity
     */
    this.resize = function(newCapacity) {
        // Create a new backing array
        newData = []
        while (newCapacity--) newData.push([]);

        // For every value in the current set, we need to rehash it to see
        // what bucket it goes in
        data.forEach(function(l) {
            l.forEach(function(s) {
                var idx = s.hashCode(newData.length);
                newData[idx].push(s);
            });
        });

        data = newData;
    }
}

/**
 * Exercise 1.2: Implement a HashMap
 *
 * @param{number} capacity
 */
function MyHashMap(capacity) {
    var data = [];
    while (capacity--) data.push([]);
    var size = 0;

    /**
     * Add a key-value pair to the map. If the key already exists, update
     * the corresponding value
     *
     * Time Complexity: O(size(map) + len(key))
     * Space Complexity: O(1)
     *
     * @param{string} key
     * @param{string} val
     */
    this.put = function(key, val) {
        // Get the bucket index
        var idx = key.hashCode(data.length);

        // Check whether the map already contains the key and if it does,
        // update the value
        for (var i = 0; i < data[idx].length; i++) {
            if (data[idx][i][0] == key) {
                data[idx][i][1] = val;
                return;
            }
        }

        // The key is not in the map, so add the key/value pair
        data[idx].push([key, val]);
        size++;
    }

    /**
     * Get the value in the map for the corresponding key
     *
     * Time Complexity: O(size(map) + len(key))
     * Space Complexity: O(1)
     *
     * @param{string} key
     * @return{string}
     */
    this.get = function(key) {
        // Get the bucket index
        var idx = key.hashCode(data.length);

        // Find the key/value pair with the matching key
        for (var i = 0; i < data[idx].length; i++) {
            if (data[idx][i][0] == key) return data[idx][i][1];
        }

        // If they key is not in the map, return null
        return null;
    }

    /**
     * Return true if the key is in the map
     *
     * Time Complexity: O(size(map) + len(key))
     * Space Complexity: O(1)
     *
     * @param{string} key
     * @return{boolean}
     */
    this.containsKey = function(key) {
        // Get the bucket index
        var idx = key.hashCode(data.length);

        // See if the key exists in the current bucket
        for (var i = 0; i < data[idx].length; i++) {
            if (data[idx][i][0] == key) return true;
        }

        return false;
    }

    /**
     * Remove the key-value pair from the map
     *
     * Time Complexity: O(size(map) + len(key))
     * Space Complexity: O(1)
     *
     * @param{string} key
     */
    this.remove = function(key) {
        // Get the bucket index
        var idx = key.hashCode(data.length);

        // Find the map entry that needs to be removed
        for (var i = 0; i < data[idx].length; i++) {
            if (data[idx][i][0] == key) {
                console.log(data[idx]);
                data[idx].splice(i,1);
                console.log(data[idx]);
                size--;
                return;
            }
        }
    }

    /**
     * Exercise 2.2: Resize the backing array to the new capacity
     *
     * Time Complexity: O(capacity + size(map) * average value length)
     * Space Complexity: O(1)
     *
     * @param{number} newCapacity
     */
    this.resize = function(newCapacity) {
        // Initialize new array
        var newData = [];
        while (newCapacity--) newData.push([]);

        // Rehash every entry and add to the new array
        data.forEach(function(l) {
            l.forEach(function(e) {
                var idx = e[0].hashCode(newData.length);
                newData[idx].push(e);
            });
        });

        data = newData;
    }
}

var tester = function() {
    var s = new MyHashSet(10);
    s.add("abc");
    s.add("bcd");
    s.add("cde");
    console.log(s.contains("bcd"));
    s.remove("bcd");
    console.log(s.contains("bcd"));
    console.log(s.toList());
    s.resize(20);
    console.log(s.toList());

    var m = new MyHashMap(10);
    m.put("abc","bcd");
    m.put("123","234");
    m.put("xyz","abc");
    m.put("abc","xyz");
    console.log(m.containsKey("abc"));
    console.log(m.get("abc"));
    m.remove("abc");
    console.log(m.containsKey("abc"));
    console.log(m.get("abc"));
    m.resize(100);
    console.log(m.containsKey("xyz"));
    console.log(m.get("xyz"));
}

tester();
