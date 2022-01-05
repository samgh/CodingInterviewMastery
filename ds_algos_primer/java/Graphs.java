/*
 *   Title: Graphs
 *
 *   This file contains the template for the Graph exercises in
 *   the DS & Algos Primer. Fill in the exercises here and refer to
 *   GraphSolutions.java for the complete code samples.
 *
 *   Execution: javac Graphs.java && java -ea Graphs
 */

import java.util.*;

public class Graphss {

    /*
     * Exercise 1.1: Given the following graph (see workbook), write out by hand
     * the representation of the graph as an Adjacency Matrix and an Adjacency
     * List.
     *
     * Adjacency Matrix:
     *
     *
     * Adjacecny List:
     */

    /*
     * Exercise 1.2: Implement two classes to represent a graph as an Adjacency
     * Matrix and an Adjacency List
     */
    public static class AdjacencyMatrix {

        /*
         * Constructor - Initializes a graph with n nodes and no edges
         *
         * Time Complexity:
         * Space Complexity:
         */
        public AdjacencyMatrix(int n) {
            // INSERT YOUR CODE HERE
        }

        /*
         * Add an edge from node1 to node2
         *
         * Time Complexity:
         * Space Complexity:
         */
        public void addEdge(int node1, int node2) {
            // INSERT YOUR CODE HERE
        }

        /*
         * Remove an edge from node1 to node2
         *
         * Time Complexity:
         * Space Complexity:
         */
        public void removeEdge(int node1, int node2) {
            // INSERT YOUR CODE HERE
        }

        /*
         * Get all neighbors of node
         *
         * Time Complexity:
         * Space Complexity:
         */
        public List<Integer> neighbors(int node) {
            // INSERT YOUR CODE HERE
        }
    }

    public static class AdjacencyList {

        /*
         * Constructor - Initializes a graph with n nodes and no edges
         *
         * Time Complexity:
         * Space Complexity:
         */
        public AdjacencyList(int n) {
            // INSERT YOUR CODE HERE
        }

        /*
         * Add an edge from node1 to node2
         *
         * Time Complexity:
         * Space Complexity:
         */
        public void addEdge(int node1, int node2) {
            // INSERT YOUR CODE HERE
        }

        /*
         * Remove an edge from node1 to node2
         *
         * Time Complexity:
         * Space Complexity:
         */
        public void removeEdge(int node1, int node2) {
            // INSERT YOUR CODE HERE
        }

        /*
         * Get all neighbors of node
         *
         * Time Complexity:
         * Space Complexity:
         */
         public List<Integer> neighbors(int node) {
             // INSERT YOUR CODE HERE
         }
    }

    /*
     * Exercise 1.3: Implement a function to convert an adjacency matrix into an
     * adjacency list and vice versa.
     */

    /*
     * Convert an adjacency matrix into an adjacency list
     *
     * Time Complexity:
     * Space Complexity:
     */
    public static List<Set<Integer>> adjacencyMatrixToList(boolean[][] matrix) {
        // INSERT YOUR CODE HERE
    }

    /*
     * Convert an adjacency list into an adjacency matrix
     *
     * Time Complexity:
     * Space Complexity:
     */
    public static boolean[][] adjacencyListToMatrix(List<Set<Integer>> list) {
        // INSERT YOUR CODE HERE
    }

    /*
     * Exercise 1.4: Write a function to clone a graph
     */

    // Node class from Leetcode: https://leetcode.com/problems/clone-graph/
    public class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    /*
     * Make a deep copy of the graph
     *
     * Time Complexity:
     * Space Complexity:
     */
    public Node cloneGraph(Node node) {
        // INSERT YOUR CODE HERE
    }


    /*
     * Exercise 2.1: Write a function to determine whether a path exists between
     * two nodes
     */

    /*
     * Implement using an adjacency list
     *
     * Time Complexity:
     * Space Complexity:
     */
    public static boolean validPathList(int n, List<List<Integer>> edges, int start, int end) {
        // INSERT YOUR CODE HERE
    }

    /*
     * Implement using an adjacency matrix
     *
     * Time Complexity:
     * Space Complexity:
     */
    public static boolean validPathMatrix(int n, boolean[][] edges, int start, int end) {
        // INSERT YOUR CODE HERE
    }

    /*
     * Exercise 2.2: Write a function to find the length of the shortest path
     * between two nodes (use BFS)
     *
     * Time Complexity:
     * Space Complexity:
     */
    public static int lengthOfShortestPath(List<List<Integer>> edges, int start, int end) {
        // INSERT YOUR CODE HERE
    }

    /*
     * Exercise 2.3: Write a function to find the shortest path between two
     * nodes (use BFS)
     *
     * Time Complexity:
     * Space Complexity:
     */
    public static List<Integer> shortestPath(List<List<Integer>> edges, int start, int end) {
        // INSERT YOUR CODE HERE
    }

    /*
     * Exercise 2.4: Write a function to find ALL the paths between two nodes
     *
     * Time Complexity:
     * Space Complexity:
     */
    public static List<List<Integer>> allPaths(List<List<Integer>> edges, int start, int end) {
        // INSERT YOUR CODE HERE
    }

    /*
     * Exercise 3.1: Course Scheduling
     *
     * Time Complexity:
     * Space Complexity:
     */
    public static boolean courseScheduling(int numCourses, int[][] prerequisites) {
        // INSERT YOUR CODE HERE
    }

    /*
     * Exercise 3.2: Course Scheduling II
     *
     * Time Complexity:
     * Space Complexity:
     */
    public static int[] courseSchedulingII(int numCourses, int[][] prerequisites) {
        // INSERT YOUR CODE HERE
    }

    /*
     * Exercise 3.3: Alien Dictionary
     *
     * Time Complexity:
     * Space Complexity:
     */
    public static String alienDictionary(String[] words) {
        // INSERT YOUR CODE HERE
    }

    /*
     * Exercise 4.1: Keys and Rooms
     *
     * Time Complexity:
     * Space Complexity:
     */
    public static boolean keysAndRooms(List<List<Integer>> rooms) {
        // INSERT YOUR CODE HERE
    }

    /*
     * Exercise 4.2: Evaluate Division
     *
     * Time Complexity:
     * Space Complexity:
     */
    public static double[] evaluateDivision(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // INSERT YOUR CODE HERE
    }

    /*
     * Exercise 4.3: Sliding Puzzle
     *
     * Time Complexity:
     * Space Complexity:
     */
    public static int slidingPuzzle(int[][] board) {
        // INSERT YOUR CODE HERE
    }


    public static void main(String[] args) {
        // ADD YOUR TEST CASES HERE
    }
}
