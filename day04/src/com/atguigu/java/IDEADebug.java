package com.atguigu.java;

import org.junit.Test;

/**
 * char[] 默认值为：\u0000
 * @author 田沛勋
 * @create 2020-07-25 17:03
 */
public class IDEADebug {

    @Test
    public void testStringBuffer(){
        String str= null;
        StringBuffer sb= new StringBuffer();
        sb.append(str);

        System.out.println(sb.length());//4

        System.out.println(sb);//"null"

        StringBuffer sb1= new StringBuffer(str);//抛异常：NullPointerException，调用super(str.length() + 16);
        System.out.println(sb1);//执行不到


    }

}
