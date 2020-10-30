package com.atguigu.team.domain;
/*
说明：
memberId 用来记录成员加入开发团队后在团队中的ID
Status是项目service包下自定义的类，声明三个对象属性，分别表示成员的状态。
FREE-空闲
BUSY-已加入开发团队
VOCATION-正在休假
equipment 表示该成员领用的设备
*/

import com.atguigu.team.service.Status;

public class Programmer extends Employee {
	private int memberId;// 开发团队中的id
	private Status status = Status.FREE;
	private Equipment equipment;

	public Programmer() {
		super();
	}

	public Programmer(int id, String name, int age, double salary, Equipment equipment) {
		super(id, name, age, salary);
		this.equipment = equipment;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

// 3	李彦宏	23	7000.0	程序员	FREE			戴尔(NEC17寸)	
//注意凡是对象，调用它的toString()默认地址值。getDescription()类似toString()方法的作用
	@Override
	public String toString() {
		return getDetails() + "\t程序员\t" + status + "\t\t\t" + equipment.getDescription();
	}

	public String getTeamBaseDetail() {
		return memberId + "/" + getId() + "\t" + getName() + "\t" + getAge() + "\t" + 
				   getSalary();
	}
	
//	 2/3	李彦宏	23	7000.0	程序员	
	public String getDetailsForTeam() {
		return  getTeamBaseDetail() + "\t程序员";
	}

}
