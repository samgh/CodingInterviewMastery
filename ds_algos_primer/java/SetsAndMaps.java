/*
 *   Title: SetsAndMaps
 *
 *   This file contains the template for the Set and Map exercises in
 *   the DS & Algos Primer. Fill in the exercises here and refer to
 *   SetAndMapSolutions.java for the complete code samples.
 *
 *   Execution: javac SetsAndMaps.java && java -ea SetsAndMaps
 */
import java.util.*;

public class SetAndMapSolutions {

    /*
     * Exercise 1.1: Implement a HashSet
     */
    public static class MyHashSet {

        /*
         * Constructor
         *
         * Time Complexity:
         * Space Complexity:
         */
        public MyHashSet(int capacity) {
            // INSERT YOUR CODE HERE
        }

        /*
         * Add a value to the set
         *
         * Time Complexity:
         * Space Complexity:
         */
        public void add(String val) {
            // INSERT YOUR CODE HERE
        }

        /*
         * Does the set contain val?
         *
         * Time Complexity:
         * Space Complexity:
         */
        public boolean contains(String val) {
            // INSERT YOUR CODE HERE
        }

        /*
         * Remove value from the set
         *
         * Time Complexity:
         * Space Complexity:
         */
        public void remove(String val) {
            // INSERT YOUR CODE HERE
        }

        /*
         * Convert the set into a list
         *
         * Time Complexity:
         * Space Complexity:
         */
        public List<String> toList() {
            // INSERT YOUR CODE HERE
        }

        /*
         * Exercise 2.1: Resize the set backing array
         *
         * We probably want to do this once size > 2*capacity to keep our lookup
         * times low
         *
         * Time Complexity:
         * Space Complexity:
         */
        public void resize(int newCapacity) {
            // INSERT YOUR CODE HERE
        }
    }

    /*
     * Exercise 1.2: Implement a HashMap
     */
    public static class MyHashMap {

        // Rather than have to maintain two parallel list for keys and values,
        // we can just create a MapEntry that contains both together
        private static class MapEntry {
            String key;
            String val;

            MapEntry(String key, String val) {
                this.key = key;
                this.val = val;
            }
        }

        /*
         * Constructor
         *
         * Time Complexity:
         * Space Complexity:
         */
        public MyHashMap(int capacity) {
            // INSERT YOUR CODE HERE
        }

        /*
         * Add a key-value pair to the map. If the key already exists, update
         * the corresponding value
         *
         * Time Complexity:
         * Space Complexity:
         */
        public void put(String key, String val) {
            // INSERT YOUR CODE HERE
        }

        /*
         * Get the value in the map for the corresponding key
         *
         * Time Complexity:
         * Space Complexity:
         */
        public String get(String key) {
            // INSERT YOUR CODE HERE
        }

        /*
         * Return true if the key is in the map
         *
         * Time Complexity:
         * Space Complexity:
         */
        public boolean containsKey(String key) {
            // INSERT YOUR CODE HERE
        }

        /*
         * Remove the key-value pair from the map
         *
         * Time Complexity:
         * Space Complexity:
         */
        public void remove(String key) {
            // INSERT YOUR CODE HERE
        }

        /*
         * Exercise 2.2: Resize the backing array to the new capacity
         *
         * Time Complexity:
         * Space Complexity:
         */
        public void resize(int newCapacity) {
            // INSERT YOUR CODE HERE
        }
    }

    /*
     * Exercise 1.3: Flatten a dictionary
     *
     * Time Complexity:
     * Space Complexity:
     */
    public static Map<String, Object> flattenDictionary(Map<String, Object> dict) {
        // INSERT YOUR TESTS HERE
    }

    // Sample test cases
    public static void main(String[] args) {
        // INSERT YOUR TESTS HERE
    }
}
