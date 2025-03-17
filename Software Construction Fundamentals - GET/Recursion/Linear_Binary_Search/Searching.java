import java.lang.reflect.Array;
import java.util.Scanner;

class Search {

    
    public int LinearSearch(int[] arr, int key) {
    return linearSearch(arr, key,0);
    }

    public int linearSearch(int[] arr, int key, int start){
        if(start>=arr.length){
            return -1;
        }
        if(arr[start]==key){
            return start;
        }
        return linearSearch(arr , key, start+1);

    }

    public int BinarySearch(int[] arr, int key) {
        return binarySearch(arr, key, 0, arr.length-1);
    }


public int binarySearch(int [] arr, int key, int start, int end){
    if (start > end){
         return -1;
    }
        int mid = (start + end) / 2;
        
        if(key == arr[mid]) return mid;
        else if (arr[mid] > key) {
            end = mid-1;
            return binarySearch(arr, key, start,end);
        } else {
            start = mid + 1;
            return binarySearch(arr, key, start,end);
        }
}
}

public class Searching{
    public static void main(String[] args) {
        Search s = new Search();
        int[] arr1 = { 1, 2, 3, 4, 5 };
        int[] arr2 = { 6, 40, 70, 89, 95,456 };
        System.out.println("Element is found at index: " + s.LinearSearch(arr1, 1));
        System.out.println("Element is found at index:  " + s.BinarySearch(arr2, 95));
    }
}