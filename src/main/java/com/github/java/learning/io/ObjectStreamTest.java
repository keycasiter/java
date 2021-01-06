package com.github.java.learning.io;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * created by guanjian on 2021/1/6 18:18
 */
public class ObjectStreamTest {

    private static final String TMP_FILE = "person.tmp";

    public static void main(String[] args) {
        testWrite();
        testRead();
    }

    private static void testWrite() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(TMP_FILE));
            //1、基本数据类型
            out.writeBoolean(true);
            out.writeByte((byte) 65);
            out.writeChar('a');
            out.writeInt(20131015);
            out.writeFloat(3.14F);
            out.writeDouble(1.414D);
            out.writeUTF("我是字符串");
            //2、复杂数据结构
            Map<String, String> map = new HashMap<String, String>();
            map.put("one", "red");
            map.put("two", "green");
            map.put("three", "blue");
            out.writeObject(map);
            //3、写入自定义的Person对象，实现了Serializable接口
            Person person = new Person("zhangsan", 30, true, 'm');
            out.writeObject(person);

            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * ObjectInputStream 测试函数
     */
    private static void testRead() {
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(TMP_FILE));
            //1、基本数据类型
            System.out.printf("boolean:%b\n", in.readBoolean());
            System.out.printf("byte:%d\n", (in.readByte() & 0xff));
            System.out.printf("char:%c\n", in.readChar());
            System.out.printf("int:%d\n", in.readInt());
            System.out.printf("float:%f\n", in.readFloat());
            System.out.printf("double:%f\n", in.readDouble());
            System.out.printf("String:%s\n", in.readUTF());
            //2、复杂数据结构
            HashMap map = (HashMap) in.readObject();
            Iterator<?> iter = map.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                System.out.printf("%-6s -- %s\n", entry.getKey(), entry.getValue());
            }
            //3、读取Person对象，实现了Serializable接口
            Person person = (Person) in.readObject();
            System.out.println("Person: " + person);

            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class Person implements Serializable {

        private static final long serialVersionUID = 5512460159096320796L;

        private String name;
        private int age;
        private boolean isAlive;
        private char gender;

        public Person(String name, int age, boolean isAlive, char gender) {
            this.name = name;
            this.age = age;
            this.isAlive = isAlive;
            this.gender = gender;
        }

        public Person() {
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", isAlive=" + isAlive +
                    ", gender=" + gender +
                    '}';
        }
    }
}
