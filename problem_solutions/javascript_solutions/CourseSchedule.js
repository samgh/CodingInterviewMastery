/**
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
 *      number numCourses          => number of courses
 *      number[][] prerequisites   => all course dependencies
 *   Output:
 *      boolean                   => is there a valid course ordering
 *
 *   Execution: node CourseSchedule.js
 */

/**
 * The easiest way to determine whether we can finish the curriculum is to
 * simply determine whether there are any cyclical dependencies. If there
 * are, then we cannot complete the curriculum, but if there are not, then
 * we are able to complete everything
 *
 * This can also be solved using topological sort:
 * https://github.com/samgh/CodingInterviewMastery/blob/main/ds_algos_primer/javascript/GraphSolutions.js#L579-L681
 *
 * Time Complexity: O(V + E)
 * Space Complexity: O(V)
 *
 * @param{number} numCourses
 * @param{number[][]} prerequisites
 * @return{boolean}
 */
var canFinish = function(numCourses, prerequisites) {
    // Convert the list of edges into an adjacency list
    var adjList = [];
    for (var i = 0; i < numCourses; i++) {
        adjList.push([]);
    }
    prerequisites.forEach(function(edge) {
        adjList[edge[0]].push(edge[1]);
    });

    // For every node we haven't already visited, we will traverse from that
    // node and see if we ever loop back on ourself. If we do, then we found
    // a cycle
    var allVisited = new Set();
    for (var i = 0; i < numCourses; i++) {
        // If we've already visited a node, we've traversed everything
        // reachable from that node
        if (!allVisited.has(i)) {
            // If we haven't visited the node, traverse and see if we find
            // a cycle
            if (!traverse(i, adjList, new Set(), allVisited)) {
                return false;
            }
        }
    }

    // We didn't find any cycles
    return true;
}

/**
 * Traverse from the current node and see if there are any cycles in the
 * path starting at that node
 *
 * @param{number} curr
 * @param{number[][]} adjList
 * @param{Set} currVisited
 * @param{Set} allVisited
 * @return{boolean}
 */
var traverse = function(curr, adjList, currVisited, allVisited) {
    // If the current path contains our node, then we've looped back on
    // ourself and found a cycle
    if (currVisited.has(curr)) return false;

    // If we reach a node that we've already visited but isn't in our current
    // path, then we know that there is no cycle in that section of the graph
    if (allVisited.has(curr)) return true;

    // Track that we've visited the node
    allVisited.add(curr);

    // Perform DFS and track the current path
    for (var i = 0; i < adjList[curr].length; i++) {
        currVisited.add(curr);
        if (!traverse(adjList[curr][i], adjList, currVisited, allVisited)) return false;

        // Backtrack so that we only track the nodes in our current path
        currVisited.delete(curr);
    }

    // We didn't find a cycle
    return true;
}

var tester = function() {
    console.assert(canFinish(2, [[1,0]]) == true);
    console.assert(canFinish(2, [[1,0],[0,1]]) == false);
    console.assert(canFinish(5, [[1,4],[2,4],[3,1],[3,2]]) == true);

    // ADD YOUR OWN TESTS HERE
}

tester();
