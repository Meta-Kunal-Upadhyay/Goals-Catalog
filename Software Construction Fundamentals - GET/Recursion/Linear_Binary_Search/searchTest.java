import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class searchTest{
    Search search= new Search();

    @Test
    public void searchTest1(){
        int[] arr1={3,5,6,8,12,45,67};
        assertEquals(1, search.LinearSearch(arr1, 5));
        assertEquals(1, search.BinarySearch(arr1, 5));

        int[] arr2={5,6,7,8,12,23,45,67};
        assertEquals(5, search.LinearSearch(arr2, 23));
        assertEquals(5, search.BinarySearch(arr2, 23));

        int[] arr3={11,23,34,45,67,78,89};
        assertEquals(2, search.LinearSearch(arr3, 34));
        assertEquals(2, search.BinarySearch(arr3, 34));

        int[] arr4={12,45,24,3,9,87,45};
        assertEquals(-1, search.LinearSearch(arr4, 88));
        assertEquals(-1, search.BinarySearch(arr4, 34));
    }

}
