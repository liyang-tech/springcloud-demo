package com.wcg.caoxian.bes.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wcg.caoxian.bes.common.utils.ExcelListUtil;
import com.wcg.caoxian.bes.common.vo.ExcelColumnVo;
import com.wcg.caoxian.bes.common.vo.ExcelVo;
import com.wcg.caoxian.bes.common.vo.ExcelVoList;
import com.wcg.caoxian.bes.dao.MasterMapper;
import com.wcg.caoxian.bes.dao.MstObjectMapper;
import com.wcg.caoxian.bes.vo.mastervo.MasterExcelVo;
import com.wcg.caoxian.bes.vo.mastervo.ObjMstExcelVo;
import com.wcg.caoxian.bes.vo.mastervo.ObjectExcelVo;
import com.wcg.caoxian.sdk.bean.Result;
import com.wcg.caoxian.sdk.exception.ErrorHandler;

@Service("excelService")
public class ExcelService {

	@Autowired
	private MstObjectMapper mstObjectMapper;
	
	@Autowired
	private MasterMapper masterMapper;
	
	public void exportObjectMasters(String objectCds, HttpServletRequest request, HttpServletResponse response, HttpHeaders headers) {
		if(objectCds == null || "".equals(objectCds)){
			ErrorHandler.reportError("ffffff");
		}
		String[] objectCodes = objectCds.split(",");
		//根据对象编码获取对象及主数据信息
		List<ObjectExcelVo> objExcelList = mstObjectMapper.selectByCode(objectCodes);
		List<MasterExcelVo> mstExcelList = masterMapper.selectByObjectCd(objectCodes);
		//定义excelvo封装对象和主数据excel
		@SuppressWarnings("rawtypes")
		List<ExcelVo> excelList = new ArrayList<ExcelVo>();
		//数据对象封装excel
		ExcelVo<ObjectExcelVo> objExcel = setObjectExcel(objExcelList);
		excelList.add(objExcel);
		//主数据封装excel
		ExcelVo<MasterExcelVo> mstExcel = setMasterExcel(mstExcelList);
		excelList.add(mstExcel);
		//封装excel列表
		ExcelVoList excelVoList = new ExcelVoList();
		excelVoList.setExcelVoList(excelList);
		//时间戳
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmSS");
		String fileName="objmstData_"+sdf.format(new Date())+".xls";
		ExcelListUtil.exportExcel(excelVoList, request, response, fileName);
	}

	private ExcelVo<MasterExcelVo> setMasterExcel(List<MasterExcelVo> mstExcelList) {
		//主数据excel，定义
		ExcelVo<MasterExcelVo> excelVo = new ExcelVo<MasterExcelVo>();
		excelVo.setSheetName("主数据表");
		//主数据excel，列定义
		List<ExcelColumnVo> columns = new ArrayList<ExcelColumnVo>();
		//主数据excel，每一列定义，objectCd
		ExcelColumnVo excelColumn1 = new ExcelColumnVo();
		excelColumn1.setTitle("对象编码");
		excelColumn1.setField("objectCd");
		excelColumn1.setWidth(5000);
		columns.add(excelColumn1);
		//主数据excel，每一列定义，objectNm
		ExcelColumnVo excelColumn2 = new ExcelColumnVo();
		excelColumn2.setTitle("对象名称");
		excelColumn2.setField("objectNm");
		excelColumn2.setWidth(5000);
		columns.add(excelColumn2);
		//主数据excel，每一列定义，code
		ExcelColumnVo excelColumn3 = new ExcelColumnVo();
		excelColumn3.setTitle("主数据编码");
		excelColumn3.setField("code");
		excelColumn3.setWidth(5000);
		columns.add(excelColumn3);
		//主数据excel，每一列定义，name
		ExcelColumnVo excelColumn4 = new ExcelColumnVo();
		excelColumn4.setTitle("主数据名称");
		excelColumn4.setField("name");
		excelColumn4.setWidth(5000);
		columns.add(excelColumn4);
		//主数据excel，每一列定义，des
		ExcelColumnVo excelColumn5 = new ExcelColumnVo();
		excelColumn5.setTitle("主数据描述");
		excelColumn5.setField("des");
		excelColumn5.setWidth(5000);
		columns.add(excelColumn5);
		//主数据excel，每一列定义，spellNo
		ExcelColumnVo excelColumn6 = new ExcelColumnVo();
		excelColumn6.setTitle("主数据拼音简码");
		excelColumn6.setField("spellNo");
		excelColumn6.setWidth(5000);
		columns.add(excelColumn6);
		//主数据excel，每一列定义，sortNo
		ExcelColumnVo excelColumn7 = new ExcelColumnVo();
		excelColumn7.setTitle("主数据排序号");
		excelColumn7.setField("sortNo");
		excelColumn7.setWidth(5000);
		columns.add(excelColumn6);
		//封装主数据excel
		excelVo.setColumns(columns);
		excelVo.setData(mstExcelList);
		return excelVo;
	}

