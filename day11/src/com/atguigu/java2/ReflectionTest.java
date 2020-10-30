package com.atguigu.java2;

import com.atguigu.java1.Person;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 调用运行时类中指定的结构：属性、方法、构造器
 *
 * 反射的源头：Class的实例
 *
 * @author 田沛勋
 * @create 2020-08-18 20:35
 */
public class ReflectionTest {

    /*
        不需要掌握，不理想对于属性的权限太高了：public
     */
    @Test
    public void testField() throws Exception {
        Class clazz = Person.class;

        //创建运行时类的对象
        Person p = (Person) clazz.newInstance();

        //获取指定的属性(非静态的属性需要依托于对象)：要求运行时类的属性声明为public权限。
        //通常不采用此方法
        Field id = clazz.getField("id");

        /*
        设置当前非静态的属性的值

        set():参数1：指明设置哪个对象的属性   参数2：将此属性设置为多少
         */
//        id.set(p, 1001);

        /*
        获取当前属性的值
        get():参数1：获取哪个对象的当前属性值
         */
        int pid = (int) id.get(p);
        System.out.println(pid);

    }


    /*
    如何操作运行时类中的指定的属性   -- 需要掌握！
     */
    @Test
    public void testField1() throws Exception {
        Class clazz = Person.class;

        //创建运行时类的对象
        Person p = (Person) clazz.newInstance();

        //获取指定的属性:
        //1. getDeclaredField(String fieldName)：获取运行时类中指定变量名的属性
        //IllegalAccessException非法访问说明已经找到了，但是不让访问
        Field name = clazz.getDeclaredField("name");

        //2. 保证当前属性是可访问的
        name.setAccessible(true);

        //3. 获取、设置指定对象的此属性值
        name.set(p,"Tom");//静态属性，p = Person.class 或 null

        System.out.println(name.get(p));//静态属性，p = Person.class 或 null


    }

    /*
    如何操作运行时类中的指定的方法   -- 需要掌握！
     */
    @Test
    public void testMethod() throws Exception {

        Class clazz = Person.class;

        Person p = (Person) clazz.newInstance();

        /*
        1.获取指定的某个(非静态)方法
        getDeclaredMethod():参数1：指明获取的方法的名称   参数2：指明获取的方法的形参列表
         */
        Method show = clazz.getDeclaredMethod("show", String.class);

        //2.保证当前方法是可访问的
        show.setAccessible(true);

        /*
        3.调用方法的invoke()执行:  参数1：方法的调用者     参数2：给方法的形参赋值的实参
          invoke()方法的返回值即为对应类中调用方法的返回值。
         */
        Object returnValue = show.invoke(p, "CHN");//String nation = p.show("CHN")
        System.out.println(returnValue);

        System.out.println("****************如何调用静态方法*******************");

        // private static void showDesc()
        Method showDesc = clazz.getDeclaredMethod("showDesc");
        showDesc.setAccessible(true);

        //如果调用的运行时类的方法没有返回值，则此invoke()返回null
//        Object returnVal = showDesc.invoke(Person.class);
        Object returnVal = showDesc.invoke(null);//clazz本身是运行时类对象，静态方法直接调用不用指定调用者
        System.out.println(returnVal);//null




    }

    /*
    如何调用运行时类中的指定的构造器
    */
    @Test
    public void testConstructor() throws Exception {

        Class clazz = Person.class;


        // private Person(String name)
        /*
        1.获取指定的构造器
        getDeclaredConstructor():参数：指明构造器的参数列表
         */
        Constructor constructor = clazz.getDeclaredConstructor(String.class);

        //2.保证此构造器是可访问的
        constructor.setAccessible(true);

        //3.调用此构造器创建运行时类的对象
        Person per = (Person) constructor.newInstance("Tom");
        System.out.println(per);


    }




}
