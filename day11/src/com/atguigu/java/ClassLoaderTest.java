package com.atguigu.java;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 了解类的加载器
 *          自定义类，通过系统类加载器加载（如AppClassLoader）。
 *          /jre/lib/ext/*jar下的.class文件所对应的类，通过扩展类加载器加载（如ExtClassLoader）。
 *          用于加载核心类库如String，的是引导类加载器（如null没有办法获取到该加载器）。
 *
 *
 *
 * @author 田沛勋
 * @create 2020-08-18 8:52
 */
public class ClassLoaderTest {

    @Test
    public void test1(){
        //获取当前自定类的加载器
        //1. 对于自定义类，使用系统类加载器进行加载
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader);

        //2. 调用系统类加载器的getParent()：获取扩展类加载器
        ClassLoader classLoader1 = classLoader.getParent();
        System.out.println(classLoader1);

        //3. 调用扩展类加载器getParent()：无法获取引导类加载器
        //引导类加载器主要负责加载java核心类库，无法加载自定义类的，无法获取该加载器。
        ClassLoader classLoader2 = classLoader1.getParent();
        System.out.println(classLoader2);

        ClassLoader classLoader3 = String.class.getClassLoader();
        System.out.println(classLoader3);

    }

    /*
    Properties：读取配置文件

     */
    @Test
    public void test2() throws IOException {
        Properties pros = new Properties();
        //此时的文件默认在当前的module下。
        //读取配置文件的方式一：
//        FileInputStream fis = new FileInputStream("jdbc.properties");
//        FileInputStream fis = new FileInputStream("src\\jdbc1.properties");
//        pros.load(fis);

        //读取配置文件的方式二：使用ClassLoader
        //配置文件默认识别为：当前module的src下
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("jdbc1.properties");//以流的方式获取一个资源resource
        pros.load(is);


        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        System.out.println("user =" + user + ",password = " + password);


    }


}
