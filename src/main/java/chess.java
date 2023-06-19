public class chess {
    public static void main(String[] args) {
        char[][] chess = getChessBoard(2);
        for (int i = 0; i < chess.length; i++) {
            for (int j = 0; j < chess.length; j++) {
                System.out.print(chess[i][j]);
            }
            System.out.println();
        }
    }

    public static char[][] getChessBoard(int n) {
        char[][] chess = new char[n][n];
        for (int i = 0; i < n * n; i++) {
            int p = i / n;
            int q = i % n;
            if ((p + q) % 2 == 0) {
                chess[p][q] = 'W';
            } else {
                chess[p][q] = 'B';
            }
        }
        return chess;
    }
}

