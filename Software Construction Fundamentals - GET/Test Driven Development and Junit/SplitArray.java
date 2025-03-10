import java.util.*;

class SplitArray {
    private int[] nums;
    private int length;

    SplitArray(int[] arr) {
        this.nums = arr;
        this.length = arr.length;
    }

    public int splitTheArray() {
        // int[] prefixSum = new int[length + 1];
        int totalSum = 0;
        // prefixSum[i] = 0;

        for(int i = 0; i < length; i++){
            totalSum += nums[i];
            // prefixSum[i + 1] = prefixSum[i] + nums[i]; 
        }

        int currSum = 0;
        for(int i = 0; i < length; i++){
            currSum += nums[i];
            if(currSum == (totalSum - currSum)){
                return i + 1;
            }
        }

        return -1;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the length of the array : ");
        int length = sc.nextInt();

        int[] arr = new int[length];

        System.out.println("Enter the elements of the Array : ");
        
        for(int i = 0; i < length; i++){
            arr[i] = sc.nextInt();    
        }

        SplitArray s1 = new SplitArray(arr);

        int ans = s1.splitTheArray();
        if(ans == -1){
            System.out.println("There is no way to divide the array so that sum is equal both the side");
        }
        else{
            System.out.println("The array can be divide from the index : " + ans + " to get the sum equal both the side.");
        }

        sc.close();
    }
}