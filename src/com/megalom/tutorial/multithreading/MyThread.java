package com.megalom.tutorial.multithreading;

public class MyThread extends Thread{
    private String name;
    private volatile boolean running=true; //переменная не будет кэшироваться, будет находится в ОЗУ,
    // это позволит избежать некогерентности кеша ядер
    private int counter;
    MyThread(String name){
        this.name = name;
    }
    public void run(){
        System.out.println("start counting");
       while(running){
           System.out.println("timer: "+counter);
           try {
               Thread.sleep(1000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           counter++;
       }
        System.out.println("stop counting");
    }
    public void shutdown(){
        running=false;
    }
}
