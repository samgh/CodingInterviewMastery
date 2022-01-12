/**
 *   Title: Graph Solutions
 *
 *   This file contains the solutions for the Graph exercises in the DS & Algos
 *   Primer. If you have not already attempted these exercises, we highly
 *   recommend you complete them before reviewing the solutions here.
 *
 *   Execution: node GraphSolutions.js
 */

/**
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

/**
 * Exercise 1.2: Implement two classes to represent a graph as an Adjacency
 * Matrix and an Adjacency List
 *
 * @param{number} n
 */
function AdjacencyMatrix(n) {

    // Build an empty matrix
    var matrix = [];
    var matrixHeight = n;
    while (matrixHeight--) {
        var matrixLength = n;
        var row = [];
        while (matrixLength--) row.push(false);
        matrix.push(row);
    }

    /**
     * Add an edge from node1 to node2
     *
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     * @param{number} node1
     * @param{number} node2
     */
    this.addEdge = function(node1, node2) {
        if (node1 < 0 || node1 >= matrix.length ||
            node2 < 0 || node2 > matrix.length) {
            throw "Node not in graph";
        }

        // Update the edge
        matrix[node1][node2] = true;
    }

    /**
     * Remove an edge from node1 to node2
     *
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     * @param{number} node1
     * @param{number} node2
     */
    this.removeEdge = function(node1, node2) {
        if (node1 < 0 || node1 >= matrix.length ||
            node2 < 0 || node2 > matrix.length) {
            throw "Node not in graph";
        }

        // Update the edge
        matrix[node1][node2] = false;
    }

    /**
     * Get all neighbors of node
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param{number} node
     * @return{number[]}
     */
    this.neighbors = function(node) {
        if (node < 0 || node >= matrix.length) {
            throw "Node not in graph";
        }

        // Iterate over the matrix and collect all the neighbors
        var result = [];
        matrix[node].forEach(function(val, i) {
            if (val) result.push(i);
        })

        return result;
    }
}

/**
 * Adjacency List
 *
 * @param{number} n
 */
function AdjacencyList(n) {

    // We'll store our edges here. Using sets will improve our efficiency
    // when updating edges for a specific node
    var edges = []
    var matrixHeight = n;
    while (matrixHeight--) edges.push(new Set());

    /**
     * Add an edge from node1 to node2
     *
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     * @param{number} node1
     * @param{number} node2
     */
    this.addEdge = function(node1, node2) {
        if (node1 < 0 || node1 >= edges.size ||
            node2 < 0 || node2 > edges.size) {
            throw "Node not in graph";
        }

        edges[node1].add(node2);
    }

    /**
     * Remove an edge from node1 to node2
     *
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     * @param{number} node1
     * @param{number} node2
     */
    this.removeEdge = function(node1, node2) {
        if (node1 < 0 || node1 >= edges.size ||
            node2 < 0 || node2 > edges.size) {
            throw "Node not in graph";
        }

        if (this.edges.get(node1).contains(node2)) {
            edges[node1].remove(node2);
        }
    }

    /**
     * Get all neighbors of node
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param{number} node
     * @return{number[]}
     */
     this.neighbors = function( node) {
         if (node < 0 || node >= edges.size) {
             throw "Node not in graph";
         }

         // Get the set of edges and add it to a list
         result = [];
         edges[node].forEach(function(i) {
             result.push(i);
         })

         return result;
     }
}

/**
 * Exercise 1.3: Implement a function to convert an adjacency matrix into an
 * adjacency list and vice versa.
 */

/**
 * Convert an adjacency matrix into an adjacency list
 *
 * Time Complexity: O(n^2)
 * Space Complexity: O(1)
 *
 * @param{boolean[][]} matrix
 * @return{Set[]}
 */
