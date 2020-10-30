package com.atguigu4.connection;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author 田沛勋
 * @create 2020-09-07 20:13
 */
public class DBCPTest {

    /**
     * @Description 测试DBCP的数据库连接池技术
     */
    //方式一：不推荐此硬编码方式
    @Test
    public void testGetConnection() throws SQLException {
        //创建了DBCP的数据库连接池
        DataSource ds = new BasicDataSource();
        BasicDataSource source = (BasicDataSource) ds;

        //设置4个基本信息
        source.setDriverClassName("com.mysql.jdbc.Driver");
        source.setUrl("jdbc:mysql:///test");
        source.setUsername("root");
        source.setPassword("123123");

        //还可以设置其它涉及数据库连接池管理的相关属性：
        source.setInitialSize(10);
        source.setMaxActive(10);
        //...

        Connection conn = source.getConnection();
        System.out.println(conn);

    }


    //方式二：推荐使用配置文件
    @Test
    public void testConnection1() throws Exception {
        //Map
        Properties pros = new Properties();
        //方式1：通过类的加载器加载Properties文件
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("dbcp.properties");
        
        //方式2：
//        FileInputStream is = new FileInputStream(new File("src/dbcp.properties"));

        pros.load(is);
        //创建了DBCP的数据库连接池
        DataSource source = BasicDataSourceFactory.createDataSource(pros);

        Connection conn = source.getConnection();
        System.out.println(conn);
    }

}
