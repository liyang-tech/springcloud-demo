package com.wcg.caoxian.bes.common.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.wcg.caoxian.bes.common.vo.ExcelColumnVo;
import com.wcg.caoxian.bes.common.vo.ExcelVo;
import com.wcg.caoxian.bes.common.vo.ExcelVoList;
import com.wcg.caoxian.sdk.exception.ErrorHandler;

public class ExcelListUtil {

	@SuppressWarnings("rawtypes")
	public static void exportExcel(ExcelVoList excelVos, HttpServletRequest request, HttpServletResponse response, String fileName){
		//接收excelvo
		List<ExcelVo> excelVoList = excelVos.getExcelVoList();
		HSSFWorkbook hw = new HSSFWorkbook();
		for (ExcelVo excelVo : excelVoList) {
			List<Object> data = excelVo.getData();
			HSSFSheet sheet = hw.createSheet(excelVo.getSheetName());
			List<ExcelColumnVo> columns = excelVo.getColumns();
			//设置首行格式
			HSSFFont titleFont = hw.createFont();
			titleFont.setFontName("宋体");
			titleFont.setFontHeightInPoints((short) 11);
			titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			
			HSSFCellStyle titleStyle = hw.createCellStyle();
			titleStyle.setFont(titleFont);
			titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			titleStyle.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
			titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			
			HSSFPalette palette = hw.getCustomPalette();
			palette.setColorAtIndex(HSSFColor.LIGHT_ORANGE.index, (byte)255, (byte)211, (byte)155);
			
			//设置表格格式
			HSSFFont titleFont1 = hw.createFont();
			titleFont1.setFontName("宋体");
			titleFont1.setFontHeightInPoints((short) 11);
			titleFont1.setItalic(true);
			
			HSSFCellStyle titleStyle1 = hw.createCellStyle();
			titleStyle1.setFont(titleFont1);
			titleStyle1.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			titleStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			titleStyle1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			titleStyle1.setBorderRight(HSSFCellStyle.BORDER_THIN);
			titleStyle1.setBorderTop(HSSFCellStyle.BORDER_THIN);
			
			HSSFRow row = sheet.createRow(0);
			for (int i = 0; i < columns.size(); i++) {
				HSSFCell creatCell = row.createCell(i);
				creatCell.setCellStyle(titleStyle);
				creatCell.setCellValue(columns.get(i).getTitle());
				sheet.setColumnWidth(i,(columns.get(i).getWidth()!=null?columns.get(i).getWidth():200));
			}
			if(data != null){
				for (int i = 0, length = data.size(); i < length; i++) {
					Object object = data.get(i);
					HSSFRow row1 = sheet.createRow(i+1);
					for (int j = 0; j < columns.size(); j++) {
						HSSFCell createCell = row1.createCell(j);
						createCell.setCellStyle(titleStyle1);
						Object value = getValue(object, columns.get(j).getField());
						if(value != null){
							createCell.setCellValue(value.toString());
						}
					}
				}
			}
		}
		
		response.setContentType("application/vnd.ms-excel");
		String userAgent = request.getHeader("USER-AGENT");
		if(userAgent != null){
			try {
				if(userAgent.contains("Mozilla")){
					fileName = new String(fileName.getBytes(),"ISO8859-1");
				}else{
					fileName = URLEncoder.encode(fileName, "UTF-8");
				}
			} catch (UnsupportedEncodingException e) {
				ErrorHandler.reportError("1", "转码异常", e);
			}
		}
		
		response.setHeader("Content-disposition", "attachment;filename="+fileName);
		ServletOutputStream outputstream = null;
		try {
			outputstream = response.getOutputStream();
			hw.write(outputstream);
			outputstream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(outputstream != null){
				try {
					outputstream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return;
	}

	private static Object getValue(Object object, String field) {
		if(object == null){
			return null;
		}
		Field declaredFiled;
		try {
			declaredFiled = object.getClass().getDeclaredField(field);
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
			return null;
		}
		declaredFiled.setAccessible(true);
		Object obj;
		try {
			obj = declaredFiled.get(object);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
		declaredFiled.setAccessible(false);
		return obj;
	}
	
}
