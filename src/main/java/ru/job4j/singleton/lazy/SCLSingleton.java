package ru.job4j.singleton.lazy;

import ru.job4j.singleton.Item;

public class SCLSingleton {

    private static SCLSingleton INSTANCE;

    private SCLSingleton() {
    }

    public static synchronized SCLSingleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SCLSingleton();
        }
        return INSTANCE;
    }

    public Item add(Item model) {
        return model;
    }

    public static void main(String[] args) {
        SCLSingleton tracker = SCLSingleton.getInstance();
    }
}
