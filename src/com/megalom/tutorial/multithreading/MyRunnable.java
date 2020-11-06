package com.megalom.tutorial.multithreading;

public class MyRunnable implements Runnable{
    String name;
    MyRunnable(String name){
        this.name = name;
    }
    @Override
    public void run() {
        for(int i=0;i<10;i++){
            System.out.println("Hello from thread <"+name+"> "+i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
