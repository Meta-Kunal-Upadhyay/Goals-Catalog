import java.util.Scanner;

public class LcmHcfRecursion {

    public int hcf(int num1, int num2){
        if(num2 == 0){ return num1;}

       return hcf(num2, num1%num2);
    }

    public int lcm(int num1, int num2){
        return (num1*num2)/hcf(num1, num2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LcmHcfRecursion LcmHcf = new LcmHcfRecursion();
        System.out.println("Enter two number");
        int x=sc.nextInt();
        int y=sc.nextInt();

        if(x > y){
            System.out.println("HCF of number is: " + LcmHcf.hcf(y,x));
        }
        else{
            System.out.println("HCF of number is: " + LcmHcf.hcf(x,y));
        }
        System.out.println("LCM of number is: " + LcmHcf.lcm(x,y));

        sc.close();
    }
}
