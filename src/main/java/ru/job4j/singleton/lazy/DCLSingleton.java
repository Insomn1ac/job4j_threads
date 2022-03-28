package ru.job4j.singleton.lazy;

import ru.job4j.singleton.Item;

public class DCLSingleton {

    private static volatile DCLSingleton inst;

    private DCLSingleton() {
    }

    public static DCLSingleton getInstance() {
        if (inst == null) {
            synchronized (DCLSingleton.class) {
                if (inst == null) {
                    inst = new DCLSingleton();
                }
            }
        }
        return inst;
    }

    public Item add(Item model) {
        return model;
    }

    public static void main(String[] args) {
        DCLSingleton tracker = DCLSingleton.getInstance();
    }
}
