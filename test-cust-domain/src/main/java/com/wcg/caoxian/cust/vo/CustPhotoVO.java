package com.wcg.caoxian.cust.vo;

import org.springframework.data.annotation.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "CustPhotoVO")
public class CustPhotoVO {

	@Id
	@ApiModelProperty(value = "id" , notes = "照片ID")
	private String id;
	
	@ApiModelProperty(value = "photo" , notes = "照片信息")
	private String photo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
}
