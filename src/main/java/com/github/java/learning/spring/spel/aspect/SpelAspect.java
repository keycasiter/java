package com.github.java.learning.spring.spel.aspect;

import com.alibaba.fastjson.JSON;
import com.github.java.learning.spring.spel.annotation.Spel;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * created by guanjian on 2020/11/30 20:00
 * 1、解析map {name:'#req.name',age:'#req.age'}
 * 2、通过动态参数获取#req.name #req.age的真实值
 */
@Aspect
@Component("spelAspect")
public class SpelAspect {

    /**
     * spEl parser
     */
    private final static ExpressionParser parser = new SpelExpressionParser();
    private final static ParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();

    @Around("@annotation(com.github.java.learning.spring.spel.annotation.Spel)")
    public Object around(ProceedingJoinPoint pjp) {

        /**
         * 解析MAP类型
         */
        String variables = getAnnotation(pjp).variables();
        System.out.println("variables=  " + variables);
        Map<String, Object> map = (Map) parser.parseExpression(variables).getValue();
        //1、解析map {name:'#req.name',age:'#req.age'}
        System.out.println("map= " + map);
        //2、获取到方法形参
        String[] params = discoverer.getParameterNames(getMethod(pjp));
        System.out.println("形参param= " + JSON.toJSONString(params));
        //3、获取到方法实参
        Object[] args = getArgs(pjp);
        System.out.println("实参args= " + JSON.toJSONString(args));
        //4、构建spel的context
        EvaluationContext context = new StandardEvaluationContext();

        for (int index = 0; index < params.length; index++) {
            //匹配形参、实参的对应关系注入到context环境中
            context.setVariable(params[index], args[index]);
        }
        //5、遍历map={name=#req.name, age=#req.age}，把对应的value的形参赋值成实参值
        map.forEach((k, spel) -> {
            String value = parser.parseExpression(String.valueOf(spel)).getValue(context, String.class);
            System.out.format("key=%s,spel=%s,value=%s\n", k, spel, value);
        });

        /**
         * 解析String类型
         */
        String bizId = getAnnotation(pjp).bizId();
        System.out.println("bizId=  " + parser.parseExpression(bizId).getValue(context, String.class));

        //target method execute...
        Object result = null;
        try {
            result = pjp.proceed();
        } catch (Throwable e) {
            e.getStackTrace();
        } finally {
            return result;
        }
    }

    protected Method getMethod(ProceedingJoinPoint pjp) {
        Method method = null;
        try {
            MethodSignature ms = (MethodSignature) pjp.getSignature();
            method = pjp.getTarget()
                    .getClass()
                    .getMethod(ms.getName(), ms.getParameterTypes());
        } catch (NoSuchMethodException e) {
            //ignore
        }
        return method;
    }

    protected Object[] getArgs(ProceedingJoinPoint pjp) {
        return pjp.getArgs();
    }

    protected static Spel getAnnotation(ProceedingJoinPoint pjp) {
        Annotation annotation = null;
        try {
            MethodSignature ms = (MethodSignature) pjp.getSignature();
            annotation = pjp.getTarget()
                    .getClass()
                    .getMethod(ms.getName(), ms.getParameterTypes())
                    .getAnnotation(Spel.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (Spel) annotation;
    }

    private String parseSpel(Method method, Object[] args, String spel) {
        String[] params = discoverer.getParameterNames(method);
        EvaluationContext context = new StandardEvaluationContext();
        for (int index = 0; index < params.length; index++) {
            context.setVariable(params[index], args[index]);
        }
        Expression expression = parser.parseExpression(spel);
        return expression.getValue(context, String.class);
    }
}
