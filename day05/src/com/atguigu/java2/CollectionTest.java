package com.atguigu.java2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * 一、集合框架的概述
 *
 * 1. 集合、数组都是对多个数据/对象，进行存储操作的结构，简称为Java容器。
 *
 *    (它是一个容器，是装对象/数据的，对象不止一个)
 *    说明：此时的存储，主要指的是内存层面的存储，不涉及到持久化的存储(.txt,.jpg,.avi,数据库中等)。
 *          (数据还是在内存里边，不涉及到硬盘的存储，持久化存储需要涉及到具体的媒介了，如硬盘，.txt)
 *    存储 = 内存层面  + 持久化层面
 *
 * 2.1 数组在存储多个数据方面的特点：
 *     > 一旦初始化以后，其长度就确定了。
 *     > 数组一旦定义好以后，其元素的类型也就确定了。我们只能操作指定类型的数据了。
 *       比如：String[] arr; int[] arr1; Object[] arr2; -- 多态性,好处：你想数组类型单一，不单一都可以
 *
 * 2.2 数组在存储多个数据方面的缺点：
 *     > 一旦初始化以后，其长度就不可修改。
 *     > 数组容器提供的方法非常有限，对于添加、删除、插入数据等操作(需要自己去写)，非常不便，同时效率不高(底层数据结构)。
 *     > 获取数组中实际元素的个数的需求，数组没有现成属性或方法可用。
 *     > 数组存储数据的特点：有序、可重复。对于先放谁后放谁？它是无序的、不可重复的需求，不能满足
 *
 * 二、集合框架(比数学中的集合范围更大)
 *      |-----Collection接口：单列集合，用来存储一个一个的对象
 *          |----List接口：存储有序的、可重复的数据。  --->称为“动态”数组(像Python中的列表)
 *              |---ArrayList、LinkedList、Vector(底层扩容原理：重点)
 *
 *          |----Set接口：存储无序的、不可重复的数据   --->高中讲的“集合”(无序性，确定性，互异性(不可重复性))
 *              |---HashSet、LinkedHashSet、TreeSet
 *
 *      |-----Map接口：双列集合，用来存储一对(key - value),一对的数据   --->高中函数：y = f(x),x = key,y = value。两个不同的key可以指向相同的value
 *              |---HashMap(重点jdk7，jdk8不同)、LinkedHashMap、TreeMap、Hashtable、Properties
 *
 *
 * 三、Collection接口中的方法的使用
 *
 *
 * 总结：
 * 1. set接口具有过滤的作用。
 *
 * @author 田沛勋
 * @create 2020-07-30 15:51
 */
public class CollectionTest {
    @Test
    public void test1(){
        Collection coll = new ArrayList();

        //1. add(Object e):将元素e添加到集合coll中
        coll.add("AA");
        coll.add("BB");
        coll.add(123);//自动装箱：Integer类
        coll.add(new Date());

        //2. size():获取添加的元素的个数
        System.out.println(coll.size());//4

        //3. addAll(Collection coll1):将coll1集合中的元素添加到当前的集合coll中
        Collection coll1 = new ArrayList();
        coll1.add(456);
        coll1.add("CC");
        coll.addAll(coll1);
        System.out.println(coll.size());//6
        System.out.println(coll);//[AA, BB, 123, Thu Jul 30 17:24:15 CST 2020, 456, CC]

        //4. clear():清空集合中的元素
//        coll.clear();

        //5. isEmpty():判断当前集合是否为空。return size == 0;
        System.out.println(coll.isEmpty());//false






    }


    @Test
    public void test2(){

    }

}
