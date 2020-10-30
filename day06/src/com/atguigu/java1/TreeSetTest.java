package com.atguigu.java1;

import org.junit.Test;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * @author 田沛勋
 * @create 2020-08-01 10:09
 */
public class TreeSetTest {

    /*
    1. 向TreeSet中添加的数据，要求是同一个类的对象。(只有相同类的对象才可以比较大小进行排序)
    2. 两种排序方式：自然排序(实现Comparable接口)  和 定制排序(Comparator接口)

       TreeSet判断不可重复的标准：使用两种不同排序方法比较是否重复:
    3. 自然排序中，比较两个对象是否相同的标准为：调用compareTo()方法返回0。不再是equals().
    4. 定制排序中，比较两个对象是否相同的标准为：调用compare(Object o1,Object o2)方法返回0，不再是equals().

     */
    @Test
    public void test1(){
        TreeSet set = new TreeSet();
        //失败：不能添加不同类的对象
//        set.add(123);
//        set.add(456);
//        set.add("AA");
//        set.add(new User("Tom", 12));

        //举例一：Integer对象，String对象实现comparable接口
//        set.add(34);
//        set.add(-34);
//        set.add(8);
//        set.add(11);
//        set.add(8);

        //举例二：
        set.add(new User("Tom", 12));
        set.add(new User("Jerry", 32));
        set.add(new User("Jim", 2));
        set.add(new User("Mike", 65));
        set.add(new User("Jack", 33));
        set.add(new User("Jack", 56));


        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }


    @Test
    public void test2(){
        Comparator com = new Comparator() {

            //按照年龄(id)从小到大排列。年龄(id)一样不要了。
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof User && o2 instanceof User){
                    User u1 = (User)o1;
                    User u2 = (User)o2;
                    return Integer.compare(u1.getAge(), u2.getAge());
                }else{
                    throw new RuntimeException("输入数据类型不匹配！");
                }
            }
        };

        TreeSet set = new TreeSet(com);

        set.add(new User("Tom", 12));
        set.add(new User("Jerry", 32));
        set.add(new User("Jim", 2));
        set.add(new User("Mike", 65));
        set.add(new User("Jack", 33));
        set.add(new User("Lucy", 33));//先来后到
        set.add(new User("Jack", 56));


        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }


    }

}
