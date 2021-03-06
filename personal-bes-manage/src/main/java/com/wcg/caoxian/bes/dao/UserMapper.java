package com.wcg.caoxian.bes.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wcg.caoxian.bes.model.User;
import com.wcg.caoxian.bes.vo.uservo.UserLoginInputVo;
import com.wcg.caoxian.bes.vo.uservo.UserReAndUpVo;
import com.wcg.caoxian.bes.vo.uservo.UserSearchInputParamVo;
import com.wcg.caoxian.bes.vo.uservo.UserSearchOutParamVo;
import com.wcg.caoxian.sdk.bean.PageBean;

public interface UserMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ur_user
	 * @mbg.generated  Fri Sep 29 10:14:24 CST 2017
	 */
	int deleteByPrimaryKey(String code);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ur_user
	 * @mbg.generated  Fri Sep 29 10:14:24 CST 2017
	 */
	int insert(User record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ur_user
	 * @mbg.generated  Fri Sep 29 10:14:24 CST 2017
	 */
	int insertSelective(User record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ur_user
	 * @mbg.generated  Fri Sep 29 10:14:24 CST 2017
	 */
	User selectByPrimaryKey(String code);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ur_user
	 * @mbg.generated  Fri Sep 29 10:14:24 CST 2017
	 */
	int updateByPrimaryKeySelective(User record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ur_user
	 * @mbg.generated  Fri Sep 29 10:14:24 CST 2017
	 */
	int updateByPrimaryKey(User record);

	/**
	 * @Title: searchByCode
	 * @Description: USER0020-用户单记录查询
	 * @author 李洋  liyang
	 * @data 2017年10月11日 下午2:14:56
	 * @return UserSearchOutParamVo
	 */
	UserSearchOutParamVo searchByCode(String code);

	/**
	 * @Title: isDup
	 * @Description: 校验登录名是否存在
	 * @author 李洋  liyang
	 * @data 2017年10月11日 下午3:34:23
	 * @return int
	 */
	int isDup(UserReAndUpVo userReAndUpVo);

	/**
	 * @Title: deleteByCode
	 * @Description: USER0040-用户信息删除
	 * @author 李洋  liyang
	 * @data 2017年10月11日 下午4:36:17
	 * @return int
	 */
	int deleteByCode(String code);

	/**
	 * @Title: searchByCondition
	 * @Description: USER0050-用户列表查询
	 * @author 李洋  liyang
	 * @data 2017年10月11日 下午6:06:11
	 * @return List<UserSearchOutParamVo>
	 */
	List<UserSearchOutParamVo> searchByCondition(@Param("userSearchInputParamVo")UserSearchInputParamVo userSearchInputParamVo, @Param("pageBean")PageBean pageBean);

	/**
	 * @Title: searchCount
	 * @Description: USER0050-用户列表查询    记录数
	 * @author 李洋  liyang
	 * @data 2017年10月11日 下午6:07:01
	 * @return Long
	 */
	Long searchCount(UserSearchInputParamVo userSearchInputParamVo);

	/**
	 * @Title: userLogin
	 * @Description: USER0060-用户登录
	 * @author 李洋  liyang
	 * @data 2017年10月12日 上午10:47:59
	 * @return User
	 */
	User userLogin(UserLoginInputVo userLoginInputVo);
	
}