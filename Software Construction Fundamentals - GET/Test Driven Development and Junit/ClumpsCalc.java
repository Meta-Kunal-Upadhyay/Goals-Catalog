import java.util.*;

class ClumpsCalc {
    private int[] clumps;
    private int length;

    ClumpsCalc(int[] arr){
        this.clumps = arr;
        this.length = arr.length;
    }

    // 1 1 2 1 1
    public int countClumps(){
        int clumpCnt = 0;
        int cnt = 1;
        for(int i = 0; i < length - 1; i++){
            if(clumps[i] != clumps[i+1]){
                if(cnt >= 2){
                    clumpCnt++;
                    cnt = 1;
                }
            }
            else {
                cnt++;
            }
        }

        if(cnt >= 2){
            clumpCnt++;
        }
        return clumpCnt;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        System.out.println("Enter the Length of the Array : ");
        int length = sc.nextInt();
        int[] arr = new int[length];

        System.out.println("Enter the Elements of the Array : ");
        for(int i = 0; i < length; i++){
            arr[i] = sc.nextInt();
        }

        ClumpsCalc c1 = new ClumpsCalc(arr);
        System.out.println("The number of clumps present is " + c1.countClumps());

        sc.close();

    }
}