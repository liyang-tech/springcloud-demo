package com.wcg.caoxian.cust;

public class Singleton2 {//懒汉式

	private Singleton2(){};//私有构造函数
	
	private static Singleton2 singleton2 = null;//私有静态变量
	
	public static Singleton2 getInstance(){//公共静态构造方法
		if(singleton2 == null){
			singleton2 = new Singleton2();
		}
		return singleton2;
	}
	
}
