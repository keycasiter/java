package com.github.java.learning.spring.spel;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * @author: <a href=mailto:guanjian@jd.com>guanjian23</a>
 * @date: 2020/11/30 10:05
 * @description: 文本解析
 */
public class SpelTextDemo {

    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();

        //字符串解析
        String str = (String) parser.parseExpression("'你好'").getValue();
        System.out.println(str);

        //整型解析
        int intVal = (Integer) parser.parseExpression("0x2F").getValue();
        System.out.println(intVal);

        //双精度浮点型解析
        double doubleVal = (Double) parser.parseExpression("4329759E+22").getValue();
        System.out.println(doubleVal);

        //布尔型解析
        boolean booleanVal = (boolean) parser.parseExpression("true").getValue();
        System.out.println(booleanVal);
    }
}
