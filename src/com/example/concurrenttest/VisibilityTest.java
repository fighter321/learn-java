package com.example.concurrenttest;

/**
 * @author wangxueqing
 */
public class VisibilityTest {

  private long count = 0;

  private final int N = 100000;

  private void add10k(){
    for(int i=0; i<N; ++i){
      ++count;
    }
  }

  public static long cal() throws InterruptedException {
    final VisibilityTest visibilityTest = new VisibilityTest();
    Thread th1 = new Thread(()->{
      visibilityTest.add10k();
    });

    Thread th2 = new Thread(() -> {
      visibilityTest.add10k();
    });

    th1.start();
    th2.start();
    th1.join();
    th2.join();
    return visibilityTest.count;
  }

  public static void main(String[] args) throws InterruptedException {
    System.out.println(cal());
  }

}
