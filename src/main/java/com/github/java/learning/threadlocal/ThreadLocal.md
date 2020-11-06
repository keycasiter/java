## 概念
- 它能让线程拥有了自己内部独享的变量
- 每一个线程可以通过get、set方法去进行操作
- 可以覆盖initialValue方法指定线程独享的值
- 通常会用来修饰类里private static final的属性，为线程设置一些状态信息，例如user ID或者Transaction ID
- 每一个线程都有一个指向threadLocal实例的弱引用，只要线程一直存活或者该threadLocal实例能被访问到，都不会被垃圾回收清理掉

## get()源码

```java
public T get() {
    Thread t = Thread.currentThread();
    ThreadLocalMap map = getMap(t);
    if (map != null) {
        ThreadLocalMap.Entry e = map.getEntry(this);
        if (e != null) {
            @SuppressWarnings("unchecked")
            T result = (T)e.value;
            return result;
        }
    }
    return setInitialValue();
}
 ```
 - 获取当前线程内部的ThreadLocalMap
 - map存在则获取当前ThreadLocal对应的value值
 - map不存在或者找不到value值，则调用setInitialValue，进行初始化

## setInitialValue()源码
```java
private T setInitialValue() {
    T value = initialValue();
    Thread t = Thread.currentThread();
    ThreadLocalMap map = getMap(t);
    if (map != null)
        map.set(this, value);
    else
        createMap(t, value);
    return value;
}
```
 - 调用initialValue方法，获取初始化值【调用者通过覆盖该方法，设置自己的初始化值】
 - 获取当前线程内部的ThreadLocalMap
 - map存在则把当前ThreadLocal和value添加到map中
 - map不存在则创建一个ThreadLocalMap，保存到当前线程内部
 
