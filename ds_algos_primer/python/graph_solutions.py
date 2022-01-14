"""
Title: Graph Solutions

This file contains the solutions for Exercise Sets of the Graph exercises in the
DS & Algos Primer. If you have not already attempted these exercises, we highly
recommend you complete them before reviewing the solutions here.

Execution: python graph_solutions.py
"""

from copy import deepcopy
import queue
from typing import List

"""
Exercise 1.1: Given the following graph (see workbook), write out by hand the
representation of the graph as an Adjacency Matrix and an Adjacency List.

Adjacency Matrix:
   0  1  2  3  4
0 [0, 1, 1, 0, 0]
1 [0, 0, 1, 0, 1]
2 [1, 0, 0, 1, 0]
3 [0, 0, 1, 0, 0]
4 [0, 0, 0, 0, 0]


Adjacecny List:
0: [1, 2]
1: [2, 4]
2: [0, 3]
3: [2]
4: []
"""

"""
Exercise 1.2: Implement two classes to represent a graph as an Adjacency Matrix
and an Adjacency List
"""
class AdjacencyMatrix:

    """
    Constructor
    """
    def __init__(self, n: int):
        self.matrix = [[0 for i in range(n)] for j in range(n)]

    """
    Add an edge from node1 to node2

    Time Complexity: O(1)
    Space Complexity: O(1)
    """
    def add_edge(self, node1: int, node2: int):
        if node1 < 0 or node1 >= len(self.matrix) or node2 < 0 or node2 >= len(self.matrix):
            raise IndexError()

        # Update the edge
        self.matrix[node1][node2] = True

    """
    Remove an edge from node1 to node2

    Time Complexity: O(1)
    Space Complexity: O(1)
    """
    def remove_edge(self, node1: int, node2: int):
        if node1 < 0 or node1 >= len(self.matrix) or node2 < 0 or node2 >= len(self.matrix):
            raise IndexError()

        # Update the edge
        self.matrix[node1][node2] = False

    """
    Get all neighbors of node

    Time Complexity: O(n)
    Space Complexity: O(1)
    """
    def neighbors(self, node: int) -> List[int]:
        if node < 0 or node >= len(self.matrix):
            raise IndexError()

        # Iterate over the matrix and collect all the neighbors
        result = []
        for i in range(len(self.matrix)):
            if (self.matrix[node][i]):
                result.append(i)

        return result

class AdjacencyList:

    """
    Constructor
    """
    def __init__(self, n: int):
        self.edges = [set() for _ in range(n)]

    """
    Add an edge from node1 to node2

    Time Complexity: O(1)
    Space Complexity: O(1)
    """
    def add_edge(self, node1: int, node2: int):
        if node1 < 0 or node1 >= len(self.edges) or node2 < 0 or node2 >= len(self.edges):
            raise IndexError()

        # Update the edge
        self.edges[node1].add(node2)

    """
    Remove an edge from node1 to node2

    Time Complexity: O(1)
    Space Complexity: O(1)
    """
    def remove_edge(self, node1: int, node2: int):
        if node1 < 0 or node1 >= len(self.edges) or node2 < 0 or node2 >= len(self.edges):
            raise IndexError()

        # Update the edge
        self.edges[node1].remove(node2)

    """
    Get all neighbors of node

    Time Complexity: O(n)
    Space Complexity: O(1)
    """
    def neighbors(self, node: int) -> List[int]:
        if node < 0 or node >= len(self.edges):
            raise IndexError()

        return list(self.edges[node])

"""
Exercise 1.3: Implement a function to convert an adjacency matrix into an
adjacency list and vice versa.
"""

"""
Convert an adjacency matrix into an adjacency list

Time Complexity: O(n^2)
Space Complexity: O(1)
"""
def adjacency_matrix_to_list(matrix: List[List[bool]]) -> List[set]:
    list = []

    # Iterate over the matrix and for every edge, add it to the list
    for i in range(len(matrix)):
        edges = set()
        for j in range(len(matrix[0])):
            if matrix[i][j]:
                edges.add(j)

        list.append(edges)

    return list

"""
Convert an adjacency list into an adjacency matrix

Time Complexity: O(n^2)
Space Complexity: O(1)
"""
def adjacency_list_to_matrix(list: List[set]) -> List[List[bool]]:
    matrix = []

    # Iterate over the list and add each edge to the matrix
    for i in range(len(list)):
        edges = []
        for j in range(len(list)):
            if j in list[i]:
                edges.append(True)
            else:
                edges.append(False)

        matrix.append(edges)

    return matrix

