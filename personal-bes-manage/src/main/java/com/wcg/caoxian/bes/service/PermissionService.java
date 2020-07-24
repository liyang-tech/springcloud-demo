package com.wcg.caoxian.bes.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.wcg.caoxian.bes.dao.PermissionMapper;
import com.wcg.caoxian.bes.dao.RolePermissionMapper;
import com.wcg.caoxian.bes.model.Permission;
import com.wcg.caoxian.bes.vo.uservo.PermissionReAndUpVo;
import com.wcg.caoxian.bes.vo.uservo.PermissionSearchOutParamVo;
import com.wcg.caoxian.sdk.bean.PageBean;
import com.wcg.caoxian.sdk.bean.Result;

@Service("permissionService")
public class PermissionService {

	@Autowired
	private PermissionMapper permissionMapper;
	
	@Autowired
	private RolePermissionMapper rolePermissionMapper;

	/**
	 * @Title: isDup
	 * @Description: 校验名称是否已存在
	 * @author 李洋  liyang
	 * @data 2017年9月29日 下午1:52:43
	 * @return int
	 */
	public int isDup(PermissionReAndUpVo permissionVo) {
		int count = permissionMapper.isDup(permissionVo);
		return count;
	}
	
	/**
	 * @Title: isEnNameDup
	 * @Description: 校验英文名称是否已存在
	 * @author 李洋  liyang
	 * @data 2017年10月19日 下午2:14:51
	 * @return int
	 */
	public int isEnNameDup(PermissionReAndUpVo permissionVo) {
		int count = permissionMapper.isEnNameDup(permissionVo);
		return count;
	}

	/**
	 * @Title: registerUser
	 * @Description: USER0110-权限信息新增
	 * @author 李洋  liyang
	 * @data 2017年9月29日 下午1:58:08
	 * @return Result<String>
	 */
	public Result<String> registerPermission(PermissionReAndUpVo permissionVo, HttpHeaders headers) {
		//新建全下对象，copy vo中信息
		Permission permission = new Permission();
		BeanUtils.copyProperties(permissionVo, permission);
		String code = UUID.randomUUID().toString().replace("-", "");
		permission.setCode(code);
		permission.setCreatedTime(new Date());
		int count = permissionMapper.insertSelective(permission);
		if(count == 1){
			return new Result<String>("0", "权限菜单创建成功", code);
		}else{
			return new Result<String>("1", "权限菜单创建失败", null);
		}
	}

	/**
	 * @Title: alertUser
	 * @Description: USER0120-权限信息编辑
	 * @author 李洋  liyang
	 * @data 2017年9月29日 下午2:40:39
	 * @return Result<String>
	 */
	public Result<String> alertPermission(PermissionReAndUpVo permissionVo, HttpHeaders headers) {
		//新建全下对象，copy vo中信息
		Permission permission = new Permission();
		BeanUtils.copyProperties(permissionVo, permission);
		permission.setLastUpdatedTime(new Date());
		int count = permissionMapper.updateByPrimaryKeySelective(permission);
		if(count == 1){
			return new Result<String>("0", "权限菜单编辑成功", null);
		}else{
			return new Result<String>("1", "权限菜单编辑失败", null);
		}
	}

	/**
	 * @Title: searchByCondition
	 * @Description: USER0130-权限菜单列表查询
	 * @author 李洋  liyang
	 * @data 2017年9月29日 下午2:55:40
	 * @return List<PermissionSearchOutParamVo>
	 */
	public List<PermissionSearchOutParamVo> searchByCondition(String name, PageBean pageBean) {
		pageBean.init();
		List<PermissionSearchOutParamVo> permissionList = permissionMapper.searchByCondition(name, pageBean);
		return permissionList;
	}

	/**
	 * @Title: searchCount
	 * @Description: USER0130-权限菜单列表查询记录数
	 * @author 李洋  liyang
	 * @data 2017年9月29日 下午2:55:50
	 * @return long
	 */
	public long searchCount(String name) {
		return permissionMapper.searchCount(name);
	}

	/**
	 * @Title: searchByCode
	 * @Description: USER0140-权限菜单单记录查询
	 * @author 李洋  liyang
	 * @data 2017年9月29日 下午5:52:09
	 * @return Result<PermissionSearchOutParamVo>
	 */
	public Result<PermissionSearchOutParamVo> searchByCode(String code) {
		Permission permission = permissionMapper.selectByPrimaryKey(code);
		if(permission == null){
			return new Result<PermissionSearchOutParamVo>("1","没有查询到数据");
		}
		PermissionSearchOutParamVo permissionSearchOutParamVo = new PermissionSearchOutParamVo();
		BeanUtils.copyProperties(permission, permissionSearchOutParamVo);
		return new Result<PermissionSearchOutParamVo>("0", permissionSearchOutParamVo);
	}

	/**
	 * @Title: searchRolePermissionCount
	 * @Description: USER0150-权限菜单删除  查询改权限菜单是否被角色引用
	 * @author 李洋  liyang
	 * @data 2017年10月10日 下午2:21:59
	 * @return int
	 */
	public int searchRolePermissionCount(String code) {
		int count = rolePermissionMapper.searchRolePermissionCount(code);
		return count;
	}

	/**
	 * @Title: delete
	 * @Description: USER0150-权限菜单删除
	 * @author 李洋  liyang
	 * @data 2017年10月10日 下午2:22:03
	 * @return int
	 */
	public int deletePermission(String code) {
		int count = permissionMapper.deleteByCode(code);
		return count;
	}

}
