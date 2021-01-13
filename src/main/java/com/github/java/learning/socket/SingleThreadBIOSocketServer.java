package com.github.java.learning.socket;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 每次只能处理一个连接请求，其他连接请求会被阻塞
 * created by guanjian on 2021/1/12 9:09
 */
public class SingleThreadBIOSocketServer {

    private static byte[] bytes = new byte[1024];

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket socketServer = new ServerSocket();
        socketServer.bind(new InetSocketAddress(9999));

        System.out.println("Server starting ...");

        while (true) {

            System.out.println("Server waiting connect ...");
            //accept方法会阻塞
            Socket socket = socketServer.accept();

            long start = System.currentTimeMillis();
            System.out.println("Server begin receive data ...");
            //read方法会阻塞
            socket.getInputStream().read(bytes);
            long end = System.currentTimeMillis();
            System.out.format("Server finish receive data: %s , cost: %s \n", new String(bytes), end - start);
        }
    }
}
