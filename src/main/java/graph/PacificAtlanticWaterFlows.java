package graph;

import java.util.*;

public class PacificAtlanticWaterFlows {
    int[][] landHeights;
    int row;
    int col;

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();
        if (heights == null || heights.length == 0) return result;
        landHeights = heights;
        row = heights.length;
        col = heights[0].length;

        Queue<int[]> pacificQueue = new LinkedList<>();
        Queue<int[]> atlanticQueue = new LinkedList<>();

        for (int r = 0; r < row; r++) {
            pacificQueue.offer(new int[]{r, 0});
            atlanticQueue.offer(new int[]{r, col - 1});
        }
        for (int c = 0; c < col; c++) {
            pacificQueue.offer(new int[]{0, c});
            atlanticQueue.offer(new int[]{row - 1, c});
        }

        boolean[][] pacificReachable = bfs(pacificQueue);
        boolean[][] atlanticReachable = bfs(atlanticQueue);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (pacificReachable[i][j] && atlanticReachable[i][j]) {
                    result.add(List.of(i, j));
                }
            }
        }
        return result;
    }

    private boolean[][] bfs(Queue<int[]> queue) {
        boolean[][] reachable = new boolean[row][col];
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            reachable[cell[0]][cell[1]] = true;

        }
        return new boolean[0][];
    }
}
