package ru.job4j.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public Base get(Base model) {
        return memory.get(model.getId());
    }

    public boolean update(Base model) {
        return memory.computeIfPresent(model.getId(), (key, value) -> {
            if (value.getVersion() != model.getVersion()) {
                throw new OptimisticException("Versions are not equal");
            }
            value.setName(model.getName());
            value.setVersion();
            return value;
        }) != null;
    }

    public void delete(Base model) {
        memory.remove(model.getId(), model);
    }
}
