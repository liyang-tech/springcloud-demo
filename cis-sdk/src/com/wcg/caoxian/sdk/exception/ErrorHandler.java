package com.wcg.caoxian.sdk.exception;

import com.wcg.caoxian.sdk.config.CodeProperties;

public class ErrorHandler {

	public static void reportError(String code){
		reportError(code, new String[]{});
	}
	
	public static void reportError(String code, String...parms){
		String msg = CodeProperties.getInstance().getValue(code, parms);
		reportError(code, msg, null);
	}
	
	public static void reportError(String code, Throwable cause){
		String msg = CodeProperties.getInstance().getValue(code);
		reportError(code, msg, cause);
	}
	
	public static void reportError(String code, Throwable cause, String...parms){
		String msg = CodeProperties.getInstance().getValue(code, parms);
		reportError(code, msg, cause);
	}
	
	public static void reportError(String code, String msg, Throwable cause){
		throw new BusinessException(code, msg, cause);
	}
	
}
