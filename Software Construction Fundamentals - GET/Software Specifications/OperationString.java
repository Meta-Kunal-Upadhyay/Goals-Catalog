import java.util.*;

class OperationString {
    String str1;
    String str2;

    public OperationString(String str1, String str2) {
        this.str1 = str1;
        this.str2 = str2;
    }

    public int compareStrings(){
        if(str1.length() != str2.length()){
            return 0;
        }
        for(int i = 0; i < str1.length(); i++){
            if(str1.charAt(i) != str2.charAt(i)){
                return 0;
            }
        }

        return 1;
    }

    public void ReverseString(String str1) {
        // StringBuilder sb = new StringBuilder(str1);
        char[] arr = str1.toCharArray();
        int s = 0;
        int e = arr.length - 1;

        while(s < e){
            char temp = arr[s];
            arr[s] = arr[e];
            arr[e] = temp;
            s++;
            e--;
        }
        System.out.println("The Reverse of the String: " + new String(arr));
    }

    public void ReplaceString(String str1) {
        char[] arr = str1.toCharArray();

        for(int i = 0; i < arr.length; i++){
            if(arr[i] >= 'A' && arr[i] <= 'Z'){
                arr[i] = (char) (arr[i] + ('a' - 'A'));
            } 
            else if(arr[i] >= 'a' && arr[i] <= 'z'){
                arr[i] = (char) (arr[i] - ('a' - 'A'));
            }
        }

        System.out.println("The Replaced String is: " + new String(arr));
    }


    public void LargestWord(String str1) {
        int maxLen = 0;
        String maxStr = "";
        String curr = "";
        
        for(int i = 0; i <= str1.length(); i++){
            if(i < str1.length() && str1.charAt(i) != ' '){
                curr += str1.charAt(i);
            } else {
                if(curr.length() >= maxLen){
                    maxLen = curr.length();
                    maxStr = curr;
                }
                curr = "";
            }
        }
        System.out.println("The Length of max word is " + maxLen + " and the word is " + maxStr);

    }

    public void execute() {
        int flag = this.compareStrings();
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
        String str1 = sc.nextLine().trim();

        System.out.println("Enter the Second String: ");
        String str2 = sc.nextLine().trim();

        OperationString s1 = new OperationString(str1, str2);

        s1.execute();

        sc.close();
    }

}