package com.springboot.designpattern.pattern_factory_method;

public abstract class Product {
    //产品类的公共方法
    public void method1() {
    //业务逻辑处理
        System.out.println("dfw===");
    }

    //抽象方法
    public abstract void method2();
}