package com.wcg.caoxian.user;

public class AnythingTest {

	static boolean foo(char c){
		System.out.println(c);
		return true;
	}
	
	public static void main(String[] args) {
		int i = 2;
		for(foo('A'); foo('B') && (i < 2); foo('C')){
			i++;
			foo('D');
		}
	}
	
}
