package com.github.java.learning.h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * created by guanjian on 2020/11/23 17:16
 */
public class TestH2 {

    /**
     * 以嵌入式(本地)连接方式连接H2数据库
     */
    //private static final String JDBC_URL = "jdbc:h2:C:/H2/abc";

    /**
     * 使用TCP/IP的服务器模式(远程连接)方式连接H2数据库(推荐)
     */
    private static final String JDBC_URL_REMOTE = "jdbc:h2:tcp://localhost/~/test";
    /**
     * 使用本地模式（内存模式）方式连接H2数据库
     */
    private static final String JDBC_URL_LOCAL = "jdbc:h2:C://localhost/~/test";

    private static final String USER = "sa";
    private static final String PASSWORD = "";
    private static final String DRIVER_CLASS = "org.h2.Driver";

    public static void main(String[] args) throws Exception {
        Class.forName(DRIVER_CLASS);
        Connection conn = DriverManager.getConnection(JDBC_URL_REMOTE, USER, PASSWORD);
        Statement statement = conn.createStatement();
        statement.execute("DROP TABLE IF EXISTS USER_INF");
        statement.execute("CREATE TABLE USER_INF(id INTEGER PRIMARY KEY ,name VARCHAR(100), sex VARCHAR(2))");

        statement.executeUpdate("INSERT INTO USER_INF VALUES(1, 'tom', '男') ");
        statement.executeUpdate("INSERT INTO USER_INF VALUES(2, 'jack', '女') ");
        statement.executeUpdate("INSERT INTO USER_INF VALUES(3, 'marry', '男') ");
        statement.executeUpdate("INSERT INTO USER_INF VALUES(4, 'lucy', '男') ");

        ResultSet resultSet = statement.executeQuery("select * from USER_INF");

        while (resultSet.next()) {
            System.out.println(resultSet.getInt("id") + ", " + resultSet.getString("name") +
                    ", " + resultSet.getString("sex"));
        }

        statement.close();
        conn.close();
    }
}
