package com.wcg.caoxian.sdk.util;

import java.util.regex.Pattern;

public class PatternUtil {
	
	/**
	 * 正则表达式，校验字符串中是否包含汉字
	 */
	private static final String REGEX_STRING_CONTANIS_CHINESS = "^[\u4e00-\u9fa5],{0,}$";
	
	/**
	 * 正则表达式，校验字符串中是否是英语
	 */
	private static final String REGEX_STRING_CONTANIS_ENGLISH = "^[a-zA-Z]+$";
	
	/**
	 * 正则表达式，校验手机号
	 */
	private static final String REGEX_MOBILE = "^((13[0-9])|(14[0-9])|(15[0-9])|(16[0-9])|(17[0-9])|(18[0-9]))\\d{8}$";

	/**
	 * 正则表达式，校验字符串中是否包含汉字
	 */
	public static boolean isStringContinsChiness(String parm){
		return Pattern.matches(REGEX_STRING_CONTANIS_CHINESS, parm);
	}
	
	/**
	 * 正则表达式，校验手机号
	 */
	public static boolean isMobile(String parm){
		return Pattern.matches(REGEX_MOBILE, parm);
	}
	
	/**
	 * 正则表达式，校验英文
	 */
	public static boolean isEnglish(String parm){
		return Pattern.matches(REGEX_STRING_CONTANIS_ENGLISH, parm);
	}
	
}
