package com.wcg.caoxian.bes.vo.uservo;

import com.wcg.caoxian.sdk.bean.PageBean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("UserSearchInputParamVo")
public class UserSearchInputParamVo {
	
	@ApiModelProperty(value="显示名称")
	private String name;
	
	@ApiModelProperty(value="登录名")
	private String loginName;
	
	@ApiModelProperty(value="手机号")
	private String mobile;
	
	public String toURL(PageBean pageBean){
    	String url = "?1=1";
    	if(this.name != null){
    		url += "&name=" + this.name;
    	}
    	if(this.mobile != null){
    		url += "&mobile=" + this.mobile;
    	}
    	if(this.loginName != null){
    		url += "&loginName=" + this.loginName;
    	}
    	if(pageBean != null){
    		if(pageBean.getNumber() != null){
    			url += "&number=" + pageBean.getNumber();
    		}
    		if(pageBean.getSize() != null){
    			url += "&size=" + pageBean.getSize();
    		}
    	}
    	return url;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
}
