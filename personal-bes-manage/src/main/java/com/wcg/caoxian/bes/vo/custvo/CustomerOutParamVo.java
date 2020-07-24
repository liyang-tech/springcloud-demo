package com.wcg.caoxian.bes.vo.custvo;

import com.wcg.caoxian.sdk.cache.Code2NameLabel;
import com.wcg.caoxian.sdk.constants.ObjectCd;

import io.swagger.annotations.ApiModel;

@ApiModel(value="CustomerOutParamVo")
public class CustomerOutParamVo {

	   private String code;

	   @Code2NameLabel(objectCd=ObjectCd.CUST_TYPE, target="typeNm")
	   private String typeCd;
	   
	   private String typeNm;

	   private String name;

	   @Code2NameLabel(objectCd=ObjectCd.SEX, target="sexNm")
	   private String sexCd;

	   private String sexNm;
	   
	   @Code2NameLabel(objectCd=ObjectCd.MARRIED, target="marriedNm")
	   private String marriedCd;

	   private String marriedNm;
	   
	   @Code2NameLabel(objectCd=ObjectCd.CERTIFICATION_TYPE, target="certificationTypeNm")
	   private String certificationTypeCd;
	   
	   private String certificationTypeNm;

	   private String certificationNo;

	   private String birthday;

	   private String email;

	   private String mobile;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTypeCd() {
		return typeCd;
	}

	public void setTypeCd(String typeCd) {
		this.typeCd = typeCd;
	}

	public String getTypeNm() {
		return typeNm;
	}

	public void setTypeNm(String typeNm) {
		this.typeNm = typeNm;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSexCd() {
		return sexCd;
	}

	public void setSexCd(String sexCd) {
		this.sexCd = sexCd;
	}

	public String getSexNm() {
		return sexNm;
	}

	public void setSexNm(String sexNm) {
		this.sexNm = sexNm;
	}

	public String getMarriedCd() {
		return marriedCd;
	}

	public void setMarriedCd(String marriedCd) {
		this.marriedCd = marriedCd;
	}

	public String getMarriedNm() {
		return marriedNm;
	}

	public void setMarriedNm(String marriedNm) {
		this.marriedNm = marriedNm;
	}

	public String getCertificationTypeCd() {
		return certificationTypeCd;
	}

	public void setCertificationTypeCd(String certificationTypeCd) {
		this.certificationTypeCd = certificationTypeCd;
	}

	public String getCertificationTypeNm() {
		return certificationTypeNm;
	}

	public void setCertificationTypeNm(String certificationTypeNm) {
		this.certificationTypeNm = certificationTypeNm;
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

}
