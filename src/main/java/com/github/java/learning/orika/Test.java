package com.github.java.learning.orika;

/**
 * created by guanjian on 2020/11/9 10:21
 */
public class Test {

    private static BeanAssembler beanAssembler = new BeanAssembler();

    public static void main(String[] args) {

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
}
