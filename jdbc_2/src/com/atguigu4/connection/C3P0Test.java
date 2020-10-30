package com.atguigu4.connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author 田沛勋
 * @create 2020-09-07 17:53
 */
public class C3P0Test {//需要导入第三方的jar包

    //方式一：
    @Test
    public void testGetConnection() throws Exception {
        //获取C3P0数据库连接池(对象)：     获取数据库连接池的前提这四个基本信息不能错！
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass("com.mysql.jdbc.Driver"); //loads the jdbc driver
        cpds.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        cpds.setUser("root");
        cpds.setPassword("123123");

        //通过设置相关的参数，对数据库连接池进行管理：
        //设置初始时数据库连接池中的连接数
        cpds.setInitialPoolSize(10);

        //获取到C3P0数据库连接池对象中的一个连接而已
        Connection conn = cpds.getConnection();
        System.out.println(conn);

        //销毁C3P0数据库连接池
//        DataSources.destroy( cpds );
    }

    //方式二：使用配置文件                        （不想使用硬编码的方式）
    @Test
    public void testConnection1() throws SQLException {
        ComboPooledDataSource cpds = new ComboPooledDataSource("helloc3p0");
        Connection conn = cpds.getConnection();
        System.out.println(conn);

    }

}
