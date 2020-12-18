package com.example.concurrent.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchSample {

    private static final int N = 10;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(N);

        for (int i=0; i<N; ++i) {
            new Thread(new Worker(startSignal, doneSignal)).start();
        }
        doSomethingElse();// don't let run yet
        startSignal.countDown();
        doSomethingElse();
        doneSignal.await(); // wait for all to finish
    }

    static void doSomethingElse() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
    }

    static class Worker implements Runnable {

        private final CountDownLatch startSignal;
        private final CountDownLatch doneSignal;

        Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
            this.startSignal = startSignal;
            this.doneSignal = doneSignal;
        }

        @Override
        public void run() {
            try {
                startSignal.await();
                doWork();
                doneSignal.countDown();
            } catch (InterruptedException ex) {}
        }

        void doWork() throws InterruptedException {
            System.out.println(Thread.currentThread().getName() + " do work...");
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
