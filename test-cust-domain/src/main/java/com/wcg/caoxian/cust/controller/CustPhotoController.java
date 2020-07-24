package com.wcg.caoxian.cust.controller;


import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wcg.caoxian.cust.service.CustPhotoService;
import com.wcg.caoxian.cust.vo.CustPhotoVO;
import com.wcg.caoxian.sdk.bean.Result;
import com.wcg.caoxian.sdk.util.Base64FileUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/custphotodomain")
@Api(description = "客户照片管理增删改查领域服务")
public class CustPhotoController {
	
	@Resource
	private CustPhotoService custPhotoService;
	
	
	/**
	 * 根据照片ID查询照片信息
	 * @param id
	 * @return CustPhotoVO
	 * @author liyang
	 * 2017年3月17日
	 */
	@ApiOperation(value = "根据照片ID查询照片信息", notes = "根据照片ID查询照片信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "照片ID", required = true, dataType = "String", paramType = "path"),
		@ApiImplicitParam(name = "TK_BUSINESS_SERIALID", value = "交易流水", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_SYS_CODE", value = "请求方系统编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODULE_CODE", value = "请求方模块编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODE_IP", value = "请求方节点IP", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "Accept", value = "接收属性", required = true, dataType = "String", paramType = "header", defaultValue = "application/json"),
		@ApiImplicitParam(name = "Accept-Charset", value = "接收字符集", required = true, dataType = "String", paramType = "header", defaultValue = "utf-8"),
		@ApiImplicitParam(name = "Content-Type", value = "内容类型", required = true, dataType = "String", paramType = "header", defaultValue = "application/json; charset = UTF-8")
	})
	@RequestMapping(value="/cust/v1/custphoto/{id}", method = RequestMethod.GET)
	public Result<CustPhotoVO> searchById(@PathVariable String id){
		CustPhotoVO custPhotoVO = custPhotoService.findById(id);
		try {
			Base64FileUtil.decoderBase64File(custPhotoVO.getPhoto(), "E:\\aaa.jpg");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result<>("0",custPhotoVO);
	}
	

	/**
	 * 新增照片信息
	 * @param custPhotoVO
	 * @param headers
	 * @return true or false
	 * @author liyang
	 * 2017年3月17日
	 */
	@ApiOperation(value = "新增照片信息", notes = "新增照片信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "custPhotoVO", value = "照片信息", required = true, dataType = "Customer", paramType = "body"),
		@ApiImplicitParam(name = "TK_BUSINESS_SERIALID", value = "交易流水", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_SYS_CODE", value = "请求方系统编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODULE_CODE", value = "请求方模块编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODE_IP", value = "请求方节点IP", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "Accept", value = "接收属性", required = true, dataType = "String", paramType = "header", defaultValue = "application/json"),
		@ApiImplicitParam(name = "Accept-Charset", value = "接收字符集", required = true, dataType = "String", paramType = "header", defaultValue = "utf-8"),
		@ApiImplicitParam(name = "Content-Type", value = "内容类型", required = true, dataType = "String", paramType = "header", defaultValue = "application/json; charset = UTF-8")
	})
	@RequestMapping(value="/cust/v1/custphoto", method = RequestMethod.POST)
	public Result<?> registerCustomer(@RequestBody CustPhotoVO custPhotoVO, @RequestHeader HttpHeaders headers){
		
		UUID uuid = UUID.randomUUID();
		custPhotoVO.setId(uuid.toString().replace("-", ""));
		try {
			String base64code = Base64FileUtil.encodeBase64File("E:\\XX0070973 (3).jpg");
			custPhotoVO.setPhoto(base64code);
		} catch (Exception e) {
			e.printStackTrace();
		}
		CustPhotoVO custPhotoVO1 = custPhotoService.save(custPhotoVO);
		return new Result<>("0", custPhotoVO1);
	}
	
	
	
}
