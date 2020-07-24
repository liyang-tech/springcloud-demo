package com.wcg.caoxian.bes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wcg.caoxian.bes.service.MstMasterService;
import com.wcg.caoxian.bes.vo.mastervo.MasterReAndUpVo;
import com.wcg.caoxian.bes.vo.mastervo.MasterSearchInputVo;
import com.wcg.caoxian.bes.vo.mastervo.MasterSearchOutVo;
import com.wcg.caoxian.sdk.bean.MasterBean;
import com.wcg.caoxian.sdk.bean.PageBean;
import com.wcg.caoxian.sdk.bean.Result;
import com.wcg.caoxian.sdk.cache.MasterService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/besapp")
@Api(description = "主数据管理增删改查应用服务")
public class MasterController {

	@Autowired
	private MstMasterService mstMasterService;
	
	@Autowired
	private MasterService masterService;
	
	/**
	 * @Title: searchMastersByObjectCd
	 * @Description: 根据对象编码获取主数据列表
	 * @author 李洋  liyang
	 * @data 2017年9月14日 上午10:01:23
	 * @return Result<List<MasterBean>>
	 */
	@ApiOperation(value = "主数据列表查询", notes = "根据对象编码获取主数据列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "objectCd", value = "对象编码", required = true, dataType = "String", paramType = "path"),
		@ApiImplicitParam(name = "TK_BUSINESS_SERIALID", value = "交易流水", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_SYS_CODE", value = "请求方系统编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODULE_CODE", value = "请求方模块编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODE_IP", value = "请求方节点IP", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "Accept", value = "接收属性", required = true, dataType = "String", paramType = "header", defaultValue = "application/json"),
		@ApiImplicitParam(name = "Accept-Charset", value = "接收字符集", required = true, dataType = "String", paramType = "header", defaultValue = "utf-8"),
		@ApiImplicitParam(name = "Content-Type", value = "内容类型", required = true, dataType = "String", paramType = "header", defaultValue = "application/json; charset=UTF-8")
	})
	@RequestMapping(value="/master/{objectCd}/masters", method = RequestMethod.GET, produces="application/json; charset=UTF-8")
	public Result<List<MasterBean>> searchMastersByObjectCd(@PathVariable String objectCd, @RequestHeader HttpHeaders headers){
		List<MasterBean> resultList = masterService.searchByObjectCd(objectCd);
		return new Result<List<MasterBean>>("0", resultList);
	}
	
	
	/**
	 * @Title: searchByCondition
	 * @Description: 根据查询条件获取主数据列表
	 * @author 李洋  liyang
	 * @data 2017年12月13日 下午2:26:58
	 * @return Result<List<MasterSearchOutVo>>
	 */
	@ApiOperation(value = "根据查询条件获取主数据列表", notes = "根据查询条件获取主数据列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "objectCd", value = "对象编码", required = false, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "code", value = "主数据编码", required = false, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "keyword", value = "主数据名称或拼音简码", required = false, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "number", value = "当前查询页", required = false, dataType = "Int", paramType = "query"),
		@ApiImplicitParam(name = "size", value = "每页记录数", required = false, dataType = "Int", paramType = "query"),
		@ApiImplicitParam(name = "TK_BUSINESS_SERIALID", value = "交易流水", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_SYS_CODE", value = "请求方系统编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODULE_CODE", value = "请求方模块编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODE_IP", value = "请求方节点IP", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "Accept", value = "接收属性", required = true, dataType = "String", paramType = "header", defaultValue = "application/json"),
		@ApiImplicitParam(name = "Accept-Charset", value = "接收字符集", required = true, dataType = "String", paramType = "header", defaultValue = "utf-8"),
		@ApiImplicitParam(name = "Content-Type", value = "内容类型", required = true, dataType = "String", paramType = "header", defaultValue = "application/json; charset=UTF-8")
	})
	@RequestMapping(value="/master/masters", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public Result<List<MasterSearchOutVo>> searchByCondition(MasterSearchInputVo masterSearchInputVo, PageBean pageBean, @RequestHeader HttpHeaders hearders){
		Result<List<MasterSearchOutVo>> result = mstMasterService.searchMasters(masterSearchInputVo, pageBean, hearders);
		return result;
	}
	
	/**
	 * @Title: registerMaster
	 * @Description: 新增主数据信息
	 * @author 李洋  liyang
	 * @data 2017年12月13日 下午3:46:06
	 * @return Result<String>
	 */
	@ApiOperation(value = "新增主数据信息", notes = "新增主数据信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "masterReAndUpVo", value = "主数据信息", required = true, dataType = "MasterReAndUpVo", paramType = "body"),
		@ApiImplicitParam(name = "TK_BUSINESS_SERIALID", value = "交易流水", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_SYS_CODE", value = "请求方系统编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODULE_CODE", value = "请求方模块编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODE_IP", value = "请求方节点IP", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "Accept", value = "接收属性", required = true, dataType = "String", paramType = "header", defaultValue = "application/json"),
		@ApiImplicitParam(name = "Accept-Charset", value = "接收字符集", required = true, dataType = "String", paramType = "header", defaultValue = "utf-8"),
		@ApiImplicitParam(name = "Content-Type", value = "内容类型", required = true, dataType = "String", paramType = "header", defaultValue = "application/json;charset=UTF-8")
	})
	@RequestMapping(value="/master", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public Result<String> registerMaster(@RequestBody MasterReAndUpVo masterReAndUpVo, @RequestHeader HttpHeaders headers){
		Result<String> result = mstMasterService.registerMaster(masterReAndUpVo, headers);
		return result;
	}
	
	/**
	 * @Title: serachByCode
	 * @Description: BES0020-主数据单记录查询
	 * @author 李洋  liyang
	 * @data 2017年12月13日 下午4:23:14
	 * @return Result<MasterSearchOutVo>
	 */
	@ApiOperation(value = "BES0020-主数据单记录查询", notes = "根据对象编码和主数据编码获取主数据信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "objectCd", value = "对象编码", required = true, dataType = "String", paramType = "path"),
		@ApiImplicitParam(name = "code", value = "主数据编码", required = true, dataType = "String", paramType = "path"),
		@ApiImplicitParam(name = "TK_BUSINESS_SERIALID", value = "交易流水", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_SYS_CODE", value = "请求方系统编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODULE_CODE", value = "请求方模块编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODE_IP", value = "请求方节点IP", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "Accept", value = "接收属性", required = true, dataType = "String", paramType = "header", defaultValue = "application/json"),
		@ApiImplicitParam(name = "Accept-Charset", value = "接收字符集", required = true, dataType = "String", paramType = "header", defaultValue = "utf-8"),
		@ApiImplicitParam(name = "Content-Type", value = "内容类型", required = true, dataType = "String", paramType = "header", defaultValue = "application/json;charset=UTF-8")
	})
	@RequestMapping(value="{objectCd}/master/{code}", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	public Result<MasterSearchOutVo> serachByCode(@PathVariable String objectCd, @PathVariable String code, @RequestHeader HttpHeaders headers){
		Result<MasterSearchOutVo> result = mstMasterService.serachByCode(objectCd, code, headers);
		return result;
	}
	
	/**
	 * @Title: alterMaster
	 * @Description: BES0030-主数据编辑
	 * @author 李洋  liyang
	 * @data 2017年12月13日 下午5:14:06
	 * @return Result<String>
	 */
	@ApiOperation(value = "BES0030-主数据编辑", notes = "根据对象编码和主数据编码变更主数据信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "objectCd", value = "对象编码", required = true, dataType = "String", paramType = "path"),
		@ApiImplicitParam(name = "code", value = "主数据编码", required = true, dataType = "String", paramType = "path"),
		@ApiImplicitParam(name = "masterReAndUpVo", value = "主数据信息", required = true, dataType = "MasterReAndUpVo", paramType = "body"),
		@ApiImplicitParam(name = "TK_BUSINESS_SERIALID", value = "交易流水", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_SYS_CODE", value = "请求方系统编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODULE_CODE", value = "请求方模块编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODE_IP", value = "请求方节点IP", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "Accept", value = "接收属性", required = true, dataType = "String", paramType = "header", defaultValue = "application/json"),
		@ApiImplicitParam(name = "Accept-Charset", value = "接收字符集", required = true, dataType = "String", paramType = "header", defaultValue = "utf-8"),
		@ApiImplicitParam(name = "Content-Type", value = "内容类型", required = true, dataType = "String", paramType = "header", defaultValue = "application/json;charset=UTF-8")
	})
	@RequestMapping(value="{objectCd}/master/{code}", method = RequestMethod.PUT, produces="application/json;charset=UTF-8")
	public Result<String> alterMaster(@PathVariable String objectCd, @PathVariable String code, @RequestBody MasterReAndUpVo masterReAndUpVo, @RequestHeader HttpHeaders headers){
		Result<String> result = mstMasterService.alterMaster(objectCd, code, masterReAndUpVo, headers);
		return result;
	}
	
	/**
	 * @Title: deleteMaster
	 * @Description: BES0040-主数据删除
	 * @author 李洋  liyang
	 * @data 2017年12月13日 下午5:34:40
	 * @return Result<String>
	 */
	@ApiOperation(value = "BES0040-主数据删除", notes = "根据对象编码和主数据编码删除主数据信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "objectCd", value = "对象编码", required = true, dataType = "String", paramType = "path"),
		@ApiImplicitParam(name = "code", value = "主数据编码", required = true, dataType = "String", paramType = "path"),
		@ApiImplicitParam(name = "TK_BUSINESS_SERIALID", value = "交易流水", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_SYS_CODE", value = "请求方系统编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODULE_CODE", value = "请求方模块编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODE_IP", value = "请求方节点IP", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "Accept", value = "接收属性", required = true, dataType = "String", paramType = "header", defaultValue = "application/json"),
		@ApiImplicitParam(name = "Accept-Charset", value = "接收字符集", required = true, dataType = "String", paramType = "header", defaultValue = "utf-8"),
		@ApiImplicitParam(name = "Content-Type", value = "内容类型", required = true, dataType = "String", paramType = "header", defaultValue = "application/json;charset=UTF-8")
	})
	@RequestMapping(value="{objectCd}/master/{code}", method = RequestMethod.DELETE, produces="application/json;charset=UTF-8")
	public Result<String> deleteMaster(@PathVariable String objectCd, @PathVariable String code, @RequestHeader HttpHeaders headers){
		Result<String> result = mstMasterService.deleteMaster(objectCd, code, headers);
		return result;
	}
	
}
