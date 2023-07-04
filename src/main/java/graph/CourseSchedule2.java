package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class CourseSchedule2 {
    HashSet<Integer> visited;
    HashSet<Integer> cycle;
    HashMap<Integer, List<Integer>> graph;
    List<Integer> result;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        result = new ArrayList<>();
        graph = new HashMap<>();
        visited = new HashSet<>();
        cycle = new HashSet<>();

        for (int i = 0; i < numCourses; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            graph.get(prerequisite[0]).add(prerequisite[1]);
        }
        for (int course = 0; course < numCourses; course++) {
            if(!dfs(course)) return new int[0];
        }
        return result.stream().mapToInt(i -> i).toArray();
    }

    boolean dfs(int course) {
        if (cycle.contains(course)) return false;
        if (visited.contains(course)) return true;
        cycle.add(course);
        for (int prerequisites : graph.get(course)) {
            if (!dfs(prerequisites)) return false;
        }
        visited.add(course);
        cycle.remove(course);
        result.add(course);
        return true;
    }
}