	private ExcelVo<ObjectExcelVo> setObjectExcel(List<ObjectExcelVo> objExcelList) {
		//数据对象excel，定义
		ExcelVo<ObjectExcelVo> excelVo = new ExcelVo<ObjectExcelVo>();
		excelVo.setSheetName("数据对象表");
		//数据对象excel，列定义
		List<ExcelColumnVo> columns = new ArrayList<ExcelColumnVo>();
		//数据对象excel，每一列定义，code
		ExcelColumnVo excelColumn1 = new ExcelColumnVo();
		excelColumn1.setTitle("对象编码");
		excelColumn1.setField("code");
		excelColumn1.setWidth(5000);
		columns.add(excelColumn1);
		//数据对象excel，每一列定义，name
		ExcelColumnVo excelColumn2 = new ExcelColumnVo();
		excelColumn2.setTitle("对象名称");
		excelColumn2.setField("name");
		excelColumn2.setWidth(5000);
		columns.add(excelColumn2);
		//数据对象excel，每一列定义，spellNo
		ExcelColumnVo excelColumn3 = new ExcelColumnVo();
		excelColumn3.setTitle("拼音简码");
		excelColumn3.setField("spellNo");
		excelColumn3.setWidth(5000);
		columns.add(excelColumn3);
		//封装数据对象excel
		excelVo.setColumns(columns);
		excelVo.setData(objExcelList);
		return excelVo;
	}

	/**
	 * @Title: importObjectMasters
	 * @Description: 导入对象和主数据
	 * @author 李洋  liyang
	 * @data 2018年6月19日 下午5:35:11
	 * @return Result<ObjMstExcelVo>
	 * @throws IOException 
	 */
	public Result<ObjMstExcelVo> importObjectMasters(MultipartFile file, HttpHeaders headers) throws IOException {
		InputStream inputStream = file.getInputStream();
		HSSFWorkbook wb = new HSSFWorkbook(inputStream);
		
		//解析数据对象
		List<ObjectExcelVo> objExcelList = new ArrayList<ObjectExcelVo>();
		analyObject(wb, objExcelList);
		
		//解析主数据
		List<MasterExcelVo> mstExcelList = new ArrayList<MasterExcelVo>();
		analyMaster(wb, mstExcelList);
		
		//封装接收数据对象列表、主数据列表的vo
		ObjMstExcelVo objMstExcelVo = new ObjMstExcelVo();
		objMstExcelVo.setObjectList(objExcelList);
		objMstExcelVo.setMasterList(mstExcelList);
		
		return new Result<ObjMstExcelVo>("0", objMstExcelVo);
	}

	private void analyObject(HSSFWorkbook wb, List<ObjectExcelVo> objExcelList) {
		HSSFSheet objSheet = wb.getSheetAt(0);
		int rowSize = objSheet.getLastRowNum() + 1;
		for (int j = 0; j < rowSize; j++) {
			if(j < 1){
				continue;
			}
			Row row = objSheet.getRow(j);
			if(row == null){
				continue;
			}
			ObjectExcelVo objectVo = new ObjectExcelVo();
			Cell cell0 = row.getCell(0);
			Cell cell1 = row.getCell(1);
			Cell cell2 = row.getCell(2);
			if(cell0 != null){
				cell0.setCellType(HSSFCell.CELL_TYPE_STRING);
				objectVo.setCode(cell0.toString().equals("")?null:cell0.toString());
			}
			if(cell1 != null){
				cell1.setCellType(HSSFCell.CELL_TYPE_STRING);
				objectVo.setName(cell1.toString().equals("")?null:cell1.toString());
			}
			if(cell2 != null){
				cell2.setCellType(HSSFCell.CELL_TYPE_STRING);
				objectVo.setSpellNo(cell2.toString().equals("")?null:cell2.toString());
			}
			objExcelList.add(objectVo);
		}
	}
	

	private void analyMaster(HSSFWorkbook wb, List<MasterExcelVo> mstExcelList) {
		HSSFSheet mstSheet = wb.getSheetAt(1);
		int rowSize = mstSheet.getLastRowNum() + 1;
		for (int j = 0; j < rowSize; j++) {
			if(j < 1){
				continue;
			}
			Row row = mstSheet.getRow(j);
			if(row == null){
				continue;
			}
			MasterExcelVo mastertVo = new MasterExcelVo();
			Cell cell0 = row.getCell(0);
			Cell cell1 = row.getCell(1);
			Cell cell2 = row.getCell(2);
			Cell cell3 = row.getCell(3);
			Cell cell4 = row.getCell(4);
			Cell cell5 = row.getCell(5);
			Cell cell6 = row.getCell(6);
			if(cell0 != null){
				cell0.setCellType(HSSFCell.CELL_TYPE_STRING);
				mastertVo.setObjectCd(cell0.toString().equals("")?null:cell0.toString());
			}
			if(cell1 != null){
				cell1.setCellType(HSSFCell.CELL_TYPE_STRING);
				mastertVo.setObjectNm(cell1.toString().equals("")?null:cell1.toString());
			}
			if(cell2 != null){
				cell2.setCellType(HSSFCell.CELL_TYPE_STRING);
				mastertVo.setCode(cell2.toString().equals("")?null:cell2.toString());
			}
			if(cell3 != null){
				cell3.setCellType(HSSFCell.CELL_TYPE_STRING);
				mastertVo.setName(cell3.toString().equals("")?null:cell3.toString());
			}
			if(cell4 != null){
				cell4.setCellType(HSSFCell.CELL_TYPE_STRING);
				mastertVo.setDes(cell4.toString().equals("")?null:cell4.toString());
			}
			if(cell5 != null){
				cell5.setCellType(HSSFCell.CELL_TYPE_STRING);
				mastertVo.setSpellNo(cell5.toString().equals("")?null:cell5.toString());
			}
			if(cell6 != null){
				cell6.setCellType(HSSFCell.CELL_TYPE_STRING);
				mastertVo.setSortNo(cell6.toString().equals("")?null:cell6.toString());
			}
			mstExcelList.add(mastertVo);
		}
	}

}
