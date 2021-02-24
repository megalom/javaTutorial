package com.megalom.tutorial.multithreading;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchTut {
    public void main(){
        int nthreads=2;
        CountDownLatch countDownLatch = new CountDownLatch(3);//щелка отопрется через 3
        ExecutorService executorService = Executors.newFixedThreadPool(nthreads);
        for(int i=0;i<nthreads;i++){
            executorService.submit(new Processor(countDownLatch));
        }
        System.out.println("Start counting...");
        executorService.shutdown();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Latch has been opened, main thread is proceeding.");


    }
}

class Processor implements Runnable{
    private CountDownLatch countDownLatch;
    public Processor(CountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        countDownLatch.countDown();

    }
}
