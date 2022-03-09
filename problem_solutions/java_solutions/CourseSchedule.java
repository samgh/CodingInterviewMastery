/*
 *   Title: Course Schedule I
 *   Leetcode Link: https://leetcode.com/problems/course-schedule/
 *
 *   Problem: There are a total of n courses you have to take, labeled from 0 to
 *   n-1.
 *
 *   Some courses may have prerequisites, for example to take course 0 you have
 *   to first take course 1, which is expressed as a pair: [0,1]
 *
 *   Given the total number of courses and a list of prerequisite pairs, is it
 *   possible for you to finish all courses?
 *
 *   Input:
 *      int numCourses          => number of courses
 *      int[][] prerequisites   => all course dependencies
 *   Output:
 *      boolean                 => is there a valid course ordering
 *
 *   Execution: javac CourseSchedule.java && java -ea CourseSchedule
 */

import java.util.*;

public class CourseSchedule {

    /*
     * The easiest way to determine whether we can finish the curriculum is to
     * simply determine whether there are any cyclical dependencies. If there
     * are, then we cannot complete the curriculum, but if there are not, then
     * we are able to complete everything
     *
     * This can also be solved using topological sort:
     * https://github.com/samgh/CodingInterviewMastery/blob/main/ds_algos_primer/java/GraphSolutions.java#L509-L605
     *
     * Time Complexity: O(V + E)
     * Space Complexity: O(V)
     */
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        // Convert the list of edges into an adjacency list
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            adjList.put(i, new LinkedList<>());
        }
        for (int[] edge : prerequisites) {
            adjList.get(edge[0]).add(edge[1]);
        }

        // For every node we haven't already visited, we will traverse from that
        // node and see if we ever loop back on ourself. If we do, then we found
        // a cycle
        Set<Integer> allVisited = new HashSet<>();
        for (int i = 0; i < numCourses; i++) {
            // If we've already visited a node, we've traversed everything
            // reachable from that node
            if (!allVisited.contains(i)) {
                // If we haven't visited the node, traverse and see if we find
                // a cycle
                if (!traverse(i, adjList, new HashSet<Integer>(), allVisited)) {
                    return false;
                }
            }
        }

        // We didn't find any cycles
        return true;
    }

    /*
     * Traverse from the current node and see if there are any cycles in the
     * path starting at that node
     */
    private static boolean traverse(int curr, Map<Integer, List<Integer>> adjList,
                                    Set<Integer> currVisited, Set<Integer> allVisited) {
        // If the current path contains our node, then we've looped back on
        // ourself and found a cycle
        if (currVisited.contains(curr)) return false;

        // If we reach a node that we've already visited but isn't in our current
        // path, then we know that there is no cycle in that section of the graph
        if (allVisited.contains(curr)) return true;

        // Track that we've visited the node
        allVisited.add(curr);

        // Perform DFS and track the current path
        for (int i : adjList.get(curr)) {
            currVisited.add(curr);
            if (!traverse(i, adjList, currVisited, allVisited)) return false;

            // Backtrack so that we only track the nodes in our current path
            currVisited.remove(curr);
        }

        // We didn't find a cycle
        return true;
    }

    public static void main(String[] args) {
        assert canFinish(2, new int[][]{{1,0}}) == true;
        assert canFinish(2, new int[][]{{1,0},{0,1}}) == false;
        assert canFinish(5, new int[][]{{1,4},{2,4},{3,1},{3,2}}) == true;
        assert canFinish(3, new int[][]{{0,1},{0,2},{1,2}}) == true;

        System.out.println("Passed all test cases");
    }

}
