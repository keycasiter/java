package com.github.java.learning.spring.spel;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * @author: <a href=mailto:guanjian@jd.com>guanjian23</a>
 * @date: 2020/11/30 11:11
 * @description: 数组解析
 */
public class SpelArrayDemo {

    public static void main(String[] args) {
        //解析器
        ExpressionParser parser = new SpelExpressionParser();

        //解析一维数组
        int[] oneArray = (int[]) parser.parseExpression("new int[]{3,4,5}").getValue();
        System.out.println("一维数组开始：");
        for (int i : oneArray) {
            System.out.println(i);
        }
        System.out.println("一维数组结束");
    }
}
