package com.example.design;

/**
 * @author wangxueqing
 */
public class Singleton1 {

  public static void main(String[] args) {
    System.out.println(getSingleton() == getSingleton());
  }

  private Singleton1() {

  }

  public static Singleton1 getSingleton() {
    return Holder.singleton1;
  }
  private static class Holder {
    private static Singleton1 singleton1 = new Singleton1();
  }
}
