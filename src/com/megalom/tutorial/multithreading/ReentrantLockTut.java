package com.megalom.tutorial.multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTut {
    Task task = new Task();
    public void main() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                task.firstThread();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                task.secondThread();
            }
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        task.showCounter();

    }
}
class Task{
    private int counter;
    private Lock lock = new ReentrantLock();
    private void increment(){
        for(int i =0; i< 10000;i++){
            counter++;
        }
    }

    public void firstThread(){
        lock.lock();
        increment();
        lock.unlock();
    }

    public void secondThread(){
        lock.lock();
        increment();
        lock.unlock();
    }
    public void showCounter(){
        System.out.println("Counter = " + counter);
    }

}
