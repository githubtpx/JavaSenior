package com.atguigu.java;

import org.junit.Test;

import java.util.Date;

/**
 * JDK 8之前的日期和时间的API测试
 *
 * @author 田沛勋
 * @create 2020-07-25 8:22
 */
public class DataTimeTest {
    /*
    java.util.Date类
            |----java.sql.Date类(继承上面的父类)

    1.两个构造器的使用
        >构造器一：Date()：创建当前时间的Date对象
        >构造器二：创建指定毫秒数(时间戳)的Date对象
    2.两个方法的使用
        >toString():显示当前的年、月、日、时、分、秒、星期几、区域(东八区)
        >getTime():获取当前Date对象的对应的毫秒数。(时间戳)

    3.java.sql.Date对应着数据库中的日期类型的变量
        >如何实例化
        >如何将java.util.Date类的对象转换为java.sql.Date类的对象(反之，多态)

     */
    @Test
    public void test2(){
        //构造器一：Date()：创建一个对应当前时间的Date对象
        Date date1 = new Date();
        System.out.println(date1.toString());//Sat Jul 25 08:51:39 CST 2020

        System.out.println(date1.getTime());//获取当前Date对象的对应的时间戳，1595638299656

        //构造器二：创建指定毫秒数(时间戳)的Date对象
        Date date2 = new Date(1595638299656L);
        System.out.println(date2.toString());

        //创建java.sql.Date类的对象
        java.sql.Date date3 = new java.sql.Date(3595638299656L);
        System.out.println(date3.toString());//2083-12-10,重写了父类的toString()

        //如何将java.util.Date类的对象转换为java.sql.Date类的对象(父类转到子类)
        //情况一：
//        Date date4 = new java.sql.Date(3595638299656L);
//        java.sql.Date date5 = (java.sql.Date)date4;
        //情况二：
        Date date6 = new Date();
        //注意：java.util.Date，java.sql.Date子父类共通的是时间戳/毫秒数
        java.sql.Date date7 = new java.sql.Date(date6.getTime());


    }


    //1.System类中的currentTimeMillis()
    @Test
    public void test1(){
        long time = System.currentTimeMillis();
        //返回当前时间与1970年1月1日0时0分0秒之间以毫秒为单位的时间差(也成为时间戳)
        //称为时间戳
        System.out.println(time);
    }



}
