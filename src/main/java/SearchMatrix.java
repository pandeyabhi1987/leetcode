public class SearchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int rowStart = 0;
        int columnStart = 0;
        int columnEnd = matrix[0].length - 1;
        int rowEnd = matrix.length - 1;

        while (rowStart < rowEnd) {
            int mid = (rowStart + rowEnd) / 2;
            if (target == matrix[mid][columnEnd]) return true;

            if (target < matrix[mid][columnEnd]) {
                rowEnd = mid;
            } else {
                rowStart = mid + 1;
            }
        }

        int mid = (rowStart + rowEnd) / 2;
        while (columnStart <= columnEnd) {
            int m = (columnStart + columnEnd) / 2;
            if (matrix[mid][m] == target) return true;

            if (target < matrix[mid][m]) {
                columnEnd = m - 1;
            } else {
                columnStart = m + 1;
            }
        }

        return false;
    }
}
