import java.util.Scanner;
class balancer {

    public static int balancerIndex(int[] nums){
        int len = nums.length;
        int leftPro = 1;
        int rightPro = 1;

        for(int i = 0; i < len; i++){
            try{
                if(nums[i] == 0){
                  throw new ArithmeticException();  
                }
                rightPro = rightPro * nums[i];
            }
            catch(ArithmeticException e){
                System.out.println("The Array Contains 0s");
                return -1;
            }
        }

        rightPro = rightPro / nums[0];
        for(int i = 1; i < len; i++){
            leftPro = leftPro * nums[i-1];
            rightPro = rightPro / nums[i];
            // System.out.println("rightPro = " + rightPro + " ,leftPro = " + leftPro);
            if(leftPro == rightPro) return i;
        }

        return -1;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try{
            System.out.println("Enter the length of the Array ");
            int len = sc.nextInt();
            if(len <= 0){
                System.out.println("Array Length must be positive.");
                return ;
            }
            
            System.err.println("Enter the elements of the Array ");
            int[] nums = new int[len];
            for(int i = 0; i < len; i++){
                nums[i] = sc.nextInt();
            }
            int ans = balancerIndex(nums);
            if(ans == -1){
                System.out.println("No balance index found.");
            } else {
                System.out.println("The balance index so that left product is equal to the right product is " + ans);
            }
        } catch (Exception e) {
            System.out.println("Invalid Input: " + e.getMessage());
        }


        finally {
            sc.close();
        }
    }
}