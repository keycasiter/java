package com.github.java.learning.socket;


import com.google.common.collect.Lists;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.List;
import java.util.Optional;

/**
 * 每次只能处理一个连接请求，其他连接请求会被阻塞
 * created by guanjian on 2021/1/12 9:09
 */
public class SingleThreadNIOSocketChannelServer {

    /**
     * 存储SocketChannel集合
     */
    private static List<SocketChannel> channels = Lists.newArrayList();

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocketChannel socketServer = ServerSocketChannel.open();
        socketServer.socket().bind(new InetSocketAddress(9999));
        //设置非阻塞模式，这里控制的是accept函数是否阻塞
        socketServer.configureBlocking(false);

        System.out.println("Server starting ...");

        while (true) {
            System.out.println("Server waiting connect ...");
            SocketChannel socketChannel = socketServer.accept();
            if (null != socketChannel) {
                System.out.println("Server receive connect ...");
                //设置非阻塞模式，这里控制的是read函数是否阻塞
                socketChannel.configureBlocking(false);
                channels.add(socketChannel);
            }

            System.out.format("============ channels:%s ============\n", channels.size());
            Optional.ofNullable(channels).ifPresent(channels->{
                channels.stream().filter(channel -> channel.isConnected()).forEach(channel -> {
                    try {
                        System.out.println("Server read data begin ...");

                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

                        int len = channel.read(byteBuffer);
                        if (len > 0) {
                            byteBuffer.flip();
                            System.out.format("Server receive data : %s ...\n", byteBufferToString(byteBuffer));
                        } else {
                            System.out.println("Server receive data is empty ...");
                        }
                        System.out.println("Server read data end ...");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            });
            //为了打印日志，故意设置时间间隔
            Thread.sleep(2000);
        }
    }

    public static String byteBufferToString(ByteBuffer buffer) {
        CharBuffer charBuffer = null;
        try {
            Charset charset = Charset.forName("UTF-8");
            CharsetDecoder decoder = charset.newDecoder();
            charBuffer = decoder.decode(buffer);
            return charBuffer.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
