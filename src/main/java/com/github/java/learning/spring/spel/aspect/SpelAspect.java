package com.github.java.learning.spring.spel.aspect;

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

/**
 * created by guanjian on 2020/11/30 20:00
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
        Object result = null;

        String str = parseSpel(getMethod(pjp),getArgs(pjp),getAnnotation(pjp).variables());

        System.out.println(str);

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
        return String.valueOf(expression.getValue(context));
    }
}
