package com.springboot.designpattern.pattern_singleton;


/*
由于静态内部类LazyClass只有在getInstance()方法第一次被调用时，才会被加载，而且构造函数为private，
因此该种方式实现了懒汉式的单例模式。不仅如此，根据JVM本身机制，静态内部类的加载已经实现了线程安全;

* */
class Singleton {
	// 私有化构造方法，防止通过new的方式创建对象
	private Singleton() {
	}
	
	// 静态内部类，懒加载
	private static class LazyClass {
    	private final static Singleton INSTANCE = new Singleton();
	}
	
	// 返回对象
	public static Singleton getInstance() {
	    return LazyClass.INSTANCE;
	}
}