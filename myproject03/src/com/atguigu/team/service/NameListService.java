package com.atguigu.team.service;

import com.atguigu.team.domain.Architect;
import com.atguigu.team.domain.Designer;
import com.atguigu.team.domain.Employee;
import com.atguigu.team.domain.Equipment;
import com.atguigu.team.domain.NoteBook;
import com.atguigu.team.domain.PC;
import com.atguigu.team.domain.Printer;
import com.atguigu.team.domain.Programmer;
//导入静态结构
import static com.atguigu.team.service.Data.*;


//类型不多的分支结构，优先考虑switch
//Integer类型返回值可以使用 == id(int id)。IntegerCache缓存的内部类可以-128到127，也撞对对象判断其实需要使用.equals()方法。
/**
* @Description:  负责将Data中的数据封装到Employee[]数组中，同时提供相关操作Employee[]的方法。
* @author:   E-mail:
* @version:  v1.0
* @date：2020年7月6日	上午9:43:30
*/
//通常在类的上面加的是：文档注释
public class NameListService {

	private Employee[] employees;

	/**
	 * 构造器中，给属性，employee数组及数组元素进行初始化
	 */
	public NameListService() {
//		1.根据项目提供的Data类构建相应大小的employees数组
//		2.再根据Data类中的数据构建不同的对象，包括Employee、Programmer、Designer和Architect对象，以及相关联的Equipment子类的对象
//		3.将对象存于数组中
		
		//数组初始化
		employees = new Employee[EMPLOYEES.length];//Data.EMPLOYEES.length，Data中所有的静态结构EMPLOYEES可以导入的

		//数组元素初始化
		for (int i = 0;i < EMPLOYEES.length; i++) {
			//获取员工的类型(根据此，造不同类型的对象)
			int type = Integer.parseInt(EMPLOYEES[i][0]);
			
			//获取Employee的4个基本信息
			int id = Integer.parseInt(EMPLOYEES[i][1]);
			String name = EMPLOYEES[i][2];
			int age = Integer.parseInt(EMPLOYEES[i][3]);
			double salary = Double.parseDouble(EMPLOYEES[i][4]);
			
			//只声明，当你有资格领取设备的时候才去createEquitment
			Equipment equipment;
//			Equipment equipment = createEquitment(i);直接放这不好，第0个员工没有设备，会报错在调用方法时候
			double bonus;
			int stock;
			
			switch(type) {
				//new对象的属性值从数据库中取，即该EMPLOYEES数组后边位置去取
				case EMPLOYEE: //10
					employees[i] = new Employee(id, name, age, salary);
					break;
				case PROGRAMMER:
					//一行代码搞不定这个第i个员工employees[i]对应的Equipment设备对象，封装在方法中。
					//找第i个员工的设备
					equipment = createEquitment(i);
					employees[i] = new Programmer(id, name, age, salary, equipment);
					break;
				case DESIGNER:
//					Equipment equipment = createEquitment(i);变量重名
					equipment = createEquitment(i);
					bonus = Double.parseDouble(EMPLOYEES[i][5]);
					employees[i] = new Designer(id, name, age, salary, equipment, bonus);
					break;
				case ARCHITECT:
					equipment = createEquitment(i);
					bonus = Double.parseDouble(EMPLOYEES[i][5]);
					stock = Integer.parseInt(EMPLOYEES[i][6]);
					employees[i] = new Architect(id, name, age, salary, equipment, bonus,stock);
					break;
			}
		}
	}

	/**
	 * 
	 * @Description: 获取指定index上的员工的设备对象
	 * @author:
	 * @date：2020年7月6日 下午2:48:35
	 * @param i
	 * @return
	 */
	private Equipment createEquitment(int index) {
		// 1.如下的EQUIPMENTS数组与上面的EMPLOYEES数组元素一一对应
		// PC :21, model, display
		// NoteBook:22, model, price
		// Printer :23, name, type
		
		int key = Integer.parseInt(EQUIPMENTS[index][0]);
		//modelOrName上面定义的变量，下面swtich-case可以共用
		String modelOrName = EQUIPMENTS[index][1];
		
		switch(key) {
			case PC://21
				String display = EQUIPMENTS[index][2];
				return new PC(modelOrName, display);
			case NOTEBOOK://22
				double price = Double.parseDouble(EQUIPMENTS[index][2]);
				return new NoteBook(modelOrName, price);
			case PRINTER://23
				String type = EQUIPMENTS[index][2];
				return new Printer(modelOrName, type);//或EQUIPMENTS[index][2]
		}
		
		return null;
	}

	/**
	 * 
	 * @Description:  获取当前所有员工，相当于是当前NameListService类，私有属性employees的get方法
	 * @author:   
	 * @date：2020年7月6日	下午3:42:26
	 * @return	包含所有员工对象的数组
	 */
	public Employee[] getAllEmployees() {
		return employees;
	}

	/**
	 * 
	 * @Description:  获取指定ID的员工对象employee
	 * @author:   
	 * @date：2020年7月6日	下午3:45:07
	 * @param id
	 * @return 
	 * @throws TeamException 整体调用的时候再try-catch-finally
	 */
	public Employee getEmployee(int id) throws TeamException {
		for(int i = 0;i < employees.length;i++) {
			//Integer类型返回值使用 == int id。IntegerCache缓存的内部类可以-128到127，也撞对对象判断其实需要使用.equals()方法。
			if(employees[i].getId() == id) {
				return employees[i];
			}
		}
		
		
		//都没有找到抛出一个自定义异常对象
		throw new TeamException("找不到指定的员工");
	}
}
