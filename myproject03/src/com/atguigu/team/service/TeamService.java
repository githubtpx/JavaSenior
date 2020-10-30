package com.atguigu.team.service;

/*
写程序/功能的思路：也叫步骤
第一：方法的先后顺序。第一考虑：跟其它方法无关联；第二考虑：跟其它方法关联较小...
第二：先去画图。想这个方法(事情)什么条件时候不成功，什么条件可以成功？
	  成功了又需要做什么？然后做这件事情，完成这个功能！
*/
import com.atguigu.team.domain.Architect;
import com.atguigu.team.domain.Designer;
import com.atguigu.team.domain.Employee;
import com.atguigu.team.domain.Programmer;

/**
 * 
 * @Description: 关于开发团队成员的管理：添加、删除等。
 * @author: E-mail:
 * @version:
 * @date：2020年7月6日 下午5:39:06
 */
public class TeamService {
	/*
	 * 说明： counter为静态变量，用来为开发团队新增成员自动生成团队中的唯一ID，即memberId。（提示：应使用增1的方式）
	 * MAX_MEMBER：表示开发团队最大成员数 team数组：用来保存当前团队中的各成员对象 total：记录团队成员的实际人数
	 */

	private static final Employee Programmer = null;
	private static int counter = 1;// 使用静态属性，给memberId自动赋值使用，外边不用私有了
	private final int MAX_MEMBER = 5;// 限制开发团队的人数
	private Programmer[] team = new Programmer[MAX_MEMBER];// 保存开发团队成员
	private int total;// 记录开发团队中世纪的人数

	public TeamService() {
		super();
	}

	/**
	 * 
	 * @Description: 获取开发团队中的所用成员
	 * @author:
	 * @date：2020年7月6日 下午5:53:47
	 * @return
	 */
	public Programmer[] getTeam() {
		// 新建一个数组，复制
		Programmer[] team = new Programmer[total];
		for (int i = 0; i < team.length; i++) {
			team[i] = this.team[i];
		}
		return team;
//		return team;//空指针异常
	}

	/**
	 * 
	 * @Description: 将指定的员工添加到开发团队当中。注意：很多添加不成功的情况！
	 * @author:
	 * @date：2020年7月6日 下午5:59:54
	 * @param e
	 * @throws TeamException
	 */
	public void addMember(Employee e) throws TeamException {
//		成员已满，无法添加
		if (total >= team.length) {
			throw new TeamException("成员已满，无法添加");
		}

//		该成员不是开发人员，无法添加
		if (!(e instanceof Programmer)) {
			throw new TeamException("该成员不是开发人员，无法添加");
		}

//		该员工已在本开发团队中(需要遍历需要好几行代码，写个方法)
		if (isExist(e)) {
			throw new TeamException("该员工已在本开发团队中");
		}
		;

//		该员工已是某团队成员 (状态)
//		该员正在休假，无法添加
		Programmer p = (Programmer) e;// 一定不会出现ClassCastException
//		if ("BUSY".equals(p.getStatus().getNAME())) {// if(p.getStatus().getNAME().equals("BUSY")) {
//			// 确定的字符串，""BUSY"."一定不会出现空指针了。
//			throw new TeamException("该员工已是某团队成员");
//		} else if ("VOCATION".equalsIgnoreCase(p.getStatus().getNAME())) {// 忽略大小写
//			throw new TeamException("该员正在休假，无法添加");
//		};
		switch(p.getStatus()){//byte\short\char\int\String\枚举类对象
			case BUSY:
				throw new TeamException("该员工已是某团队成员");
			case VOCATION:
				throw new TeamException("该员正在休假，无法添加");
		}



//		团队中至多只能有一名架构师
//		团队中至多只能有两名设计师
//		团队中至多只能有三名程序员
		// 第一步：获取team团队当中，已经有几个架构师、设计师、程序员的人数。
		// 第二步：你看自己是什么职位，然后找对应它有几个了，看满不满足。

		// 获取team已有的成员中架构师，设计师，程序员的人数
		int numOfArch = 0, numOfDes = 0, numOfPro = 0;
		for (int i = 0; i < total; i++) {
			if (team[i] instanceof Architect) {
				numOfArch++;
			} else if (team[i] instanceof Designer) {
				numOfDes++;
			} else if (team[i] instanceof Programmer) {// team[i]数组本身Programmer
				numOfPro++;
			}
		}

		// 第二步：你看自己是什么职位，然后找对应它有几个了，看满不满足
		if (p instanceof Architect) {
			if (numOfArch >= 1) {
				throw new TeamException("团队中至多只能有一名架构师");
			}
		} else if (p instanceof Designer) {
			if (numOfDes >= 2) {
				throw new TeamException("团队中至多只能有两名设计师");
			}
		} else {
			if (numOfPro >= 3) {
				throw new TeamException("团队中至多只能有三名程序员");
			}
		}

		// 反例： 语法就不一样。p instanceof Architect成立，第一个numOfArch是true和false，下面都不进去
		// 错误测试：team恰好有两个设计师，无架构师。考虑p是一个架构师。
//		if(p instanceof Architect && numOfArch >= 1) {
//			throw new TeamException("团队中至多只能有一名架构师");
//		}else if(p instanceof Designer && numOfDes >= 2) {
//			throw new TeamException("团队中至多只能有两名设计师");
//		}else if(p instanceof Programmer && numOfPro >= 3) {
//			throw new TeamException("团队中至多只能有三名程序员");
//		}

		// 上面都不满足，成功了又需要做什么？然后做这件事情。先后无所谓!都是地址。
		// 1.将p(或e)添加到现有的team中
		team[total++] = p;
		// 2.teamId赋值 == p的属性赋值
		p.setStatus(Status.BUSY);
		// 3.修改状态
		p.setMemberId(counter++);
	}

	/**
	 * 
	 * @Description: 判断指定的员工是否已经存在于现有的开发团队中
	 * @author:
	 * @date：2020年7月6日 下午6:26:30
	 * @param e
	 * @return
	 */
	private boolean isExist(Employee e) {
		// 拿id判断，也能拿memberId判断，它是Employee强转成Programmer类型的属性
		for (int i = 0; i < total; i++) {
			return team[i].getId() == e.getId();
//			if(team[i].getId() == e.getId()) {
//				return true;
//			}
		}
		return false;

	}

	/**
	 * 
	 * @Description:  从团队中删除指定memberId成员
	 * @author:   
	 * @date：2020年7月6日	下午9:07:45
	 * @param memberId
	 * @throws TeamException 
	 */
	public void removeMember(int memberId) throws TeamException {
		int i = 0;
		for(;i < total;i++) {
			if(team[i].getMemberId() == memberId) {
				//成功了又需要做什么？然后做这件事情
				//1.team数组，将待删除的对象被它后边的对象覆盖。最后一个改为null。同时total减去1
				//2.Programmer的属性status从“BUSY”改为“FREE”
				//3.Programmer的属性memberId可以修改为0，也可不改
				team[i].setStatus(Status.FREE);
				break;
			}
		}
		
		//未找到指定memberId的情况
		if(i == total) {
			throw new TeamException("找不到指定memberId的员工，删除失败");
		}
		
		//后一个元素覆盖前一个元素，实现删除操作。最后一个改为null。同时total减去1。
		for(int j = i + 1;j < total;j++) {//j充当后面角标
			team[ j-1 ] = team[j];
		}
//		for(int j = i; j < total - 1;j++) {
//			team[j] = team[j + 1];
//		}
		
		//写法一：
		team[--total] = null;
		//写法二：
//		team[total - 1] = null;
//		total--;
		
		
		
		
	}
	
	

}
