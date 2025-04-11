import java.util.*;

public class OrganicChemistry{

    public static void evaluateMolecularWeight(Map<Character, Integer> formula, String input){
        int weight = 0;
        char[] arr = input.toCharArray();
                // i
        // OH(CH32)O
        // 01234567
        // weigth = 17
        // newWeight = 12 + 32 = 44 + 16 = 60;

        // count = 32
 
        for(int i = 0; i < arr.length-1; i++){
            int newWeight = 0;
            if(arr[i] == '('){
                i = i + 1;

                while(i < arr.length && arr[i] != ')'){
                    if(formula.containsKey(arr[i])){
                        if(arr[i+1] >= '0' && arr[i+1] <= '9'){
                            int count=0;
                            int j = i + 1;
                            while(j < arr.length && Character.isDigit(arr[j])){
                                count = count * 10 + (arr[j] - '0');
                                j++;
                            }
                            if(count == 0) count = 1;
                            int up = formula.get(arr[i]) * count;
                            newWeight += up;
                            i = j - 1;
                        }
                        else{
                            newWeight += formula.get(arr[i]);
                        }
                    }
                    
                    i++;
                }

                if(i + 1 < arr.length && arr[i + 1] >= '0' && arr[i + 1] <= '9'){
                    int count=0;
                    int j = i + 1;
                    while(j < arr.length && Character.isDigit(arr[j])){
                        count = count * 10 + (arr[j] - '0');
                        j++;
                    }
                    if(count == 0) count = 1;
                    int up = newWeight * count;
                    i = j - 1;
                    weight += up;
                }
                else{
                    weight += newWeight;
                }
                
            }


            else{
                if(arr[i+1] >= '0' && arr[i+1] <= '9'){
                    int count=0;
                    int j = i + 1;
                    while(j < arr.length && Character.isDigit(arr[j])){
                        count = count * 10 + (arr[j] - '0');
                        j++;
                    }
                    if(count == 0) count = 1;
                    int up = formula.get(arr[i]) * count;
                    weight += up;
                    i = j - 1;
                }
                else{
                    weight += formula.get(arr[i]);
                }
            }
            
        }

        if(arr[arr.length - 1] != ')' && (arr[arr.length - 1] == 'C' || arr[arr.length - 1] == 'H' || arr[arr.length - 1] =='O')){
            weight += formula.get(arr[arr.length - 1]);
        }

        System.out.println("The Molecular Weight of formula " + input + " is :- " + weight);
    }

    public static void main(String[] args) {
        Map<Character, Integer> formula = new HashMap<>();
        formula.put('O', 16);
        formula.put('H', 1);
        formula.put('C', 12);

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Chemical Formula :- ");
        String input = sc.nextLine();

        evaluateMolecularWeight(formula, input);


        sc.close();

    }
}

