package com.wcg.caoxian.user.vo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("UserSearchOutParamVo")
public class UserSearchOutParamVo {

	@ApiModelProperty(value="用户编码")
	private String code;
	
	@ApiModelProperty(value="显示名称")
	private String name;
	
	@ApiModelProperty(value="登录名")
	private String loginName;
	
	@ApiModelProperty(value="手机号")
	private String mobile;
	
	@ApiModelProperty(value="登录密码")
	private String password;

	@ApiModelProperty(value="用户-角色编码列表")
	private List<String> roleCds;
	
	@ApiModelProperty(value="角色列表")
	private List<RoleSearchOutParamVo> roleList;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getRoleCds() {
		return roleCds;
	}

	public void setRoleCds(List<String> roleCds) {
		this.roleCds = roleCds;
	}

	public List<RoleSearchOutParamVo> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<RoleSearchOutParamVo> roleList) {
		this.roleList = roleList;
	}
	
}
