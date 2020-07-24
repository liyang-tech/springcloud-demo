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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/besapp")
@Api(description="权限管理应用服务的API接口")
public class PermissionController {

	@Autowired
	private PermissionService permissionService;
	
	/**
	 * @Title: registerUser
	 * @Description: BES0110-权限信息新增
	 * @author 李洋  liyang
	 * @data 2017年9月29日 下午1:57:45
	 * @return Result<String>
	 */
	@ApiOperation(value = "BES0110-权限信息新增", notes = "权限信息新增")
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
		Result<String> result = permissionService.registerPermission(permissionVo, headers);
		return result;
	}
	
	/**
	 * @Title: alertPermission
	 * @Description: BES0120-权限信息编辑
	 * @author 李洋  liyang
	 * @data 2017年10月13日 下午1:58:35
	 * @return Result<String>
	 */
	@ApiOperation(value = "BES0120-权限信息编辑", notes = "根据权限编码修改权限信息")
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
		Result<String> result = permissionService.alertPermission(code, permissionVo, headers);
		return result;
	}
	
	/**
	 * @Title: searchPermissions
	 * @Description: BES0130-权限菜单列表查询
	 * @author 李洋  liyang
	 * @data 2017年10月13日 下午2:02:30
	 * @return Result<List<PermissionSearchOutParamVo>>
	 */
	@ApiOperation(value = "BES0130-权限菜单列表查询", notes = "根据查询条件获取权限菜单列表信息")
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
		Result<List<PermissionSearchOutParamVo>> result = permissionService.searchPermissions(name, pageBean, headers);
		return result;
	}
	
	/**
	 * @Title: searchByCode
	 * @Description: BES0140-权限菜单单记录查询
	 * @author 李洋  liyang
	 * @data 2017年10月13日 下午2:06:27
	 * @return Result<PermissionSearchOutParamVo>
	 */
	@ApiOperation(value = "BES0140-权限菜单单记录查询", notes = "根据权限编码获取权限菜单信息")
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
		Result<PermissionSearchOutParamVo> result = permissionService.searchByCode(code, headers);
		return result;
	}
	
	/**
	 * @Title: delete
	 * @Description: BES0150-权限菜单删除
	 * @author 李洋  liyang
	 * @data 2017年10月13日 下午2:09:32
	 * @return Result<String>
	 */
	@ApiOperation(value = "BES0150-权限菜单删除", notes = "根据权限编码删除权限菜单信息")
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
		Result<String> result = permissionService.deletePermission(code, headers);
		return result;
	}
	
}
