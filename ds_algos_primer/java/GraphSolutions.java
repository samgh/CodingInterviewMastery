/*
 *   Title: Graph Solutions
 *
 *   This file contains the solutions for the Graph exercises in the DS & Algos
 *   Primer. If you have not already attempted these exercises, we highly
 *   recommend you complete them before reviewing the solutions here.
 *
 *   Execution: javac GraphSolutions.java && java -ea GraphSolutions
 */

import java.util.*;

public class GraphSolutions {

    /*
     * Exercise 1.1: Given the following graph (see workbook), write out by hand
     * the representation of the graph as an Adjacency Matrix and an Adjacency
     * List.
     *
     * Adjacency Matrix:
     *    0  1  2  3  4
     * 0 [0, 1, 1, 0, 0]
     * 1 [0, 0, 1, 0, 1]
     * 2 [1, 0, 0, 1, 0]
     * 3 [0, 0, 1, 0, 0]
     * 4 [0, 0, 0, 0, 0]
     *
     *
     * Adjacecny List:
     * 0: [1, 2]
     * 1: [2, 4]
     * 2: [0, 3]
     * 3: [2]
     * 4: []
     */

    /*
     * Exercise 1.2: Implement two classes to represent a graph as an Adjacency
     * Matrix and an Adjacency List
     */
    public static class AdjacencyMatrix {

        // We'll store all of our edges here
        boolean[][] matrix;

        /*
         * Constructor - Initializes a graph with n nodes and no edges
         *
         * Time Complexity: O(n^2)
         * Space Complexity: O(1)
         */
        public AdjacencyMatrix(int n) {
            this.matrix = new boolean[n][n];
        }

        /*
         * Add an edge from node1 to node2
         *
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         */
        public void addEdge(int node1, int node2) {
            if (node1 < 0 || node1 >= this.matrix.length ||
                node2 < 0 || node2 > this.matrix.length) {
                throw new IndexOutOfBoundsException();
            }

            // Update the edge
            this.matrix[node1][node2] = true;
        }

        /*
         * Remove an edge from node1 to node2
         *
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         */
        public void removeEdge(int node1, int node2) {
            if (node1 < 0 || node1 >= this.matrix.length ||
                node2 < 0 || node2 > this.matrix.length) {
                throw new IndexOutOfBoundsException();
            }

            // Update the edge
            this.matrix[node1][node2] = false;
        }

        /*
         * Get all neighbors of node
         *
         * Time Complexity: O(n)
         * Space Complexity: O(1)
         */
        public List<Integer> neighbors(int node) {
            if (node < 0 || node >= this.matrix.length) {
                throw new IndexOutOfBoundsException();
            }

            // Iterate over the matrix and collect all the neighbors
            List<Integer> result = new LinkedList<>();
            for (int i = 0; i < this.matrix.length; i++) {
                if (this.matrix[node][i]) result.add(i);
            }

            return result;
        }
    }

    public static class AdjacencyList {

        // We'll store our edges here. Using sets will improve our efficiency
        // when updating edges for a specific node
        List<Set<Integer>> edges;

        /*
         * Constructor - Initializes a graph with n nodes and no edges
         *
         * Time Complexity: O(n)
         * Space Complexity: O(1)
         */
        public AdjacencyList(int n) {
            this.edges = new ArrayList<>();

            // Slighly inefficient but makes our life easier later to initialize
            // all of our sets up front
            for (int i = 0; i < n; i++) {
                this.edges.add(new HashSet<>());
            }
        }

        /*
         * Add an edge from node1 to node2
         *
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         */
        public void addEdge(int node1, int node2) {
            if (node1 < 0 || node1 >= this.edges.size() ||
                node2 < 0 || node2 > this.edges.size()) {
                throw new IndexOutOfBoundsException();
            }

            this.edges.get(node1).add(node2);
        }