"""
Exercise 1.4: Write a function to clone a graph
"""

# Node class from Leetcode: https://leetcode.com/problems/clone-graph/
class Node:
    def __init__(self, val = 0, neighbors = None):
        self.val = val
        self.neighbors = neighbors if neighbors is not None else []

"""
Make a deep copy of the graph

Time Complexity: O(V+E)
Space Complexity: O(V)
"""
def clone_graph(node: Node) -> Node:
    if not node:
        return None

    # We need to do 2 things:
    #  1. Copy each node
    #  2. Copy all the edges
    # To copy the edges, we need to know what the mapping is of nodes in
    # the original graph to the nodes in the new graph. Therefore, we'll
    # traverse our graph and add everything to a map.
    #
    # This dict stores oldNode -> newNode
    nodes = {}

    # Do DFS and add all the nodes to map as well as make a copy of each
    _traverse_all_nodes(node, nodes)

    # We have all of our nodes, now go through and add all the edges
    for oldNode, newNode in nodes.items():
        # key is our old node and value is our new node. To get the edges
        # for newNode, we need to find the edges for the old node and
        # then look up what the corresponding node is in our new graph
        for neighbor in oldNode.neighbors:
            newNode.neighbors.append(nodes[neighbor])

    return nodes[node]

"""
Traverse our graph using DFS and add nodes/copies of nodes to nodes
"""
def _traverse_all_nodes(node: Node, nodes: dict):
    # We've already visited this node
    if node in nodes:
        return

    # Add the node and a copy of the node to our map
    nodes[node] = Node(node.val)

    # Traverse all the neighbors
    for neighbor in node.neighbors:
        _traverse_all_nodes(neighbor, nodes)

"""
Exercise 2.1: Write a function to determine whether a path exists between
two nodes
"""

"""
Implement using an adjacency list

Time Complexity: O(V + E)
Space Complexity: O(V)
"""
def valid_path_list(n: int, edges: List[List[int]], start: int, end: int, visited: set = set()) -> bool:
    # If we've found the target node, there is a valid path
    if start == end:
        return True

    # If we've already visited this node, skip it
    if start in visited:
        return False

    # Track that we've visited the current node
    visited.add(start)

    # Traverse every edge to the next node and see if any neighbor connects
    # to the target node
    for edge in edges[start]:
        if (valid_path_list(n, edges, edge, end, visited)):
            return True

    # If we don't find a valid path, there is no valid path from this node
    return False

"""
Implement using an adjacency matrix

Time Complexity: O(V^2)
Space Complexity: O(V)
"""
def valid_path_matrix(n: int, edges: List[List[bool]], start: int, end: int, visited: set = set()) -> bool:
    # If we've found the target node, there is a valid path
    if start == end:
        return True

    # If we've already visited this node, skip it
    if start in visited:
        return False

    # Track that we've visited the current node
    visited.add(start)

    # Traverse every edge to the next node and see if any neighbor connects
    # to the target node
    for i in range(len(edges[start])):
        # We have to traverse all the possible edges to see which exist
        if (edges[start][i]):
            if (valid_path_list(n, edges, i, end, visited)):
                return True

    # If we don't find a valid path, there is no valid path from this node
    return False

"""
Exercise 2.2: Write a function to find the length of the shortest path between
two nodes (use BFS)

Time Complexity: O(V + E)
Space Complexity: O(V)
"""
def length_of_shortest_path(edges: List[List[int]], start: int, end: int) -> int:
    # For BFS, we use a queue
    to_visit = queue.Queue()

    # We need to track the level of each node in our BFS so when we find
    # our target, we know what the length of the path is
    node_depth = {}

    # Initialize both with our start node
    to_visit.put(start)
    node_depth[start] = 1

    # Do our BFS
    while not to_visit.empty():
        curr = to_visit.get()

        # If we found the destination, return depth
        if curr == end:
            return node_depth[curr]

        # Otherwise add all the neighbors to the queue to visit
        for edge in edges[curr]:
            # Make sure we haven't already visited the node
            if not edge in node_depth:
                to_visit.put(edge)

                # The distance to any node is the distance to the previous
                # node + 1
                node_depth[edge] = node_depth[curr] + 1

    return -1

