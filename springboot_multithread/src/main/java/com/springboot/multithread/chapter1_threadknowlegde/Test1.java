package com.springboot.multithread.chapter1_threadknowlegde;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/*
* 生产者消费者模式程序模拟
*
* */
@Slf4j(topic = "c.Test1")
public class Test1 {
    public static void main(String[] args) throws Exception {

        MesseageQueue messeageQueue = new MesseageQueue(2);

        for (int i=0;i<3;i++){
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int id = finalI;
                    Object value = "值："+id;
                    Message message = new Message(id,value);
                    messeageQueue.putMessage(message);
                }
            },"生产者线程").start();
        }

        TimeUnit.SECONDS.sleep(4);

        for (int i=0;i<2;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    messeageQueue.takeMessage();
                }
            },"消费者线程").start();
        }

    }

}

@Slf4j(topic = "c.MesseageQueue")
class MesseageQueue {

    private LinkedList<Message> linkedList = new LinkedList<>();

    public MesseageQueue(int capacity) {
        this.capacity = capacity;
    }

    private int capacity;

    public Message takeMessage() {
        synchronized (linkedList) {
            while (linkedList.isEmpty()) {
                try {
                    log.debug("队列为空，消费者线程等待....");
                    linkedList.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Message message = linkedList.removeFirst();
            log.debug(message.getId()+" "+message.getValue());
            linkedList.notifyAll();
            return message;
        }
    }

    public void putMessage(Message message) {
        synchronized (linkedList) {
            while (linkedList.size() == capacity) {
                try {
                    log.debug("队列已满，生产者线程等待....");
                    linkedList.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            linkedList.addLast(message);
            log.debug(message.getId()+" "+message.getValue());
            linkedList.notifyAll();
        }
    }
}

final class Message {
    int id;

    Object value;

    public Message(int id, Object value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public Object getValue() {
        return value;
    }


}
