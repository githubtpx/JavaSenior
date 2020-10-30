package com.atguigu.java;


import org.junit.Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * @author 田沛勋
 * @create 2020-08-24 8:48
 */
public class Java9Test {


    //jshell:java交互式
    @Test
    public void test1()  {
        try {
            URL url = new URL("www.baidu.com");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    //java9特性5：钻石操作符的升级(与匿名实现类共同使用钻石操作符)
    @Test
    public void test2(){
        //钻石操作符与匿名内部类在java 8 不能共存，在java 9可以共存。
        Comparable<Object> com = new Comparable<>() {
            @Override
            public int compareTo(Object o) {
                return 0;
            }
        };

        //jdk 7中的新特性：类型推断
        ArrayList<Object> list = new ArrayList<>();

    }

    //java9 特性六：try操作的升级
    public static void main(String[] args) {
        //java 8 之前的资源关闭的操作
//        InputStreamReader isr = null;
//        try {
//            isr = new InputStreamReader(System.in);
//
//            char[] cbuf = new char[20];
//            int len;
//            if((len = isr.read(cbuf))!= -1){
//                String str = new String(cbuf,0,len);
//                System.out.println(str);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if(isr != null);
//                isr.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }


        //java 8中资源关闭操作：
        //要求自动关闭的资源的实例化(初始化)必须放在try的()中
//        try(InputStreamReader isr = new InputStreamReader(System.in)){//try()，()中是需要关闭的资源
//            char[] cbuf = new char[20];
//            int len;
//            if((len = isr.read(cbuf))!= -1){
//                String str = new String(cbuf,0,len);
//                System.out.println(str);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//


        //java 9中资源关闭操作：
        //需要自动关闭的资源的实例化可以放在try的一对小括号外。
        //此时的资源属性是常量，声明为final的，不可修改
        //用资源语句编写try将更容易，我们可以在try子句中使用已经初始化过的资源，此时的资源是final的：
        InputStreamReader isr = new InputStreamReader(System.in);
        try(isr){
            char[] cbuf = new char[20];
            int len;
            if((len = isr.read(cbuf))!= -1){
                String str = new String(cbuf,0,len);
                System.out.println(str);
            }

//            reader = null;reader不能改是常量
        } catch (IOException e) {
            e.printStackTrace();
        }

//        InputStreamReader reader = new InputStreamReader(System.in);
//        OutputStreamWriter writer = new OutputStreamWriter(System.out);
//        try (reader; writer) {

          //reader是final的，不可再被赋值
          // reader = null;
//        //具体读写操作省略
//        }
//        catch (IOException e) {
//          e.printStackTrace();
//        }
        }


}
