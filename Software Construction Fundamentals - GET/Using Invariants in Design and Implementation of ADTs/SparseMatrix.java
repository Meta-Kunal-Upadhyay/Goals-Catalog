final class SparseMatrix {
    
    public int[][] isTranspose(int[][] mat){
        int row = mat.length;
        int col = mat[0].length;
        int[][] res = new int[col][row];

        for(int i = 0;  i < row; i++){
            for(int j = 0; j < col; j++){
                res[j][i] = mat[i][j];
            }
        }

        return res;
    }

    public boolean isSymmetrical(int[][] mat){
        int row = mat.length;
        int col = mat[0].length;
        
        if(row != col) return false;

        int[][] trans = isTranspose(mat);

        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(mat[i][j] != trans[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    // public int[][] isAddition(int[][] mat1, int[][] mat2){
    //     int row1 = mat1.length;
    //     int col1 = mat1[0].length;

    //     int row2 = mat2.length;
    //     int col2 = mat2[0].length;

    //     if(row1 != row2 || col1 != col2) return new int[0][0];

    //     int[][] res = new int[row1][col1];
    //     for(int i = 0; i < row1; i++){
    //         for(int j = 0; j < col1; j++){
    //             res[i][j] = mat1[i][j] + mat2[i][j];
    //         }
    //     }

    //     return res;
    // }


    public int[][] isAddition(int[][] mat1, int[][] mat2){
        int[][] res = new int[3][3];
        System.out.println(mat1[0].length);

        for(int i = 0; i < mat1[0].length; i++){
            res[(mat1[0][i])][(mat1[1][i])] = mat1[2][i];
        
        }
        
        for(int i = 0; i < mat2[0].length; i++){
            res[(mat2[0][i])][(mat2[1][i])] += mat2[2][i];
        }

        return res;
    }

    public int[][] findMultiplication(int[][] mat1, int[][] mat2){
        int row1 = mat1.length;
        int col1 = mat1[0].length;

        int row2 = mat2.length;
        int col2 = mat2[0].length;

        if(col1 != row2) {
            System.out.println("Multiplication is not Possible!!");
            return new int[0][0];
        }

        int[][] res = new int[row1][col2];

        for (int i = 0; i < row1; i++) {
            for (int j = 0; j < col2; j++) {
                for (int k = 0; k < col1; k++) {
                    res[i][j] += mat1[i][k] * mat2[k][j];
                }
            }
        }

        return res;
    }

    public void printMatrix(int[][] mat){
        int row = mat.length;
        int col = mat[0].length;

        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                System.out.print(+ mat[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int[][] compactM(int[][] mat1){
        int size = 0;
        for(int i = 0; i < mat1.length; i++){
            for(int j = 0; j < mat1[0].length; j++){
                if(mat1[i][j] != 0){
                    size++;
                }
            }
        }
        int compactMatrix[][] = new int[3][size];

        int k = 0;
        for (int i = 0; i < mat1.length; i++) 
        {
            for (int j = 0; j < mat1[0].length; j++)
            {
                if (mat1[i][j] != 0) 
                {
                    compactMatrix[0][k] = i;
                    compactMatrix[1][k] = j;
                    compactMatrix[2][k] = mat1[i][j];
                    k++;
                }
            }
        }

        return compactMatrix;
    }

    public static void main(String[] args) {
        int[][] mat1 = {{0,0,0}, {5,7,8}, {22,25,18}};
        int[][] mat2 = {{1,1,1}, {2,2,2}, {0,0,0}}; 

        
        
        SparseMatrix s1 = new SparseMatrix();
        int[][] mat1U = s1.compactM(mat1);
        int[][] mat2U = s1.compactM(mat2);
        s1.printMatrix(mat1U);
        s1.printMatrix(mat2U);
        
        int[][] addd = s1.isAddition(mat1U, mat2U);
        s1.printMatrix(addd);
        
        // int[][] arr = s1.isTranspose(mat1);
        // s1.printMatrix(arr);

        // int[][] updated = s1.isAddition(mat1U, mat2U);
        // s1.printMatrix(updated);

    }
}
