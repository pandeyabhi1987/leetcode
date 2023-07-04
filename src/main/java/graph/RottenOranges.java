package graph;

import java.util.LinkedList;
import java.util.Queue;

public class RottenOranges {
    public int orangesRotting(int[][] grid) {
        int fresh = 0;
        int time = 0;
        Queue<int[]> q = new LinkedList();
        int rows = grid.length;
        int cols = grid[0].length;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1) fresh++;
                if (grid[r][c] == 2) q.offer(new int[]{r, c});
            }
        }
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!q.isEmpty() && fresh != 0) {
            time++;
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                int[] rotten = q.poll();
                int r = rotten[0], c = rotten[1];
                for (int[] dir : dirs) {
                    int x = r + dir[0], y = c + dir[1];
                    if(x < 0 || x >= rows || y <0 ||y >= cols || grid[x][y] != 1) continue;

                    grid[x][y] = 2;
                    q.offer(new int[]{x, y});
                    fresh--;
                }
            }

        }
        return fresh == 0 ? time: -1;
    }
}
