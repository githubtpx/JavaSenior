package com.atguigu.java;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * 涉及到String类与其他结构之间的转换
 * String不可变的字符序列
 *
 * @author 田沛勋
 * @create 2020-07-23 17:05
 */
public class StringTest1 {
    /*
    String 与 byte[]之间的转换

    编码：String  --->  byte[]：(使用默认编码集)调用String的getBytes()方法
    解码：byte[]  --->  String：(需要解码器)调用String的构造器

    编码：字符串  ---> 字节(数组),    (能看得懂的 --->  底层二进制数据(看不懂的))
    解码：字节(数组) --->  字符串,    (底层二进制数据(看不懂的) ---> 能看得懂的东西)

    说明：解码时，要求解码使用的字符集必须与其编码使用的字符集一致，否则会出现乱码。(编码集和解码集必须一致)
     */
    @Test
    public void test3() throws UnsupportedEncodingException {
        //1. 编码过程：String  --> byte[]字节数组，调用String的getBytes()方法
        String str1 = "abc123中国";
        byte[] bytes = str1.getBytes();//使用默认的字符集，进行编码
        System.out.println(Arrays.toString(bytes));//[,,,,...]格式

        byte[] gbks = str1.getBytes("gbk");//使用gbk字符集进行编码
        System.out.println(Arrays.toString(gbks));
        System.out.println("*****************************");

        //2. 解码过程：byte[]  --->  String：(需要解码器)调用String的构造器
        String str2 = new String(bytes);//使用默认的字符集，进行解码
        System.out.println(str2);//abc123中国
        System.out.println("*****************************");

        String str3 = new String(gbks);
        System.out.println(str3);//出现乱码。原因：编码集和解码集不一致！

        String str4 = new String(gbks, "gbk");
        System.out.println(str4);//没有出现乱码。原因：编码集和解码集一致！    abc123中国


    }


    /*
    String 与 char[]之间的转换

    String  --->  char[]：调用String类对象的toCharArray()方法
    char[]  --->  String：调用String类的构造器
     */
    @Test
    public void test2(){
        String str1 = "abc123";  //题目：反转(bc12) + String与char[]之间转换，结果为：a21cb3的String串

        char[] toCharArray = str1.toCharArray();
        for (int i = 0; i < toCharArray.length; i++) {
            System.out.println(toCharArray[i]);
        }

        char[] arr = new char[]{'h','e','l','l','o'};
        String str2 = new String(arr);
        System.out.println(str2);

        //反转方法二：双指针i，j
//		for(int i = 0,j = arr.length - 1;i < j;i++,j--){
//			String temp = arr[i];
//			arr[i] = arr[j];
//			arr[j] = temp;

        //反转方法一：
//		for(int i = 0;i < arr.length / 2;i++){
//			String temp = arr[i];
//			arr[i] = arr[arr.length - i -1];
//			arr[arr.length - i -1] = temp;
//		}
    }

    /*
    复习：
    String与基本数据类型、包装类之间的转换。

    String  -->  基本数据类型、包装类：调用包装类的静态方法：包装类.parseXxx(String str)
    基本数据类型、包装类   -->  String：调用String重载的valueOf(xxx)。或者使用连接符 +""
     */
    @Test
    public void test1(){
        String str1 = "123";
        //不可强转，只有子父类关系才可强转
        //int num = (int)str1;
        int num = Integer.parseInt(str1);

        String str2 = String.valueOf(num);
        String str3 = num + "";//在堆里，有变量参与

        System.out.println(str1 == str3);//false


    }

}
