package ru.job4j;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CountTest {

    private static class ThreadCount extends Thread {
        private final Count count;

        private ThreadCount(final Count value) {
            count = value;
        }

        @Override
        public void run() {
            count.increment();
        }
    }

    @Test
    public void whenExecute2ThreadThen2() throws InterruptedException {
        final Count count = new Count();
        Thread first = new ThreadCount(count);
        Thread second = new ThreadCount(count);
        first.start();
        second.start();
        first.join();
        second.join();
        assertEquals(count.getCount(), 2);

    }
}