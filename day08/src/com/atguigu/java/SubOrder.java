package com.atguigu.java;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 由于子类在继承带泛型的父类时，指明了父类的泛型类型，则实例化子类对象时，不再需要指明泛型
 *
 * @author 田沛勋
 * @create 2020-08-10 8:32
 */
public class SubOrder  extends Order<Integer>{//SubOrder：不是泛型类

    public static <E> List<E> copyFromArrayToList(E[] arr){
        ArrayList<E> list = new ArrayList<>();

        for(E e:arr){
            list.add(e);
        }
        return list;
    }

}
