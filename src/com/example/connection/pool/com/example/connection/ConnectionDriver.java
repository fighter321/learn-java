package com.example.connection.pool.com.example.connection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;

/**
 * @author wangxueqing
 */
public class ConnectionDriver {

  static class ConnectionHander implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      if("commit".equals(method.getName())){
        TimeUnit.MILLISECONDS.sleep(100);
      }
      return null;
    }
  }

  public static final Connection createConnection() {
    return (Connection) Proxy.newProxyInstance(ConnectionDriver.class.getClassLoader(),
        new Class<?>[]{Connection.class}, new ConnectionHander());
  }

}
