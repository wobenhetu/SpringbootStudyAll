package com.springboot.multithread.chapter1_threadknowlegde;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

@Slf4j(topic = "c.Sync")
public class SysncTest {

    public static void main(String[] args) throws Exception {

//        Runnable runnable = () -> { log.debug("hello");};
//
//        Thread thread = new Thread(runnable,"t1");
//        thread.start();
//        log.debug("dfw=====");


        FutureTask<Integer> callTask = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {

                log.debug("执行完如下内容返回");
                int time = 2;
                TimeUnit.SECONDS.sleep(2);
                return time;
            }
        });

        Thread thread = new Thread(callTask,"t1");
        thread.start();

        log.debug("{}",callTask.get());//这块儿会进行阻塞，拿到结果下面的日志才会打印；
        log.debug("dfw====");
    }
}
