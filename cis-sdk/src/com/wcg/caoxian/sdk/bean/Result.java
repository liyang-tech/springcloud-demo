package com.wcg.caoxian.sdk.bean;

import com.wcg.caoxian.sdk.config.ApplicationConfig;
import com.wcg.caoxian.sdk.config.CodeProperties;

public class Result<T> {

	private String code;
	
	private String msg;
	
	private PageBean page;
	
	private T data;
	
	private String throwable;
	

	public Result() {
	}

	public Result(String code){
		this(code, CodeProperties.getInstance().getValue(code));
	}
	
	public Result(String code, String msg){
		this(code, msg, null);
	}
	
	public Result(String code, String msg, T data){
		this(code, msg, data, null);
	}
	
	public Result(PageBean page, String code, String msg, T data){
		super();
		this.page = page;
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	public Result(String code, String msg, T data, Throwable throwable){
		this.code = code;
		this.msg = msg;
		this.data = data;
		if("true".equals(ApplicationConfig.getInstance().hasThrowable())){
			this.throwable = throwable.getMessage();
		}
	}
	
	public Result(String code, T data){
		this(code, CodeProperties.getInstance().getValue(code), data, null);
	}
	
	public Result(String code, Throwable throwable){
		this(code, CodeProperties.getInstance().getValue(code), null, throwable);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public PageBean getPage() {
		return page;
	}

	public void setPage(PageBean page) {
		this.page = page;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getThrowable() {
		return throwable;
	}

	public void setThrowable(String throwable) {
		this.throwable = throwable;
	}
	
}
