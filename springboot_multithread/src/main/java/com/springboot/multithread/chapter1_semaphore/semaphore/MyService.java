package com.springboot.multithread.chapter1_semaphore.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class MyService {

    /*
    * 传入许可;限制线程并发的数量，permits为1，就是限制同时只有一个线程执行acquire() 和 release()之间的代码；
    *
    * acquire（int permits） 表示每调用1次此方法，就使用掉几个许可；比如：有10个许可，这里acquire（2）,则
    *    10/2=5，说明同一时间允许执行acquire() 和 release()之间的代码的只有5个线程；
    *
    * acquireUninterruptibly()使等待进入acquire（）方法的线程，不允许被中断：
    *
    * */
    private Semaphore semaphore =  new Semaphore(3);

    private ReentrantLock reentrantLock = new ReentrantLock();

    public void testMethod(String name){
        try {
            semaphore.acquire();
            //semaphore.acquireUninterruptibly();//使等待进入acquire（）方法的线程，不允许被中断；
            System.out.println(Thread.currentThread().getName()+name+"  begin time="+System.currentTimeMillis());
            System.out.println("大约还有"+semaphore.getQueueLength()+"个线程在等待");
            System.out.println("是否有线程还在等待信号量呢？"+semaphore.hasQueuedThreads());
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName()+name+"  end time="+System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphore.release();
        }
    }


    /*
    * semaphore.tryAcquire() 的作用是尝试的获得一个许可，如果获取不到则返回false；
    * 其具有无阻塞的特点；无阻塞的特点可以使线程不至于在同步处一直持续等待的状态；与if语句结合使用，如果if不成立，
    * 则线程会继续走else语句，程序会继续向下执行；
    *
    * */
    public void testMethod2(String name){

        if(semaphore.tryAcquire()){
            System.out.println(Thread.currentThread().getName()+name+"  begin time="+System.currentTimeMillis());
            System.out.println("大约还有"+semaphore.getQueueLength()+"个线程在等待");
            System.out.println("是否有线程还在等待信号量呢？"+semaphore.hasQueuedThreads());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+name+"  end time="+System.currentTimeMillis());
            semaphore.release();
        }else{
            System.out.println(Thread.currentThread().getName()+name+"未获得许可");
        }
    }


    public void testMethod3(){
        try {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName()+"准备");
            reentrantLock.lock();
            System.out.println(Thread.currentThread().getName()+"  begin time="+System.currentTimeMillis());

            for (int i=0;i<5;i++){
                System.out.println(Thread.currentThread().getName()+"打印"+i);
            }

            System.out.println("大约还有"+semaphore.getQueueLength()+"个线程在等待");
            System.out.println("是否有线程还在等待信号量呢？"+semaphore.hasQueuedThreads());
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName()+"  end time="+System.currentTimeMillis());
            reentrantLock.unlock();
            semaphore.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
