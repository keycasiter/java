package com.github.test;

import com.github.java.learning.spring.aop.AopService;
import com.github.java.learning.spring.spel.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: <a href=mailto:guanjian@jd.com>guanjian23</a>
 * @date: 2020/07/06 9:24
 * @description:
 */
public class AopTest extends BaseTest {

    @Autowired
    private AopService aopService;

    @Test
    public void test() {
        User user = new User("zhangsan");
        user.setAge(10);
        user.setBizId("12312212521512");
        aopService.test(user);
    }
}