package com.wcg.caoxian.bes.vo.uservo;

import java.util.List;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("RoleSearchOutParamVo")
public class RoleSearchOutParamVo {

	@ApiModelProperty(value="角色编码")
	private String code;
	
	@ApiModelProperty(value="角色名称")
	private String name;
	
	@ApiModelProperty(value="角色-权限菜单编码列表", hidden=true)
	private List<String> permissionCds;
	
	@ApiModelProperty(value="权限菜单列表")
	private List<PermissionSearchOutParamVo> permissionList;
	
	@ApiModelProperty(value="选中标志")
	private Boolean checkFlag;

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

	public List<String> getPermissionCds() {
		return permissionCds;
	}

	public void setPermissionCds(List<String> permissionCds) {
		this.permissionCds = permissionCds;
	}

	public List<PermissionSearchOutParamVo> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<PermissionSearchOutParamVo> permissionList) {
		this.permissionList = permissionList;
	}

	public Boolean getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(Boolean checkFlag) {
		this.checkFlag = checkFlag;
	}
	
}
