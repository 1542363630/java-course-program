package com.example.courseprogram;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer {
    public static void main(String[] args) throws Exception{
        int port = 9091;
        ServerSocket server = new ServerSocket(port);
        ExecutorService threadPool = Executors.newFixedThreadPool(100);

        while(true){
            Socket socket = server.accept();

            Runnable runnable=()->{
                try {
                    InputStream inputStream = socket.getInputStream();
                    byte[] bytes = new byte[1024];
                    int len;
                    StringBuilder stringBuilder = new StringBuilder();
                    while((len=inputStream.read(bytes))!=-1){
                        stringBuilder.append(new String(bytes,0,len,"UTF-8"));
                    }
                    inputStream.close();
                    socket.close();

                }catch (Exception e){
                    e.printStackTrace();
                }
            };
            threadPool.submit(runnable);
        }
    }
}
