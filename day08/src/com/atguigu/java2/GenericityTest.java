package com.atguigu.java2;

import org.junit.Test;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * 1. 泛型在继承方面的体现
 *
 *
 *
 * 2. 通配符的使用
 *
 *
 * @author 田沛勋
 * @create 2020-08-10 10:20
 */
public class GenericityTest {
    /*
    1. 泛型在继承方面的体现

       虽然类A是类B的父类，但是 G<A> 和 G<B>二者不具备子父类关系，二者是并列关系.

        补充：类A是类B的父类，A<G> 是 B<G>的父类

     */
    @Test
    public void test1(){
        Object obj = null;
        String str = null;
        obj = str;

        Object[] arr1 = null;
        String[] arr2 = null;
        arr1 = arr2;

        //编译不通过
//        Date date = new Date();
//        str = date;
        List<Object> list1 = null;
        List<String> list2 = null;
        //此时的list1和list2的类型不具有子父类关系
        //编译不通过
//        list1 = list2;
        /*
        反例：反证法：
        假设list1 = list2;
            list1.add(123);导致混入非String的数据。出错。反推成立

         */

        show(list1);
//        show(list2);//错误
        show1(list2);

    }
    public void show1(List<String> list){

    }

    public void show(List<Object> list){//因为list1不是List<Object>的子类

    }

    @Test
    public void test2(){
        List<String> list1 = null;
        AbstractList<String> list2= null;
        ArrayList<String> list3 = null;

        list1 = list3;
        list2 = list3;

        List<String> list4 = new ArrayList<>();

    }


    /*
    2. 通配符的使用
      通配符：?

      类A是类B的父类，G<A> 和 G<B>是没有关系的(并列)，二者共同的父类是G<?>


     */
    @Test
    public void test3(){
        List<Object> list1 = null;
        List<String> list2 = null;

        //List<?> 作为List<Object>和List<String>两个并列关系的通用父类
        List<?> list;
        list = list1;
        list = list2;

        //编译通过
//        print(list1);
//        print(list2);



        //
        List<String> list3 = new ArrayList<>();
        list3.add("AA");
        list3.add("BB");
        list3.add("CC");
        list = list3;
        //添加(写入)：对于List<?>不能向其内部添加数据。
        //除了添加null之外
//        list.add("DD");
//        list.add("?");

        list.add(null);

        //获取(读取)：对于List<?>允许读取数据，读取的数据类型为? = Object。
        Object o = list.get(0);
        System.out.println(o);


    }


    public void print(List<?> list){
        Iterator<?> iterator = list.iterator();
        while(iterator.hasNext()){
            Object obj = iterator.next();//? obj = 不行
            System.out.println(obj);
        }
    }


    /*
    3.有限制条件的通配符的使用
        ? extends A:
                G<? extends A>  可以作为G<A>和G<B>的父类，其中B是A的子类
        ? super A:
                G<? super A>  可以作为G<A>和G<B>的父类，其中B是A的父类
        ? extends Comparable:
     */
    @Test
    public void test4(){


        List<? extends Person> list1 = null;//？小于等于Person
        List<? super Person> list2 = null;//？大于等于Person

        List<Student> list3 = new ArrayList<>();
        List<Person> list4 = new ArrayList<>();
        List<Object> list5 = new ArrayList<>();

        list1 = list3;
        list1 = list4;
//        list1 = list5;

//        list2 = list3;
        list2 = list4;
        list2 = list5;

        //读取数据
        list1 = list4;
        Person p = list1.get(0);
        //编译不通过
        //Student s = list1.get(0);

        list2 = list4;
        Object obj = list2.get(0);
        //编译不通过
        //Person s = list2.get(0);


        //写入数据
        //编译不通过
//        list1.add(new Student());

        list2.add(new Person());
        list2.add(new Student());


    }
}
