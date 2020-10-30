package com.atguigu.java1;

import java.io.Serializable;

/**
 * @author 田沛勋
 * @create 2020-08-18 10:59
 */
public class Creature<T> implements Serializable {
    private char gender;
    public double weight;

    private void breath(){
        System.out.println("生物呼吸");
    }

    public void eat(){
        System.out.println("生物吃东西");
    }


}
