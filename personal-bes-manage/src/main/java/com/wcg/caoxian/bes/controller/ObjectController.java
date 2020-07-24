package com.wcg.caoxian.bes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wcg.caoxian.bes.service.ObjectService;
import com.wcg.caoxian.bes.vo.mastervo.ObjectSearchOutVo;
import com.wcg.caoxian.sdk.bean.PageBean;
import com.wcg.caoxian.sdk.bean.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/tkrsbes")
@Api(description = "数据对象管理服务API接口")
public class ObjectController {
	

	@Autowired
	private ObjectService objectService;
	
	/**
	 * @Title: searchByCondition
	 * @Description: 根据查询条件获取数据对象列表
	 * @author 李洋  liyang
	 * @data 2017年12月14日 下午1:51:28
	 * @return Result<List<ObjectSearchOutVo>>
	 */
	@ApiOperation(value = "根据查询条件获取数据对象列表", notes = "根据查询条件获取数据对象列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "keyword", value = "数据对象名称或拼音简码", required = false, dataType = "String", paramType = "query"),
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
	@RequestMapping(value="/object/objects", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public Result<List<ObjectSearchOutVo>> searchByCondition(String keyword, PageBean pageBean, @RequestHeader HttpHeaders hearders){
		List<ObjectSearchOutVo> objectList = objectService.searchByCondition(keyword, pageBean);
		Result<List<ObjectSearchOutVo>> result = new Result<List<ObjectSearchOutVo>>("0", objectList);
		//封装分页信息
		if(pageBean.getNumber() != null && pageBean.getSize() != null && pageBean.getNumber() != 0 && pageBean.getSize() != 0){
			long count = objectService.searchCount(keyword);
			pageBean.setTotalElements(count);
			pageBean.setTotalPages();
			result.setPage(pageBean);
		}
		return result;
	}
	
	/**
	 * @Title: registerMaster
	 * @Description: 新增数据对象信息
	 * @author 李洋  liyang
	 * @data 2017年12月13日 下午3:46:06
	 * @return Result<String>
	 */
	/*@ApiOperation(value = "新增数据对象信息", notes = "新增数据对象信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "masterReAndUpVo", value = "数据对象信息", required = true, dataType = "MasterReAndUpVo", paramType = "body"),
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
		//1.校验同数据对象下数据对象名称是否存在
		int isNmDup = mstMasterService.isDup(masterReAndUpVo.getObjectCd(), null, masterReAndUpVo.getName());
		if(isNmDup > 0){
			ErrorHandler.reportError("MST0010_01");
		}
		//2.校验同数据对象下数据对象编码是否存在
		int isCodeDup = mstMasterService.isDup(masterReAndUpVo.getObjectCd(), masterReAndUpVo.getCode(), null);
		if(isCodeDup > 0){
			ErrorHandler.reportError("MST0010_02");
		}
		//3.注册用户
		Result<String> result = mstMasterService.registerMaster(masterReAndUpVo, headers);
		//4.初始化到redis中
		HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
		opsForHash.put("mst_"+masterReAndUpVo.getObjectCd(), masterReAndUpVo.getCode(), masterReAndUpVo.getName());
		return result;
	}
	
	*//**
	 * @Title: serachByCode
	 * @Description: MST0020-数据对象单记录查询
	 * @author 李洋  liyang
	 * @data 2017年12月13日 下午4:23:14
	 * @return Result<MasterSearchOutVo>
	 *//*
	@ApiOperation(value = "MST0020-数据对象单记录查询", notes = "根据对象编码和数据对象编码获取数据对象信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "objectCd", value = "对象编码", required = true, dataType = "String", paramType = "path"),
		@ApiImplicitParam(name = "code", value = "数据对象编码", required = true, dataType = "String", paramType = "path"),
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
		Result<MasterSearchOutVo> result = mstMasterService.serachByCode(objectCd, code);
		return result;
	}
	
	*//**
	 * @Title: alterMaster
	 * @Description: MST0030-数据对象编辑
	 * @author 李洋  liyang
	 * @data 2017年12月13日 下午5:14:06
	 * @return Result<String>
	 *//*
	@ApiOperation(value = "MST0030-数据对象编辑", notes = "根据对象编码和数据对象编码变更数据对象信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "objectCd", value = "对象编码", required = true, dataType = "String", paramType = "path"),
		@ApiImplicitParam(name = "code", value = "数据对象编码", required = true, dataType = "String", paramType = "path"),
		@ApiImplicitParam(name = "masterReAndUpVo", value = "数据对象信息", required = true, dataType = "MasterReAndUpVo", paramType = "body"),
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
		//1.校验同对象编码下名称是否已存在(排除自身)
		int isNmDup = mstMasterService.isNmDup(objectCd, code, masterReAndUpVo.getName());
		if(isNmDup > 0){
			ErrorHandler.reportError("MST0030_01");
		}
		int count = mstMasterService.alterMaster(objectCd, code, masterReAndUpVo);
		if(count > 0){
			//变更redis
			HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
			opsForHash.put("mst_"+objectCd, code, masterReAndUpVo.getName());
			return new Result<String>("0", "数据对象修改成功！", null);
		}else{
			return new Result<String>("1", "数据对象修改失败！", null);
		}
	}
	
	*//**
	 * @Title: deleteMaster
	 * @Description: MST0040-数据对象删除
	 * @author 李洋  liyang
	 * @data 2017年12月13日 下午5:34:40
	 * @return Result<String>
	 *//*
	@ApiOperation(value = "MST0040-数据对象删除", notes = "根据对象编码和数据对象编码删除数据对象信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "objectCd", value = "对象编码", required = true, dataType = "String", paramType = "path"),
		@ApiImplicitParam(name = "code", value = "数据对象编码", required = true, dataType = "String", paramType = "path"),
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
		int count = mstMasterService.deleteMaster(objectCd, code);
		if(count > 0){
			return new Result<String>("0", "数据对象删除成功！", null);
		}else{
			return new Result<String>("1", "数据对象删除失败！", null);
		}
	}*/
	
}
