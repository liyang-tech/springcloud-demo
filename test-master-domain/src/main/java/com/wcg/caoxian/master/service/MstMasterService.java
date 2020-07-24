package com.wcg.caoxian.master.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.wcg.caoxian.master.dao.MasterMapper;
import com.wcg.caoxian.master.model.Master;
import com.wcg.caoxian.master.vo.MasterReAndUpVo;
import com.wcg.caoxian.master.vo.MasterSearchInputVo;
import com.wcg.caoxian.master.vo.MasterSearchOutVo;
import com.wcg.caoxian.sdk.bean.PageBean;
import com.wcg.caoxian.sdk.bean.Result;

@Service("mstMasterService")
public class MstMasterService {
	
	@Autowired
	private MasterMapper masterMapper;

	public List<Master> searchByCondition(Master master, PageBean page) {
		if(page == null){
			page = new PageBean();
		}
		if(master == null){
			master = new Master();
		}
		page.init();
		List<Master> masterList = masterMapper.searchByCondition(master, page);
		return masterList;
	}

	/**
	 * @Title: searchMasters
	 * @Description: 根据查询条件获取主数据列表
	 * @author 李洋  liyang
	 * @data 2017年12月13日 下午2:26:46
	 * @return List<MasterSearchOutVo>
	 */
	public List<MasterSearchOutVo> searchMasters(MasterSearchInputVo masterSearchInputVo, PageBean pageBean) {
		pageBean.init();
		List<MasterSearchOutVo> list = masterMapper.searchMasters(masterSearchInputVo, pageBean);
		return list;
	}

	/**
	 * @Title: searchCount
	 * @Description: 根据查询条件获取主数据列表记录数
	 * @author 李洋  liyang
	 * @data 2017年12月13日 下午2:35:31
	 * @return long
	 */
	public long searchCount(MasterSearchInputVo masterSearchInputVo) {
		long coung = masterMapper.searchCount(masterSearchInputVo);
		return coung;
	}

	/**
	 * @Title: isDup
	 * @Description:校验同数据对象下主数据名称是否存在  huo 同数据对象下主数据编码是否存在
	 * @author 李洋  liyang
	 * @data 2017年12月13日 下午3:42:21
	 * @return int
	 */
	public int isDup(String objectCd, String code, String name) {
		int count = masterMapper.isDup(objectCd, code, name);
		return count;
	}

	/**
	 * @Title: registerMaster
	 * @Description: 新增主数据信息
	 * @author 李洋  liyang
	 * @data 2017年12月13日 下午3:45:41
	 * @return Result<String>
	 */
	public Result<String> registerMaster(MasterReAndUpVo masterReAndUpVo, HttpHeaders headers) {
		Master master = new Master();
		BeanUtils.copyProperties(masterReAndUpVo, master);
		master.setCreatedTime(new Date());
		int count = masterMapper.insertSelective(master);
		if(count != 1){
			return new Result<String>("1", "主数据新增失败", null);
		}
		return new Result<String>("0", "主数据新增成功", null);
	}

	/**
	 * @Title: serachByCode
	 * @Description: MST0020-主数据单记录查询
	 * @author 李洋  liyang
	 * @data 2017年12月13日 下午4:23:26
	 * @return Result<MasterSearchOutVo>
	 */
	public Result<MasterSearchOutVo> serachByCode(String objectCd, String code) {
		MasterSearchOutVo masterSearchOutVo = masterMapper.serachByCode(objectCd, code);
		if(masterSearchOutVo == null){
			return new Result<MasterSearchOutVo>("1","未查询到主数据信息!");
		}
		return new Result<MasterSearchOutVo>("0", masterSearchOutVo);
	}

	/**
	 * @Title: isNmDup
	 * @Description: MST0030-主数据编辑 判断名称是否已存在
	 * @author 李洋  liyang
	 * @data 2017年12月13日 下午5:13:48
	 * @return int
	 */
	public int isNmDup(String objectCd, String code, String name) {
		int count = masterMapper.isNmDup(objectCd, code, name);
		return count;
	}

	/**
	 * @Title: alterMaster
	 * @Description: MST0030-主数据编辑
	 * @author 李洋  liyang
	 * @data 2017年12月13日 下午5:16:42
	 * @return Result<String>
	 */
	public int alterMaster(String objectCd, String code, MasterReAndUpVo masterReAndUpVo) {
		masterReAndUpVo.setObjectCd(objectCd);
		masterReAndUpVo.setCode(code);
		masterReAndUpVo.setLastUpdatedTime(new Date());
		int count = masterMapper.alterMaster(masterReAndUpVo);
		return count;
	}

	/**
	 * @Title: deleteMaster
	 * @Description: MST0040-主数据删除
	 * @author 李洋  liyang
	 * @data 2017年12月13日 下午5:34:58
	 * @return int
	 */
	public int deleteMaster(String objectCd, String code) {
		int count = masterMapper.deleteMaster(objectCd, code);
		return count;
	}

}
