package com.megalom.tutorial.multithreading;

public class MyThread2 implements  Runnable {
    Counter counter;
    public MyThread2(Counter counter){
        this.counter = counter;
    }
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            try {

                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            counter.increment();
        }
    }
}

class Counter {
    public int counter;

    public Counter() {
        counter = 0;
    }

    public synchronized void increment() {
        counter++;
    }
    // or
    // use synchronized block
    // synchornized(this){
    // counter++;
    // }
}
