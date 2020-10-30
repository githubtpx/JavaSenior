package com.atguigu.team.domain;

public class Designer extends Programmer {

	private double bonus;// 奖金

	public Designer() {
		super();
	}

	public Designer(int id, String name, int age, double salary, Equipment equipment, double bonus) {
		super(id, name, age, salary, equipment);
		this.bonus = bonus;
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

// 5	雷军	28	10000.0	设计师	FREE	5000.0		激光(佳能 2900)
//调用父类的父类的toString方法：想招
	@Override
	public String toString() {
		// 错误的return super.toString(),不是间接父类，间接父类使用唯一的继承下来的方法;
		return getDetails() + "\t设计师\t" + getStatus() + "\t" + bonus + "\t\t" + getEquipment().getDescription();
	}
	
	
//	 3/5	雷军	28	10000.0	设计师	5000.0
	public String getDetailsForTeam() {
		return getTeamBaseDetail() + "\t设计师" + "\t" + getBonus();
	}
	
	
	
	
}
