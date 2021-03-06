package ru.job4j.singleton.lazy;

import ru.job4j.singleton.Item;

public class SCLSingleton {

    private static SCLSingleton inst;

    private SCLSingleton() {
    }

    public static synchronized SCLSingleton getInstance() {
        if (inst == null) {
            inst = new SCLSingleton();
        }
        return inst;
    }

    public Item add(Item model) {
        return model;
    }

    public static void main(String[] args) {
        SCLSingleton tracker = SCLSingleton.getInstance();
    }
}
