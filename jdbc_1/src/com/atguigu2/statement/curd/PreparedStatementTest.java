package com.atguigu2.statement.curd;

import com.atguigu3.util.JDBCUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Scanner;

/**
 * @Explain 预编译SQL语句：我们生成的PreparedStatement对象已经把这个sql使用过了。
 *          WHERE user = ? AND password = ?"  我在你没有填充数据的时候，已经预编译sql语句，我就表示谁且谁。这种关系不会被随意被改变。挖了两个坑
 *          Statement是拿着这个sql去操作了，没有预编译的说法。
 * @Description 演示使用PreparedStatement替换Statement，解决SQL注入问题，解决了拼串。
 * @author 田沛勋
 * @create 2020-09-04 7:40
 *
 *  除了解决Statement的拼串、SQL注入问题之外，PreparedStatement还有哪些好处呢？
 *  1. PreparedStatement操作Blob类型的数据(流)。而Statement做不到的。
 *  2. PreparedStatement可以实现更高效的批量操作。(如，Insert等)
 *
 */
public class PreparedStatementTest {

    @Test
    public void testLogin(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入用户名：");
        //next()换行与空格结束。nextLine()换行结束
        String user = scanner.next();
        System.out.print("请输入密码：");
        String password = scanner.next();

        //SELECT user,password FROM user_table WHERE user = '1' or ' AND password = '=1 or '1' = '1' ;  //SQL注入
        String sql = "SELECT user,password FROM user_table WHERE user = ? AND password = ?";

        User returnUser = getInstance(User.class,sql,user,password);
        if(returnUser != null){
            System.out.println("登陆成功");
        }else{
            System.out.println("用户名不存在或密码错误");
        }
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
