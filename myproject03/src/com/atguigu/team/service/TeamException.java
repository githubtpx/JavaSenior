package com.atguigu.team.service;

//自定义异常类三步骤：
/* 1.让它继承于现有的异常体系:Exception(编译报错需要异常处理)/RuntimeException
 * 2.异常类的唯一标识：全局常量的识别类的序列号
 * 3.提供构造器
 * 
 */


/**
 * 
 * @Description:  自定义异常类
 * @author:   E-mail:
 * @version:  
 * @date：2020年7月6日	下午4:06:31
 */
public class TeamException extends Exception{
	static final long serialVersionUID = -3387516993129948L;
	
	public TeamException() {
		super();
	}
	
	public TeamException(String msg) {
		super(msg);
	}
	
}
