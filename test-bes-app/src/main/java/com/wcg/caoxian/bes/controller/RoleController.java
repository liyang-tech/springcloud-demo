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

import com.wcg.caoxian.bes.service.RoleService;
import com.wcg.caoxian.bes.vo.uservo.RoleReAndUpVo;
import com.wcg.caoxian.bes.vo.uservo.RoleSearchOutParamVo;
import com.wcg.caoxian.sdk.bean.PageBean;
import com.wcg.caoxian.sdk.bean.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/besapp")
@Api(description="角色管理应用服务的API接口")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	/**
	 * @Title: registerRole
	 * @Description: BES0160-角色(角色和权限关系绑定)新增
	 * @author 李洋  liyang
	 * @data 2017年10月13日 下午2:30:53
	 * @return Result<String>
	 */
	@ApiOperation(value = "BES0160-角色(角色和权限关系绑定)新增", notes = "角色(角色和权限关系绑定)新增")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "roleVo", value = "角色信息", required = true, dataType = "RoleReAndUpVo", paramType = "body"),
		@ApiImplicitParam(name = "TK_BUSINESS_SERIALID", value = "交易流水", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_SYS_CODE", value = "请求方系统编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODULE_CODE", value = "请求方模块编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODE_IP", value = "请求方节点IP", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "Accept", value = "接收属性", required = true, dataType = "String", paramType = "header", defaultValue = "application/json"),
		@ApiImplicitParam(name = "Accept-Charset", value = "接收字符集", required = true, dataType = "String", paramType = "header", defaultValue = "utf-8"),
		@ApiImplicitParam(name = "Content-Type", value = "内容类型", required = true, dataType = "String", paramType = "header", defaultValue = "application/json;charset=UTF-8")
	})
	@RequestMapping(value="/role", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public Result<String> registerRole(@RequestBody RoleReAndUpVo roleVo, @RequestHeader HttpHeaders headers){
		Result<String> result = roleService.registerRole(roleVo, headers);
		return result;
	}
	
	/**
	 * @Title: alterRole
	 * @Description: BES0170-角色(角色和权限关系绑定)修改
	 * @author 李洋  liyang
	 * @data 2017年10月13日 下午2:40:08
	 * @return Result<String>
	 */
	@ApiOperation(value = "BES0170-角色(角色和权限关系绑定)修改", notes = "角色(角色和权限关系绑定)修改")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "code", value = "角色编码", required = true, dataType = "String", paramType = "path"),
		@ApiImplicitParam(name = "roleVo", value = "角色信息", required = true, dataType = "RoleReAndUpVo", paramType = "body"),
		@ApiImplicitParam(name = "TK_BUSINESS_SERIALID", value = "交易流水", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_SYS_CODE", value = "请求方系统编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODULE_CODE", value = "请求方模块编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODE_IP", value = "请求方节点IP", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "Accept", value = "接收属性", required = true, dataType = "String", paramType = "header", defaultValue = "application/json"),
		@ApiImplicitParam(name = "Accept-Charset", value = "接收字符集", required = true, dataType = "String", paramType = "header", defaultValue = "utf-8"),
		@ApiImplicitParam(name = "Content-Type", value = "内容类型", required = true, dataType = "String", paramType = "header", defaultValue = "application/json;charset=UTF-8")
	})
	@RequestMapping(value="/role/{code}", method = RequestMethod.PUT, produces="application/json;charset=UTF-8")
	public Result<String> alterRole(@PathVariable String code, @RequestBody RoleReAndUpVo roleVo, @RequestHeader HttpHeaders headers){
		Result<String> result = roleService.alterRole(code, roleVo, headers);
		return result;
	}
	
	/**
	 * @Title: getByCode
	 * @Description: BES0180-角色单记录查询
	 * @author 李洋  liyang
	 * @data 2017年10月13日 下午2:42:12
	 * @return Result<RoleSearchOutParamVo>
	 */
	@ApiOperation(value = "BES0180-角色单记录查询", notes = "根据角色编码获取角色信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "code", value = "角色编码", required = true, dataType = "String", paramType = "path"),
		@ApiImplicitParam(name = "TK_BUSINESS_SERIALID", value = "交易流水", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_SYS_CODE", value = "请求方系统编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODULE_CODE", value = "请求方模块编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODE_IP", value = "请求方节点IP", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "Accept", value = "接收属性", required = true, dataType = "String", paramType = "header", defaultValue = "application/json"),
		@ApiImplicitParam(name = "Accept-Charset", value = "接收字符集", required = true, dataType = "String", paramType = "header", defaultValue = "utf-8"),
		@ApiImplicitParam(name = "Content-Type", value = "内容类型", required = true, dataType = "String", paramType = "header", defaultValue = "application/json;charset=UTF-8")
	})
	@RequestMapping(value="/role/{code}", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	public Result<RoleSearchOutParamVo> getByCode(@PathVariable String code, @RequestHeader HttpHeaders headers){
		Result<RoleSearchOutParamVo> result = roleService.getByCode(code, headers);
		return result;
	}
	
	/**
	 * @Title: deleteRole
	 * @Description: BES0190-角色删除
	 * @author 李洋  liyang
	 * @data 2017年10月13日 下午2:43:24
	 * @return Result<String>
	 */
	@ApiOperation(value = "BES0190-角色删除", notes = "根据角色编码删除角色信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "code", value = "角色编码", required = true, dataType = "String", paramType = "path"),
		@ApiImplicitParam(name = "TK_BUSINESS_SERIALID", value = "交易流水", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_SYS_CODE", value = "请求方系统编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODULE_CODE", value = "请求方模块编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODE_IP", value = "请求方节点IP", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "Accept", value = "接收属性", required = true, dataType = "String", paramType = "header", defaultValue = "application/json"),
		@ApiImplicitParam(name = "Accept-Charset", value = "接收字符集", required = true, dataType = "String", paramType = "header", defaultValue = "utf-8"),
		@ApiImplicitParam(name = "Content-Type", value = "内容类型", required = true, dataType = "String", paramType = "header", defaultValue = "application/json;charset=UTF-8")
	})
	@RequestMapping(value="/role/{code}", method = RequestMethod.DELETE, produces="application/json;charset=UTF-8")
	public Result<String> deleteRole(@PathVariable String code, @RequestHeader HttpHeaders headers){
		Result<String> result = roleService.deleteRole(code, headers);
		return result;
	}
	
	/**
	 * @Title: searchRoles
	 * @Description: BES0200-角色列表查询
	 * @author 李洋  liyang
	 * @data 2017年10月13日 下午2:45:50
	 * @return Result<List<RoleSearchOutParamVo>>
	 */
	@ApiOperation(value = "BES0200-角色列表查询", notes = "根据查询条件获取角色列表信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "name", value = "角色名称", required = false, dataType = "String", paramType = "query"),
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
	@RequestMapping(value="/roles", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	public Result<List<RoleSearchOutParamVo>> searchRoles(String name, PageBean pageBean, @RequestHeader HttpHeaders headers){
		Result<List<RoleSearchOutParamVo>> result = roleService.searchRoles(name, pageBean, headers);
		return result;
	}
	
	
}
