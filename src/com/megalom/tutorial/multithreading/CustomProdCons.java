package com.megalom.tutorial.multithreading;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class CustomProdCons {
    public void main(){
        ProducerConsumer wn= new ProducerConsumer();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                wn.produce();
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                wn.consume();
            }
        });
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
class ProducerConsumer {
    private Queue<Integer> queue = new LinkedList<>();
    private final int LIMIT = 10;
    private Object lock = new Object();

    public void produce(){
        int value = 0;
        while(true){
            synchronized (lock){
                while(queue.size()==LIMIT){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                queue.offer(value++);
                lock.notify();
            }
        }
        /*
        synchronized (this){

            System.out.println("Producer thread started");
            try {
                wait(); // 1 - отдаем intrinsic lock 2 - ждем, пока будет вызван notify().
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Producer thread resumed");
        }
        */
    }
    public void consume(){

        while(true){
            synchronized (lock){
                while(queue.size()==0){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int value = queue.poll();
                System.out.println(value);
                System.out.println("Queue size is "+queue.size());
                lock.notify();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /*
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        synchronized (this){
            System.out.println("Waiting for return key pressed");
            scanner.nextLine();
            notify();
        }*/
    }
}
