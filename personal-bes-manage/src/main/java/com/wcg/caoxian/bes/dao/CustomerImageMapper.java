package com.wcg.caoxian.bes.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wcg.caoxian.bes.vo.custvo.CustomerImageVo;

public interface CustomerImageMapper {

	/**
	 * @Title: searchCustomerImage
	 * @Description: 根据客户编码和照片类型编码获取照片ID
	 * @author 李洋  liyang
	 * @data 2018年6月22日 下午4:12:18
	 * @return CustomerImageVo
	 */
	List<String> searchCustomerImage(@Param("customerCd")String customerCd, @Param("imageTypeCd")String imageTypeCd);

	/**
	 * @Title: saveCustomerImage
	 * @Description: 保存客户和照片关系
	 * @author 李洋  liyang
	 * @data 2018年6月25日 下午4:05:23
	 * @return int
	 */
	int saveCustomerImage(CustomerImageVo customerImageVo);

}
