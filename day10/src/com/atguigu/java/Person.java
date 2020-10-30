package com.atguigu.java;

import java.io.Serializable;

/**
 * Person类需要满足如下的要求，方可序列化(要想自定义类是可序列化的，必须满足如下要求！)
 * 1.需要实现接口：Serializable(标识接口，类似标签)
 * 2.需要当前类提供一个全局常量(序列版本号，它是类的唯一标识)：serialVersionUID。
 *   它是为了识别你到底是哪个类，用来表明类的不同版本间的兼容性。
 * 3.除了当前Person类需要实现Serializable接口外，还必须保证其内部所有属性也必须是可序列化的。
 *   (默认情况下，基本数据类型可序列化)
 *
 * 4.序列化机制：
 *      对象序列化机制允许把内存中的Java对象转换成平台无关的二进制流，
 *      从而允许把这种二进制流持久地保存在磁盘上，或通过网络将这种二
 *      进制流传输到另一个网络节点。//当其它程序获取了这种二进制流，
 *      就可以恢复成原来的Java对象。
 *
 *
 *
 * 补充：ObjectOutputStream和ObjectInputStream不能序列化static和transient修饰的成员变量
 *      transient不让序列化这个修饰的属性
 *
 *
 * Serializable标识接口
 *
 * @author 田沛勋
 * @create 2020-07-30 20:08
 */
public class Person implements Serializable {
    public static final long serialVersionUID = 42524854154545L;//序列版本号，它是序列化进入的一个修饰

    private String name;
    private int age;
    private int id;
    private Account acct;


    public Person() {
    }

    public Person(String name, int age, int id, Account acct) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.acct = acct;
    }

    public Person(String name, int age, int id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public Person(String tom, int i) {
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                ", acct=" + acct +
                '}';
    }
}

class Account implements Serializable{
    public static final long serialVersionUID = 4252485414344545L;//序列版本号，它是序列化进入的一个修饰

    private double balance;

    public Account(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                '}';
    }
}
