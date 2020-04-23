package com.example.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * @author wangxueqing
 */
public class DeadLock {

  private static Object resource1 = new Object();

  private static Object resource2 = new Object();

  public static void main(String[] args) {
    new Thread(()->{
      synchronized (resource1) {
        System.out.println(Thread.currentThread() + " get resource1.");
        try {
          // key 1: 这里的sleep很有必要，因为它可以保证线程获取第一个资源的锁，否则不加的话，
          // 会导致同时获取资源二的锁，并释放，这样两个线程就不会竞争了！
          TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println(Thread.currentThread() + " waiting get resource2");
        synchronized (resource2){
          System.out.println(Thread.currentThread() + " get resource2");
        }
      }
    }, "thread-1").start();

    new Thread(()->{
      synchronized (resource2) {
        System.out.println(Thread.currentThread() + " get resource2.");
        try {
          TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println(Thread.currentThread() + " waiting get resource1");
        synchronized (resource1){
          System.out.println(Thread.currentThread() + " get resource1");
        }
      }
    }, "thread-2").start();
  }


}
