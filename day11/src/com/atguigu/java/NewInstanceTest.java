package com.atguigu.java;

import org.junit.Test;

import java.util.Random;

/**
 * 通过反射创建对应运行时类的对象
 *
 * @author 田沛勋
 * @create 2020-08-18 9:57
 */
public class NewInstanceTest {
    @Test
    public void test1() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<Person> clazz = (Class<Person>) Class.forName("com.atguigu.java.Person");

        /*
        newInstance():调用此方法，创建对应运行时类的对象。内部调用运行时类的空参的构造器。

        要想此方法正常的创建运行时类的对象，要求：
        1. 运行时类必须提供空参的构造器
        2. 空参的构造器，访问权限得够。通常设置为public。


        在javabean中要求提供一个public的空参构造器。原因：
        1. 便于通过反射去创建运行时类的对象
        2. 便于子类继承此运行时类时，默认调用super()时，保证父类有此构造器。

        喜欢写通用创建运行时类对象的方法，常用空参的构造器去造对象，带参的有困难。

         */
        Person obj = clazz.newInstance();
        System.out.println(obj);

    }

    //体会反射的动态性 -- 对应着框架的通用性的体现：在运行时才知道到底造哪个类的对象
    @Test
    public void test2(){
        for (int i = 0; i < 100; i++) {
            int num = new Random().nextInt(3);//0,1,2
            String classPath ="";
            switch(num){
                case 0:
                    classPath = "java.util.Date";
                    break;
                case 1:
                    classPath = "java.lang.Object";//"java.sql.Date"，Integer它下没有空参构造器
                    break;
                case 2:
                    classPath = "com.atguigu.java.Person";
                    break;
            }

            try {
                Object obj = getInstance(classPath);
                System.out.println(obj);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }



    }

    /*
    此方法创建一个指定类的对象。
    classPath:指定类的全类名
     */
    public Object getInstance(String classPath) throws Exception {
        Class clazz = Class.forName(classPath);
        return clazz.newInstance();
    }


}
