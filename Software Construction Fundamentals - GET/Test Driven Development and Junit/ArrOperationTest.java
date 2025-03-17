import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class ArrOperationTest {

    ArrOperations operation = new ArrOperations();

    @Test
    public void clumpsTest(){
        int[] arr1 = {1,1,1,1,1}  ;
        assertEquals(1, operation.clumpsMax(arr1));

        int[] arr2 = {1,2,2,3,4,4};
        assertEquals(2, operation.clumpsMax(arr2));

        int[] arr3={};
        assertThrows(AssertionError.class, () -> {
            if(arr3.length==0){
                throw new AssertionError("Array cant be empty");
            }
        });

        int[] arr4= {1, 1, 2, 1, 1};
        assertEquals(2, operation.clumpsMax(arr4));

  
    }


    @Test
    public void maxMirrorTest(){
        int[] arr1 = {1, 2, 3, 8, 9, 3, 2, 1}  ;
        assertEquals(3, operation.maxMirror(arr1));

        int[] arr2 = {7, 1, 4, 9, 7, 4, 1};
        assertEquals(2, operation.maxMirror(arr2));

        int[] arr3={};
        assertThrows(AssertionError.class, () -> {
            if(arr3.length==0){
                throw new AssertionError("Array is empty");
            }
        });

        int[] arr4= {1, 4, 5, 3, 5, 4, 1};
        assertEquals(7, operation.maxMirror(arr4));

  
    }

    @Test
    public void SplitArr(){

        int[] arr1 = {1, 1, 1, 2, 1}  ;
        assertEquals(3, operation.SplitArray(arr1));

        int[] arr2 = {2, 1, 1, 2, 1};
        assertEquals(-1, operation.SplitArray(arr2));

        int[] arr3={};
        assertThrows(AssertionError.class, () -> {
            if(arr3.length==0){
                throw new AssertionError("Array cant be empty");
            }
        });

        int[] arr4= {10, 10};
        assertEquals(1, operation.SplitArray(arr4));

  
    }

    @Test
    public void FixXYText(){
        

        int[] arr1={};
        assertThrows(AssertionError.class, () -> {
            if(arr1.length==0){
                throw new AssertionError("Array cant be empty");
            }
        });

        int[] arr2 = {4, 5, 4, 5, 4};
        assertThrows(AssertionError.class, () -> operation.fixXY(arr2, 4, 5));


        int[] arr3 = {4, 4, 5, 1, 5};
        assertThrows(AssertionError.class, () -> operation.fixXY(arr3, 4, 5));

    
        int[] arr5 = {5, 4, 9, 4, 9, 5} ;
        int[] output = {9, 4, 5, 4, 5, 9};
        assertArrayEquals(output, operation.fixXY(arr5, 4, 5));

        int[] arr6 = {1, 4, 1, 5};
        int[] output2={1, 4, 5, 1};
        assertArrayEquals(output2, operation.fixXY(arr6, 4,5));

    
        int[] arr7= {1, 4, 1, 5, 5, 4, 1};
        int[] output4 = {1, 4, 5, 1, 1, 4, 5};
        assertArrayEquals(output4, operation.fixXY(arr7, 4,5));

  
    }
    

}