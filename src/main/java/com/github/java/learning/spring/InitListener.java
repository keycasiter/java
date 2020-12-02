package com.github.java.learning.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * created by guanjian on 2020/11/17 9:22
 */
//@Component("initListener")
public class InitListener implements ApplicationContextAware, ServletContextAware, BeanFactoryPostProcessor, BeanPostProcessor,
        InitializingBean, ApplicationListener<ContextRefreshedEvent> {

    private final static Logger LOGGER = LoggerFactory.getLogger(InitListener.class);

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        LOGGER.info("BeanFactoryPostProcessor====postProcessBeanFactory");
    }

    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        LOGGER.info("BeanPostProcessor====postProcessBeforeInitialization");
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        LOGGER.info("BeanPostProcessor====postProcessAfterInitialization");
        return o;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        LOGGER.info("ApplicationContextAware====setApplicationContext");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        LOGGER.info("InitializingBean====afterPropertiesSet");
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        LOGGER.info("ApplicationListener====onApplicationEvent");
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        LOGGER.info("ServletContextAware====setServletContext");
    }
}