        /*
         * Remove an edge from node1 to node2
         *
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         */
        public void removeEdge(int node1, int node2) {
            if (node1 < 0 || node1 >= this.edges.size() ||
                node2 < 0 || node2 > this.edges.size()) {
                throw new IndexOutOfBoundsException();
            }

            if (this.edges.get(node1).contains(node2)) {
                this.edges.get(node1).remove(node2);
            }
        }

        /*
         * Get all neighbors of node
         *
         * Time Complexity: O(n)
         * Space Complexity: O(1)
         */
         public List<Integer> neighbors(int node) {
             if (node < 0 || node >= this.edges.size()) {
                 throw new IndexOutOfBoundsException();
             }

             // Get the set of edges and add it to a list
             List<Integer> result = new LinkedList<>();
             result.addAll(this.edges.get(node));
             return result;
         }
    }

    /*
     * Exercise 1.3: Implement a function to convert an adjacency matrix into an
     * adjacency list and vice versa.
     */

    /*
     * Convert an adjacency matrix into an adjacency list
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     */
    public static List<Set<Integer>> adjacencyMatrixToList(boolean[][] matrix) {
        List<Set<Integer>> list = new LinkedList<>();

        // Iterate over the matrix and for every edge, add it to the list
        for (int i = 0; i < matrix.length; i++) {
            Set<Integer> edges = new HashSet<>();
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j]) edges.add(j);
            }
            list.add(edges);
        }

        return list;
    }

    /*
     * Convert an adjacency list into an adjacency matrix
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     */
    public static boolean[][] adjacencyListToMatrix(List<Set<Integer>> list) {
        boolean[][] matrix = new boolean[list.size()][list.size()];

        // Iterate over the list and add each edge to the matrix
        for (int i = 0; i < list.size(); i++) {
            Set<Integer> edges = list.get(i);
            for (int edge : edges) matrix[i][edge] = true;
        }

        return matrix;
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
     * Time Complexity: O(V+E)
     * Space Complexity: O(V)
     */
    public Node cloneGraph(Node node) {
        if (node == null) return null;

        // We need to do 2 things:
        //  1. Copy each node
        //  2. Copy all the edges
        // To copy the edges, we need to know what the mapping is of nodes in
        // the original graph to the nodes in the new graph. Therefore, we'll
        // traverse our graph and add everything to a map.
        //
        // This map stores oldNode -> newNode
        Map<Node, Node> nodes = new HashMap<Node, Node>();

        // Do DFS and add all the nodes to map as well as make a copy of each
        traverseAllNodes(node, nodes);

        // We have all of our nodes, now go through and add all the edges
        for (Node n : nodes.keySet()) {
            // n is our old node and newNode is our new node. To get the edges
            // for newNode, we need to find the edges for the old node and
            // then look up what the corresponding node is in our new graph
            Node newNode = nodes.get(n);
            for (Node neighbor : n.neighbors) {
                newNode.neighbors.add(nodes.get(neighbor));
            }
        }

        return nodes.get(node);
    }

    // Traverse our graph using DFS and add nodes/copies of nodes to nodes
    private void traverseAllNodes(Node node, Map<Node, Node> nodes) {
        // We've already visited this node
        if (nodes.containsKey(node)) return;

        // Add the node and a copy of the node to our map
        nodes.put(node, new Node(node.val));

        // Traverse all the neighbors
        for (Node n : node.neighbors) traverseAllNodes(n, nodes);
    }

    /*
     * Exercise 2.1: Write a function to determine whether a path exists between
     * two nodes
     */

    /*
     * Implement using an adjacency list
     *
     * Time Complexity: O(V + E)
     * Space Complexity: O(V)
     */
    public static boolean validPathList(int n, List<List<Integer>> edges, int start, int end) {
        // Do a DFS to see if the two nodes are connected
        return validPathList(n, edges, start, end, new HashSet<>());
    }

    private static boolean validPathList(int n, List<List<Integer>> edges, int start, int end, Set<Integer> visited) {
        // If we've found the target node, there is a valid path
        if (start == end) return true;

        // If we've already visited this node, skip it
        if (visited.contains(start)) return false;

        // Track that we've visited the current node
        visited.add(start);

        // Traverse every edge to the next node and see if any neighbor connects
        // to the target node
        for (int e : edges.get(start)) {
            if (validPathList(n, edges, e, end, visited)) return true;
        }

        // If we don't find a valid path, there is no valid path from this node
        return false;
    }

    /*
     * Implement using an adjacency matrix
     *
     * Time Complexity: O(V^2)
     * Space Complexity: O(V)
     */
    public static boolean validPathMatrix(int n, boolean[][] edges, int start, int end) {
        // Do a DFS to see if the two nodes are connected
        return validPathMatrix(n, edges, start, end, new HashSet<>());
    }

    private static boolean validPathMatrix(int n, boolean[][] edges, int start, int end, Set<Integer> visited) {
        // If we've found the target node, there is a valid path
        if (start == end) return true;

        // If we've already visited this node, skip it
        if (visited.contains(start)) return false;

        // Track that we've visited the current node
        visited.add(start);

        // Traverse every edge to the next node and see if any neighbor connects
        // to the target node
        for (int i = 0; i < edges[start].length; i++) {
            // We have to traverse all the possible edges to see which exist
            if (edges[start][i]) {
                if (validPathMatrix(n, edges, i, end, visited)) return true;
            }
        }

        // If we don't find a valid path, there is no valid path from this node
        return false;
    }

    /*
     * Exercise 2.2: Write a function to find the length of the shortest path
     * between two nodes (use BFS)
     *
     * Time Complexity: O(V + E)
     * Space Complexity: O(V)
     */
    public static int lengthOfShortestPath(List<List<Integer>> edges, int start, int end) {
        // For BFS, we use a queue
        Queue<Integer> toVisit = new LinkedList<>();

        // We need to track the level of each node in our BFS so when we find
        // our target, we know what the length of the path is
        Map<Integer, Integer> nodeDepth = new HashMap<>();

        // Initialize both with our start node
        toVisit.add(start);
        nodeDepth.put(start, 1);

        // Do our BFS
        while(!toVisit.isEmpty()) {
            int curr = toVisit.remove();

            // If we found the destination, return depth
            if (curr == end) return nodeDepth.get(curr);

            // Otherwise add all the neighbors to the queue to visit
            for (int e : edges.get(curr)) {
                // Make sure we haven't already visited the node
                if (!nodeDepth.containsKey(e)) {
                    toVisit.add(e);

                    // The distance to any node is the distance to the previous
                    // node + 1
                    nodeDepth.put(e, nodeDepth.get(curr)+1);
                }
            }
        }

        return -1;
    }

    /*
     * Exercise 2.3: Write a function to find the shortest path between two
     * nodes (use BFS)
     *
     * Time Complexity: O(V + E)
     * Space Complexity: O(V)
     */
    public static List<Integer> shortestPath(List<List<Integer>> edges, int start, int end) {
        // For BFS, we use a queue to store the next nodes to visit
        Queue<Integer> toVisit = new LinkedList<>();

        // To reconstruct the path, we need to track which node let us to which
        // other node. When we go from NodeA to NodeB, we'll add NodeB -> NodeA
        // to our map. Then once we've found our target node we'll be able to
        // traverse backwards to reconstruct the path
        Map<Integer, Integer> previousNodeInPath = new HashMap<>();

        // Add our starting node
        toVisit.add(start);
        previousNodeInPath.put(start, null);

        // Traverse until we visit all the nodes or we find our target node
        while(!toVisit.isEmpty()) {
            // If the current node is our target, we can stop searching
            int curr = toVisit.remove();
            if (curr == end) break;

            // Add all the neighbor nodes to our queue to search
            for (int e : edges.get(curr)) {
                // Rather than separately track which nodes we've visited, just
                // check whether it is in previousNodeInPath
                if (!previousNodeInPath.containsKey(e)) {
                    toVisit.add(e);
                    previousNodeInPath.put(e, curr);
                }
            }
        }

        // If we haven't visited the target node, then it's because there is no
        // valid path
        if (!previousNodeInPath.containsKey(end)) return null;

        // Reconstruct our path using the map. Start at the end of our path and
        // repeatedly look up the node that came before it until we get to the
        // beginning
        List<Integer> result = new LinkedList<>();
        Integer curr = end;
        while (curr != null) {
            result.add(0, curr);
            curr = previousNodeInPath.get(curr);
        }

        return result;
    }

    /*
     * Exercise 2.4: Write a function to find ALL the paths between two nodes
     *
     * Time Complexity: O(V!)
     * Space Complexity: O(V)
     */
    public static List<List<Integer>> allPaths(List<List<Integer>> edges, int start, int end) {
        // We'll use DFS and store every valid path to our list
        List<List<Integer>> result = new LinkedList<>();

        // All paths start with start
        List<Integer> path = new LinkedList<Integer>(Arrays.asList(new Integer[]{start}));
        Set<Integer> visited = new HashSet<>();
        visited.add(start);

        // Recursive call
        allPaths(edges, start, end, visited, result, path);

        return result;
    }

    private static void allPaths(List<List<Integer>> edges, int start, int end,
                                 Set<Integer> visited, List<List<Integer>> result,
                                 List<Integer> currPath) {
        // If we've found a valid path, add it to the results
        if (start == end) {
            // Make sure to make a deep copy of the list
            result.add(new LinkedList<>(currPath));
            return;
        }

        // Try traversing every edge
        for (int e : edges.get(start)) {
            // Check that we haven't visited the node
            if (!visited.contains(e)) {
                // Update the path and visited, then make recursive call
                currPath.add(e);
                visited.add(e);
                allPaths(edges, e, end, visited, result, currPath);

                // Make sure to backtrack
                currPath.remove(currPath.size()-1);
                visited.remove(e);
            }
        }
    }

    /*
     * Exercise 3.1: Course Scheduling
     *
     * Time Complexity: O(V^2)
     * Space Complexity: O(V + E)
     */
    public static boolean courseScheduling(int numCourses, int[][] prerequisites) {
        // We'll convert our prerequisites into an adjacency list
        List<Set<Integer>> adjList = new ArrayList<Set<Integer>>(numCourses);
        for (int i = 0; i < numCourses; i++) adjList.add(new HashSet<>());

        // Add all the edges to our adjacency list
        for (int[] pre : prerequisites) {
            adjList.get(pre[0]).add(pre[1]);
        }

        // Use a queue (or other list, order doesn't matter) to keep track of
        // which courses don't have any prerequisites
        Queue<Integer> noDependencies = new LinkedList<>();
        for (int i = 0; i < adjList.size(); i++) {
            if (adjList.get(i).isEmpty()) noDependencies.add(i);
        }

        // If we track the courses we've taken, it's easy to check at the end
        // whether we found a valid solution
        int count = 0;

        // Repeatedly remove dependencies until we've gone through all courses
        // or there are no courses that don't have dependencies
        while (!noDependencies.isEmpty()) {
            int curr = noDependencies.remove();
            count++;

            // Remove the course as a dependency from all the other courses
            for (int i = 0; i < numCourses; i++) {
                Set<Integer> set = adjList.get(i);
                if (set.contains(curr)) {
                    set.remove(curr);

                    // If it has no more dependencies, we can take this course
                    if (set.isEmpty()) noDependencies.add(i);
                }
            }
        }

        return count == numCourses;
    }

    /*
     * Exercise 3.2: Course Scheduling II
     *
     * Time Complexity: O(V^2)
     * Space Complexity: O(V + E)
     */
    public static int[] courseSchedulingII(int numCourses, int[][] prerequisites) {
        // We'll convert our prerequisites into an adjacency list
        List<Set<Integer>> adjList = new ArrayList<Set<Integer>>(numCourses);
        for (int i = 0; i < numCourses; i++) adjList.add(new HashSet<>());

        // Add all the edges to our adjacency list
        for (int[] pre : prerequisites) {
            adjList.get(pre[0]).add(pre[1]);
        }


        // Use a queue (or other list, order doesn't matter) to keep track of
        // which courses don't have any prerequisites
        Queue<Integer> noDependencies = new LinkedList<>();
        for (int i = 0; i < adjList.size(); i++) {
            if (adjList.get(i).isEmpty()) noDependencies.add(i);
        }

        // We'll add courses to our result array as we take them
        int[] result = new int[numCourses];
        int idx = 0;

        // Repeatedly remove dependencies until we've gone through all courses
        // or there are no courses that don't have dependencies
        while (!noDependencies.isEmpty()) {
            int curr = noDependencies.remove();
            result[idx++] = curr;

            // Remove the course as a dependency from all the other courses
            for (int i = 0; i < numCourses; i++) {
                Set<Integer> set = adjList.get(i);
                if (set.contains(curr)) {
                    set.remove(curr);

                    // If it has no more dependencies, we can take this course
                    if (set.isEmpty()) noDependencies.add(i);
                }
            }
        }

        if (idx == numCourses) return result;
        return new int[0];
    }

    /*
     * Exercise 3.3: Alien Dictionary
     *
     * Time Complexity: O(max(sum(length of all strings), # unique characters ^ 2))
     * Space Complexity: O(# unique characters)
     */
    public static String alienDictionary(String[] words) {
        // Per Leetcode testcases, ["abcd", "abc"] would be invalid. We
        // specifically check for this edge case here
        for (int i = 0; i < words.length-1; i++) {
            if (words[i].length() <= words[i+1].length()) continue;
            if (prefixEqual(words[i], words[i+1], words[i+1].length())) return "";
        }

        // Generate a graph of which chars come before which other chars
        Map<Character, Set<Character>> graph = generateAlienGraph(words);

        // Once we have our graph, this is a standard topological sort
        Queue<Character> noDependencies = new LinkedList<>();
        for (Map.Entry<Character, Set<Character>> e : graph.entrySet()) {
            // Start with all the characters that have no dependencies
            if (e.getValue().isEmpty()) noDependencies.add(e.getKey());
        }

        StringBuilder result = new StringBuilder();

        // For each character, add it to the result and remove it as a dependency
        // from all of the other characters
        while (!noDependencies.isEmpty()) {
            char curr = noDependencies.remove();
            result.append(curr);

            for (Map.Entry<Character, Set<Character>> e : graph.entrySet()) {
                Set<Character> set = e.getValue();
                if (set.contains(curr)) {
                    set.remove(curr);
                    if (set.isEmpty()) noDependencies.add(e.getKey());
                }
            }
        }

        // Check that our result is valid
        if (result.length() != graph.keySet().size()) return "";
        return result.toString();
    }

    // Helper function to convert our strings into a graph
    private static Map<Character, Set<Character>> generateAlienGraph(String[] words) {
        // Since our nodes are character values and not just integers, we'll
        // use a map rather than a list
        Map<Character, Set<Character>> adjList = new HashMap<>();

        // Add all the nodes to our map
        for (String word : words) {
            for (char c : word.toCharArray()) {
                if (!adjList.containsKey(c)) adjList.put(c, new HashSet<>());
            }
        }

        // We compare each sequential pair of strings. We essentially just want
        // to find the first different character because that tells us which
        // character of the two comes first in lexographical order
        for (int i = 0; i < words.length - 1; i++) {
            // Find the first character that is different
            for (int j = 0; j < Math.min(words[i].length(), words[i+1].length()); j++) {
                if (words[i].charAt(j) == words[i+1].charAt(j)) continue;

                // When we find a character that is different, add the dependency
                // to our adjacency list
                adjList.get(words[i+1].charAt(j)).add(words[i].charAt(j));
                break;
            }
        }

        return adjList;
    }

    // Helper function to test if two strings have the same prefix of length len
    private static boolean prefixEqual(String a, String b, int len) {
        for (int i = 0; i < len; i++) {
            if (a.charAt(i) != b.charAt(i)) return false;
        }
        return true;
    }

    /*
     * Exercise 4.1: Keys and Rooms
     *
     * Time Complexity: O(# rooms + # keys)
     * Space Complexity: O(# rooms)
     */
    public static boolean keysAndRooms(List<List<Integer>> rooms) {
        // We do a graph search where the rooms are the nodes and the edges are
        // the keys in each room. BFS is a simpler implementation but we could
        // do either BFS or DFS
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> toVisit = new LinkedList<>();
        toVisit.add(0);

        // DO the search
        while (!toVisit.isEmpty()) {
            int curr = toVisit.remove();
            visited.add(curr);

            // Add keys in the current room to the next possible rooms
            for (int room : rooms.get(curr)) {
                if (!visited.contains(room)) toVisit.add(room);
            }
        }

        // Return true if we successfully visited all the rooms
        return visited.size() == rooms.size();
    }

    /*
     * Exercise 4.2: Evaluate Division
     *
     * Time Complexity: O(# queries * # variables * # equations)
     * Space Complexity: O(# variables)
     */
    public static double[] evaluateDivision(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // For each query, do DFS to determine series of operations required
        // to compute the value
        double[] result = new double[queries.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = evaluateDivision(equations, values, queries.get(i).get(0), queries.get(i).get(1), new HashSet<>());
        }
        return result;
    }

    // Do DFS
    private static double evaluateDivision(List<List<String>> equations, double[] values, String curr, String dest, Set<String> visited) {
        // Check if we've already computed a variable
        if (visited.contains(curr)) return -1;

        // The result of something divided by itself is 1
        if (curr.equals(dest)) return 1.0;

        // Track that we've seen a variable
        visited.add(curr);

        // Iterate over each equation to see which variables we can compute
        // given our current variable. Then repeat until we find the target
        // variable
        for (int i = 0; i < equations.size(); i++) {

            // If the first variable in the equation is our current variable,
            // then we multiply by the value. If it's the second variable then
            // we multiply by 1/the value
            List<String> equation = equations.get(i);
            if (equation.get(0).equals(curr)) {
                double result = evaluateDivision(equations, values, equation.get(1), dest, visited);
                if (result >= 0) return result * values[i];
            } else if (equation.get(1).equals(curr)) {
                double result = evaluateDivision(equations, values, equation.get(0), dest, visited);
                if (result >= 0) return result / values[i];
            }
        }

        // Backtrack
        visited.remove(curr);

        return -1;
    }

    /*
     * Exercise 4.3: Sliding Puzzle
     *
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * Given the fixed board size, the time doesn't change based on the input
     */
    public static int slidingPuzzle(int[][] board) {
        // Since we have a fixed size board, we can explicitly express the
        // valid moves for each possible position of the empty space as well as
        // the win condition
        int[][] moves = new int[][]{{1,3}, {0,2,4}, {1,5}, {0,4}, {1,3,5}, {2,4}};
        String solved = "123450";

        // We will represent the board as a string because it makes it way
        // easier for us to compare and add to a map
        String linearBoard = "";

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // We just add all the values from the board to our string
                linearBoard += board[i][j];
            }
        }

        // Now we do BFS to find the shortest path. We need to track the depth
        // of each board state so that we can get our result
        Queue<String> toVisit = new LinkedList<String>();
        Map<String, Integer> depth = new HashMap<>();

        toVisit.add(linearBoard);
        depth.put(linearBoard, 0);

        while (!toVisit.isEmpty()) {
            String curr = toVisit.remove();
            if (curr.equals(solved)) break;

            // The index of the empty spot tells us what valid moves we can make
            int idx = curr.indexOf('0');

            // Go through each possible move and add it to our queue
            for (int move : moves[idx]) {
                String swapped = swap(curr, idx, move);
                if (!depth.containsKey(swapped)) {
                    toVisit.add(swapped);
                    depth.put(swapped, depth.get(curr) + 1);
                }
            }
        }

        // Either we found a valid path or we didn't
        if (!depth.containsKey(solved)) return -1;
        return depth.get(solved);
    }

    // Simple helper function to swap two characters in a string
    private static String swap(String s, int i, int j) {
        StringBuilder sb = new StringBuilder(s);

        char temp = s.charAt(j);
        sb.setCharAt(j, s.charAt(i));
        sb.setCharAt(i, temp);
        return sb.toString();
    }

    public static void main(String[] args) {
        AdjacencyMatrix am = new AdjacencyMatrix(4);
        am.addEdge(1,2);
        am.addEdge(1,3);
        System.out.println(am.neighbors(1));

        AdjacencyList al = new AdjacencyList(4);
        al.addEdge(1,2);
        al.addEdge(1,3);
        System.out.println(al.neighbors(1));


        System.out.println(adjacencyMatrixToList(am.matrix));

        boolean[][] matrix = adjacencyListToMatrix(al.edges);
        for (boolean[] m : matrix) System.out.println(Arrays.toString(m));

        List<List<Integer>> adjList = new ArrayList<>();
        adjList.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{1,2})));
        adjList.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{0})));
        adjList.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{0})));
        adjList.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{4,5})));
        adjList.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{3,5})));
        adjList.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{3,4})));
        System.out.println(validPathList(6, adjList, 0, 1));
        System.out.println(validPathList(6, adjList, 0, 5));

        boolean[][] adjMatrix = new boolean[][]{
            {false, true, true, false, false, false},
            {true, false, false, false, false, false},
            {true, false, false, false, false, false},
            {false, false, false, false, true, true},
            {false, false, false, true, false, true},
            {false, false, false, true, true, false}
        };

        System.out.println(validPathMatrix(6, adjMatrix, 0, 1));
        System.out.println(validPathMatrix(6, adjMatrix, 0, 5));

        adjList.get(2).add(4);
        System.out.println(lengthOfShortestPath(adjList, 0, 5));
        System.out.println(shortestPath(adjList, 0, 5));
        System.out.println(allPaths(adjList, 0, 5));

        System.out.println(courseScheduling(4, new int[][]{{1,0},{2,0},{3,1},{3,2}}));

        System.out.println(Arrays.toString(courseSchedulingII(4, new int[][]{{1,0},{2,0},{3,1},{3,2}})));

        System.out.println(alienDictionary(new String[]{"wrt", "wrf", "er", "ett", "rftt"}));
        System.out.println(alienDictionary(new String[]{"ac", "ab", "zc", "zb"}));

        List<List<Integer>> rooms = new LinkedList<>();
        rooms.add(Arrays.asList(new Integer[]{1}));
        rooms.add(Arrays.asList(new Integer[]{2}));
        rooms.add(Arrays.asList(new Integer[]{3}));
        rooms.add(Arrays.asList(new Integer[]{}));
        System.out.println(keysAndRooms(rooms));

        List<List<String>> equations = new LinkedList<>();
        equations.add(Arrays.asList(new String[]{"a", "b"}));
        equations.add(Arrays.asList(new String[]{"b", "c"}));

        List<List<String>> queries = new LinkedList<>();
        queries.add(Arrays.asList(new String[]{"a", "c"}));
        queries.add(Arrays.asList(new String[]{"b", "a"}));
        queries.add(Arrays.asList(new String[]{"a", "e"}));
        queries.add(Arrays.asList(new String[]{"a", "a"}));
        queries.add(Arrays.asList(new String[]{"x", "x"}));

        System.out.println(Arrays.toString(evaluateDivision(equations, new double[]{2.0, 3.0}, queries)));

        System.out.println(slidingPuzzle(new int[][]{{1,2,3},{4,0,5}}));
        System.out.println(slidingPuzzle(new int[][]{{1,2,3},{5,4,0}}));
    }
}
