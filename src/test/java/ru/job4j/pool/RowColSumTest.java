package ru.job4j.pool;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

public class RowColSumTest {
    @Test
    public void whenSquareMatrixAndNotAsync() {
        int[][] matrix = new int[][] {{1, 2}, {3, 4}};
        String expected = "[Sums{rowSum=3, colSum=4},"
                + " Sums{rowSum=7, colSum=6}]";
        RowColSum.Sums[] rsl = RowColSum.sum(matrix);
        assertEquals(expected, Arrays.toString(rsl));
    }

    @Test
    public void whenSquareMatrixAndAsync() throws ExecutionException, InterruptedException {
        int[][] matrix = new int[][] {{1, 2}, {3, 4}};
        String expected = "[Sums{rowSum=3, colSum=4},"
                + " Sums{rowSum=7, colSum=6}]";
        RowColSum.Sums[] rsl = RowColSum.asyncSum(matrix);
        assertEquals(expected, Arrays.toString(rsl));
    }
}