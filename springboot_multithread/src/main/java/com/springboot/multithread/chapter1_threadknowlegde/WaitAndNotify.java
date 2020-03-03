package com.springboot.multithread.chapter1_threadknowlegde;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j(topic = "c.WaitAndNotify")
public class WaitAndNotify {
    static final Object lock = new Object();

    public static void main(String[] args) throws Exception {

        new Thread(() -> {
            synchronized (lock){
                log.debug("执行");

                try {
                    //lock.wait();///让线程在lock对象上一直等下去
                    lock.wait(1);//让线程等1秒后如果没有主动唤醒则继续执行下面的代码
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                log.debug("其他代码");
            }
        },"t1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                synchronized (lock){
                    log.debug("执行");
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.debug("其他代码");
                }

            }
        },"t2").start();

        TimeUnit.SECONDS.sleep(2);
        log.debug("唤醒lock上其他线程");

        synchronized (lock){
            //lock.notify();//唤醒lock上的一个线程
            lock.notifyAll();
        }

    }

}
