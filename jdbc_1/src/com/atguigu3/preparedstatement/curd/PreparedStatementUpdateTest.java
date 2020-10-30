package com.atguigu3.preparedstatement.curd;

import com.atguigu3.util.JDBCUtils;
import org.junit.Test;

import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Properties;

/**
 * 使用PreparedStatement来替换Statement，实现对数据表的增删改操作
 * <p>
 * 增删改； 没有返回任何结果集
 * 查；     需要返回结果集
 *
 * @author 田沛勋
 * @create 2020-09-02 20:53
 */
public class PreparedStatementUpdateTest {
    @Test
    public void testCommonUpdate() {
//        String sql = "delete from customers where id = ?";
//        update(sql, 3);

        String sql = "update `order` set order_name = ? where order_id = ?";//表名order是数据库关键字
        update(sql, "DD", 2);

    }

    //通用的增删改操作(适合于这个test库的可操作的任何一张表)
    public void update(String sql, Object... args) {//sql中占位符的个数与可变形参的长度相同！
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //1.获取数据库连接
            conn = JDBCUtils.getConnection();
            //2.预编译sql语句，返回PreparedStatement实例
            ps = conn.prepareStatement(sql);
            //3.填充占位符
            for (int i = 0; i < args.length; i++) {//可变形参看成数组即可！
                ps.setObject(i + 1, args[i]);//小心参数声明错误
            }

            //4.执行
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5.关闭资源
            JDBCUtils.closeResourse(conn, ps);
        }


    }


    //修改customers表的一条数据
    @Test
    public void testUpdate() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //1.获取数据库的连接
            conn = JDBCUtils.getConnection();

            //2.预编译sql语句，返回PreparedStatement实例
            String sql = "update customers set name = ? where id = ? ";
//        Statement statement = conn.createStatement();
            //预编译：ps这个信使的出生要干什么事情sql，它是携带者的，它是知道的，正因为它知道了，才可能解决sql注入问题。
            ps = conn.prepareStatement(sql);

            //3.填充sql占位符
            ps.setObject(1, "莫扎特");
            ps.setObject(2, 18);

            //4.执行
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5.资源的关闭
            JDBCUtils.closeResourse(conn, ps);
        }

    }


    //向customers表中添加一条记录
    @Test
    public void testInsert() {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
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
            conn = DriverManager.getConnection(url, user, password);
//        System.out.println(conn);

            //4.预编译SQL语句，返回PreparedStatement的实例
            String sql = "Insert into customers(name,email,birth)values(?,?,?)";
            ps = conn.prepareStatement(sql);
            //5.填充占位符
            ps.setString(1, "哪吒");
            ps.setString(2, "nezha@gmail.com");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = sdf.parse("1000-01-01");
            ps.setDate(3, new Date(date.getTime()));

            //6.执行sql操作(PreparedStatedment就是一个信使)
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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


    }


}
