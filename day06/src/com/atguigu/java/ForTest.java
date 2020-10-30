package com.atguigu.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

/**
 * jdk 5.0 新增了foreach循环，用于遍历数组、集合
 * (增强for循环：foreach)
 *
 * @author 田沛勋
 * @create 2020-07-31 10:41
 */
public class ForTest {
    @Test
    public void test1(){
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new Person("Jerry", 20));
        coll.add(new String("Tom"));
        coll.add(false);

        //for(集合元素的类型  局部变量 : 集合对象)
        //内部仍然调用了迭代器。形式上不一样，本质上一样
        for(Object obj : coll){
            System.out.println(obj);
        }

    }

    @Test
    public void test2() {
        //for(数组元素的类型  局部变量 : 数组对象)
        int[] arr = new int[]{1,2,3,4,5,6};
        for(int i : arr){
            System.out.println(i);
        }

    }

    //数组练习题：
    @Test
    public void test3() {
        String[] arr = new String[]{"MM","MM","MM"};

        //方式一：普通for赋值
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = "GG";
//        }

        //方式二：增强for循环赋值(foreach),重写将arr中的元素取出来赋值给了变量s，只修改了变量s，而本身没有改
        for(String s : arr){
            s = "GG";
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);//MM
        }





    }

}
