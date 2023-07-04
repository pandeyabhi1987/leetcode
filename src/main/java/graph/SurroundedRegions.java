package graph;

public class SurroundedRegions {
    char[][] board;
    int row;
    int col;

    public void solve(char[][] board) {
        if (board == null || board.length == 0) return;
        this.board = board;
        row = board.length;
        col = board[0].length;

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if ((r == 0 || r == row - 1 || c == 0 || c == col - 1) && board[r][c] == 'O') {
                    dfs(r, c);
                }
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == 'V') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void dfs(int r, int c) {
        if (r < 0 || r >= row || c < 0 || c >= col || board[r][c] == 'V'|| board[r][c] != 'O') {
            return;
        }
        board[r][c] = 'V';
        dfs(r + 1, c);
        dfs(r - 1, c);
        dfs(r, c + 1);
        dfs(r, c - 1);
    }
}
