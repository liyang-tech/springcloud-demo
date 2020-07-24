package com.wcg.caoxian.bes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wcg.caoxian.bes.dao.MstObjectMapper;
import com.wcg.caoxian.bes.vo.mastervo.ObjectSearchOutVo;
import com.wcg.caoxian.sdk.bean.PageBean;

@Service("objectService")
public class ObjectService {

	@Autowired
	private MstObjectMapper mstObjectMapper;
	
	/**
	 * @Title: searchByCondition
	 * @Description: 根据查询条件获取数据对象列表
	 * @author 李洋  liyang
	 * @data 2017年12月14日 下午1:51:34
	 * @return List<ObjectSearchOutVo>
	 */
	public List<ObjectSearchOutVo> searchByCondition(String keyword, PageBean pageBean) {
		pageBean.init();
		List<ObjectSearchOutVo> objectList = mstObjectMapper.searchByCondition(keyword, pageBean);
		return objectList;
	}

	/**
	 * @Title: searchCount
	 * @Description: 根据查询条件获取数据对象列表记录数
	 * @author 李洋  liyang
	 * @data 2017年12月14日 下午1:57:29
	 * @return long
	 */
	public long searchCount(String keyword) {
		long count = mstObjectMapper.searchCount(keyword);
		return count;
	}

}
