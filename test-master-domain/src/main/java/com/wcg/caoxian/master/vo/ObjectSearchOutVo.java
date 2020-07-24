package com.wcg.caoxian.master.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("ObjectSearchOutVo")
public class ObjectSearchOutVo {

	@ApiModelProperty("编码")
	private String code;
	
	@ApiModelProperty("名称")
	private String name;
	
	@ApiModelProperty("拼音简码")
	private String spellNo;

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

	public String getSpellNo() {
		return spellNo;
	}

	public void setSpellNo(String spellNo) {
		this.spellNo = spellNo;
	}
	
}
