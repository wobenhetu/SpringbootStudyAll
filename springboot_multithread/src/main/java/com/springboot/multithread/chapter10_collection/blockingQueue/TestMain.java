package com.springboot.multithread.chapter10_collection.blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class TestMain {

    public static void main(String[] args) throws InterruptedException{

        //test_ArrayBlockingQueue();
        test_SynchronousQueue();
    }


    /*
    * ArrayBlockingQueue是有界队列；如下，定义了3个集合的队列后，如果再往进添加一个，则会处于阻塞状态；
    * 下面通过poll()方法进行弹出数据，然后就可以往队列里填充数据；
    * */
    public static void test_ArrayBlockingQueue() throws InterruptedException{
        ArrayBlockingQueue queue = new ArrayBlockingQueue(3);
        queue.put("a1");
        queue.put("a2");
        queue.put("a3");

        System.out.println(queue.size());
        System.out.println(System.currentTimeMillis());

        Thread.sleep(5000);
        System.out.println(System.currentTimeMillis());
        queue.poll();
        queue.poll();
        queue.put("a4");

        System.out.println(queue.size());
        System.out.println(System.currentTimeMillis());
    }

    /*
    * LinkedBlockingQueue与ArrayBlockingQueue功能上大体一样，LinkedBlockingQueue是无界的，也可以定义成有界的，
    * 都有阻塞特性；只支持对列头的操作；
    * */
    public static void  test_linkedBlockingQueue(){

    }


    /*
    * LinkedBlockingDeque提供对双端节点的操作，都具有阻塞特性；
    * */
    public static void test_LinkedBlockingDeque(){

    }


    /*
    * SynchronousQueue是一个内部只能包含一个元素的队列；
    * 插入元素到队列的线程被阻塞，直到另一个线程从队列中获取了队列中存储的元素。
    * 同样，如果线程尝试获取元素并且当前不存在任何元素，则该线程将被阻塞，直到线程将元素插入队列。
    *
    * 在多个线程之间传输数据时候使用；
    * */
    public static void test_SynchronousQueue() throws InterruptedException{
       /* SynchronousQueue synchronousQueue = new SynchronousQueue();

        System.out.println("step1");
        synchronousQueue.put("step1");
        System.out.println("step2");
        synchronousQueue.take();
        System.out.println("step3");*/

       MyService myService = new MyService();
       ThreadPut threadPut = new ThreadPut(myService);
       ThreadTake threadTake = new ThreadTake(myService);
       threadPut.start();

//       Thread.sleep(2000);
        threadTake.start();

    }

    static class ThreadPut extends Thread{

        public ThreadPut(MyService myService) {
            this.myService = myService;
        }

        private MyService myService;

        @Override
        public void run() {
            super.run();
            for (int i = 0; i < 10; i++) {
                myService.putMethod();
            }
        }
    }


    static class ThreadTake extends Thread{

        public ThreadTake(MyService myService) {
            this.myService = myService;
        }

        private MyService myService;

        @Override
        public void run() {
            super.run();
            for (int i = 0; i < 10; i++) {
                myService.takeMethod();
            }
        }
    }
}
