package com.atguigu.exer;

/**
 * 定义一个Employee类。
 * 该类包含：private成员变量name,age,birthday，其中birthday 为MyDate 类的对象；
 * 并为每一个属性定义getter, setter 方法；
 * 并重写toString 方法输出name, age, birthday
 *
 *
 * @author 田沛勋
 * @create 2020-08-01 16:01
 */
public class Employee implements Comparable<Employee> {
    private String name;
    private int age;
    private MyDate birthday;


    public Employee() {
    }

    public Employee(String name, int age, MyDate birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public MyDate getBirthday() {
        return birthday;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBirthday(MyDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                '}';
    }

    //指明Comparable接口泛型时的写法
    @Override
    public int compareTo(Employee o) {
        return this.name.compareTo(o.name);
    }


    //没有指明Comparable接口泛型时的写法
    //按照name从小到大排序
//    @Override
//    public int compareTo(Object o) {
//        if(o instanceof Employee){
//            Employee e = (Employee)o;
//            return this.name.compareTo(e.getName());
//        }
////        return 0;//TreeSet当中不能放相同数据
//        throw new RuntimeException("传入的类型不一致");
//    }


}
