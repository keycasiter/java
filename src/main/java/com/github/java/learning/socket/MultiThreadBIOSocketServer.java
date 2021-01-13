package com.github.java.learning.socket;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 开启多线程来接收处理Socket请求
 * 不会被阻塞socket请求，但是会耗费大量线程资源
 *
 * created by guanjian on 2021/1/12 9:09
 */
public class MultiThreadBIOSocketServer {

    private static byte[] bytes = new byte[1024];

    public static void main(String[] args) throws IOException, InterruptedException {

        ServerSocket socketServer = new ServerSocket();
        socketServer.bind(new InetSocketAddress(9999));
        System.out.println("Server starting ...");

        while (true) {
            System.out.println("Server waiting connect ...");
            Socket socket = socketServer.accept();
            //接收请求后开启线程来处理，避免accept阻塞
            if (null != socket) {
                new SockectServerHandler(socket).start();
            }
        }
    }

    static class SockectServerHandler extends Thread {
        private Socket socket;

        public SockectServerHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                long start = System.currentTimeMillis();
                System.out.println("Server begin receive data ...");
                //read方法会阻塞
                socket.getInputStream().read(bytes);
                long end = System.currentTimeMillis();
                System.out.format("Server finish receive data: %s , cost: %s \n", new String(bytes), end - start);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
