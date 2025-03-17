import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class SparseMatrixTest {
    SparseMatrix matrix = new SparseMatrix();
    @Test
    public void transposeTest(){
        int[][] array1 = {{1,2,3},{3,4,5}, {6,7,8}};
        int[][] result1 = {{1,3,6}, {2,4,7},{3,5,8}};
        assertArrayEquals(result1, matrix.isTranspose(array1));

        int[][] array2 = {{5,7,8},{22,25,18}};
        int[][] result2 = {{5,22}, {7,25},{8,18}};
        assertArrayEquals(result2, matrix.isTranspose(array2));

    }

    @Test
    public void isSymmetricTest(){
        int[][] array1 = {{2,3,6},{3,4,5}, {6,5,9}};
        assertEquals(true, matrix.isSymmetrical(array1));

        int[][] array2 = {{5,7,8},{22,25,18},{2,3,4}};
        assertEquals(false, matrix.isSymmetrical(array2));

    }
   
    @Test
    public void addTest(){
        int[][] array1 = {{2,2,1},{1,5,0},{0,0,1}};
        int[][] array2 = {{5,7,1},{0,3,0}, {1,0,8}};
        int[][] result1 = {{7,9,2}, {1,8,0}, {1,0,9}};
        assertArrayEquals(result1, matrix.isAddition(array1,array2));

        int[][] array3 = {{1,2,3},{1,5,0},{2,3,1}};
        int[][] array4 = {{6,7,3},{1,2,3}, {4,3,5}};
        int[][] result2 = {{7,9,6}, {2,7,3}, {6,6,6}};
        assertArrayEquals(result2, matrix.isAddition(array3,array4));



    }


    @Test
    public void multiplyTest(){
        int[][] array1 = {{1,2},{3,4}};
        int[][] array2 = {{5,6},{8,9,10}};
        int[][] result1 = {{21,24}, {47,54}};
        assertArrayEquals(result1, matrix.findMultiplication(array1,array2));

        int[][] array3 = {{1,1,1},{2,2,2},{3,3,3}};
        int[][] array4 = {{1,1,1},{2,2,2},{3,3,3}};
        int[][] result2 = {{6,6,6}, {12,12,12},{18,18,18}};
        assertArrayEquals(result2, matrix.findMultiplication(array3,array4));

    }

}
