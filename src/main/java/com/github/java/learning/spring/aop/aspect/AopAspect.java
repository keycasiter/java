package com.github.java.learning.spring.aop.aspect;

import com.github.java.learning.spring.aop.annotation.Aop;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.stream.Stream;

/**
 * created by guanjian on 2020/11/30 20:00
 */
@Aspect
@Component("aopAspect")
public class AopAspect {

    @Resource
    private TransactionTemplate transactionTemplate;

    /**
     * 1、当使用一个@Aop时，如果只拦截@Aop不拦截@Aops，只能通过getAnnotation()获取到@Aop直接方法
     * 2、当使用多个@Aop时，需要拦截@Aops通过getAnnotationsByType来获取@Aop数组才可以拿到注解对象
     */
    @Around("@annotation(com.github.java.learning.spring.aop.annotation.Aop) || @annotation(com.github.java.learning.spring.aop.annotation.Aops) " )
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Aop[] aops = getMethod(pjp).getAnnotationsByType(Aop.class);
        Stream.of(aops).forEach(x->{
            System.out.format("value=== %s\n", x.value());
        });

        return pjp.proceed();
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

    protected static Aop getAnnotation(ProceedingJoinPoint pjp) {
        Annotation annotation = null;
        try {
            MethodSignature ms = (MethodSignature) pjp.getSignature();
            annotation = pjp.getTarget()
                    .getClass()
                    .getMethod(ms.getName(), ms.getParameterTypes())
                    .getAnnotation(Aop.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (Aop) annotation;
    }
}
