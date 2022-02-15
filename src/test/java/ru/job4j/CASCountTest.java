package ru.job4j;

import org.junit.Test;

import static org.junit.Assert.*;

public class CASCountTest {

    @Test
    public void whenIncrementAndGetOneTimeFrom4() {
        CASCount count = new CASCount(4);
        count.increment();
        assertEquals(5, count.get());
    }

    @Test
    public void whenIncrementAndGetTwoTimesFrom0() {
        CASCount count = new CASCount();
        count.increment();
        count.increment();
        assertEquals(2, count.get());
    }

    @Test
    public void whenIncrementAndGetThreeTimesFrom1() {
        CASCount count = new CASCount(1);
        count.increment();
        count.increment();
        count.increment();
        assertEquals(4, count.get());
    }

    @Test
    public void whenIncrementAndGetFourTimesFrom0() {
        CASCount count = new CASCount();
        int expected = count.get() + 4;
        count.increment();
        count.increment();
        count.increment();
        count.increment();
        assertEquals(expected, count.get());
    }
}