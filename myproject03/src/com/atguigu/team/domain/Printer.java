package com.atguigu.team.domain;

//model 表示机器的型号
//display 表示显示器名称
//type 表示机器的类型
public class Printer implements Equipment {
	private String name;// 机器的型号
	private String type;// 机器的类型

	public Printer() {
		super();
	}

	public Printer(String name, String type) {
		super();
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String getDescription() {
		return name + "(" + type + ")";
	}

}
