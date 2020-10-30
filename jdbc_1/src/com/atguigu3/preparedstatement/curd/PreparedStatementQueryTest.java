package com.atguigu3.preparedstatement.curd;

import com.atguigu3.bean.Customer;
import com.atguigu3.bean.Order;
import com.atguigu3.util.JDBCUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 田沛勋
 * @Description 使用PreparedStatement实现针对于不同表的通用的查询操作
 * @create 2020-09-03 16:12
 */
public class PreparedStatementQueryTest {

    /*
    因为占位符的使用解决了sql注入问题！
     */
    @Test
    public void testGetForList(){

        String sql = "select id,name,email from customers where id < ?";
        List<Customer> list = getForList(Customer.class, sql, 12);
        list.forEach(System.out::println);//foreEach是一个默认方法

        System.out.println("*********************************");
        String sql1 = "select order_id orderId,order_name orderName from `order` ";
//        String sql1 = "select order_id orderId,order_name orderName from `order` < 12";
        List<Order> orderList = getForList(Order.class, sql1);
        orderList.forEach(System.out::println);
    }

    /*
     * 针对于不同表的通用的查询操作，返回表中的多条记录
     */
    public <T>  List<T> getForList(Class<T> clazz, String sql, Object ...args){//泛型方法
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();

            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            rs = ps.executeQuery();
            //获取结果集的元数据：ResultSetMetaData
            ResultSetMetaData rsmd = rs.getMetaData();
            //通过ResultSetMetaData获取结果集中的列数
            int columnCount = rsmd.getColumnCount();

            //创建集合对象
            ArrayList<T> list = new ArrayList<>();
            while (rs.next()) {//对应表中的某一行
                T t = clazz.newInstance();

                //处理结果集一行数据中的每一个列：给t对象指定的属性赋值过程
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
                list.add(t);
            }
            return list;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResourse(conn, ps, rs);
        }
        return null;
    }


    @Test
    public void testGetInstance() {
        String sql = "select id,name,email from customers where id = ?";
        Customer cust = getInstance(Customer.class, sql, 12);
        System.out.println(cust);

        String sql1 = "select order_id orderId,order_name orderName from `order` where order_id = ?";
        Order order = getInstance(Order.class, sql1, 1);
        System.out.println(order);
    }


    /**
     * @Description 针对于不同表的通用的查询操作，返回表中的一条记录
     * @param clazz
     * @param sql
     * @param args
     * @param <T>
     * @return
     */
    public <T> T getInstance(Class<T> clazz,String sql, Object... args) {//泛型方法，T是参数 ，不是参数
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();

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
            JDBCUtils.closeResourse(conn, ps, rs);
        }

        return null;


    }

}
