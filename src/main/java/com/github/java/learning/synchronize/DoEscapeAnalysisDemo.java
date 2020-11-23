package com.github.java.learning.synchronize;

import java.util.Vector;

/**
 * created by guanjian on 2020/11/23 16:27
 */
public class DoEscapeAnalysisDemo {


    /**
     * -XX:+DoEscapeAnalysis 开启逃逸分析
     * -XX:-DoEscapeAnalysis 关闭逃逸分析
     */
    public static void main(String[] args) {
        Vector<String> vector = new Vector<>();

        long start = System.currentTimeMillis();

        System.out.format("start : %s\n", start);
        for (int i = 0; i < 10000000; i++) {
            vector.add(i + "");
        }
        long end = System.currentTimeMillis();
        System.out.format("cost : %s\n", end - start);
    }
}
