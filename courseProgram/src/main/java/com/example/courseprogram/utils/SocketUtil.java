package com.example.courseprogram.utils;

import java.io.IOException;
import java.net.ServerSocket;

public class SocketUtil extends ServerSocket {

    public SocketUtil(int port) throws IOException {
        super(port);
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9091);
        serverSocket.accept();
        try {
            SocketUtil socketUtil=new SocketUtil(9091);
        }catch (IOException ioException){

        }
    }
}
