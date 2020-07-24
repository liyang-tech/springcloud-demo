package com.wcg.caoxian.user.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wcg.caoxian.sdk.bean.PageBean;
import com.wcg.caoxian.sdk.bean.Result;
import com.wcg.caoxian.user.dao.RoleMapper;
import com.wcg.caoxian.user.dao.RolePermissionMapper;
import com.wcg.caoxian.user.dao.UserRoleMapper;
import com.wcg.caoxian.user.model.Role;
import com.wcg.caoxian.user.model.RolePermission;
import com.wcg.caoxian.user.vo.PermissionSearchOutParamVo;
import com.wcg.caoxian.user.vo.RoleSearchOutParamVo;
import com.wcg.caoxian.user.vo.RoleReAndUpVo;

@Service("roleService")
public class RoleService {

	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private UserRoleMapper userRoleMapper;
	
	@Autowired
	private RolePermissionMapper rolePermissionMapper;
	
	@Autowired
	private PermissionService permissionService;
	
	
	/**
	 * @Title: isDup
	 * @Description: USER0160-角色(角色和权限关系绑定)新增     校验名称是否存在
	 * @author 李洋  liyang
	 * @data 2017年10月10日 下午5:37:03
	 * @return int
	 */
	public int isDup(RoleReAndUpVo roleVo) {
		int count = roleMapper.searchNameCount(roleVo);
		return count;
	}

	/**
	 * @Title: registerRole
	 * @Description: USER0160-角色(角色和权限关系绑定)新增
	 * @author 李洋  liyang
	 * @data 2017年10月10日 下午5:37:11
	 * @return Result<String>
	 */
	@Transactional
	public Result<String> registerRole(RoleReAndUpVo roleVo, HttpHeaders headers) {
		//1.新增角色信息
		Role role = new Role();
		BeanUtils.copyProperties(roleVo, role);
		String code = UUID.randomUUID().toString().replace("-", "");
		role.setCode(code);
		role.setCreatedTime(new Date());
		int count = roleMapper.insertSelective(role);
		if(count == 0){
			return new Result<String>("1", "角色创建失败", null);
		}
		//2.如果权限菜单编码串不为空，绑定角色和权限菜单关系
		if(roleVo.getPermissionCds() != null && !"".equals(roleVo.getPermissionCds())){
			RolePermission rolePermission = new RolePermission();
			rolePermission.setRoleCd(code);
			//遍历权限菜单编码列表，新增角色和权限关系
			String[] permissionCds = roleVo.getPermissionCds().split(",");
			for (String permissionCd : permissionCds) {
				rolePermission.setPermissionCd(permissionCd);
				rolePermission.setCreatedTime(new Date());
				rolePermissionMapper.insertSelective(rolePermission);
			}
		}
		return new Result<String>("0", "角色创建成功", code);
	}

	/**
	 * @Title: alterRole
	 * @Description: USER0170-角色(角色和权限关系绑定)修改
	 * @author 李洋  liyang
	 * @data 2017年10月11日 上午10:03:38
	 * @return Result<String>
	 */
	@Transactional
	public Result<String> alterRole(RoleReAndUpVo roleVo, HttpHeaders headers) {
		//1.修改角色信息
		Role role = new Role();
		BeanUtils.copyProperties(roleVo, role);
		role.setLastUpdatedTime(new Date());
		int count = roleMapper.updateByPrimaryKeySelective(role);
		if(count == 0){
			return new Result<String>("1", "角色修改失败", null);
		}
		//2.如果权限菜单编码不为空，修改角色权限菜单关系(先删后增)
		//2_1.根据角色编码，删除角色-权限菜单 关系
		rolePermissionMapper.deleteByRoleCd(roleVo.getCode());
		if(roleVo.getPermissionCds() != null && !"".equals(roleVo.getPermissionCds())){
			//2_2.新增角色-权限菜单 关系
			RolePermission rolePermission = new RolePermission();
			rolePermission.setRoleCd(roleVo.getCode());
			String[] permissionCds = roleVo.getPermissionCds().split(",");
			for (String permissionCd : permissionCds) {
				rolePermission.setPermissionCd(permissionCd);
				rolePermission.setCreatedTime(new Date());
				rolePermissionMapper.insertSelective(rolePermission);
			}
		}
		return new Result<String>("0", "角色修改成功", null);
	}

	/**
	 * @Title: getByCode
	 * @Description: USER0180-角色单记录查询
	 * @author 李洋  liyang
	 * @data 2017年10月11日 上午10:36:01
	 * @return Result<RoleSearchOutParamVo>
	 */
	public Result<RoleSearchOutParamVo> getByCode(String code, HttpHeaders headers) {
		RoleSearchOutParamVo roleSearchOutParamVo = roleMapper.getByCode(code);
		//查询权限列表
		PageBean pageBean = new PageBean();
		List<PermissionSearchOutParamVo> permissionList = permissionService.searchByCondition(null, pageBean);
		//比对权限列表和该角色拥有权限编码列表，重置checkFlag标志
		if(roleSearchOutParamVo.getPermissionCds() != null && roleSearchOutParamVo.getPermissionCds().size() > 0 
				&& permissionList != null && permissionList.size() > 0){
			List<String> permissionCds = roleSearchOutParamVo.getPermissionCds();
			for (PermissionSearchOutParamVo permissionSearchOutParamVo : permissionList) {//遍历权限列表
				for (String perCd : permissionCds) {//遍历改角色下权限编码列表
					if(perCd.equals(permissionSearchOutParamVo.getCode())){
						permissionSearchOutParamVo.setCheckFlag(true);
					}
				}//遍历改角色下权限编码列表
			}//遍历权限列表
		}
		//保存该角色权限列表是否选中信息
		roleSearchOutParamVo.setPermissionList(permissionList);
		return new Result<RoleSearchOutParamVo>("0", roleSearchOutParamVo);
	}

	/**
	 * @Title: searchUserRoleCount
	 * @Description: USER0190-角色删除   查询角色是否被用户引用
	 * @author 李洋  liyang
	 * @data 2017年10月11日 下午1:33:18
	 * @return int
	 */
	public int searchUserRoleCount(String code) {
		int count = userRoleMapper.searchUserRoleCount(code);
		return count;
	}

	/**
	 * @Title: deleteRole
	 * @Description: USER0190-角色删除
	 * @author 李洋  liyang
	 * @data 2017年10月11日 下午1:33:25
	 * @return int
	 */
	@Transactional
	public int deleteRole(String code, HttpHeaders headers) {
		//1.删除角色-权限关系
		rolePermissionMapper.deleteByRoleCd(code);
		//2.删除角色
		int count = roleMapper.deleteByCode(code);
		return count;
	}

	/**
	 * @Title: searchByCondition
	 * @Description: USER0200-角色列表查询
	 * @author 李洋  liyang
	 * @data 2017年10月11日 下午1:48:43
	 * @return List<RoleSearchOutParamVo>
	 */
	public List<RoleSearchOutParamVo> searchByCondition(String name, PageBean pageBean) {
		pageBean.init();
		List<RoleSearchOutParamVo> roleList = roleMapper.searchByCondition(name, pageBean);
		return roleList;
	}

	/**
	 * @Title: searchCount
	 * @Description: USER0200-角色列表查    记录数
	 * @author 李洋  liyang
	 * @data 2017年10月11日 下午1:49:22
	 * @return long
	 */
	public long searchCount(String name) {
		Long amont = roleMapper.searchCount(name);
		return amont;
	}

	
}
