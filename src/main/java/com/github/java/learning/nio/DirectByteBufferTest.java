package com.github.java.learning.nio;

import java.nio.ByteBuffer;

/**
 * created by guanjian on 2021/1/18 9:34
 */
public class DirectByteBufferTest {
    public static void main(String[] args) throws InterruptedException {

        /**
         * allocateXXX
         * 开辟内存空间
         */
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(10);

        /**
         * put
         * 写入操作
         */
        byteBuffer.putInt(1);//一个int占用4字节
        byteBuffer.putInt(233);//一个int占用4字节
        System.out.println(byteBuffer);//[pos=8 lim=10 cap=10]

        /**
         * flip
         *
         * 从写模式转换到读模式，这里需要调用flip，这里其实是对ByteBuffer中的变量position、limit、capacity进行重新复制来支持读取操作，
         * 因为底层是通过有限线性容器进行存储的，通过变更变量控制进行写入和读取切换实现空间共享
         */
        byteBuffer.flip();
        System.out.println(byteBuffer);//[pos=0 lim=8 cap=10]

        /**
         * get
         * 读取操作
         */
        System.out.println(byteBuffer.getInt());//这里获取按照存储顺序对称取出，底层存储的都是byte数组，getXXX和putXXX只不过是对Java基本类型与byte之间的封装来保存或者读取
        System.out.println(byteBuffer);//[pos=4 lim=8 cap=10]

        /**
         * rewind
         * 将position标记为0，可以从头继续读取
         */
        byteBuffer.rewind();//复位后可以从0重新开始读
        System.out.println(byteBuffer);//[pos=0 lim=8 cap=10]
        System.out.println(byteBuffer.getInt());//由于position=0，继续读取第一个元素

        /**
         * clear
         * 清除，初始化
         * 其实这里的内存空间并没有释放，而是对内部position、mark、limit、capacity变量这些进行初始化
         */
        byteBuffer.clear();
        System.out.println(byteBuffer);//[pos=0 lim=10 cap=10]

        byteBuffer.put((byte)1);
        byteBuffer.put((byte)2);
        byteBuffer.put((byte)3);
        System.out.println(byteBuffer);//[pos=3 lim=10 cap=10]
        byteBuffer.flip();
        System.out.println(byteBuffer.get());
        System.out.println(byteBuffer);//[pos=1 lim=3 cap=10]
        byteBuffer.compact();
        System.out.println(byteBuffer);//[pos=2 lim=10 cap=10]
        System.out.println(byteBuffer.get());
        System.out.println(byteBuffer.get());
    }
}
