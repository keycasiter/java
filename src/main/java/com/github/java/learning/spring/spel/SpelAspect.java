package com.github.java.learning.spring.spel;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

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

    @Around("@annotation(com.github.java.learning.spring.spel.Spel)")
    public Object around(ProceedingJoinPoint pjp) {
        Object result = null;

        try {
            result = pjp.proceed();
        } catch (Throwable e) {
            e.getStackTrace();
        } finally {
            return result;
        }
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
