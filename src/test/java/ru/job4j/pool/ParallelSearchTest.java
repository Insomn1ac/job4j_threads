package ru.job4j.pool;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParallelSearchTest {
    @Test
    public void whenIntArrayWithLengthLessThan10() {
        Integer[] arr = new Integer[] {40, 21, 54, 6, 87, 2, 65, 2, 6};
        int target = 87;
        int expected = 4;
        int rsl = ParallelSearch.search(arr, target);
        assertEquals(expected, rsl);
    }

    @Test
    public void whenIntArrayWithLengthMoreThan10() {
        Integer[] arr = new Integer[] {40, 21, 54, 6, 87, 2, 65, 2, 16, 53, 77};
        int target = 16;
        int expected = 8;
        int rsl = ParallelSearch.search(arr, target);
        assertEquals(expected, rsl);
    }

    @Test
    public void whenIntArrayWithoutTargetElementThenMinus1() {
        Integer[] arr = new Integer[] {40, 21, 54, 6, 87, 2, 65, 2, 16, 53, 77};
        int target = 18;
        int expected = -1;
        int rsl = ParallelSearch.search(arr, target);
        assertEquals(expected, rsl);
    }

    @Test
    public void whenStringArrayWithLengthLessThan10() {
        String[] arr = new String[] {"мама", "мыла", "раму"};
        String target = "мама";
        int expected = 0;
        int rsl = ParallelSearch.search(arr, target);
        assertEquals(expected, rsl);
    }
}