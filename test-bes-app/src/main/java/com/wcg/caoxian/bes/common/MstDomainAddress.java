package com.wcg.caoxian.bes.common;

public class MstDomainAddress {

	public static final String MSTPATH = "http://test-master-domain";
	
	/**
	 * 根据查询条件获取主数据列表
	 */
	public static final String searchMasters = MSTPATH + "/mst/master/masters";
	
	/**
	 * 新增主数据信息
	 */
	public static final String registerMaster = MSTPATH + "/mst/master";
	
	/**
	 *  BES0020-主数据单记录查询
	 */
	public static final String serachByCode = MSTPATH + "/mst/{objectCd}/master/{code}";
	
	/**
	 * BES0030-主数据编辑
	 */
	public static final String alterMaster = MSTPATH + "/mst/{objectCd}/master/{code}";
	
	/**
	 * 主数据删除
	 */
	public static final String deleteMaster = MSTPATH + "/mst/{objectCd}/master/{code}";
	
	/**
	 * 数据对象列表查询
	 */
	public static final String searchObjects = MSTPATH + "/mst/object/objects";
	
}
