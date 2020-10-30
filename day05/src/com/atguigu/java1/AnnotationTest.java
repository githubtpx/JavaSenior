package com.atguigu.java1;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Date;

/**
 * 注解的使用(注解：它是对其修饰的结构，解释说明的注解)
 *
  1. 理解Annotation注解：
  ①  jdk5.0 新增的功能，, Java 增加了对元数据MetaData的支持,也就是Annotation

  ②  Annotation 其实就是代码里的特殊标记, 这些标记可以在编译, 类加载, 运行时被读取,并执行相应的处理。
     通过使用Annotation, 程序员可以在不改变原有逻辑的情况下, 在源文件中嵌入一些补充信息。

         *    Annotation 可以像修饰符一样被使用, 可用于修饰包,类, 构造器, 方法, 成员变量, 参数, 局部变量
         *    的声明, 这些信息被保存在Annotation 的“name=value” 对中。

  ③  在JavaSE中，注解的使用目的比较简单，例如标记过时的功能，忽略警告等。在JavaEE/Android中注解
     占据了更重要的角色，例如用来配置应用程序的任何切面，代替JavaEE旧版中所遗留的繁冗代码和XML配置等。

         *    未来的开发模式都是基于注解的，JPA是基于注解的，Spring2.5以上都是基于注解的，Hibernate3.x以后也
         *    是基于注解的，现在的Struts2有一部分也是基于注解的了，注解是一种趋势，一定程度上可以说：
         *    框架 = 注解 + 反射 + 设计模式。

 2.Annotation使用示例
    示例一：生成文档相关的注解
    示例二：在编译时进行格式检查 (JDK内置的三个基本注解)
             @Override: 限定重写父类方法 , 该注解只能用于方法(只有方法才能叫做@Override)
             @Deprecated : 用于表示所修饰的元素类 , 方法等结构已过时。通常是因为所修饰的结构危险或存在更好的选择
             @SuppressWarnings : 抑制编译器警告(把编译器的警告抑制住了)


    示例三：跟踪代码依赖性，实现替代配置文件功能

 3.如何自定义注解：参照抑制编译器警告@SuppressWarnings定义
   ① 注解声明为：@interface
   ② 内部定义成员变量，一个成员变量通常使用value表示
   ③ 可以指定成员变量的默认值，使用default定义
   ④ 如果自定义注解没有成员，表明是一个标识，标记作用(标记的注解)

   如果注解有成员变量，在使用注解时候，需要指明成员的值（没有默认值的前提下）

   自定义注解   必须配上   注解的信息处理流程(使用反射)才有意义！
   自定义注解通常都会指明两个元注解：Retention指明你注解的生命周期、Target指明你注解能够修饰哪些结构

 4. jdk 提供的4种元注解(元注解：修饰其它注解的注解称为元注解；
             数据库中结果集的元数据：对现有数据的修饰的数据称为元数据。如String name = "atguigu"，其中String和name是对数据的修饰称为元数据)

    元注解：对现有的注解，进行解释说明的"注解"。

     Retention：n. 保留、持
             指定所修饰的Annotation的生命周期：SOURCE、CLASS(默认行为)、RUNTIME(为RetentionPolicy枚举类中的三个对象)
             只有声明为RUNTIME生命周期的注解，才能通过反射获取。通过反射获取注解
     Target：
             用于指定被修饰的Annotation，能用于修饰哪些程序元素
     ***************出现的频率较低****************
     Documented：
             表示所修饰的注解在被javadoc解析时，保留下来被修饰的注解。(默认不保留注解)
     Inherited：v.继承
             被它修饰的Annotation，将具有继承性。
             如果某个类使用了被@Inherited修饰的Annotation, 则其子类将自动具有该注解。

 5.通过反射获取注解信息 ---到反射内容时系统讲解

 6. jdk 8 中注解的新特性：可重复注解、类型注解
    6.1 可重复注解：
            ① 在MyAnnotation上声明一个元注解@Repeatable，成员值为MyAnnotations.class（就是它让这两个注解关联）
            ② MyAnnotation的Target和Retention等元注解(和运行元注解@Inherited)，与MyAnnotations相同一致
            ③ 接着就可以使用重复注解

    6.2 类型注解
         ElementType.TYPE_PARAMETER表示该注解能写在类型变量的声明语句中（如：泛型声明）。
         ElementType.TYPE_USE表示该注解能写在使用类型的任何语句中（只要是类型的地方都可以使用该注解）


注意：任何注解修饰的结构，通过反射拿到它的注解信息(看看你注解的值是多少？看看你到底想干什么？去完成相关的功能)
      把注解要做的事情，通过反射去获取，获取的功能封装起来了。你只需要声明好以后，直接调用更加便捷！


 *
 * @author 田沛勋
 * @create 2020-07-29 15:37
 */
public class AnnotationTest {
    public static void main(String[] args) {
        Person p = new Student();
        p.walk();

        Date date = new Date(2020, 10, 20);
        System.out.println(date);

        @SuppressWarnings("unused")
        int num = 10;//从灰色变为白色，使用抑制编译器的警告注解
//        System.out.println(num);

        @SuppressWarnings({ "rawtypes", "unused" })//{ "rawtypes", "unused" }是SuppressWarnings成员变量
        //“rawtypes”关于泛型的警告，声明ArrayList有泛型，实例化没有用泛型，提示一个泛型的警告
        ArrayList list = new ArrayList();

    }

    @Test
    /**
     * 获取Student类的注解信息：通过反射代码获取注解信息
     */
    public void testGetAnnotation(){

        Class clazz = Student.class;
        Annotation[] annotations = clazz.getAnnotations();
        for (int i = 0; i < annotations.length; i++) {
            System.out.println(annotations[i]);//@com.atguigu.java1.MyAnnotation(value=hello)
        }

    }

}

//@MyAnnotation(value = "hello")//该注解它有一个成员变量，注解/自定义注解的使用，就像类的实例一样
//@MyAnnotation("hi")
//@MyAnnotation
//@MyAnnotation()

//jdk 8 之前写法：
//@MyAnnotations({@MyAnnotation(value = "hello"),@MyAnnotation("hi")})
//jdk 8 之后写法：可重复的注解
@MyAnnotation(value = "hello")
@MyAnnotation(value = "hi")
class Person{
    private String name;
    private int age;

    public Person() {
    }

    @MyAnnotation
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @MyAnnotation
    public void walk(){
        System.out.println("人走路");
    }

    public void eat(){
        System.out.println("人吃饭");
    }
}

interface Info{
    void show();
}


class Student extends Person implements Info{

    //不加@Override限定重写父类方法，此时不会在在编译时进行格式检查/检验，你会有不是重写父类的可能性。
    //因为我没加Override没有在编译时候校验，你以为你这个方法是重写父类的，但实际调用的时候有可能出现问题。
    //如果重写父类的方法中加入@Override方法，它强制的会在编译的时候去校验这个方法是不是重写的，如果不是报错。良好的提示
    @Override
    public void walk() {
        System.out.println("学生走路");
    }

    @Override
    public void show() {

    }
}


class Generic<@MyAnnotation T>{
    //泛型也可通过反射获取它的注解。使用注解修饰泛型的类型T。
    //具体希望它具有什么样的特点，功能展示。我们通过反射得到你的注解，再去做什么操作

    public void show() throws @MyAnnotation RuntimeException{

        ArrayList<@MyAnnotation  String> list = new ArrayList<>();

        int num  = (@MyAnnotation  int) 10L;
    }


}







