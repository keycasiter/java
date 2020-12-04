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

    @Around("@annotation(com.github.java.learning.spring.aop.annotation.Aop) " +
            "|| @annotation(com.github.java.learning.spring.aop.annotation.Aops)")
    public Object around(ProceedingJoinPoint pjp) {
        return transactionTemplate.execute(transactionStatus -> {
            Object result = null;
            try {
                Aop[] aops = getMethod(pjp).getAnnotationsByType(Aop.class);
                Stream.of(aops).forEach(x->{
                    System.out.format("value=== %s\n", x.value());
                });

                result = pjp.proceed();
                System.out.println("execute===");
            } catch (Throwable e) {
                e.getStackTrace();
                transactionStatus.setRollbackOnly();
            } finally {
                return true;
            }
        });
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
