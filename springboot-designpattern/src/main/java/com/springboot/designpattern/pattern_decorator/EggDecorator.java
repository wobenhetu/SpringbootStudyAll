package com.springboot.designpattern.pattern_decorator;

/**
 * 具体装饰类：
 * 对煎饼加鸡蛋的装饰类，继承PancakeDecorator，覆盖cook操作
 */
public class EggDecorator extends PancakeDecorator {
    public EggDecorator(IPancake pancake) {
        super(pancake);
    }

    /**
     * 覆盖cook方法，加入自身的实现，并且调用父类的cook方法，也就是构造函数中
     * EggDecorator(IPancake pancake)，这里传入的pancake的cook操作
     */
    @Override
    public void cook() {
        super.cook();
        System.out.println("加了一个鸡蛋，");

    }
}