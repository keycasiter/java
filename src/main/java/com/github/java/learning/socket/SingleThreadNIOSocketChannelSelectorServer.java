package com.github.java.learning.socket;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * 单线程处理 多路复用
 * created by guanjian on 2021/1/12 9:09
 */
public class SingleThreadNIOSocketChannelSelectorServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        // 创建ServerSocketChannel通道，绑定监听端口为8080
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(9999));
        // 设置为非阻塞模式
        serverSocketChannel.configureBlocking(false);
        // 注册选择器,设置选择器选择的操作类型
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("Server starting ...");

        while (true) {
            System.out.println("Server receive request ...");
            // 等待请求，每次等待阻塞3s，超过时间则向下执行，若传入0或不传值，则在接收到请求前一直阻塞
            if (selector.select(1000) > 0) {
                System.out.println("Server receive event ...");
                // 获取待处理的选择键集合
                Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
                while (keyIterator.hasNext()) {
                    SelectionKey selectionKey = keyIterator.next();

                    // 如果是连接请求，调用处理器的连接处理方法
                    if (selectionKey.isAcceptable()) {
                        System.out.println("Server receive connect ...");
                        handleAccept(selectionKey);
                    }
                    // 如果是读请求，调用对应的读方法
                    if (selectionKey.isReadable()) {
                        System.out.println("Server receive read ...");
                        handleRead(selectionKey);
                    }
                    // 处理完毕从待处理集合移除该选择键
                    keyIterator.remove();
                }
            }
            //为了打印日志，故意设置时间间隔
            Thread.sleep(2000);
        }

    }

    public static void handleAccept(SelectionKey selectionKey) throws IOException {
        SocketChannel socketChannel = ((ServerSocketChannel) selectionKey.channel()).accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(selectionKey.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(1024));
    }

    public static void handleRead(SelectionKey selectionKey) throws IOException {
        // 获取套接字通道
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        // 获取缓冲器并进行重置,selectionKey.attachment()为获取选择器键的附加对象
        ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
        byteBuffer.clear();
        // 没有内容则关闭通道
        if (socketChannel.read(byteBuffer) == -1) {
            socketChannel.close();
        } else {
            // 将缓冲器转换为读状态
            byteBuffer.flip();
            // 将缓冲器中接收到的值按localCharset格式编码保存
            String receivedRequestData = Charset.forName("UTF-8").newDecoder().decode(byteBuffer).toString();
            System.out.format("Server receive data：" + receivedRequestData);
            // 关闭通道
            //socketChannel.close();
        }
    }
}
