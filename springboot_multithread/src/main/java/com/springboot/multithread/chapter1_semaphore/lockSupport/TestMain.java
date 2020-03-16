package com.springboot.multithread.chapter1_semaphore.lockSupport;

import java.util.concurrent.locks.LockSupport;

public class TestMain {

    public static void main(String[] args){
        Thread mainThread = Thread.currentThread();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t1=============");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t1==end==============");

                LockSupport.unpark(mainThread);
            }
        });

        t1.start();
        LockSupport.park();

        System.out.println("main==============");
    }
}
