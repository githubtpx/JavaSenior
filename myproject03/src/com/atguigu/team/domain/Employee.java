package com.atguigu.team.domain;
//注意：开发中，父类中常常提供空参的构造器，否则子类调用父类构造器非默认空参的
public class Employee {
	private int id;
	private String name;
	private int age;
	private double salary;
	public Employee() {
		super();
	}
	public Employee(int id, String name, int age, double salary) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.salary = salary;
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
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	//防止子类再次重写
	public String getDetails() {
		return id + "\t" + name + "\t" + age + "\t" + salary;
	}
	
	@Override
	public String toString() {
		return getDetails();
	}
	
}
