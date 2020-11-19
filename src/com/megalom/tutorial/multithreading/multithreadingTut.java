package com.megalom.tutorial.multithreading;

import java.util.Scanner;

public class multithreadingTut {
    public static void myThreadTest(){
        System.out.println("Sleep before creating threads "+System.currentTimeMillis());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Waking up "+System.currentTimeMillis());
        MyThread myThread = new MyThread("Thread #1");
        System.out.println("Thread 1 was created.");
        myThread.start();
        System.out.println("Thread 1 started.");

        MyThread myThread2 = new MyThread("Thread #2");
        System.out.println("Thread 2 was created.");
        myThread2.start();
        System.out.println("Thread 2 started.");

        System.out.println("Starting runnable");
        Thread thread = new Thread(new MyRunnable("Runnable 1"));
        thread.start();


        System.out.println("Starting main loop");
        for(int i=0; i< 10;i++){
            System.out.println("main loop"+i);
        }
    }
    public static void volatileTest() {
        MyThread myThread = new MyThread("counter1");
        String scaninput = "";
        myThread.start();
        Scanner scanner = new Scanner(System.in);

        while (!scaninput.equalsIgnoreCase("quit")) {
            scaninput = scanner.nextLine();
            System.out.println(scaninput);
        }

        myThread.shutdown();
        scanner.close();
    }
    public static void synchronizedTest(){
        Counter counter = new Counter();

        MyThread2 mt1 = new MyThread2(counter);
        MyThread2 mt2 = new MyThread2(counter);

        Thread t1= new Thread(mt1);
        Thread t2= new Thread(mt2);

        t1.start();
        t2.start();
        /*
        while((t1.isAlive())&&(t2.isAlive())) {
            System.out.println(counter.counter);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(counter.counter);


    }
    public static void synchronizedTest2(){
        Worker worker = new Worker();
        worker.main();
    }

    public static void threadPool(){
        ThreadPool threadPool = new ThreadPool();
        threadPool.main();
    }
    public static void producerConsumer(){
        PatternProducerConsumer patternProducerConsumer = new PatternProducerConsumer();
        patternProducerConsumer.main();
    }
}
