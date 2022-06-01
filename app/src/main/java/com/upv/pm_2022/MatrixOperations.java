package com.upv.pm_2022;

/**
 * @author jmonrealr
 * Some functions and description of them has been taken by the source:
 *  Refer to <a href="https://www.geeksforgeeks.org/">GeeksforGeeks</a>
 */
public class MatrixOperations {

    double[][] Mat;

    public MatrixOperations() { Mat = new double[3][3]; }

    /**
     * The value of the determinant of a matrix can be calculated by the following procedure –
     *  For each element of the first row or first column get the cofactor of those elements
     *  and then multiply the element with the determinant of the corresponding cofactor,
     *  and finally add them with alternate signs. As a base case, the value of the determinant
     *  of a 1*1 matrix is the single value itself.
     * @param mat Matrix
     * @param n Size of the matrix
     * @return
     */
    public int determinant(int[][] mat, int n) {
        int result = 0;

        int [][]temp = new int[3][3];
        int sign = 1;

        for (int i = 0; i < 3; i++) {
            getCoFactor(mat, temp, 0, i, n);
            result += sign * mat[0][i] * determinant(temp, n-1);
            sign = -sign;
        }
        return result;
    }

    /**
     * The following four simple steps are helpful to find the co-factor matrix of the given matrix.
     * - First, find the minor of each element of the matrix by excluding the row and column of
     *   that particular element, and then taking the remaining part of the matrix.
     * - Secondly, find the minor element value by taking the determinant of the remaining part
     *   of the matrix.
     * - The third step involves finding the co-factor of the element by multiplying the minor
     *   of the element with -1 to the power of position values of the element.
     * - The third step involves finding the co-factor of the element by multiplying the minor
     *   of the element with -1 to the power of position values of the element.
     *
     * Refer to <a href="https://www.cuemath.com/algebra/cofactor-matrix/">Cofactor</a>
     * @param mat matrix
     * @param temp temporal matrix
     * @param p actual index for row
     * @param q actual index for column
     */
    private void getCoFactor(int[][] mat, int[][] temp, int p, int q, int n) {
        int i = 0, j = 0;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++){
                if (row != p && col != q){
                    temp[i][j++] = mat[row][col];
                    // Row is filled, so increase row index and
                    // reset col index
                    if (j == 3 - 1){
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }

    /**
     * The adjoint of a matrix is calculated by taking the transpose of a cofactor matrix.
     * Therefore, if we take the cofactor of a matrix A and then take its transpose,
     * the transposed matrix is considered the adjoint of A
     * @param mat matrix to be used
     * @param adj matrix to store the result
     */
    public void adjoint(int[][] mat, int[][] adj){
        int sign = 1;
        int[][] temp = new int[3][3];
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                // Get cofactor of A[i][j]
                getCoFactor(mat, temp, i, j, 3);

                // sign of adj[j][i] positive if sum of row
                // and column indexes is even.
                sign = ((i + j) % 2 == 0)? 1: -1;

                // Interchanging rows and columns to get the
                // transpose of the cofactor matrix
                adj[j][i] = (sign)*(determinant(temp, 3-1));
            }
        }
    }

    /**
     * The inverse of a matrix A is a matrix that, when multiplied by the original matrix,
     * the result is an identity matrix. The notation used to refer to an inverse matrix is a ^-1 ,
     * or what is the same, raising the matrix to -1.
     * @param mat Matrix
     * @return
     */
    public boolean inverse(int[][] mat) {
        int det = determinant(mat, 3);
        if ( det != 0){
            int [][]adj = new int[3][3];
            adjoint(mat, adj);
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    Mat[i][j] = adj[i][j]/(double) det;
                }
                
            }
        } else
            return false;
        return true;
    }

    /**
     * The addition of matrices can be carried out as long as the dimensions of the matrices
     * are the same, that is, both matrices must have the same number of rows and columns.
     * To add two matrices, we add the numbers of each matrix that are in the same position,
     * i.e. C = A + B with c_(i, j) = a_(i, j) + b_(i, j).
     * @param matA Matrix A
     * @param matB Matrix B
     * @return
     */
    public int[][] addition(int [][]matA, int [][]matB){
        int[][] result = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result[i][j] = matA[i][j] + matB[i][j];
            }

        }
        return result;
    }

    /**
     * Matrix subtraction is performed similarly to addition. If both matrices have the same
     * dimensions, we subtract the elements of the second matrix from the elements of the
     * first matrix that are in the same position,
     * i.e. C = A - B with c_(i, j) = a_(i, j) - b_(i, j) .
     * @param matA Matrix A
     * @param matB Matrix B
     * @return
     */
    public int[][] substraction(int [][]matA, int [][]matB){
        int[][] result = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result[i][j] = matA[i][j] - matB[i][j];
            }

        }
        return result;
    }

    /**
     * We can multiply a matrix (A) by another matrix (B) if the number of columns in A is equal
     * to the number of rows in B. Each element c_(i,j) of the resulting matrix is obtained
     * by multiplying each element in row i of matrix A by each element in column j of
     * matrix B and adding them together.
     * @param matA Matrix A
     * @param matB Matrix B
     * @return
     */
    public int[][] multiply(int [][]matA, int [][]matB){
        int[][] result = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result[i][j] = 0;
                for (int k = 0; k < 3; k++) {
                    result[i][j] += matA[i][k] + matB[k][j];
                }
            }

        }
        return result;
    }

    public double[][] div(int [][]matA, int [][]matB){
        double[][] result = new double[3][3];
        return result;
    }
}
