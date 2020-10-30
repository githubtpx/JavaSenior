package com.atguigu.exer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * @author 田沛勋
 * @create 2020-08-01 15:13
 */
public class CollectionTest {
    @Test
    public void test1(){
        Collection coll = new ArrayList();

        coll.add(123);
        coll.add(456);
        coll.add(789);
        coll.add(110);

        //java 8新特性：lamda表达式
        coll.forEach(System.out::println);//形参Consume接口。方法引用：System.out::println

    }

    //练习：在List内去除重复数字值，要求尽量简单(使用Set接口充当过滤重复数据的工具)
    public static List duplicateList(List list) {
        HashSet set= new HashSet();
        set.addAll(list);
        return new ArrayList(set);
    }

    @Test
    public void test2(){
        List list= new ArrayList();
        list.add(new Integer(1));
        list.add(new Integer(2));
        list.add(new Integer(2));
        list.add(new Integer(4));
        list.add(new Integer(4));
        List list2= duplicateList(list);

        for(Object integer: list2) {
            System.out.println(integer);
        }

    }

    @Test
    public void test3(){
        HashSet set = new HashSet();
        Person p1 = new Person(1001,"AA");
        Person p2 = new Person(1002,"BB");

        set.add(p1);
        set.add(p2);
        System.out.println(set);//[Person{id=1002, name='BB'}, Person{id=1001, name='AA'}]

        p1.name = "CC";
        set.remove(p1);
        System.out.println(set);//[Person{id=1002, name='BB'}, Person{id=1001, name='CC'}]

        set.add(new Person(1001,"CC"));
        System.out.println(set);//[Person{id=1002, name='BB'}, Person{id=1001, name='CC'}, Person{id=1001, name='CC'}]

        set.add(new Person(1001,"AA"));
        System.out.println(set);//[Person{id=1002, name='BB'}, Person{id=1001, name='CC'}, Person{id=1001, name='CC'}, Person{id=1001, name='AA'}]
    }


}
