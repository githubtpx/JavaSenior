package com.atguigu3.preparedstatement.curd;

import com.atguigu3.bean.Customer;
import com.atguigu3.util.JDBCUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.*;

/**
 * 针对于Customers表的查询操作
 *
 * @author 田沛勋
 * @create 2020-09-03 9:23
 */
public class CustomerForQuery {
    @Test
    public void testQueryForCustomers(){
        String sql = "select id,name,birth,email from customers where id = ?";
        Customer customer = queryForCustomers(sql, 13);
        System.out.println(customer);

        sql ="select name,email from customers where name = ?";
        Customer customer1 = queryForCustomers(sql, "周杰伦");
        System.out.println(customer1);

    }


    /**
     * @Description 针对于customers表的通用的查询操作(字段多少通用，把变的东西放在参数中)
     * 两个技术：结果集的元数据 + 反射
     */
    public Customer queryForCustomers(String sql,Object ...args)  {
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
            //元注解：修饰现有注解的注解叫做元注解
            //元数据：修饰现有数据的数据叫做元数据：String name = "Tom",核心数据"Tom"的元数据为String 和 name
            //结果集的元数据：修饰结果集的数据
            ResultSetMetaData rsmd = rs.getMetaData();

            //通过ResultSetMetaData获取结果集中的列数
            int columnCount = rsmd.getColumnCount();
            if(rs.next()){//对应表中的某一行
                //你查到结果我才给你造对象,造一个对象
                Customer cust = new Customer();

                //处理结果集一行数据中的每一个列
                for (int i = 0; i < columnCount; i++) {//对应表中的列
                    //获取列值
                    Object columnValue = rs.getObject(i + 1);

                    //获取每个列的列名
//                    String columnName = rsmd.getColumnName(i + 1);
                    String columnLabel = rsmd.getColumnLabel(i + 1);

                    //给cust对象指定的columnName属性，赋值为columnValue：通过反射
                    Field field = Customer.class.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(cust, columnValue);
                }
                return cust;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResourse(conn, ps,rs);
        }

        return null;
    }


    @Test
    public void testQuery1()  {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            //1.获取数据库连接
            conn = JDBCUtils.getConnection();
            //2.预编译sql语句，并返回PreparedStatement对象
            String sql = "select id,name,email,birth from customers where id = ?";
            ps = conn.prepareStatement(sql);
            //3.填充占位符
            ps.setObject(1, 1);

            //4.执行，并返回结果集
            resultSet = ps.executeQuery();
            //5.处理结果集
            if(resultSet.next()){//next():判断结果集下一条是否有数据，如果有数据返回true，并指针下移；如果返回false，指针不会下移。

                //获取当前这条数据的各个字段值
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String email = resultSet.getString(3);
                Date birth = resultSet.getDate(4);

                //方式一：
    //            System.out.println("id = " + id + ",name = "+ name + ",email = " + email + ",birth = " + birth);

                //方式二：
    //            Object[] data = new Object[]{id,name,email,birth};

                //方式三：将表中的一条记录封装成一个Customer类的对象（推荐）
                Customer customer = new Customer(id, name, email, birth);
                System.out.println(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //6.关闭资源
            JDBCUtils.closeResourse(conn, ps, resultSet);
        }

    }




}
