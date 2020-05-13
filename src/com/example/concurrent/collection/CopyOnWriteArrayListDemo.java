package com.example.concurrent.collection;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author wangxueqing
 */
public class CopyOnWriteArrayListDemo {

  public static void main(String[] args) {
    CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
    list.add(1);
    list.add(3);
    System.out.println(list.get(1));
  }
}
