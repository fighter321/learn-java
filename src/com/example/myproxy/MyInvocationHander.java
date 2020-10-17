package com.example.myproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author wangxueqing
 */
public class MyInvocationHander implements InvocationHandler {

  private Object target;

  public MyInvocationHander(Object target) {
    this.target = target;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    System.out.println("invoking sayHello");
    Object result = method.invoke(target, args);
    return result;
  }
}
