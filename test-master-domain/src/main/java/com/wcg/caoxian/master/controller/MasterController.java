package com.wcg.caoxian.master.controller;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wcg.caoxian.master.model.Master;
import com.wcg.caoxian.master.service.MasterSolrService;
import com.wcg.caoxian.master.service.MstMasterService;
import com.wcg.caoxian.master.vo.MasterReAndUpVo;
import com.wcg.caoxian.master.vo.MasterSearchInputVo;
import com.wcg.caoxian.master.vo.MasterSearchOutVo;
import com.wcg.caoxian.master.vo.MasterSolrVo;
import com.wcg.caoxian.sdk.bean.MasterBean;
import com.wcg.caoxian.sdk.bean.PageBean;
import com.wcg.caoxian.sdk.bean.Result;
import com.wcg.caoxian.sdk.cache.MasterService;
import com.wcg.caoxian.sdk.exception.ErrorHandler;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/mst")
@Api(description = "主数据管理增删改查领域服务")
public class MasterController {
	

	@Autowired
	private MstMasterService mstMasterService;
	
	@Autowired
	private MasterService masterService;
	
	@Resource
	private MasterSolrService masterSolrService;
	
	@Autowired
	@Qualifier("masterRedisTemplate")
	private RedisTemplate<String, String> redisTemplate;
	
