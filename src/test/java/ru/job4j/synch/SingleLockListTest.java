package ru.job4j.synch;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.*;

public class SingleLockListTest {

    @Test
    public void whenAddElementsInTwoDifferentThreads() throws InterruptedException {
        SingleLockList<Integer> list = new SingleLockList<>();
        Thread first = new Thread(() -> list.add(1));
        Thread second = new Thread(() -> list.add(2));
        first.start();
        second.start();
        first.join();
        second.join();
        Set<Integer> rsl = new TreeSet<>();
        list.iterator().forEachRemaining(rsl::add);
        assertEquals(rsl, Set.of(1, 2));
    }

    @Test
    public void whenGetElementInOneThreadAndAddInAnother() throws InterruptedException {
        List<Integer> src = List.of(1, 2, 3);
        SingleLockList<Integer> list = new SingleLockList<>(src);
        List<Integer> rsl = new ArrayList<>();
        Thread first = new Thread(() -> rsl.add(list.get(0)));
        Thread second = new Thread(() -> list.add(4));
        first.start();
        second.start();
        first.join();
        second.join();
        assertEquals(rsl, List.of(1));
        assertEquals(list.get(3), Integer.valueOf(4));
    }
}