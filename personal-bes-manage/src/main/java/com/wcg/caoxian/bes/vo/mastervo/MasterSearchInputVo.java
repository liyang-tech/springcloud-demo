package com.wcg.caoxian.bes.vo.mastervo;

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
	
}
