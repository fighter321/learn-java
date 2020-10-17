package com.example.concurrenttest;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author wangxueqing
 */
public class CyclicBarrierDemo {

  public static void main(String[] args) {
    CyclicBarrierDemo cyclicBarrierDemo = new CyclicBarrierDemo();
    cyclicBarrierDemo.doTest();
  }

  public void doTest(){
    final int N = 8;
    CyclicBarrier cyclicBarrier = new CyclicBarrier(N);
    for (int i = 0; i < N; i++) {
      new Thread(new Worker(cyclicBarrier)).start();
    }
  }

  class Worker implements Runnable {
    CyclicBarrier cyclicBarrier;

    public Worker(CyclicBarrier cyclicBarrier){
      this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
      try{
        cyclicBarrier.await();
        System.out.println(Thread.currentThread().getName() + "@start" + System.currentTimeMillis());
      }catch (BrokenBarrierException | InterruptedException e){
        e.printStackTrace();
      }
    }
  }

}
