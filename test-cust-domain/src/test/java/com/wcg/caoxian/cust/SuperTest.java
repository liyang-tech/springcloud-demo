package com.wcg.caoxian.cust;

import java.util.Date;

public class SuperTest extends Date {

public static final long serialVersionUID = 1L;
	
	private void test(){
		System.out.println(super.getClass().getName());
	}
	
	public static void main(String[] args) {
		new SuperTest().test();
	}
	
}
