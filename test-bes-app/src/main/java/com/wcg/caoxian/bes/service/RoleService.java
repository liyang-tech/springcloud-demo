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

import com.wcg.caoxian.bes.common.UserDomainAddress;
import com.wcg.caoxian.bes.vo.uservo.RoleReAndUpVo;
import com.wcg.caoxian.bes.vo.uservo.RoleSearchOutParamVo;
import com.wcg.caoxian.bes.vo.uservo.UserReAndUpVo;
import com.wcg.caoxian.sdk.bean.PageBean;
import com.wcg.caoxian.sdk.bean.Result;

@Service("roleService")
public class RoleService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * @Title: registerRole
	 * @Description: BES0160-角色(角色和权限关系绑定)新增
	 * @author 李洋  liyang
	 * @data 2017年10月13日 下午2:31:08
	 * @return Result<String>
	 */
	public Result<String> registerRole(RoleReAndUpVo roleVo, HttpHeaders headers) {
		HttpEntity<RoleReAndUpVo> requestEntity = new HttpEntity<>(roleVo, headers);
		ResponseEntity<Result<String>> response = restTemplate.exchange(UserDomainAddress.registerRole, 
				HttpMethod.POST, requestEntity, new ParameterizedTypeReference<Result<String>>() {
		});
		return response.getBody();
	}

	/**
	 * @Title: alterRole
	 * @Description: BES0170-角色(角色和权限关系绑定)修改
	 * @author 李洋  liyang
	 * @data 2017年10月13日 下午2:40:28
	 * @return Result<String>
	 */
	public Result<String> alterRole(String code, RoleReAndUpVo roleVo, HttpHeaders headers) {
		HttpEntity<RoleReAndUpVo> requestEntity = new HttpEntity<>(roleVo, headers);
		ResponseEntity<Result<String>> response = restTemplate.exchange(UserDomainAddress.alterRole, 
				HttpMethod.PUT, requestEntity, 
				new ParameterizedTypeReference<Result<String>>() {}, code);
		return response.getBody();
	}

	/**
	 * @Title: getByCode
	 * @Description: BES0180-角色单记录查询
	 * @author 李洋  liyang
	 * @data 2017年10月13日 下午2:42:23
	 * @return Result<RoleSearchOutParamVo>
	 */
	public Result<RoleSearchOutParamVo> getByCode(String code, HttpHeaders headers) {
		HttpEntity<RoleSearchOutParamVo> requestEntity = new HttpEntity<>(null, headers);
		ResponseEntity<Result<RoleSearchOutParamVo>> response = restTemplate.exchange(UserDomainAddress.getByCode, 
				HttpMethod.GET, requestEntity, 
				new ParameterizedTypeReference<Result<RoleSearchOutParamVo>>() {}, code);
		return response.getBody();
	}

	/**
	 * @Title: deleteRole
	 * @Description: BES0190-角色删除
	 * @author 李洋  liyang
	 * @data 2017年10月13日 下午2:44:05
	 * @return Result<String>
	 */
	public Result<String> deleteRole(String code, HttpHeaders headers) {
		HttpEntity<UserReAndUpVo> requestEntity = new HttpEntity<>(null, headers);
		ResponseEntity<Result<String>> response = restTemplate.exchange(UserDomainAddress.deleteRole, 
				HttpMethod.DELETE, requestEntity, 
				new ParameterizedTypeReference<Result<String>>() {}, code);
		return response.getBody();
	}

	/**
	 * @Title: searchRoles
	 * @Description: BES0200-角色列表查询
	 * @author 李洋  liyang
	 * @data 2017年10月13日 下午2:45:59
	 * @return Result<List<RoleSearchOutParamVo>>
	 */
	public Result<List<RoleSearchOutParamVo>> searchRoles(String name, PageBean pageBean, HttpHeaders headers) {
		//拼接查询条件
		String url = "?1=1";
		if(name != null && !"".equals(name)){
			url += "&name=" + name;
		}
		if(pageBean != null){
		   	if(pageBean.getNumber() != null){
		   		url += "&number=" + pageBean.getNumber();
		   	}
		   	if(pageBean.getSize() != null){
		   		url += "&size=" + pageBean.getSize();
		   	}
		}
		HttpEntity<?> requestEntity = new HttpEntity<>(null, headers);
		ResponseEntity<Result<List<RoleSearchOutParamVo>>> response = restTemplate.exchange(UserDomainAddress.searchRoles + url, 
				HttpMethod.GET, requestEntity, 
				new ParameterizedTypeReference<Result<List<RoleSearchOutParamVo>>>() {});
		return response.getBody();
	}

	
}
