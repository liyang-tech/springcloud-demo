package com.wcg.caoxian.bes.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wcg.caoxian.bes.dao.PermissionMapper;
import com.wcg.caoxian.bes.dao.UserMapper;
import com.wcg.caoxian.bes.dao.UserRoleMapper;
import com.wcg.caoxian.bes.model.User;
import com.wcg.caoxian.bes.model.UserRole;
import com.wcg.caoxian.bes.vo.uservo.PermissionSearchOutParamVo;
import com.wcg.caoxian.bes.vo.uservo.RoleSearchOutParamVo;
import com.wcg.caoxian.bes.vo.uservo.UserLoginInputVo;
import com.wcg.caoxian.bes.vo.uservo.UserLoginOutVo;
import com.wcg.caoxian.bes.vo.uservo.UserReAndUpVo;
import com.wcg.caoxian.bes.vo.uservo.UserSearchInputParamVo;
import com.wcg.caoxian.bes.vo.uservo.UserSearchOutParamVo;
import com.wcg.caoxian.sdk.bean.PageBean;
import com.wcg.caoxian.sdk.bean.Result;
import com.wcg.caoxian.sdk.util.AESEncrypt;
import com.wcg.caoxian.sdk.util.MD5keyUtil;

@Service("userService")
public class UserService {

	private static final String KEY = "wenchanggeliyang";
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserRoleMapper userRoleMapper;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PermissionMapper permissionMapper;
	
	/**
	 * @Title: isDup
	 * @Description: USER0010-新增用户信息    校验登录名是否存在
	 * @author 李洋  liyang
	 * @data 2017年10月11日 下午3:34:05
	 * @return int
	 */
	public int isDup(UserReAndUpVo userReAndUpVo) {
		int count = userMapper.isDup(userReAndUpVo);
		return count;
	}
	
	/**
	 * @Title: registerUser
	 * @Description: USER0010-新增用户信息
	 * @author 李洋  liyang
	 * @data 2017年9月29日 上午10:31:24
	 * @return Result<String>
	 */
	@Transactional
	public Result<String> registerUser(UserReAndUpVo userReAndUpVo, HttpHeaders headers) {
		//密码MD5加密
		userReAndUpVo.setPassword(MD5keyUtil.getMD5Str(userReAndUpVo.getPassword()));
		//新建用户对象，copy Vo中信息
		User user = new User();
		BeanUtils.copyProperties(userReAndUpVo, user);
		String code = UUID.randomUUID().toString().replace("-", "");
		user.setCode(code);
		user.setCreatedTime(new Date());
		int count = userMapper.insertSelective(user);
		if(count != 1){
			return new Result<String>("1", "用户创建失败", null);
		}
		//绑定用户角色关系
		if(userReAndUpVo.getRoleCds() != null && !"".equals(userReAndUpVo.getRoleCds())){
			UserRole userRole = new UserRole();
			userRole.setUserCd(code);
			String[] roleCds = userReAndUpVo.getRoleCds().split(",");
			for (String roleCd : roleCds) {
				userRole.setRoleCd(roleCd);
				userRole.setCreatedTime(new Date());
				userRoleMapper.insertSelective(userRole);
			}
		}
		return new Result<String>("0", "用户创建成功", code);
	}

	/**
	 * @Title: serachByCode
	 * @Description: USER0020-用户单记录查询
	 * @author 李洋  liyang
	 * @data 2017年9月29日 上午10:53:17
	 * @return Result<UserSearchOutParamVo>
	 */
	public Result<UserSearchOutParamVo> serachByCode(String code) {
		//1.获取该用户信息(包含该用户下角色编码列表)
		UserSearchOutParamVo userSearchOutParamVo = userMapper.searchByCode(code);
		//2.获取角色列表(全)
		PageBean pageBean = new PageBean();
		List<RoleSearchOutParamVo> roleList = roleService.searchByCondition(null, pageBean);
		//3.遍历列表，重置选中标志
		if(userSearchOutParamVo.getRoleCds() != null && userSearchOutParamVo.getRoleCds().size() > 0
				&& roleList != null && roleList.size() > 0){
			List<String> roleCds = userSearchOutParamVo.getRoleCds();
			for (RoleSearchOutParamVo roleSearchOutParamVo : roleList) {//遍历角色列表
				for (String roleCd : roleCds) {//遍历用户下角色编码列表
					if(roleCd.equals(roleSearchOutParamVo.getCode())){
						roleSearchOutParamVo.setCheckFlag(true);
					}
				}
			}//遍历角色列表
		}
		//保存该用户角色列表是否选中信息
		userSearchOutParamVo.setRoleList(roleList);
		return new Result<UserSearchOutParamVo>("0", userSearchOutParamVo);
	}

