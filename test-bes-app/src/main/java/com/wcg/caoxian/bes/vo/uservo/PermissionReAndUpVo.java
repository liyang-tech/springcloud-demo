package com.wcg.caoxian.bes.vo.uservo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("PermissionReAndUpVo")
public class PermissionReAndUpVo {
	
	@ApiModelProperty(value="权限名称", hidden = true)
	private String code;
	
	@ApiModelProperty(value="权限名称")
	private String name;
	
	@ApiModelProperty(value="权限英文名称")
	private String enName;
	
	@ApiModelProperty(value="链接路径")
	private String uri;
	
	@ApiModelProperty(value="排序号")
	private Integer sortNo;

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

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}
	
}