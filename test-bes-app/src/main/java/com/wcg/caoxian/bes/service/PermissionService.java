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
import com.wcg.caoxian.bes.vo.uservo.PermissionReAndUpVo;
import com.wcg.caoxian.bes.vo.uservo.PermissionSearchOutParamVo;
import com.wcg.caoxian.bes.vo.uservo.UserReAndUpVo;
import com.wcg.caoxian.sdk.bean.PageBean;
import com.wcg.caoxian.sdk.bean.Result;

@Service("permissionService")
public class PermissionService {

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * @Title: registerUser
	 * @Description: BES0110-权限信息新增
	 * @author 李洋  liyang
	 * @data 2017年9月29日 下午1:58:08
	 * @return Result<String>
	 */
	public Result<String> registerPermission(PermissionReAndUpVo permissionVo, HttpHeaders headers) {
		HttpEntity<PermissionReAndUpVo> requestEntity = new HttpEntity<>(permissionVo, headers);
		ResponseEntity<Result<String>> response = restTemplate.exchange(UserDomainAddress.registerPermission, 
				HttpMethod.POST, requestEntity, new ParameterizedTypeReference<Result<String>>() {
		});
		return response.getBody();
	}

	/**
	 * @Title: alertPermission
	 * @Description: BES0120-权限信息编辑
	 * @author 李洋  liyang
	 * @param code 
	 * @data 2017年10月13日 下午1:58:44
	 * @return Result<String>
	 */
	public Result<String> alertPermission(String code, PermissionReAndUpVo permissionVo, HttpHeaders headers) {
		HttpEntity<PermissionReAndUpVo> requestEntity = new HttpEntity<>(permissionVo, headers);
		ResponseEntity<Result<String>> response = restTemplate.exchange(UserDomainAddress.alertPermission, 
				HttpMethod.PUT, requestEntity, 
				new ParameterizedTypeReference<Result<String>>() {}, code);
		return response.getBody();
	}

	/**
	 * @Title: searchByCondition
	 * @Description: BES0130-权限菜单列表查询
	 * @author 李洋  liyang
	 * @param headers 
	 * @data 2017年9月29日 下午2:55:40
	 * @return List<PermissionSearchOutParamVo>
	 */
	public Result<List<PermissionSearchOutParamVo>> searchPermissions(String name, PageBean pageBean, HttpHeaders headers) {
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
		ResponseEntity<Result<List<PermissionSearchOutParamVo>>> response = restTemplate.exchange(UserDomainAddress.searchPermissions + url, 
				HttpMethod.GET, requestEntity, 
				new ParameterizedTypeReference<Result<List<PermissionSearchOutParamVo>>>() {});
		return response.getBody();
	}

	/**
	 * @Title: searchByCode
	 * @Description: BES0140-权限菜单单记录查询
	 * @author 李洋  liyang
	 * @data 2017年10月13日 下午2:06:58
	 * @return Result<PermissionSearchOutParamVo>
	 */
	public Result<PermissionSearchOutParamVo> searchByCode(String code, HttpHeaders headers) {
		HttpEntity<PermissionSearchOutParamVo> requestEntity = new HttpEntity<>(null, headers);
		ResponseEntity<Result<PermissionSearchOutParamVo>> response = restTemplate.exchange(UserDomainAddress.serachPermissionByCode, 
				HttpMethod.GET, requestEntity, 
				new ParameterizedTypeReference<Result<PermissionSearchOutParamVo>>() {}, code);
		return response.getBody();
	}

	/**
	 * @Title: deletePermission
	 * @Description: BES0150-权限菜单删除
	 * @author 李洋  liyang
	 * @data 2017年10月13日 下午2:09:40
	 * @return Result<String>
	 */
	public Result<String> deletePermission(String code, HttpHeaders headers) {
		HttpEntity<UserReAndUpVo> requestEntity = new HttpEntity<>(null, headers);
		ResponseEntity<Result<String>> response = restTemplate.exchange(UserDomainAddress.deletePermission, 
				HttpMethod.DELETE, requestEntity, 
				new ParameterizedTypeReference<Result<String>>() {}, code);
		return response.getBody();
	}

}
