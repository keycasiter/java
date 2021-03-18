package com.github.java.learning.threadPool;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * created by guanjian on 2021/3/15 19:40
 */
public class CatcheExceptionDemo {

    /**
     * try catch
     */
    public static class TryCatch {
        public static void main(String[] args) {
            ExecutorService executor = Executors.newFixedThreadPool(2);

            //try-catch
            IntStream.rangeClosed(0, 10).forEach(i -> {
                executor.execute(() -> {
                    try {
                        throw new RuntimeException("exe-error");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                });
            });
        }
    }

    public static class ExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("handler " + e.getMessage());
        }
    }

    /**
     * 配置UncaughtExceptionHandler
     */
    public static class UncaughtException {
        public static void main(String[] args) {
            ThreadFactory executorThreadFactory = new BasicThreadFactory.Builder()
                    .namingPattern("task-scanner-executor-%d")
                    .uncaughtExceptionHandler(new ExceptionHandler())
                    .build();
            ExecutorService executor = Executors.newSingleThreadExecutor(executorThreadFactory);

            //try-catch
            IntStream.rangeClosed(0, 10).forEach(i -> {
                executor.execute(() -> {
                    throw new RuntimeException("exe-error");
                });
            });
            executor.shutdown();
        }
    }

    /**
     * 重写AfterExecute
     */
    public static class AfterExecute extends ThreadPoolExecutor {

        public AfterExecute(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        public AfterExecute(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            super.afterExecute(r, t);
            System.out.println("after execute " + t.getMessage());
        }

        public static void main(String[] args) {

            ExecutorService executor = new AfterExecute(
                    1,
                    10,
                    10, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<>());

            //try-catch
            IntStream.rangeClosed(0, 10).forEach(i -> {
                executor.execute(() -> {
                    throw new RuntimeException("exe-error");
                });
            });
            executor.shutdown();
        }
    }

    /**
     * 异常信息流
     */
    public static class ExceptionFlow{

        public static void main(String[] args) {
            ThreadFactory executorThreadFactory = new BasicThreadFactory.Builder()
                    .namingPattern("task-scanner-executor-%d")
                    .uncaughtExceptionHandler(new ExceptionHandler())
                    .build();

            ExecutorService executor = new AfterExecute(
                    1,
                    10,
                    10, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<>(),
                    executorThreadFactory
                    );

            executor.execute(()->{
                try{
                    throw new RuntimeException("execute - error");
                }catch (Exception e){
                    System.out.println("try-catch " + e.getMessage());
                    throw e;
                }
            });

            Future future = executor.submit(()->{
                try{
                    throw new RuntimeException("submit - error");
                }catch (Exception e){
                    System.out.println("try-catch " + e.getMessage());
                    throw e;
                }
            });
            try {
                future.get();
            }catch (Exception e){
                System.out.println("future - catch " + e.getMessage());
            }
        }
    }
}
