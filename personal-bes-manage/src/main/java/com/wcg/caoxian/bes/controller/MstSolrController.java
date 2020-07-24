package com.wcg.caoxian.bes.controller;
/*package com.wcg.caoxian.master.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wcg.caoxian.sdk.bean.PageBean;
import com.wcg.caoxian.sdk.bean.Result;
import com.wcg.caoxian.sdk.util.PatternUtil;
import com.wcg.caoxian.sdk.util.Translation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/mstsolr")
@ConfigurationProperties(prefix="spring.solr")
@Api(description = "主数据solr管理查询领域服务")
public class MstSolrController {
	
	private String basecodeURL;
	
	public String getBasecodeURL() {
		return basecodeURL;
	}

	public void setBasecodeURL(String basecodeURL) {
		this.basecodeURL = basecodeURL;
	}


	*//**
	 * 根据name 或  objectCd 或 spellNo 查询主数据信息
	 * @param content start rrows
	 * @return  list
	 * @author liyang
	 * 2017年4月1日
	 * @throws IOException 
	 *//*
	@ApiOperation(value = "solr查询主数据信息", notes = "solr查询主数据信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "content", value = "name或objectCd或spellNo", required = true, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "start", value = "起始行(0)", required = true, dataType = "int", paramType = "query"),
		@ApiImplicitParam(name = "rows", value = "行数", required = true, dataType = "int", paramType = "query"),
		@ApiImplicitParam(name = "TK_BUSINESS_SERIALID", value = "交易流水", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_SYS_CODE", value = "请求方系统编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODULE_CODE", value = "请求方模块编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODE_IP", value = "请求方节点IP", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "Accept", value = "接收属性", required = true, dataType = "String", paramType = "header", defaultValue = "application/json"),
		@ApiImplicitParam(name = "Accept-Charset", value = "接收字符集", required = true, dataType = "String", paramType = "header", defaultValue = "utf-8"),
		@ApiImplicitParam(name = "Content-Type", value = "内容类型", required = true, dataType = "String", paramType = "header", defaultValue = "application/json; charset = UTF-8")
	})
	@RequestMapping(value="/basecode/v1/basecodes", method = RequestMethod.GET)
	public Result<JSONArray> searchBySolr(@RequestParam String content, @RequestParam Integer start, @RequestParam Integer rows, @RequestHeader HttpHeaders headers ) throws IOException{
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		PageBean pageBean = new PageBean();
		//查询条件信息为空  返回code
		if(content == null || content.equals("")){
			pageBean.setTotalElements(0l);
			Result<JSONArray> result = new Result<JSONArray>();
			result.setPage(pageBean);
			result.setCode("1");
			return result;
		}
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setFacet(true);
		solrQuery.set("start", start);
		solrQuery.set("rows", rows);
		content = Translation.translationSpcilStr(content);
		if(PatternUtil.isStringContinsChiness(content)){
			content = "name:"+content+"*";
		}else if(PatternUtil.isEnglish(content)){
			content = "spellNo:"+content.toUpperCase()+"*";
		}else{
			content = "objectCd:"+content+"*";
		}
		solrQuery.set("q", content);
		
		QueryResponse queryResponse = null;
		HttpSolrClient httpSolrClient = null;
		try {
			httpSolrClient = new HttpSolrClient(this.basecodeURL);
			queryResponse = httpSolrClient.query(solrQuery);
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(httpSolrClient != null){
				httpSolrClient.close();
			}
		}
		
		SolrDocumentList solrDocumentList = queryResponse.getResults();
		
		long number = solrDocumentList.getNumFound();
		for (SolrDocument solrDocument : solrDocumentList) {
			Map<String, Object> map = solrDocument.getFieldValueMap();
			resultList.add(map);
		}
		
		pageBean.setNumber(number);
		pageBean.setTotalElements(Long.parseLong(start.toString()));
		pageBean.setOffset(Long.parseLong(rows.toString()));
		
		JSONArray arr = new JSONArray();
		for (Map<String, Object> map : resultList) {
			JSONObject obj = new JSONObject();
			Iterator<String> it = map.keySet().iterator();
			while(it.hasNext()){
				String key = it.next();
				Object value = map.get(key);
				obj.put(key, value);
			}
			arr.add(obj);
		}
		Result<JSONArray> result = new Result<JSONArray>();
		result.setPage(pageBean);
		result.setData(arr);
		result.setCode("0");
		return result;
	}
	
	
}
*/