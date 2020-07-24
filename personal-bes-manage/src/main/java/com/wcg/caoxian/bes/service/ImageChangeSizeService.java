package com.wcg.caoxian.bes.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.springframework.stereotype.Service;

import com.wcg.caoxian.sdk.exception.ErrorHandler;

import net.coobird.thumbnailator.Thumbnails;

@Service("imageChangeSizeService")
public class ImageChangeSizeService {

	/**
	 * @Title: saveBaseImage
	 * @Description: 保存图片到缓存
	 * @author 李洋  liyang
	 * @data 2018年6月23日 上午11:46:11
	 * @return void
	 */
	public void saveBaseImage(byte[] imageDecodeArray, File file){
		FileOutputStream out = null;
		FileChannel fc = null;
		
		try {
			ByteBuffer bbf = ByteBuffer.wrap(imageDecodeArray);
			out = new FileOutputStream(file);
			fc = out.getChannel();
			fc.write(bbf);
		} catch (IOException e) {
			e.printStackTrace();
			ErrorHandler.reportError("");
		} finally {
			try {
				if(fc != null){
					fc.close();
				}
				if(out != null){
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				ErrorHandler.reportError("");
			}
		}
	}
	
	/**
	 * @Title: changeLargeImageSize
	 * @Description: 压缩大头像
	 * @author 李洋  liyang
	 * @data 2018年6月25日 下午1:21:09
	 * @return void
	 */
	public void changeLargeImageSize(File file, String largeImagePath){
		try {
			long baselength = file.length();
			long largelength = baselength;
			while(largelength > 2.2*1024*1024){
				double accuracy = getLargeAccuracy(largelength/1024);
				File largeImageFile = new File(largeImagePath);
				if(largelength == baselength){
					Thumbnails.of(file).scale(accuracy).outputQuality(accuracy).toFile(largeImageFile);
				}else{
					Thumbnails.of(largeImagePath).scale(accuracy).outputQuality(accuracy).toFile(largeImageFile);
				}
				largelength = largeImageFile.length();
			}
		} catch (IOException e) {
			e.printStackTrace();
			ErrorHandler.reportError("22");
		}
	}
	/**
	 * @Title: getLargeAccuracy
	 * @Description: 大头像压缩进行实践调整的精度
	 * @author 李洋  liyang
	 * @data 2018年6月25日 下午1:21:32
	 * @return double
	 */
	private double getLargeAccuracy(long size){
		double accuracy;
		if(size < 2700){
			accuracy = 0.97;
		}else if(size < 3275){
			accuracy = 0.93;
		}else if(size < 4096){
			accuracy = 0.85;
		}else if(size < 5120){
			accuracy = 0.8;
		}else{
			accuracy = 0.6;
		}
		return accuracy;
	}
	
	
	public void changeSmallImageSize(File file, String smallImagePath){
		try {
			long baselength = file.length();
			long smalllength = baselength;
			while(smalllength > 230*1024){
				double accuracy = getSmallAccuracy(smalllength/1024);
				File smallImageFile = new File(smallImagePath);
				if(smalllength == baselength){
					Thumbnails.of(file).scale(accuracy).outputQuality(accuracy).toFile(smallImageFile);
				}else{
					Thumbnails.of(smallImagePath).scale(accuracy).outputQuality(accuracy).toFile(smallImageFile);
				}
				smalllength = smallImageFile.length();
			}
		} catch (IOException e) {
			e.printStackTrace();
			ErrorHandler.reportError("22");
		}
	}
	/**
	 * @Title: getSmallAccuracy
	 * @Description: 小头像压缩进行实践调整的精度
	 * @author 李洋  liyang
	 * @data 2018年6月25日 下午1:23:21
	 * @return double
	 */
	private double getSmallAccuracy(long size){
		double accuracy;
		if(size < 300){
			accuracy = 0.8;
		}else if(size < 600){
			accuracy = 0.7;
		}else if(size < 1024){
			accuracy = 0.6;
		}else if(size < 2047){
			accuracy = 0.55;
		}else if(size < 3275){
			accuracy = 0.5;
		}else{
			accuracy = 0.44;
		}
		return accuracy;
	}
	
}
