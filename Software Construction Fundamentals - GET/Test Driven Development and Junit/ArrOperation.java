import java.util.*;

class ArrOperation {
    public int maxMirror(int [] arr){
        int maxSize = 0;
        for(int i = 0; i < arr.length; i++){
            for(int j = arr.length-1; j >= 0; j--){
                int start = i;
                int end = j;
                int size = 0;
                while(start < arr.length && end >= 0 && arr[start] == arr[end]){
                    size++;
                    start++;
                    end--;
                }
                if(size > maxSize){
                    maxSize = size;
                }
            }
        }
        return maxSize;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        ArrOperation a1 = new ArrOperation();
        
        System.out.println("Enter the length of Array : ");
        
        int length = sc.nextInt();
        
        int[] arr = new int[length];
        
        System.out.println("Enter the elements of the Array : ");
        for(int i = 0 ; i < length; i++){
            arr[i] = sc.nextInt();
        }

        System.out.println("Maxmum Mirror Array : " + a1.maxMirror(arr));

        sc.close();
    }
}