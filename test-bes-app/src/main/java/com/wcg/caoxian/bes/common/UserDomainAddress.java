package com.wcg.caoxian.bes.common;

public class UserDomainAddress {

	public static final String USERPATH = "http://test-user-domain";
/*---------华丽丽的分割线-----------用户管理调用领域服务路径   start--------华丽丽的分割线----------*/
	/**
	 * BES0010-新增用户信息
	 */
	public static final String registerUser = USERPATH + "/userdomain/user";
	/**
	 * BES0020-用户单记录查询
	 */
	public static final String serachByCode = USERPATH + "/userdomain/user/{code}";
	/**
	 * USER0030-修改用户信息
	 */
	public static final String alterUser = USERPATH + "/userdomain/user/{code}";
	/**
	 * BES0040-用户信息删除
	 */
	public static final String deleteUser = USERPATH + "/userdomain/user/{code}";
	/**
	 * BES0050-用户列表查询
	 */
	public static final String searchUsers = USERPATH + "/userdomain/users";
	/**
	 * BES0060-用户登录
	 */
	public static final String userLogin = USERPATH + "/userdomain/user/login";
	/**
	 * BES0070-根据用户编码获取该用户菜单权限
	 */
	public static final String serachByUserCd = USERPATH + "/userdomain/user/{userCd}/permissions";
/*---------华丽丽的分割线-------------------用户管理调用领域服务路径    end-------------------华丽丽的分割线-------------*/
/*---------华丽丽的分割线-------------------权限管理调用领域服务路径    start---------------华丽丽的分割线-------------*/
	/**
	 * BES0110-权限信息新增
	 */
	public static final String registerPermission = USERPATH + "/userdomain/permission";
	/**
	 * BES0120-权限信息编辑
	 */
	public static final String alertPermission = USERPATH + "/userdomain/permission/{code}";
	/**
	 * BES0130-权限菜单列表查询
	 */
	public static final String searchPermissions = USERPATH + "/userdomain/permissions";
	/**
	 * BES0140-权限菜单单记录查询
	 */
	public static final String serachPermissionByCode = USERPATH + "/userdomain/permission/{code}";
	/**
	 * BES0150-权限菜单删除
	 */
	public static final String deletePermission = USERPATH + "/userdomain/permission/{code}";
/*---------华丽丽的分割线-------------------权限管理调用领域服务路径    end---------------华丽丽的分割线-------------*/
/*---------华丽丽的分割线-------------------角色管理调用领域服务路径    start---------------华丽丽的分割线-------------*/
	/**
	 * BES0160-角色(角色和权限关系绑定)新增
	 */
	public static final String registerRole = USERPATH + "/userdomain/role";
	/**
	 * BES0170-角色(角色和权限关系绑定)修改
	 */
	public static final String alterRole = USERPATH + "/userdomain/role/{code}";
	/**
	 * BES0180-角色单记录查询
	 */
	public static final String getByCode = USERPATH + "/userdomain/role/{code}";
	/**
	 * BES0190-角色删除
	 */
	public static final String deleteRole = USERPATH + "/userdomain/role/{code}";
	/**
	 * BES0200-角色列表查询
	 */
	public static final String searchRoles = USERPATH + "/userdomain/roles";
/*---------华丽丽的分割线-------------------角色管理调用领域服务路径    end---------------华丽丽的分割线-------------*/
	
}
