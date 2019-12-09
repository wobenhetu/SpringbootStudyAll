package com.springboot.designpattern.pattern_singleton;

/*

不仅超级简单，而且保证了线程安全。这里引用一下，此方法无偿提供了序列化机制，
绝对防止多次实例化，即使面对复杂的序列化或者反射攻击。
单元素枚举类型已经成为实现Singleton的最佳方法

* */
public enum  Singleton2 {

    INSTANCE;
    // 这里隐藏了一个空的私有构造方法
    private Singleton2 () {}
}
