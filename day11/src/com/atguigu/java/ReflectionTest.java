package com.atguigu.java;

import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author 田沛勋
 * @create 2020-08-17 17:18
 */
public class ReflectionTest {


    //反射之前，对于Person类的操作
    @Test
    public void test1(){

        //1.创建Person类的对象
        Person p1 = new Person("Tom", 25);

        //2.通过对象，调用其内部的属性和方法
        p1.age = 10;
        System.out.println(p1.toString());

        p1.show();

        //在Person类外部，不可以通过Person类对象，调用其私有结构。  --受封装性的限制
        //比如：name、showNation()、以及私有的构造器

    }

    //反射之后，对于Person类的操作
    @Test
    public void test2() throws Exception {
        //此Class类：用来描述Person类的类，它像一面镜子，反射出来的还是Person类内部的结构+自己定义的结构。
        Class clazz = Person.class;//加载到内存中的类本身(运行时类)，我们说是Class的实例，不能直接Person写，补了一个属性:类本身.class
        //1.通过反射，创建Person类的对象
        Constructor cons = clazz.getConstructor(String.class,int.class);
        Object obj = cons.newInstance("Tom", 22);
        Person p = (Person)obj;
        System.out.println(p.toString());

        //2.通过反射，调用对象指定的属性、指定方法
        //2.1 调用对象指定的属性
        //获取到clazz描述的Person类里面名字叫age的属性，并修改其对象下的指定属性的值
        Field age = clazz.getDeclaredField("age");
        age.set(p,10);//将p对象中的age属性值改为10
        System.out.println(p);

        //2.2 调用对象指定的方法
        Method show = clazz.getDeclaredMethod("show");
        show.invoke(p);//invoke调用

        System.out.println("*******************************************");

        //通过反射，可以调用运行时Person类的私有结构的。比如：私有的构造器、方法、属性
        //调用私有的构造器
        Constructor cons1 = clazz.getDeclaredConstructor(String.class);
        cons1.setAccessible(true);//使得构造器可用
        Person p1 = (Person) cons1.newInstance("Jerry");
        System.out.println(p1);

        //调用私有的属性
        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);
        name.set(p1,"HanMeimei");
        System.out.println(p1);

        //调用私有的方法
        Method showNation = clazz.getDeclaredMethod("showNation", String.class);
        showNation.setAccessible(true);
        String nation = (String)showNation.invoke(p1, "中国");//相当于String nation = p1.showNation("中国")
        System.out.println(nation);

    }


    //疑问？1. 反射机制与面向对象中的封装性是不是矛盾的呢？如果看待两个技术？
    //不矛盾。封装性给我们提示：到底建议怎么调用的问题。  反射给我们提示：我能不能调用的问题。


    //疑问？2. 通过直接new的方式或反射的方式都可以调用公共的结构，开发中到底用哪个？
    //建议：直接new的方式。
    //什么时候会使用：反射的方式。  反射的特征：动态性
    /* 动态性：在服务器的运行期间。java代码层面已经跑起来了，不知道该造哪个类对象，
     因为我不知道你到底是想(注册还是登陆)，客户端你发过来时注册还是登陆，
     我就造相关类的对象(注册 / 登陆的对象)，去调用相关的方法，这就是动态性。这个特性需要反射来做！
    */

    // 如果我们一开始在编译的时候，不能确定下来new谁，确定不下来到底要造哪个类的对象，不排除？    使用反射的方式！
    // 也就是说编译的时候，这个如果就确定下来new谁了？    使用new的方式！


    /*
    关于java.lang.Class类的理解
    1. 类的加载过程：
    程序在经过javac.exe命令以后，会生成一个或多个字节码文件(.class结尾)。

    接着我们使用java.exe命令对某个字节码文件(main方法所对应类)进行解释运行，相当于将字节码文件
    所对应的类加载到内存中。此过程就称为"类的加载过程"。加载到内存中的类，我们就称为运行时类
    (因为你运行时进来内存的)，此运行时类，就作为Class的一个实例。

    2.换句话说，Class的实例(不能new)  就对应着一个运行时类。

    3.加载到内存中的运行时类，会存放在缓存区，缓存一定时间。在此时间之内，我们可以通过不同方式
      来获取此运行时类。
     */


    //获取大的Class类实例方式：(前三种方式需要掌握)
    //Class对于类通用描述。其泛型是它对象所属类。
    @Test
    public void test3() throws ClassNotFoundException {
        //方式一：调用运行时类属性：.class。         运行时类本身是它的一个实例
        Class<Person> clazz1 = Person.class;//编译时，定死了
        System.out.println(clazz1);//class com.atguigu.java.Person

        //方式二：通过运行时类的对象,调用getClass(),获取类本身
        Person p1 = new Person();
        Class clazz2 = p1.getClass();//对象所属的类本身：p1.getClass()
        System.out.println(clazz2);

        //方式三：调用大的Class的静态方法：forName(String classPath),运行时类的路径
        Class clazz3 = Class.forName("com.atguigu.java.Person");//类的全类名
//        clazz3 = Class.forName("java.lang.String");
        System.out.println(clazz3);


        System.out.println(clazz1 == clazz2);//true
        System.out.println(clazz2 == clazz3);//true


        //方式四：使用类的加载器：ClassLoader   (了解)
        ClassLoader classLoader = ReflectionTest.class.getClassLoader();
        Class clazz4 = classLoader.loadClass("com.atguigu.java.Person");
        System.out.println(clazz4);

        System.out.println(clazz1 == clazz4);



    }


    //万事万物皆对象？ 对象.xxx，File,URL,反射,前端,数据库操作
    //反射：
    //1.任何一个类也是对象。这样类当中静态结构不是直接通过类去调用呢？那怎么还叫对象呢？
    //2.类本身还是对象，可以理解为通过对象来调用。



    //Class实例可以是哪些结构的说明
    @Test
    public void test4(){
        Class c1= Object.class;
        Class c2= Comparable.class;
        Class c3= String[].class;
        Class c4= int[][].class;
        Class c5= ElementType.class;
        Class c6= Override.class;//注解
        Class c7= int.class;
        Class c8= void.class;
        Class c9= Class.class;


        int[] a= new int[10];
        int[] b= new int[100];
        Class c10= a.getClass();
        Class c11= b.getClass();
// 只要数组元素类型与维度一样，就是同一个Class
        System.out.println(c10 == c11);//true

    }

}
