package com.springboot.designpattern.pattern_composite;

public class MyFile extends Entry {
    private String name;
    private int size;

    public MyFile(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    protected void printList(String prefix) {
        System.out.println(prefix + "/" + this);
    }
}