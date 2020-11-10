package com.github.test;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * created by guanjian on 2019/11/4 10:54
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-config.xml")
public class BaseTest {

    @BeforeClass
    public static void init() {

    }

    @Before
    public void initMocks() {
//        MockitoAnnotations.initMocks(this);
    }
}
