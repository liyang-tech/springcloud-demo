package com.wcg.caoxian.cust;

import java.util.Date;

import org.junit.Test;


public class AnythingTest extends Date {

	public static final long serialVersionUID = 1L;
	
	private void test(){
		System.out.println(super.getClass().getName());
	}
	
	public static void main(String[] args) {
		
	}
	
	/*static boolean foo(char c){
		System.out.println(c);
		return true;
	}
	
	public static void main(String[] args) {
		int i = 0;
		for(foo('A'); foo('B') && (i < 2); foo('C')){
			i++;
			foo('D');
		}
	}*/
	/*@Test
	public void method() {
		try {
			System.out.println("jirudao");
		} catch (Exception e) {
			System.out.println("yichang");
		}
		finally {
			
		}
	}*/
	
}
