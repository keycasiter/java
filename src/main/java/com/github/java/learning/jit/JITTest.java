package com.github.java.learning.jit;

/**
 * @author: <a href=mailto:guanjian@jd.com>guanjian23</a>
 * @date: 2020/11/06 9:52
 * @description: JIT编译测试
 *
 *
 * -server 服务端模式
 * -Xint 解释器执行
 * -Xcomp 编译器执行
 * -Xmixed 混合模式
 * -XX:+TieredCompilation 打开多级编译策略（JDK1.8是默认开启的，如果关闭则 -XX:-TieredCompilation）
 * -XX:CompileThreshold 编译阈值 -client模式下为1500，-server模式下为10000
 * -XX:+PrintCompilation 开启编译日志打印
 * –XX:ReservedCodeCacheSize 代码缓存大小 （64-bit server with Tiered Compilation, Java 8 ，默认是240mb）
 */
public class JITTest {

    /**
     * 模拟耗时方法
     */
    public static double cal() {
        double d1 = 0.618d;
        double d2 = 6.18d;
        double d3 = 0d;

        for (int i = 0; i < 50000; i++) {
            d3 += (d1 * d2) / (d1 * d2);
        }
        return d3;
    }

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        System.out.format("start : %s \n", start);

        for (int i = 0; i < 50000; i++) {
            cal();
        }

        long end = System.currentTimeMillis();
        System.out.format("end : %s \n", end);
        System.out.format("cost : %s\n", end - start);
    }

    /**
     *
     * 测试-Xint解释器执行 vs -Xcomp编译器执行性能
     *
     * 50000次，-XX:CompileThreshold为10000触发即时编译，-XX:+TieredCompilation JDK8默认开启分层编译
     *
     * -server -Xint cost：38077ms
     * -server -Xcomp cost：4311ms
     *
     * 结论：-Xcomp编译执行更快
     *
     *
     * 测试-XX:CompileThreshold的影响
     *
     * 100000次，-XX:+TieredCompilation JDK8默认开启分层编译
     *
     * -server -Xcomp -XX:CompileThreshold=10 cost:7017
     *
     * -server -Xcomp -XX:CompileThreshold=100000 cost:7686
     *
     * 结论：越早触发优化越能提升性能，由于编译过程也有性能损耗，所以阈值要合理，不然适得其反
     *
     *
     *
     * 测试开启与关闭分层编译性能
     *
     * 50000次，-XX:+TieredCompilation JDK8默认开启分层编译
     * -server -Xcomp -XX:CompileThreshold=100 -XX:-TieredCompilation cost : 1568ms
     * -server -Xcomp -XX:CompileThreshold=100 -XX:+TieredCompilation cost : 4281ms
     *
     * 结论：关闭分层编译后，减少了探测优化升级过程，直接编译优化，性能有提升
     */
}
