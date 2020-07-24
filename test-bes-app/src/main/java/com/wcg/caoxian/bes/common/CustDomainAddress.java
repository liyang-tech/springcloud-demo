package com.wcg.caoxian.bes.common;

public class CustDomainAddress {

	public static final String CUSTPATH = "http://test-cust-domain";
	/**
	 * BES0220-根据查询条件获取客户信息--CUST0020-根据查询条件获取客户信息
	 */
	public static final String searchCustomers = CUSTPATH + "/custdomain/cust/v1/customers";
	/**
	 * BES0210-新增客户信息--CUST0010-新增客户信息
	 */
	public static final String registerCustomer = CUSTPATH + "/custdomain/cust/v1/customer";
	/**
	 * BES0230-客户信息修改--CUST0030-客户信息修改
	 */
	public static final String alterCustomer = CUSTPATH + "/custdomain/cust/v1/customer/{code}";
	/**
	 * BES0240-客户信息删除--CUST0040-客户信息删除
	 */
	public static final String deleteCustomer = CUSTPATH + "/custdomain/cust/v1/customer/{code}";
	/**
	 * BES0250-客户单记录查询--CUST0050-客户单记录查询
	 */
	public static final String searchOneCust = CUSTPATH + "/custdomain/cust/v1/customer/{code}";
	
}
