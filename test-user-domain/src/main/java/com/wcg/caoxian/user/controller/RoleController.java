package com.wcg.caoxian.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wcg.caoxian.sdk.bean.PageBean;
import com.wcg.caoxian.sdk.bean.Result;
import com.wcg.caoxian.sdk.exception.ErrorHandler;
import com.wcg.caoxian.user.service.RoleService;
import com.wcg.caoxian.user.vo.RoleSearchOutParamVo;
import com.wcg.caoxian.user.vo.RoleReAndUpVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/userdomain")
@Api(description="角色管理领域服务的API接口")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	/**
	 * @Title: registerRole
	 * @Description: USER0160-角色(角色和权限关系绑定)新增
	 * @author 李洋  liyang
	 * @data 2017年10月10日 下午5:36:04
	 * @return Result<String>
	 */
	@ApiOperation(value = "USER0160-角色(角色和权限关系绑定)新增", notes = "角色(角色和权限关系绑定)新增")
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
		//1.校验名称是否存在
		int isDup = roleService.isDup(roleVo);
		if(isDup > 0){
			ErrorHandler.reportError("USER0160_01");
		}
		//2.新增角色
		Result<String> result = roleService.registerRole(roleVo, headers);
		return result;
	}
	
	/**
	 * @Title: alterRole
	 * @Description: USER0170-角色(角色和权限关系绑定)修改
	 * @author 李洋  liyang
	 * @data 2017年10月11日 上午9:56:02
	 * @return Result<String>
	 */
	@ApiOperation(value = "USER0170-角色(角色和权限关系绑定)修改", notes = "角色(角色和权限关系绑定)修改")
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
		//1.校验名称是否存在
		roleVo.setCode(code);
		int isDup = roleService.isDup(roleVo);
		if(isDup > 0){
			ErrorHandler.reportError("USER0170_01");
		}
		//2.角色修改
		Result<String> result = roleService.alterRole(roleVo, headers);
		return result;
	}
	
	/**
	 * @Title: getByCode
	 * @Description: USER0180-角色单记录查询
	 * @author 李洋  liyang
	 * @data 2017年10月11日 上午10:36:09
	 * @return Result<RoleSearchOutParamVo>
	 */
	@ApiOperation(value = "USER0180-角色单记录查询", notes = "根据角色编码获取角色信息")
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
	 * @Description: USER0190-角色删除
	 * @author 李洋  liyang
	 * @data 2017年10月11日 下午1:32:49
	 * @return Result<String>
	 */
	@ApiOperation(value = "USER0190-角色删除", notes = "根据角色编码删除角色信息")
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
		//1.查询角色是否被用户引用
		int count = roleService.searchUserRoleCount(code);
		if(count > 0){
			ErrorHandler.reportError("USER0190_01");
		}
		//2.删除角色信息
		int amont = roleService.deleteRole(code, headers);
		if(amont == 1){
			return new Result<String>("0");
		}else{
			return new Result<String>("1");
		}
	}
	
	/**
	 * @Title: searchRoles
	 * @Description: USER0200-角色列表查询
	 * @author 李洋  liyang
	 * @data 2017年10月11日 下午1:49:03
	 * @return Result<List<RoleSearchOutParamVo>>
	 */
	@ApiOperation(value = "USER0200-角色列表查询", notes = "根据查询条件获取角色列表信息")
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
		//1.查询权限菜单列表
		List<RoleSearchOutParamVo> roleList = roleService.searchByCondition(name, pageBean);
		Result<List<RoleSearchOutParamVo>> result = new Result<List<RoleSearchOutParamVo>>("0", roleList);
		//2.封装分页信息
		if(pageBean.getNumber() != null && pageBean.getSize() != null && pageBean.getNumber() != 0 && pageBean.getSize() != 0){
			long count = roleService.searchCount(name);
			pageBean.setTotalElements(count);
			pageBean.setTotalPages();
			result.setPage(pageBean);
		}
		return result;
	}
	
	
}
