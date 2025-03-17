import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class nQueenTest{
    nQueen queenProblem = new nQueen();
    @Test
    public void queen(){

        int[][] arr = new int[2][2];
        assertEquals(false, queenProblem.NQueen(arr, 0, 2));

        int[][] arr1 = new int[4][4];
        assertEquals(true, queenProblem.NQueen(arr1, 0, 4));

        int[][] arr3 = new int[8][8];
        assertEquals(true, queenProblem.NQueen(arr3, 0, 8));

        int[][] arr4 = new int[1][1];
        assertEquals(false, queenProblem.NQueen(arr3, 0, 1));


    }
}