"""
Exercise 2.3: Write a function to find the shortest path between two nodes
(use BFS)

Time Complexity: O(V + E)
Space Complexity: O(V)
"""
def shortest_path(edges: List[List[int]], start: int, end: int):
    # For BFS, we use a queue to store the next nodes to visit
    to_visit = queue.Queue()

    # To reconstruct the path, we need to track which node let us to which
    # other node. When we go from NodeA to NodeB, we'll add NodeB -> NodeA
    # to our map. Then once we've found our target node we'll be able to
    # traverse backwards to reconstruct the path
    previous_node_in_path = {}

    # Add our starting node
    to_visit.put(start)
    previous_node_in_path[start] = None

    # Traverse until we visit all the nodes or we find our target node
    while not to_visit.empty():
        # If the current node is our target, we can stop searching
        curr = to_visit.get()
        if curr == end:
            break

        # Add all the neighbor nodes to our queue to search
        for edge in edges[curr]:
            # Rather than separately track which nodes we've visited, just
            # check whether it is in previousNodeInPath
            if not edge in previous_node_in_path:
                to_visit.put(edge)
                previous_node_in_path[edge] = curr

    # If we haven't visited the target node, then it's because there is no
    # valid path
    if not end in previous_node_in_path:
        return None

    # Reconstruct our path using the map. Start at the end of our path and
    # repeatedly look up the node that came before it until we get to the
    # beginning
    result = []
    curr = end

    # Be careful here. If we just do `while not curr`, this will behave
    # incorrectly when curr == 0
    while not curr == None:
        result.append(curr)
        curr = previous_node_in_path[curr]

    # We constructed this in reverse order
    result.reverse()
    return result

"""
Exercise 2.4: Write a function to find ALL the paths between two nodes

Time Complexity: O(V!)
Space Complexity: O(V)
"""
def all_paths(edges: List[int], start: int, end: int) -> List[List[int]]:
    # We'll use DFS and store every valid path to our list
    result = []

    # All paths start with start
    path = [start]
    visited = {start}

    # Recursive call
    _all_paths_inner(edges, start, end, visited, result, path)

    return result

"""
Inner function
"""
def _all_paths_inner(edges: List[int], start: int, end: int, visited: set, result: List[List[int]], curr_path: List[int]):
    # If we've found a valid path, add it to the results
    if start == end:
        # Make sure to make a deep copy of the list
        result.append(deepcopy(curr_path))
        return

    # Try traversing every edge
    for edge in edges[start]:
        # Check that we haven't visited the node
        if not edge in visited:
            # Update the path and visited, then make recursive call
            curr_path.append(edge)
            visited.add(edge)
            _all_paths_inner(edges, edge, end, visited, result, curr_path)

            # Make sure to backtrack
            curr_path.pop()
            visited.remove(edge)

"""
Exercise 3.1: Course Scheduling

Time Complexity: O(V^2)
Space Complexity: O(V + E)
"""
def course_scheduling(num_courses: int, prerequisites: List[List[int]]) -> bool:
    # We'll convert our prerequisites into an adjacency list
    adj_list = [set() for _ in range(num_courses)]

    # Add all the edges to our adjacency list
    for pre in prerequisites:
        adj_list[pre[0]].add(pre[1])

    # Use a queue (or other list, order doesn't matter) to keep track of
    # which courses don't have any prerequisites
    no_dependencies = []
    for i in range(len(adj_list)):
        if len(adj_list[i]) == 0:
            no_dependencies.append(i)

    # If we track the courses we've taken, it's easy to check at the end
    # whether we found a valid solution
    count = 0

    # Repeatedly remove dependencies until we've gone through all courses
    # or there are no courses that don't have dependencies
    while len(no_dependencies) > 0:
        curr = no_dependencies.pop()
        count = count + 1

        # Remove the course as a dependency from all the other courses
        for i in range(num_courses):
            s = adj_list[i]
            if curr in s:
                s.remove(curr)

                # If it has no more dependencies, we can take this course
                if len(s) == 0:
                    no_dependencies.append(i)

    return count == num_courses

