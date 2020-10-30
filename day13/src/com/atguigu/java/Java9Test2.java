package com.atguigu.java;


import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author 田沛勋
 * @create 2020-08-24 16:11
 */
public class Java9Test2 extends TestCase {

    //java9 新特性十：增强的Stream API
    @Test
    public void test6() {
        //1. takeWhile()的使用
        //takeWhile返回从开头开始的尽量多的元素。按照我们指定的规则。
        List<Integer> list = Arrays.asList(2, 23, 4, 5, 12, 40, 345, 60, 645, 312);
//        list.stream().takeWhile(x -> x < 40).forEach(System.out::println);

        //返回剩余的元素。
        list.stream().dropWhile(x -> x < 40).forEach(System.out::println);

    }

    @Test
    public void test2() {
        //of()参数中的多个元素，是可以包含null值，但是不能只是一个null
        Stream<Integer> stream = Stream.of(1, 2, 3, null);
        stream.forEach(System.out::println);

        //of()参数不能存储单个null。否则报异常
//        Stream<Object> stream1 = Stream.of(null);
//        stream1.forEach(System.out::println);
        //可以
        Stream<Object> stream2 = Stream.of(null, null);

        //ofNullable():形参变量是可以为null值得单个元素。参数只有一个元素，创建单个元素的Stream
        Stream<Integer> stream3 = Stream.ofNullable(null);
        Stream<Integer> stream4 = Stream.ofNullable(1);
        long count = stream3.count();
        System.out.println(count);//0
        stream4.forEach(System.out::println);
    }

    @Test
    public void test3() {
        //java8 方式四方式创建Stream()

        //原来的控制终止方式：
        Stream.iterate(0, x -> x + 2).limit(10).forEach(System.out::println);

        //java9 中新增的重载的方法。
        //现在终止方式：
        Stream.iterate(0, x -> x < 100, x -> x + 1).forEach(System.out::println);
    }

    //java 9新特性十一：Optional容器提供了新的stream()
    @Test
    public void test4(){
        List<String> list= new ArrayList<>();
        list.add("Tom");
        list.add("Jerry");
        list.add("Tim");
        Optional<List<String>> optional= Optional.ofNullable(list);
        Stream<List<String>> stream= optional.stream();
        long count = stream.count();//1
        stream.flatMap(x-> x.stream()).forEach(System.out::println);

    }


}
