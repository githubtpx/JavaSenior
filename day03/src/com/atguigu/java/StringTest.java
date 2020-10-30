package com.atguigu.java;

import org.junit.Test;

/**
 * String的使用:
 Public final class String
 implements java.io.Serializable, Comparable<String>, CharSequence {
    Private final char value[];
    Private int hash; // Default to 0
 }

 * 注意：
 *     1) String是一个final类，代表不可变的字符序列。
 *     2) 字符串是常量，用双引号引起来表示。它们的值在创建之后不能更改。
 *     3) String对象的字符内容都是存储在一个字符数组value[]中
 *     4)不论何种创建数组对象(实例化)的方式，字符串值都声明在方法区的字符串常量池中
 *
 * @author 田沛勋
 * @create 2020-07-20 9:32
 */
public class StringTest {

    /*
    结论：此时，字面量 == 常量
    1.常量与常量的拼接结果在常量池。且常量池中不会存在相同内容的常量。final修饰的局部变量也算
    2.只要其中(拼接)有一个是变量，结果就在堆中。
    3.如果拼接的结果调用intern()方法，返回值就在常量池中。
     */
    @Test
    public void test4(){
        String s1 = "javaEEhadoop";
        String s2 = "javaEE";
        String s3 = s2 + "hadoop";
        System.out.println(s1 == s3 );//false

        final String s4 = "javaEE";//s4常量
        String s5 = s4 + "hadoop";//常量 + 常量 拼接结果在常量池中。
        System.out.println(s1 == s5);//true


    }

    @Test
    public void test3(){
        String s1 = "javaEE";
        String s2 = "hadoop";

        String s3 = "javaEEhadoop";
        String s4 = "javaEE" +"hadoop";
        String s5 = s1 +"hadoop";
        String s6 = "javaEE" + s2;
        String s7 = s1 + s2;

        System.out.println(s3 == s4);//true
        System.out.println(s3 == s5);//false
        System.out.println(s3 == s6);//false
        System.out.println(s3 == s7);//false
        System.out.println(s5 == s6);//false
        System.out.println(s5 == s7);//false
        System.out.println(s6 == s7);//false

        String s8 = s5.intern();//返回值得到的s8使用的常量池中已经存在的"javaEEhadoop"
                                // (返回常量池当中的s5对象相应数据的地址值)
        System.out.println(s3 == s8);//true

    }


    /*
    String的实例化方式(造对象)：
    方式一：通过字面量定义的方式
    方式二：通过new + 构造器的方式(char[] value是一个引用类型的变量)

    面试题：String s = new String("abc");方式创建对象，在内存中创建了几个对象？
            两个：一个是堆空间new结构创建的对象；另一个是char[]对应的常量池中的数据："abc"
            (如果常量池中已经有了不用再造了，用下)

     */
    @Test
    public void test2(){
        //通过字面量定义的方式：此时的s1和s2对象的字符串的数据，声明在方法区的字符串常量池中
        String s1 = "javaEE";
        String s2 = "javaEE";

        //通过new + 构造器的方式：此时的s3和s4保存的地址值，是数据(字符串)在堆空间开辟以后对应的地址值
        String s3 = new String("javaEE");
        String s4 = new String("javaEE");


        System.out.println(s1 == s2);//true,因为字符串常量池不能出现相同内容
        System.out.println(s1 == s3);//false
        System.out.println(s1 == s4);//false
        System.out.println(s3 == s4);//false


    }

    /*
    String:字符串，使用一对""引起来表示。
    1.String类声明为final的，不可以被继承
    2.String实现了Serializable接口：表示字符串是支持序列化的(I/O流)
       (/ˈsɪˌriəˌlaɪzəbl/,可序列化的，able可以...的)
      String实现了Compareable接口：表示String字符串可以比较大小
      (可以able....的 + 比较的)

    3.String内部定一个了final char[] value用于存储String对象的字符串数据

    4.String:代表不可变的字符序列。简称：不可变性。
       体现：1.当对字符串重新赋值时，需要重新指定内存区域赋值，不能使用原有的value进行赋值(因为它是final的)。
            2.当对现有的字符串进行连接操作时，也需要重新指定内存区域赋值，不能再原有的value进行赋值。
            3.当调用String的replace()方法修改指定字符/字符串时，也必须重新指定内存区域赋值，不能再原有的value进行赋值。

       (1.char型数组value不可被重新赋地址值了 2.数组的元素也不能再被修改了。统一下)

    5.通过字面量方式(区别于new方式)给一个字符串赋值，此时的字符串值声明在方法区的字符串常量池中。
    6.字符串常量池中是不会存储相同内容字符串的。

     */

    @Test
    public void test1(){
        String s1 = "abc";//字面量的定义方式(String对象赋值直接可以写，所以潜意识它是基本数据类型一样)
        String s2 = "abc";
//        s1 = "hello";
        System.out.println(s1 == s2);//对象，比较s1和s2的地址值(true)

        System.out.println(s1);//hello
        System.out.println(s2);//abc
        System.out.println("********************************");

        String s3 = "abc";
        s3 += "def";
        System.out.println(s3);//abcdef
        System.out.println(s2);//abc
        System.out.println("********************************");

        String s4 = "abc";
        String s5 = s4.replace("a", "m");
        System.out.println(s4);//abc
        System.out.println(s5);//mbc
        System.out.println("********************************");

        Person p1 = new Person("Tom", 12);
        Person p2 = new Person("Tom", 12);

        System.out.println(p1.name.equals(p2.name));//true
        System.out.println(p1.name == p2.name);//true

        p1.name = "Jerry";
        System.out.println(p1.name);//Tom,p1.name不可变性

    }

}
