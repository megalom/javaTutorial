package com.megalom.tutorial.multithreading;

import java.util.Scanner;

public class multithreadingTut {
    public static void MyThreadTest(){
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
    public static void VolatileTest() {
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
}
