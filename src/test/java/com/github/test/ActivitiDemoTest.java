package com.github.test;

import org.activiti.engine.RuntimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * @author: <a href=mailto:guanjian@jd.com>guanjian23</a>
 * @date: 2020/07/06 9:24
 * @description:
 */
public class ActivitiDemoTest extends BaseTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(ActivitiDemoTest.class);

    @Resource
    private RuntimeService runtimeService;
}