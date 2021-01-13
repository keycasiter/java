package com.github.java.learning.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 模拟多线程并发socket请求
 * created by guanjian on 2021/1/12 9:09
 */
public class MultiThreadBIOSocketClient {
    private static CyclicBarrier cb = new CyclicBarrier(10, () -> {
        System.out.println("===== Client Multi Threads Begin Request =====");
    });

    public static void main(String[] args) throws IOException {
        IntStream.range(0, 10).forEach(t -> {
            new SockectClient(t).start();
        });
    }

    static class SockectClient extends Thread {
        private int seq;

        public SockectClient(int seq) {
            this.seq = seq;
        }

        @Override
        public void run() {
            Socket socketClient = new Socket();
            try {
                cb.await(5, TimeUnit.SECONDS);

                socketClient.connect(new InetSocketAddress("127.0.0.1", 9090));
                System.out.println("Client connected.");

                String sendMsg = "你好 " + seq;

                System.out.format("Client sending content:%s \n", sendMsg);
                socketClient.getOutputStream().write(sendMsg.getBytes());
                socketClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}