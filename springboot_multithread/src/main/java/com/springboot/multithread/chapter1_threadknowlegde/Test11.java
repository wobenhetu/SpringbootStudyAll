package com.springboot.multithread.chapter1_threadknowlegde;

import java.util.concurrent.TimeUnit;

/*
* ThreadLocal 使用
* */
public class Test11 {
    // 第一次get()方法调用时会进行初始化（如果set方法没有调用），每个线程会调用一次
    private static final ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal<Long>() {
        protected Long initialValue() {
            return System.currentTimeMillis();
        }
    };

    private static final ThreadLocal<Long> time_local = new ThreadLocal<Long>(){

        @Override
        protected Long initialValue() {
            return System.currentTimeMillis();
        }
    };

    public static final void begin() {
        time_local.set(System.currentTimeMillis());
    }

    public static final long end() {
        return System.currentTimeMillis() - time_local.get();
    }

    public static void main(String[] args) throws Exception {
        Test11.begin();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Cost: " + Test11.end() + " mills");
    }
}
