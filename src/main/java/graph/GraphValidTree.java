package graph;

import java.util.*;

public class GraphValidTree {
    HashMap<Integer, List<Integer>> graph;
    HashSet<Integer> visited;

    public boolean validTree(int n, int[][] edges) {
        if (n == 0 || n == 1) return true;

        if (edges.length == 0) return false;

        visited = new HashSet<>();
        graph = new HashMap<>();

        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        return dfs(0, -1) && visited.size() == n;
    }

    boolean dfs(int node, int prev) {
        if (visited.contains(node)) return false;

        visited.add(node);

        for (int neighbour : graph.get(node)) {
            if (neighbour == prev) continue;
            if (!dfs(neighbour, node)) {
                return false;
            }
        }
        return true;
    }
}
