package com.wcg.caoxian.bes.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wcg.caoxian.bes.service.CustomerService;
import com.wcg.caoxian.bes.vo.custvo.CustomerAlterVo;
import com.wcg.caoxian.bes.vo.custvo.CustomerInputParamVo;
import com.wcg.caoxian.bes.vo.custvo.CustomerOutParamVo;
import com.wcg.caoxian.bes.vo.custvo.CustomerRegisterVo;
import com.wcg.caoxian.sdk.bean.PageBean;
import com.wcg.caoxian.sdk.bean.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/tkrsbes")
@Api(description = "客户管理服务API接口")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	/**
	 * @Title: registerCustomer
	 * @Description: CUST0010-新增客户信息
	 * @author 李洋  liyang
	 * @data 2017年9月15日 下午6:16:13
	 * @return Result<String>
	 */
	@ApiOperation(value = "CUST0010-新增客户信息", notes = "新增客户信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "customerRegisterVo", value = "客户信息", required = true, dataType = "CustomerRegisterVo", paramType = "body"),
		@ApiImplicitParam(name = "TK_BUSINESS_SERIALID", value = "交易流水", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_SYS_CODE", value = "请求方系统编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODULE_CODE", value = "请求方模块编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODE_IP", value = "请求方节点IP", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "Accept", value = "接收属性", required = true, dataType = "String", paramType = "header", defaultValue = "application/json"),
		@ApiImplicitParam(name = "Accept-Charset", value = "接收字符集", required = true, dataType = "String", paramType = "header", defaultValue = "utf-8"),
		@ApiImplicitParam(name = "Content-Type", value = "内容类型", required = true, dataType = "String", paramType = "header", defaultValue = "application/json; charset=UTF-8")
	})
	@RequestMapping(value="/cust/v1/customer", method = RequestMethod.POST, produces="application/json; charset=UTF-8")
	public Result<String> registerCustomer(@Valid @RequestBody CustomerRegisterVo customerRegisterVo, BindingResult  bindingResult, @RequestHeader HttpHeaders headers){
		Result<String> result = customerService.register(customerRegisterVo, headers);
		return result;
	}
	
	/**
	 * @Title: searchCustomer
	 * @Description: CUST0020-根据查询条件获取客户信息
	 * @author 李洋  liyang
	 * @data 2017年9月8日 下午4:10:58
	 * @return Result<List<CustomerOutParamVo>>
	 */
	@ApiOperation(value = "CUST0020-根据查询条件获取客户信息", notes = "根据查询条件获取客户信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "name", value = "客户名称", required = false, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "mobile", value = "手机号码", required = false, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "sexCd", value = "性别编码", required = false, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "marriedCd", value = "婚姻状态编码", required = false, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "certificationNo", value = "证件号码", required = false, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "number", value = "当前页码", required = false, dataType = "Int", paramType = "query"),
		@ApiImplicitParam(name = "size", value = "每页条数", required = false, dataType = "Int", paramType = "query"),
		@ApiImplicitParam(name = "TK_BUSINESS_SERIALID", value = "交易流水", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_SYS_CODE", value = "请求方系统编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODULE_CODE", value = "请求方模块编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODE_IP", value = "请求方节点IP", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "Accept", value = "接收属性", required = true, dataType = "String", paramType = "header", defaultValue = "application/json"),
		@ApiImplicitParam(name = "Accept-Charset", value = "接收字符集", required = true, dataType = "String", paramType = "header", defaultValue = "utf-8"),
		@ApiImplicitParam(name = "Content-Type", value = "内容类型", required = true, dataType = "String", paramType = "header", defaultValue = "application/json; charset=UTF-8")
	})
	@RequestMapping(value="/cust/v1/customers", method = RequestMethod.GET, produces="application/json; charset=UTF-8")
	public Result<List<CustomerOutParamVo>> searchCustomer(CustomerInputParamVo customerInputParamVo, PageBean pageBean, @RequestHeader HttpHeaders headers){
		List<CustomerOutParamVo> custList = customerService.searchByCondition(customerInputParamVo, pageBean);
		Result<List<CustomerOutParamVo>> result = new Result<List<CustomerOutParamVo>>("0", custList);
		if(pageBean.getNumber() != null && pageBean.getSize() != null && pageBean.getNumber() != 0 && pageBean.getSize() != 0){
			long count = customerService.searchCount(customerInputParamVo);
			pageBean.setTotalElements(count);
			pageBean.setTotalPages();
			result.setPage(pageBean);
		}
		return result;
	}
	
	/**
	 * @Title: alterCustomer
	 * @Description: CUST0030-客户信息修改
	 * @author 李洋  liyang
	 * @data 2017年9月18日 下午2:19:40
	 * @return Result<String>
	 */
	@ApiOperation(value = "CUST0030-客户信息修改", notes = "根据客户编码修改客户信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "customerAlterVo", value = "客户编辑信息", required = true, dataType = "CustomerAlterVo", paramType = "body"),
		@ApiImplicitParam(name = "TK_BUSINESS_SERIALID", value = "交易流水", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_SYS_CODE", value = "请求方系统编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODULE_CODE", value = "请求方模块编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODE_IP", value = "请求方节点IP", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "Accept", value = "接收属性", required = true, dataType = "String", paramType = "header", defaultValue = "application/json"),
		@ApiImplicitParam(name = "Accept-Charset", value = "接收字符集", required = true, dataType = "String", paramType = "header", defaultValue = "utf-8"),
		@ApiImplicitParam(name = "Content-Type", value = "内容类型", required = true, dataType = "String", paramType = "header", defaultValue = "application/json; charset=UTF-8")
	})
	@RequestMapping(value="/cust/v1/customer/{code}", method = RequestMethod.PUT, produces="application/json; charset=UTF-8")
	public Result<String> alterCustomer(@PathVariable String code, @RequestBody CustomerAlterVo customerAlterVo, @RequestHeader HttpHeaders headers){
		Result<String> result = customerService.alterCustomer(code, customerAlterVo, headers);
		return result;		
	}
	
	/**
	 * @Title: deleteCustomer
	 * @Description: CUST0040-客户信息删除
	 * @author 李洋  liyang
	 * @data 2017年9月18日 下午3:12:56
	 * @return Result<String>
	 */
	@ApiOperation(value = "CUST0040-客户信息删除", notes = "根据客户编码删除客户信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "code", value = "客户编码", required = true, dataType = "String", paramType = "path"),
		@ApiImplicitParam(name = "TK_BUSINESS_SERIALID", value = "交易流水", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_SYS_CODE", value = "请求方系统编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODULE_CODE", value = "请求方模块编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODE_IP", value = "请求方节点IP", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "Accept", value = "接收属性", required = true, dataType = "String", paramType = "header", defaultValue = "application/json"),
		@ApiImplicitParam(name = "Accept-Charset", value = "接收字符集", required = true, dataType = "String", paramType = "header", defaultValue = "utf-8"),
		@ApiImplicitParam(name = "Content-Type", value = "内容类型", required = true, dataType = "String", paramType = "header", defaultValue = "application/json; charset=UTF-8")
	})
	@RequestMapping(value="/cust/v1/customer/{code}", method = RequestMethod.DELETE, produces="application/json; charset=UTF-8")
	public Result<String> deleteCustomer(@PathVariable String code, @RequestHeader HttpHeaders headers){
		Result<String> result = customerService.deleteCustomer(code, headers);
		return result;
	}
	
	/**
	 * @Title: searchOneCust
	 * @Description: CUST0050-客户单记录查询
	 * @author 李洋  liyang
	 * @data 2017年9月19日 上午10:38:41
	 * @return Result<CustomerOutParamVo>
	 */
	@ApiOperation(value = "CUST0050-客户单记录查询", notes = "根据客户编码查询客户信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "code", value = "客户编码", required = true, dataType = "String", paramType = "path"),
		@ApiImplicitParam(name = "TK_BUSINESS_SERIALID", value = "交易流水", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_SYS_CODE", value = "请求方系统编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODULE_CODE", value = "请求方模块编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODE_IP", value = "请求方节点IP", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "Accept", value = "接收属性", required = true, dataType = "String", paramType = "header", defaultValue = "application/json"),
		@ApiImplicitParam(name = "Accept-Charset", value = "接收字符集", required = true, dataType = "String", paramType = "header", defaultValue = "utf-8"),
		@ApiImplicitParam(name = "Content-Type", value = "内容类型", required = true, dataType = "String", paramType = "header", defaultValue = "application/json; charset=UTF-8")
	})
	@RequestMapping(value="/cust/v1/customer/{code}", method = RequestMethod.GET, produces="application/json; charset=UTF-8")
	public Result<CustomerOutParamVo> searchOneCust(@PathVariable String code, @RequestHeader HttpHeaders headers){
		CustomerOutParamVo customerOutParamVo = customerService.searchOneCust(code);
		return new Result<CustomerOutParamVo>("0",customerOutParamVo);
	}
	
}
