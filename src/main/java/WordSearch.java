import java.util.HashSet;
import java.util.*;

public class WordSearch {
    boolean[][] visited;
    public boolean exist(char[][] board, String word) {
        int row = board.length;
        int column = board[0].length;
        visited = new boolean[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (dfs(i, j, 0, word, board)) return true;
            }
        }
        return false;
    }
    private boolean dfs (int row, int col, int index, String word, char[][] board){
        if(index >= word.length()){
            return true;
        }
        if(row <0 || col < 0 || row >= board.length || col >= board[0].length
                || word.charAt(index) != board[row][col]
        || visited[row][col]){
            return false;
        }
        visited[row][col] = true;
        var res = dfs(row+1, col, index+1, word, board)
                || dfs(row-1, col, index+1, word, board)
                || dfs(row, col+1, index+1, word, board)
                || dfs(row, col-1, index+1, word, board);

        visited[row][col] = false;
        return res;
    }
}
