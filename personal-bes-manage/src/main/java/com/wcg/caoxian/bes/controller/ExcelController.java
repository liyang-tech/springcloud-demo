package com.wcg.caoxian.bes.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wcg.caoxian.bes.service.ExcelService;
import com.wcg.caoxian.bes.vo.mastervo.ObjMstExcelVo;
import com.wcg.caoxian.sdk.bean.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/tkrsbes")
@Api(description = "Excel服务API接口")
public class ExcelController {

	@Autowired
	private ExcelService excelService;
	
	@ApiOperation(value = "导出对象和主数据", notes = "导出对象和主数据")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "objectCds", value = "对象编码串", required = true, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "TK_BUSINESS_SERIALID", value = "交易流水", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_SYS_CODE", value = "请求方系统编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODULE_CODE", value = "请求方模块编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODE_IP", value = "请求方节点IP", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "Accept", value = "接收属性", required = true, dataType = "String", paramType = "header", defaultValue = "application/json"),
		@ApiImplicitParam(name = "Accept-Charset", value = "接收字符集", required = true, dataType = "String", paramType = "header", defaultValue = "utf-8"),
		@ApiImplicitParam(name = "Content-Type", value = "内容类型", required = true, dataType = "String", paramType = "header", defaultValue = "application/json; charset=UTF-8")
	})
	@RequestMapping(value="/object/masters", method = RequestMethod.GET, produces="application/json; charset=UTF-8")
	public void exportObjectMasters(String objectCds, HttpServletRequest request, HttpServletResponse response, @RequestHeader HttpHeaders headers){
		excelService.exportObjectMasters(objectCds, request, response, headers);
	}
	
	/**
	 * @Title: importObjectMasters
	 * @Description: 导入对象和主数据
	 * @author 李洋  liyang
	 * @data 2018年6月19日 下午5:34:59
	 * @return Result<ObjMstExcelVo>
	 */
	@ApiOperation(value = "导入对象和主数据预览", notes = "导入对象和主数据预览")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "TK_BUSINESS_SERIALID", value = "交易流水", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_SYS_CODE", value = "请求方系统编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODULE_CODE", value = "请求方模块编码", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "TK_REQUEST_NODE_IP", value = "请求方节点IP", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "Accept", value = "接收属性", required = true, dataType = "String", paramType = "header", defaultValue = "application/json"),
		@ApiImplicitParam(name = "Accept-Charset", value = "接收字符集", required = true, dataType = "String", paramType = "header", defaultValue = "utf-8"),
		@ApiImplicitParam(name = "Content-Type", value = "内容类型", required = true, dataType = "String", paramType = "header", defaultValue = "application/json; charset=UTF-8")
	})
	@RequestMapping(value="/object/masters", method = RequestMethod.POST, produces="application/json; charset=UTF-8")
	public Result<ObjMstExcelVo> importObjectMasters(@RequestParam("file")MultipartFile file, @RequestHeader HttpHeaders headers){
		try {
			return excelService.importObjectMasters(file, headers);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
