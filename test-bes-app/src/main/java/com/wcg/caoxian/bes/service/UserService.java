package com.wcg.caoxian.bes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wcg.caoxian.bes.common.UserDomainAddress;
import com.wcg.caoxian.bes.vo.uservo.UserLoginInputVo;
import com.wcg.caoxian.bes.vo.uservo.UserLoginOutVo;
import com.wcg.caoxian.bes.vo.uservo.UserReAndUpVo;
import com.wcg.caoxian.bes.vo.uservo.UserSearchInputParamVo;
import com.wcg.caoxian.bes.vo.uservo.UserSearchOutParamVo;
import com.wcg.caoxian.sdk.bean.PageBean;
import com.wcg.caoxian.sdk.bean.Result;
import com.wcg.caoxian.sdk.util.AESEncrypt;

@Service("userService")
public class UserService {
	
	private static final String KEY = "wenchanggeliyang";
	
	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * @Title: registerUser
	 * @Description: BES0010-新增用户信息
	 * @author 李洋  liyang
	 * @data 2017年10月13日 上午10:25:33
	 * @return Result<String>
	 */
	public Result<String> registerUser(UserReAndUpVo userReAndUpVo, HttpHeaders headers) {
		HttpEntity<UserReAndUpVo> requestEntity = new HttpEntity<>(userReAndUpVo, headers);
		ResponseEntity<Result<String>> response = restTemplate.exchange(UserDomainAddress.registerUser, 
				HttpMethod.POST, requestEntity, new ParameterizedTypeReference<Result<String>>() {
		});
		return response.getBody();
	}

	/**
	 * @Title: serachByCode
	 * @Description: BES0020-用户单记录查询
	 * @author 李洋  liyang
	 * @data 2017年10月13日 上午10:32:47
	 * @return Result<UserSearchOutParamVo>
	 */
	public Result<UserSearchOutParamVo> serachByCode(String code, HttpHeaders headers) {
		HttpEntity<UserSearchOutParamVo> requestEntity = new HttpEntity<>(null, headers);
		ResponseEntity<Result<UserSearchOutParamVo>> response = restTemplate.exchange(UserDomainAddress.serachByCode, 
				HttpMethod.GET, requestEntity, 
				new ParameterizedTypeReference<Result<UserSearchOutParamVo>>() {}, code);
		return response.getBody();
	}

	/**
	 * @Title: alterUser
	 * @Description: USER0030-修改用户信息
	 * @author 李洋  liyang
	 * @param code 
	 * @data 2017年10月11日 下午4:20:04
	 * @return Result<String>
	 */
	public Result<String> alterUser(String code, UserReAndUpVo userReAndUpVo, HttpHeaders headers) {
		HttpEntity<UserReAndUpVo> requestEntity = new HttpEntity<>(userReAndUpVo, headers);
		ResponseEntity<Result<String>> response = restTemplate.exchange(UserDomainAddress.alterUser, 
				HttpMethod.PUT, requestEntity, 
				new ParameterizedTypeReference<Result<String>>() {}, code);
		return response.getBody();
	}

	/**
	 * @Title: deleteUser
	 * @Description: BES0040-用户信息删除
	 * @author 李洋  liyang
	 * @data 2017年10月13日 上午10:40:32
	 * @return Result<String>
	 */
	public Result<String> deleteUser(String code, HttpHeaders headers) {
		HttpEntity<UserReAndUpVo> requestEntity = new HttpEntity<>(null, headers);
		ResponseEntity<Result<String>> response = restTemplate.exchange(UserDomainAddress.deleteUser, 
				HttpMethod.DELETE, requestEntity, 
				new ParameterizedTypeReference<Result<String>>() {}, code);
		return response.getBody();
	}

	/**
	 * @Title: searchUsers
	 * @Description: BES0050-用户列表查询
	 * @author 李洋  liyang
	 * @data 2017年10月13日 上午10:46:57
	 * @return Result<List<UserSearchOutParamVo>>
	 */
	public Result<List<UserSearchOutParamVo>> searchUsers(UserSearchInputParamVo userSearchInputParamVo, PageBean pageBean, HttpHeaders headers) {
		HttpEntity<UserSearchInputParamVo> requestEntity = new HttpEntity<>(userSearchInputParamVo, headers);
		ResponseEntity<Result<List<UserSearchOutParamVo>>> response = restTemplate.exchange(UserDomainAddress.searchUsers + userSearchInputParamVo.toURL(pageBean), 
				HttpMethod.GET, requestEntity, 
				new ParameterizedTypeReference<Result<List<UserSearchOutParamVo>>>() {});
		return response.getBody();
	}

	/**
	 * @Title: userLogin
	 * @Description: BES0060-用户登录
	 * @author 李洋  liyang
	 * @data 2017年10月13日 上午10:49:14
	 * @return Result<String>
	 */
	public Result<String> userLogin(UserLoginInputVo userLoginInputVo, HttpHeaders headers) {
		//解密
		String aesDecrypt = aesDecrypt("1", userLoginInputVo.getPassword());
		userLoginInputVo.setPassword(aesDecrypt);
		
		HttpEntity<UserLoginInputVo> requestEntity = new HttpEntity<>(userLoginInputVo, headers);
		ResponseEntity<Result<String>> response = restTemplate.exchange(UserDomainAddress.userLogin, 
				HttpMethod.POST, requestEntity, new ParameterizedTypeReference<Result<String>>() {
		});
		return response.getBody();
	}

	/**
	 * @Title: serachByUserCd
	 * @Description: BES0070-根据用户编码获取该用户菜单权限
	 * @author 李洋  liyang
	 * @data 2017年10月13日 上午10:50:29
	 * @return Result<UserLoginOutVo>
	 */
	public Result<UserLoginOutVo> serachByUserCd(String userCd, HttpHeaders headers) {
		HttpEntity<UserLoginOutVo> requestEntity = new HttpEntity<>(null, headers);
		ResponseEntity<Result<UserLoginOutVo>> response = restTemplate.exchange(UserDomainAddress.serachByUserCd, 
				HttpMethod.GET, requestEntity, 
				new ParameterizedTypeReference<Result<UserLoginOutVo>>() {}, userCd);
		return response.getBody();
	}
	
	/**
	 * @Title: aesDecrypt
	 * @Description: 密码解密
	 * @author 李洋  liyang
	 * @data 2017年12月8日 下午9:31:45
	 * @return String
	 */
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
