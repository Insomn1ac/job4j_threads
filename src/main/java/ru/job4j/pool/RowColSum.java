package ru.job4j.pool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RowColSum {
    public static class Sums {
        private int rowSum;
        private int colSum;

        public Sums(int rowSum, int colSum) {
            this.rowSum = rowSum;
            this.colSum = colSum;
        }

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }

        @Override
        public String toString() {
            return "Sums{"
                    + "rowSum=" + rowSum
                    + ", colSum=" + colSum
                    + '}';
        }
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] sum = new Sums[matrix.length];
        for (int i = 0; i < sum.length; i++) {
            sum[i] = new Sums(getRowSum(matrix, i), getColSum(matrix, i));
        }
        return sum;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] sum = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            int index = i;
            int row = CompletableFuture.supplyAsync(() -> getRowSum(matrix, index)).get();
            int col = CompletableFuture.supplyAsync(() -> getColSum(matrix, index)).get();
            sum[i] = new Sums(row, col);
        }
        return sum;
    }

    public static int getRowSum(int[][] matrix, int rowIndex) {
        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            sum += matrix[rowIndex][i];
        }
        return sum;
    }

    public static int getColSum(int[][] matrix, int colIndex) {
        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            sum += matrix[i][colIndex];
        }
        return sum;
    }
}
