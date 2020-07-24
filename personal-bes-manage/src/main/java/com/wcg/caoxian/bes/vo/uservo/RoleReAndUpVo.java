package com.wcg.caoxian.bes.vo.uservo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("RoleReAndUpVo")
public class RoleReAndUpVo {

	@ApiModelProperty(value="角色编码", hidden=true)
	private String code;
	
	@ApiModelProperty(value="角色名称")
	private String name;
	
	@ApiModelProperty(value="权限菜单编码串")
	private String permissionCds;

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

	public String getPermissionCds() {
		return permissionCds;
	}

	public void setPermissionCds(String permissionCds) {
		this.permissionCds = permissionCds;
	}
	
}
