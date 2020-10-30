package com.atguigu.java3;

import com.atguigu.java2.Employee;
import com.atguigu.java2.EmployeeData;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 测试Stream的中间操作
 *
 *
 * @author 田沛勋
 * @create 2020-08-23 9:48
 */
public class StreamAPITest1 {

    //1、筛选与切片
    @Test
    public void test1(){
        List<Employee> list = EmployeeData.getEmployees();


//        filter(Predicatep) —— 接收Lambda，从流中排除某些元素
        Stream<Employee> stream = list.stream();
        //练习：查询员工表中薪资大于7000的员工信息
        stream.filter(e -> e.getSalary() > 7000).forEach(System.out :: println);
        System.out.println();



//        limit(long maxSize) —— 截断流，使其元素不超过给定数量(查询表中前三条数据)
        list.stream().limit(3).forEach(System.out :: println);
        System.out.println();



//        skip(long n) —— 跳过元素，返回一个扔掉了前n个元素的流。若流中元素不足n个，则返回一个空流。与limit(n)互补
        list.stream().skip(3).forEach(System.out :: println);
        System.out.println();



//        distinct() —— 筛选，通过流所生成元素的hashCode() 和equals()去除重复元素。(类似Set中判断重复与否的标准)

        list.add(new Employee(1010,"刘强东",40,8000));
        list.add(new Employee(1010,"刘强东",40,8000));
        list.add(new Employee(1010,"刘强东",40,8000));
        list.add(new Employee(1010,"刘强东",40,8000));
        list.add(new Employee(1010,"刘强东",40,8000));
        list.add(new Employee(1010,"刘强东",41,8000));

//        System.out.println(list);

        list.stream().distinct().forEach(System.out::println);

    }

    //2、映 射(大数据的Map操作)
    @Test
    public void test2(){

//        map(Function f) —— 接收一个函数作为参数，将元素转换成其他形式或提取信息，该函数会被应用到每个元素上，并将其映射成一个新的元素。
        List<String> list = Arrays.asList("aa", "bb", "cc", "dd", "ee");
        list.stream().map(str -> str.toUpperCase()).forEach(System.out::println);


//        练习1：获取员工姓名长度大于3的员工的姓名。
        List<Employee> list1 = EmployeeData.getEmployees();
//        list1.stream().map(e -> e.getName()).forEach(System.out :: println);
        Stream<String> namesStream = list1.stream().map(Employee::getName);
        namesStream.filter(s -> s.length() >3).forEach(System.out :: println);
        System.out.println();


        //练习2：
        Stream<Stream<Character>> streamStream = list.stream().map(StreamAPITest1::fromStringToStream);//方法引用
        streamStream.forEach(s -> {
            s.forEach(System.out::println);
        });
        System.out.println();


//        flaMap(Function f)	—— 接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流。
        //针对于集合中套集合，遍历其中的元素使用flatMap()操作
        Stream<Character> characterStream = list.stream().flatMap(StreamAPITest1::fromStringToStream);
        characterStream.forEach(System.out::println);

    }


    //将字符串中的多个字符构成的集合转换为对应的Stream的实例
    public static Stream<Character> fromStringToStream(String str){
        ArrayList<Character> list = new ArrayList<>();
        for(Character c:str.toCharArray()){
            list.add(c);
        }
        return list.stream();
    }


    @Test
    public void test4(){
        ArrayList list1 = new ArrayList();
        list1.add(1);
        list1.add(2);
        list1.add(3);

        ArrayList list2 = new ArrayList();
        list2.add(4);
        list2.add(5);
        list2.add(6);

//        list1.add(list2);
        list1.addAll(list2);
        System.out.println(list1);

    }


    //3、排序（对象排序：一个是comparable一个是comparator）
    @Test
    public void test3(){
//        sorted() —— 产生一个新流，其中按自然顺序排序
        List<Integer> list = Arrays.asList(12, 34, 56, 12, 1, 0, -29);
        list.stream().sorted().forEach(System.out :: println);

        //抛异常，原因Employee类没有实现Comparable接口
//        List<Employee> employees = EmployeeData.getEmployees();
//        employees.stream().sorted().forEach(System.out::println);


//        sorted(Comparator com) —— 产生一个新流，其中按比较器顺序排序。Comparator函数型接口
        List<Employee> employees = EmployeeData.getEmployees();
        employees.stream().sorted((e1,e2) ->{

            int ageVal = Integer.compare(e1.getAge(), e2.getAge());
            if(ageVal != 0){
                return ageVal;
            }else{
                return -Double.compare(e1.getSalary(), e2.getSalary());
            }

        }).forEach(System.out::println);

    }

}
