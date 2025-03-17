import java.util.*;
class ArrOperations{
    public void isEmpty(int[] array){
        int n=array.length;
        if(n==0 || array==null){
            throw new AssertionError("Error");
        }
    }

    public void count(int[] array,int X,int Y){
        int countX=0;
        int countY=0;
        for(int i=0; i<array.length; i++){
            if(array[i]==X){
                countX++;
            }
            if(array[i]==Y){
                countY++;
            }
        }
        if(countX!=countY){
            throw new AssertionError("count of  X and Y are not equal ");
        }
    }
    public void adjacentsX(int[] array, int X){
        for(int i=0; i<array.length; i++){
            if(array[i]==X && array[i+1]==X){
                throw new AssertionError("two adjacents X values are there");
            }
        }
    }

    public void xOccursAtLast(int[] array, int X){
        int n = array.length;
        int last = array[n-1];
        if(last==X){
          throw new AssertionError("X occure at last index");
        }
    }

    public int maxMirror(int [] array){
        int max_size=0;
        isEmpty(array);
        for(int i=0; i<array.length; i++){
            for(int j=array.length-1; j>=0; j--){
                int start=i;
                int end=j;
                int size=0;
                while(start<array.length && end>=0 && array[start]==array[end]){
                    size++;
                    start++;
                    end--;
                }
                if(size>max_size){
                    max_size=size;
                }
            }
        }
        return max_size;
    }


    public int clumpsMax(int [] array){

        int maxCnt=0;
        int count=1;
        isEmpty(array);
        
        for(int i=0;i<array.length-1; i++){
            if(array[i] != array[i+1]){
                if(count >= 2){
                    maxCnt++;
                    count = 1;
                }
            }
            else{
                count++;
            }
        }

        if(count >= 2){
            maxCnt++;
        }
       return maxCnt;

    }



    public int[] fixXY(int[] array, int X, int Y){
        int i=0;
        int j=0;
              isEmpty(array);
        count(array, X, Y);
        adjacentsX(array, X); 
        xOccursAtLast(array, X);
        while(i<array.length-1 && j<array.length){
            if(array[j]==X && array[j+1]==Y) j+=2;
            
            while(array[i]!=X && i<array.length){             
                  i++;
            }
            while (array[j]!=Y && j<array.length) {    
                j++;
              }
              
              int temp=array[i+1];
              array[i+1]=array[j];
              array[j]=temp;
        
              i++; 
              j++;
        }
    
        return array;

    }

    public int SplitArray(int[] array){

       isEmpty(array);
        int leftSum=0;
        for(int i=0; i<array.length; i++){
            leftSum+=array[i];
            int rightSum=0;
            
            for(int j=i+1; j<array.length; j++){
                rightSum+=array[j];
            }
            if(leftSum==rightSum){
                return i+1;
            }
        }
        return -1;
    }



   public static void main(String[] args){
    Scanner sc= new Scanner(System.in);
    ArrOperation operation = new ArrOperation();


    while (true) {
        System.out.println("Array operation");
        System.out.println("1. Largest mirror section");
        System.out.println("2. Number of clumps in the input array");
        System.out.println("3. FixXY problem");
        System.out.println("4. SplitArray");
        System.out.println("5. Exit");
    
        int ch = sc.nextInt();
        switch (ch) {
            case 1:
                System.out.println("Enter the size of array");
                int n= sc.nextInt();
                int[] array=new int[n];
                for(int i=0 ; i<n; i++){
                    array[i]=sc.nextInt();
                }
            
                System.out.println("Maxmum Mirror array " + operation.maxMirror(array));
                break;

            case 2:
            System.out.println("Enter the size of array");
            int n1= sc.nextInt();
            int [] array1 = new int[n1];
            for(int i=0; i< n1; i++){
                array1[i]=sc.nextInt();
            } 
            System.out.println("Maximum Clums length: " + operation.clumpsMax(array1));
                break;

            case 3:
            System.out.println("Enter the size of array");
            int n2= sc.nextInt();
            int[] array2 = new int[n2];
            for(int i=0; i<n2; i++){
                array2[i] = sc.nextInt();
            }
            System.out.println("enter X");
            int X = sc.nextInt();
            System.out.println("enter Y");
            int Y = sc.nextInt();
            int[] result=operation.fixXY(array2, X, Y);
            System.out.println("rearrange array is: ");
            for(int i=0; i<result.length; i++){
                System.out.print(result[i] + "  ");
            }
                break;

            case 4:
            System.out.println("Enter the size of array");
            int n3= sc.nextInt();
            int[] array3 = new int[n3];
            System.out.println("Enter element of array");
             for(int i=0; i<n3; i++){
                array3[i] = sc.nextInt();
             }
             System.out.println("Split Array: " + operation.SplitArray(array3));
                break;

            case 5:
                sc.close();
                System.exit(0);
            
            default:
                System.out.println("Invalid Choice");

   }
}
}
}