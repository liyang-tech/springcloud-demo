package com.wcg.caoxian.master.vo;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(solrCoreName="master")
public class MasterSolrVo {

	@Id
	@Field
	private String id;
	
	@Field(value="objectCd")
	private String objectCd;
	
	@Field(value="code")
	private String code;
	
	@Field(value="name")
	private String name;
	
	@Field(value="spellNo")
	private String spellNo;
	
	@Field(value="wubiNo")
	private String wubiNo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpellNo() {
		return spellNo;
	}

	public void setSpellNo(String spellNo) {
		this.spellNo = spellNo;
	}

	public String getWubiNo() {
		return wubiNo;
	}

	public void setWubiNo(String wubiNo) {
		this.wubiNo = wubiNo;
	}
	
}
