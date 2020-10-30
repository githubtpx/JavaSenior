package com.atguigu.exer;

/**
 * 一道面试题:值传递
 * （1.String不可变性：我把地址值付给你了，你改了，我还是不变，因为我本身没改也不能改。
 *   2.其它可变，如数组）
 *
 * @author 田沛勋
 * @create 2020-07-23 8:34
 */
public class StringTest {
    String str = new String("good");
    char[] ch = {'t', 'e', 's', 't'};

    public void change(String str, char ch[]) {
        //数组可变，String不可变性
        str = "test ok";
        ch[0] = 'b';
    }

    public static void main(String[] args) {
        StringTest ex = new StringTest();
        ex.change(ex.str, ex.ch);
        System.out.println(ex.str);//good
        System.out.println(ex.ch);//best
    }
}

