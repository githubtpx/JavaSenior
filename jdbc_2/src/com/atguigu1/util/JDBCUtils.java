package com.atguigu1.util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 操作数据的工具类:
 * 1.方法
 * 2.方法且是静态的
 *
 * 面向接口编程的思想：没有第三方的API。都是java类库中的接口。
 * @author 田沛勋
 * @create 2020-09-02 21:21
 */

public class JDBCUtils {

    /**
     * @Description 获取数据库的连接
     * @return
     * @throws Exception
     */
    public static Connection getConnection() throws Exception {
        //1.读取配置文件中的4个基本信息(可以通过类的加载器获取)
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");

        Properties pros = new Properties();
        pros.load(is);

        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        String url = pros.getProperty("url");
        String driverClass = pros.getProperty("driverClass");

        //2.加载驱动
        Class.forName(driverClass);

        //3.获取连接
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }


    /**
     * @Description 关闭连接和Statement资源的操作
     * @param conn
     * @param ps
     */
    public static void closeResourse(Connection conn, Statement ps){
        //7.资源的关闭
        //避免对象空指针调用
        try {
            if (ps != null)
                ps.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    /**
     * @Description 关闭资源操作
     * @param conn
     * @param ps
     * @param rs
     */
    public static void closeResourse(Connection conn, Statement ps, ResultSet rs){
        try {
            if (ps != null)
                ps.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
