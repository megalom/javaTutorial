package com.megalom.tutorial.multithreading;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class PatternProducerConsumer {
    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
    public void main(){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                produce();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                consume();
            }
        });

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void produce(){
        Random random = new Random();
        while (true){
            try {
                Thread.sleep(100);
                queue.put(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void consume(){
        Random random = new Random();
        while(true){
            try {
                Thread.sleep(100);
                if(random.nextInt(10) < 2) {
                    System.out.println(queue.take());
                    System.out.println("Queue size: " + queue.size());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
