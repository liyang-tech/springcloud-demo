package com.wcg.caoxian.master.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wcg.caoxian.master.model.Master;
import com.wcg.caoxian.master.vo.MasterReAndUpVo;
import com.wcg.caoxian.master.vo.MasterSearchInputVo;
import com.wcg.caoxian.master.vo.MasterSearchOutVo;
import com.wcg.caoxian.sdk.bean.PageBean;

public interface MasterMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mst_master
     *
     * @mbg.generated Thu Aug 31 17:08:16 CST 2017
     */
    int deleteByPrimaryKey(String code);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mst_master
     *
     * @mbg.generated Thu Aug 31 17:08:16 CST 2017
     */
    int insert(Master record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mst_master
     *
     * @mbg.generated Thu Aug 31 17:08:16 CST 2017
     */
    int insertSelective(Master record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mst_master
     *
     * @mbg.generated Thu Aug 31 17:08:16 CST 2017
     */
    Master selectByPrimaryKey(String code);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mst_master
     *
     * @mbg.generated Thu Aug 31 17:08:16 CST 2017
     */
    int updateByPrimaryKeySelective(Master record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mst_master
     *
     * @mbg.generated Thu Aug 31 17:08:16 CST 2017
     */
    int updateByPrimaryKey(Master record);

	List<Master> searchByCondition(@Param("master")Master master, @Param("page")PageBean page);

	/**
	 * @Title: searchMasters
	 * @Description: 根据查询条件获取主数据列表
	 * @author 李洋  liyang
	 * @data 2017年12月13日 下午2:37:45
	 * @return List<MasterSearchOutVo>
	 */
	List<MasterSearchOutVo> searchMasters(@Param("mstInVo")MasterSearchInputVo masterSearchInputVo, @Param("page")PageBean pageBean);

	/**
	 * @Title: searchCount
	 * @Description: 根据查询条件获取主数据列表记录数
	 * @author 李洋  liyang
	 * @data 2017年12月13日 下午2:43:32
	 * @return long
	 */
	long searchCount(MasterSearchInputVo masterSearchInputVo);

	/**
	 * @Title: isDup
	 * @Description: 校验同数据对象下主数据名称是否存在  huo 同数据对象下主数据编码是否存在
	 * @author 李洋  liyang
	 * @data 2017年12月13日 下午3:43:08
	 * @return int
	 */
	int isDup(@Param("objectCd")String objectCd, @Param("code")String code, @Param("name")String name);

	/**
	 * @Title: serachByCode
	 * @Description: MST0020-主数据单记录查询
	 * @author 李洋  liyang
	 * @data 2017年12月13日 下午4:26:59
	 * @return MasterSearchOutVo
	 */
	MasterSearchOutVo serachByCode(@Param("objectCd")String objectCd, @Param("code")String code);

	/**
	 * @Title: isNmDup
	 * @Description: MST0030-主数据编辑 判断名称是否已存在
	 * @author 李洋  liyang
	 * @data 2017年12月13日 下午5:15:22
	 * @return int
	 */
	int isNmDup(@Param("objectCd")String objectCd, @Param("code")String code, @Param("name")String name);

	/**
	 * @Title: alterMaster
	 * @Description: MST0030-主数据编辑
	 * @author 李洋  liyang
	 * @data 2017年12月13日 下午5:21:13
	 * @return int
	 */
	int alterMaster(MasterReAndUpVo masterReAndUpVo);

	/**
	 * @Title: deleteMaster
	 * @Description: MST0040-主数据删除
	 * @author 李洋  liyang
	 * @data 2017年12月13日 下午5:35:40
	 * @return int
	 */
	int deleteMaster(@Param("objectCd")String objectCd, @Param("code")String code);
}