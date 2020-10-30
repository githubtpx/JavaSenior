package com.atguigu.java;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * JDK 8之前的日期和时间的API测试
 * 1. System类中的currentTimeMillis();
 * 2. java.util.Date类和子类java.sql.Date
 * 3. SimpleDateFormat
 * 4. Calendar日历类、抽象类：可变性
 * 
 *
 * @author 田沛勋
 * @create 2020-07-25 19:38
 */
public class DateTimeTest {
    /*
    SimpleDateFormat的使用：SimpleDateFormat对日期Date类进行格式化和解析

    1.两个操作(非静态方法，需要实例化)
    1.1 格式化： 日期Date  --->指定的字符串
    1.2 解析：   指定的字符串 ---> 日期Date

    2.SimpleDateFormat的实例化
     */

    @Test
    public void testSimpleDateFormat() throws ParseException {
        //实例化SimpleDateFormat：使用默认的构造器
        SimpleDateFormat sdf = new SimpleDateFormat();

        //格式化：日期Date  --->指定的字符串，(默认的构造器格式化为指定的字符串格式)
        Date date = new Date();
        String format = sdf.format(date);
        System.out.println(format);//20-7-26 上午8:43

        //解析：格式化的逆过程，指定的字符串 ---> 日期Date
        String str = "20-7-29 上午8:43";
        Date date1 = sdf.parse(str);
        System.out.println(date1);

        //********************按照指定的方式格式化和解析：调用带参的构造器来实现**************************
//        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyy.MMMMM.dd GGG hh:mm aaa");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");


        //格式化
        String format1 = sdf1.format(date);
        System.out.println(format1);//2020-07-26 08-49-41
        //解析:要求字符串必须是符合SimpleDateFormat识别的格式(通过其构造器的参数体现)。
        //否则，抛异常
        Date date2 = sdf1.parse("2022-07-26 08-49-41");
        System.out.println(date2);//Tue Jul 26 08:49:41 CST 2022

    }

    /*
    练习一：字符串"2020-09-08"转换为java.sql.Date
    SimpleDateFormat解析的过程，要求字符串必须是符合SimpleDateFormat构造器参数要求的格式


    练习二："三天打渔两天晒网"，从1990-01-01开始，问xxxx-xx-xx这天渔夫是在打渔？ 还是晒网？

    举例：2020-09-08 ？ 求总天数

    总天数 % 5 == 1，2，3：打渔
    总天数 % 5 == 4，0：晒网

    总天数的计算？难点
    方式一(通过毫秒数)：
         总天数 = (date2.getTime() - date1.getTime()) / (1000 * 60 * 60 * 24) + 1    (除尽24看成新的一天开始)
    方式二(分割年份)：
         总天数 = 1990-01-01  ---> 2019-12-31(有几个润年)  +  2020-01-01 ---> 2020-09-08


     */
    @Test
    public void testExer() throws ParseException {
        String birth = "2020-09-08";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date date1 = sdf.parse(birth);//java.util.Date
        System.out.println(date1);

        java.sql.Date birthDate = new java.sql.Date(date1.getTime());
        System.out.println(birthDate);//2020-09-08


    }

    /*
    Calendar日历类(抽象类)的使用：它的实例化实际获得是它子类的对象。(抽象类)

     */
    @Test
    public void testCalendar(){
        //1.实例化：一个Calendar的实例是系统时间的抽象表示
        //方式一：创建子类(GregorianCalendar 公历)的对象
        //方式二：调用其静态方法Calendar.getInstance()
        Calendar calendar = Calendar.getInstance();
//        System.out.println(calendar.getClass());//GregorianCalendar

        //2.常用方法
        //get()
        int days = calendar.get(Calendar.DAY_OF_MONTH);//返回当前这个时间calendar是这个月的第几天
        System.out.println(days);//26
        System.out.println(calendar.get(Calendar.DAY_OF_YEAR));


        //set()
        //calendar是可变性
        //set()对calendar本身做了修改，可变。和String相反(String str2 = str.replace("str1"))
        calendar.set(Calendar.DAY_OF_MONTH, 22);
        days = calendar.get(Calendar.DAY_OF_MONTH);//返回当前这个calendar是这个月的第几天
        System.out.println(days);


        //add()
        calendar.add(Calendar.DAY_OF_MONTH,-3);//3,-3
        days = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(days);

        //getTime()：日历类 ---> Date类
        Date date = calendar.getTime();
        System.out.println(date);


        //setTime(): Date  ---> 日历类
        Date date1 = new Date();
        calendar.setTime(date1);
        days = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(days);

    }

}
