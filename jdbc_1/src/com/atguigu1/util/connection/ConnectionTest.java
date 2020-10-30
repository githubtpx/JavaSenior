package com.atguigu1.util.connection;

import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author 田沛勋
 * @create 2020-09-02 8:28
 */
public class ConnectionTest {

    //方式一：
    @Test
    public void testConnection1() throws SQLException {
        //获取Driver实现类对象
        Driver driver = new com.mysql.jdbc.Driver();

        //url:http://localhost:8080/gmall/keyboard.jpg
        //jdbc:mysql：主副协议
        //localhost：IP地址
        //3306：默认mysql的端口号
        //test：test数据库名
        String url = "jdbc:mysql://localhost:3306/test";

        //将用户名和密码封装在Properties中
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "123123");

        Connection conn = driver.connect(url, info);

        System.out.println(conn);

    }

    //方式二：对方式一的迭代：在如下的程序中不出现第三方的API，使得程序具有更好的可移植性
    @Test
    public void test2() throws Exception {
        //1.获取Driver实现类对象：使用反射
        // (更具有通用性：没有第三方的API，都是SUN公司提供的API，可移植性好)
        Class clazz = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();

        //2.提供要连接的数据库
        String url = "jdbc:mysql://localhost:3306/test";

        //3.提供连接需要的用户名和密码
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "123123");

        //4.获取连接
        Connection conn = driver.connect(url, info);
        System.out.println(conn);
    }


    //方式三：对方式二的迭代：使用DriverManager类替换Driver接口
    @Test
    public void testConnection3() throws Exception {
        //1.获取Driver实现类对象
        Class clazz = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();

        //2.提供另外三个连接的基本信息
        String url = "jdbc:mysql://localhost:3306/test";//可从mysql驱动的readme文件中找url
        String user = "root";
        String password = "123123";

        //1.注册驱动
        DriverManager.registerDriver(driver);

        //2.获取连接
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println(conn);
    }


    //方式四：可以只是加载驱动，不用显示的注册驱动了。
    @Test
    public void testConnection4() throws Exception {
        //1.提供另外三个连接的基本信息
        String url = "jdbc:mysql://localhost:3306/test";//可从mysql驱动的readme文件中找url
        String user = "root";
        String password = "123123";

        //2.加载Driver
        Class.forName("com.mysql.jdbc.Driver");
        //相较于方式三，可以省略如下的操作：
//        Driver driver = (Driver) clazz.newInstance();
//        //注册驱动
//        DriverManager.registerDriver(driver);
        //为什么可以省略上述操作呢？
        /*
         * 在mysql的Driver实现类中，声明了如下的操作：
            static {
            try {
                DriverManager.registerDriver(new Driver());
            } catch (SQLException var1) {
                throw new RuntimeException("Can't register driver!");
            }
            }
         */

        //3.获取连接
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println(conn);
    }

    //方式五(final版)：将数据库连接需要的4个基本信息声明在配置文件中，通过读取配置文件的方式，获取连接(最终版)
    /*
     * 此种方式的好处？
     * 1.实现了数据与代码的分离。实现了解耦(通过改配置文件数据进行数据库连接的切换：
     *       不用以硬编码的方式，写死了，给它融合在一起。此时我们代码不用动，你要改只需要改数据在配置文件中)
     * 2.如果需要修改配置文件信息，就可以避免程序重新打包。(只需要替换文件即可)
     *
     */
    @Test
    public void getConnnection5() throws Exception {

        //1.读取配置文件中的4个基本信息(可以通过类的加载器获取)
        ClassLoader classLoader = ConnectionTest.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("jdbc.properties");

        Properties pros = new Properties();
        pros.load(is);

        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        String url = pros.getProperty("url");
        String driverClass = pros.getProperty("driverClass");

        //2.加载驱动
        Class.forName(driverClass);

        //3.获取连接
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println(conn);

    }


}
