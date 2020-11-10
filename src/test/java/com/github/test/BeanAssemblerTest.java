package com.github.test;

import com.github.java.learning.clazz.ClassUtil;
import com.github.java.learning.orika.BeanAssembler;
import com.github.java.learning.orika.DTO;
import com.github.java.learning.orika.Vo;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * created by guanjian on 2020/11/10 10:32
 */
public class BeanAssemblerTest extends BaseTest {

    @Resource
    private BeanAssembler beanAssembler;
    @Resource
    private ApplicationContext applicationContext;

    @Test
    public void testBeanCopyNeedInit() {
        DTO dto = new DTO();
        dto.setId(111L);
        dto.setName("张三");
        dto.setSeq(222);


        //第一次，会触发缓存加载逻辑，耗时较长
        long start = System.currentTimeMillis();
        Vo vo = beanAssembler.copy(dto, Vo.class);
        long end = System.currentTimeMillis();
        System.out.format("cost: %s  vo: %s \n", end - start, vo.toString());

        //第二次，直接利用缓存数据，效率高
        long start1 = System.currentTimeMillis();
        beanAssembler.copy(dto, Vo.class);
        long end1 = System.currentTimeMillis();

        System.out.format("cost: %s  vo: %s \n", end1 - start1, vo.toString());
    }

    @Test
    public void testBeanCopyFinishInit() {
        ClassMapBuilder classMapBuilder = BeanAssembler.getMapperFactory().classMap(Vo.class, DTO.class);
        classMapBuilder.byDefault().register();

        Vo vo = new Vo();
        vo.setId(111L);
        vo.setName("test000");
        vo.setSeq(222);

        MapperFacade mapper = BeanAssembler.getMapperFacade();

        long start1 = System.currentTimeMillis();
        DTO dto = mapper.map(vo, DTO.class);
        long end1 = System.currentTimeMillis();
        System.out.format("cost: %s  vo: %s \n", end1 - start1, dto.toString());
    }

    @Test
    public void testScanMapper() {
        String[] beans = applicationContext.getBeanDefinitionNames();
        if (null == beans || 0 == beans.length) return;

        Stream.of(beans).forEach(beanName -> {
            Class<?> clazz = applicationContext.getType(beanName);
            if (null == clazz) return;

            System.out.println("class：" + clazz);

            Method[] methods = clazz.getMethods();

            Stream.of(Optional.ofNullable(methods).orElse(new Method[]{})).forEach(x -> {

            });
        });

    }

    @Test
    public void test() throws Exception {
        //找到当前ClassLoader所有加载的类
        List<Class> list = ClassUtil.loadClassByLoader(this.getClass().getClassLoader());
        list.forEach(clz -> {
            Method[] methods = clz.getMethods();

            Stream.of(methods).forEach(method -> {
                StackTraceElement[] ste = Thread.currentThread().getStackTrace();
                Stream.of(ste).forEach(x -> {
                    System.out.println("==================");
                    System.out.println(x.getClassName());
                    System.out.println(x.getMethodName());
                    System.out.println(x.getFileName());
                    System.out.println(x.getLineNumber());
                });
            });
        });
    }
}
