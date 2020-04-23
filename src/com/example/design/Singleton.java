package com.example.design;

/**
 * @author wangxueqing
 */
public class Singleton {

  public static void main(String[] args) {
    Singleton obj1 = getInstance();
    Singleton obj2 = getInstance();
    System.out.println(obj1 == obj2);
  }


  /**
   * volatile 主要起可见性及重排序的作用
   * 因为instance = new Singleton() 分三步 1.分配内存 2.初始化对象 3.对象地址赋给引用变量
   * 其中2，3步在多线程中是没法保证顺序的，只有通过volatile禁止重排序才能保证其有序执行
   */
  private static volatile Singleton instance;

  private Singleton() {

  }

  public static Singleton getInstance(){
    if(instance == null){
      synchronized (Singleton.class) {
        if(instance == null){
          instance = new Singleton();
        }
      }
    }

    return instance;
  }
}
