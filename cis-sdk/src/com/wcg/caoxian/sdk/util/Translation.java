package com.wcg.caoxian.sdk.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Translation {

	//solr查询的特殊字符转义
	public static String translationSpcilStr(String c){
		StringBuilder st = new StringBuilder();
		for (int i = 0; i < c.length(); i++) {
			char s = c.charAt(i);
			if(s == '\\' || s == '+' || s == '-' || s == '!' || s == '(' || s == ')' 
					|| s == ':' || s == '^' || s == '[' || s == ']' || s == '{' || s == '}' || s == '~'
					|| s == '*' || s == '&' || s == '?' || s == '|' || s == ';' || s == '/' || Character.isWhitespace(s)){
				st.append('\\');
			}
			st.append(s);
		}
		return st.toString();
	}
	
}
