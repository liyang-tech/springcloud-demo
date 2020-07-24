package com.wcg.caoxian.user.vo;

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
