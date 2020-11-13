package com.github.java.learning.monitor;

/**
 * 汉森管程
 * created by guanjian on 2020/11/13 9:14
 */
public class HansenMonitor extends AbstractMonitor {

    @Override
    public void wait(String id) throws InterruptedException {
        //获取锁，需要在获得锁的情况下才可以操作condition
        notFull.increaseCount();
        LOGGER.info("当前condition中阻塞的线程数：【{},{},{},{}】", blockingQueue.enterCount, blockingQueue.nextCount, notFull.getCount(), notEmpty.getCount());

        //判断是否有进程在高优先级队列中
        if (blockingQueue.nextCount > 0) {
            //唤醒因调用signal操作的线程
            blockingQueue.next.release();
        }
        LOGGER.info("线程【{}】调用wait被挂起到条件变量【{}】。", id, notFull.getCondition());
        //挂起时自动释放锁，等待进入管程的队列可以获得锁并进入管程
        notFull.getCondition().await();
        LOGGER.info("被挂起的线程【{}】被唤醒执行。", id);
        notFull.decreaseCount();
    }

    @Override
    public void signal(String id) throws InterruptedException {
        LOGGER.info("线程【{}】执行了释放资源", id);
        if (notEmpty.getCount() > 0) {
            //挂起自己后，因为调用signal挂起自己的进程数量加1
            blockingQueue.nextCount++;
            //唤醒因为条件变量而阻塞的线程
            notEmpty.getCondition().signal();
            LOGGER.info("唤醒的条件变量为：【{}】", notEmpty.getName());
            //log.info("释放后所有condition中阻塞的线程数：【mutex：{},nextCount:{},notFull:{},notEmpty:{}】", IM.enterCount, IM.nextCount, notFull.count, notEmpty.count);
            //释放资源，继续执行，直至线程退出管程后，别的线程才可进入-------MESA的处理方式
            LOGGER.info("被挂起的线程【{}】被唤醒执行。", id);
            //恢复执行后，等待调用的管程的线程数量减1
            blockingQueue.nextCount--;
        }
    }

    @Override
    public void put(String id, Integer item) throws InterruptedException {
        lock.lock();
        //如果缓冲池满了，就挂起到notFull的阻塞队列中
        LOGGER.info("线程【{}】投放消息，缓冲池的消息的数量：【{}】", id, BUFFER.size());
        while (BUFFER.size() >= CACHE_SIZE) {
            LOGGER.info("缓冲池满，线程【{}】阻塞", id);
            wait(id);
        }
        //保证互斥访问
        //IM.mutex.acquire();
        BUFFER.add(item);
        //IM.mutex.release();
        signal(id);
        //...     一些别的操作
        lock.unlock();
    }

    @Override
    public void get(String id, Integer item) throws InterruptedException {
        //保证互斥访问
        lock.lock();
        //如果缓冲池满了，就挂起到notFull的阻塞队列中
        LOGGER.info("执行了消费消息，缓冲池的消息的数量：【{}】", BUFFER.size());
        while (BUFFER.size() <= 0) {
            wait(id);
        }
        item = BUFFER.remove(0);
        LOGGER.info("消费了一条消息：【{}】", item);
        //IM.mutex.release();
        signal(id);
        //############### 这里是汉森管程的区别点：signal操作应当是最后一个操作，此处不再允许有别的操作，应当立即退出管程，
        lock.unlock();
    }

}
