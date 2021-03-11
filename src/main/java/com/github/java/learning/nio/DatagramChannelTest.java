package com.github.java.learning.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * created by guanjian on 2021/1/22 11:18
 */
public class DatagramChannelTest {

    public static void main(String[] args) throws IOException {
        DatagramChannel channel = DatagramChannel.open();
        channel.socket().bind(new InetSocketAddress(9090));
        channel.configureBlocking(false);

        while (true) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(10);
            channel.receive(byteBuffer);
            if (channel.read(byteBuffer) > 0){
                System.out.println(byteBufferToString(byteBuffer));
            }
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
