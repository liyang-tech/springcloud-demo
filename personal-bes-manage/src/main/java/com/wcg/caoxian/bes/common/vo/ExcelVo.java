package com.wcg.caoxian.bes.common.vo;

import java.util.List;

public class ExcelVo<T> {

	private List<T> data;//内容
	
	private String title;//标题
	
	private String sheetName;//sheet页
	
	private List<ExcelColumnVo> columns;//列表信息

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public List<ExcelColumnVo> getColumns() {
		return columns;
	}

	public void setColumns(List<ExcelColumnVo> columns) {
		this.columns = columns;
	}
	
}
