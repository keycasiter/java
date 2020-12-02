package com.github.test;

import com.github.java.learning.spring.spel.SpelService;
import com.github.java.learning.spring.spel.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * @author: <a href=mailto:guanjian@jd.com>guanjian23</a>
 * @date: 2020/07/06 9:24
 * @description:
 */
public class SpelTest extends BaseTest {

    @Autowired
    private SpelService spelService;

    @Test
    public void test() {
        User user = new User("zhangsan");
        user.setAge(10);
        user.setBizId("12312212521512");
        spelService.test(user);
    }
}