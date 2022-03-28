package ru.job4j.singleton.eager;

import ru.job4j.singleton.Item;

public class FinalFieldSingleton {

    private static final FinalFieldSingleton INSTANCE = new FinalFieldSingleton();

    private FinalFieldSingleton() {
    }

    public static FinalFieldSingleton getInstance() {
        return INSTANCE;
    }

    public Item add(Item model) {
        return model;
    }

    public static void main(String[] args) {
        FinalFieldSingleton tracker = FinalFieldSingleton.getInstance();
    }
}
