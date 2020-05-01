package com.example.connection.pool;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wangxueqing
 */
public class ConnectionPoolTest {
  static ConnectionPool pool = new ConnectionPool(10);

  /**
   * 保证所有线程同时开始
   */
  static CountDownLatch start = new CountDownLatch(1);

  /**
   * 保证所有线程都结束再执行main线程
   */
  static CountDownLatch end;

  public static void main(String[] args) throws Exception {
    int threadCount = 30;
    end = new CountDownLatch(threadCount);
    int count = 20;
    AtomicInteger got = new AtomicInteger();
    AtomicInteger notGot = new AtomicInteger();
    for (int i = 0; i < threadCount; i++) {
      new Thread(new ConnectionRunner(count, got, notGot), "ConnectionRunnerThread")
          .start();
    }
    start.countDown();
    end.await();
    System.out.println("total invoke: " + (threadCount * count));
    System.out.println("got connection: " + got);
    System.out.println("not got connection: " + notGot);
  }

  static class ConnectionRunner implements Runnable {

    int count;

    AtomicInteger got;

    AtomicInteger notGot;

    public ConnectionRunner(int count, AtomicInteger got, AtomicInteger notGot){
      this.count = count;
      this.got = got;
      this.notGot = notGot;
    }

    @Override
    public void run() {
      try {
        start.await();
      } catch (Exception e){

      }

      while (count > 0){
        try {
          Connection connection = pool.fetchConnection(1000);
          if(connection != null){
            try {
              connection.createStatement();
              connection.commit();
            } finally {
              pool.releaseConnection(connection);
              got.incrementAndGet();
            }
          } else {
            notGot.incrementAndGet();
          }
        } catch (Exception e){

        } finally {
          count--;
        }
      }
      end.countDown();
    }
  }
}
