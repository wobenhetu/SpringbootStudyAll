package com.springboot.designpattern.pattern_decorator;


public class Client {
    public static void main(String[] args) {
        System.out.println("=========我是土豪都给我加上===========");
        IPancake pancake = new Pancake();
        IPancake pancakeWithEgg = new EggDecorator(pancake);
        IPancake pancakeWithEggAndHam = new HamDecorator(pancakeWithEgg);
        IPancake panckeWithEggAndHamAndLettuce = new LettuceDecorator(pancakeWithEggAndHam);
        panckeWithEggAndHamAndLettuce.cook();

        System.out.println("==========我是程序猿，加两个鸡蛋补补==============");
        IPancake pancake2 = new Pancake();
        IPancake pancakeWithEgg2 = new EggDecorator(pancake2);
        IPancake pancakeWithTwoEgg = new EggDecorator(pancakeWithEgg2);
        pancakeWithTwoEgg.cook();
    }
}