package com.github.java.learning.oopKlassModel;

/**
 * created by guanjian on 2020/11/12 15:33
 */
public class OopKlassModel {
    public static int a = 1;
    public int b;

    public OopKlassModel(int b) {
        this.b = b;
    }

    public static void main(String[] args) {
        //c、modelA、modelB 在栈中
        int c = 10;
        OopKlassModel modelA = new OopKlassModel(2);
        OopKlassModel modelB = new OopKlassModel(3);
    }
}
