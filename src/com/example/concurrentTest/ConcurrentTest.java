package com.example.concurrentTest;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;
import sun.misc.Unsafe;

/**
 * @author wangxueqing
 */
public class ConcurrentTest extends Thread {

  private static LongAdder longAdder = new LongAdder();

  private static AtomicInteger atomicInteger = new AtomicInteger(0);

  private static int _synchronized = 0;

  public static volatile int cas = 0;
  private static long casOffset;

  public static Unsafe UNSAFE;


  static {
    try {
      @SuppressWarnings("ALL")
      Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
      theUnsafe.setAccessible(true);
      UNSAFE = (Unsafe) theUnsafe.get(null);
      casOffset = UNSAFE.staticFieldOffset(ConcurrentTest.class.getDeclaredField("cas"));
    }catch (Exception e){
      e.printStackTrace();
    }
  }

  // 乐观锁 调用Unsafe实现cas
  public void cas() {
    boolean bl = false;
    int tmp;
    while (!bl){
      tmp = cas;
      bl = UNSAFE.compareAndSwapInt(ConcurrentTest.class, casOffset, tmp, tmp+1);
    }
  }

  /**
   * 模拟AtomicInteger的实现
   */
  public void atomicInteger() {
    UNSAFE.getAndSetInt(this, casOffset, 1);
  }


  @Override
  public void run() {
    int i = 1;
    while (i<=10000000) {
      // 测试AtomicInteger
      atomicInteger.incrementAndGet();

      //atomicInteger实现
      //atomicInteger();

      //测试LongAdder
      //longAdder.increment();

      //测试volatile和cas 乐观锁
      //cas();

      //synchronized
//      synchronized (this){
//        ++_synchronized;
//      }
      i++;
    }
  }


  public static void main(String[] args) {
    //System.out.println("Hello world!");

    long start = System.currentTimeMillis();
    // 100 threads
    for (int i = 0; i < 60; i++) {
      new ConcurrentTest().start();
    }

    while (Thread.activeCount() > 1){
      Thread.yield();
    }

    System.out.println(System.currentTimeMillis() - start);
    System.out.println(_synchronized);
    System.out.println(atomicInteger);
    System.out.println(longAdder);
    System.out.println(cas);
  }



}
