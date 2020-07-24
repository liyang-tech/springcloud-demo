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

import com.wcg.caoxian.bes.service.UserService;
import com.wcg.caoxian.bes.vo.uservo.UserLoginInputVo;
import com.wcg.caoxian.bes.vo.uservo.UserLoginOutVo;
import com.wcg.caoxian.bes.vo.uservo.UserReAndUpVo;
import com.wcg.caoxian.bes.vo.uservo.UserSearchInputParamVo;
import com.wcg.caoxian.bes.vo.uservo.UserSearchOutParamVo;
import com.wcg.caoxian.sdk.bean.PageBean;
import com.wcg.caoxian.sdk.bean.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/besapp")
@Api(description="用户管理应用服务的API接口")
public class UserController {

	@Autowired
	private UserService userService;
	
	/**
	 * @Title: registerUser
	 * @Description: BES0010-新增用户信息
	 * @author 李洋  liyang
	 * @data 2017年10月13日 上午10:18:43
	 * @return Result<String>
	 */
	@ApiOperation(value = "BES0010-新增用户信息", notes = "新增用户信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userReAndUpVo", value = "用户信息", required = true, dataType = "UserReAndUpVo", paramType = "body"),
		@ApiImplicitParam(name = "TK_BUSINESS_SERIALID", value = "交易流水", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_SYS_CODE", value = "请求方系统编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODULE_CODE", value = "请求方模块编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODE_IP", value = "请求方节点IP", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "Accept", value = "接收属性", required = true, dataType = "String", paramType = "header", defaultValue = "application/json"),
		@ApiImplicitParam(name = "Accept-Charset", value = "接收字符集", required = true, dataType = "String", paramType = "header", defaultValue = "utf-8"),
		@ApiImplicitParam(name = "Content-Type", value = "内容类型", required = true, dataType = "String", paramType = "header", defaultValue = "application/json;charset=UTF-8")
	})
	@RequestMapping(value="/user", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public Result<String> registerUser(@RequestBody UserReAndUpVo userReAndUpVo, @RequestHeader HttpHeaders headers){
		Result<String> result = userService.registerUser(userReAndUpVo, headers);
		return result;
	}
	
	/**
	 * @Title: serachByCode
	 * @Description: BES0020-用户单记录查询
	 * @author 李洋  liyang
	 * @data 2017年10月13日 上午10:32:40
	 * @return Result<UserSearchOutParamVo>
	 */
	@ApiOperation(value = "BES0020-用户单记录查询", notes = "根据用户编码获取用户详细信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "code", value = "用户编码", required = true, dataType = "String", paramType = "path"),
		@ApiImplicitParam(name = "TK_BUSINESS_SERIALID", value = "交易流水", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_SYS_CODE", value = "请求方系统编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODULE_CODE", value = "请求方模块编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODE_IP", value = "请求方节点IP", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "Accept", value = "接收属性", required = true, dataType = "String", paramType = "header", defaultValue = "application/json"),
		@ApiImplicitParam(name = "Accept-Charset", value = "接收字符集", required = true, dataType = "String", paramType = "header", defaultValue = "utf-8"),
		@ApiImplicitParam(name = "Content-Type", value = "内容类型", required = true, dataType = "String", paramType = "header", defaultValue = "application/json;charset=UTF-8")
	})
	@RequestMapping(value="/user/{code}", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	public Result<UserSearchOutParamVo> serachByCode(@PathVariable String code, @RequestHeader HttpHeaders headers){
		Result<UserSearchOutParamVo> result = userService.serachByCode(code, headers);
		return result;
	}
	
	/**
	 * @Title: alterUser
	 * @Description: BES0030-修改用户信息
	 * @author 李洋  liyang
	 * @data 2017年10月13日 上午10:35:27
	 * @return Result<String>
	 */
	@ApiOperation(value = "BES0030-修改用户信息", notes = "修改用户信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "code", value = "用户编码", required = true, dataType = "String", paramType = "path"),
		@ApiImplicitParam(name = "userReAndUpVo", value = "用户信息", required = true, dataType = "UserReAndUpVo", paramType = "body"),
		@ApiImplicitParam(name = "TK_BUSINESS_SERIALID", value = "交易流水", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_SYS_CODE", value = "请求方系统编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODULE_CODE", value = "请求方模块编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODE_IP", value = "请求方节点IP", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "Accept", value = "接收属性", required = true, dataType = "String", paramType = "header", defaultValue = "application/json"),
		@ApiImplicitParam(name = "Accept-Charset", value = "接收字符集", required = true, dataType = "String", paramType = "header", defaultValue = "utf-8"),
		@ApiImplicitParam(name = "Content-Type", value = "内容类型", required = true, dataType = "String", paramType = "header", defaultValue = "application/json;charset=UTF-8")
	})
	@RequestMapping(value="/user/{code}", method = RequestMethod.PUT, produces="application/json;charset=UTF-8")
	public Result<String> alterUser(@PathVariable String code, @RequestBody UserReAndUpVo userReAndUpVo, @RequestHeader HttpHeaders headers){
		Result<String> result = userService.alterUser(code, userReAndUpVo, headers);
		return result;
	}
	
	/**
	 * @Title: deleteUser
	 * @Description: BES0040-用户信息删除
	 * @author 李洋  liyang
	 * @data 2017年10月13日 上午10:40:22
	 * @return Result<String>
	 */
	@ApiOperation(value = "BES0040-用户信息删除", notes = "根据用户编码删除用户信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "code", value = "用户编码", required = true, dataType = "String", paramType = "path"),
		@ApiImplicitParam(name = "TK_BUSINESS_SERIALID", value = "交易流水", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_SYS_CODE", value = "请求方系统编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODULE_CODE", value = "请求方模块编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODE_IP", value = "请求方节点IP", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "Accept", value = "接收属性", required = true, dataType = "String", paramType = "header", defaultValue = "application/json"),
		@ApiImplicitParam(name = "Accept-Charset", value = "接收字符集", required = true, dataType = "String", paramType = "header", defaultValue = "utf-8"),
		@ApiImplicitParam(name = "Content-Type", value = "内容类型", required = true, dataType = "String", paramType = "header", defaultValue = "application/json;charset=UTF-8")
	})
	@RequestMapping(value="/user/{code}", method = RequestMethod.DELETE, produces="application/json;charset=UTF-8")
	public Result<String> deleteUser(@PathVariable String code, @RequestHeader HttpHeaders headers){
		Result<String> result = userService.deleteUser(code, headers);
		return result;
	}
	
	/**
	 * @Title: searchUsers
	 * @Description: BES0050-用户列表查询
	 * @author 李洋  liyang
	 * @data 2017年10月13日 上午10:46:49
	 * @return Result<List<UserSearchOutParamVo>>
	 */
	@ApiOperation(value = "BES0050-用户列表查询", notes = "根据查询条件获取用户列表信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "name", value = "用户名称", required = false, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "loginName", value = "用户登录名", required = false, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "mobile", value = "手机号", required = false, dataType = "String", paramType = "query"),
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
	@RequestMapping(value="/users", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	public Result<List<UserSearchOutParamVo>> searchUsers(UserSearchInputParamVo userSearchInputParamVo, PageBean pageBean, @RequestHeader HttpHeaders headers){
		Result<List<UserSearchOutParamVo>> result = userService.searchUsers(userSearchInputParamVo, pageBean, headers);
		return result;
	}
	
	/**
	 * @Title: userLogin
	 * @Description: BES0060-用户登录
	 * @author 李洋  liyang
	 * @data 2017年10月13日 上午10:49:06
	 * @return Result<String>
	 */
	@ApiOperation(value = "BES0060-用户登录", notes = "根据用户登录名和密码验证用户信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userLoginInputVo", value = "用户登录信息", required = true, dataType = "UserLoginInputVo", paramType = "body"),
		@ApiImplicitParam(name = "TK_BUSINESS_SERIALID", value = "交易流水", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_SYS_CODE", value = "请求方系统编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODULE_CODE", value = "请求方模块编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODE_IP", value = "请求方节点IP", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "Accept", value = "接收属性", required = true, dataType = "String", paramType = "header", defaultValue = "application/json"),
		@ApiImplicitParam(name = "Accept-Charset", value = "接收字符集", required = true, dataType = "String", paramType = "header", defaultValue = "utf-8"),
		@ApiImplicitParam(name = "Content-Type", value = "内容类型", required = true, dataType = "String", paramType = "header", defaultValue = "application/json;charset=UTF-8")
	})
	@RequestMapping(value="/user/login", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public Result<String> userLogin(@RequestBody UserLoginInputVo userLoginInputVo, @RequestHeader HttpHeaders headers){
		Result<String> result = userService.userLogin(userLoginInputVo, headers);
		return result;
	}
	
	/**
	 * @Title: serachByUserCd
	 * @Description: BES0070-根据用户编码获取该用户菜单权限
	 * @author 李洋  liyang
	 * @data 2017年10月13日 上午10:50:08
	 * @return Result<UserLoginOutVo>
	 */
	@ApiOperation(value = "BES0070-根据用户编码获取该用户菜单权限", notes = "根据用户编码获取该用户菜单权限")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userCd", value = "用户编码", required = true, dataType = "String", paramType = "path"),
		@ApiImplicitParam(name = "TK_BUSINESS_SERIALID", value = "交易流水", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_SYS_CODE", value = "请求方系统编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODULE_CODE", value = "请求方模块编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODE_IP", value = "请求方节点IP", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "Accept", value = "接收属性", required = true, dataType = "String", paramType = "header", defaultValue = "application/json"),
		@ApiImplicitParam(name = "Accept-Charset", value = "接收字符集", required = true, dataType = "String", paramType = "header", defaultValue = "utf-8"),
		@ApiImplicitParam(name = "Content-Type", value = "内容类型", required = true, dataType = "String", paramType = "header", defaultValue = "application/json;charset=UTF-8")
	})
	@RequestMapping(value="/user/{userCd}/permissions", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	public Result<UserLoginOutVo> serachByUserCd(@PathVariable String userCd, @RequestHeader HttpHeaders headers){
		Result<UserLoginOutVo> result = userService.serachByUserCd(userCd, headers);
		return result;
	}
	
}
