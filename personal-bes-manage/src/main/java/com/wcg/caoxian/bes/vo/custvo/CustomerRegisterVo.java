package com.wcg.caoxian.bes.vo.custvo;

import com.wcg.caoxian.sdk.cache.DateValide;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("CustomerRegisterVo")
public class CustomerRegisterVo {

	@ApiModelProperty(value="姓名")
	private String name;
	
	@ApiModelProperty(value="类型编码")
	private String typeCd;
	
	@ApiModelProperty(value="性别编码")
	private String sexCd;
	
	@ApiModelProperty(value="婚姻状态编码")
	private String marriedCd;
	
	@ApiModelProperty(value="证件类型编码")
	private String certificationTypeCd;
	
	@ApiModelProperty(value="证件号")
	private String certificationNo;
	
	@ApiModelProperty(value="出生日期")
	@DateValide(dateType = "date")
	private String birthday;
	
	@ApiModelProperty(value="邮箱")
	private String email;
	
	@ApiModelProperty(value="联系电话")
	private String mobile;
	
	@ApiModelProperty(value="创建者编码")
	private String createdByCd;
	
	@ApiModelProperty(value="创建时间")
	@DateValide(dateType = "datetime")
	private String createdTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTypeCd() {
		return typeCd;
	}

	public void setTypeCd(String typeCd) {
		this.typeCd = typeCd;
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

	public String getCertificationTypeCd() {
		return certificationTypeCd;
	}

	public void setCertificationTypeCd(String certificationTypeCd) {
		this.certificationTypeCd = certificationTypeCd;
	}

	public String getCertificationNo() {
		return certificationNo;
	}

	public void setCertificationNo(String certificationNo) {
		this.certificationNo = certificationNo;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCreatedByCd() {
		return createdByCd;
	}

	public void setCreatedByCd(String createdByCd) {
		this.createdByCd = createdByCd;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	
}
