package com.wcg.caoxian.bes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wcg.caoxian.bes.common.MstDomainAddress;
import com.wcg.caoxian.bes.vo.mastervo.ObjectSearchOutVo;
import com.wcg.caoxian.sdk.bean.PageBean;
import com.wcg.caoxian.sdk.bean.Result;

@Service("objectService")
public class ObjectService {

	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * @Title: searchByCondition
	 * @Description: 根据查询条件获取数据对象列表
	 * @author 李洋  liyang
	 * @data 2017年12月14日 下午2:08:25
	 * @return Result<List<ObjectSearchOutVo>>
	 */
	public Result<List<ObjectSearchOutVo>> searchByCondition(String keyword, PageBean pageBean, HttpHeaders hearders) {
		//拼接查询条件
		String url = "?1=1";
		if(keyword != null && !"".equals(keyword)){
			url += "&keyword=" + keyword;
		}
		if(pageBean != null){
    		if(pageBean.getNumber() != null){
    			url += "&number=" + pageBean.getNumber();
    		}
    		if(pageBean.getSize() != null){
    			url += "&size=" + pageBean.getSize();
    		}
    	}
		HttpEntity<?> requestEntity = new HttpEntity<>(null, hearders);
		ResponseEntity<Result<List<ObjectSearchOutVo>>> response = restTemplate.exchange(MstDomainAddress.searchObjects + url, 
				HttpMethod.GET, requestEntity, new ParameterizedTypeReference<Result<List<ObjectSearchOutVo>>>() {});
		return response.getBody();
	}

	

}
