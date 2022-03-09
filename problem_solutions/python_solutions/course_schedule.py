"""
Title: Course Schedule I
Leetcode Link: https://leetcode.com/problems/course-schedule/

Problem: There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have
to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it
possible for you to finish all courses?

Input:
    int numCourses          => number of courses
    int[][] prerequisites   => all course dependencies
Output:
    boolean                 => is there a valid course ordering

Execution: python course_schedule.py
"""
import unittest
from collections import defaultdict
from typing import List

"""
The easiest way to determine whether we can finish the curriculum is to
simply determine whether there are any cyclical dependencies. If there
are, then we cannot complete the curriculum, but if there are not, then
we are able to complete everything

This can also be solved using topological sort:
https://github.com/samgh/CodingInterviewMastery/blob/main/ds_algos_primer/python/graph_solutions.py#L442-L529

Time Complexity: O(V + E)
Space Complexity: O(V)
"""
def can_finish(num_courses: int, prerequisites: List[List[int]]) -> bool:

    """
    Traverse from the current node and see if there are any cycles in the
    path starting at that node
    """
    def traverse(curr: int, adj_list: dict, curr_visited: set, all_visited: set) -> bool:
        # If the current path contains our node, then we've looped back on
        # ourself and found a cycle
        if curr in curr_visited:
            return False

        # If we reach a node that we've already visited but isn't in our current
        # path, then we know that there is no cycle in that section of the graph
        if curr in all_visited:
            return True

        # Track that we've visited the node
        all_visited.add(curr)

        # Perform DFS and track the current path
        for i in adj_list[curr]:
            curr_visited.add(curr)
            if not traverse(i, adj_list, curr_visited, all_visited):
                return False

            # Backtrack so that we only track the nodes in our current path
            curr_visited.remove(curr)

        # We didn't find a cycle
        return True

    adj_list = defaultdict(list)
    for p in prerequisites:
        adj_list[p[0]].append(p[1])

    # For every node we haven't already visited, we will traverse from that
    # node and see if we ever loop back on ourself. If we do, then we found
    # a cycle
    all_visited = set()
    for i in range(num_courses):
        # If we've already visited a node, we've traversed everything
        # reachable from that node
        if not i in all_visited:
            # If we haven't visited the node, traverse and see if we find
            # a cycle
            if not traverse(i, adj_list, set(), all_visited):
                return False

    # We didn't find any cycles
    return True


class TestCourseSchedule(unittest.TestCase):
    """Unit test for can_finish for course schedule."""

    def test_1(self):
        self.assertEqual(can_finish(2, [[1, 0]]), True)
        print("Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.")

    def test_2(self):
        self.assertEqual(can_finish(2, [[1,0],[0,1]]), False)
        print("Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.")


if __name__ == '__main__':
    unittest.main()
