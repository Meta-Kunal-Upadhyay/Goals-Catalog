public class DegreeMultivariablePoly {
    public static int findDegree(int[][] terms) {
        int maxDegree = 0;
        for (int[] term : terms) {
            int degree = 0;
            for (int i = 1; i < term.length; i++) degree += term[i];
            maxDegree = Math.max(maxDegree, degree);
        }
        return maxDegree;
    }

    public static void main(String[] args){
        int[][] polynomial = { {3, 2, 4}, {-5, 0, 2}, {1, 1, 0}, {-1, 0, 1}, {20, 0, 0} };
        System.out.println("Degree of polynomial: " + findDegree(polynomial));
        int[][] polynomial1 = { {0, 5, 0}, {3, 4, 1}, {2, 1, 3}, {4, 0, 2}, {-2, 0, 0}, {1,0,0}};
        System.out.println("Degree of polynomial: " + findDegree(polynomial1));

    }
}


// 3x^2y^4z^6 - 5y^2 + 1x^1 - 1y^1 + 20 