	/**
	 * @Title: alterUser
	 * @Description: USER0030-修改用户信息
	 * @author 李洋  liyang
	 * @data 2017年10月11日 下午4:20:04
	 * @return Result<String>
	 */
	public Result<String> alterUser(UserReAndUpVo userReAndUpVo, HttpHeaders headers) {
		//1.修改用户信息
		if(userReAndUpVo.getPassword() != null && !"".equals(userReAndUpVo.getPassword())){
			userReAndUpVo.setPassword(MD5keyUtil.getMD5Str(userReAndUpVo.getPassword()));
		}
		User user = new User();
		BeanUtils.copyProperties(userReAndUpVo, user);
		user.setLastUpdatedTime(new Date());
		int count = userMapper.updateByPrimaryKeySelective(user);
		if(count != 1){
			return new Result<String>("1", "用户修改失败", null);
		}
		//2.删除用户角色关系，再重新保存
		//根据用户编码删除用户-角色关系
		userRoleMapper.deleteByUserCd(userReAndUpVo.getCode());
		if(userReAndUpVo.getRoleCds() != null && !"".equals(userReAndUpVo.getRoleCds())){
			//保存新的用户-角色关系
			UserRole userRole = new UserRole();
			userRole.setUserCd(userReAndUpVo.getCode());
			String[] roleCds = userReAndUpVo.getRoleCds().split(",");
			for (String roleCd : roleCds) {
				userRole.setRoleCd(roleCd);
				userRole.setCreatedTime(new Date());
				userRoleMapper.insertSelective(userRole);
			}
		}
		return new Result<String>("0", "用户修改成功", null);
	}

	/**
	 * @Title: deleteUser
	 * @Description: USER0040-用户信息删除
	 * @author 李洋  liyang
	 * @data 2017年10月11日 下午4:34:58
	 * @return int
	 */
	public int deleteUser(String code, HttpHeaders headers) {
		//1.根据用户编码删除用户-角色关系
		userRoleMapper.deleteByUserCd(code);
		//2.删除用户
		int count = userMapper.deleteByCode(code);
		return count;
	}

	/**
	 * @Title: searchByCondition
	 * @Description: USER0050-用户列表查询
	 * @author 李洋  liyang
	 * @data 2017年10月11日 下午6:03:23
	 * @return List<UserSearchOutParamVo>
	 */
	public List<UserSearchOutParamVo> searchByCondition(UserSearchInputParamVo userSearchInputParamVo,
			PageBean pageBean) {
		pageBean.init();
		List<UserSearchOutParamVo> userList = userMapper.searchByCondition(userSearchInputParamVo, pageBean);
		return userList;
	}

	/**
	 * @Title: searchCount
	 * @Description: USER0050-用户列表查询    记录数
	 * @author 李洋  liyang
	 * @data 2017年10月11日 下午6:03:32
	 * @return long
	 */
	public long searchCount(UserSearchInputParamVo userSearchInputParamVo) {
		Long count = userMapper.searchCount(userSearchInputParamVo);
		return count;
	}

	/**
	 * @Title: userLogin
	 * @Description: USER0060-用户登录
	 * @author 李洋  liyang
	 * @data 2017年10月12日 上午10:46:21
	 * @return Result<String>
	 */
	public Result<String> userLogin(UserLoginInputVo userLoginInputVo, HttpHeaders headers) {
		//解密
		String aesDecrypt = aesDecrypt("1", userLoginInputVo.getPassword());
		userLoginInputVo.setPassword(aesDecrypt);
		//根据登录名验证用户是否存在
		User user = userMapper.userLogin(userLoginInputVo);
		if(user == null){
			return new Result<String>("1", "用户不存在，请核对登录名", null);
		}
		//验证密码是否正确
		if(!MD5keyUtil.getMD5Str(userLoginInputVo.getPassword()).equals(user.getPassword())){
			return new Result<String>("1", "密码不正确，请核对密码", null);
		}
		return new Result<String>("0", "登录成功", user.getCode());
	}

	/**
	 * @Title: serachByUserCd
	 * @Description: USER0070-根据用户编码获取该用户菜单权限
	 * @author 李洋  liyang
	 * @data 2017年10月12日 下午1:32:51
	 * @return Result<UserLoginOutVo>
	 */
	public Result<UserLoginOutVo> serachByUserCd(String userCd) {
		UserLoginOutVo userLoginOutVo = new UserLoginOutVo();
		//1.保存用户信息
		User user = userMapper.selectByPrimaryKey(userCd);
		userLoginOutVo.setName(user.getName());
		//2.获取用户菜单列表
		List<PermissionSearchOutParamVo> permissionList = permissionMapper.serachByUserCd(userCd);
		userLoginOutVo.setPermissionList(permissionList);
		return new Result<UserLoginOutVo>("0", userLoginOutVo);
	}
	
	
	public String aesDecrypt(String secretType, String aesData){
		String aesDecrypt = "";
		switch (secretType) {
		case "1":   //1为AES加密方式
			try {
				aesDecrypt = AESEncrypt.aesDecrypt(aesData, KEY);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		default:
			break;
		}
		return aesDecrypt;
	}
}
