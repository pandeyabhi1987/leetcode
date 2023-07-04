package graph;

public class NumOfIslands {
    char[][] visited;
    char[][] grid;

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        visited = grid;
        this.grid = grid;
        int row = grid.length;
        int col = grid[0].length;
        int numIslands = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    numIslands++;
                    dfs(i, j);
                }
            }
        }
        return numIslands;
    }

    private void dfs(int r, int c) {
        if (r < 0 || r >= grid.length || c < 0 || c > grid[0].length || visited[r][c] == '0') return;

        visited[r][c] = '0';
        dfs(r + 1, c);
        dfs(r - 1, c);
        dfs(r, c + 1);
        dfs(r, c - 1);
    }
}
