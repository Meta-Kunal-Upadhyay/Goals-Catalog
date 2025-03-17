import java.util.Arrays;

final class Intset {
    private int[] set;

    Intset(int[] arr) {
        set = new int[1001];
        for (int x : arr) {
            set[x] = 1;
        }
    }

    public int[] getSet() {
        return set;
    }

    public boolean isMember(int val) {
        return set[val] > 0;
    }

    public int arrSize() {
        int count = 0;
        for (int i = 1; i < 1001; i++) {
            count += set[i];
        }
        return count;
    }

    public boolean isSubSet(Intset s) {
        for (int i = 1; i < 1001; i++) {
            if (set[i] == 0 && s.set[i] > 0) {
                return false;
            }
        }
        return true;
    }

    Intset getComplement() {
        int[] temp = new int[1001];
        int count = 0;

        for (int i = 1; i < 1001; i++) {
            if (set[i] == 0) { 
                temp[count++] = i;
            }
        }

        int[] complementArr = new int[count];
        for (int i = 0; i < count; i++) {
            complementArr[i] = temp[i];
        }

        return new Intset(complementArr);
    }

    Intset getUnion(Intset s) {
        int[] temp = new int[1001];
        int count = 0;

        for (int i = 1; i < 1001; i++) {
            if (set[i] > 0 || s.set[i] > 0) {
                temp[count++] = i;
            }
        }

        int[] unionArr = new int[count];
        for (int i = 0; i < count; i++) {
            unionArr[i] = temp[i];
        }

        return new Intset(unionArr);
    }

}

public class IntsetMain {
    public static void main(String[] args) {
        int[] arr1 = { 2, 4, 6, 8, 10 };
        int[] arr2 = { 1, 2, 3, 4, 5 };

        Intset s1 = new Intset(arr1);
        Intset s2 = new Intset(arr2);

        System.out.println("Is 2 in set s1: " + s1.isMember(2));
        System.out.println("Size of s1: " + s1.arrSize());
        System.out.println("Is s2 a subset of s1 " + s1.isSubSet(s2));

        System.out.print("Complement of s1: ");
        Intset complement = s1.getComplement();
        for (int i = 1; i < 1001; i++) {
            if (complement.getSet()[i] > 0) {
                System.out.print(i + " ");
            }
        }
        System.out.println();

        System.out.print("Union of s1 and s2: ");
        Intset union = s1.getUnion(s2);
        for (int i = 1; i < 1001; i++) {
            if (union.getSet()[i] > 0) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }
}