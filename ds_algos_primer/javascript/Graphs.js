/**
 *   Title: Graph
 *
 *   This file contains the template for the Graph exercises in
 *   the DS & Algos Primer. Fill in the exercises here and refer to
 *   GraphSolutions.java for the complete code samples.
 *
 *   Execution: node Graphs.js
 */

/**
 * Exercise 1.1: Given the following graph (see workbook), write out by hand
 * the representation of the graph as an Adjacency Matrix and an Adjacency
 * List.
 *
 * Adjacency Matrix:
 *
 *
 * Adjacecny List:
 */

/**
 * Exercise 1.2: Implement two classes to represent a graph as an Adjacency
 * Matrix and an Adjacency List
 *
 * @param{number} n
 */
function AdjacencyMatrix(n) {

    // INSERT YOUR CODE HERE

    /**
     * Add an edge from node1 to node2
     *
     * Time Complexity:
     * Space Complexity:
     *
     * @param{number} node1
     * @param{number} node2
     */
    this.addEdge = function(node1, node2) {
        // INSERT YOUR CODE HERE
    }

    /**
     * Remove an edge from node1 to node2
     *
     * Time Complexity:
     * Space Complexity:
     *
     * @param{number} node1
     * @param{number} node2
     */
    this.removeEdge = function(node1, node2) {
        // INSERT YOUR CODE HERE
    }

    /**
     * Get all neighbors of node
     *
     * Time Complexity:
     * Space Complexity:
     *
     * @param{number} node
     * @return{number[]}
     */
    this.neighbors = function(node) {
        // INSERT YOUR CODE HERE
    }
}

/**
 * Adjacency List
 *
 * @param{number} n
 */
function AdjacencyList(n) {

    // INSERT YOUR CODE HERE

    /**
     * Add an edge from node1 to node2
     *
     * Time Complexity:
     * Space Complexity:
     *
     * @param{number} node1
     * @param{number} node2
     */
    this.addEdge = function(node1, node2) {
        // INSERT YOUR CODE HERE
    }

    /**
     * Remove an edge from node1 to node2
     *
     * Time Complexity:
     * Space Complexity:
     *
     * @param{number} node1
     * @param{number} node2
     */
    this.removeEdge = function(node1, node2) {
        // INSERT YOUR CODE HERE
    }

    /**
     * Get all neighbors of node
     *
     * Time Complexity:
     * Space Complexity:
     *
     * @param{number} node
     * @return{number[]}
     */
     this.neighbors = function( node) {
         // INSERT YOUR CODE HERE
     }
}

/**
 * Exercise 1.3: Implement a function to convert an adjacency matrix into an
 * adjacency list and vice versa.
 */

/**
 * Convert an adjacency matrix into an adjacency list
 *
 * Time Complexity:
 * Space Complexity:
 *
 * @param{boolean[][]} matrix
 * @return{Set[]}
 */
var adjacencyMatrixToList = function(matrix) {
    // INSERT YOUR CODE HERE
}

/**
 * Convert an adjacency list into an adjacency matrix
 *
 * Time Complexity:
 * Space Complexity:
 *
 * @param{Set[]} list
 * @return{boolean[][]}
 */
var adjacencyListToMatrix = function(list) {
    // INSERT YOUR CODE HERE
}

/**
 * Exercise 1.4: Write a function to clone a graph
 */

// Node class from Leetcode: https://leetcode.com/problems/clone-graph/
function Node(val, neighbors) {
    this.val = val === undefined ? 0 : val;
    this.neighbors = neighbors === undefined ? [] : neighbors;
};

/**
 * Make a deep copy of the graph
 *
 * Time Complexity:
 * Space Complexity:
 *
 * @param{Node} node
 * @return{Node}
 */
var cloneGraph = function(node) {
    // INSERT YOUR CODE HERE
}

/**
 * Exercise 2.1: Write a function to determine whether a path exists between
 * two nodes
 */

/**
 * Implement using an adjacency list
 *
 * Time Complexity:
 * Space Complexity:
 *
 * @param{number} n
 * @param{number[][]} edges
 * @param{number} start
 * @param{number} end
 * @return{boolean}
 */
var validPathList = function(n, edges, start, end) {
    // INSERT YOUR CODE HERE
}

/**
 * Implement using an adjacency matrix
 *
 * Time Complexity:
 * Space Complexity:
 *
 * @param{number} n
 * @param{boolean[][]} edges
 * @param{number} start
 * @param{number} end
 * @return{boolean}
 */
var validPathMatrix = function(n, edges, start, end) {
    // INSERT YOUR CODE HERE
}

/**
 * Exercise 2.2: Write a function to find the length of the shortest path
 * between two nodes (use BFS)
 *
 * Time Complexity:
 * Space Complexity:
 *
 * @param{number[][]} edges
 * @param{number} start
 * @param{number} end
 * @return{number}
 */
