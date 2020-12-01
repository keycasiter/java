package com.github.test;

import com.github.java.learning.spring.spel.SpelService;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author: <a href=mailto:guanjian@jd.com>guanjian23</a>
 * @date: 2020/07/06 9:24
 * @description:
 */
public class SpelTest extends BaseTest {

    @Resource
    private SpelService spelService;

    @Test
    public void test() {
        spelService.test();
    }
}