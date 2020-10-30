package com.atguigu.team.junit;

import org.junit.Test;

import com.atguigu.team.domain.Employee;
import com.atguigu.team.service.NameListService;
import com.atguigu.team.service.TeamException;

/**
 * 
 * @Description:  对NameListService类的测试
 * @author:   E-mail:
 * @version:  v1.0
 * @date：2020年7月6日	下午4:15:01
 */
public class NameListServiceTest {
	@Test
	public void testGetAllEmployees() {
		NameListService service = new NameListService();
		Employee[] allEmployees = service.getAllEmployees();
		for(int i = 0;i < allEmployees.length;i++) {
			System.out.println(allEmployees[i]);//自动调用toString
		}
	}
	@Test
	public void testGetEmployee() {
		NameListService service = new NameListService();
		int id = 1;
		id = 10;
		id = 101;
		
		try {
			Employee employee = service.getEmployee(id);
			System.out.println(employee);
		} catch (TeamException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	
	
}
