package com.atguigu.team.service;

import com.atguigu.team.domain.Employee;

public class IsExistTest {
	private int[] arr = new int[] {1,3,4,5,6};
	public static void main(String[] args) {
		IsExistTest m1 = new IsExistTest();
		boolean exist = m1.isExist(6);
		System.out.println(exist);
		
	}
	
	
	private boolean isExist(int e) {
		// 拿id判断，也能拿memberId判断，它是Employee强转成Programmer类型的属性
		for (int i = 0; i < arr.length; i++) {
//			return arr[i] == e;
			if(arr[i] == e) {
				return true;
			}
		}
		return false;

	}
}


