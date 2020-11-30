package com.github.java.learning.spring.spel;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.List;
import java.util.Map;

/**
 * @author: <a href=mailto:guanjian@jd.com>guanjian23</a>
 * @date: 2020/11/30 11:11
 * @description: Map解析
 */
public class SpelMapDemo {

    public static void main(String[] args) {
        //解析器
        ExpressionParser parser = new SpelExpressionParser();

        //解析集合
        Map map = (Map) parser.parseExpression("{account:'deniro',footballCount:10}") .getValue();
        System.out.println("map:" + map);
    }
}
