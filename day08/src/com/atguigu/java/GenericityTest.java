package com.atguigu.java;

import org.junit.Test;

import java.util.*;
import java.util.Map.*;

/**
 * 泛型的使用(Genericity)
 * 1.jdk 1.5新增的特性
 *
 * 2.在集合中使用泛型：
 *   总结：
 *   ① 集合接口或集合类在jdk5.0时都修改为带泛型的结构
 *   ② 在实例化集合类时，可以指明具体的泛型类型。（泛型是一个类型名）
 *   ③ 指明完以后，在集合类或接口中凡是定义类或接口时，内部结构(比如：方法，构造器，属性等)使用到类的泛型的位置，都指定为实例化时泛型类型
 *     比如：add(E e)   ---> 实例化集合类以后其add方法为：add(Integer e)
 *   ④ 注意点：泛型的类型必须是类，不能是基本数据类型。需要用到基本数据类型的位置，拿包装类替换
 *   ⑤ 如果实例化时，没有指明泛型的类型。默认类型为java.lang.Object类型。
 *
 *
 * 3.如何自定义泛型结构：泛型类、泛型接口；泛型方法。见GenericityTest1.java
 *
 *
 *
 * 注意：在接口/类中，它里面可以在方法中，或者属性中，构造器中可以使用接口/类的泛型。
 *      当我们实例化我们的类或者接口时，凡是其里面用到的泛型位置都变成实例化好的泛型的类型！
 *      泛型的类型不可使用基本数据类型，使用它的包装类。
 *
 * @author 田沛勋
 * @create 2020-08-09 16:49
 */
public class GenericityTest {

    /*
    在集合中使用泛型之前的情况
     */
    @Test
    public void  test1(){
        ArrayList list = new ArrayList();
        //需求：该集合存放学生的成绩
        list.add(76);
        list.add(86);
        list.add(92);
        list.add(56);
        //问题一：类型不安全
//        list.add("Tom");

        for(Object score : list){
            //问题二：强转时，有可能出现ClassCastException
            int stuScore = (Integer) score;//自动拆箱

            System.out.println(stuScore);

        }

    }


    /*
    在集合中使用泛型的情况：以ArrayList为例
    */
    @Test
    public void  test2(){
        ArrayList<Integer> list = new ArrayList<Integer>();

        list.add(78);
        list.add(95);
        list.add(23);
        list.add(56);
        //编译时，就会进行类型检查，保证数据的安全
//        list.add("Tom");

        //方式一：
        for(Integer score:list){
            //避免了强转操作
            int stuScore = score;

            System.out.println(stuScore);
        }

        //方式二：
        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()){
            int stuScore = iterator.next();
            System.out.println(stuScore);
        }
    }

    /*
    在集合中使用泛型的情况：以HashMap为例
    */
    @Test
    public void  test3(){
//        Map<String,Integer> map = new HashMap<String,Integer>();
        //jdk 7新特性：类型推断
        Map<String,Integer> map = new HashMap<>();

        map.put("Tom", 87);
        map.put("Jerry", 87);
        map.put("Jack", 87);

        //泛型的嵌套
        Set<Entry<String,Integer>> entry = map.entrySet();//<Map.Entry
        Iterator<Entry<String,Integer>> iterator = entry.iterator();//<Map.Entry

        while(iterator.hasNext()){
            Entry<String, Integer> e = iterator.next();
            String key = e.getKey();
            Integer value = e.getValue();
            System.out.println(key + "--------"+ value);

        }



    }

}
