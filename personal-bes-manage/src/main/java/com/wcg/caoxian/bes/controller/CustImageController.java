package com.wcg.caoxian.bes.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wcg.caoxian.bes.service.CustomerImageService;
import com.wcg.caoxian.bes.vo.custvo.CustImageVo;
import com.wcg.caoxian.sdk.bean.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/tkrsbes")
@Api(description="客户照片管理服务API接口")
public class CustImageController {
	
	@Autowired
	private CustomerImageService customerImageService;
	
	/**
	 * @Title: searchImage
	 * @Description: 客户照片信息查询
	 * @author 李洋  liyang
	 * @data 2018年6月22日 下午3:56:15
	 * @return Result<CustImageVO>
	 */
	@ApiOperation(value="客户照片信息查询", notes="根据客户编码和图片类型查询照片信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name="customerCd", value="客户编码", required=true, dataType="String", paramType="path"),
		@ApiImplicitParam(name="imageTypeCd", value="图片类型编码", required=true, dataType="String", paramType="query"),
		@ApiImplicitParam(name="TK_BUSINESS_SERIALID", value="交易流水", required=false, dataType="String", paramType="header"),
		@ApiImplicitParam(name="TK_REQUEST_SYS_CODE", value="请求方系统编码", required=false, dataType="String", paramType="header"),
		@ApiImplicitParam(name="TK_REQUEST_NODULE_CODE", value="请求方模块编码", required=false, dataType="String", paramType="header"),
		@ApiImplicitParam(name="TK_REQUEST_NODE_IP", value="请求方节点IP", required=false, dataType="String", paramType="header"),
		@ApiImplicitParam(name="Accept", value="接收属性", required=true, dataType="String", paramType="header", defaultValue="application/json"),
		@ApiImplicitParam(name="Accept-Charset", value="接收字符集", required=true, dataType="String", paramType="header", defaultValue="utf-8"),
		@ApiImplicitParam(name="Content-Type", value="内容类型", required=true, dataType="String", paramType="header", defaultValue="application/json; charset=UTF-8")
	})
	@RequestMapping(value="/cust/{customerCd}/image", method=RequestMethod.GET, produces="application/json; charset=UTF-8")
	public Result<List<CustImageVo>> searchImage(@PathVariable String customerCd, String imageTypeCd, @RequestHeader HttpHeaders headers){
		Result<List<CustImageVo>> result = customerImageService.searchImage(customerCd, imageTypeCd);
		return result;
	}
	

	/**
	 * @Title: registerCustomer
	 * @Description: 客户照片信息更新
	 * @author 李洋  liyang
	 * @data 2018年6月22日 上午10:46:21
	 * @return Result<String>
	 */
	@ApiOperation(value="客户照片信息更新", notes="客户照片信息更新")
	@ApiImplicitParams({
		@ApiImplicitParam(name="customerCd", value="客户编码", required=true, dataType="String", paramType="path"),
		@ApiImplicitParam(name="TK_BUSINESS_SERIALID", value="交易流水", required=false, dataType="String", paramType="header"),
		@ApiImplicitParam(name="TK_REQUEST_SYS_CODE", value="请求方系统编码", required=false, dataType="String", paramType="header"),
		@ApiImplicitParam(name="TK_REQUEST_NODULE_CODE", value="请求方模块编码", required=false, dataType="String", paramType="header"),
		@ApiImplicitParam(name="TK_REQUEST_NODE_IP", value="请求方节点IP", required=false, dataType="String", paramType="header"),
		@ApiImplicitParam(name="Accept", value="接收属性", required=true, dataType="String", paramType="header", defaultValue="application/json"),
		@ApiImplicitParam(name="Accept-Charset", value="接收字符集", required=true, dataType="String", paramType="header", defaultValue="utf-8"),
		@ApiImplicitParam(name="Content-Type", value="内容类型", required=true, dataType="String", paramType="header", defaultValue="application/json; charset=UTF-8")
	})
	@RequestMapping(value="/cust/{customerCd}/image", method=RequestMethod.POST)
	public Result<String> updateCustomerImage(@PathVariable String customerCd, @RequestBody CustImageVo custImageVo, HttpServletRequest request, @RequestHeader HttpHeaders headers){
		Result<String> result = customerImageService.updateCustomerImage(customerCd, custImageVo, request, headers);
		return result;
	}
	
	
	
}
