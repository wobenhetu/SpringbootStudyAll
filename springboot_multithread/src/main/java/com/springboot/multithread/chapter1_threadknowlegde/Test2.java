package com.springboot.multithread.chapter1_threadknowlegde;


import lombok.extern.slf4j.Slf4j;
/*
* 线程1输出a 5次，线程2输出b 5次，线程1输出c 5次，现在要求输出abcabcabcabcabc 实现
* */
@Slf4j(topic = "c.Test2")
public class Test2 {
    public static void main(String[] args) throws Exception {

        WaitFlag waitFlag = new WaitFlag(0,5);

        new Thread(() -> {
            waitFlag.print("a",0,1);
        },"t1").start();

        new Thread(() -> {
            waitFlag.print("b",1,2);
        },"t2").start();

        new Thread(() -> {
            waitFlag.print("c",2,0);
        },"t3").start();
    }


}

class WaitFlag{
    private int flag;

    private int loopNumber;

    public WaitFlag(int flag, int loopNumber) {
        this.flag = flag;
        this.loopNumber = loopNumber;
    }

    public void print(String str,int currentFlag,int nextFlag){
        for (int i = 0; i < loopNumber; i++) {
            synchronized (this){
                while(flag!=currentFlag){
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.print(str);
                this.flag = nextFlag;
                this.notifyAll();
            }
        }
    }
}
