package com.github.java.learning.aio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * created by guanjian on 2021/1/19 9:19
 */
public class AsynchronousFileChannelTest {

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException, TimeoutException {
        Path path = Paths.get("C:\\Users\\guanjian\\Desktop\\demo.txt");

        AsynchronousFileChannel fileChannel =
                AsynchronousFileChannel.open(path, StandardOpenOption.READ);

        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        Future<Integer> future = fileChannel.read(byteBuffer,0);

        int c = future.get(5, TimeUnit.SECONDS);
        System.out.println(c);
    }
}
