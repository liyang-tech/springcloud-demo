package com.wcg.caoxian.cust;

public class Singleton1 {//恶汉式

	private Singleton1(){};//私有构造函数
	
	private static Singleton1 singleton1 = new Singleton1();//私有静态变量
	
	public static Singleton1 getInstance(){//公共静态构造方法
		return singleton1;
	}
	
}
