package ru.job4j.singleton.lazy;

import ru.job4j.singleton.Item;

public class DCLSingleton {

    private static volatile DCLSingleton INSTANCE;

    private DCLSingleton() {
    }

    public static DCLSingleton getInstance() {
        if (INSTANCE == null) {
            synchronized (DCLSingleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DCLSingleton();
                }
            }
        }
        return INSTANCE;
    }

    public Item add(Item model) {
        return model;
    }

    public static void main(String[] args) {
        DCLSingleton tracker = DCLSingleton.getInstance();
    }
}
