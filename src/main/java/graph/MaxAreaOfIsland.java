package graph;

public class MaxAreaOfIsland {
    int[][] visited;
    int[][] grid;

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        visited = grid;
        this.grid = grid;
        int maxArea = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    int area = dfs(i, j);
                    maxArea = Math.max(area, maxArea);
                }
            }
        }
        return maxArea;
    }

    private int dfs(int r, int c) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || visited[r][c] == 0) {
            return 0;
        }
        visited[r][c] = 0;
        int area = 1;
        area+=dfs(r + 1, c);
        area+=dfs(r, c + 1);
        area+=dfs(r - 1, c);
        area+=dfs(r, c - 1);
        return area;
    }
}
