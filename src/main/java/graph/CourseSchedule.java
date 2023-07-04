package graph;

import java.util.*;

public class CourseSchedule {
    public static void main(String[] args) {
        CourseSchedule cs = new CourseSchedule();
        System.out.println(cs.canFinish(5, new int[][]{{0, 1}, {0, 2}, {1, 3}, {1, 4}, {3, 4}}));
    }

    HashSet<Integer> visited;
    HashMap<Integer, List<Integer>> adjacencyList;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0) return true;
        adjacencyList = new HashMap<>();
        visited = new HashSet<>();

        for (int[] prerequisite : prerequisites) {
            adjacencyList.putIfAbsent(prerequisite[0], new ArrayList<>());
            adjacencyList.get(prerequisite[0]).add(prerequisite[1]);
        }
        for (int i = 0; i <= numCourses; i++) {
            if(!dfs(i)) return false;
        }
        return true;
    }

    boolean dfs(int course) {
        if (visited.contains(course)) return false;
        if (!adjacencyList.containsKey(course)) return true;

        visited.add(course);
        for (int c : adjacencyList.get(course)) {
            if (!dfs(c)) return false;
        }
        visited.remove(course);
        adjacencyList.remove(course);
        return true;
    }
}
