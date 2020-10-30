package com.atguigu5.blob;

import com.atguigu3.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * 使用PreparedStatement实现批量数据的操作(增删改)
 *
 * update、delete本身具有批量操作的效果。
 * 此时的批量操作，主要指的是批量插入(insert)。使用PreparedStatement如何实现更高效的批量插入？
 *
 *
 * 题目：向goods表中插入20000条数据
 * CREATE TABLE goods(
 * id INT PRIMARY KEY AUTO_INCREMENT,
 * NAME VARCHAR(25));
 *
 * 方式一：使用Statement(每次执行sql都得编译一下。每插入一次都得现生成一个sql，内存中加载一个新的String类型的字符串)
 * Connection conn = JDBCUtils.getConnection();
 * Statement st = conn.createStatement();
 * for(int i = 1;i <= 20000; i++){
 *     String sql = "insert into goods(name) values('names_" + i + "')";
 *     st.execute(sql);
 * }
 *
 * 方式二：使用PreparedStatement
 *
 * @author 田沛勋
 * @create 2020-09-04 15:51
 */
public class InsertTest {

    //批量插入的方式二：使用PreparedStatement
    @Test
    public void testInsert1()  {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            long start = System.currentTimeMillis();

            conn = JDBCUtils.getConnection();
            /*
            1. 从内存角度讲：我这个String sql在内存中只是生成一份。
            2. 另外它后面毕竟是一个sql语句，sql语句在数据库服务器执行时会进行一系列校验。
             */
            String sql = "insert into goods(name) values(?)";
            ps = conn.prepareStatement(sql);

            for(int i = 1; i <= 20000; i++){
                ps.setObject(1, "name_" + i);

                ps.execute();//弊端：与数据库服务器过多的交互：20000次
            }

            long end = System.currentTimeMillis();
            System.out.println("花费的时间为:" + (end - start));//20000：594082

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResourse(conn, ps);
        }
    }



    /*
     * 批量插入的方式三：Batch(“攒”sql放入盒子batch中，与数据库服务器交互少)
     * 1.addBatch()/executeBatch()/clearBatch()
     * 2.mysql服务器默认是关闭批处理的，我们需要通过一个参数，让mysql开启批处理的支持。
     * 		 ?rewriteBatchedStatements=true 写在配置文件的url后面
     * 3.使用更新的mysql 驱动：mysql-connector-java-5.1.37-bin.jar
     *
     *
     * 修改1：使用 addBatch() / executeBatch() / clearBatch()
     * 修改2：mysql服务器默认是关闭批处理的，我们需要通过一个参数，让mysql开启批处理的支持。
     * 		 ?rewriteBatchedStatements=true 写在配置文件的url后面
     * 修改3：使用更新的mysql 驱动：mysql-connector-java-5.1.37-bin.jar
     */
    @Test
    public void testInsert2()  {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            long start = System.currentTimeMillis();

            conn = JDBCUtils.getConnection();
            /*
            1. 从内存角度讲：我这个String sql在内存中只是生成一份。
            2. 另外它后面毕竟是一个sql语句，sql语句在数据库服务器执行时会进行一系列校验。
             */
            String sql = "insert into goods(name) values(?)";
            ps = conn.prepareStatement(sql);

            for(int i = 1; i <= 20000; i++){
                ps.setObject(1, "name_" + i);

                //1."攒"sql到batch中(batch像个袋子)
                ps.addBatch();

                if(i % 500 == 0){
                    //2.执行batch
                    ps.executeBatch();

                    //3.清空batch
                    ps.clearBatch();
                }
//                if( i == 19999){
//                }
            }

            long end = System.currentTimeMillis();
            System.out.println("花费的时间为:" + (end - start));//20000：2690

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResourse(conn, ps);
        }
    }



    /*
     * 批量插入的方式四：设置连接不允许自动提交数据。
     *                  commit最后只提交一次，不要自动提交
     */
    @Test
    public void testInsert3()  {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            long start = System.currentTimeMillis();

            conn = JDBCUtils.getConnection();

            //设置不允许自动提交数据
            conn.setAutoCommit(false);

            String sql = "insert into goods(name) values(?)";
            ps = conn.prepareStatement(sql);

            for(int i = 1; i <= 20000; i++){
                ps.setObject(1, "name_" + i);

                //1."攒"sql到batch中(batch像个袋子)
                ps.addBatch();

                if(i % 500 == 0){
                    //2.执行batch
                    ps.executeBatch();

                    //3.清空batch
                    ps.clearBatch();
                }
//                if( i == 19999){
//                }
            }

            //提交数据
            conn.commit();

            long end = System.currentTimeMillis();
            System.out.println("花费的时间为:" + (end - start));//20000：1575

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResourse(conn, ps);
        }
    }

}
