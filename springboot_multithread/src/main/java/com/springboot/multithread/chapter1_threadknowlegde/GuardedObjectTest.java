package com.springboot.multithread.chapter1_threadknowlegde;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@Slf4j(topic = "c.GuardedObjectTest")
public class GuardedObjectTest {

    public static void main(String[] args) throws Exception {
        GuardedObject guardedObject = new GuardedObject();
        //线程t1获取下载结果
        new Thread(new Runnable() {
            @Override
            public void run() {
                log.debug("获取结果");
                List<String> lists = (List<String>)guardedObject.get();
                log.debug("获取结果为"+ lists.size());
            }
        }, "t1").start();

        //线程t2进行下载
        new Thread(new Runnable() {
            @Override
            public void run() {
                log.debug("下载结果");
                try {
                    List<String> lists = DownLoader.download();
                    guardedObject.complete(lists);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, "t2").start();


    }
}

class GuardedObject {
    private Object response;

    public Object get() {
        synchronized (this) {
            while (response == null) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return response;
        }
    }

    public void complete(Object result) {
        synchronized (this) {
            this.response = result;
            this.notifyAll();
        }
    }
}
