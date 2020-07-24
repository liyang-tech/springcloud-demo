package com.wcg.caoxian.sdk.util;

import java.util.Random;

public class GenerateRandomNum {

	public static final String generateRandomNum(){
		StringBuffer randomCode = new StringBuffer();
		Random random = new Random();
		int length = 6;
		String base = "0123456789";
		int size = base.length();
		for (int i = 0; i < length; i++) {
			int start = random.nextInt(size);
			String Num = base.substring(start, start+1);
			randomCode.append(Num);
		}
		return randomCode.toString();
	}
	
	public static void main(String[] args) {
		String aaa = generateRandomNum();
		System.out.println(aaa);
	}
	
}