"""
Exercise 3.2: Course Scheduling II

Time Complexity: O(V^2)
Space Complexity: O(V + E)
"""
def course_scheduling_ii(num_courses: int, prerequisites: List[List[int]]) -> List[int]:
    # We'll convert our prerequisites into an adjacency list
    adj_list = [set() for _ in range(num_courses)]

    # Add all the edges to our adjacency list
    for pre in prerequisites:
        adj_list[pre[0]].add(pre[1])

    # Use a queue (or other list, order doesn't matter) to keep track of
    # which courses don't have any prerequisites
    no_dependencies = []
    for i in range(len(adj_list)):
        if len(adj_list[i]) == 0:
            no_dependencies.append(i)

    # We'll add courses to our result array as we take them
    result = []


    # Repeatedly remove dependencies until we've gone through all courses
    # or there are no courses that don't have dependencies
    while len(no_dependencies) > 0:
        curr = no_dependencies.pop()
        result.append(curr)

        # Remove the course as a dependency from all the other courses
        for i in range(num_courses):
            s = adj_list[i]
            if curr in s:
                s.remove(curr)

                # If it has no more dependencies, we can take this course
                if len(s) == 0:
                    no_dependencies.append(i)

    if len(result) == num_courses:
        return result

    return []

"""
Exercise 3.3: Alien Dictionary

Time Complexity: O(max(sum(length of all strings), # unique characters ^ 2))
Space Complexity: O(# unique characters)
"""
def alien_dictionary(words: List[str]) -> str:
    # Per Leetcode testcases, ["abcd", "abc"] would be invalid. We specifically
    # check for this edge case here
    for i in range(len(words)-1):
        if len(words[i]) <= len(words[i+1]):
            continue
        if prefix_equal(words[i], words[i+1], len(words[i+1])):
            return ""

    # Generate a graph of which chars come before which other chars
    graph = generate_alien_graph(words)

    # Once we have our graph, this is a standard topological sort
    no_dependencies = []
    for key, value in graph.items():
        if len(value) == 0:
            no_dependencies.append(key)

    result = []

    # For each character, add it to the result and remove it as a dependency
    # from all of the other characters
    while len(no_dependencies) != 0:
        curr = no_dependencies.pop()
        result.append(curr)

        for key, value in graph.items():
            if curr in value:
                value.remove(curr)
                if len(value) == 0:
                    no_dependencies.append(key)

    # Check that our result is valid
    if len(result) != len(graph):
        return ""

    return "".join(result)

"""
Helper function to convert our strings into a graph
"""
def generate_alien_graph(words: List[str]) -> dict:
    # Since our nodes are character values and not just integers, we'll
    # use a map rather than a list
    adj_list = {}

    # Add all the nodes to our map
    for word in words:
        for c in word:
            if not c in adj_list:
                adj_list[c] = set()

    # We compare each sequential pair of strings. We essentially just want
    # to find the first different character because that tells us which
    # character of the two comes first in lexographical order
    for i in range(len(words)-1):
        # Find the first character that is different
        for j in range(min(len(words[i]), len(words[i+1]))):
            if words[i][j] == words[i+1][j]:
                continue

            # When we find a character that is different, add the dependency
            # to our adjacency list
            adj_list[words[i+1][j]].add(words[i][j])
            break

    return adj_list

"""
Helper function to test if two strings have the same prefix of length len
"""
def prefix_equal(a: str, b: str, len: int):
    for i in range(len):
        if a[i] != b[i]:
            return False
    return True

"""
Exercise 4.1: Keys and Rooms

Time Complexity: O(# rooms + # keys)
Space Complexity: O(# rooms)
"""
def keys_and_rooms(rooms: List[List[int]]):
    # We do a graph search where the rooms are the nodes and the edges are
    # the keys in each room. BFS is a simpler implementation but we could
    # do either BFS or DFS
    visited = set()
    to_visit = queue.Queue()
    to_visit.put(0)

    # Do the search
    while not to_visit.empty():
        curr = to_visit.get()
        visited.add(curr)

        # Add keys in the current room to the next possible rooms
        for room in rooms[curr]:
            if not room in visited:
                to_visit.put(room)

    # Return true if we successfully visited all the rooms
    return len(visited) == len(rooms)

"""
Exercise 4.2: Evaluate Division

Time Complexity: O(# queries * # variables * # equations)
Space Complexity: O(# variables)
"""
def evaluate_division(equations: List[List[str]], values: List[float], queries: List[List[str]]) -> List[float]:
    # For each query, do DFS to determine series of operations required
    # to compute the value
    result = []
    for i in range(len(queries)):
        result.append(_evaluate_division_inner(equations, values, queries[i][0], queries[i][1], set()))

    return result

