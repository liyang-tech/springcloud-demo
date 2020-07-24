package com.wcg.caoxian.sdk.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "PageBean", description = "分页信息")
public class PageBean {
	
	@ApiModelProperty(value = "number", hidden = true, notes = "当前页")
	private Long number;//当前页
	
	@ApiModelProperty(value = "size", hidden = true, notes = "每页条数")
	private Long size;//每页条数
	
	@ApiModelProperty(value = "offset", hidden = true, notes = "起始条数")
	private Long offset;//起始条数
	
	@ApiModelProperty(value = "totalElements", hidden = true, notes = "总条数")
	private Long totalElements;//总条数
	
	@ApiModelProperty(value = "totalPages", hidden = true, notes = "总页数")
	private Long totalPages;//总页数
	
	public void init(){
		if(this.number != null && this.size != null){
			if(this.number != 0 && this.size != 0){
				this.offset = (this.number-1)*this.size;
				return;
			}
			this.number = null;
			this.size = null;
			this.offset = null;
		}
	}
	
	public void setTotalPages(){
		if(this.totalElements != null && this.size != null){
			if(this.totalElements != 0 && this.size != 0){
				if(this.totalElements % this.size != 0){
					this.totalPages = this.totalElements/this.size + 1;
					return;
				}else{
					this.totalPages = this.totalElements/this.size;
					return;
				}
			}
		}
		this.number = null;
		this.size = null;
		this.offset = null;
		this.totalElements = null;
		this.totalPages = null;
	}
	
	public Long getNumber() {
		return number;
	}
	public void setNumber(Long number) {
		this.number = number;
	}
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	public Long getOffset() {
		return offset;
	}
	public void setOffset(Long offset) {
		this.offset = offset;
	}
	public Long getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}
	public Long getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(Long totalPages) {
		this.totalPages = totalPages;
	}
	
}
