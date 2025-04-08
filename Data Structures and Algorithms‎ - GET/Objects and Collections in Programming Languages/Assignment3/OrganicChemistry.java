import java.util.*;

public class OrganicChemistry {

    public static void evaluateMolecularWeight(Map<Character, Integer> formula, String input){
        int weight = 0;
        char[] arr = input.toCharArray();

        for(int i = 0; i < arr.length-1; i++){
            if(arr[i] == '('){
                int newWeight = 0;
                i = i + 1;

                while(i < arr.length && arr[i] != ')'){
                    if(arr[i + 1] >= '0' && arr[i + 1] <= '9'){
                        int up = formula.get(arr[i]) * (arr[i+1] - '0');
                        newWeight += up;
                        i = i + 1;
                    }
                    else{
                        newWeight += formula.get(arr[i]);
                    }
                    i++;
                }

                if(i < arr.length && arr[i + 1] >= '0' && arr[i + 1] <= '9'){
                    newWeight = newWeight * (arr[i+1] - '0');
                    i++;
                }
                
                weight += newWeight;
            }


            else{
                if(arr[i+1] >= '0' && arr[i+1] <= '9'){
                    weight += formula.get(arr[i]) * (arr[i+1] - '0');
                    i = i + 1;
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