package com.wcg.caoxian.sdk.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64FileUtil {

	/**
	 * 编码
	 * @param str
	 * @return string
	 * @author liyang
	 * 2017年3月28日
	 */
	@SuppressWarnings("restriction")
	public static String encodeBase64(byte[] str){
		return new BASE64Encoder().encode(str);
	}
	
	
	/**
	 * 将文件转成base64字符串
	 * @param path 文件路径
	 * @return string
	 * @throws Exception
	 * @author liyang
	 * 2017年3月28日
	 */
	@SuppressWarnings("restriction")
	public static String encodeBase64File(String path) throws Exception{
		File file = new File(path);
		FileInputStream inputFile = new FileInputStream(file);
		byte[] buffer = new byte[(int) file.length()];
		inputFile.read(buffer);
		inputFile.close();
		return new BASE64Encoder().encode(buffer);
	}
	
	
	/**
	 * 将base64字符串解码保存文件
	 * @param base64Code
	 * @param targetPath
	 * @throws Exception
	 * @author liyang
	 * 2017年3月28日
	 */
	@SuppressWarnings("restriction")
	public static void decoderBase64File(String base64Code, String targetPath) throws Exception{
		byte[] buffer = new BASE64Decoder().decodeBuffer(base64Code);
		FileOutputStream out = new FileOutputStream(targetPath);
		out.write(buffer);
		out.close();
	}
	
	
	/**
	 * 将base64字符串保存文本文件
	 * @param base64Code
	 * @param targetPath
	 * @throws Exception
	 * @author liyang
	 * 2017年3月28日
	 */
	public static void toFile(String base64Code, String targetPath) throws Exception{
		byte[] buffer = base64Code.getBytes();
		FileOutputStream out = new FileOutputStream(targetPath);
		out.write(buffer);
		out.close();
	}
	
	
	public static void main(String[] args) {
		try {
			String base64Code = encodeBase64File("E:\\XX0070973 (7).jpg");
			System.out.println(base64Code);
			//decoderBase64File(base64Code, "E:\\XX0070973(7)1.jpg");
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*File f = new File("d:" + File.separator+"adc.txt");
		 Writer out=new FileWriter(f);
		 String str="Hello World";
		 out.write(str);
		 out.close();*/
	}
	
	
	
}
