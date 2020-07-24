package com.wcg.caoxian.bes.vo.custvo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("CustomerAlterVo")
public class CustomerAlterVo {
	
	@ApiModelProperty(value="客户编码", hidden=true)
	private String code;

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
	private String birthday;
	
	@ApiModelProperty(value="邮箱")
	private String email;
	
	@ApiModelProperty(value="联系电话")
	private String mobile;
	
	@ApiModelProperty(value="修改者编码")
	private String lastUpdatedByCd;

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

	public String getLastUpdatedByCd() {
		return lastUpdatedByCd;
	}

	public void setLastUpdatedByCd(String lastUpdatedByCd) {
		this.lastUpdatedByCd = lastUpdatedByCd;
	}

	
}
