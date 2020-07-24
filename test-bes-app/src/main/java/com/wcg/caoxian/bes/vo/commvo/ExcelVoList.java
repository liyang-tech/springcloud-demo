package com.wcg.caoxian.bes.vo.commvo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("ExcelVoList")
public class ExcelVoList {

	@SuppressWarnings("rawtypes")
	@ApiModelProperty(value="表格列表vo")
	private List<ExcelVo> excelVoList;

	@SuppressWarnings("rawtypes")
	public List<ExcelVo> getExcelVoList() {
		return excelVoList;
	}

	@SuppressWarnings("rawtypes")
	public void setExcelVoList(List<ExcelVo> excelVoList) {
		this.excelVoList = excelVoList;
	}
	
}
