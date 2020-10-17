package com.example.myproxy;

import com.example.myproxy.api.Hello;

/**
 * @author wangxueqing
 */
public class HelloImpl implements Hello {

  @Override
  public void sayHello() {
    System.out.println("Hello world!");
  }
}
