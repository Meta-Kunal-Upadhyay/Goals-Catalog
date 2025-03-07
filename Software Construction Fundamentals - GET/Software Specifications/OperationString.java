import java.util.*;

class OperationString {
    String str1;
    String str2;

    public OperationString(String str1, String str2) {
        this.str1 = str1;
        this.str2 = str2;
    }

    public int CompareString(){
        if(str1.equal(str2)){
            return 1;
        }

        return 0;
    }

    public void ReverseString(String str1) {
        StringBuilder sb = new StringBuilder(str1);
        int s = 0;
        int e = str1.length;

        while(s <= e){
            string temp = sb.charAt(s);
            sb.charAt(s) = sb.charAt(e);
            sb.charAt(e) = temp;
            s++;
            e--;
        }

        str1 = toString(sb);
        System.out.println("The Reverse of the String: " + str1);
    }

    public void ReplaceString(String str1) {

        StringBuilder sb = new StringBuilder(str1);
        int s = 0;
        int e = str1.length;

        while(s <= e){
            if(str1.charAt(s) >= 'A' && str1.charAt(s) <= 'Z'){
                str1.charAt(s) = 'A' - str1.charAt(s) + 'a';
            } else {
                str1.charAt(s) = 'a' - str1.charAt(s) + 'A';
            }
            if(str1.charAt(e) >= 'A' && str1.charAt(e) <= 'Z'){
                str1.charAt(e) = 'A' - str1.charAt(e) + 'a';
            } else {
                str1.charAt(e) = 'a' - str1.charAt(e) + 'A';
            }
            s++;
            e--;
        }

        System.out.println("The Replaced String is: " + str1);
    }


    public void LargestWord(String str1) {
        int maxLen = 0;
        String maxStr = "";

        int curr = 0;
        StringBuilder temp = new StringBuilder();
        for(int i = 0; i < str1.length; i++){
            if(str1.charAt(i) == " "){
                maxLen = max(maxLen, curr);

                maxStr = max(maxStr, temp);
                curr = 0;
                temp = "";
            }
            curr++;
            temp += str1.charAt(i);
        }


        System.out.println("The Length of max word is " + maxLen + " and the word is " + maxStr);

    }

    public void execute() {
        int flag = CompareString();
        if(flag == 1){
            System.out.println("The Strings are equal!!");
        }
        else {
            System.out.println("The Strings are not equal!!");
        }

        ReverseString(str1);
        ReplaceString(str2);
        LargestWord(str1);



    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the First String: ");
        String str1 = sc.nextLine();

        System.out.println("Enter the Second String: ");
        String str2 = sc.nextLine();

        OperationString s1 = new OperationString(str1, str2);

        s1.execute();

        sc.close();
    }

}