package ru.job4j.cache;

public class Base {
    private final int id;
    private int version;
    private String name;

    public Base(int id, int version) {
        this.id = id;
        this.version = version;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion() {
        this.version = version + 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}