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
import com.wcg.caoxian.bes.vo.mastervo.MasterReAndUpVo;
import com.wcg.caoxian.bes.vo.mastervo.MasterSearchInputVo;
import com.wcg.caoxian.bes.vo.mastervo.MasterSearchOutVo;
import com.wcg.caoxian.sdk.bean.PageBean;
import com.wcg.caoxian.sdk.bean.Result;

@Service("mstMasterService")
public class MstMasterService {
	
	@Autowired
	private RestTemplate restTemplate;

	/**
	 * @Title: searchMasters
	 * @Description: 根据查询条件获取主数据列表
	 * @author 李洋  liyang
	 * @data 2017年12月13日 下午6:07:00
	 * @return Result<List<MasterSearchOutVo>>
	 */
	public Result<List<MasterSearchOutVo>> searchMasters(MasterSearchInputVo masterSearchInputVo, PageBean pageBean, HttpHeaders hearders) {
		HttpEntity<MasterSearchInputVo> requestEntity = new HttpEntity<>(masterSearchInputVo, hearders);
		ResponseEntity<Result<List<MasterSearchOutVo>>> response = restTemplate.exchange(MstDomainAddress.searchMasters + masterSearchInputVo.toURL(pageBean), 
				HttpMethod.GET, requestEntity, new ParameterizedTypeReference<Result<List<MasterSearchOutVo>>>() {});
		return response.getBody();
	}

	/**
	 * @Title: registerMaster
	 * @Description: 新增主数据信息
	 * @author 李洋  liyang
	 * @data 2017年12月14日 上午9:00:49
	 * @return Result<String>
	 */
	public Result<String> registerMaster(MasterReAndUpVo masterReAndUpVo, HttpHeaders headers) {
		HttpEntity<MasterReAndUpVo> requestEntity = new HttpEntity<>(masterReAndUpVo, headers);
		ResponseEntity<Result<String>> response = restTemplate.exchange(MstDomainAddress.registerMaster, 
				HttpMethod.POST, requestEntity, new ParameterizedTypeReference<Result<String>>() {});
		return response.getBody();
	}

	/**
	 * @Title: serachByCode
	 * @Description: BES0020-主数据单记录查询
	 * @author 李洋  liyang
	 * @data 2017年12月14日 上午9:03:05
	 * @return Result<MasterSearchOutVo>
	 */
	public Result<MasterSearchOutVo> serachByCode(String objectCd, String code, HttpHeaders headers) {
		HttpEntity<MasterSearchOutVo> requestEntity = new HttpEntity<>(null, headers);
		ResponseEntity<Result<MasterSearchOutVo>> response = restTemplate.exchange(MstDomainAddress.serachByCode, 
				HttpMethod.GET, requestEntity, new ParameterizedTypeReference<Result<MasterSearchOutVo>>() {}, objectCd, code);
		return response.getBody();
	}

	/**
	 * @Title: alterMaster
	 * @Description: BES0030-主数据编辑
	 * @author 李洋  liyang
	 * @data 2017年12月14日 上午9:05:21
	 * @return Result<String>
	 */
	public Result<String> alterMaster(String objectCd, String code, MasterReAndUpVo masterReAndUpVo, HttpHeaders headers) {
		HttpEntity<MasterReAndUpVo> requestEntity = new HttpEntity<>(masterReAndUpVo, headers);
		ResponseEntity<Result<String>> response = restTemplate.exchange(MstDomainAddress.alterMaster, 
				HttpMethod.PUT, requestEntity, new ParameterizedTypeReference<Result<String>>() {}, objectCd, code);
		return response.getBody();
	}

	/**
	 * @Title: deleteMaster
	 * @Description: BES0040-主数据删除
	 * @author 李洋  liyang
	 * @data 2017年12月14日 上午9:08:31
	 * @return Result<String>
	 */
	public Result<String> deleteMaster(String objectCd, String code, HttpHeaders headers) {
		HttpEntity<MasterReAndUpVo> requestEntity = new HttpEntity<>(null, headers);
		ResponseEntity<Result<String>> response = restTemplate.exchange(MstDomainAddress.deleteMaster, 
				HttpMethod.DELETE, requestEntity, new ParameterizedTypeReference<Result<String>>() {}, objectCd, code);
		return response.getBody();
	}

}
