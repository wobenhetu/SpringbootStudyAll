package com.springboot.multithread.chapter1_semaphore.exchanger;

import java.util.concurrent.Exchanger;


/*
* Exchanger功能可以使2个线程之间传输数据，类中的exchange()方法具有阻塞的特色，
* 也就是此方法被调用后等待其他线程来取得数据，如果没有数据，则一直等待；
* */

public class TestMain {

    public static void main(String[] args) throws InterruptedException{
        Exchanger<String> exchanger = new Exchanger<>();

        MyThreadA a = new MyThreadA(exchanger);
        MyThreadB b = new MyThreadB(exchanger);

        a.start();
        b.start();
    }

    static class MyThreadA extends Thread{
        private Exchanger<String> exchanger;


        public MyThreadA(Exchanger<String> exchanger) {
            this.exchanger = exchanger;
        }


        @Override
        public void run() {
            super.run();

            try {
                System.out.println("A线程中得到的值："+exchanger.exchange("A数据"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    static class MyThreadB extends Thread{
        private Exchanger<String> exchanger;


        public MyThreadB(Exchanger<String> exchanger) {
            this.exchanger = exchanger;
        }


        @Override
        public void run() {
            super.run();

            try {
                System.out.println("B线程中得到的值："+exchanger.exchange("B数据"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
