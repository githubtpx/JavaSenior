package com.atguigu.exer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 田沛勋
 * @create 2020-07-31 17:00
 */
public class ListExer {
    /*
    区分List中remove(int index)和remove(Object obj)
     */

    //面试题
    @Test
    public void testListRemove() {
        List list= new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        updateList(list);
        System.out.println(list);//[1,2]
    }
    private void updateList(List list) {
//        list.remove(2);
        list.remove(new Integer(2));//删除对象2，输出list为[1,3]
    }


}
