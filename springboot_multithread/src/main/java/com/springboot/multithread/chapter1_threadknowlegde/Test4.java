package com.springboot.multithread.chapter1_threadknowlegde;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


@Slf4j(topic = "c.Test4")
public class Test4 {


    public static void main(String[] args) throws Exception {


//        AtomicInteger atomicInteger = new AtomicInteger(5);
//
//        new Thread(() -> {
//
//            updateAndGet(atomicInteger);
//            log.debug(atomicInteger.get()+"");
//        }, "t1").start();

//        new Thread(() -> {
//
//            updateAndGet(atomicInteger);
//            log.debug(atomicInteger.get()+"");
//        }, "t2").start();
//
//        new Thread(() -> {
//
//            updateAndGet(atomicInteger);
//            log.debug(atomicInteger.get()+"");
//        }, "t3").start();



        AtomicInteger integer = new AtomicInteger(0);

        System.out.println(integer.get());

        Thread[] threads = new Thread[1000];

        for (int j = 0; j < 1000; j++) {
            threads[j] = new Thread(() -> {
                updateAndGet(integer);
//            log.debug(atomicInteger.get()+"");
            }
                    //integer.incrementAndGet()
            );
            threads[j].start();
        }

        for (int j = 0; j < 1000; j++) {
            threads[j].join();
        }

        //TimeUnit.SECONDS.sleep(2);
        System.out.println(integer.get());

    }

    public static void updateAndGet(AtomicInteger i) {
        while (true) {
            int prev = i.get();

            int next = prev + 1;

            if (i.compareAndSet(prev, next)) {
                break;
            }
        }
    }
}

