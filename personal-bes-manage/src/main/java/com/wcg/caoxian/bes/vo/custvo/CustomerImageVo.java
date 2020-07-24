package com.wcg.caoxian.bes.vo.custvo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("CustomerImageVo")
public class CustomerImageVo {

	@ApiModelProperty(value="客户编码")
	private String customerCd;
	
	@ApiModelProperty(value="照片ID")
	private String fileId;
	
	@ApiModelProperty(value="照片类型编码")
	private String imageTypeCd;

	public String getCustomerCd() {
		return customerCd;
	}

	public void setCustomerCd(String customerCd) {
		this.customerCd = customerCd;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getImageTypeCd() {
		return imageTypeCd;
	}

	public void setImageTypeCd(String imageTypeCd) {
		this.imageTypeCd = imageTypeCd;
	}
	
}
