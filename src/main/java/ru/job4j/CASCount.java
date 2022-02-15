package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count;

    public CASCount() {
        this.count = new AtomicReference<>(0);
    }

    public CASCount(int val) {
        this.count = new AtomicReference<>(val);
    }

    public void increment() {
        Integer value;
        do {
            value = count.get();
        } while (!count.compareAndSet(value, value + 1));
    }

    public int get() {
        return count.get();
    }
}