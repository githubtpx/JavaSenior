package com.atguigu4.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author 田沛勋
 * @create 2020-09-07 19:51
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
     * @Description 使用dbutils.jar中提供的DbUtils工具类，实现资源的关闭
     * @param conn
     * @param ps
     */
    public static void closeResourse1(Connection conn, Statement ps){
//        try {
//            DbUtils.close(conn);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        try {
//            DbUtils.close(ps);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
        DbUtils.closeQuietly(conn);
        DbUtils.closeQuietly(ps);
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
    public static void closeResourse1(Connection conn, Statement ps, ResultSet rs){
        DbUtils.closeQuietly(conn);
        DbUtils.closeQuietly(rs);
        DbUtils.closeQuietly(ps);
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

    /**
     * @Description 使用C3P0数据库连接池技术
     * @return
     */

    //数据库连接池只需提供一个即可！
    private static ComboPooledDataSource cpds = new ComboPooledDataSource("helloc3p0");
    public static Connection getConnection1() throws SQLException {
        Connection conn = cpds.getConnection();

        return conn;
    }

    /**
     * @Description 使用DBCP数据库连接池技术，获取数据库连接
     * @return
     * @throws Exception
     */

    //创建了DBCP的数据库连接池
    private static DataSource source;
    static{
        try {
            Properties pros = new Properties();
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("dbcp.properties");
            pros.load(is);
            source = BasicDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection2() throws Exception {
        Connection conn = source.getConnection();
        return conn;
    }


    /**
     * @Description 使用Druid数据库连接池技术
     */
    private static DataSource source1;
    static{
        try {
            Properties pros = new Properties();
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
            pros.load(is);
            source1 = DruidDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection3() throws Exception {
        Connection conn = source1.getConnection();
        return conn;
    }

}
