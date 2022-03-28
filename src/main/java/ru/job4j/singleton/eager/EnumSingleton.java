package ru.job4j.singleton.eager;

import ru.job4j.singleton.Item;

public enum EnumSingleton {
    INSTANCE;

    public Item add(Item model) {
        return model;
    }

    public static void main(String[] args) {
        EnumSingleton tracker = EnumSingleton.INSTANCE;
    }
}
