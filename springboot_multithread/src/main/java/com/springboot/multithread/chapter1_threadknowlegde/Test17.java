package com.springboot.multithread.chapter1_threadknowlegde;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@Slf4j(topic = "c.Test17")
public class Test17 {

    public static void main(String[] args) {


        int a = 5;
        int sum = 0;
        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                log.debug("线程1开始执行");
                try {
                    TimeUnit.SECONDS.sleep(4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("线程1执行完毕");
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {

                log.debug("线程2开始执行");
                try {
                    t1.join(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("线程2执行完毕");
            }
        });

        t1.start();
        t2.start();

        sum = a + 5;
        System.out.println(sum);
    }
}
