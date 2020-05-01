package com.example.connection.pool;

import com.example.connection.pool.com.example.connection.ConnectionDriver;
import java.sql.Connection;
import java.util.LinkedList;

/**
 * @author wangxueqing
 */
public class ConnectionPool {

  private LinkedList<Connection> pool = new LinkedList<>();

  public ConnectionPool(int initialSize){
    if(initialSize > 0){
      for (int i = 0; i < initialSize ; i++) {
        pool.addLast(ConnectionDriver.createConnection());
      }
    }
  }

  /**
   * release connection
   * @param connection
   */
  public void releaseConnection(Connection connection){
    if (connection != null){
      synchronized (pool) {
        pool.addLast(connection);
        pool.notifyAll();
      }
    }
  }

  /**
   * fetch a connection
   * @param mills
   * @return
   * @throws InterruptedException
   */
  public Connection fetchConnection(long mills) throws InterruptedException {
    synchronized (pool) {
      if(mills <= 0){
        while (pool.isEmpty()){
          pool.wait();
        }
        return pool.removeFirst();
      } else {
        long future = System.currentTimeMillis() + mills;
        long remaining = mills;
        while(pool.isEmpty() && remaining > 0){
          pool.wait(remaining);
          remaining = future - System.currentTimeMillis();
        }

        Connection connection = null;
        if(!pool.isEmpty()){
          connection = pool.removeFirst();
        }
        return connection;
      }
    }
  }

}
