package com.springboot.designpattern.pattern_factory_method;

public class Client {
    public static void main(String[] args) {
        Creator creator = new ConcreteCreator();
        Product product1 = creator.createProduct(ConcreteProduct1.class);
        /*
         * 继续业务处理
         */
        product1.method2();

        Product product2 = creator.createProduct(ConcreteProduct1.class);
        product2.method2();
    }
}