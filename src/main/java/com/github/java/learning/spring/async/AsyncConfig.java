package com.github.java.learning.spring.async;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;

/**
 * created by guanjian on 2021/4/7 9:36
 */
@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

    /**
     * 这里手动注入异步线程池
     */
    @Override
    public Executor getAsyncExecutor() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        //核心线程池数量
//        executor.setCorePoolSize(Runtime.getRuntime().availableProcessors());
//        //最大线程数量
//        executor.setMaxPoolSize(Runtime.getRuntime().availableProcessors() * 5);
//        //线程池的队列容量
//        executor.setQueueCapacity(Runtime.getRuntime().availableProcessors() * 2);
//        //线程名称的前缀
//        executor.setThreadNamePrefix("async-executor-");
//        executor.initialize();
//        return executor;
        return null;
    }

    /**
     * 这里注入异步未知错误捕获处理
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SimpleAsyncUncaughtExceptionHandler();
    }
}
