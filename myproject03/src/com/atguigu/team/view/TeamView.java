package com.atguigu.team.view;

import com.atguigu.team.domain.Employee;
import com.atguigu.team.domain.Programmer;
import com.atguigu.team.service.NameListService;
import com.atguigu.team.service.TeamException;
import com.atguigu.team.service.TeamService;

public class TeamView {
	private NameListService listSvc = new NameListService();
	private TeamService teamSvc = new TeamService();

	public void enterMainMenu() {
		boolean loopFlag = true;//循环标签loopFlag
		char menu = '0';
		while(loopFlag) {
			
			if(menu != '1') {//是'1'不进去，即反例：不是1就进去哦！
				listAllEmployees();
			}
			
			System.out.print("1-团队列表  2-添加团队成员  3-删除团队成员 4-退出   请选择(1-4)：");
			
			menu = TSUtility.readMenuSelection();
			switch(menu) {
			case '1':
				getTeam();
				break;
			case '2':
				addMember();
				break;
			case '3':
				deleteMember();
				break;
			case '4':
				System.out.print("确认是否退出(Y/N)：");
				char isExit = TSUtility.readConfirmSelection();
				if(isExit == 'Y') {
					loopFlag = false;
				}
				break;
			}
		}
	}
	/**
	 * 显示所有的员工信息
	 * @Description:  
	 * @author:   
	 * @date：2020年7月7日	上午9:14:24
	 */
	private void listAllEmployees() {
//		System.out.println("显示公司所有的员工信息");
		System.out.println("-------------------------------开发团队调度软件--------------------------------\n");
		
		Employee[] employees = listSvc.getAllEmployees();
		
		//判断数组中没有元素的话：1.首先判断该数组对象是否为null		2.在考虑数组中是否有元素
		if(employees == null || employees.length == 0) {
			System.out.println("公司没有中没有任何员工信息！");
		}else {
			System.out.println("ID\t姓名\t年龄\t工资\t职位\t状态\t奖金\t股票\t领用设备");
			
			for(int i = 0; i < employees.length;i++) {
				System.out.println(employees[i]);//自动调用Employee的toString
			}
		}
		
		System.out.println("-------------------------------------------------------------------------------");
		
	}
	/**
	 * 
	 * @Description:  
	 * @author:   
	 * @date：2020年7月7日	上午9:23:14
	 */
	private void getTeam() {
//		System.out.println("查看开发团队情况");
		System.out.println("--------------------团队成员列表---------------------\n");
		
		Programmer[] team = teamSvc.getTeam();
		//判断数组中没有元素的话：1.首先判断该数组对象是否为null		2.在考虑数组中是否有元素
		if(team == null || team.length == 0) {
			System.out.println("开发团队目前没有成员！");
		}else {
			System.out.println("TID/ID\t姓名\t年龄\t工资\t职位\t奖金\t股票\n");
			for(int i = 0;i < team.length;i++) {
				System.out.println(team[i].getDetailsForTeam());//team[i]匹配不上
				
			}
		}
		System.out.println("-----------------------------------------------------");
		
	}
	
	private void addMember() {
		System.out.println("---------------------添加成员---------------------");
		System.out.print("请输入要添加的员工ID：");
		int id = TSUtility.readInt();
		
		try {
			Employee employee = listSvc.getEmployee(id);//返回指定id的员工
			teamSvc.addMember(employee);
			System.out.println("添加成功");
		} catch (TeamException e) {
			System.out.println("添加失败，原因：" + e.getMessage());
		}
		//按回车键继续...
		TSUtility.readReturn();
		
	}
	
	private void deleteMember() {
		System.out.println("---------------------删除成员---------------------");
		System.out.print("请输入要删除员工的TID：");
		int memberId = TSUtility.readInt();
		
		System.out.print("确认是否删除(Y/N)：");
		char isDelete = TSUtility.readConfirmSelection();
		if(isDelete == 'N') {
			return;//结束方法
		}
		
		try {
			teamSvc.removeMember(memberId);
			System.out.println("删除成功");
		} catch (TeamException e) {
			System.out.println("删除失败，原因：" + e.getMessage());
		}
		//回车继续
		TSUtility.readReturn();
		
	}
	
	public static void main(String[] args) {
		TeamView view = new TeamView();
		view.enterMainMenu();
	}

}
