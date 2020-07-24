package com.wcg.caoxian.bes.service;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.wcg.caoxian.sdk.bean.Result;

@Service("custSolrService")
public class CustSolrService {

	
	/**
	 * 根据name或者是电话查询客户信息
	 * @param content start rrows
	 * @return  custList
	 * @author liyang
	 * 2017年4月1日
	 */
	public Result<JSONArray> searchBySolr(String content, Integer start, Integer rows, HttpHeaders headers) {
		return null;
	}

}
