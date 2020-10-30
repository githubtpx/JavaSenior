package com.atguigu.java3;

import com.atguigu.java2.Employee;
import com.atguigu.java2.EmployeeData;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream的终止操作：
 *
 * @author 田沛勋
 * @create 2020-08-23 15:05
 */
public class StramAPITest2 {

    //1. 匹配与查找
    @Test
    public void test1(){
//        allMatch(Predicate p) ———— 检查是否匹配所有元素
//        练习：是否所有的员工的年龄都大于18
        List<Employee> employees = EmployeeData.getEmployees();
        boolean allMatch = employees.stream().allMatch(e -> e.getAge() > 18);
        System.out.println(allMatch);

//        anyMatch(Predicate p) ———— 检查是否至少匹配一个元素
//        练习：是否存在员工的工资大于10000
        boolean anyMatch = employees.stream().anyMatch(e -> e.getSalary() > 10000);
        System.out.println(anyMatch);


//        noneMatch(Predicate p) ———— 检查是否没有匹配所有元素
//        练习：是否存在员工姓“雷”
        boolean noneMatch = employees.stream().noneMatch(e -> e.getName().startsWith("雷"));
        System.out.println(noneMatch);

//        findFirst() ———— 返回第一个元素
        Optional<Employee> first = employees.stream().sorted((e1, e2) -> {

            int ageVal = Integer.compare(e1.getAge(), e2.getAge());
            if (ageVal != 0) {
                return ageVal;
            } else {
                return -Double.compare(e1.getSalary(), e2.getSalary());
            }

        }).findFirst();
        System.out.println(first);

//        findAny() ———— 返回当前流中的任意元素
        Optional<Employee> any = employees.parallelStream().findAny();
        System.out.println(any);

    }

    @Test
    public void test2(){
//        count() ———— 返回流中元素总数
        List<Employee> employees = EmployeeData.getEmployees();
        long count = employees.stream().filter(e -> e.getSalary() > 5000).count();
        System.out.println(count);


//        max(Comparator c) ———— 返回流中最大值(前提能比较)
//        练习：返回最高的工资  -> 首先map拿到employees中salary属性构成的Stream结果
        Optional<Double> maxSalary = employees.stream().map(e -> e.getSalary()).max(Double::compare);//类 ：： 静态方法
//        employees.stream().map(e -> e.getSalary()).max((e1,e2) -> Double.compare(e1, e2));
        System.out.println(maxSalary);

//        min(Comparator c) ———— 返回流中最小值
//        练习：返回最低工资的员工
        Optional<Employee> employee = employees.stream().min((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(employee);
        System.out.println();

//        forEach(Consumerc) ———— 内部迭代(使用Collection 接口需要用户去做迭代，称为外部迭代。相反，Stream API 使用内部迭代——它帮你把迭代做了)
        employees.stream().forEach(System.out :: println);

        //使用集合的遍历操作：集合的内部迭代；  iterator属于集合的外部迭代
        employees.forEach(System.out::println);


    }



    //2. 归约 reduce
    @Test
    public void test3(){
//        reduce(T iden, BinaryOperator b) —— 可以将流中元素反复结合起来，得到一个值。返回T
        //练习1：计算1-10的自然数的和
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer sum = integers.stream().reduce(0, Integer::sum);//extends BiFunction<T,T,T>
        System.out.println(sum);


//        reduce(BinaryOperator b) —— 可以将流中元素反复结合起来，得到一个值。返回Optional<T>
        //练习2：计算公司所有员工工资总和
        List<Employee> employees = EmployeeData.getEmployees();
        Stream<Double> salaryStream = employees.stream().map(Employee::getSalary);
//        Optional<Double> sumSalary = salaryStream.reduce(Double::sum);

        Optional<Double> sumSalary = salaryStream.reduce((d1,d2) -> d1 + d2);
        System.out.println(sumSalary.get());

//        备注：map 和reduce 的连接通常称为map-reduce 模式，因Google 用它来进行网络搜索而出名。
        //把Stream中的每一个，先做map映射：employees.stream.map(Function f)得到每个员工的salary
        //然后再做reduce归约：然后把每个员工的salary求总和，求出所有员工的总工资

    }

    //3. 收集
    @Test
    public void test4(){
//        collect(Collector c) —— 将流转换为其他形式。接收一个Collector(收集器)接口的实现，用于给Stream中元素做汇总的方法
        //Collectors工具类
        //练习1：查找工资大于6000的员工，结果返回为一个List或Set

        List<Employee> employees = EmployeeData.getEmployees();
        List<Employee> employeeList = employees.stream().filter(e -> e.getSalary() > 6000).collect(Collectors.toList());

        employeeList.forEach(System.out :: println);
        System.out.println();
        Set<Employee> employeeSet = employees.stream().filter(e -> e.getSalary() > 6000).collect(Collectors.toSet());

        employeeSet.forEach(System.out :: println);

    }


}
