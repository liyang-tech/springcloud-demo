package com.wcg.caoxian.sdk.exception;

public class BusinessException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private String code;
	
	private Throwable cause;
	
	public BusinessException(String code, String msg){
		this(code, msg, null);
	}
	
	public BusinessException(String code, String msg, Throwable cause){
		super(msg);
		this.code = code;
		this.cause = cause;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Throwable getCause() {
		return cause;
	}

	public void setCause(Throwable cause) {
		this.cause = cause;
	}
	
}
