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

import com.wcg.caoxian.bes.service.PermissionService;
import com.wcg.caoxian.bes.vo.uservo.PermissionReAndUpVo;
import com.wcg.caoxian.bes.vo.uservo.PermissionSearchOutParamVo;
import com.wcg.caoxian.sdk.bean.PageBean;
import com.wcg.caoxian.sdk.bean.Result;
import com.wcg.caoxian.sdk.exception.ErrorHandler;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/tkrsbes")
@Api(description="权限管理服务API接口")
public class PermissionController {

	@Autowired
	private PermissionService permissionService;
	
	/**
	 * @Title: registerUser
	 * @Description: USER0110-权限信息新增
	 * @author 李洋  liyang
	 * @data 2017年9月29日 下午1:57:45
	 * @return Result<String>
	 */
	@ApiOperation(value = "USER0110-权限信息新增", notes = "权限信息新增")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "permissionVo", value = "权限信息", required = true, dataType = "PermissionReAndUpVo", paramType = "body"),
		@ApiImplicitParam(name = "TK_BUSINESS_SERIALID", value = "交易流水", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_SYS_CODE", value = "请求方系统编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODULE_CODE", value = "请求方模块编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODE_IP", value = "请求方节点IP", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "Accept", value = "接收属性", required = true, dataType = "String", paramType = "header", defaultValue = "application/json"),
		@ApiImplicitParam(name = "Accept-Charset", value = "接收字符集", required = true, dataType = "String", paramType = "header", defaultValue = "utf-8"),
		@ApiImplicitParam(name = "Content-Type", value = "内容类型", required = true, dataType = "String", paramType = "header", defaultValue = "application/json;charset=UTF-8")
	})
	@RequestMapping(value="/permission", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public Result<String> registerPermission(@RequestBody PermissionReAndUpVo permissionVo, @RequestHeader HttpHeaders headers){
		//1.校验名称是否存在
		int isDup = permissionService.isDup(permissionVo);
		if(isDup > 0){
			ErrorHandler.reportError("USER0110_01");
		}
		//2.校验英文名称是否存在
		int amont = permissionService.isEnNameDup(permissionVo);
		if(amont > 0){
			ErrorHandler.reportError("USER0110_02");
		}
		//3.新增权限
		Result<String> result = permissionService.registerPermission(permissionVo, headers);
		return result;
	}
	
	/**
	 * @Title: alertUser
	 * @Description: USER0120-权限信息编辑
	 * @author 李洋  liyang
	 * @data 2017年9月29日 下午2:39:16
	 * @return Result<String>
	 */
	@ApiOperation(value = "USER0120-权限信息编辑", notes = "根据权限编码修改权限信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "code", value = "权限编码", required = true, dataType = "String", paramType = "path"),
		@ApiImplicitParam(name = "permissionVo", value = "权限信息", required = true, dataType = "PermissionReAndUpVo", paramType = "body"),
		@ApiImplicitParam(name = "TK_BUSINESS_SERIALID", value = "交易流水", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_SYS_CODE", value = "请求方系统编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODULE_CODE", value = "请求方模块编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODE_IP", value = "请求方节点IP", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "Accept", value = "接收属性", required = true, dataType = "String", paramType = "header", defaultValue = "application/json"),
		@ApiImplicitParam(name = "Accept-Charset", value = "接收字符集", required = true, dataType = "String", paramType = "header", defaultValue = "utf-8"),
		@ApiImplicitParam(name = "Content-Type", value = "内容类型", required = true, dataType = "String", paramType = "header", defaultValue = "application/json;charset=UTF-8")
	})
	@RequestMapping(value="/permission/{code}", method = RequestMethod.PUT, produces="application/json;charset=UTF-8")
	public Result<String> alertPermission(@PathVariable String code, @RequestBody PermissionReAndUpVo permissionVo, @RequestHeader HttpHeaders headers){
		//1.校验名称是否存在
		permissionVo.setCode(code);
		int isDup = permissionService.isDup(permissionVo);
		if(isDup > 0){
			ErrorHandler.reportError("USER0120_01");
		}
		//2.校验英文名称是否存在
		int amont = permissionService.isEnNameDup(permissionVo);
		if(amont > 0){
			ErrorHandler.reportError("USER0120_02");
		}
		//3.权限菜单编辑
		Result<String> result = permissionService.alertPermission(permissionVo, headers);
		return result;
	}
	
	/**
	 * @Title: searchPermissions
	 * @Description: USER0130-权限菜单列表查询
	 * @author 李洋  liyang
	 * @data 2017年9月29日 下午2:54:24
	 * @return Result<List<PermissionSearchOutParamVo>>
	 */
	@ApiOperation(value = "USER0130-权限菜单列表查询", notes = "根据查询条件获取权限菜单列表信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "name", value = "权限名称", required = false, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "number", value = "当前页码", required = false, dataType = "Int", paramType = "query"),
		@ApiImplicitParam(name = "size", value = "每页条数", required = false, dataType = "Int", paramType = "query"),
		@ApiImplicitParam(name = "TK_BUSINESS_SERIALID", value = "交易流水", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_SYS_CODE", value = "请求方系统编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODULE_CODE", value = "请求方模块编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODE_IP", value = "请求方节点IP", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "Accept", value = "接收属性", required = true, dataType = "String", paramType = "header", defaultValue = "application/json"),
		@ApiImplicitParam(name = "Accept-Charset", value = "接收字符集", required = true, dataType = "String", paramType = "header", defaultValue = "utf-8"),
		@ApiImplicitParam(name = "Content-Type", value = "内容类型", required = true, dataType = "String", paramType = "header", defaultValue = "application/json;charset=UTF-8")
	})
	@RequestMapping(value="/permissions", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	public Result<List<PermissionSearchOutParamVo>> searchPermissions(String name, PageBean pageBean, @RequestHeader HttpHeaders headers){
		//1.查询权限菜单列表
		List<PermissionSearchOutParamVo> permissionList = permissionService.searchByCondition(name, pageBean);
		Result<List<PermissionSearchOutParamVo>> result = new Result<List<PermissionSearchOutParamVo>>("0", permissionList);
		//2.封装分页信息
		if(pageBean.getNumber() != null && pageBean.getSize() != null && pageBean.getNumber() != 0 && pageBean.getSize() != 0){
			long count = permissionService.searchCount(name);
			pageBean.setTotalElements(count);
			pageBean.setTotalPages();
			result.setPage(pageBean);
		}
		return result;
	}
	
	/**
	 * @Title: searchByCode
	 * @Description: USER0140-权限菜单单记录查询
	 * @author 李洋  liyang
	 * @data 2017年9月29日 下午5:51:16
	 * @return Result<PermissionSearchOutParamVo>
	 */
	@ApiOperation(value = "USER0140-权限菜单单记录查询", notes = "根据权限编码获取权限菜单信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "code", value = "权限编码", required = true, dataType = "String", paramType = "path"),
		@ApiImplicitParam(name = "TK_BUSINESS_SERIALID", value = "交易流水", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_SYS_CODE", value = "请求方系统编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODULE_CODE", value = "请求方模块编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODE_IP", value = "请求方节点IP", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "Accept", value = "接收属性", required = true, dataType = "String", paramType = "header", defaultValue = "application/json"),
		@ApiImplicitParam(name = "Accept-Charset", value = "接收字符集", required = true, dataType = "String", paramType = "header", defaultValue = "utf-8"),
		@ApiImplicitParam(name = "Content-Type", value = "内容类型", required = true, dataType = "String", paramType = "header", defaultValue = "application/json;charset=UTF-8")
	})
	@RequestMapping(value="/permission/{code}", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	public Result<PermissionSearchOutParamVo> searchByCode(@PathVariable String code, @RequestHeader HttpHeaders headers){
		Result<PermissionSearchOutParamVo> result = permissionService.searchByCode(code);
		return result;
	}
	
	/**
	 * @Title: delete
	 * @Description: USER0150-权限菜单删除
	 * @author 李洋  liyang
	 * @data 2017年10月10日 下午2:22:15
	 * @return Result<String>
	 */
	@ApiOperation(value = "USER0150-权限菜单删除", notes = "根据权限编码删除权限菜单信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "code", value = "权限编码", required = true, dataType = "String", paramType = "path"),
		@ApiImplicitParam(name = "TK_BUSINESS_SERIALID", value = "交易流水", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_SYS_CODE", value = "请求方系统编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODULE_CODE", value = "请求方模块编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODE_IP", value = "请求方节点IP", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "Accept", value = "接收属性", required = true, dataType = "String", paramType = "header", defaultValue = "application/json"),
		@ApiImplicitParam(name = "Accept-Charset", value = "接收字符集", required = true, dataType = "String", paramType = "header", defaultValue = "utf-8"),
		@ApiImplicitParam(name = "Content-Type", value = "内容类型", required = true, dataType = "String", paramType = "header", defaultValue = "application/json;charset=UTF-8")
	})
	@RequestMapping(value="/permission/{code}", method = RequestMethod.DELETE, produces="application/json;charset=UTF-8")
	public Result<String> delete(@PathVariable String code, @RequestHeader HttpHeaders headers){
		//1.查询改权限菜单是否被角色引用
		int count = permissionService.searchRolePermissionCount(code);
		if(count > 0){
			ErrorHandler.reportError("USER0150_01");
		}
		//2.删除权限菜单
		int amont = permissionService.deletePermission(code);
		if(amont == 1){
			return new Result<String>("0");
		}else{
			return new Result<String>("1");
		}
	}
	
}
