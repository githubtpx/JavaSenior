package com.atguigu5.dbutils;

import com.atguigu2.bean.Customer;
import com.atguigu4.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * commons-dbutils是Apache组织提供的一个开源JDBC工具类库，封装了针对于数据库的增删改查操作。
 *
 * @author 田沛勋
 * @create 2020-09-08 8:59
 */
public class QueryRunnerTest {

    //测试Apache的开源库DBUtiles工具类的关于数据库表的插入操作
    //测试插入
    @Test
    public void testInsert() {
        int insertCount = 0;
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection3();
            String sql = "insert into customers(name,email,birth) values(?,?,?)";

            insertCount = runner.update(conn, sql, "蔡徐坤", "caixukun@126.com", "1997-09-10");
            System.out.println("添加了" + insertCount + "条记录");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResourse(conn, null);
        }
    }


    //测试查询
    /*
     * BeanHandler是ResultSetHandler结果集处理器接口的实现类，用于封装表中的一条记录。返回java具体类的一个对象。
     */
    @Test
    public void testQuery1() {
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection3();
            String sql = "select id,name,email,birth from customers where id = ?";

            //返回一个类的对象，BeanHandler是ResultSetHandler结果集处理器接口的实现类
            BeanHandler<Customer> handler = new BeanHandler<Customer>(Customer.class);
            Customer customer = runner.query(conn, sql, handler, 23);
            System.out.println(customer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //防止内存泄漏，关闭连接
            JDBCUtils.closeResourse(conn, null);
        }

    }


    /*
     * BeanListHandler是ResultSetHandler结果集处理器接口的实现类，用于封装表中的多条记录构成的集合。
     */
    @Test
    public void testQuery2() throws Exception {
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection3();
            String sql = "select id,name,email,birth from customers where id < ?";

            BeanListHandler<Customer> handler = new BeanListHandler<>(Customer.class);
            List<Customer> list = runner.query(conn, sql, handler, 23);
            list.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //防止内存泄漏，关闭连接
            JDBCUtils.closeResourse(conn, null);
        }
    }


    /*
     * MapHandler是ResultSetHandler结果集处理器接口的实现类，对应表中的一条记录。
     * 将字段及相应字段值作为Map中的key和value
     */
    @Test
    public void testQuery3() {
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection3();
            String sql = "select id,name,email,birth from customers where id = ?";

            //返回一个类的对象，BeanHandler是ResultSetHandler结果集处理器接口的实现类
            MapHandler handler = new MapHandler();
            Map<String, Object> map = runner.query(conn, sql, handler, 23);
            System.out.println(map);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //防止内存泄漏，关闭连接
            JDBCUtils.closeResourse(conn, null);
        }

    }



    /*
     * MapListHandler是ResultSetHandler结果集处理器接口的实现类，对应表中的多条记录。
     * 将字段及相应字段值作为Map中的key和value。将这些map添加到List中。
     */
    @Test
    public void testQuery4() {
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection3();
            String sql = "select id,name,email,birth from customers where id < ?";

            //返回一个类的对象，BeanHandler是ResultSetHandler结果集处理器接口的实现类
            MapListHandler handler = new MapListHandler();
            List<Map<String, Object>> list = runner.query(conn, sql, handler, 23);
            list.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //防止内存泄漏，关闭连接
            JDBCUtils.closeResourse(conn, null);
        }

    }


    /*
     * ScalarHandler是ResultSetHandler结果集处理器接口的实现类，用于查询特殊值
     */
    @Test
    public void testQuery5() {
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection3();
            String sql = "select count(*) from customers";

            ScalarHandler handler = new ScalarHandler();
            //多态左边Object类型
            Long count = (Long) runner.query(conn, sql, handler);
            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //防止内存泄漏，关闭连接
            JDBCUtils.closeResourse(conn, null);
        }

    }

    @Test
    public void testQuery6() {
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection3();
            String sql = "select max(birth) from customers";

            ScalarHandler handler = new ScalarHandler();
            //多态左边Object类型
            Date maxBirth = (Date) runner.query(conn, sql, handler);
            System.out.println(maxBirth);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //防止内存泄漏，关闭连接
            JDBCUtils.closeResourse(conn, null);
        }

    }



    /*
     * 自定义ResultSetHandler实现类
     */
    @Test
    public void testQuery7() {
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection3();
            String sql = "select id,name,email,birth from customers where id = ?";


            ResultSetHandler<Customer> handler = new ResultSetHandler<Customer>() {
                @Override
                public Customer handle(ResultSet rs) throws SQLException {//ResultSet把查询的结果集放到参数里边了
//                    System.out.println("handle");
//                    return null;

//                    return new Customer(12, "成龙", "Jackey@126.com", new Date(23525323L));

                    if(rs.next()){
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        String email = rs.getString("email");
                        Date birth = rs.getDate("birth");
                        Customer customer = new Customer(id, name, email, birth);
                        return customer;
                    }
                    
                    return null;
                }
            };

            Customer customer = runner.query(conn, sql, handler, 23);
            System.out.println(customer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //防止内存泄漏，关闭连接
            JDBCUtils.closeResourse(conn, null);
        }

    }



}
