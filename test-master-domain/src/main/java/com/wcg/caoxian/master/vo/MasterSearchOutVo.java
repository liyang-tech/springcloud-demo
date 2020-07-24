package com.wcg.caoxian.master.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("MasterSearchOutVo")
public class MasterSearchOutVo {

	@ApiModelProperty(value="对象编码")
	private String objectCd;
	
	@ApiModelProperty(value="主数据编码")
	private String code;
	
	@ApiModelProperty(value="主数据名称")
	private String name;
	
	@ApiModelProperty(value="描述")
	private String des;
	
	@ApiModelProperty(value="拼音简码")
	private String spellNo;
	
	@ApiModelProperty(value="排序号")
	private String sortNo;
	
	@ApiModelProperty(value="对象名称")
	private String objectNm;

	public String getObjectCd() {
		return objectCd;
	}

	public void setObjectCd(String objectCd) {
		this.objectCd = objectCd;
	}

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

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getSpellNo() {
		return spellNo;
	}

	public void setSpellNo(String spellNo) {
		this.spellNo = spellNo;
	}

	public String getSortNo() {
		return sortNo;
	}

	public void setSortNo(String sortNo) {
		this.sortNo = sortNo;
	}

	public String getObjectNm() {
		return objectNm;
	}

	public void setObjectNm(String objectNm) {
		this.objectNm = objectNm;
	}
	
}
