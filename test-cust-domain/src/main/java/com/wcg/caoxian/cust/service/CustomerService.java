package com.wcg.caoxian.cust.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.wcg.caoxian.cust.dao.CustomerMapper;
import com.wcg.caoxian.cust.model.Customer;
import com.wcg.caoxian.cust.vo.CustomerAlterVo;
import com.wcg.caoxian.cust.vo.CustomerInputParamVo;
import com.wcg.caoxian.cust.vo.CustomerOutParamVo;
import com.wcg.caoxian.cust.vo.CustomerRegisterVo;
import com.wcg.caoxian.sdk.bean.PageBean;
import com.wcg.caoxian.sdk.bean.Result;

@Service("customerService")
public class CustomerService {

	@Autowired
	private CustomerMapper customerMapper;
	
	/**
	 * @Title: searchByCondition
	 * @Description: CUST0020-根据查询条件获取客户信息列表
	 * @author 李洋  liyang
	 * @data 2017年9月8日 下午4:08:48
	 * @return List<Customer>
	 */
	public List<CustomerOutParamVo> searchByCondition(CustomerInputParamVo customerInputParamVo, PageBean pageBean) {
		pageBean.init();
		List<CustomerOutParamVo> custList = customerMapper.searchByCondition(customerInputParamVo, pageBean);
		return custList;
	}

	/**
	 * @Title: searchByCondition
	 * @Description: CUST0020-根据查询条件获取客户信息记录数
	 * @author 李洋  liyang
	 * @data 2017年9月8日 下午4:08:48
	 * @return List<Customer>
	 */
	public Long searchCount(CustomerInputParamVo customerInputParamVo) {
		Long count = customerMapper.searchCount(customerInputParamVo);
		return count;
	}
	
	/**
	 * @Title: register
	 * @Description: CUST0010-新增客户信息
	 * @author 李洋  liyang
	 * @data 2017年9月15日 下午6:16:25
	 * @return Result<String>
	 */
	public Result<String> register(CustomerRegisterVo customerRegisterVo, HttpHeaders headers) {
		//创建客户对象接收vo信息
		Customer customer = new Customer();
		BeanUtils.copyProperties(customerRegisterVo, customer);
		String code = UUID.randomUUID().toString().replace("-", "");
		customer.setCode(code);
		customer.setCreatedTime(new Date());
		//转换日期
		if(customerRegisterVo.getBirthday() != null && !"".equals(customerRegisterVo.getBirthday())){
			try {
				customer.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(customerRegisterVo.getBirthday()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		//新增客户信息
		int count = customerMapper.insertSelective(customer);
		if(count > 0){
			return new Result<String>("0", "客户新增成功", code);
		}else{
			return new Result<String>("1", "客户新增失败", null);
		}
	}
	
	/**
	 * @Title: alterCustomer
	 * @Description: CUST0030-客户信息修改
	 * @author 李洋  liyang
	 * @param code 
	 * @data 2017年9月18日 下午2:22:43
	 * @return Result<String>
	 */
	public Result<String> alterCustomer(String code, CustomerAlterVo customerAlterVo, HttpHeaders headers) {
		//创建客户对象接收vo信息
		customerAlterVo.setCode(code);
		Customer customer = new Customer();
		BeanUtils.copyProperties(customerAlterVo, customer);
		customer.setLastUpdatedTime(new Date());
		//转换日期
		if(customerAlterVo.getBirthday() != null && !"".equals(customerAlterVo.getBirthday())){
			try {
				customer.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(customerAlterVo.getBirthday()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		//修改客户信息，返回结果
		int count = customerMapper.updateByPrimaryKeySelective(customer);
		if(count > 0){
			return new Result<String>("0", null, "客户信息变更成功");
		}else{
			return new Result<String>("1", null, "客户信息变更失败");
		}
	}
	
	/**
	 * @Title: deleteCustomer
	 * @Description: CUST0040-客户信息删除
	 * @author 李洋  liyang
	 * @param headers 
	 * @data 2017年9月18日 下午3:13:23
	 * @return Result<String>
	 */
	public Result<String> deleteCustomer(String code, HttpHeaders headers) {
		int count = customerMapper.deleteCustomer(code);
		if(count > 0){
			return new Result<String>("0", null, "删除客户成功");
		}else{
			return new Result<String>("1", null, "删除客户失败");
		}
	}
	
	/**
	 * @Title: searchOneCust
	 * @Description: CUST0050-客户单记录查询
	 * @author 李洋  liyang
	 * @data 2017年9月19日 上午11:02:35
	 * @return CustomerOutParamVo
	 */
	public CustomerOutParamVo searchOneCust(String code) {
		CustomerOutParamVo customerOutParamVo = customerMapper.searchOneCust(code);
		return customerOutParamVo;
	}


}
