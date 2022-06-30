package com.upv.pm_2022.iti_27849_u2_monreal_romero_juan_carlos;

import java.util.ArrayList;

/**
 * @author jmonrealr
 * Some functions and description of them has been taken by the source:
 *  Refer to <a href="https://www.geeksforgeeks.org/">GeeksforGeeks</a>
 */
public class MatrixOperations {

    double[][] Mat;
    static ArrayList<double[][]> listOfInverse = null;
    static ArrayList<String> steps;

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
    public int determinantInt(int[][] mat, int n) {
        int result = 0;

        int [][]temp = new int[3][3];
        int sign = 1;

        for (int i = 0; i < 3; i++) {
            getCoFactor(mat, temp, 0, i, n);
            result += sign * mat[0][i] * determinantInt(temp, n-1);
            sign = -sign;
        }
        return result;
    }

    /**
     * In this approach, we are iterating every diagonal element and
     * making all the elements down the diagonal as zero using determinant properties
     * @param mat Matrix
     * @param n Size of the matrix
     * @return
     */
    int determinantOfMatrix(int mat[][], int n){
        int num1, num2, det = 1, index, total = 1;

        int[] temp = new int[n + 1];

        for (int i = 0; i < n; i++){
            index = i;

            while (index < n && mat[index][i] == 0) {
                index++;
            }

            // if there is non zero element
            if (index == n){
                // the determinant of matrix as zero
                continue;
            }
            if (index != i){
                // loop for swapping the diagonal element row
                // and index row
                for (int j = 0; j < n; j++){
                    swap(mat, index, j, i, j);
                }
                // determinant sign changes when we shift
                // rows go through determinant properties
                det = (int)(det * Math.pow(-1, index - i));
            }

            // storing the values of diagonal row elements
            for (int j = 0; j < n; j++){
                temp[j] = mat[i][j];
            }

            // traversing every row below the diagonal
            for (int j = i + 1; j < n; j++){
                num1 = temp[i]; // value of diagonal element
                num2 = mat[j][i]; // value of next row element

                // traversing every column of row
                // and multiplying to every row
                for (int k = 0; k < n; k++){
                    // multiplying to make the diagonal
                    // element and next row element equal
                    mat[j][k] = (num1 * mat[j][k])
                                - (num2 * temp[k]);
                }
                total = total * num1; // Det(kA)=kDet(A);
            }
        }
        // multiplying the diagonal elements to get
        // determinant
        for (int i = 0; i < n; i++){
            det = det * mat[i][i];
        }
        return (det / total); // Det(kA)/k=Det(A);
    }

    /**
     * Utility function to swap the values of any matrix
     * @param arr Matrix
     * @param i1 First row index
     * @param j1 First column index
     * @param i2 Second row index
     * @param j2 Second column index
     * @return
     */
    int[][] swap(int[][] arr, int i1, int j1, int i2, int j2){
        int temp = arr[i1][j1];
        arr[i1][j1] = arr[i2][j2];
        arr[i2][j2] = temp;
        return arr;
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
                adj[j][i] = (sign)*(determinantInt(temp, 3-1));
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
        int det = determinantInt(mat, 3);
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
     * The inverse of a matrix A is a matrix that, when multiplied by the original matrix,
     * the result is an identity matrix. The notation used to refer to an inverse matrix is a ^-1 ,
     * or what is the same, raising the matrix to -1.
     * @param matrix Matrix
     * @return
     */
    public double[][] inverse(double[][] matrix) {
        listOfInverse = new ArrayList<>();
        steps = new ArrayList<>();
		double[][] inverse = new double[matrix.length][matrix.length];
        listOfInverse.add(inverse);
		// minors and cofactors
		for (int i = 0; i < matrix.length; i++)
			for (int j = 0; j < matrix[i].length; j++) {
                steps.add(String.valueOf(Math.pow(-1, i + j) * newDeterminant(minor(matrix, i, j))));
                inverse[i][j] = Math.pow(-1, i + j)
                        * newDeterminant(minor(matrix, i, j));
            }
       listOfInverse.add(inverse);
		// adjugate and determinant
		double det = 1.0 / newDeterminant(matrix);
		for (int i = 0; i < inverse.length; i++) {
			for (int j = 0; j <= i; j++) {
				double temp = inverse[i][j];
                steps.add(String.valueOf(inverse[j][i] * det));
				inverse[i][j] = inverse[j][i] * det;
                steps.add(String.valueOf(temp*det));
				inverse[j][i] = temp * det;

			}
		}
        listOfInverse.add(inverse);

		return inverse;
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
                    result[i][j] += matA[i][k] * matB[k][j];
                }
            }

        }
        return result;
    }

    /**
     * The determinant of a matrix A is denoted det(A), det A, or |A|.
     * @param matrix
     * @return
     */
    public double newDeterminant(double[][] matrix) {
		if (matrix.length != matrix[0].length)
			throw new IllegalStateException("invalid dimensions");

		if (matrix.length == 2)
			return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];

		double det = 0;
		for (int i = 0; i < matrix[0].length; i++)
			det += Math.pow(-1, i) * matrix[0][i]
					* newDeterminant(minor(matrix, 0, i));
		return det;
	}

    /**
     * A minor of a matrix A is the determinant of some smaller square matrix,
     * cut down from A by removing one or more of its rows and columns.
     * Minors obtained by removing just one row and one column from square matrices (first minors)
     * are required for calculating matrix cofactors, which in turn are useful for computing both
     * the determinant and inverse of square matrices.
     * @param matrix
     * @param row
     * @param column
     * @return
     */
    public double[][] minor(double[][] matrix, int row, int column) {
		double[][] minor = new double[matrix.length - 1][matrix.length - 1];

		for (int i = 0; i < matrix.length; i++)
			for (int j = 0; i != row && j < matrix[i].length; j++)
				if (j != column)
					minor[i < row ? i : i - 1][j < column ? j : j - 1] = matrix[i][j];
		return minor;
	}




    /**
     * @deprecated not implemented yet
     *
     * @param matA Matrix A
     * @param matB Matrix B
     * @return
     */
    public double[][] div(int [][]matA, int [][]matB){
        double[][] result = new double[3][3];
        return result;
    }
}
