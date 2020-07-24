package com.wcg.caoxian.bes.vo.uservo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("UserLoginOutVo")
public class UserLoginOutVo {

	@ApiModelProperty(value="显示名称")
	private String name;
	
	@ApiModelProperty(value="菜单列表")
	private List<PermissionSearchOutParamVo> permissionList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PermissionSearchOutParamVo> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<PermissionSearchOutParamVo> permissionList) {
		this.permissionList = permissionList;
	}
	
}
