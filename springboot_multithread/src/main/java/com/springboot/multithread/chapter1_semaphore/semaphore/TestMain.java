package com.springboot.multithread.chapter1_semaphore.semaphore;

public class TestMain {

    public static void main(String[] args) throws InterruptedException{
        MyService myService = new MyService();
        /*MyThread a = new MyThread("a",myService);
        MyThread b = new MyThread("b",myService);
        MyThread c = new MyThread("c",myService);

        a.start();
        b.start();
        c.start();*/

       /* Thread.sleep(1000);

        a.interrupt();
        b.interrupt();
        c.interrupt();*/

        //System.out.println("被中断了");


        MyThread2[] myThreads = new MyThread2[12];
        for (int i=0;i<12;i++){
            myThreads[i] = new MyThread2(myService);
            myThreads[i].start();
        }
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
            //this.myService.testMethod2(name);
        }
    }


    static class MyThread2 extends Thread{
        private MyService myService;

        public MyThread2(MyService myService) {
            this.myService = myService;

        }

        @Override
        public void run() {
            super.run();
            //this.myService.testMethod(name);
            //this.myService.testMethod2(name);
            this.myService.testMethod3();
        }
    }
}
