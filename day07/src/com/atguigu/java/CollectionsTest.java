package com.atguigu.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Collections：操作Collection(List、Set)、Map的工具类
 *
 *
 * 面试题：Collection 和 Collections的区别？
 * Collections操作Collection、Map集合的工具类。
 * Collection它是存储单列数据的集合接口。常见的子接口List、Set
 *
 *
 *
 * @author 田沛勋
 * @create 2020-08-04 11:36
 */
public class CollectionsTest {
    /*
排序操作相关的方法
reverse(List)：反转List 中元素的顺序
shuffle(List)：对List集合元素进行随机排序
sort(List)：根据元素的自然顺序对指定List 集合元素按升序排序
sort(List，Comparator)：根据指定的Comparator 产生的顺序对List 集合元素进行排序
swap(List，int，int)：将指定list 集合中的i处元素和j 处元素进行交换

查找、替换
Object max(Collection)：根据元素的自然顺序，返回给定集合中的最大元素
Object max(Collection，Comparator)：根据Comparator 指定的顺序，返回给定集合中的最大元素
Object min(Collection)
Object min(Collection，Comparator)
int frequency(Collection，Object)：返回指定集合中指定元素的出现次数
void copy(List dest,List src)：将src中的内容复制到dest中
boolean replaceAll(List list，Object oldVal，Object newVal)：使用新值替换List对象的所有旧值
     */
    @Test
    public void test2(){
        List list = new ArrayList();

        list.add(123);
        list.add(342);
        list.add(32);
        list.add(4);
        list.add(-5);
        list.add(-0);

        //报异常:"Source does not fit in dest"  --->src.size() > dest.size()
//        List dest = new ArrayList();
//        Collections.copy(dest, list);
//        List dest = new ArrayList(list.size());

        //方法一：
//        dest.add(123);
//        dest.add(123);
//        dest.add(123);
//        dest.add(123);
//        dest.add(123);
//        dest.add(123);
//        System.out.println(dest.size());//0

        //方法二：正确的
        List dest = Arrays.asList(new Object[list.size()]);//该数组已经初始化null了

        System.out.println(dest);
        System.out.println(dest.size());

        Collections.copy(dest, list);
        System.out.println(dest);

    }

    @Test
    public void test1(){
        List list = new ArrayList();

        list.add(123);
        list.add(342);
        list.add(32);
        list.add(4);
        list.add(4);
        list.add(4);
        list.add(5);
        list.add(-5);
        list.add(-0);
        System.out.println(list);

//        Collections.reverse(list);
//        Collections.shuffle(list);
//        Collections.sort(list);
//        Collections.swap(list, 1, 2);

        int frequency = Collections.frequency(list, 4);
        System.out.println(list);
        System.out.println(frequency);


    /*
    Collections 类中提供了多个synchronizedXxx() 方法，
    该方法可使将指定集合包装成线程同步的集合，从而可以解
    决多线程并发访问集合时的线程安全问题

     */
        //返回的list1即为线程安全的List
    List list1 = Collections.synchronizedList(list);


    }


}
