import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class ArrayRearranger {
    public static int[] fixXY(int[] arr, int X, int Y) {
        assert arr.length > 0 : "Array cannot be empty";
        int xCount = 0;
        int yCount = 0; 
        for (int num : arr) {
            if (num == X) xCount++;
            if (num == Y) yCount++; 
        }
        assert xCount == yCount : "Number of X's and Y's must be equal"; 
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == X && arr[i + 1] == X) {
                assert false : "Two adjacent X's are not allowed";
            } 
        }
        assert arr[arr.length - 1] != X : "X cannot occur at the last index";
        List<Integer> result = new ArrayList<>();
        List<Integer> nonXY = new ArrayList<>();
        for (int num : arr) {
            if (num == X) { 
                result.add(X);
            } else if (num == Y) { 
                nonXY.add(Y);
            } else {
                nonXY.add(num);
            } 
        }
        int nonXYIndex = 0;
        for (int i = 0; i < result.size(); i++) {
            result.set(i, nonXY.get(nonXYIndex));
            nonXYIndex++;
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
        }
    
    
    public static void main(String[] args) {
        int[] result1 = fixXY(new int[]{5, 4, 9, 4, 9, 5}, 4, 5);
        System.out.println("Result 1: " + Arrays.toString(result1));
        int[] result2 = fixXY(new int[]{1, 4, 1, 5}, 4, 5);
        System.out.println("Result 2: " + Arrays.toString(result2));
        int[] result3 = fixXY(new int[]{1, 4, 1, 5, 5, 4, 1}, 4, 5);
        System.out.println("Result 3: " + Arrays.toString(result3));
    }
}