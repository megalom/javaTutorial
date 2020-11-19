package com.megalom.tutorial.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPool {
    public void main(){
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for(int i = 0;i < 5; i++){
            executorService.submit(new Work(i));
        }
        executorService.shutdown();
        System.out.println("All tasks submitted.");
        try {
            executorService.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class Work implements Runnable{
    private int id;
    public Work(int id){this.id=id;}

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Work "+id+" was completed.");
    }
}
