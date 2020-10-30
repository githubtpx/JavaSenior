package com.atguigu.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 如何自定义泛型结构：泛型类、泛型接口；泛型方法。
 *
 * 1.关于自定义泛型类、泛型接口：
 *
 *
 *      异常类不能声明为泛型类
 *      静态方法中不能使用类的泛型
 *
 *
 * 原来的判断、强转，加上泛型不用加这些了，更简单了。
 *
 * @author 田沛勋
 * @create 2020-08-10 8:15
 */
public class GenericityTest1 {
    @Test
    public void test1(){
        //如果定义了泛型类，实例化时没有指明类的泛型，则认为此泛型类型为Object类型
        //要求：如果大家定义了类是带泛型的，建议在实例化时要指明类的泛型。
        Order order = new Order();
        order.setOrderT(123);
        order.setOrderT("AA");

        //建议：实例化时指明类的泛型
        Order<String> order1 = new Order<>("orderAA",1001,"order:AA");
        order.setOrderT("AA:hello");

    }

    @Test
    public void test2(){
        SubOrder sub1 = new SubOrder();
        //由于子类在继承带泛型的父类时，指明了父类的泛型类型，则实例化子类对象时，不再需要指明泛型

        SubOrder1<String> stu2 = new SubOrder1<>();
        stu2.setOrderT("order2...");
    }


    @Test
    public void test3(){
        ArrayList<String> list1 = null;
        ArrayList<Integer> list2 = null;
        //泛型不同的引用不能相互赋值
//        list1 = list2;

        Person p1 = null;
        Person p2 = null;
        p1 = p2;

    }

    //测试泛型方法
    @Test
    public void test4(){
        Order<String> order = new Order<>();
        Integer[] arr = new Integer[]{1,2,3,4};
        //泛型方法在调用时，指明泛型参数的类型。
        List<Integer> list = order.copyFromArrayToList(arr);

        System.out.println(list);


    }

}
