package com.atguigu.java2;

import org.junit.Test;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 一、构造器引用
 *       和方法引用类似，函数式接口的抽象方法的形参列表和构造器的形参列表一致。
 *       抽象方法的返回值类型即为构造器所属的类的类型
 *
 *
 * 二、数组引用
 *      大家可以把数组看作是一个特殊的类，则写法与构造器引用一致。
 *
 * Created by shkstart
 */
public class ConstructorRefTest {
	//构造器引用
    //Supplier中的T get()
    //Employee的空参构造器：Employee()
    @Test
    public void test1(){

        //供给型函数式接口
        Supplier<Employee> sup = new Supplier<Employee>() {
            @Override
            public Employee get() {
                return new Employee();
            }
        };
        System.out.println(sup.get());

        System.out.println("*****************************");
        Supplier<Employee> sup1 = () -> new Employee();
        System.out.println(sup1.get());


        System.out.println("*****************************");
        Supplier<Employee> sup2 = Employee::new;
        System.out.println(sup2.get());

	}

	//Function中的R apply(T t)
    //Employee中的构造器：Employee(int id)
    @Test
    public void test2(){
        Function<Integer,Employee> func = Employee::new;
        Employee employee = func.apply(1001);
        System.out.println(employee);

        System.out.println("*****************************");
        Function<Integer,Employee> func1 = Employee :: new;
        Employee employee1 = func1.apply(1002);
        System.out.println(employee1);

    }

	//BiFunction中的R apply(T t,U u)
    //Employee中的构造器：Employee(int id,String name)
    @Test
    public void test3(){
        BiFunction<Integer,String,Employee> bifunc = (id,name) -> new Employee(id,name);
        Employee employee = bifunc.apply(1001, "Tom");
        System.out.println(employee);

        System.out.println("***************************************");
        BiFunction<Integer,String,Employee> bifunc1 = Employee::new;
        Employee employee1 = bifunc1.apply(1002, "Aimy");
        System.out.println(employee1);

    }




	//数组引用(把数组看成类类型)
    //Function中的R apply(T t)
    //String中的new String[Integer length]; 假设为数组构造器String[Integer length]
    @Test
    public void test4(){
        Function<Integer,String[]> func1 = length -> new String[length];
        String[] arr1 = func1.apply(5);
        System.out.println(Arrays.toString(arr1));

        System.out.println("***************************************");
        Function<Integer,String[]> func2 = String[] :: new;
        String[] arr2 = func2.apply(10);
        System.out.println(Arrays.toString(arr2));

	}
}
