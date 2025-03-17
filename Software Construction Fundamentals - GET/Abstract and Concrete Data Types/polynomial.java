final class poly {

    private final int[] arr1;

    public poly(int[] arr1){
        this.arr1 = arr1;
    }

    public float evaluate(int[] arr, float val) {
        float result = 0;
        for (int i = 0; i < arr.length; i++) {
            result += arr[i] * Math.pow(val, i);
        }
        return result;
    }

    public int degree(int[] arr) {
        int degree1 = arr.length - 1;
        while (degree1 >= 0 && arr[degree1] == 0) {
            degree1--;
        }
        return degree1;
    }

    public int[] addPoly(int[] arr, int[] arr1) {
        int maxLength = Math.max(arr.length, arr1.length);
        int[] result = new int[maxLength];

        for (int i = 0; i < arr.length; i++) {
            result[i] += arr[i];
        }

        for (int i = 0; i < arr1.length; i++) {
            result[i] += arr1[i];
        }
        return result;
    }

    public int[] multiplyPoly(int[] arr, int[] arr2) {
        int maxLength = (arr.length - 1) + (arr2.length - 1);
        int[] result = new int[maxLength + 1];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr2.length; j++) {
                result[i + j] += arr[i] * arr2[j];
            }
        }
        return result;
    }

}

public class polynomial {
    public static void main(String[] args) {
        int[] arr = { 3, 2, 1 };
        int[] arr2 = { 1, 1, 2 };
        poly p = new poly(arr);
        System.out.println("Evaluated value of polynommial " + p.evaluate(arr, 2));
        System.out.println("Degree of  polynomial: " + p.degree(arr2));

        System.out.println("Addition of two polynomial: ");

        int[] sum = p.addPoly(arr, arr2);
        for (int i = sum.length - 1; i >= 0; i--) {
            if (sum[i] != 0) {
                System.out.print(sum[i] + "x^" + i + " ");
            }
        }
        System.out.println();

        System.out.println("Multiplication of two polynomial: ");
        int[] product = p.multiplyPoly(arr, arr2);
        for (int i = product.length - 1; i >= 0; i--) {
            if (product[i] != 0) {
                System.out.print(product[i] + "x^" + i + " ");
            }
        }
    }

}
