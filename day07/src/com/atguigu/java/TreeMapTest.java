package com.atguigu.java;

import org.junit.Test;

import java.util.*;

/**
 * @author 田沛勋
 * @create 2020-08-02 20:56
 */
public class TreeMapTest {

    //向TreeMap中添加key-value对，要求添加的key必须是同一个类创建的对象
    //因为要按照key进行排序：自然排序 、 定制排序


    //自然排序
    @Test
    public void test1(){
        TreeMap map = new TreeMap();

        User u1 = new User("Tom", 23);
        User u2 = new User("Jerry", 32);
        User u3 = new User("Jack", 20);
        User u4 = new User("Rose", 18);

        map.put(u1,98);
        map.put(u2,89);
        map.put(u3,76);
        map.put(u4,100);

        //方式一：entrySet()
        Set entrySet = map.entrySet();
        Iterator iterator1 = entrySet.iterator();
        while(iterator1.hasNext()){
            Object obj = iterator1.next();
            //entrySet集合中的元素都是Entry对象
            Map.Entry entry  = (Map.Entry)obj;
            System.out.println(entry.getKey() + "---->" + entry.getValue());
        }

    }


    //定制排序： 按照key：User对象的age从小到大排序
    @Test
    public void test2(){

        TreeMap map = new TreeMap(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof User && o2 instanceof  User){
                    User u1 = (User) o1;
                    User u2 = (User) o2;
//                    return u1.getAge() - u2.getAge();
                    return Integer.compare(u1.getAge(), u2.getAge());
                }
                throw new RuntimeException("输入的类型不匹配");
            }
        });

        User u1 = new User("Tom", 23);
        User u2 = new User("Jerry", 32);
        User u3 = new User("Jack", 20);
        User u4 = new User("Rose", 18);

        map.put(u1,98);
        map.put(u2,89);
        map.put(u3,76);
        map.put(u4,100);

        //方式一：entrySet()
        Set entrySet = map.entrySet();
        Iterator iterator1 = entrySet.iterator();
        while(iterator1.hasNext()){
            Object obj = iterator1.next();
            //entrySet集合中的元素都是Entry对象
            Map.Entry entry  = (Map.Entry)obj;
            System.out.println(entry.getKey() + "---->" + entry.getValue());
        }



    }


}
