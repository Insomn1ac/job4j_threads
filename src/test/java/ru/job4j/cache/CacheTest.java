package ru.job4j.cache;

import org.junit.Test;

import static org.junit.Assert.*;

public class CacheTest {

    @Test
    public void whenAddModelToMap() {
        Cache cache = new Cache();
        Base base = new Base(1, 11);
        cache.add(base);
        assertEquals(11, cache.get(base).getVersion());
    }

    @Test
    public void whenDeleteModelFromMap() {
        Cache cache = new Cache();
        Base base = new Base(1, 11);
        cache.add(base);
        assertEquals(11, cache.get(base).getVersion());
        cache.delete(base);
        assertNull(cache.get(base));
    }

    @Test
    public void whenUpdateModelInMap() {
        Cache cache = new Cache();
        Base base = new Base(1, 11);
        cache.add(base);
        assertEquals(11, cache.get(base).getVersion());
        cache.update(base);
        assertEquals(12, cache.get(base).getVersion());
    }

    @Test (expected = OptimisticException.class)
    public void whenUpdateModelInMapWhenVersionsAreDifferent() {
        Cache cache = new Cache();
        Base base = new Base(1, 11);
        Base base2 = new Base(1, 12);
        cache.add(base);
        cache.update(base2);
    }
}