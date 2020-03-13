package com.springboot.multithread.chapter1_threadknowlegde;


import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Slf4j(topic = "c.Test5")
public class Test5 {

    public static void main(String[] args){

        demo(
                () -> new AtomicIntegerArray(20),
                (array) -> array.length(),
                (array,index) -> array.getAndIncrement(index),
                array -> System.out.println(array)
        );
    }

    private static <T> void demo(
            Supplier<T> arraySupplier,
            Function<T,Integer> lengthFun,
            BiConsumer<T,Integer>  putConsumer,
            Consumer<T> printConsumer)
    {

        List<Thread> ts =  new ArrayList<>();

        T array =  arraySupplier.get();

        int length = lengthFun.apply(array);

        for (int i = 0; i < length; i++) {
            ts.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        putConsumer.accept(array,j%length);
                    }
                }
            }));
        }

        ts.forEach(t -> t.start());

        ts.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        printConsumer.accept(array);
    }
}
