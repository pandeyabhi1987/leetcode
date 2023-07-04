package graph;

public class IslandPerimeter {
    public int islandPerimeter(int[][] grid) {
        int perimeter = 0;
        int row = grid.length;
        int col = grid[0].length;
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (grid[r][c] == 1) {
                    perimeter = 4;
                    if (r > 0 && grid[r - 1][c] == 1) {
                        perimeter--;
                    }
                    if (c > 0 && grid[r][c - 1] == 1) {
                        perimeter--;
                    }
                    if (r < row - 1 && grid[r + 1][c] == 1) {
                        perimeter--;
                    }
                    if (c < col - 1 && grid[r][c + 1] == 1) {
                        perimeter--;
                    }
                }

            }
        }
        return perimeter;
    }
}