var adjacencyMatrixToList = function(matrix) {
    var list = [];

    // Iterate over the matrix and for every edge, add it to the list
    for (var i = 0; i < matrix.length; i++) {
        var edges = new Set();
        for (var j = 0; j < matrix[i].length; j++) {
            if (matrix[i][j]) edges.add(j);
        }
        list.push(edges);
    }

    return list;
}

/**
 * Convert an adjacency list into an adjacency matrix
 *
 * Time Complexity: O(n^2)
 * Space Complexity: O(1)
 *
 * @param{Set[]} list
 * @return{boolean[][]}
 */
var adjacencyListToMatrix = function(list) {
    var matrix = [];

    // Iterate over the list and add each edge to the matrix
    for (var i = 0; i < list.length; i++) {
        var edges = [];
        for (var j = 0; j < list.length; j++) {
            if (list[i].has(j)) edges.push(true);
            else edges.push(false);
        }
        matrix.push(edges);
    }

    return matrix;
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
 * Time Complexity: O(V+E)
 * Space Complexity: O(V)
 *
 * @param{Node} node
 * @return{Node}
 */
var cloneGraph = function(node) {
    if (!node) return null;

    // We need to do 2 things:
    //  1. Copy each node
    //  2. Copy all the edges
    // To copy the edges, we need to know what the mapping is of nodes in
    // the original graph to the nodes in the new graph. Therefore, we'll
    // traverse our graph and add everything to a map.
    //
    // This map stores oldNode -> newNode
    nodes = new Map();

    // Do DFS and add all the nodes to map as well as make a copy of each
    traverseAllNodes(node, nodes);

    // We have all of our nodes, now go through and add all the edges
    nodes.forEach(function(value, key) {
        // key is our old node and value is our new node. To get the edges
        // for newNode, we need to find the edges for the old node and
        // then look up what the corresponding node is in our new graph

        key.neighbors.forEach(function(neighbor) {
            value.neighbors.push(nodes.get(neighbor));
        });
    });

    return nodes.get(node);
}

/**
 * Traverse our graph using DFS and add nodes/copies of nodes to nodes
 *
 * @param{Node} node
 * @param{Map} nodes
 */
var traverseAllNodes = function(node, nodes) {
    // We've already visited this node
    if (nodes.has(node)) return;

    // Add the node and a copy of the node to our map
    nodes.set(node, new Node(node.val));

    // Traverse all the neighbors
    node.neighbors.forEach(function(n) {
        traverseAllNodes(n, nodes);
    });
}

/**
 * Exercise 2.1: Write a function to determine whether a path exists between
 * two nodes
 */

/**
 * Implement using an adjacency list
 *
 * Time Complexity: O(V + E)
 * Space Complexity: O(V)
 *
 * @param{number} n
 * @param{number[][]} edges
 * @param{number} start
 * @param{number} end
 * @return{boolean}
 */
var validPathList = function(n, edges, start, end) {
    // Do a DFS to see if the two nodes are connected
    return validPathListInner(n, edges, start, end, new Set());
}

/**
 * Inner recursive function
 *
 * @param{number} n
 * @param{number[][]} edges
 * @param{number} start
 * @param{number} end
 * @param{Set} visited
 * @return{boolean}
 */
var validPathListInner = function(n, edges, start, end, visited) {
    // If we've found the target node, there is a valid path
    if (start == end) return true;

    // If we've already visited this node, skip it
    if (visited.has(start)) return false;

    // Track that we've visited the current node
    visited.add(start);

    // Traverse every edge to the next node and see if any neighbor connects
    // to the target node
    for (var i = 0; i < edges[start].length; i++) {
        if (validPathListInner(n, edges, edges[start][i], end, visited)) return true;
    };

    // If we don't find a valid path, there is no valid path from this node
    return false;
}

/**
 * Implement using an adjacency matrix
 *
 * Time Complexity: O(V^2)
 * Space Complexity: O(V)
 *
 * @param{number} n
 * @param{boolean[][]} edges
 * @param{number} start
 * @param{number} end
 * @return{boolean}
 */
var validPathMatrix = function(n, edges, start, end) {
    // Do a DFS to see if the two nodes are connected
    return validPathMatrixInner(n, edges, start, end, new Set());
}

/**
 * Inner recursive function
 *
 * @param{number} n
 * @param{boolean[][]} edges
 * @param{number} start
 * @param{number} end
 * @param{Set} visited
 * @return{boolean}
 */
var validPathMatrixInner = function(n, edges, start, end, visited) {
    // If we've found the target node, there is a valid path
    if (start == end) return true;

    // If we've already visited this node, skip it
    if (visited.has(start)) return false;

    // Track that we've visited the current node
    visited.add(start);

    // Traverse every edge to the next node and see if any neighbor connects
    // to the target node
    for (var i = 0; i < edges[start].length; i++) {
        // We have to traverse all the possible edges to see which exist
        if (edges[start][i]) {
            if (validPathMatrixInner(n, edges, i, end, visited)) return true;
        }
    }

    // If we don't find a valid path, there is no valid path from this node
    return false;
}

/**
 * Exercise 2.2: Write a function to find the length of the shortest path
 * between two nodes (use BFS)
 *
 * Time Complexity: O(V + E)
 * Space Complexity: O(V)
 *
 * @param{number[][]} edges
 * @param{number} start
 * @param{number} end
 * @return{number}
 */
var lengthOfShortestPath = function(edges, start, end) {
    var toVisit = [];
    nodeDepth = {};

    toVisit.push(start);
    nodeDepth[start] = 1;

    while (toVisit.length != 0) {
        var curr = toVisit.shift();
        if (curr == end) return nodeDepth[curr];

        edges[curr].forEach(function(e) {
            if (!(e in nodeDepth)) {
                toVisit.push(e);
                nodeDepth[e] = nodeDepth[curr] + 1;
            }
        });
    }

    return -1;
}

/**
 * Exercise 2.3: Write a function to find the shortest path between two
 * nodes (use BFS)
 *
 * Time Complexity: O(V + E)
 * Space Complexity: O(V)
 *
 * @param{number[][]} edges
 * @param{number} start
 * @param{number} end
 * @return{number[]}
 */
var shortestPath = function(edges, start, end) {
    // For BFS, we use a queue to store the next nodes to visit
    var toVisit = [];

    // To reconstruct the path, we need to track which node let us to which
    // other node. When we go from NodeA to NodeB, we'll add NodeB -> NodeA
    // to our map. Then once we've found our target node we'll be able to
    // traverse backwards to reconstruct the path
    var previousNodeInPath = {};

    // Add our starting node
    toVisit.push(start);
    previousNodeInPath[start] = null;

    // Traverse until we visit all the nodes or we find our target node
    while(toVisit.length != 0) {
        // If the current node is our target, we can stop searching
        var curr = toVisit.shift();
        if (curr == end) break;

        // Add all the neighbor nodes to our queue to search
        edges[curr].forEach(function(e) {
            // Rather than separately track which nodes we've visited, just
            // check whether it is in previousNodeInPath
            if (!(e in previousNodeInPath)) {
                toVisit.push(e);
                previousNodeInPath[e] = curr;
            }
        });
    }

    // If we haven't visited the target node, then it's because there is no
    // valid path
    if (!(end in previousNodeInPath)) return null;

    // Reconstruct our path using the map. Start at the end of our path and
    // repeatedly look up the node that came before it until we get to the
    // beginning
    var result = [];
    var curr = end;
    while (curr != null) {
        result.unshift(curr);
        curr = previousNodeInPath[curr];
    }

    return result;
}

/**
 * Exercise 2.4: Write a function to find ALL the paths between two nodes
 *
 * Time Complexity: O(V!)
 * Space Complexity: O(V)
 *
 * @param{number[]} edges
 * @param{number} start
 * @param{number} end
 * @return{number[][]}
 */
var allPaths = function(edges, start, end) {
    // We'll use DFS and store every valid path to our list
    var result = [];

    // All paths start with start
    var path = [start];
    var visited = new Set();
    visited.add(start);

    // Recursive call
    allPathsInner(edges, start, end, visited, result, path);

    return result;
}

/**
 * Inner function
 *
 * @param{number[]} edges
 * @param{number} start
 * @param{number} end
 * @param{Set} visited
 * @param{number[][]} result
 * @param{number[]} currPath
 */
var allPathsInner = function(edges, start, end, visited, result, currPath) {
    // If we've found a valid path, add it to the results
    if (start == end) {
        // Make sure to make a deep copy of the list
        result.push([...currPath]);
        return;
    }

    // Try traversing every edge
    edges[start].forEach(function(e) {
        // console.log(e);
        // Check that we haven't visited the node
        if (!(visited.has(e))) {
            // Update the path and visited, then make recursive call
            currPath.push(e);
            visited.add(e);
            allPathsInner(edges, e, end, visited, result, currPath);

            // Make sure to backtrack
            currPath.pop();
            visited.delete(e);
        }
    });

    return;
}

/**
 * Exercise 3.1: Course Scheduling
 *
 * Time Complexity: O(V^2)
 * Space Complexity: O(V + E)
 *
 * @param{number} numCourses
 * @param{number[][]} prerequisites
 * @return{boolean}
 */
var courseScheduling = function(numCourses, prerequisites) {
    // We'll convert our prerequisites into an adjacency list
    var adjList = [];
    for (var i = 0; i < numCourses; i++) adjList.push(new Set());

    // Add all the edges to our adjacency list
    prerequisites.forEach(function(pre) {
        adjList[pre[0]].add(pre[1]);
    });

    // Use a queue (or other list, order doesn't matter) to keep track of
    // which courses don't have any prerequisites
    var noDependencies = [];
    for (var i = 0; i < adjList.length; i++) {
        if (adjList[i].size == 0) noDependencies.push(i);
    }

    // If we track the courses we've taken, it's easy to check at the end
    // whether we found a valid solution
    var count = 0;

    // Repeatedly remove dependencies until we've gone through all courses
    // or there are no courses that don't have dependencies
    while (noDependencies.length != 0) {
        var curr = noDependencies.pop();
        count++;

        // Remove the course as a dependency from all the other courses
        for (var i = 0; i < numCourses; i++) {
            var set = adjList[i];
            if (set.has(curr)) {
                set.delete(curr);

                // If it has no more dependencies, we can take this course
                if (set.size == 0) noDependencies.push(i);
            }
        }
    }

    return count == numCourses;
}

/**
 * Exercise 3.2: Course Scheduling II
 *
 * Time Complexity: O(V^2)
 * Space Complexity: O(V + E)
 *
 * @param{number} numCourses
 * @param{number[][]} prerequisites
 * @return{number[]}
 */
var courseSchedulingII = function(numCourses, prerequisites) {
    // We'll convert our prerequisites into an adjacency list
    var adjList = [];
    for (var i = 0; i < numCourses; i++) adjList.push(new Set());

    // Add all the edges to our adjacency list
    prerequisites.forEach(function(pre) {
        adjList[pre[0]].add(pre[1]);
    });

    // Use a queue (or other list, order doesn't matter) to keep track of
    // which courses don't have any prerequisites
    var noDependencies = [];
    for (var i = 0; i < adjList.length; i++) {
        if (adjList[i].size == 0) noDependencies.push(i);
    }

    // We'll add courses to our result array as we take them
    var result = [];

    // Repeatedly remove dependencies until we've gone through all courses
    // or there are no courses that don't have dependencies
    while (noDependencies.length != 0) {
        var curr = noDependencies.pop();
        result.push(curr);

        // Remove the course as a dependency from all the other courses
        for (var i = 0; i < numCourses; i++) {
            var set = adjList[i];
            if (set.has(curr)) {
                set.delete(curr);

                // If it has no more dependencies, we can take this course
                if (set.size == 0) noDependencies.push(i);
            }
        }
    }

    if (result.length == numCourses) return result;
    return [];
}

/**
 * Exercise 3.3: Alien Dictionary
 *
 * Time Complexity: O(max(sum(length of all strings), # unique characters ^ 2))
 * Space Complexity: O(# unique characters)
 *
 * @param{string[]} words
 * @return{string}
 */
var alienDictionary = function(words) {
    // Per Leetcode testcases, ["abcd", "abc"] would be invalid. We
    // specifically check for this edge case here
    for (var i = 0; i < words.length-1; i++) {
        if (words[i].length <= words[i+1].length) continue;
        if (prefixEqual(words[i], words[i+1], words[i+1].length)) return "";
    }

    // Generate a graph of which chars come before which other chars
    var graph = generateAlienGraph(words);

    // Once we have our graph, this is a standard topological sort
    var noDependencies = [];
    Object.entries(graph).forEach(function([key, value]) {
        // Start with all the characters that have no dependencies
        if (value.size == 0) noDependencies.push(key);
    });

    result = [];

    // For each character, add it to the result and remove it as a dependency
    // from all of the other characters
    while (noDependencies.length != 0) {
        var curr = noDependencies.pop();
        result.push(curr);

        Object.entries(graph).forEach(function([key, value]) {
            if (value.has(curr)) {
                value.delete(curr);
                if (value.size == 0) noDependencies.push(key);
            }
        });
    }

    // Check that our result is valid
    if (result.length != Object.keys(graph).length) return "";
    return result.join("");
}

/**
 * Helper function to convert our strings into a graph
 *
 * @param{string[]} words
 * @return{Object}
 */
var generateAlienGraph = function(words) {
    // Since our nodes are character values and not just integers, we'll
    // use a map rather than a list
    var adjList = {};

    // Add all the nodes to our map
    words.forEach(function(word) {
        [...word].forEach(function(c) {
            if (!(c in adjList)) adjList[c] = new Set();
        });
    });

    // We compare each sequential pair of strings. We essentially just want
    // to find the first different character because that tells us which
    // character of the two comes first in lexographical order
    for (var i = 0; i < words.length - 1; i++) {
        // Find the first character that is different
        for (var j = 0; j < Math.min(words[i].length, words[i+1].length); j++) {
            if (words[i][j] == words[i+1][j]) continue;

            // When we find a character that is different, add the dependency
            // to our adjacency list
            adjList[words[i+1][j]].add(words[i][j]);
            break;
        }
    }

    return adjList;
}

/**
 * Helper function to test if two strings have the same prefix of length len
 *
 * @param{string} a
 * @param{string} b
 * @param{number} len
 * @return{boolean}
 */
var prefixEqual = function(a, b, len) {
    for (var i = 0; i < len; i++) {
        if (a[i] != b[i]) return false;
    }
    return true;
}

/**
 * Exercise 4.1: Keys and Rooms
 *
 * Time Complexity: O(# rooms + # keys)
 * Space Complexity: O(# rooms)
 *
 * @param{number[][]} rooms
 * @return{boolean}
 */
var keysAndRooms = function(rooms) {
    // We do a graph search where the rooms are the nodes and the edges are
    // the keys in each room. BFS is a simpler implementation but we could
    // do either BFS or DFS
    var visited = new Set();
    var toVisit = [];
    toVisit.push(0);

    // Do the search
    while (toVisit.length != 0) {
        var curr = toVisit.shift();
        visited.add(curr);

        // Add keys in the current room to the next possible rooms
        rooms[curr].forEach(function(room) {
            if (!visited.has(room)) toVisit.push(room);
        })
    }

    // Return true if we successfully visited all the rooms
    return visited.size == rooms.length;
}

/**
 * Exercise 4.2: Evaluate Division
 *
 * Time Complexity: O(# queries * # variables * # equations)
 * Space Complexity: O(# variables)
 *
 * @param{string[][]} equations
 * @param{number[]} values
 * @param{string[][]} queries
 */
var evaluateDivision = function(equations, values, queries) {
    // For each query, do DFS to determine series of operations required
    // to compute the value
    var result = [];
    for (var i = 0; i < queries.length; i++) {
        result.push(evaluateDivisionInner(equations, values, queries[i][0], queries[i][1], new Set()));
    }
    return result;
}

/**
 * Do DFS
 *
 * @param{string[][]} equations
 * @param{number[]} values
 * @param{string} curr
 * @param{string} dest
 * @param{Set} visited
 * @return{number}
 */
var evaluateDivisionInner = function(equations, values, curr, dest, visited) {
    // Check if we've already computed a variable
    if (visited.has(curr)) return -1.0;

    // The result of something divided by itself is 1
    if (curr == dest) return 1.0;

    // Track that we've seen a variable
    visited.add(curr);

    // Iterate over each equation to see which variables we can compute
    // given our current variable. Then repeat until we find the target
    // variable
    for (var i = 0; i < equations.length; i++) {

        // If the first variable in the equation is our current variable,
        // then we multiply by the value. If it's the second variable then
        // we multiply by 1/the value
        var equation = equations[i];
        if (equation[0] == curr) {
            var result = evaluateDivisionInner(equations, values, equation[1], dest, visited);
            if (result >= 0) return result * values[i];
        } else if (equation[1] == curr) {
            var result = evaluateDivisionInner(equations, values, equation[0], dest, visited);
            if (result >= 0) return result / values[i];
        }
    }

    // Backtrack
    visited.delete(curr);

    return -1.0;
}

/**
 * Exercise 4.3: Sliding Puzzle
 *
 * Time Complexity: O(1)
 * Space Complexity: O(1)
 * Given the fixed board size, the time doesn't change based on the input
 *
 * @param{number[][]} board
 * @return{number}
 */
var slidingPuzzle = function(board) {
    // Since we have a fixed size board, we can explicitly express the
    // valid moves for each possible position of the empty space as well as
    // the win condition
    var moves = [[1,3],[0,2,4],[1,5],[0,4],[1,3,5],[2,4]];
    var solved = "123450";

    // We will represent the board as a string because it makes it way
    // easier for us to compare and add to a map
    var linearBoard = "";

    for (var i = 0; i < board.length; i++) {
        for (var j = 0; j < board[0].length; j++) {
            // We just add all the values from the board to our string
            linearBoard += board[i][j];
        }
    }

    // Now we do BFS to find the shortest path. We need to track the depth
    // of each board state so that we can get our result
    var toVisit = [];
    var depth = {};

    toVisit.push(linearBoard);
    depth[linearBoard] = 0;

    while (toVisit.length != 0) {
        var curr = toVisit.shift();
        if (curr == solved) break;

        // The index of the empty spot tells us what valid moves we can make
        var idx = curr.indexOf('0');

        // Go through each possible move and add it to our queue
        moves[idx].forEach(function(move) {
            var swapped = swap(curr, idx, move);
            if (!(swapped in depth)) {
                toVisit.push(swapped);
                depth[swapped] = depth[curr]+1;
            }
        });
    }

    // Either we found a valid path or we didn't
    if (!(solved in depth)) return -1;
    return depth[solved];
}

/**
 * Simple helper function to swap two characters in a string
 *
 * @param{string} s
 * @param{number} i
 * @param{number} j
 * @return{string}
 */
var swap = function(s, i, j) {
    result = [...s];

    var temp = result[j];
    result[j] = result[i];
    result[i] = temp;
    return result.join("");
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
