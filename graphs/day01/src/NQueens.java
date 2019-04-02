import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NQueens {


    /**
     * Checks the 45° and 135° diagonals for an existing queen. For example, if the board is a 5x5
     * and you call checkDiagonal(board, 3, 1), The positions checked for an existing queen are
     * marked below with an `x`. The location (3, 1) is marked with an `o`.
     *
     * ....x
     * ...x.
     * x.x..
     * .o...
     * .....
     *
     * Returns true if a Queen is found.
     *
     * Do not modify this function (the tests use it)
     */
    public static boolean checkDiagonal(char[][] board, int r, int c) {
        int y = r - 1;
        int x = c - 1;
        while (y >= 0 && x >= 0) {
            if (board[y][x] == 'Q') return true;
            x--;
            y--;
        }
        y = r - 1;
        x = c + 1;
        while (y >= 0 && x < board[0].length) {
            if (board[y][x] == 'Q') return true;
            x++;
            y--;
        }
        return false;
    }

    public static boolean checkColumn(char[][] board, int r, int c) {
        int y = 0;
        while (y < board.length) {
            if (board[y][c] == 'Q') return true;
            y++;
        }
        return false;
    }


    /**
     * Creates a deep copy of the input array and returns it
     */
    private static char[][] copyOf(char[][] A) {
        char[][] B = new char[A.length][A[0].length];
        for (int i = 0; i < A.length; i++)
            System.arraycopy(A[i], 0, B[i], 0, A[0].length);
        return B;
    }


    public static List<char[][]> nQueensSolutions(int n) {
        char[][] board = new char[n][n];
        for (int k = 0; k < board.length; k++) {
            for (int l = 0; l < board[0].length; l++) {
                board[k][l] = '.';
            }
        }
        int rowNum = 0;
        List<char[][]> boards = new LinkedList<char[][]>();
        queenHelper(board,rowNum,boards);
        return boards;
    }

    public static void queenHelper(char[][] board, int rowNum, List<char[][]> boards) {
        if (rowNum == board.length) {
            boards.add(copyOf(board));
        }

        for (int i = 0; i < board[0].length; i++) {
            if (!checkDiagonal(board,rowNum,i) && !checkColumn(board,rowNum,i)) {
                board[rowNum][i] = 'Q';
                queenHelper(board,rowNum+1,boards);
                board[rowNum][i] = '.';
            }
        }


    }

}
