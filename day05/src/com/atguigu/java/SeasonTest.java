package com.atguigu.java;

/**
 * 一、枚举类的使用（当需要定义一组(对象)常量时，强烈建议使用枚举类实现）
 *
 * 1. 枚举类的理解：当一个类的对象是有限个、确定多个时候，我们称此类为枚举类。
 * 2. 当需要定义一组(对象)常量时，强烈建议使用枚举类实现。(枚举类当中有好几个对象，这个对象相当于就是这个常量)
 * 3. 如果枚举类中只有一个对象，则可以作为单例模式的实现方式。
 *
 * 二、如何定义枚举类
 * 方式一：JDK5.0之前，自定义枚举类
 * 方式二：JDK5.0，可以使用enum关键字定义枚举类
 *
 *
 * 三、Enum类中的常用方法(enum关键字声明的枚举类的父类class java.lang.Enum)
 *values()方法：返回枚举类型的对象数组。该方法可以很方便地遍历所有的枚举值。
 *valueOf(String str)：可以把一个字符串转为对应的枚举类对象。要求字符串必须是枚举类对象的“名字”。
                        如不是，会有运行时异常：IllegalArgumentException。
 *toString()：返回当前枚举类对象常量的名称
 *
 *
 * 四、使用enum关键字定义的枚举类实现接口的情况
 *    情况一：实现接口，在enum枚举类中实现抽象方法
 *    情况二：让枚举类的对象分别实现接口中的抽象方法(体现每个对象的方法体都不一样了)
 *
 *
 *
 *
 * @author 田沛勋
 * @create 2020-07-28 8:58
 */
public class SeasonTest {
    public static void main(String[] args) {
        Season spring = Season.SPRING;
        System.out.println(spring);

    }

}

//自定义枚举类：                   //Season显然是一个枚举类，因为它就有四个对象
class Season{
    //1.声明Season对象的属性:private final修饰
    //Season对象有属性的话，我们声明为private私有的属性：因为造枚举类的几个对象，每个对象都有它的属性。
    //这几个对象定义为常量，因为对象都是常量了，所以对象的属性也不让它变量，常量。

    private final String seasonName;
    private final String seasonDesc;

    //2.私有化类的构造器,并给对象属性赋值           (否则不确定外面造几个对象了，确定了不能再去多造对象了)
    private Season(String seasonName,String seasonDesc){
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    //3.提供当前枚举类的多个对象(即：枚举类型的变量)：使用public static final修饰。
    //  因为不提供该Season类型变量的get/set方法，且是常量。
    public static final Season SPRING = new Season("春天", "春暖花开");
    public static final Season SUMMER = new Season("夏天", "夏日炎炎");
    public static final Season AUTUMN = new Season("秋天", "秋高气爽");
    public static final Season WINTER = new Season("冬天", "冰天雪地");

    //4.其它诉求1：获取枚举类对象的属性
    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

    //4.其它诉求2：提供toString()
    @Override
    public String toString() {
        return "Season{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }
}