## get()方法时序图
![avatar](http://static.iocoder.cn/9fc1a0aa41bdf49444e953ce89f9d1f3)

## set()源码
```java
public void set(T value) {
    Thread t = Thread.currentThread();
    ThreadLocalMap map = getMap(t);
    if (map != null)
        map.set(this, value);
    else
        createMap(t, value);
}
```
- 获取当前线程内部的ThreadLocalMap
- map存在则把当前ThreadLocal和value添加到map中
- map不存在则创建一个ThreadLocalMap，保存到当前线程内部

## remove()源码
```java
public void remove() {
    ThreadLocalMap m = getMap(Thread.currentThread());
    if (m != null)
     m.remove(this);
}
```
## remove()方法时序图
![avatar](http://static.iocoder.cn/3833cb28a0f535385ecced8774f2aac4)
## 内存泄露问题
参考MemoryLeak中的demo
![avatar](http://static.iocoder.cn/2ba5a00b11a13141f27377b6c94b94fa)
- t为创建TestClass对象返回的引用，临时变量，在一次for循环后就执行出栈了
- thread为创建Thread对象返回的引用，run方法在执行过程中，暂时不会执行出栈
![avatar](http://static.iocoder.cn/4e5f0bb74313f968e1413db0c8393f4d)
因为remove方法将referent和value都被设置为null，所以ThreadLocal@540和Memory$TestClass@538对应的内存地址都变成不可达，Java垃圾回收自然就会回收这片内存，从而不会出现内存泄漏的错误。
## ThreadLocalMap
- ThreadLocalMap是一个自定义的hash map，专门用来保存线程的thread local变量
- 它的操作仅限于ThreadLocal类中，不对外暴露
- 这个类被用在Thread类的私有变量threadLocals和inheritableThreadLocals上
- 为了能够保存大量且存活时间较长的threadLocal实例，hash table entries采用了WeakReferences作为key的类型
- 一旦hash table运行空间不足时，key为null的entry就会被清理掉
```java
static class ThreadLocalMap {

    // hash map中的entry继承自弱引用WeakReference，指向threadLocal对象
    // 对于key为null的entry，说明不再需要访问，会从table表中清理掉
    // 这种entry被成为“stale entries”
    static class Entry extends WeakReference<ThreadLocal<?>> {
        /** The value associated with this ThreadLocal. */
        Object value;

        Entry(ThreadLocal<?> k, Object v) {
            super(k);
            value = v;
        }
    }

    private static final int INITIAL_CAPACITY = 16;

    private Entry[] table;

    private int size = 0;

    private int threshold; // Default to 0

    private void setThreshold(int len) {
        threshold = len * 2 / 3;
    }

    ThreadLocalMap(ThreadLocal<?> firstKey, Object firstValue) {
        table = new Entry[INITIAL_CAPACITY];
        int i = firstKey.threadLocalHashCode & (INITIAL_CAPACITY - 1);
        table[i] = new Entry(firstKey, firstValue);
        size = 1;
        setThreshold(INITIAL_CAPACITY);
    }
}
```
## 如何降低哈希冲突
### 哈希值生成算法
```java
private static AtomicInteger nextHashCode = new AtomicInteger();

private static final int HASH_INCREMENT = 0x61c88647;

private static int nextHashCode() {
    return nextHashCode.getAndAdd(HASH_INCREMENT);
}
```
哈希值生成算法：HASH_INCREMENT是参与哈希计算的哈希魔数，这里是16进制的0x61c88647，转换为10进制就是-1640531527,
每次自增HASH_INCREMENT进行生成，这里使用了AtomicInteger保证了线程安全
### 索引生成算法
索引生成算法：key.threadLocalHashCode & (len-1)
- key.threadLocalHashCode即通过HASH_INCREMENT自增1得到的哈希值
- len即当前哈希槽的容量，初始化默认是16，即时哈希槽扩容也是保持16的2倍，可以理解为2的N次方
- 综上，索引生成是（HASH_INCREMENT自增HASH_INCREMENT）&（2的N次方-1）
### 黄金分割数
ThreadLocalMap采用黄金分割数的方式，大大降低了哈希冲突的情况。
![avatar](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9pbWcyMDE4LmNuYmxvZ3MuY29tL2Jsb2cvMTQxMjMzMS8yMDIwMDIvMTQxMjMzMS0yMDIwMDIxNDEyNDM0MDIxNS02ODgwNDk1MjIucG5n?x-oss-process=image/format,png)
- 由于HASH_INCREMENT是int类型，它的数据范围最小值：Integer.MIN_VALUE= -2147483648 （-2的31次方），最大值：Integer.MAX_VALUE= 2147483647  （2的31次方-1），
因此根据黄金分割比例0.618进行分割，int取值范围的黄金分割数是-1640531527
- （HASH_INCREMENT自增HASH_INCREMENT）&（2的N次方-1）是索引生成的算法，因为是与操作的位运算，当两位都为1时结果才为1，所以（HASH_INCREMENT自增HASH_INCREMENT）和（2的N次方-1）
的计算项数据会在此运算规则的影响下产生数学效应
- int的黄金分割数是按照int的最大值2147483647乘以黄金分割比例Math.sqrt(5) - 1) / 2 计算得来的，最终int的黄金分割数为-1640531527

##### （2的N次方-1）的二进制
```java
2的1次方 	num:1 	             binary:00000000000000000000000000000001 
2的2次方 	num:3 	             binary:00000000000000000000000000000011 
2的3次方 	num:7 	             binary:00000000000000000000000000000111 
2的4次方 	num:15 	             binary:00000000000000000000000000001111 
2的5次方 	num:31 	             binary:00000000000000000000000000011111 
2的6次方 	num:63 	             binary:00000000000000000000000000111111 
2的7次方 	num:127 	         binary:00000000000000000000000001111111 
2的8次方 	num:255 	         binary:00000000000000000000000011111111 
2的9次方 	num:511 	         binary:00000000000000000000000111111111 
2的10次方    num:1023 	         binary:00000000000000000000001111111111 
2的11次方    num:2047 	         binary:00000000000000000000011111111111 
2的12次方    num:4095 	         binary:00000000000000000000111111111111 
2的13次方    num:8191 	         binary:00000000000000000001111111111111 
2的14次方    num:16383 	         binary:00000000000000000011111111111111 
2的15次方    num:32767 	         binary:00000000000000000111111111111111 
2的16次方    num:65535 	         binary:00000000000000001111111111111111 
2的17次方    num:131071 	         binary:00000000000000011111111111111111 
2的18次方    num:262143 	         binary:00000000000000111111111111111111 
2的19次方    num:524287 	         binary:00000000000001111111111111111111 
2的20次方    num:1048575 	 	 binary:00000000000011111111111111111111 
2的21次方    num:2097151 	 	 binary:00000000000111111111111111111111 
2的22次方    num:4194303 	 	 binary:00000000001111111111111111111111 
2的23次方    num:8388607 	 	 binary:00000000011111111111111111111111 
2的24次方    num:16777215 	 	 binary:00000000111111111111111111111111 
2的25次方    num:33554431 	 	 binary:00000001111111111111111111111111 
2的26次方    num:67108863 	 	 binary:00000011111111111111111111111111 
2的27次方    num:134217727 	 	 binary:00000111111111111111111111111111 
2的28次方    num:268435455 	 	 binary:00001111111111111111111111111111 
2的29次方    num:536870911 	 	 binary:00011111111111111111111111111111 
2的30次方    num:1073741823 	     binary:00111111111111111111111111111111
```
不难发现，2的幂次方-1的数字的二进制有一个特点那就是，高位都是0，,低位都是1
##### （HASH_INCREMENT自增HASH_INCREMENT）的二进制
```java
id:1 	 hashCode:1640531527 	 binary:01100001110010001000011001000111 
id:2 	 hashCode:-1013904242 	 binary:11000011100100010000110010001110 
id:3 	 hashCode:626627285 	 binary:00100101010110011001001011010101 
id:4 	 hashCode:-2027808484 	 binary:10000111001000100001100100011100 
id:5 	 hashCode:-387276957 	 binary:11101000111010101001111101100011 
id:6 	 hashCode:1253254570 	 binary:01001010101100110010010110101010 
id:7 	 hashCode:-1401181199 	 binary:10101100011110111010101111110001 
id:8 	 hashCode:239350328 	 binary:00001110010001000011001000111000 
id:9 	 hashCode:1879881855 	 binary:01110000000011001011100001111111 
id:10 	 hashCode:-774553914 	 binary:11010001110101010011111011000110 
id:11 	 hashCode:865977613 	 binary:00110011100111011100010100001101 
id:12 	 hashCode:-1788458156 	 binary:10010101011001100100101101010100 
id:13 	 hashCode:-147926629 	 binary:11110111001011101101000110011011 
id:14 	 hashCode:1492604898 	 binary:01011000111101110101011111100010 
id:15 	 hashCode:-1161830871 	 binary:10111010101111111101111000101001 
id:16 	 hashCode:478700656 	 binary:00011100100010000110010001110000 
id:17 	 hashCode:2119232183 	 binary:01111110010100001110101010110111 
id:18 	 hashCode:-535203586 	 binary:11100000000110010111000011111110 
id:19 	 hashCode:1105327941 	 binary:01000001111000011111011101000101 
id:20 	 hashCode:-1549107828 	 binary:10100011101010100111110110001100 
id:21 	 hashCode:91423699 	     binary:00000101011100110000001111010011 
id:22 	 hashCode:1731955226 	 binary:01100111001110111000101000011010 
id:23 	 hashCode:-922480543 	 binary:11001001000001000001000001100001 
id:24 	 hashCode:718050984 	 binary:00101010110011001001011010101000 
id:25 	 hashCode:-1936384785 	 binary:10001100100101010001110011101111 
id:26 	 hashCode:-295853258 	 binary:11101110010111011010001100110110 
id:27 	 hashCode:1344678269 	 binary:01010000001001100010100101111101 
id:28 	 hashCode:-1309757500 	 binary:10110001111011101010111111000100 
id:29 	 hashCode:330774027 	 binary:00010011101101110011011000001011 
id:30 	 hashCode:1971305554 	 binary:01110101011111111011110001010010 
id:31 	 hashCode:-683130215 	 binary:11010111010010000100001010011001
```
以上通过nextHashCode.getAndAdd(HASH_INCREMENT)生成32次hashCode。
由于（2的N次方-1）的二进制所有位均为1，且是&运算，因此hashCode越具有散列性，最终索引值也会具有很好的散列性，哈希碰撞的可能性就会减少。
在（2的N次方-1）的二进制也就是length固定的情况下，低位都是1，高位都是0，因此，hashCode高位或低位相同太多会导致严重碰撞，一定要如上图这样到高低位都能具有很好的差异性参与计算才可以减少碰撞

```java
id:1 	 hashCode:1640531527 	 index:7 
id:2 	 hashCode:-1013904242 	 index:14 
id:3 	 hashCode:626627285 	 index:21 
id:4 	 hashCode:-2027808484 	 index:28 
id:5 	 hashCode:-387276957 	 index:3 
id:6 	 hashCode:1253254570 	 index:10 
id:7 	 hashCode:-1401181199 	 index:17 
id:8 	 hashCode:239350328 	 index:24 
id:9 	 hashCode:1879881855 	 index:31 
id:10 	 hashCode:-774553914 	 index:6 
id:11 	 hashCode:865977613 	 index:13 
id:12 	 hashCode:-1788458156 	 index:20 
id:13 	 hashCode:-147926629 	 index:27 
id:14 	 hashCode:1492604898 	 index:2 
id:15 	 hashCode:-1161830871 	 index:9 
id:16 	 hashCode:478700656 	 index:16 
id:17 	 hashCode:2119232183 	 index:23 
id:18 	 hashCode:-535203586 	 index:30 
id:19 	 hashCode:1105327941 	 index:5 
id:20 	 hashCode:-1549107828 	 index:12 
id:21 	 hashCode:91423699 	     index:19 
id:22 	 hashCode:1731955226 	 index:26 
id:23 	 hashCode:-922480543 	 index:1 
id:24 	 hashCode:718050984 	 index:8 
id:25 	 hashCode:-1936384785 	 index:15 
id:26 	 hashCode:-295853258 	 index:22 
id:27 	 hashCode:1344678269 	 index:29 
id:28 	 hashCode:-1309757500 	 index:4 
id:29 	 hashCode:330774027 	 index:11 
id:30 	 hashCode:1971305554 	 index:18 
id:31 	 hashCode:-683130215 	 index:25 
id:32 	 hashCode:957401312 	 index:0
```
以上是length为32时，生成的32次索引值的情况，发现索引值分布非常均匀，没有出现碰撞。

## 如何解决哈希冲突
当出现哈希冲突时，它的做法看是否是同一个对象或者是是否可以替换，否则往后移动一位，继续判断
```java
private static int nextIndex(int i, int len) {
    return ((i + 1 < len) ? i + 1 : 0);
}
```

## 参考
https://www.cnblogs.com/wang-meng/p/12856648.html
https://blog.csdn.net/zjcsuct/article/details/104310194
http://www.iocoder.cn/JDK/ThreadLocal/