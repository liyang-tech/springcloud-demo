package com.wcg.caoxian.bes.vo.mastervo;

import com.wcg.caoxian.sdk.bean.PageBean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("MasterSearchInputVo")
public class MasterSearchInputVo {

	@ApiModelProperty(value="对象编码")
	private String objectCd;
	
	@ApiModelProperty(value="主数据编码")
	private String code;
	
	@ApiModelProperty(value="关键字，匹配名称或拼音简码")
	private String keyword;

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

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String toURL(PageBean pageBean) {
		String url = "?1=1";
    	if(this.keyword != null){
    		url += "&keyword=" + this.keyword;
    	}
    	if(this.code != null){
    		url += "&code=" + this.code;
    	}
    	if(this.objectCd != null){
    		url += "&objectCd=" + this.objectCd;
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
