package com.springboot.designpattern.pattern_strategy;

//折扣类：抽象策略类
interface Discount {
	public double calculate(double price);
}
