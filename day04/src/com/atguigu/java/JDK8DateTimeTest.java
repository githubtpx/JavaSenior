package com.atguigu.java;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

/**
 * Jdk 8 中日期和时间API的测试
 *
 * @author 田沛勋
 * @create 2020-07-26 14:41
 */
public class JDK8DateTimeTest {

    @Test
    public void testDate() {
        //偏移量：
//        Date date1 = new Date(2020,9,8);//Fri Oct 08 00:00:00 CST 3920,存在偏移量
        Date date1 = new Date(2020 - 1900, 9 - 1, 8);//Fri Oct 08 00:00:00 CST 3920
        System.out.println(date1);//Tue Sep 08 00:00:00 CST 2020

    }

    /*
    LocalDate、LocalTime、LocalDateTime的使用
    说明：
        1. localDateTime相较于其它两个类，使用频率要高。且三者都是具有不可变性
        2. 类似于Calendar
        3. 三者都实现了TemporalAccessor接口

     */
    @Test
    public void test1() {
        //1. 实例化
        //now()：获取当前的本地的日期、时间、日期+时间
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();//使用较多

        System.out.println(localDate);//2020-07-26
        System.out.println(localTime);//15:01:35.388
        System.out.println(localDateTime);//2020-07-26T15:01:35.388


        //of():设置指定的年、月、日、时、分、秒。没有偏移量
        LocalDateTime localDateTime1 = LocalDateTime.of(2020, 10, 20, 12, 33, 44);
        System.out.println(localDateTime1);


        //2.相关操作
        //getXxx()：获取相关的属性
        System.out.println(localDateTime.getDayOfMonth());
        System.out.println(localDateTime.getDayOfWeek());
        System.out.println(localDateTime.getMonth());
        System.out.println(localDateTime.getMonthValue());
        System.out.println(localDateTime.getMinute());

        //with()设置：体现了不可变性      (类似Calendar.set()可变性)
        //withXxx()：设置相关属性
        LocalDateTime localDateTime2 = localDateTime.withDayOfMonth(22);//
        System.out.println(localDateTime2);//2020-07-22T15:13:14.100
        System.out.println(localDateTime);//2020-07-26T15:13:14.100,不可变性


        //plusDays(), plusWeeks(),plusMonths(), plusYears(),plusHours()：增加相关属性的操作,体现了不可变性
        // (类似Calendar.add()可变性)
        LocalDateTime localDateTime3 = localDateTime.plusDays(3);
        System.out.println(localDateTime3);
        System.out.println(localDateTime);


        //minusMonths()/minusDays()/minusYears()/minusHours()：减少相关属性的操作,体现了不可变性
        LocalDateTime localDateTime4 = localDateTime.minusDays(6);
        System.out.println(localDateTime4);
        System.out.println(localDateTime);


    }

    /*
    瞬时点Instant类的使用： 实例化中存在时区问题，需要处理
    类似于java.util.Date()类

     */
    @Test
    public void test2() {

        //1.实例化
        //类.now()： 获取本初子午线UTC对应的标准时间
        Instant instant = Instant.now();//返回默认 UTC 时区的 Instant 类的对象，本初子午线的时间(我们东8区，差8h)
        System.out.println(instant);//2020-07-26T07:33:46.657Z

        //类.ofEpochMilli()：通过给定的毫秒数，获取Instant实例  ---> Date(long millis)
        // 类似于Date类实例化：通过形参为时间戳/毫秒数的构造器
        Instant instant1 = Instant.ofEpochMilli(1595749203190L);
        System.out.println(instant1);


        //2.相关操作
        //对象.atOffset()： 添加时间的偏移量
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));//结合即时的偏移来创建一个OffsetDateTime
        System.out.println(offsetDateTime);//2020-07-26T15:33:46.657+08:00

        //对象.toEpochMilli()：获取瞬时点instant的毫秒数：   ----> Date类的getTime()
        long milli = instant.toEpochMilli();//它只是简单的表示自1970年1月1日0时0分0秒（UTC）开始的秒数
        System.out.println(milli);


    }

    /*
    DateTimeFormatter：格式化和解析日期、时间
    类似于SimpleDateFormat

     */
    @Test
    public void test3(){

//方式一：预定义的标准格式。如：ISO_LOCAL_DATE_TIME;  ISO_LOCAL_DATE;   ISO_LOCAL_TIME
        //1. 实例化
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        //2. 相关操作：格式化和解析
        //格式化：日期  --->   字符串
        LocalDateTime localDateTime = LocalDateTime.now();
        String str1 = formatter.format(localDateTime);//参数是TemporalAccessor接口
        System.out.println(str1);//"2020-07-26T15:59:51.045"
        System.out.println(localDateTime);//2020-07-26T15:59:51.045

        //解析：字符串  --->   日期
        TemporalAccessor parse = formatter.parse("2020-07-26T15:59:51.045");//多态
        System.out.println(parse);//{},ISO resolved to 2020-07-26T15:59:51.045，调用toString()
        System.out.println("*****************************************************************");



//方式二：
//本地化相关的格式。如：ofLocalizedDateTime(FormatStyle.LONG)。
//对于LocalDateTime适用于的三个参数：FormatStyle.LONG / FormatStyle.MEDIUM / FormatStyle.SHORT,格式出来的字符串不一样
        //1.实例化
        DateTimeFormatter formatter1 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);

        //2. 相关操作：格式化和解析
        //格式化：日期  --->   字符串
        String str2 = formatter1.format(localDateTime);
        System.out.println(str2);//2020年7月26日 下午04时09分04秒
        System.out.println(localDateTime);//2020-07-26T16:09:04.629

        //解析：字符串  --->   日期
        System.out.println("*****************************************************************");
//        formatter1.parse();

//本地化相关的格式。如：ofLocalizedDate()。
//对于LocalDate适用于的三个参数：FormatStyle.FULL/ FormatStyle.LONG / FormatStyle.MEDIUM / FormatStyle.SHORT,格式出来的字符串不一样
        //1. 实例化
        DateTimeFormatter formatter2 = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        //2. 相关操作：格式化和解析
        //格式化：日期  --->   字符串
        String str3 = formatter2.format(LocalDate.now());
        System.out.println(str3);//2020年7月26日 星期日
        System.out.println("*****************************************************************");




//方式三：自定义的格式。如：ofPattern(“yyyy-MM-dd hh:mm:ss”)，用的较多
        //1. 实例化
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        //2. 相关操作：格式化和解析
        //格式化：日期  --->   字符串
        String str4 = formatter3.format(LocalDateTime.now());
        System.out.println(str4);//2020-07-26 04:19:11

        //解析：字符串  --->   日期
        TemporalAccessor parse1 = formatter3.parse("2020-07-26 04:19:11");//得到接口，多态
        System.out.println(parse1);//{SecondOfMinute=11, NanoOfSecond=0, MicroOfSecond=0, HourOfAmPm=4, MinuteOfHour=19, MilliOfSecond=0},ISO resolved to 2020-07-26


    }



}