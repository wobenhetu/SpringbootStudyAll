package com.springboot.multithread.chapter1_semaphore.semaphore;

public class TestMain {

    public static void main(String[] args) throws InterruptedException{
        MyService myService = new MyService();
        MyThread a = new MyThread("a",myService);
        MyThread b = new MyThread("b",myService);
        MyThread c = new MyThread("c",myService);

        a.start();
        b.start();
        c.start();

       /* Thread.sleep(1000);

        a.interrupt();
        b.interrupt();
        c.interrupt();*/

        //System.out.println("被中断了");
    }

    static class MyThread extends Thread{
        private MyService myService;
        private String name;

        public MyThread(String name,MyService myService) {
            this.myService = myService;
            this.name = name;
        }

        @Override
        public void run() {
            super.run();
            //this.myService.testMethod(name);
            this.myService.testMethod2(name);
        }
    }
}
