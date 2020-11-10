package com.github.java.learning.orika;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;

/**
 * @author: <a href=mailto:guanjian@jd.com>guanjian23</a>
 * @date: 2020/05/01 15:43
 * @description: 提供bean的各种组装能力
 */
@Component
public class BeanAssembler {

    private final static MapperFactory MAPPER_FACTORY = new DefaultMapperFactory.Builder().build();

    private final static MapperFacade MAPPER_FACADE = MAPPER_FACTORY.getMapperFacade();

    public static MapperFactory getMapperFactory() {
        return MAPPER_FACTORY;
    }

    public static MapperFacade getMapperFacade() {
        return MAPPER_FACADE;
    }

    /**
     * 复制简单对象
     *
     * @param s 复制对象
     * @param d 对象类
     * @return
     */
    public <S, D> D copy(S s, Class<D> d) {
        return MAPPER_FACADE.map(s, d);
    }

    /**
     * 复制简单对象
     *
     * @param s 复制对象
     * @param d 对象类
     * @return
     */
    public <S, D> D copyObj(S s, Class<D> d) {
        return MAPPER_FACADE.map(s, d);
    }
}
