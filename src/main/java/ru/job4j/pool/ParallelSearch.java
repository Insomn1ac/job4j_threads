package ru.job4j.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelSearch<V> extends RecursiveTask<Integer> {
    private final V[] array;
    private final int from;
    private final int to;
    private final V target;

    public ParallelSearch(V[] array, int from, int to, V target) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.target = target;
    }

    @Override
    protected Integer compute() {
        if (to - from <= 10) {
            for (int i = 0; i < array.length; i++) {
                if (target.equals(array[i])) {
                    return i;
                }
            }
            return -1;
        }
        int mid = (from + to) / 2;
        ParallelSearch<V> leftSearch = new ParallelSearch<>(array, from, mid, target);
        ParallelSearch<V> rightSearch = new ParallelSearch<>(array, mid + 1, to, target);
        leftSearch.fork();
        rightSearch.fork();
        int left = leftSearch.join();
        int right = rightSearch.join();
        return Math.max(left, right);
    }

    public static <V> int search(V[] arr, V target) {
        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(new ParallelSearch<>(arr, 0, arr.length - 1, target));
    }
}
