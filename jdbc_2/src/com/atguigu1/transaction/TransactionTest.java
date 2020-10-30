package com.atguigu1.transaction;

import com.atguigu1.util.JDBCUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.*;

/**
 * @author 田沛勋
 * @create 2020-09-04 17:59
 */


/*
 * 1.什么叫数据库事务
 * 事务：一组逻辑操作单元,使数据从一种状态变换到另一种状态。
 *      > 一组逻辑操作单元：一个或多个DML操作。
 *
 * 2.事务处理的原则：
 * 事务处理（事务操作）：保证所有事务都作为一个工作单元来执行，即使出现了故障，都不能改变这种执行方式。
 * 当在一个事务中执行多个操作时，要么所有的事务都被提交(commit)，那么这些修改就永久地保存下来；
 * 要么数据库管理系统将放弃所作的所有修改，整个事务回滚(rollback)到最初状态。
 *
 * 3.数据一旦提交，就不可回滚
 *
 * 4.哪些操作导致数据的自动提交？
 *      > DDL操作一旦执行，都会自动提交
 *          >set autocommit = false的方式，对于DDL操作失效
 *      > DML默认情况下，一旦执行，就会自动提交。
 *          >我们可以通过set autocommit = false的方式，取消DML操作的自动提交功能。
 *      > 默认在关闭数据库连接时，会自动提交数据
 *
 *
 * 为确保数据库中数据的一致性，数据的操纵应当是离散的成组的逻辑单元：当它全部完成时，
 * 数据的一致性可以保持，而当这个单元中的一部分操作失败，整个事务应全部视为错误，所
 * 有从起始点以后的操作应全部回退到开始状态。
 *
 *
 */
public class TransactionTest {

    //**********************考虑数据库事务后的转账操作*******************************
    //转账功能：AA用户给BB用户转账100
    @Test
    public void testUpdateWithTransaction() {
        Connection conn = null;
        try {

            conn = JDBCUtils.getConnection();

            System.out.println(conn.getAutoCommit());
            //1.取消(DML操作的)数据的自动提交
            conn.setAutoCommit(false);

            String sql1 = "update user_table set balance = balance - 100 where user = ?";
            update(conn, sql1, "AA");

            //模拟网络异常
//            System.out.println(10 / 0);

            String sql2 = "update user_table set balance = balance + 100 where user = ?";
            update(conn, sql2, "BB");

            System.out.println("转账成功");

            //2. 提交数据
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            //3. 回滚数据
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            //修改其为自动提交数据
            //主要针对于使用数据库连接池的使用
            try {
                conn.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            //4.资源的关闭
            JDBCUtils.closeResourse(conn, null);
        }

    }


    //通用的增删改操作---version 2.0（考虑事务）
    public int update(Connection conn, String sql, Object... args) {//sql中占位符的个数与可变形参的长度相同！
        PreparedStatement ps = null;
        try {
            //1.预编译sql语句，返回PreparedStatement实例
            ps = conn.prepareStatement(sql);
            //2.填充占位符
            for (int i = 0; i < args.length; i++) {//可变形参看成数组即可！
                ps.setObject(i + 1, args[i]);//小心参数声明错误
            }

            //3.执行
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //4.关闭资源
            JDBCUtils.closeResourse(null, ps);//外面传进来的资源不要关
        }

        return 0;
    }

    //**********************未考虑数据库事务情况下的转账操作***********************
    /*
     * 针对于数据表user_table来说：
     * AA用户给BB用户转账100
     *
     * update user_table set balance = balance - 100 where user = "AA";
     * update user_table set balance = balance + 100 where user = "BB";
     *
     */
    @Test
    public void testUpdate() {
        //转账功能：AA用户给BB用户转账100
        String sql1 = "update user_table set balance = balance - 100 where user = ?";
        update(sql1, "AA");

        //模拟网络异常（如，添加的转账用户名不对，或者转账账户不对了，即中间出现异常了）
        System.out.println(10 / 0);

        String sql2 = "update user_table set balance = balance + 100 where user = ?";
        update(sql2, "BB");

        System.out.println("转账成功");

    }


    //通用的增删改操作---version 1.0
    public int update(String sql, Object... args) {//sql中占位符的个数与可变形参的长度相同！
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
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5.关闭资源
            JDBCUtils.closeResourse(conn, ps);
        }

        return 0;
    }


    //****************************************************************************

    //事务1：
    @Test
    public void testTransactionSelect() throws Exception {
        Connection conn = JDBCUtils.getConnection();
        //获取当前连接的事务隔离级别
        System.out.println(conn.getTransactionIsolation());
        //设置数据库的事务的隔离级别：避免脏读
        conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

        //取消自动提交数据
        conn.setAutoCommit(false);

        String sql = "select user,password,balance from user_table where user = ?";
        User user = getInstance(conn, User.class, sql, "CC");

        System.out.println(user);
    }

    //事务2：
    @Test
    public void testTransactionUpdate() throws Exception {
        Connection conn = JDBCUtils.getConnection();
        //取消自动提交数据
        conn.setAutoCommit(false);

        String sql = "update user_table set balance = ? where user = ?";
        update(sql,5000,"CC");

        Thread.sleep(15000);
        System.out.println("修改结束");

    }



    /**
     * @Description 针对于不同表的通用的查询操作，返回数据库表中的一条记录(version 2.0：考虑上事务)
     * @param clazz
     * @param sql
     * @param args
     * @param <T>
     * @return
     */
    public <T> T getInstance(Connection conn,Class<T> clazz,String sql, Object... args) {//泛型方法，T是参数 ，不是参数
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            rs = ps.executeQuery();

            //获取结果集的元数据：ResultSetMetaData
            ResultSetMetaData rsmd = rs.getMetaData();
            //通过ResultSetMetaData获取结果集中的列数
            int columnCount = rsmd.getColumnCount();
            if (rs.next()) {//对应表中的某一行
                T t = clazz.newInstance();

                //处理结果集一行数据中的每一个列
                for (int i = 0; i < columnCount; i++) {//对应表中的列
                    //获取列值
                    Object columnValue = rs.getObject(i + 1);

                    //获取每个列的列名
//                    String columnName = rsmd.getColumnName(i + 1);
                    String columnLabel = rsmd.getColumnLabel(i + 1);

                    //给t对象指定的columnName属性，赋值为columnValue：通过反射
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResourse(null, ps, rs);
        }

        return null;
    }


}
