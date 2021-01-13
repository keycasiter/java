package com.github.java.learning.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * 单独一个socket请求
 * created by guanjian on 2021/1/12 9:09
 */
public class SingleThreadBIOSocketClient {

    public static void main(String[] args) throws IOException {
        Socket socketClient = new Socket();
        socketClient.connect(new InetSocketAddress("127.0.0.1", 9999));

        System.out.println("Client connected ...");

        Scanner scanner = new Scanner(System.in);

        while (true){
            //会阻塞socket等待输入
            String input = scanner.next();

            socketClient.getOutputStream().write(input.getBytes());

            System.out.format("Client sending content:%s \n", input);
        }

    }
}
