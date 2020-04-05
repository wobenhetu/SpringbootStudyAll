package com.springboot.multithread.chapter1_threadknowlegde;

/*
* countdownLatch 的使用
* */

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j(topic = "c.Test18")
public class Test18 {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);

        CountDownLatch countDownLatch = new CountDownLatch(10);
        Random r = new Random();

        String[] all = new String[10];
        for (int i = 0; i < 10; i++) {
            int k = i;
            service.submit(()->{
                for (int j = 0; j <= 100; j++) {
                    try {
                        Thread.sleep(r.nextInt(100));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    all[k] = j+ "%";
                    System.out.print("\r"+ Arrays.toString(all));
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println("\n所有线程加载完毕，游戏正式开始");
        service.shutdown();
    }
}
