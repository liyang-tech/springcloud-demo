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

import com.wcg.caoxian.bes.common.CustDomainAddress;
import com.wcg.caoxian.bes.vo.custvo.CustomerAlterVo;
import com.wcg.caoxian.bes.vo.custvo.CustomerInputParamVo;
import com.wcg.caoxian.bes.vo.custvo.CustomerOutParamVo;
import com.wcg.caoxian.bes.vo.custvo.CustomerRegisterVo;
import com.wcg.caoxian.sdk.bean.PageBean;
import com.wcg.caoxian.sdk.bean.Result;

@Service("customerService")
public class CustomerService {

	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * @Title: registerCustomer
	 * @Description: BES0210-新增客户信息
	 * @author 李洋  liyang
	 * @data 2017年9月18日 上午10:40:35
	 * @return Result<String>
	 */
	public Result<String> registerCustomer(CustomerRegisterVo customerRegisterVo, HttpHeaders headers) {
		HttpEntity<CustomerRegisterVo> requestEntity = new HttpEntity<>(customerRegisterVo, headers);
		ResponseEntity<Result<String>> response = restTemplate.exchange(CustDomainAddress.registerCustomer, 
				HttpMethod.POST, requestEntity, new ParameterizedTypeReference<Result<String>>() {
		});
		return response.getBody();
	}
	
	/**
	 * @Title: searchByCondition
	 * @Description: BES0220-根据查询条件获取客户信息
	 * @author 李洋  liyang
	 * @data 2017年9月8日 下午5:49:52
	 * @return List<CustomerOutParamVo>
	 */
	public Result<List<CustomerOutParamVo>> searchByCondition(CustomerInputParamVo customerInputParamVo, PageBean pageBean, HttpHeaders headers) {
		HttpEntity<List<CustomerOutParamVo>> requestEntity = new HttpEntity<>(null, headers);
		ResponseEntity<Result<List<CustomerOutParamVo>>> response = restTemplate.exchange(CustDomainAddress.searchCustomers + customerInputParamVo.toURL(pageBean), 
				HttpMethod.GET, requestEntity, new ParameterizedTypeReference<Result<List<CustomerOutParamVo>>>() {
				});
		return response.getBody();
	}
	

	/**
	 * @Title: alterCustomer
	 * @Description: BES0230-客户信息修改
	 * @author 李洋  liyang
	 * @data 2017年9月19日 上午11:44:30
	 * @return Result<String>
	 */
	public Result<String> alterCustomer(String code, CustomerAlterVo customerAlterVo, HttpHeaders headers) {
		HttpEntity<CustomerAlterVo> requestEntity = new HttpEntity<>(customerAlterVo, headers);
		ResponseEntity<Result<String>> response = restTemplate.exchange(CustDomainAddress.alterCustomer, 
				HttpMethod.PUT, requestEntity, new ParameterizedTypeReference<Result<String>>(){}, code);
		return response.getBody();
	}

	/**
	 * @Title: deleteCustomer
	 * @Description: BES0240-客户信息删除
	 * @author 李洋  liyang
	 * @data 2017年9月19日 上午11:45:32
	 * @return Result<String>
	 */
	public Result<String> deleteCustomer(String code, HttpHeaders headers) {
		HttpEntity<?> requestEntity = new HttpEntity<>(null, headers);
		ResponseEntity<Result<String>> response = restTemplate.exchange(CustDomainAddress.deleteCustomer, 
				HttpMethod.DELETE, requestEntity, new ParameterizedTypeReference<Result<String>>(){}, code);
		return response.getBody();
	}

	/**
	 * @Title: searchOneCust
	 * @Description: BES0250-客户单记录查询
	 * @author 李洋  liyang
	 * @param headers 
	 * @data 2017年9月19日 上午11:46:52
	 * @return CustomerOutParamVo
	 */
	public Result<CustomerOutParamVo> searchOneCust(String code, HttpHeaders headers) {
		HttpEntity<?> requestEntity = new HttpEntity<>(null, headers);
		ResponseEntity<Result<CustomerOutParamVo>> response = restTemplate.exchange(CustDomainAddress.searchOneCust, 
				HttpMethod.GET, requestEntity, new ParameterizedTypeReference<Result<CustomerOutParamVo>>(){}, code);
		return response.getBody();
	}
	
	

}
