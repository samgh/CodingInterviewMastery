"""
Title: Graph Solutions

This file contains the template for the Graph exercises in the DS & Algos
Primer. Fill in the exercises here and refer to graph_solutions.py for the
complete code samples.

Execution: python graphs.py
"""

from copy import deepcopy
import queue
from typing import List

"""
Exercise 1.1: Given the following graph (see workbook), write out by hand the
representation of the graph as an Adjacency Matrix and an Adjacency List.

Adjacency Matrix:


Adjacecny List:


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
        # INSERT YOUR CODE HERE

    """
    Add an edge from node1 to node2

    Time Complexity:
    Space Complexity:
    """
    def add_edge(self, node1: int, node2: int):
        # INSERT YOUR CODE HERE

    """
    Remove an edge from node1 to node2

    Time Complexity:
    Space Complexity:
    """
    def remove_edge(self, node1: int, node2: int):
        # INSERT YOUR CODE HERE

    """
    Get all neighbors of node

    Time Complexity:
    Space Complexity:
    """
    def neighbors(self, node: int) -> List[int]:
        # INSERT YOUR CODE HERE


class AdjacencyList:

    """
    Constructor
    """
    def __init__(self, n: int):
        # INSERT YOUR CODE HERE

    """
    Add an edge from node1 to node2

    Time Complexity:
    Space Complexity:
    """
    def add_edge(self, node1: int, node2: int):
        # INSERT YOUR CODE HERE

    """
    Remove an edge from node1 to node2

    Time Complexity:
    Space Complexity:
    """
    def remove_edge(self, node1: int, node2: int):
        # INSERT YOUR CODE HERE

    """
    Get all neighbors of node

    Time Complexity:
    Space Complexity:
    """
    def neighbors(self, node: int) -> List[int]:
        # INSERT YOUR CODE HERE

"""
Exercise 1.3: Implement a function to convert an adjacency matrix into an
adjacency list and vice versa.
"""

"""
Convert an adjacency matrix into an adjacency list

Time Complexity:
Space Complexity:
"""
def adjacency_matrix_to_list(matrix: List[List[bool]]) -> List[set]:
    # INSERT YOUR CODE HERE

"""
Convert an adjacency list into an adjacency matrix

Time Complexity:
Space Complexity:
"""
def adjacency_list_to_matrix(list: List[set]) -> List[List[bool]]:
    # INSERT YOUR CODE HERE

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

Time Complexity:
Space Complexity:
"""
def clone_graph(node: Node) -> Node:
    # INSERT YOUR CODE HERE

"""
Exercise 2.1: Write a function to determine whether a path exists between
two nodes
"""

"""
Implement using an adjacency list

Time Complexity:
Space Complexity:
"""
def valid_path_list(n: int, edges: List[List[int]], start: int, end: int, visited: set = set()) -> bool:
    # INSERT YOUR CODE HERE

"""
Implement using an adjacency matrix

Time Complexity:
Space Complexity:
"""
def valid_path_matrix(n: int, edges: List[List[bool]], start: int, end: int, visited: set = set()) -> bool:
    # INSERT YOUR CODE HERE

"""
Exercise 2.2: Write a function to find the length of the shortest path between
two nodes (use BFS)

Time Complexity:
Space Complexity:
"""
def length_of_shortest_path(edges: List[List[int]], start: int, end: int) -> int:
    # INSERT YOUR CODE HERE

"""
Exercise 2.3: Write a function to find the shortest path between two nodes
(use BFS)

Time Complexity:
Space Complexity:
"""
def shortest_path(edges: List[List[int]], start: int, end: int):
    # INSERT YOUR CODE HERE

"""
Exercise 2.4: Write a function to find ALL the paths between two nodes

Time Complexity:
Space Complexity:
"""
def all_paths(edges: List[int], start: int, end: int) -> List[List[int]]:
    # INSERT YOUR CODE HERE

"""
Exercise 3.1: Course Scheduling

Time Complexity:
Space Complexity:
"""
def course_scheduling(num_courses: int, prerequisites: List[List[int]]) -> bool:
    # INSERT YOUR CODE HERE

"""
Exercise 3.2: Course Scheduling II

Time Complexity:
Space Complexity:
"""
def course_scheduling_ii(num_courses: int, prerequisites: List[List[int]]) -> List[int]:
    # INSERT YOUR CODE HERE

"""
Exercise 3.3: Alien Dictionary

Time Complexity:
Space Complexity:
"""
def alien_dictionary(words: List[str]) -> str:
    # INSERT YOUR CODE HERE

"""
Exercise 4.1: Keys and Rooms

Time Complexity:
Space Complexity:
"""
def keys_and_rooms(rooms: List[List[int]]):
    # INSERT YOUR CODE HERE

"""
Exercise 4.2: Evaluate Division

Time Complexity:
Space Complexity:
"""
def evaluate_division(equations: List[List[str]], values: List[float], queries: List[List[str]]) -> List[float]:
    # INSERT YOUR CODE HERE

"""
Exercise 4.3: Sliding Puzzle

Time Complexity:
Space Complexity:
"""
def sliding_puzzle(board: List[List[int]]) -> int:
    # INSERT YOUR CODE HERE


"""
ADD TEST CASES HERE
"""
if __name__ == '__main__':
    # INSERT YOUR CODE HERE
