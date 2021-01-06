package com.github.java.learning.io;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.stream.IntStream;

/**
 * created by guanjian on 2021/1/6 16:26
 */
public class PipedStreamTest {

    public static void main(String[] args) throws InterruptedException {
        Sender sender = new Sender();
        Receiver receiver = new Receiver();

        PipedOutputStream outStream = sender.getOutStream();
        PipedInputStream inStream = receiver.getInStream();

        try {
            inStream.connect(outStream); // 与下一句一样
            //outStream.connect(inStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sender.start();
        receiver.start();
    }


    static class Sender extends Thread {
        private PipedOutputStream outStream = new PipedOutputStream();

        public PipedOutputStream getOutStream() {
            return outStream;
        }

        @Override
        public void run() {
            IntStream.rangeClosed(0, 10).forEach(x -> {
                String info = "hello, receiver";

                try {
                    outStream.write(info.getBytes());

                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            try {
                outStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class Receiver extends Thread {
        private PipedInputStream inStream = new PipedInputStream();

        public PipedInputStream getInStream() {
            return inStream;
        }

        @Override
        public void run() {
            byte[] buf = new byte[1024];

            try {
                int len;
                while ((len = inStream.read(buf)) != -1) {

                    System.out.format("receive message from sender : %s \n" , new String(buf, 0, len));
                }
                inStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}