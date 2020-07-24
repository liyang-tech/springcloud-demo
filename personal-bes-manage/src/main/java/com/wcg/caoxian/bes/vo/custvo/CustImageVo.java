package com.wcg.caoxian.bes.vo.custvo;

import org.springframework.data.annotation.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="CustPhotoVO")
public class CustImageVo {

	@Id
	@ApiModelProperty(value="id", notes="照片ID", hidden=true)
	private String id;
	
	@ApiModelProperty(value="image", notes="照片信息")
	private String image;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id=id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image=image;
	}

}
