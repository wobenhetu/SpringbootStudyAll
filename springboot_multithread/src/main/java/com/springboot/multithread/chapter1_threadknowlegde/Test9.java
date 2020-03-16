package com.springboot.multithread.chapter1_threadknowlegde;

import java.util.concurrent.TimeUnit;

public class Test9 {

    public static void main(String[] args) throws Exception {


        System.out.println("dfw==begin"+System.currentTimeMillis());
        Thread preThread = Thread.currentThread();

        for (int i = 0; i < 10; i++) {
            Thread mThread = new Thread(new MyThread(preThread), String.valueOf(i));
            mThread.start();
            preThread = mThread;
        }

        TimeUnit.SECONDS.sleep(5);
        System.out.println(Thread.currentThread().getName() + "terminate.");
        System.out.println("dfw==end"+System.currentTimeMillis());
    }

    public static class MyThread implements Runnable {
        private Thread mThread;
        public MyThread(Thread mThread) {
            this.mThread = mThread;
        }

        @Override
        public void run() {
            try {
                mThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(System.currentTimeMillis());
            System.out.println(Thread.currentThread().getName() + "terminate.");
        }
    }
}
