package com.megalom.tutorial.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Worker {
    Random random = new Random();
    Object lock1 = new Object();
    Object lock2 = new Object();

    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();

    public void addToListOne(){
        synchronized (lock1) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list1.add(random.nextInt(100));
        }
    }
    public void addToListTwo(){
        synchronized (lock2) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list2.add(random.nextInt(100));
        }
    }

    public void work(){
        for(int i=0;i<1000;i++) {
            addToListOne();
            addToListTwo();
        }
    }

    public void main(){
        long before = System.currentTimeMillis();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                work();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                work();
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

        long after = System.currentTimeMillis();
        System.out.println("Program took "+(after-before)+"ms to run");

        System.out.println("list1: "+list1.size());
        System.out.println("list2: "+list2.size());
    }
}
