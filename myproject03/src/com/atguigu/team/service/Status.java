package com.atguigu.team.service;
/*
Status是项目service包下自定义的类，声明三个对象属性，分别表示成员的状态。
FREE-空闲
BUSY-已加入开发团队
VOCATION-正在休假
*/

/**
 * 表示员工的状态，枚举：有限个对象
 * @author:   E-mail:
 * @version:  
 * 创建时间：2020年7月6日	上午8:47:24
 */

//方式一：
public enum Status{
	FREE,BUSY,VOCATION;
}


//方式二：
//public enum Status{
//	FREE ("FREE"),
//	BUSY ("BUSY"),
//	VOCATION ("VOCATION");
//
//	private final String Name;
//
//	private Status(String Name){
//		this.Name = Name;
//	}
//}

//方式三：
//public class Status {
//	private final String NAME;
//
//
//	private Status(String name) {
//		this.NAME = name;
//	}
//
//	//这个类，只创建三个对象，不允许外面造对象了。像单例模式
//	public static final Status FREE = new Status("FREE");
//	public static final Status BUSY = new Status("BUSY");
//	public static final Status VOCATION = new Status("VOCATION");
//
//
//	public String getNAME() {
//		return NAME;
//	}
//
//	@Override
//	public String toString() {
//		return NAME;
//	}
//
//}
