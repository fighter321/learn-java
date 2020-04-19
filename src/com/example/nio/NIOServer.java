package com.example.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.sql.SQLOutput;
import java.util.Iterator;

/**
 * @author wangxueqing
 */
public class NIOServer {

  public static void main(String[] args) throws IOException {
    // 创建ServerSocket,并监听8080
    ServerSocketChannel ssc = ServerSocketChannel.open();
    ssc.socket().bind(new InetSocketAddress(8080));

    ssc.configureBlocking(false);

    Selector selector = Selector.open();
    ssc.register(selector, SelectionKey.OP_ACCEPT);

    while (true) {
      if(selector.select(3000) == 0){
        System.out.println("time out...");
        continue;
      }

      System.out.println("handle request...");
      Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();

      while(keyIter.hasNext()) {
        SelectionKey key = keyIter.next();
        try {
          // accept
          if(key.isAcceptable()){
            Handler.accept(key);
          }
          if(key.isReadable()){
            Handler.read(key);
          }
        } catch (Exception e){
          keyIter.remove();
          continue;
        }
        keyIter.remove();
      }
    }


  }


  private static class Handler {
    private static void accept(SelectionKey key){}

    private static void read(SelectionKey key){}
  }
}
