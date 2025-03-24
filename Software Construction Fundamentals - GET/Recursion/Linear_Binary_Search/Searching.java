import java.lang.reflect.Array;
import java.util.Scanner;

class Search {

    public int linearSearch(int[] arr, int key, int idx){
        if(idx >= arr.length){
            return -1;
        }

        if(arr[idx] == key){
            return idx;
        }

        return linearSearch(arr , key, idx + 1);

    }



public int binarySearch(int [] arr, int key, int start, int end){
    if (start > end){
         return -1;
    }

    int mid = start + ((end - start) / 2);
        
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
        int[] arr2 = { 6, 40, 70, 89, 95, 456};
        System.out.println("Element is found at index: " + s.linearSearch(arr1, 4, 0));
        System.out.println("Element is found at index:  " + s.binarySearch(arr2, 95, 0, arr2.length - 1));
    }
}