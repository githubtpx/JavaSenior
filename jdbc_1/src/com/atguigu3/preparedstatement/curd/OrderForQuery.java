package com.atguigu3.preparedstatement.curd;

import com.atguigu3.bean.Order;
import com.atguigu3.util.JDBCUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.*;

/**
 *
 * @Description 针对于Order表的通用的查询操作
 * @author 田沛勋
 * @create 2020-09-03 11:19
 */
public class OrderForQuery {
    @Test
    public void testOrderForQuery(){
        String sql = "select order_id orderId,order_name orderName,order_date orderDate from `order` where order_id = ?";
        Order order = orderForQuery(sql, 1);
        System.out.println(order);

    }


    /**
     * @Description 通用的针对于Order表的查询操作
     * @throws Exception
     */
    public Order orderForQuery(String sql,Object ...args)  {
        /*
         * 针对于表的字段名与类的属性名不一致的情况：
         * 1.  必须声明sql时，使用类的属性名来命名字段的别名。
         * 2.  在使用ResultSetMetaData时，需要时getColumnLabel()替换getColumnName()方法，获取列的别名。
         *     说明：如果sql中没有给字段起别名，getColumnLabel()仍然获取的就是列名
         *
         */
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1, args[i]);
            }

            rs = ps.executeQuery();
            //获取结果集的元数据
            ResultSetMetaData rsmd = rs.getMetaData();
            //获取列数
            int columnCount = rsmd.getColumnCount();
            if(rs.next()){
                Order order = new Order();

                for (int i = 0; i < columnCount; i++) {
                    //获取每个列的列值：通过结果集，因为它是存储数据值的。它的修饰(它的列数，列名字等)是通过结果集的元数据
                    Object columnValue = rs.getObject(i + 1);

                    //获取表的每个列的列名：通过ResultSetMetaData
                    //获取列的列名：getColumnName()      ---不推荐使用
//                    String columnName = rsmd.getColumnName(i + 1);
                    //获取列的别名：getColumnLabel()     --推荐使用
                    String columnLabel = rsmd.getColumnLabel(i + 1);


                    //通过反射，拿到对象指定名columnName的属性赋值为指定的值columnValue
                    Field field = Order.class.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(order,columnValue);
                }

                return order;

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResourse(conn, ps, rs);
        }
        return null;

    }

    /**
     * @Description 针对于Order表的非通用的查询操作
     * @throws Exception
     */
    @Test
    public void testQuery1()  {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();

            String sql = "select order _id,order_name,order_date from `order` where order_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1, 1);

            rs = ps.executeQuery();
            if(rs.next()){
                int id = (int) rs.getObject(1);
                String name = (String) rs.getObject(2);
                Date date = (Date) rs.getObject(3);

                Order order = new Order(id, name, date);
                System.out.println(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResourse(conn, ps,rs);
        }
    }

}
