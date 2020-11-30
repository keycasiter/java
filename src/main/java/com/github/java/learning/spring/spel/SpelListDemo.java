package com.github.java.learning.spring.spel;

import com.google.common.collect.Lists;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.List;
/**
 * @author: <a href=mailto:guanjian@jd.com>guanjian23</a>
 * @date: 2020/11/30 11:11
 * @description: 集合解析
 */
public class SpelListDemo {

    public static void main(String[] args) {
        //解析器
        ExpressionParser parser = new SpelExpressionParser();

        //解析集合
        List<Integer> list = (List) parser.parseExpression("{3,4,5}").getValue();
        System.out.println("集合开始：");
        for (int i : list) {
            System.out.println(i);
        }
        System.out.println("集合结束");
    }
}
