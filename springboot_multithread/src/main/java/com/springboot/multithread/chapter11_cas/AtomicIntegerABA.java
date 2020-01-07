package com.springboot.multithread.chapter11_cas;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


/*
* CAS的ABA问题模拟
   假设你的账户上有100块钱，你要给女票转50块钱。

我们使用CAS进行原子更新账户余额。由于某种原因，你第一次点击转账出现错误，你以为没有发起转账请求，
这时候你又点击了一次。系统开启了两个线程进行转账操作，第一个线程进行CAS比较，发现你的账户上预期是100块钱，
实际也有100块钱，这时候转走了50，需要设置为100 - 50 = 50 元，这时账户余额为50

第一个线程操作成功了，第二个线程由于某种原因阻塞住了；这时候，你的家人又给你转了50块钱，并且转账成功。
那你账户上现在又是100块钱；

太巧了，第二个线程被唤醒了，发现你的账户是100块钱，跟预期的100是相等的，这时候又CAS为50。
大兄弟，哭惨了，你算算，正确的场景你要有多少钱？这就是CAS存在的ABA问题。


* */
public class AtomicIntegerABA {

    private static AtomicInteger atomicInteger = new AtomicInteger(100);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        //线程1
        executorService.execute(() -> {
            System.out.println(Thread.currentThread().getName() + " - " + atomicInteger.get());
            atomicInteger.compareAndSet(100, 50);
            System.out.println(Thread.currentThread().getName() + " - " + atomicInteger.get());
        });

        //线程2
        executorService.execute(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " - " + atomicInteger.get());
            atomicInteger.compareAndSet(50, 100);
            System.out.println(Thread.currentThread().getName() + " - " + atomicInteger.get());
        });

        //线程3
        executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " - " + atomicInteger.get());
            atomicInteger.compareAndSet(100, 50);
            System.out.println(Thread.currentThread().getName() + " - " + atomicInteger.get());
        });

        executorService.shutdown();
    }
}