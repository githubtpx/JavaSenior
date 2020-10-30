package com.atguigu.java2;

import com.atguigu.java1.Person;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author 田沛勋
 * @create 2020-08-18 17:59
 */
public class OtherTest {
    /*
    获取构造器结构

     */
    @Test
    public void test1() throws ClassNotFoundException {
        Class clazz = Class.forName("com.atguigu.java1.Person");

        //getConstructors():获取当前运行时类中声明为public的构造器
        Constructor[] constructors = clazz.getConstructors();
        for(Constructor c : constructors){
            System.out.println(c);
        }
        System.out.println();

        //getDeclaredConstructors():获取当前运行时类中声明的所有的构造器。(不包含父类声明的构造器)
        Constructor[] declaredConstructors = clazz.getDeclaredConstructors();
        for(Constructor c :declaredConstructors){
            System.out.println(c);
        }


    }


    /*
    获取运行时类的父类
     */
    @Test
    public void test2(){
        Class clazz = Person.class;

        Class superclass = clazz.getSuperclass();
        System.out.println(superclass);//com.atguigu.java1.Creature
    }


    /*
    获取运行时类的带泛型的父类
     */
    @Test
    public void test3(){
        Class clazz = Person.class;

        Type genericSuperclass = clazz.getGenericSuperclass();
        System.out.println(genericSuperclass);//com.atguigu.java1.Creature<java.lang.String>
    }

    /*
    重要：获取运行时类的带泛型的父类的泛型

    代码：逻辑性代码  vs  功能性代码
     */
    @Test
    public void test4(){
        Class clazz = Person.class;

        Type genericSuperclass = clazz.getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType) genericSuperclass;//创建泛型参数/类型对象

        //获取泛型类型(参数)：获取实际上的类型(泛型)的参数
        //Class实现了Type接口
        Type[] actualTypeArguments = paramType.getActualTypeArguments();
//        System.out.println(actualTypeArguments[0].getTypeName());
        System.out.println(((Class)actualTypeArguments[0]).getName());

    }


    /*
    获取运行时类实现的接口(代理类会获取运行时类被代理类所实现的接口)
     */
    @Test
    public void test5(){
        Class clazz = Person.class;

        Class[] interfaces = clazz.getInterfaces();
        for(Class i : interfaces){
            System.out.println(i);
        }

        System.out.println();

        //获取运行时类的父类实现的接口
        Class[] interfaces1 = clazz.getSuperclass().getInterfaces();
        for(Class i : interfaces1){
            System.out.println(i);
        }

    }

    /*
    获取运行时类所在的包
     */
    @Test
    public void test6(){
        Class clazz = Person.class;

        Package pack = clazz.getPackage();
        System.out.println(pack);
    }

    /*
    获取运行时类声明的注解
     */
    @Test
    public void test7(){
        Class clazz = Person.class;

        Annotation[] annotations = clazz.getAnnotations();
        for(Annotation a: annotations){
            System.out.println(a);
        }
    }


}