var lengthOfShortestPath = function(edges, start, end) {
    // INSERT YOUR CODE HERE
}

/**
 * Exercise 2.3: Write a function to find the shortest path between two
 * nodes (use BFS)
 *
 * Time Complexity:
 * Space Complexity:
 *
 * @param{number[][]} edges
 * @param{number} start
 * @param{number} end
 * @return{number[]}
 */
var shortestPath = function(edges, start, end) {
    // INSERT YOUR CODE HERE
}

/**
 * Exercise 2.4: Write a function to find ALL the paths between two nodes
 *
 * Time Complexity:
 * Space Complexity:
 *
 * @param{number[]} edges
 * @param{number} start
 * @param{number} end
 * @return{number[][]}
 */
var allPaths = function(edges, start, end) {
    // INSERT YOUR CODE HERE
}

/**
 * Exercise 3.1: Course Scheduling
 *
 * Time Complexity:
 * Space Complexity:
 *
 * @param{number} numCourses
 * @param{number[][]} prerequisites
 * @return{boolean}
 */
var courseScheduling = function(numCourses, prerequisites) {
    // INSERT YOUR CODE HERE
}

/**
 * Exercise 3.2: Course Scheduling II
 *
 * Time Complexity:
 * Space Complexity:
 *
 * @param{number} numCourses
 * @param{number[][]} prerequisites
 * @return{number[]}
 */
var courseSchedulingII = function(numCourses, prerequisites) {
    // INSERT YOUR CODE HERE
}

/**
 * Exercise 3.3: Alien Dictionary
 *
 * Time Complexity:
 * Space Complexity:
 *
 * @param{string[]} words
 * @return{string}
 */
var alienDictionary = function(words) {
    // INSERT YOUR CODE HERE
}

/**
 * Exercise 4.1: Keys and Rooms
 *
 * Time Complexity:
 * Space Complexity:
 *
 * @param{number[][]} rooms
 * @return{boolean}
 */
var keysAndRooms = function(rooms) {
    // INSERT YOUR CODE HERE
}

/**
 * Exercise 4.2: Evaluate Division
 *
 * Time Complexity:
 * Space Complexity:
 *
 * @param{string[][]} equations
 * @param{number[]} values
 * @param{string[][]} queries
 */
var evaluateDivision = function(equations, values, queries) {
    // INSERT YOUR CODE HERE
}

/**
 * Exercise 4.3: Sliding Puzzle
 *
 * Time Complexity:
 * Space Complexity:
 *
 * @param{number[][]} board
 * @return{number}
 */
var slidingPuzzle = function(board) {
    // INSERT YOUR CODE HERE
}



/**
 * Sample test cases
 */
var tester = function() {

    var am = new AdjacencyMatrix(4);
    am.addEdge(1,2);
    am.addEdge(1,3);
    console.log(am.neighbors(1));

    var al = new AdjacencyList(4);
    al.addEdge(1,2);
    al.addEdge(1,3);
    console.log(al.neighbors(1));

    matrix = [[false, false, false, true],
              [true, false, true, true],
              [true, false, false, true],
              [false, true, false, false]];

    console.log(adjacencyMatrixToList(matrix));

    console.log(adjacencyListToMatrix(adjacencyMatrixToList(matrix)));

    var node = new Node(1);
    node.neighbors.push(new Node(2));
    node.neighbors.push(new Node(3));
    console.log(cloneGraph(node));

    var adjList = [[1,2],[0],[0],[4,5],[3,5],[3,4]];
    console.log(validPathList(6, adjList, 0, 1));
    console.log(validPathList(6, adjList, 0, 5));


    var adjMatrix = [
        [false, true, true, false, false, false],
        [true, false, false, false, false, false],
        [true, false, false, false, false, false],
        [false, false, false, false, true, true],
        [false, false, false, true, false, true],
        [false, false, false, true, true, false]];

    console.log(validPathMatrix(6, adjMatrix, 0, 1));
    console.log(validPathMatrix(6, adjMatrix, 0, 5));

    adjList[2].push(4);
    console.log(lengthOfShortestPath(adjList, 0, 5));
    console.log(shortestPath(adjList, 0, 5));
    console.log(allPaths(adjList, 0, 5));

    console.log(courseScheduling(4, [[1,0],[2,0],[3,1],[3,2]]));

    console.log(courseSchedulingII(4, [[1,0],[2,0],[3,1],[3,2]]));

    console.log(alienDictionary(["wrt", "wrf", "er", "ett", "rftt"]));
    console.log(alienDictionary(["ac", "ab", "zc", "zb"]));

    console.log(keysAndRooms([[1],[2],[3],[]]));
    console.log(evaluateDivision([["a","b"],["b","c"]], [2.0, 3.0], [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]));

    console.log(slidingPuzzle([[1,2,3],[4,0,5]]));
    console.log(slidingPuzzle([[1,2,3],[5,4,0]]));
}

tester();
