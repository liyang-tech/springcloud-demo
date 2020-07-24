package com.wcg.caoxian.cust.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="CustomerInputParamVo")
public class CustomerInputParamVo {

	@ApiModelProperty(value="客户名称")
	private String name;
	
	@ApiModelProperty(value="手机号")
	private String mobile;
	
	@ApiModelProperty(value="性别编码")
	private String sexCd;
	
	@ApiModelProperty(value="婚姻状态编码")
	private String marriedCd;
	
	@ApiModelProperty(value="证件号")
	private String certificationNo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSexCd() {
		return sexCd;
	}

	public void setSexCd(String sexCd) {
		this.sexCd = sexCd;
	}

	public String getMarriedCd() {
		return marriedCd;
	}

	public void setMarriedCd(String marriedCd) {
		this.marriedCd = marriedCd;
	}

	public String getCertificationNo() {
		return certificationNo;
	}

	public void setCertificationNo(String certificationNo) {
		this.certificationNo = certificationNo;
	}
	
}
