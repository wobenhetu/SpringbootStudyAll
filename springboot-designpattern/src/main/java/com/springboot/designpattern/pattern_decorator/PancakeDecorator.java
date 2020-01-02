package com.springboot.designpattern.pattern_decorator;


/*
* 定义抽象装饰类
* */
public abstract  class PancakeDecorator implements IPancake {

    private IPancake pancake;

    public PancakeDecorator(IPancake pancake) {
        this.pancake = pancake;
    }

    public void cook() {
        if (this.pancake != null) {
            pancake.cook();
        }
    }
}