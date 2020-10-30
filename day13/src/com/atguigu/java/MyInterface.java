package com.atguigu.java;

/**
 * @author 田沛勋
 * @create 2020-08-24 9:30
 */
public interface MyInterface {
    //如下的，三个方法权限修饰符都是public
    void methodAbstract();

    static void methodStatic(){
        System.out.println("我是接口中的静态方法");
    }

    default void methodDefault(){
        System.out.println("我是接口中的默认方法");

        methodPrivate();
    }

    //java 9中允许接口中定义私有方法
    private void methodPrivate(){
        System.out.println("我是接口中的私有方法");
    }

}
