package com.atguigu3.dao;

import com.atguigu1.util.JDBCUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO：data(base) access object。
 *     1, 数据库访问对象，它封装了用java代码操作数据库的功能方法。
 *     2, 具体针对于一张表操作体现为：表名DAO(CustomerDAO)的接口
 *     3，需要提供表的一组操作：CustomerDAO接口与CustomerDAOImpl实现类
 *     4，提供针对于某张表的接口，然后再提供这张表接口的实现类，
 *        且它们实现类都去继承与BaseDAO(封装了通用的增删改查操作)
 *
 * DAO(BaseDAO)封装了针对于数据表的通用的操作：
 *     1，不会造它的对象
 *     2，它只是给我们提供通用的方法，帮我们完成后续的针对于具体表，具体sql的逻辑。
 *
 * @author 田沛勋
 * @create 2020-09-07 8:07
 */

//BaseDAO它封装了通用的数据库表的增删改查操作！
public abstract class BaseDAO<T> {//抽象类：不能造它的对象
    private Class<T> clazz = null;

    //非静态代码块：每创建一个对象，就执行一次非静态代码块
    {
        //获取当前BaseDAO的子类继承的父类中的泛型
        //1. 获取带泛型的父类（返回一个多态的Type）
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        //2. 带泛型参数的Type（强转多态，为Type的子类）
        ParameterizedType paramType = (ParameterizedType) genericSuperclass;
        //3. 获取了父类的泛型参数
        Type[] typeArguments = paramType.getActualTypeArguments();
        clazz = (Class<T>) typeArguments[0];//获取了泛型的第一个参数

    }

    //通用的增删改操作---version 2.0（考虑事务:连接从外边传进来）
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


    /**
     *  针对于不同表的通用的查询操作，返回数据库表中的一条记录(version 2.0：考虑上事务)
     */
    public  T getInstance(Connection conn,  String sql, Object... args) {//泛型方法，T是参数 ，不是参数
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


    /*
     * 针对于不同表的通用的查询操作，返回表中的多条记录构成的集合
     */
    public  List<T> getForList(Connection conn, String sql, Object... args) {//泛型方法
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

            //创建集合对象
            ArrayList<T> list = new ArrayList<>();
            while (rs.next()) {//对应表中的某一行
                T t = clazz.newInstance();//反射获取，动态的

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
            JDBCUtils.closeResourse(null, ps, rs);
        }
        return null;
    }


    //用于查询特殊值的通用的方法(如：select count(*) from emp)
    public <E>E getValue(Connection conn,String sql,Object ...args)  {

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            rs = ps.executeQuery();
            //针对于结果集只有一行一列
            if (rs.next()) {
                return (E) rs.getObject(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.closeResourse(null, ps,rs);
        }
        return null;
    }


}
