package com.github.java.learning.logback;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * created by guanjian on 2020/11/10 20:36
 */
public class LogbackTest {

    private final static ch.qos.logback.classic.Logger LOGGER = (ch.qos.logback.classic.Logger)LoggerFactory.getLogger(LogbackTest.class);

    public static void main(String[] args) throws InterruptedException {
        //level - info
        System.out.println("INFO\n");
        LOGGER.setLevel(Level.INFO);
        LOGGER.trace("trace");
        LOGGER.debug("debug");
        LOGGER.info("info");
        LOGGER.warn("warn");
        LOGGER.error("error");

        Thread.currentThread().join();

        //level - debug
        System.out.println("DEBUG\n");
        LOGGER.setLevel(Level.DEBUG);
        LOGGER.trace("trace");
        LOGGER.debug("debug");
        LOGGER.info("info");
        LOGGER.warn("warn");
        LOGGER.error("error");

        Thread.currentThread().join();

        //level - warn
        System.out.println("WARN\n");
        LOGGER.setLevel(Level.WARN);
        LOGGER.trace("trace");
        LOGGER.debug("debug");
        LOGGER.info("info");
        LOGGER.warn("warn");
        LOGGER.error("error");

        Thread.currentThread().join();
    }
}
