package com.wcg.caoxian.bes.vo.custvo;

import com.wcg.caoxian.sdk.bean.PageBean;

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
	
	
	public String toURL(PageBean pageBean){
    	String url = "?1=1";
    	if(this.name != null){
    		url += "&name=" + this.name;
    	}
    	if(this.mobile != null){
    		url += "&mobile=" + this.mobile;
    	}
    	if(this.sexCd != null){
    		url += "&sexCd=" + this.sexCd;
    	}
    	if(this.marriedCd != null){
    		url += "&marriedCd=" + this.marriedCd;
    	}
    	if(this.certificationNo != null){
    		url += "&certificationNo=" + this.certificationNo;
    	}
    	if(pageBean != null){
    		if(pageBean.getNumber() != null){
    			url += "&number=" + pageBean.getNumber();
    		}
    		if(pageBean.getSize() != null){
    			url += "&size=" + pageBean.getSize();
    		}
    	}
    	return url;
    }
}
