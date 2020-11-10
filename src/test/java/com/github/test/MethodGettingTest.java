package com.github.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author: <a href=mailto:guanjian@jd.com>guanjian23</a>
 * @date: 2020/07/06 9:24
 * @description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-config.xml")
public class MethodGettingTest {

    /**
     * Spring容器注入
     */
    @Autowired
    private ResourceLoader resourceLoader;

    @Test
    public void test() throws IOException, ClassNotFoundException {

        ResourcePatternResolver resolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
        MetadataReaderFactory metaReader = new CachingMetadataReaderFactory(resourceLoader);
        //配置包路径，模糊匹配目标类
        Resource[] resources = resolver.getResources("classpath*:*.class");

        for (Resource r : resources) {
            MetadataReader reader = metaReader.getMetadataReader(r);
            //获取类名
            Class cls = Class.forName(reader.getClassMetadata().getClassName());
            //获取类的方法名
            Method[] methods = cls.getDeclaredMethods();
            for (Method m : methods) {
                System.out.println(
                        String.format("%s.%s",
                                cls.getName(),
                                m.getName()
                        )
                );
            }
        }

    }
}