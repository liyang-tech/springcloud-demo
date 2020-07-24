package com.wcg.caoxian.sdk.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CodeProperties {
	
	
	private static Logger logger = LoggerFactory.getLogger(CodeProperties.class);
	
	private static String PUBLIC = "public.properties";
	private static String PRIVATE = "/private.properties";

	private static CodeProperties instance = null;
	
	Properties properties = new Properties();
	
	private CodeProperties(){
		try {
			InputStream publicPro = this.getClass().getResourceAsStream(PUBLIC);
			properties.load(new InputStreamReader(publicPro, "UTF-8"));
		} catch (IOException e) {
			logger.info("共有属性文件加载失败");
		}
		try {
			InputStream privatePro = this.getClass().getResourceAsStream(PRIVATE);
			properties.load(new InputStreamReader(privatePro, "UTF-8"));
		} catch (IOException e) {
			logger.info("私有属性文件加载失败");
		} catch (Exception e) {
			logger.info("私有属性文件不存在");
		}
	}
	
	public static synchronized CodeProperties getInstance(){
		if(instance == null){
			instance = new CodeProperties();
		}
		return instance;
	}
	
	public String getValue(String code){
		return getValue(code, new String[]{});
	}

	public String getValue(String code, String... parms) {
		Object obj = properties.get(code);
		if(obj == null){
			return null;
		}
		String strValue = obj.toString();
		for (int i = 0; i < parms.length; i++) {
			strValue = strValue.replace("{"+i+"}", parms[i]);
		}
		return strValue;
	}
	
}
