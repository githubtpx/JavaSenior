package com.atguigu.java;

import org.junit.Test;

import java.util.Optional;

/**
 * @author 田沛勋
 * @create 2020-08-23 20:02
 */
public class OptionalTest {

    @Test
    public void test1(){
        //empty(): 创建的Optional对象内部的value = null
        Optional<Object> op1 = Optional.empty();
        if(!op1.isPresent()){//判断Optional封装的数据是否包含数据
            System.out.println("数据为空");
        }

        System.out.println(op1);

        //如果Optional封装的数据value为null，则get()方法报错。否则，value不为null时，返回value。
//        System.out.println(op1.get());
        System.out.println(op1.isPresent());

    }

    @Test
    public void test2(){
        String str = "hello";
//        str = null;
        //of(T t): 封装数据t生成Optional对象。要求t非空，否则报错
        Optional<String> op1 = Optional.of(str);
        //get()通常与of()方法搭配使用，用于获取内部封装的数据value。
        String str1 = op1.get();
        System.out.println(str1);

    }

    @Test
    public void test3(){
        String str = "beijing";
        str = null;
        //ofNullable(T t): 封装数据t赋给Optional内部属性value。不要求t非空。
        Optional<String> op1 = Optional.ofNullable(str);
        //orElse(T t1):如果Optional内部的value非空，则返回此value值。
        //如果value为null，则返回t1
        String str1 = op1.orElse("shanghai");

        System.out.println(str1);

    }


}
