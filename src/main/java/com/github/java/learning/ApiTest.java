package com.github.java.learning;

import java.util.stream.IntStream;

/**
 * created by guanjian on 2020/11/4 17:07
 */
public class ApiTest {

    //测试2的幂次方-1数据二进制展示
    public static void test01() {
        IntStream.rangeClosed(0, 30).forEach(x -> {
            int num = new Double(Math.pow(2, x)).intValue() - 1;
            System.out.format("\t 2的%s次方 num:%s \t binary:%s \n", x, num, Long.toBinaryString(num));
        });
    }


    public static void main(String[] args) {
//        int[][] nums = new int[][]{{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
//        System.out.println(findNumberIn2DArray(nums, 5));
//
//        int[][] nums2 = new int[][]{{-5}};
//        System.out.println(findNumberIn2DArray(nums2, -5));

        System.out.println(replaceSpace("We are happy."));
    }

    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        //{0,0},{0,1},{0,2}....
        //{1,0},{1,1},{1,2}....
        //{2,0},{2,1},{2,2}....

        //{x,y},{x,y+1},{x,y+2}
        //{x+1,y},{x+1,y+1},{x+1,y+2}
        //{x+2,y},{x+2,y+1},{x+2,y+2}

        int x = 0, y = 0;

        while (x <= matrix.length -1 && y <= matrix[x].length - 1) {
            System.out.format("x=%s y=%s matrix=%s target=%s \n", x, y, matrix[x][y],target);

            if (target == matrix[x][y] || target == matrix[x+1][y] || target == matrix[x][y+1]) return true;

            //右子树
            else if (target < matrix[x+1][y]) {
                x++;
            }
            //左子树
            else if (target > matrix[x][y+1]) {
                y++;
            }
        }

        return false;
    }

    public static String replaceSpace(String s) {
        //扩容替换文本长度的倍数，避免扩容或溢出
        char[] ret = new char[3 * s.length()];

        int index = 0;
        //遍历字符串
        for(int i = 0 ; i < s.length(); i ++){
            char c = s.charAt(i);
            index = i;
            if( c == ' '){
                ret[index++] = '%';
                ret[index++] = '2';
                ret[index++] = '0';
            }else{
                ret[index] = c;
            }
        }
        return new String(ret);
    }
}
