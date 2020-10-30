package com.atguigu.java1;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

/**
 * 自定义注解：
 * 该注解它有一个成员变量，注解/自定义注解的使用，就像类的实例一样
 *
 * @author 田沛勋
 * @create 2020-07-30 7:51
 */
@Inherited
@Repeatable(MyAnnotations.class)//可重复的注解
@Target({TYPE,FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE,TYPE_PARAMETER,TYPE_USE})//干掉TYPE：Class, interface (including annotation type), or enum declaration
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {

    //注解的成员变量设置默认值
    String value() default "hello";
}
