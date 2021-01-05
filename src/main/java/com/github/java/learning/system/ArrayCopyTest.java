package com.github.java.learning.system;

import java.util.Arrays;

/**
 * created by guanjian on 2021/1/5 20:24
 */
public class ArrayCopyTest {

    public static void main(String[] args) {
        //下面是 System.arrayCopy的源代码声明 :
        //    Object src : 原数组
        //    int srcPos : 从元数据的起始位置开始
        //　　 Object dest : 目标数组
        //　　 int destPos : 目标数组的开始起始位置
        //　　 int length  : 要copy的数组的长度
        byte[] srcBytes = new byte[]{2, 4, 0, 0, 0, 0, 0, 10, 15, 50};  // 源数组

        byte[] destBytes = new byte[5]; // 目标数组

        System.arraycopy(srcBytes, 0, destBytes, 0, 5);

        System.out.println(Arrays.toString(srcBytes));
        System.out.println(Arrays.toString(destBytes));
    }
}
