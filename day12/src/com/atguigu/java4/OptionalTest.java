package com.atguigu.java4;

import org.junit.Test;

import java.util.Optional;

/**
 * Optional类：为了在程序中避免出现空指针异常而创建的。
 *
 * 常用方法：
 *     ofNullable(T t)
 *     orElse(T t)
 *
 *
 * @author 田沛勋
 * @create 2020-08-23 17:52
 */
public class OptionalTest {


/*
创建Optional类对象的方法：
Optional.of(T t) : 创建一个Optional 实例，t必须非空；
Optional.empty() : 创建一个空的Optional 实例
Optional.ofNullable(T t)：t可以为null
*/
    @Test
    public void test1(){
        Girl girl = new Girl();
        girl = null;
        //of(T t):必须保证t是非空的
        Optional<Girl> optionalGirl = Optional.of(girl);

    }

    @Test
    public void test2() {
        Girl girl = new Girl();
//        girl = null;
        //ofNullable(T t): t可以为null
        Optional<Girl> optionalGirl = Optional.ofNullable(girl);
        System.out.println(optionalGirl);

        //orElse(T t1):如果当前的Optional内部封装的t是非空的，则返回内部的t
        //如果内部的t是空的，则返回orElse()方法中的参数t1
        Girl girl1 = optionalGirl.orElse(new Girl("冯程程"));
        System.out.println(girl1);

    }


    public String getGrilName(Boy boy){
        return boy.getGirl().getName();
    }

    @Test
    public void test3(){
        Boy boy = new Boy();
//        boy = null;
        String grilName = getGrilName(boy);
        System.out.println(grilName);

    }


    //优化以后的getGrilName():
    public String getGrilName1(Boy boy){
        if(boy != null){
            Girl girl = boy.getGirl();
            if(girl != null){
                return girl.getName();
            }
        }
        return null;

    }

    @Test
    public void test4(){
        Boy boy = new Boy();
//        boy = null;
        String grilName = getGrilName1(boy);
        System.out.println(grilName);

    }



    //使用Optional类，优化以后的getGrilName():
    public String getGrilName2(Boy boy){
        Optional<Boy> boyOptional = Optional.ofNullable(boy);
        //取得boyOptional容器中的数据
        //此时的boy1一定非空
        Boy boy1 = boyOptional.orElse(new Boy(new Girl("爱琴海")));

        Girl girl = boy1.getGirl();

        Optional<Girl> girlOptional = Optional.ofNullable(girl);
        //取得girlOptional容器中的数据
        //此时的girl1一定非空
        Girl girl2 = girlOptional.orElse(new Girl("古力娜扎"));

        return girl2.getName();

    }

    @Test
    public void test5(){
        Boy boy = null;
        boy = new Boy();
        boy = new Boy(new Girl("热巴"));
        String grilName = getGrilName2(boy);
        System.out.println(grilName);

    }










/*
判断Optional容器中是否包含对象：
boolean isPresent() : 判断是否包含对象
void ifPresent(Consumer<? super T> consumer) ：如果有值，就执行Consumer接口的实现代码，并且该值会作为参数传给它。
*/





/*
获取Optional容器的对象：
T get(): 如果调用对象包含值，返回该值，否则抛异常
T orElse(T other) ：如果有值则将其返回，否则返回指定的other对象。
T orElseGet(Supplier<? extends T> other) ：如果有值则将其返回，否则返回由Supplier接口实现提供的对象。
T orElseThrow(Supplier<? extends X> exceptionSupplier) ：如果有值则将其返回，否则抛出由Supplier接口实现提供的异常。
*/




}
