package com.springboot.multithread.chapter10_collection.blockingQueue;

import java.util.concurrent.SynchronousQueue;

public class MyService {

    private SynchronousQueue synchronousQueue = new SynchronousQueue();

    public void putMethod(){
        try {
            String putString = "anyString"+Math.random();
            System.out.println("put="+putString);
            synchronousQueue.put(putString);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void takeMethod(){
        try {
            System.out.println("take="+synchronousQueue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
