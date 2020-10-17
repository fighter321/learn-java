package com.example.concurrenttest;

import java.util.concurrent.CountDownLatch;

/**
 * @author wangxueqing
 */
public class CountDownLatchDemo {

  public static void main(String[] args) {
    CountDownLatchDemo countDownLatchDemo = new CountDownLatchDemo();
    countDownLatchDemo.doTest();
  }

  public void doTest() {
    final int N = 8;
    CountDownLatch countDownLatch = new CountDownLatch(N);
    for(int i=0; i<N; ++i){
      new Thread(new Worker(countDownLatch)).start();
      countDownLatch.countDown();
    }
  }

  class Worker implements Runnable {

    CountDownLatch countDownLatch;

    public Worker(CountDownLatch countDownLatch){
      this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
      try{
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "start@" + System.currentTimeMillis());
      }catch (InterruptedException e){
        e.printStackTrace();
      }
    }
  }

}
