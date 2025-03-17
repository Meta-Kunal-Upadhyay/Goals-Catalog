import java.util.Scanner;

class nQueen{

    public boolean isSafe(int[][] board, int row, int col, int dimenation) {
        int i = row;
        int j = col;
        while (i >= 0 && j >= 0) {
            if (board[i][j] == 1)
                return false;
            i--;
            j--;
        }

        int i1 = row;
        int j1 = col;
        while (j1 >= 0) {
            if (board[i1][j1] == 1)
                return false;
            j1--;
        }

        int i2 = row;
        int j2 = col;
        while (i2 < dimenation && j2 >= 0) {
            if (board[i2][j2] == 1)
                return false;
            i2++;
            j2--;

        }
        return true;
    }

    public boolean NQueen(int[][] board, int col, int dimenation) {
        if(col == dimenation) return true;

        for (int row = 0; row < dimenation; row++) {
            if (isSafe(board, row, col, dimenation)) {
                board[row][col] = 1;
                if (NQueen(board, col+1, dimenation)) {
                    return true;
                }
                board[row][col] = 0;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        nQueen s = new nQueen();
        int[][] board = new int[4][4];

        System.out.println("n queue: " + s.NQueen(board, 0, 4));
    }
}