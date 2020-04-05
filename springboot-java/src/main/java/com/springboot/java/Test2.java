package com.springboot.java;

public class Test2 {

    public static void main(String[] args) {
        Person[] people1 = new Person[]{new Person("1号"),new Person("2号"),new Person("3号"),new Person("4号")};
        Person[] people2 = new Person[4];
        System.arraycopy(people1, 0, people2, 0, 4);
        people1[0].name = "蝙蝠侠";
        System.out.println(people2[0].name);
    }
}


class Person {
    public String name;
    public Person(String name) {
        this.name = name;
    }
}