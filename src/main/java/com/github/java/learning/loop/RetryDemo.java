package com.github.java.learning.loop;

/**
 * created by guanjian on 2021/3/17 17:07
 */
public class RetryDemo {

    public static void main(String[] args) {
        formalBreak();
//        oneLoopBreak();
    }

    public static void oneLoopBreak(){
        oneLoop:
        for (int i = 0; i < 2; i++) {
            moreLoop:
            for (int j = 0; j < 5; j++) {
                System.out.println(j);
                if (j ==3) break oneLoop;
            }
        }
        //0,1,2,3
        //断点在oneLoop，断点指向所有循环外，遇到断点跳出全部循环
    }

    public static void moreLoopBreak(){
        oneLoop:
        for (int i = 0; i < 2; i++) {
            moreLoop:
            for (int j = 0; j < 5; j++) {
                System.out.println(j);
                if (j ==3) break moreLoop;
            }
        }
        //0,1,2,3,0,1,2,3
        //断点在moreLoop，断点指向内循环，会把外循环i执行完毕
    }

    public static void formalBreak(){
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.println(j);
                if (j ==3) break;
            }
        }
        //0,1,2,3,0,1,2,3
        //断点在内循环，外循环会执行完
    }
}
