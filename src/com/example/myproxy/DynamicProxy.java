package com.example.myproxy;


import com.example.myproxy.api.Hello;
import java.lang.reflect.Proxy;

/**
 * @author wangxueqing
 */
public class DynamicProxy {

  public static void main(String[] args) {
    HelloImpl hello = new HelloImpl();
    MyInvocationHander handler = new MyInvocationHander(hello);
    Hello target = (Hello) Proxy.newProxyInstance(HelloImpl.class.getClassLoader(), HelloImpl.class.getInterfaces(), handler);
    target.sayHello();
  }

}
