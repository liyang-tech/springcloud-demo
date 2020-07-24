package com.wcg.caoxian.bes.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import com.wcg.caoxian.bes.dao.CustomerImageMapper;
import com.wcg.caoxian.bes.vo.custvo.CustImageVo;
import com.wcg.caoxian.bes.vo.custvo.CustomerImageVo;
import com.wcg.caoxian.sdk.bean.Result;
import com.wcg.caoxian.sdk.exception.ErrorHandler;

@Service("customerImageService")
public class CustomerImageService {
	
	@Autowired
	private CustImageService custImageService;
	
	@Autowired
	private CustomerImageMapper customerImageMapper;
	
	@Autowired
	private ImageChangeSizeService imageChangeSizeService;
	
	/**
	 * @Title: searchImage
	 * @Description: 客户照片信息查询
	 * @author 李洋  liyang
	 * @data 2018年6月22日 下午4:01:35
	 * @return Result<CustImageVO>
	 */
	public Result<List<CustImageVo>> searchImage(String customerCd, String imageTypeCd) {
		//1.根据客户编码和照片类型编码获取照片的id
		List<String> fileIds = customerImageMapper.searchCustomerImage(customerCd,imageTypeCd);
		List<CustImageVo> customerImageList = new ArrayList<CustImageVo>();
		for (String fileId : fileIds) {
			CustImageVo custImageVo=custImageService.findById(fileId);
			customerImageList.add(custImageVo);
		}
		return new Result<List<CustImageVo>>("0", customerImageList);
	}
	
	/**
	 * @Title: updateCustomerImage
	 * @Description: 客户照片信息更新
	 * @author 李洋  liyang
	 * @param request 
	 * @data 2018年6月22日 上午10:55:59
	 * @return Result<String>
	 */
	@Transactional
	public Result<String> updateCustomerImage(String customerCd, CustImageVo custImageVo, HttpServletRequest request, HttpHeaders headers) {
		//1.base64解码，将图片信息转换为文件信息
		byte[] imageDecodeArray = Base64Utils.decodeFromString(custImageVo.getImage());
		if(imageDecodeArray.length >= 2 * 1024 * 1024){
			ErrorHandler.reportError("太大了");
		}
		//获取头像的id
		List<String> baseIds = customerImageMapper.searchCustomerImage(customerCd,"2");
		List<String> largeIds = customerImageMapper.searchCustomerImage(customerCd,"3");
		List<String> smallIds = customerImageMapper.searchCustomerImage(customerCd,"4");
		//原始头像
		String newbaseId = null;
		if(baseIds == null || baseIds.isEmpty()){
			newbaseId = UUID.randomUUID().toString().replaceAll("-", "");
		}else{
			newbaseId = baseIds.get(0);
		}
		//大头像
		String newlargeId = null;
		if(largeIds == null || largeIds.isEmpty()){
			newlargeId = UUID.randomUUID().toString().replaceAll("-", "");
		}else{
			newlargeId = largeIds.get(0);
		}
		//小头像
		String newsmallId = null;
		if(smallIds == null || smallIds.isEmpty()){
			newsmallId = UUID.randomUUID().toString().replaceAll("-", "");
		}else{
			newsmallId = smallIds.get(0);
		}
		//创建各个头像的目录
		//缓存文件父目录
		String parentDirectory = "uploadPath";
		//缓存本次请求文件目录
		String fileDirectory = new Date().getTime() + "";
		String basePhotoName = fileDirectory + File.separator + "1_image.jpg";
		//服务器真是路径
		String realPath = request.getServletContext().getRealPath(parentDirectory);
		String basePhotoPath = realPath + File.separator + basePhotoName;
		//创建父目录
		File file = new File(basePhotoPath);
		createParentDirs(file);
		//缓存原始头像
		imageChangeSizeService.saveBaseImage(imageDecodeArray, file);
		//缓存大头像目录
		String largePhotoPath = realPath + File.separator + fileDirectory + File.separator + "large" + File.separator + ".jpg";
		createParentDirs(new File(largePhotoPath));
		//缓存小头像
		String smallPhotoPath = realPath + File.separator + fileDirectory + File.separator + "small" + File.separator + ".jpg";
		createParentDirs(new File(smallPhotoPath));
		//压缩头像，保存mongo
		long baseLength = file.length();
		try {
			if(baseLength > 2 * 1024 * 1024){
				//压缩大头像
				imageChangeSizeService.changeLargeImageSize(file, largePhotoPath);
				//压缩小头像
				imageChangeSizeService.changeSmallImageSize(file, smallPhotoPath);
				//保存到mongo
				saveOrUpdateImageToMongo(newbaseId, basePhotoPath);
				saveOrUpdateImageToMongo(newlargeId, largePhotoPath);
				saveOrUpdateImageToMongo(newsmallId, smallPhotoPath);
			}else if(baseLength > 200 * 1024){
				//压缩小头像
				imageChangeSizeService.changeSmallImageSize(file, smallPhotoPath);
				//保存到mongo
				saveOrUpdateImageToMongo(newbaseId, basePhotoPath);
				saveOrUpdateImageToMongo(newlargeId, basePhotoPath);
				saveOrUpdateImageToMongo(newsmallId, smallPhotoPath);
			}else{
				//保存到mongo
				saveOrUpdateImageToMongo(newbaseId, basePhotoPath);
				saveOrUpdateImageToMongo(newlargeId, basePhotoPath);
				saveOrUpdateImageToMongo(newsmallId, basePhotoPath);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ErrorHandler.reportError("00");
		}finally {
			deleteAllFiles(new File(realPath));
		}
		//如果原先文件id不存在，保存一份新的
		if(baseIds == null || baseIds.isEmpty()){
			saveCustomerImage(customerCd, newbaseId, "2");
		}
		if(largeIds == null || largeIds.isEmpty()){
			saveCustomerImage(customerCd, newlargeId, "3");
		}
		if(smallIds == null || smallIds.isEmpty()){
			saveCustomerImage(customerCd, newsmallId, "4");
		}
		return new Result<String>("0");
	}


	private void createParentDirs(File file) {
		if(file.getParentFile() != null || !file.getParentFile().isDirectory()){
			file.getParentFile().mkdirs();
		}
	}

	private void saveOrUpdateImageToMongo(String id, String photoPath) {
		String base64ImageStr = readFileToBase64(photoPath);
		CustImageVo custImageVo = new CustImageVo();
		custImageVo.setId(id);
		custImageVo.setImage(base64ImageStr);
		custImageService.save(custImageVo);
	}

	private String readFileToBase64(String photoPath) {
		InputStream in = null;
		byte[] data = null;
		try {
			in = new FileInputStream(photoPath);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
			ErrorHandler.reportError("ddd");
		}
		return Base64Utils.encodeToString(data);
	}
	
	private void deleteAllFiles(File file) {
		if(file == null){
			return;
		}
		if(file.exists()){
			if(file.isFile()){
				file.delete();
			}else if(file.isDirectory()){
				File[] files = file.listFiles();
				if(files != null){
					for (File file2 : files) {
						deleteAllFiles(file2);
					}
				}
				file.delete();
			}
		}
	}
	

	private void saveCustomerImage(String customerCd, String fileId, String imageTypeCd) {
		CustomerImageVo customerImageVo = new CustomerImageVo();
		customerImageVo.setCustomerCd(customerCd);
		customerImageVo.setFileId(fileId);
		customerImageVo.setImageTypeCd(imageTypeCd);
		customerImageMapper.saveCustomerImage(customerImageVo);
	}
	
}
