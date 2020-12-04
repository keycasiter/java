package com.github.java.learning.spring.transaction;

import org.springframework.transaction.support.TransactionSynchronization;

/**
 * created by guanjian on 2020/12/4 9:07
 */
public class DiyTransactionSynchronization implements TransactionSynchronization {
    @Override
    public void suspend() {
        System.out.println("==========suspend==========");
    }

    @Override
    public void resume() {
        System.out.println("==========resume==========");
    }

    @Override
    public void flush() {
        System.out.println("==========flush==========");
    }

    @Override
    public void beforeCommit(boolean b) {
        System.out.println("==========beforeCommit==========");
    }

    @Override
    public void beforeCompletion() {
        System.out.println("==========beforeCompletion==========");
    }

    @Override
    public void afterCommit() {
        System.out.println("==========afterCommit==========");
    }

    @Override
    public void afterCompletion(int i) {
        System.out.println("==========afterCompletion==========");
    }
}
