package com.wcg.caoxian.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("UserLoginInputVo")
public class UserLoginInputVo {

	@ApiModelProperty(value="登录名")
	private String loginName;
	
	@ApiModelProperty(value="登录密码")
	private String password;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