	/**
	 * @Title: inintRedis
	 * @Description: 初始化到redis中
	 * @author 李洋  liyang
	 * @data 2017年9月5日 上午11:14:06
	 * @return Result<?>
	 */
	@ApiOperation(value = "初始化到redis中", notes = "初始化到redis中")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "TK_BUSINESS_SERIALID", value = "交易流水", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_SYS_CODE", value = "请求方系统编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODULE_CODE", value = "请求方模块编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODE_IP", value = "请求方节点IP", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "Accept", value = "接收属性", required = true, dataType = "String", paramType = "header", defaultValue = "application/json"),
		@ApiImplicitParam(name = "Accept-Charset", value = "接收字符集", required = true, dataType = "String", paramType = "header", defaultValue = "utf-8"),
		@ApiImplicitParam(name = "Content-Type", value = "内容类型", required = true, dataType = "String", paramType = "header", defaultValue = "application/json; charset=UTF-8")
	})
	@RequestMapping(value="/redis/inintialization", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public Result<String> inintRedis(@RequestHeader HttpHeaders hearders){
		Set<String> keys = redisTemplate.keys("mst_*");
		redisTemplate.delete(keys);
		List<Master> masterList = mstMasterService.searchByCondition(null, null);
		for (Master master : masterList) {
			String objectCd = master.getObjectCd();
			HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
			opsForHash.put("mst_"+objectCd, master.getCode(), master.getName());
		}
		return new Result<String>("0", "初始化成功", null);
	}
	
	/**
	 * @Title: searchByObjectCd
	 * @Description: 根据对象编码获取redis主数据列表
	 * @author 李洋  liyang
	 * @data 2017年9月5日 上午11:20:49
	 * @return Result<List<MasterBean>>
	 */
	@ApiOperation(value = "根据对象编码获取redis主数据列表", notes = "根据对象编码获取redis主数据列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "objectCd", value = "对象编码", required = true, dataType = "String", paramType = "path"),
		@ApiImplicitParam(name = "TK_BUSINESS_SERIALID", value = "交易流水", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_SYS_CODE", value = "请求方系统编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODULE_CODE", value = "请求方模块编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODE_IP", value = "请求方节点IP", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "Accept", value = "接收属性", required = true, dataType = "String", paramType = "header", defaultValue = "application/json"),
		@ApiImplicitParam(name = "Accept-Charset", value = "接收字符集", required = true, dataType = "String", paramType = "header", defaultValue = "utf-8"),
		@ApiImplicitParam(name = "Content-Type", value = "内容类型", required = true, dataType = "String", paramType = "header", defaultValue = "application/json; charset=UTF-8")
	})
	@RequestMapping(value="/redis/masters/{objectCd}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public Result<List<MasterBean>> searchByObjectCd(@PathVariable String objectCd, @RequestHeader HttpHeaders hearders){
		List<MasterBean> masterList = masterService.searchByObjectCd(objectCd);
		return new Result<List<MasterBean>>("0", masterList);
	}
	
	/**
	 * @Title: searchNameByCode
	 * @Description: 根据对象编码/主数据编码获取redis主数据名称
	 * @author 李洋  liyang
	 * @data 2017年9月5日 上午11:23:50
	 * @return Result<String>
	 */
	@ApiOperation(value = "根据对象编码/主数据编码获取redis主数据名称", notes = "根据对象编码/主数据编码获取redis主数据名称")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "objectCd", value = "对象编码", required = true, dataType = "String", paramType = "path"),
		@ApiImplicitParam(name = "code", value = "主数据编码", required = true, dataType = "String", paramType = "path"),
		@ApiImplicitParam(name = "TK_BUSINESS_SERIALID", value = "交易流水", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_SYS_CODE", value = "请求方系统编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODULE_CODE", value = "请求方模块编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODE_IP", value = "请求方节点IP", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "Accept", value = "接收属性", required = true, dataType = "String", paramType = "header", defaultValue = "application/json"),
		@ApiImplicitParam(name = "Accept-Charset", value = "接收字符集", required = true, dataType = "String", paramType = "header", defaultValue = "utf-8"),
		@ApiImplicitParam(name = "Content-Type", value = "内容类型", required = true, dataType = "String", paramType = "header", defaultValue = "application/json; charset=UTF-8")
	})
	@RequestMapping(value="/redis/{objectCd}/master/{code}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public Result<String> searchNameByCode(@PathVariable String objectCd, @PathVariable String code, @RequestHeader HttpHeaders hearders){
		String masterName = masterService.getNameByObjectCdAndCode(objectCd, code);
		return new Result<String>("0", null, masterName);
	}
	
	/**
	 * @Title: searchSolrByName
	 * @Description: 根据主数据名称查询solr
	 * @author 李洋  liyang
	 * @data 2017年12月14日 上午9:02:36
	 * @return Result<List<MasterSolrVo>>
	 */
	@ApiOperation(value = "根据主数据名称查询solr", notes = "根据主数据名称查询solr")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "name", value = "主数据名称", required = true, dataType = "String", paramType = "path"),
		@ApiImplicitParam(name = "TK_BUSINESS_SERIALID", value = "交易流水", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_SYS_CODE", value = "请求方系统编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODULE_CODE", value = "请求方模块编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODE_IP", value = "请求方节点IP", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "Accept", value = "接收属性", required = true, dataType = "String", paramType = "header", defaultValue = "application/json"),
		@ApiImplicitParam(name = "Accept-Charset", value = "接收字符集", required = true, dataType = "String", paramType = "header", defaultValue = "utf-8"),
		@ApiImplicitParam(name = "Content-Type", value = "内容类型", required = true, dataType = "String", paramType = "header", defaultValue = "application/json; charset=UTF-8")
	})
	@RequestMapping(value="/mastersSolr/{name}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public Result<List<MasterSolrVo>> searchSolrByName(@PathVariable String name, @RequestHeader HttpHeaders hearders){
		List<MasterSolrVo> masterList = masterSolrService.findByNameContaining(name);
		return new Result<List<MasterSolrVo>>("0", masterList);
	}
	
	/**
	 * @Title: searchByCondition
	 * @Description: 根据查询条件获取主数据列表
	 * @author 李洋  liyang
	 * @data 2017年12月13日 下午2:26:58
	 * @return Result<List<MasterSearchOutVo>>
	 */
	@ApiOperation(value = "根据查询条件获取主数据列表", notes = "根据查询条件获取主数据列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "objectCd", value = "对象编码", required = false, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "code", value = "主数据编码", required = false, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "keyword", value = "主数据名称或拼音简码", required = false, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "number", value = "当前查询页", required = false, dataType = "Int", paramType = "query"),
		@ApiImplicitParam(name = "size", value = "每页记录数", required = false, dataType = "Int", paramType = "query"),
		@ApiImplicitParam(name = "TK_BUSINESS_SERIALID", value = "交易流水", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_SYS_CODE", value = "请求方系统编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODULE_CODE", value = "请求方模块编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODE_IP", value = "请求方节点IP", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "Accept", value = "接收属性", required = true, dataType = "String", paramType = "header", defaultValue = "application/json"),
		@ApiImplicitParam(name = "Accept-Charset", value = "接收字符集", required = true, dataType = "String", paramType = "header", defaultValue = "utf-8"),
		@ApiImplicitParam(name = "Content-Type", value = "内容类型", required = true, dataType = "String", paramType = "header", defaultValue = "application/json; charset=UTF-8")
	})
	@RequestMapping(value="/master/masters", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public Result<List<MasterSearchOutVo>> searchByCondition(MasterSearchInputVo masterSearchInputVo, PageBean pageBean, @RequestHeader HttpHeaders hearders){
		List<MasterSearchOutVo> masterList = mstMasterService.searchMasters(masterSearchInputVo, pageBean);
		Result<List<MasterSearchOutVo>> result = new Result<List<MasterSearchOutVo>>("0", masterList);
		//封装分页信息
		if(pageBean.getNumber() != null && pageBean.getSize() != null && pageBean.getNumber() != 0 && pageBean.getSize() != 0){
			long count = mstMasterService.searchCount(masterSearchInputVo);
			pageBean.setTotalElements(count);
			pageBean.setTotalPages();
			result.setPage(pageBean);
		}
		return result;
	}
	
	/**
	 * @Title: registerMaster
	 * @Description: 新增主数据信息
	 * @author 李洋  liyang
	 * @data 2017年12月13日 下午3:46:06
	 * @return Result<String>
	 */
	@ApiOperation(value = "新增主数据信息", notes = "新增主数据信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "masterReAndUpVo", value = "主数据信息", required = true, dataType = "MasterReAndUpVo", paramType = "body"),
		@ApiImplicitParam(name = "TK_BUSINESS_SERIALID", value = "交易流水", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_SYS_CODE", value = "请求方系统编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODULE_CODE", value = "请求方模块编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODE_IP", value = "请求方节点IP", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "Accept", value = "接收属性", required = true, dataType = "String", paramType = "header", defaultValue = "application/json"),
		@ApiImplicitParam(name = "Accept-Charset", value = "接收字符集", required = true, dataType = "String", paramType = "header", defaultValue = "utf-8"),
		@ApiImplicitParam(name = "Content-Type", value = "内容类型", required = true, dataType = "String", paramType = "header", defaultValue = "application/json;charset=UTF-8")
	})
	@RequestMapping(value="/master", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public Result<String> registerMaster(@RequestBody MasterReAndUpVo masterReAndUpVo, @RequestHeader HttpHeaders headers){
		//1.校验同数据对象下主数据名称是否存在
		int isNmDup = mstMasterService.isDup(masterReAndUpVo.getObjectCd(), null, masterReAndUpVo.getName());
		if(isNmDup > 0){
			ErrorHandler.reportError("MST0010_01");
		}
		//2.校验同数据对象下主数据编码是否存在
		int isCodeDup = mstMasterService.isDup(masterReAndUpVo.getObjectCd(), masterReAndUpVo.getCode(), null);
		if(isCodeDup > 0){
			ErrorHandler.reportError("MST0010_02");
		}
		//3.注册用户
		Result<String> result = mstMasterService.registerMaster(masterReAndUpVo, headers);
		//4.初始化到redis中
		HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
		opsForHash.put("mst_"+masterReAndUpVo.getObjectCd(), masterReAndUpVo.getCode(), masterReAndUpVo.getName());
		return result;
	}
	
	/**
	 * @Title: serachByCode
	 * @Description: MST0020-主数据单记录查询
	 * @author 李洋  liyang
	 * @data 2017年12月13日 下午4:23:14
	 * @return Result<MasterSearchOutVo>
	 */
	@ApiOperation(value = "MST0020-主数据单记录查询", notes = "根据对象编码和主数据编码获取主数据信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "objectCd", value = "对象编码", required = true, dataType = "String", paramType = "path"),
		@ApiImplicitParam(name = "code", value = "主数据编码", required = true, dataType = "String", paramType = "path"),
		@ApiImplicitParam(name = "TK_BUSINESS_SERIALID", value = "交易流水", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_SYS_CODE", value = "请求方系统编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODULE_CODE", value = "请求方模块编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODE_IP", value = "请求方节点IP", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "Accept", value = "接收属性", required = true, dataType = "String", paramType = "header", defaultValue = "application/json"),
		@ApiImplicitParam(name = "Accept-Charset", value = "接收字符集", required = true, dataType = "String", paramType = "header", defaultValue = "utf-8"),
		@ApiImplicitParam(name = "Content-Type", value = "内容类型", required = true, dataType = "String", paramType = "header", defaultValue = "application/json;charset=UTF-8")
	})
	@RequestMapping(value="{objectCd}/master/{code}", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	public Result<MasterSearchOutVo> serachByCode(@PathVariable String objectCd, @PathVariable String code, @RequestHeader HttpHeaders headers){
		Result<MasterSearchOutVo> result = mstMasterService.serachByCode(objectCd, code);
		return result;
	}
	
	/**
	 * @Title: alterMaster
	 * @Description: MST0030-主数据编辑
	 * @author 李洋  liyang
	 * @data 2017年12月13日 下午5:14:06
	 * @return Result<String>
	 */
	@ApiOperation(value = "MST0030-主数据编辑", notes = "根据对象编码和主数据编码变更主数据信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "objectCd", value = "对象编码", required = true, dataType = "String", paramType = "path"),
		@ApiImplicitParam(name = "code", value = "主数据编码", required = true, dataType = "String", paramType = "path"),
		@ApiImplicitParam(name = "masterReAndUpVo", value = "主数据信息", required = true, dataType = "MasterReAndUpVo", paramType = "body"),
		@ApiImplicitParam(name = "TK_BUSINESS_SERIALID", value = "交易流水", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_SYS_CODE", value = "请求方系统编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODULE_CODE", value = "请求方模块编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODE_IP", value = "请求方节点IP", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "Accept", value = "接收属性", required = true, dataType = "String", paramType = "header", defaultValue = "application/json"),
		@ApiImplicitParam(name = "Accept-Charset", value = "接收字符集", required = true, dataType = "String", paramType = "header", defaultValue = "utf-8"),
		@ApiImplicitParam(name = "Content-Type", value = "内容类型", required = true, dataType = "String", paramType = "header", defaultValue = "application/json;charset=UTF-8")
	})
	@RequestMapping(value="{objectCd}/master/{code}", method = RequestMethod.PUT, produces="application/json;charset=UTF-8")
	public Result<String> alterMaster(@PathVariable String objectCd, @PathVariable String code, @RequestBody MasterReAndUpVo masterReAndUpVo, @RequestHeader HttpHeaders headers){
		//1.校验同对象编码下名称是否已存在(排除自身)
		int isNmDup = mstMasterService.isNmDup(objectCd, code, masterReAndUpVo.getName());
		if(isNmDup > 0){
			ErrorHandler.reportError("MST0030_01");
		}
		int count = mstMasterService.alterMaster(objectCd, code, masterReAndUpVo);
		if(count > 0){
			//变更redis
			HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
			opsForHash.put("mst_"+objectCd, code, masterReAndUpVo.getName());
			return new Result<String>("0", "主数据修改成功！", null);
		}else{
			return new Result<String>("1", "主数据修改失败！", null);
		}
	}
	
	/**
	 * @Title: deleteMaster
	 * @Description: MST0040-主数据删除
	 * @author 李洋  liyang
	 * @data 2017年12月13日 下午5:34:40
	 * @return Result<String>
	 */
	@ApiOperation(value = "MST0040-主数据删除", notes = "根据对象编码和主数据编码删除主数据信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "objectCd", value = "对象编码", required = true, dataType = "String", paramType = "path"),
		@ApiImplicitParam(name = "code", value = "主数据编码", required = true, dataType = "String", paramType = "path"),
		@ApiImplicitParam(name = "TK_BUSINESS_SERIALID", value = "交易流水", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_SYS_CODE", value = "请求方系统编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODULE_CODE", value = "请求方模块编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODE_IP", value = "请求方节点IP", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "Accept", value = "接收属性", required = true, dataType = "String", paramType = "header", defaultValue = "application/json"),
		@ApiImplicitParam(name = "Accept-Charset", value = "接收字符集", required = true, dataType = "String", paramType = "header", defaultValue = "utf-8"),
		@ApiImplicitParam(name = "Content-Type", value = "内容类型", required = true, dataType = "String", paramType = "header", defaultValue = "application/json;charset=UTF-8")
	})
	@RequestMapping(value="{objectCd}/master/{code}", method = RequestMethod.DELETE, produces="application/json;charset=UTF-8")
	public Result<String> deleteMaster(@PathVariable String objectCd, @PathVariable String code, @RequestHeader HttpHeaders headers){
		int count = mstMasterService.deleteMaster(objectCd, code);
		if(count > 0){
			return new Result<String>("0", "主数据删除成功！", null);
		}else{
			return new Result<String>("1", "主数据删除失败！", null);
		}
	}
	
}
