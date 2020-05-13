package com.example.hot.trace;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangxueqing
 */
public class BTraceTest {

  public static void main(String[] args) throws IOException {
    BTraceTest test = new BTraceTest();
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    for (int i = 0; i < 10; i++) {
      reader.readLine();
      int a = (int) Math.round(Math.random() * 1000);
      int b = (int) Math.round(Math.random() * 1000);
      System.out.println(test.add(a, b));
    }
  }

  private int add(int a, int b){
    return a + b;
  }

}
