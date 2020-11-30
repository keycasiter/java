package com.github.java.learning.spring.spel;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @author: <a href=mailto:guanjian@jd.com>guanjian23</a>
 * @date: 2020/11/30 10:06
 * @description: 对象解析
 */
public class SpelObjectDemo {

    public static void main(String[] args) {
        //初始化对象
        User user = new User("zhangsan");
        user.setAge(10);
        user.setUser(new User("lisi"));

        //解析器
        ExpressionParser parser = new SpelExpressionParser();
        //解析上下文
        EvaluationContext context = new StandardEvaluationContext(user);

        //获取不同类型的属性
        String name = (String) parser.parseExpression("name").getValue(context);
        System.out.println(name);
        int count = (Integer) parser.parseExpression("age+1").getValue(context);
        System.out.println(count);

        //获取嵌套类中的属性
        String friend = (String) parser.parseExpression("user.name").getValue(context);
        System.out.println(friend);

        //获取嵌套类中的属性
        user.setUser(null);
        String friend2 = (String) parser.parseExpression("user?.name").getValue(context);
        System.out.println(friend2);
    }
}