"""
Do DFS
"""
def _evaluate_division_inner(equations: List[List[str]], values: List[float], curr: str, dest: str, visited: set) -> float:
    # Check if we've already computed a variable
    if curr in visited:
        return -1.0

    # The result of something divided by itself is 1
    if curr == dest:
        return 1.0

    # Track that we've seen a variable
    visited.add(curr)

    # Iterate over each equation to see which variables we can compute given our
    # current variable. Then repeat until we find the target variable
    for i in range(len(equations)):
        # If the first variable in the equation is our current variable,
        # then we multiply by the value. If it's the second variable then
        # we multiply by 1/the value
        equation = equations[i]
        if equation[0] == curr:
            result = _evaluate_division_inner(equations, values, equation[1], dest, visited)
            if result >= 0:
                return result * values[i]
        elif equation[1] == curr:
            result = _evaluate_division_inner(equations, values, equation[0], dest, visited)
            if result >= 0:
                return result / values[i]

    # Backtrack
    visited.remove(curr)

    return -1.0

"""
Exercise 4.3: Sliding Puzzle

Time Complexity: O(1)
Space Complexity: O(1)
Given the fixed board size, the time doesn't change based on the input
"""
def sliding_puzzle(board: List[List[int]]) -> int:
    # Since we have a fixed size board, we can explicitly express the
    # valid moves for each possible position of the empty space as well as
    # the win condition
    moves = [[1,3],[0,2,4],[1,5],[0,4],[1,3,5],[2,4]];
    solved = "123450";

    # We will represent the board as a string because it makes it way
    # easier for us to compare and add to a map
    linear_board = "".join(["".join([str(r) for r in row]) for row in board])

    # Now we do BFS to find the shortest path. We need to track the depth
    # of each board state so that we can get our result
    to_visit = queue.Queue()
    to_visit.put(linear_board)

    depth = {linear_board: 0}

    while not to_visit.empty():
        curr = to_visit.get()
        if curr == solved:
            break

        # The index of the empty spot tells us what valid moves we can make
        idx = curr.index('0')

        for move in moves[idx]:
            swapped = _swap(curr, idx, move)
            if not swapped in depth:
                to_visit.put(swapped)
                depth[swapped] = depth[curr] + 1

    if not solved in depth:
        return -1

    return depth[solved]

"""
Simple helper function to swap two characters in a string
"""
def _swap(s: str, i: int, j: int):
    l = list(s)
    temp = l[i]
    l[i] = l[j]
    l[j] = temp
    return "".join(l)


"""
Sample test cases
"""
if __name__ == '__main__':
    am = AdjacencyMatrix(4);
    am.add_edge(1,2);
    am.add_edge(1,3);
    print(am.neighbors(1));


    al = AdjacencyList(4);
    al.add_edge(1,2);
    al.add_edge(1,3);
    print(al.neighbors(1));

    matrix = [[False, False, False, True],
              [True, False, True, True],
              [True, False, False, True],
              [False, True, False, False]]

    print(adjacency_matrix_to_list(matrix))

    print(adjacency_list_to_matrix(adjacency_matrix_to_list(matrix)));

    node = Node(1);
    node.neighbors.append(Node(2));
    node.neighbors.append(Node(3));
    print(clone_graph(node));

    adj_list = [[1,2],[0],[0],[4,5],[3,5],[3,4]];
    print(valid_path_list(6, adj_list, 0, 1));
    print(valid_path_list(6, adj_list, 0, 5));


    adj_matrix = [
        [False, True, True, False, False, False],
        [True, False, False, False, False, False],
        [True, False, False, False, False, False],
        [False, False, False, False, True, True],
        [False, False, False, True, False, True],
        [False, False, False, True, True, False]];

    print(valid_path_matrix(6, adj_matrix, 0, 1));
    print(valid_path_matrix(6, adj_matrix, 0, 5));


    adj_list[2].append(4);
    print(length_of_shortest_path(adj_list, 0, 5));
    print(shortest_path(adj_list, 0, 5));
    print(all_paths(adj_list, 0, 5));

    print(course_scheduling(4, [[1,0],[2,0],[3,1],[3,2]]));

    print(course_scheduling_ii(4, [[1,0],[2,0],[3,1],[3,2]]));

    print(alien_dictionary(["wrt", "wrf", "er", "ett", "rftt"]));
    print(alien_dictionary(["ac", "ab", "zc", "zb"]));

    print(keys_and_rooms([[1],[2],[3],[]]));

    print(evaluate_division([["a","b"],["b","c"]], [2.0, 3.0], [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]));

    print(sliding_puzzle([[1,2,3],[4,0,5]]));
    print(sliding_puzzle([[1,2,3],[5,4,0]]));
