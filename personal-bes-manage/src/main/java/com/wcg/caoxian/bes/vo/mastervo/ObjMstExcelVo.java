package com.wcg.caoxian.bes.vo.mastervo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("ObjMstExcelVo")
public class ObjMstExcelVo {

	@ApiModelProperty(value="数据对象列表")
	private List<ObjectExcelVo> objectList;
	
	@ApiModelProperty(value="主数据列表")
	private List<MasterExcelVo> masterList;

	public List<ObjectExcelVo> getObjectList() {
		return objectList;
	}

	public void setObjectList(List<ObjectExcelVo> objectList) {
		this.objectList = objectList;
	}

	public List<MasterExcelVo> getMasterList() {
		return masterList;
	}

	public void setMasterList(List<MasterExcelVo> masterList) {
		this.masterList = masterList;
	}

}
